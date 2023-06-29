package com.sci.bpm.controller.item;

import java.math.BigDecimal;
import java.util.*;

import com.sci.bpm.db.model.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.sci.bpm.command.mi.ItemCommand;
import com.sci.bpm.controller.base.SciBaseController;
import com.sci.bpm.service.item.PurchaseItemService;
import com.sci.bpm.service.mi.MaterialIndentService;

@Controller("itemcont")
public class ITemMasterController extends SciBaseController {

	@Autowired
	private PurchaseItemService service;
	
	public Event addPurchItem(RequestContext context) {
		
		return null;
	}
	@Autowired
	private MaterialIndentService miservice;
	@Override
	public Event setupForm(RequestContext context) throws Exception {
		setFormObjectClass(ItemCommand.class);
		setFormObjectName("itemcommand");
		
		return super.setupForm(context);
	}
	public Event addNewItem(RequestContext context) throws Exception {
		
		ItemCommand command = (ItemCommand) getFormObject(context);
		String estcost[] =  command.getMicost();
		String miremarks[] = command.getMiremarks();
		String miquantity[] = command.getMiquantity();
		SciPurchItemMaster  itemmaster = new SciPurchItemMaster();
		BeanUtils.copyProperties(itemmaster,command);
		List<SciMatindMaster> milist = (List)context.getFlowScope().get("itemmilist");
		List<SciMatindMaster> childMiList = (List)context.getFlowScope().get("childMiList");
		List<SciMatindMaster> updatedMiList = new ArrayList<SciMatindMaster>();
		String itemCode = null;
		 int idx = 0;
		 double totalQuantity = 0.000;
		 double totalCost = 0.000;
		 List<SciMatindMaster> datalist = new ArrayList<SciMatindMaster>();
		 List<SciMatindMaster> splitdatalist = new ArrayList<SciMatindMaster>();
		 boolean checkrawmi = checkRawMI(command.getRawmiid());
		 if(!checkrawmi) {
			 throw new Exception("Raw mi are not valid issued MI");
		 }
		 itemmaster.setRawmis(command.getRawmiid());
		 Set<SciAddMatInfoDocsEntity> addMatInfoDocsEntities = new HashSet<SciAddMatInfoDocsEntity>();
		Set<SciMiMaterialAddinfoEntity> sciMiMaterialAddinfoEntities = new HashSet<SciMiMaterialAddinfoEntity>();
		for(SciMatindMaster m : milist){
			SciItemmiDetails midetails = new SciItemmiDetails();
			m = miservice.loadMI(m.getSeqMiId());
			midetails.setSeqMiId(m.getSeqMiId());
			midetails.setSciPurchItemMaster(itemmaster);
			BigDecimal estquant = new BigDecimal(miquantity[idx]);
			if(estquant.floatValue() <= 0) {
				throw new Exception("Estimated Quantity Cannot be null");
			}
			else if(estquant.doubleValue() > m.getMatQty().doubleValue()) {
				throw new Exception("Estimated Quantity Cannot be greater than the Original Quantity");
			}
			else if(estquant.doubleValue() < m.getMatQty().doubleValue()) {
			double value = m.getMatQty().doubleValue()-estquant.doubleValue();
				SciMatindMaster remain = new SciMatindMaster();
				//BeanUtilsBean.getInstance().getConvertUtils().re
				BeanUtilsBean.getInstance().copyProperties(remain, m);
				remain.setSeqMiId(null);
				remain.setMatQty(new BigDecimal(value));
				
				remain.setInsertedBy("PO_SPLIT");
				remain.setInsertedDate(new Date());
				 for(SciAddMatInfoDocsEntity addMatInfoDocsEntity: m.getMatInfoDocsEntities()) {
				 	SciAddMatInfoDocsEntity entity = new SciAddMatInfoDocsEntity();
				 	BeanUtils.copyProperties(entity,addMatInfoDocsEntity);
					 addMatInfoDocsEntities.add(entity);
				 }

				 for(SciMiMaterialAddinfoEntity sciMiMaterialAddinfoEntity: m.getMatInfos()) {
				 	SciMiMaterialAddinfoEntity sciMiMaterialAddinfoEntity1 = new SciMiMaterialAddinfoEntity();
				 	sciMiMaterialAddinfoEntity1.setSeqMiId(remain);
				 	sciMiMaterialAddinfoEntity1.setInsertedBy(sciMiMaterialAddinfoEntity.getInsertedBy());
				 	sciMiMaterialAddinfoEntity1.setInsertedDate(sciMiMaterialAddinfoEntity.getInsertedDate());
				 	sciMiMaterialAddinfoEntity1.setAddInfoValue(sciMiMaterialAddinfoEntity.getAddInfoValue());
				 	sciMiMaterialAddinfoEntity1.setAddInfoLabel(sciMiMaterialAddinfoEntity.getAddInfoLabel());
					 sciMiMaterialAddinfoEntities.add(sciMiMaterialAddinfoEntity1);
				 }
				remain.setMatInfoDocsEntities(addMatInfoDocsEntities);
				 remain.setMatInfos(sciMiMaterialAddinfoEntities);
				remain.setUpdatedBy(getUserPreferences().getUserID());
				splitdatalist.add(remain);
				
			}
			m.setUnitCost(new BigDecimal(estcost[idx]));

			m.setMatQty(estquant);
			if(m.getUnitCost().floatValue() <= 0) {
				throw new Exception("Estimated Cost Cannot be null");
			}
		
					
			
			m.setPurStatus(getLookupservice().loadIDData("MI_ITEMISED"));
			m.setUpdatedBy(getUserPreferences().getUserID());
			m.setUpdatedDate(new java.util.Date());
			m.setPoDetails(miremarks[idx]);
			m.setUnitCostDate(new java.util.Date());
			totalQuantity = totalQuantity + m.getMatQty().doubleValue();
			totalCost = totalCost + (m.getMatQty().doubleValue() * m.getUnitCost().doubleValue());
			itemCode = m.getMatcode();
			datalist.add(m);
			itemmaster.addMidetails(midetails);
			idx++;
		}
		itemmaster.setItemType(0L);
		itemmaster.setItemQty(String.valueOf(totalQuantity));
		itemmaster.setItemEstimatedCost(new Float(totalCost));
		itemmaster.setItemOrderDate(new java.util.Date());
		itemmaster.setUpdatedBy(getUserPreferences().getUserID());
		itemmaster.setUpdatedDate(new java.util.Date());
		itemmaster.setItemStatus(getLookupservice().loadIDData("PITEM_OPEN"));
		itemmaster.setItemCode(itemCode) ;
		if(command.getItemDenom().equals("")) {
			throw new Exception("ITem denom cannot be blank");
		}
		itemmaster.setItemDenom(command.getItemDenom());
		int jdx = 0;
		int totalPercentage = 0;
		if(command.getMiCostPercentage() != null && command.getMiCostPercentage().length > 0) {
			totalPercentage = Arrays.stream(command.getMiCostPercentage()).mapToInt(dti -> dti.intValue() ).sum();
			if(totalPercentage != 100) {
				throw new Exception("The Child MI's cost ratio should be totalling to 100");
			}
			if(childMiList.size() == command.getMiCostPercentage().length) {
				for (SciMatindMaster mis : childMiList) {
						SciMatindMaster dbMi = this.miservice.loadMI(mis.getSeqMiId());
					dbMi.setUpdatedBy(getUserPreferences().getUserID());
					dbMi.setUpdatedDate(new java.util.Date());
					dbMi.setCostPercentageRation(command.getMiCostPercentage()[jdx].longValue());
					BigDecimal cost = new BigDecimal((estcost[idx-1]));
					dbMi.setUnitCost(new BigDecimal((cost.doubleValue()) * (dbMi.getCostPercentageRation().longValue())/100));
					dbMi.setPurStatus(getLookupservice().loadIDData("MI_ITEMISED"));
					updatedMiList.add(dbMi);
					jdx++;
				}
			}
		}





				service.addNewItem(itemmaster, datalist,splitdatalist,new SciRawMIDetails(),updatedMiList);
				String rawmis  = itemmaster.getRawmis();
				
				String[] rawmisarr = StringUtils.split(rawmis,",");
				for(SciMatindMaster m: datalist) {
					
					if(rawmisarr != null) {
						for(String rawmi:rawmisarr) {
							SciRawMIDetails rmidetails = new SciRawMIDetails();

							rmidetails.setRawMIMaster(miservice.loadMI(Long.parseLong(rawmi)));
							rmidetails.setSubcontractMIMaster(m);
							service.addRawMI(rmidetails);
							 
						}
						}
				}
				
		return success();
	}
	
