package com.sci.bpm.controller.po;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sci.bpm.command.design.DocPropery;
import com.sci.bpm.db.model.SciPurchaseDocs;

public class PurchaseDocStreamerServlet extends HttpServlet {

	public static final String OPENED_DOCS_KEY = "openedpurchasedocs";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String idkey = req.getParameter("idkey");
		BufferedOutputStream bos = null;
		Long idkeynumber = new Long(idkey);
		SciPurchaseDocs selectedDetails = null;
		ApplicationContext cont = WebApplicationContextUtils.getRequiredWebApplicationContext(req.getSession(false).getServletContext());
		DocPropery dbean = (DocPropery) cont.getBean("docfilelocation");
		String docfileloc = dbean.getDocfilelocation();
		try {
			List<SciPurchaseDocs> doclist = (List) req.getSession(false)
					.getAttribute("purchasedocs");
			for (SciPurchaseDocs details : doclist) {
				if (details.getSeqPurchDocId().longValue() == idkeynumber
						.longValue()) {
					selectedDetails = details;
					break;
				}
			}
			String filename = selectedDetails.getOriginalDoc();
			String filename2 = filename.replaceAll("[ \t]", "");
			resp.setHeader("content-disposition", "attachment; filename=" + filename2);

			resp.setContentType(selectedDetails.getDocCnttype());
			bos = new BufferedOutputStream(resp.getOutputStream());

			String diskfile = "PODOC_" + selectedDetails.getSeqPurchDocId().toString() + "_" + filename;
			BufferedInputStream bis =
				new BufferedInputStream(new FileInputStream(new File(docfileloc + diskfile)));

			byte[] buf = new byte[4096];
			int n = 0;

			while ((n = bis.read(buf, 0, 4096)) != -1) {
				bos.write(buf, 0, n);
			}
			bis.close();

			// remember the documents this user has opened - checked before PO approval
			Map<Long, Date> opened = (Map<Long, Date>) req.getSession(false).getAttribute(OPENED_DOCS_KEY);
			if (opened == null) {
				opened = new HashMap<Long, Date>();
				req.getSession(false).setAttribute(OPENED_DOCS_KEY, opened);
			}
			opened.put(selectedDetails.getSeqPurchDocId(), new Date());
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (bos != null) {
				bos.close();
			}
		}

	}

}
