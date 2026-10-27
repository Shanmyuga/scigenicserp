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
		String milaborcost[] = command.getMilaborcost();
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
		 List<RawMiRow> rawMiRows = readRawMiRows(command);
		 for(RawMiRow row : rawMiRows) {
			 if(!service.checkRawMI(row.rawMiId)) {
				 throw new Exception("Raw MI " + row.rawMiId + " is not a valid issued MI");
			 }
		 }
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
			if(milaborcost != null && milaborcost.length > idx && milaborcost[idx] != null && !milaborcost[idx].isEmpty()) {
				m.setLaborCost(new BigDecimal(milaborcost[idx]));
			}
			boolean result = service.checkEstCost(m);
			if(!result) {
				throw new Exception("Bigger than the estimated cost");
			}
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
				for(SciMatindMaster m: datalist) {

					for(RawMiRow row : rawMiRows) {
						SciRawMIDetails rmidetails = new SciRawMIDetails();
						SciMatindMaster rawMiMaster = miservice.loadMI(row.rawMiId);

						rmidetails.setRawMIMaster(rawMiMaster);
						rmidetails.setSubcontractMIMaster(m);
						SciPurchaseMast rawMiPO = service.loadPurchaseOrderForMi(rawMiMaster.getSeqMiId());
						if(rawMiPO != null) {
							rmidetails.setSciVendorMaster(rawMiPO.getSciVendorMaster());
						}
						rmidetails.setMatDimension(rawMiMaster.getMatDimesion());
						rmidetails.setRawMaterialDesc(rawMiMaster.getMatDesc());
						rmidetails.setMatQty(row.quantity.floatValue());
						if(row.returnableQty != null) {
							rmidetails.setRetQty(row.returnableQty.floatValue());
						}
						service.addRawMI(rmidetails);
					}
				}
				
		return success();
	}
	
	/**
	 * "Load Raw Material Cost": raw material cost = sum of (raw MI unit cost * (raw MI quantity - returnable quantity))
	 * over the raw MI rows.
	 * A blank modified quantity is filled with the MI quantity, and the estimated unit cost of every MI row is
	 * raw material cost / total modified quantity.
	 */
	public Event loadRawMiCost(RequestContext context) throws Exception {
		ItemCommand command = (ItemCommand) getFormObject(context);
		List<SciMatindMaster> milist = (List)context.getFlowScope().get("itemmilist");
		List<RawMiRow> rawMiRows = readRawMiRows(command);
		if(rawMiRows.isEmpty()) {
			throw new Exception("Enter the Raw MI details to load the raw material cost");
		}
		BigDecimal rawMaterialCost = BigDecimal.ZERO;
		for(RawMiRow row : rawMiRows) {
			SciMatindMaster rawMiMaster = miservice.loadMI(row.rawMiId);
			if(rawMiMaster == null) {
				throw new Exception("Raw MI " + row.rawMiId + " does not exist");
			}
			if(rawMiMaster.getUnitCost() == null) {
				throw new Exception("Raw MI " + row.rawMiId + " has no unit cost");
			}
			// only the consumed quantity is costed: raw MI quantity less the returnable quantity
			BigDecimal consumedQty = row.returnableQty == null ? row.quantity : row.quantity.subtract(row.returnableQty);
			rawMaterialCost = rawMaterialCost.add(rawMiMaster.getUnitCost().multiply(consumedQty));
		}

		String[] miquantity = new String[milist.size()];
		String[] micost = new String[milist.size()];
		BigDecimal totalQuantity = BigDecimal.ZERO;
		for(int idx = 0; idx < milist.size(); idx++) {
			String entered = command.getMiquantity() != null && command.getMiquantity().length > idx
					? StringUtils.trimToEmpty(command.getMiquantity()[idx]) : "";
			BigDecimal quantity;
			if(entered.isEmpty()) {
				quantity = milist.get(idx).getMatQty();
				if(quantity == null) {
					throw new Exception("Enter the Modified Quantity for MI " + milist.get(idx).getSeqMiId());
				}
			} else {
				try {
					quantity = new BigDecimal(entered);
				} catch (NumberFormatException e) {
					throw new Exception("Modified Quantity of MI " + milist.get(idx).getSeqMiId() + " is not a valid number");
				}
			}
			miquantity[idx] = quantity.stripTrailingZeros().toPlainString();
			totalQuantity = totalQuantity.add(quantity);
		}
		if(totalQuantity.signum() <= 0) {
			throw new Exception("Modified Quantity should be greater than zero to derive the estimated unit cost");
		}
		BigDecimal unitCost = rawMaterialCost.divide(totalQuantity, 2, BigDecimal.ROUND_HALF_UP);
		Arrays.fill(micost, unitCost.toPlainString());

		command.setMiquantity(miquantity);
		command.setMicost(micost);
		context.getFlowScope().put("rawMaterialCost", rawMaterialCost.setScale(2, BigDecimal.ROUND_HALF_UP));
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
			SciPurchItemMaster selected = (SciPurchItemMaster) fullitemlist.get(Integer.parseInt(itemlist[idx])-1);
			if (!containsItem(poitemlist, selected)) {
				poitemlist.add(selected);
			}
		}
		context.getFlowScope().put("poItemlist", poitemlist);
		return success();
	}

	private boolean containsItem(List poitemlist, SciPurchItemMaster candidate) {
		for (Object obj : poitemlist) {
			SciPurchItemMaster existing = (SciPurchItemMaster) obj;
			if (existing.getSeaPuritemId() != null
					&& existing.getSeaPuritemId().equals(candidate.getSeaPuritemId())) {
				return true;
			}
		}
		return false;
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
			SciPurchItemMaster selected = (SciPurchItemMaster) fullitemlist.get(Integer.parseInt(itemlist[idx])-1);
			if (!containsItem(poitemlist, selected)) {
				poitemlist.add(selected);
			}
		}
		context.getFlowScope().put("poItemlist", poitemlist);
		return success();
	}


	private static class RawMiRow {
		private final Long rawMiId;
		private final BigDecimal quantity;
		private final BigDecimal returnableQty;

		private RawMiRow(Long rawMiId, BigDecimal quantity, BigDecimal returnableQty) {
			this.rawMiId = rawMiId;
			this.quantity = quantity;
			this.returnableQty = returnableQty;
		}
	}

	private List<RawMiRow> readRawMiRows(ItemCommand command) throws Exception {
		List<RawMiRow> rows = new ArrayList<RawMiRow>();
		String[] ids = command.getRawMiIds();
		String[] qtys = command.getRawMiQty();
		String[] retQtys = command.getRawMiReturnQty();
		if(ids == null || ids.length == 0) {
			return rows;
		}
		// a single blank field binds as an empty array, so an empty returnable qty array means no returnable qty was entered
		boolean noRetQtys = retQtys == null || retQtys.length == 0;
		if(qtys == null || qtys.length != ids.length || (!noRetQtys && retQtys.length != ids.length)) {
			throw new Exception("Enter the Raw MI ID and Quantity for every Raw MI row");
		}
		for(int idx = 0; idx < ids.length; idx++) {
			String id = StringUtils.trimToEmpty(ids[idx]);
			String qty = StringUtils.trimToEmpty(qtys[idx]);
			String retQty = noRetQtys ? "" : StringUtils.trimToEmpty(retQtys[idx]);
			if(id.isEmpty() && qty.isEmpty() && retQty.isEmpty()) {
				continue;
			}
			if(id.isEmpty() || qty.isEmpty()) {
				throw new Exception("Enter the Raw MI ID and Quantity for every Raw MI row");
			}
			Long rawMiId;
			BigDecimal quantity;
			BigDecimal returnableQty;
			try {
				rawMiId = Long.valueOf(id);
				quantity = new BigDecimal(qty);
				returnableQty = retQty.isEmpty() ? null : new BigDecimal(retQty);
			} catch (NumberFormatException e) {
				throw new Exception("Raw MI row " + (idx + 1) + " has an invalid number");
			}
			if(quantity.signum() <= 0) {
				throw new Exception("Raw MI Quantity should be greater than zero for Raw MI " + rawMiId);
			}
			if(returnableQty != null && (returnableQty.signum() < 0 || returnableQty.compareTo(quantity) > 0)) {
				throw new Exception("Raw MI Returnable Quantity should be between zero and the Raw MI Quantity for Raw MI " + rawMiId);
			}
			rows.add(new RawMiRow(rawMiId, quantity, returnableQty));
		}
		return rows;
	}

	
	

}
