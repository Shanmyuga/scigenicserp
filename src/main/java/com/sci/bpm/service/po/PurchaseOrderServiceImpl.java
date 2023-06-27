package com.sci.bpm.service.po;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.sci.bpm.db.model.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sci.bpm.command.po.POCommand;
import com.sci.bpm.dao.item.ISciPurchItemMasterDAO;
import com.sci.bpm.dao.mi.ISciMatindMasterDAO;
import com.sci.bpm.dao.po.ISciPurchaseMastDAO;

@Transactional
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
	
	
	
	
	@Autowired
	private ISciPurchItemMasterDAO itemdao;

	@Autowired
	private ISciPurchaseMastDAO dao;

	@Autowired
	private ISciMatindMasterDAO matdao;

	@Override
	public SciPurchaseMast loadPOById(Long seqPurchId) {
		return dao.findById(seqPurchId);
	}

	public void addPurchOrder(SciPurchaseMast master,
							  List<SciPurchItemMaster> itemlist) {
		// TODO Auto-generated method stub
		dao.save(master);
		for (SciPurchItemMaster im : itemlist) {
			itemdao.update(im);

		}
	}

	public List<SciPurchaseMast> searchPOs(POCommand command) {

		List<SciPurchaseMast> purchaseMastList = this.dao.searchPO(command);
		for(SciPurchaseMast purchaseMast:purchaseMastList) {
			if(command.getSeqWorkId() != null) {
				Float workCost = this.dao.getCostByWork(purchaseMast.getSeqPurchId(), command.getSeqWorkId());
				purchaseMast.setWorkCost(workCost);
			}
		}

		return purchaseMastList;
	}

	public List searchMatCodePO(POCommand command) {
		return this.dao.searchMatCodePO(command);
	}

	public List<SciPurchItemMaster> loadPODetails(SciPurchaseMast master) {

		return itemdao.loadPODetails(master);
	}

	public void updatePOStatus(SciPurchaseMast master) {
		itemdao.updatePOStatus(master);

	}

	@Override
	public void updatePOStatusBy(SciPurchaseMast master) {

	}

	public List closePO(POCommand command) {
		// TODO Auto-generated method stub
		return dao.closePO(command);
	}

	public List viewRejected(POCommand command) {
		// TODO Auto-generated method stub
		return dao.viewRejected(command);
	}

	public void updateReject(SciRejectedMaterials mater,SciRejectMaterialAudit audit) {

		dao.updateReject(mater,audit);
	}

	public void addPurchOrder(SciPurchaseMast master,
			List<SciPurchItemMaster> itemlist, Long mistatus) {
		dao.save(master);
		for (SciPurchItemMaster im : itemlist) {
			itemdao.update(im);
			Set<SciItemmiDetails> miset = im.getSciItemmiDetailses();
			for (SciItemmiDetails itemmi : miset) {
				updateChildMi(master, mistatus, im, itemmi);

			}

		}

	}

	public void updatePOStatus(SciPurchaseMast master, Long mistatus) {
		// TODO Auto-generated method stub
		List<SciPurchItemMaster> imasterlist = itemdao.loadPODetails(master);
		itemdao.updatePOStatus(master);
		for (SciPurchItemMaster im : imasterlist) {
			
			Set<SciItemmiDetails> miset = im.getSciItemmiDetailses();
			for (SciItemmiDetails itemmi : miset) {
				updateChildMi(master, mistatus, im, itemmi);
				SciMatindMaster mi;

			}

		}
	}

	private void updateChildMi(SciPurchaseMast master, Long mistatus, SciPurchItemMaster im, SciItemmiDetails itemmi) {
		SciMatindMaster mi = matdao.findById(itemmi.getSeqMiId());

		if("Y".equals(mi.getIsGroupMiId())) {
			List<SciMatindMaster> childmis = matdao.loadChildMi(mi.getSeqMiId());
			for(SciMatindMaster childmi : childmis) {
				childmi.setPurStatus(mistatus);
				childmi.setUpdatedBy(im.getUpdatedBy());
				childmi.setUpdatedDate(im.getUpdatedDate());
				childmi.setPodueDate(master.getPurchaseDueDate());
				matdao.update(childmi);
			}
		}
		mi.setPurStatus(mistatus);
		mi.setUpdatedBy(im.getUpdatedBy());
		mi.setUpdatedDate(im.getUpdatedDate());
		matdao.updateMI(mi);
	}

	public void cancelPO(SciPurchaseMast master) {
		dao.cancelPO(master);
		
	}

	public void despatchPO(SciPurchaseMast master) {
		// TODO Auto-generated method stub
		dao.despatchPO(master);
	}

	public void addPaymentDetails(SciPaymentDetails details) {
		// TODO Auto-generated method stub
		dao.addPaymentDetails(details);
	}

	public List<SciPaymentDetails> loadPaymentDetails() {
		// TODO Auto-generated method stub
		return dao.loadPaymentDetails();
	}

	public SciVendorMaster loadSciVendorMaster(Long seqVendorID) {
		// TODO Auto-generated method stub
		return dao.loadSciVendorMaster(seqVendorID);
	}

	public void addInvoiceDetails(SciVendorInvoiceMaster vmaster) {
		// TODO Auto-generated method stub
		dao.addInvoiceDetails(vmaster);
	}

	public List<SciVendorInvoiceMaster> loadInvoiceDetails() {
		// TODO Auto-generated method stub
		return dao.loadInvoiceDetails();
	}

	public List<SciPurchaseMast> loadPODetails(Long seqVendorID) {
		// TODO Auto-generated method stub
		
		return dao.loadPODetails(seqVendorID);
	}

	@Override
	public List<SciRawMIDetails> loadSubContractMI(Long seqSubcontractMIId,Long seqVendorId,String stageDesc) {
		return dao.loadSubContractMI(seqSubcontractMIId,seqVendorId,stageDesc);
	}

	@Override
	public List<SciVendorInvoiceMaster> loadbillNo(Long seqVendorID) {
		// TODO Auto-generated method stub
		return dao.loadbillNo(seqVendorID);
	}

	@Override
	public String getWorkOrders(Long seqPurchId) {
		return dao.getWorkOrders(seqPurchId);
	}

	@Override
	public void addPurchaseVendorCost(SciVendorPurchaseCost purchaseCost) {
		// TODO Auto-generated method stub
		dao.addPurchaseVendorCost(purchaseCost);
	}

	@Override
	public List<SciVendorPurchaseCost> loadQuotations(String matDept,
			String matCategory) {
		// TODO Auto-generated method stub
		return dao.loadQuotations(matDept, matCategory);
	}

	@Override
	public List<SciRawMIDetails> loadMis(Long seqPurchId) {
		return dao.loadMis(seqPurchId);
	}


}
