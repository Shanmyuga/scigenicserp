package com.sci.bpm.controller.po;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.sci.bpm.command.po.POCommand;
import com.sci.bpm.command.po.PurchaseDocBean;
import com.sci.bpm.controller.base.SciBaseController;
import com.sci.bpm.db.model.SciPurchaseDocs;
import com.sci.bpm.db.model.SciPurchaseMast;
import com.sci.bpm.service.po.PurchaseOrderService;
import com.sci.bpm.service.task.DiskWriterJob;


@Controller("purchdoccont")
public class PurchaseDocController extends SciBaseController {

	@Autowired
	private PurchaseOrderService service;

	@Autowired
	private DiskWriterJob diskwriter;

	@Override
	public Event setupForm(RequestContext context) throws Exception {

		setFormObjectClass(PurchaseDocBean.class);
		setFormObjectName("purchdocbean");
		return super.setupForm(context);
	}

	/**
	 * Loads all purchase orders that are not yet approved (created or pending
	 * director approval) so documents can be attached before approval.
	 */
	public Event searchCreatedPO(RequestContext context) throws Exception {

		List<Long> statuses = new ArrayList<Long>();
		statuses.add(getLookupservice().loadIDData("PO_CREATED"));
		statuses.add(getLookupservice().loadIDData("PO_APPROVAL_PENDING"));
		List<SciPurchaseMast> polist = service.searchPOByStatus(statuses);
		context.getFlowScope().put("createdpolist", polist);
		return success();
	}

	/**
	 * Picks the purchase order chosen on the search page (pocommand.scipurchID).
	 */
	public Event selectPO(RequestContext context) throws Exception {

		POCommand command = (POCommand) context.getFlowScope().get("pocommand");
		List<SciPurchaseMast> polist = (List<SciPurchaseMast>) context
				.getFlowScope().get("createdpolist");
		SciPurchaseMast selected = null;
		if (command.getScipurchID() != null && polist != null) {
			for (SciPurchaseMast pm : polist) {
				if (pm.getSeqPurchId().longValue() == command.getScipurchID().longValue()) {
					selected = pm;
					break;
				}
			}
		}
		if (selected == null) {
			throw new Exception("Please select a purchase order.");
		}
		context.getFlowScope().put("selectedpurchase", selected);
		return success();
	}

	public Event addPurchaseDoc(RequestContext context) throws Exception {

		PurchaseDocBean bean = (PurchaseDocBean) getFormObject(context);

		MultipartFile file = bean.getFiledoc();
		if (file == null || file.isEmpty()) {
			throw new Exception("Please select a document file to upload.");
		}

		SciPurchaseDocs master = new SciPurchaseDocs();
		SciPurchaseMast pmaster = (SciPurchaseMast) context
				.getFlowScope().get("selectedpurchase");
		BeanUtils.copyProperties(master, bean);
		master.setSeqPurchId(pmaster.getSeqPurchId());
		master.setUpdatedBy(getUserPreferences().getUserID());
		master.setUpdatedDate(new java.util.Date());

		master.setPurchDoc(file.getBytes());

		master.setOriginalDoc(file.getOriginalFilename());
		master.setDocCnttype(file.getContentType());

		service.addPurchaseDocs(master);
		diskwriter.writePurchaseDocs();
		return success();
	}

	/**
	 * Loads the documents of the selected purchase order. Works for both
	 * the add docs flow (selectedpurchase) and the view PO flow (pocommand.scipurchID).
	 */
	public Event loadPurchaseDocList(RequestContext context) {

		Long seqPurchId = null;
		SciPurchaseMast pmaster = (SciPurchaseMast) context
				.getFlowScope().get("selectedpurchase");
		if (pmaster != null) {
			seqPurchId = pmaster.getSeqPurchId();
		} else {
			POCommand command = (POCommand) context.getFlowScope().get("pocommand");
			if (command != null) {
				seqPurchId = command.getScipurchID();
			}
		}
		if (seqPurchId == null) {
			return success();
		}

		List docList = service.getPurchaseDocs(seqPurchId);
		context.getExternalContext().getSessionMap().put("purchasedocs", docList);
		context.getFlowScope().put("purchasedoclist", docList);
		return success();
	}

}
