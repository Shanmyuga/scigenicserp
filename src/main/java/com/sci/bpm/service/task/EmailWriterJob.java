package com.sci.bpm.service.task;

import com.google.api.services.gmail.model.*;
import com.google.api.services.gmail.model.Message;
import com.sci.bpm.command.design.DocPropery;
import com.sci.bpm.db.model.SciEnquiryDetails;
import com.sci.bpm.db.model.SciEnquiryDocs;
import com.sci.bpm.db.model.SciEnquiryMaster;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.jobs.ee.jms.SendQueueMessageJob;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.QuartzJobBean;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import java.io.*;
import java.security.GeneralSecurityException;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;

import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EmailWriterJob extends QuartzJobBean  {

    private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "credentials.json";
    public void setTservice(TaskService tservice) {
        this.tservice = tservice;
    }

    private TaskService tservice;

    private DiskWriterJob diskWriterJob;

    private DocPropery docPropery;

    public DocPropery getDocPropery() {
        return docPropery;
    }

    public void setDocPropery(DocPropery docPropery) {
        this.docPropery = docPropery;
    }

    private  Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(this.docPropery.getDocfilelocation()+TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME)
                    .build();

            // Print the labels in the user's account.
            String user = "me";
            List<String> labelnamess = new ArrayList<String>();
            labelnamess.add("INBOX");
            ListMessagesResponse messagesResponse  = service.users().messages().list(user).setLabelIds(labelnamess).setQ("from:@scigenics.in is:unread").execute();
            if(messagesResponse == null || messagesResponse.getMessages() == null) {
                return;
            }
            System.out.println(messagesResponse.getMessages().size());
            List<SciEnquiryDocs> docs = new ArrayList<SciEnquiryDocs>();
            List<Message> messages = messagesResponse.getMessages();
            for(Message message:messages) {
                Message fullMessage = service.users().messages().get(user,message.getId()).setFormat("raw").execute();


                Message nonrawmessage = service.users().messages().get(user,message.getId()).setFormat("raw").execute();

                System.out.println(nonrawmessage.getRaw());

                Base64 base64Url = new Base64(true);
                byte[] emailBytes = base64Url.decodeBase64(fullMessage.getRaw());

                Properties props = new Properties();


                Session session = Session.getDefaultInstance(props, null);

                MimeMessage email = new MimeMessage(session, new ByteArrayInputStream(emailBytes));
                Arrays.stream(email.getHeader("From")).forEach(x-> System.out.println(x));
                String subject = null;
                    String[] array = email.getHeader("Subject");
                    if(array != null && array.length >0) {
                        subject = array[0];
                    }
                if (markAsUnread(service, user, message, subject == null, subject)) continue;

                String date = Arrays.stream(email.getHeader("Date")).findFirst().get();
                String from = Arrays.stream(email.getHeader("From")).findFirst().get();
                Pattern MY_PATTERN = Pattern.compile("ENQ_ID_(.*?)$");

                Matcher matcher = MY_PATTERN.matcher(subject);
                String data = null;
                if(matcher.find()) {
                     data = matcher.group(1);

                }
                SciEnquiryMaster master = tservice.loadEnquiryMaster(data);
                if (markAsUnread(service, user, message, master == null, subject)) continue;
                Calendar calendar =  Calendar.getInstance();
                calendar.add(Calendar.DATE,7);
                SciEnquiryDetails details = new SciEnquiryDetails();
                details.setActionDate(new Date());
                details.setSciEnquiryMaster(master);
                details.setActionTaken("Email update  " +from+ " on Enquiry -" + master.getEnqFullCode());
                details.setUpdatedBy("System");
                details.setActionTaken("Email Communication");
                details.setUpdatedDate(new Date());
                details.setBringForwardDate(calendar.getTime());
                details.setInsertedBy("System");
                details.setInsertedDate(new Date());
                SciEnquiryDetails savedDetails = tservice.addNewEnquiryActionDetail(details);
                String contentType = email.getContentType();
                String messageContent = "";
                if (contentType.contains("multipart")) {
                    // content may contain attachments
                    Multipart multiPart = (Multipart) email.getContent();
                    int numberOfParts = multiPart.getCount();
                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                            // this part is attachment
                            String fileName = part.getFileName();
                            fileName = fileName.replaceAll("\\s","");
                                SciEnquiryDocs doc = new SciEnquiryDocs();
                                doc.setDocCnttype(part.getContentType());
                            BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream()));
                            part.saveFile(docPropery.getDocfilelocation()+"savefiles"+File.separator+ savedDetails.getSeqEnqDetId()+"_"+fileName);
                            byte[] datafile = FileUtils.readFileToByteArray(new File(docPropery.getDocfilelocation()+"savefiles"+File.separator+ savedDetails.getSeqEnqDetId()+"_"+fileName));
                                FileUtils.deleteQuietly(new File(docPropery.getDocfilelocation()+"savefiles"+File.separator+ savedDetails.getSeqEnqDetId()+"_"+fileName));
                                doc.setEnqDocData(datafile);
                                doc.setUpdatedBy("System");
                                doc.setUpdatedDate(new Date());
                                doc.setSeqEnqId(master.getSeqEnqryId());
                                doc.setDocVersion("1.0");
                                doc.setEnqDocName(savedDetails.getSeqEnqDetId()+ "_"+fileName);
                                doc.setOriginalDoc( savedDetails.getSeqEnqDetId()+ "_"+fileName);

                                docs.add(doc);
                            System.out.println(fileName);
                           /* attachFiles += fileName + ", ";
                            part.saveFile(saveDirectory + File.separator + fileName);*/
                        } else {
                            // this part may be the message content
                            messageContent = part.getContent().toString();

                            BufferedReader reader = new BufferedReader(new InputStreamReader(part.getRawInputStream()));
                             data  =   reader.lines().collect(Collectors.joining());
                            int index  = data.indexOf("printable");
                            data =    data.substring(index+9,data.length());
                            data.replaceAll("=C2=A0","");
                            System.out.println(data);
                            SciEnquiryDocs doc = new SciEnquiryDocs();
                            doc.setDocCnttype("text/html");
                            doc.setEnqDocData(data.getBytes());
                            doc.setUpdatedDate(new Date());
                            doc.setUpdatedBy("System");
                            doc.setSeqEnqId(master.getSeqEnqryId());
                            from = from.replaceAll("\\s","");
                            from =  from.replaceAll("<(.*?)>","");
                            doc.setOriginalDoc(savedDetails.getSeqEnqDetId()+ "_"+"Email_Data_from_" + from+".html");
                            doc.setEnqDocName(savedDetails.getSeqEnqDetId()+ "_"+"Email_Data_from_" + from+".html");
                            docs.add(doc);
                        }
                    }


                } else if (contentType.contains("text/plain")
                        || contentType.contains("text/html")) {
                    Object content = email.getContent();
                    if (content != null) {
                        messageContent = content.toString();
                        int index  = messageContent.indexOf("printable");
                        data =    messageContent.substring(index+9,data.length());
                        SciEnquiryDocs doc = new SciEnquiryDocs();
                        doc.setDocCnttype("text/html");
                        doc.setEnqDocData(data.getBytes());
                        doc.setUpdatedDate(new Date());
                        doc.setUpdatedBy("System");
                        from = from.replaceAll("\\s","");
                        from =  from.replaceAll("<(.*?)>","");
                        doc.setOriginalDoc("Email_Data_from_" + from+".html");
                        doc.setEnqDocName("Email_Data_from_" + from+".html");
                        docs.add(doc);
                    }
                }
                List<String> labelsToRemove = new ArrayList<String>();
                labelsToRemove.add("UNREAD");
                ModifyMessageRequest request = new ModifyMessageRequest();
                request.setRemoveLabelIds(labelsToRemove);
                service.users().messages().modify(user,message.getId(),request).execute();
               tservice.addNewEnquiryDocs(docs);
               diskWriterJob.writeEnquiryDoc();
                docs.clear();
                break;
            }

            System.out.println("Docs "+ docs.size());


        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private boolean markAsUnread(Gmail service, String user, Message message, boolean b, String subject) throws IOException {
        if(b) {
            List<String> labelsToRemove = new ArrayList<String>();
            labelsToRemove.add("UNREAD");
            ModifyMessageRequest request = new ModifyMessageRequest();
            request.setRemoveLabelIds(labelsToRemove);
            service.users().messages().modify(user,message.getId(),request).execute();
            return true;
        }
        return false;
    }

    public DiskWriterJob getDiskWriterJob() {
        return diskWriterJob;
    }

    public void setDiskWriterJob(DiskWriterJob diskWriterJob) {
        this.diskWriterJob = diskWriterJob;
    }
}