	public Event selectPOItem(RequestContext context) throws Exception {
		ItemCommand command = (ItemCommand) getFormObject(context);
		List poitemlist = null;
		if(context.getFlowScope().get("poItemlist") == null) {
			 poitemlist = new ArrayList();
		}
		else {
			poitemlist = (List)context.getFlowScope().get("poItemlist");
		}
		
		
		String[] itemlist = command.getItemlist();
		List fullitemlist = (List) context.getFlowScope().get("itemlist");
		for(int idx=0;idx < itemlist.length;idx++) {
			poitemlist.add(fullitemlist.get(Integer.parseInt(itemlist[idx])-1));
		}
		context.getFlowScope().put("poItemlist", poitemlist);
		return success();
	}
	
	
	public Event searchItems(RequestContext context) throws Exception {
		ItemCommand command = (ItemCommand) getFormObject(context);
		List<SciPurchItemMaster> itemlist = service.search(command);
		context.getFlowScope().put("itemlist", itemlist);
		context.getFlowScope().put("openITEM", getLookupservice().loadIDData("PITEM_OPEN"));
		return success();
	}
	
	public Event loadItemDesc(RequestContext context) throws Exception {
		ItemCommand command = (ItemCommand) getFormObject(context);
		String matspec = (String) context.getFlowScope().get("itemdesc");
		command.setItemDescription(matspec);
		return success();
	}
	public Event selecttempItem(RequestContext context) throws Exception {
		ItemCommand command = (ItemCommand) getFormObject(context);
		String[] itemlist = command.getItemlist();
		List poitemlist = null;
		if(context.getFlowScope().get("poItemlist") == null) {
			 poitemlist = new ArrayList();
		}
		else {
			poitemlist = (List)context.getFlowScope().get("poItemlist");
		}
		
		List fullitemlist = (List) context.getFlowScope().get("itemlist");
		
		for(int idx=0;idx<itemlist.length;idx++) {
			poitemlist.add(fullitemlist.get(Integer.parseInt(itemlist[idx])-1));
		}
		context.getFlowScope().put("poItemlist", poitemlist);
		return success();
	}
	
	
	public boolean checkRawMI(String rawMiid) {
		boolean check = true;
		String[] rawmis = StringUtils.split(rawMiid,",");
		for(String rawmi:rawmis) {
			
			 check = service.checkRawMI(Long.parseLong(rawmi));
			if(!check) {
				break;
			}
		}
		return check;
	}

	
	

}
