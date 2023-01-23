package com.sci.bpm.dao.mi;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sci.bpm.db.model.*;
import org.springframework.stereotype.Repository;

import com.sci.bpm.command.design.DrawingDetailsBean;
import com.sci.bpm.command.mi.MatindCommand;

/**
 * A data access object (DAO) providing persistence and search support for
 * SciMatindMaster entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see com.sci.bpm.db.model.SciMatindMaster
 * @author MyEclipse Persistence Tools
 */

@Repository
public class SciMatindMasterDAO implements ISciMatindMasterDAO {

	@PersistenceContext
	private EntityManager em;

	public void delete(SciMatindMaster entity) {
		// TODO Auto-generated method stub

	}

	public List<SciMatindMaster> findAll(int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	public SciMatindMaster findById(Long id) {
		// TODO Auto-generated method stub
		return em.find(SciMatindMaster.class, id);
	}

	@Override
	public boolean checkStockAvailability(String matcode, BigDecimal quantity) {

		Query qry = em.createNativeQuery("Select avail_qty from VW_ACTUAL_AVAIL_STOCK where matcode =:matcode");

		qry.setParameter("matcode",matcode);

		List<BigDecimal> tdatalist  = qry.getResultList();
		if(tdatalist != null && tdatalist.size()> 0) {
			if(tdatalist.get(0).doubleValue() > quantity.doubleValue()) {
				return true;
			}
		}

		return false;
	}

	public List<SciMatindMaster> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(SciMatindMaster entity) {
		// TODO Auto-generated method stub
		SciWorkorderMaster workorder = em.getReference(
				SciWorkorderMaster.class, entity.getSciWorkorderMaster()
						.getSeqWorkId());
		
		
		
		
		entity.setSciWorkorderMaster(workorder);
		em.persist(entity);
	}

	public SciMatindMaster update(SciMatindMaster entity) {
		return em.merge(entity);
	}

	public List<SciMatindMaster> searchMI(MatindCommand command) {
		// TODO Auto-generated method stub
		SciWorkorderMaster wmaster = null;
		System.out.println("myquery " +  "inside" );
		if(command.getSeqWorkId() != null && !"".equals(command.getSeqWorkId())) {
			wmaster = em.find(SciWorkorderMaster.class, new Long(command.getSeqWorkId()));
		}

		String query = "select  m.seqMiId,m.matType,m.matSpec,m.matDesc,m.matQty,m.matDimesion,m.recommend,m.purStatus,m.preparedBy,m.estUnintCost,m.matcode,(select CASE  WHEN requestStatus = 'Y' then 'Request Issued' WHEN requestStatus = 'N' then 'Request Not Issued'   WHEN requestStatus = 'C' then 'Request Cancelled by Stores'  WHEN requestStatus = 'R' then 'Request Rejected' ELSE 'Not raised' END from SciStoresRequest r  where  r.sciMiMaster.seqMiId =m.seqMiId ),(select r.prodApproval from SciStoresRequest r where r.sciMiMaster.seqMiId =m.seqMiId),(select r.purchApproval from SciStoresRequest r where r.sciMiMaster.seqMiId =m.seqMiId),m.insertedBy,m.insertedDate,(select wm.jobDesc from SciWorkorderMaster wm where wm.seqWorkId=m.sciWorkorderMaster.seqWorkId),(select  max(pm.seqPurchId) from SciPurchaseMast pm,SciPurchItemMaster im,SciItemmiDetails immi,SciPurchaseItemdetails pmmd where immi.seqMiId =m.seqMiId and immi.sciPurchItemMaster.seaPuritemId=im.seaPuritemId and im.seaPuritemId=pmmd.seqItemId and pm.seqPurchId=pmmd.sciPurchaseMast.seqPurchId) ,m.matDuedate ,m.isGroupMiId ,m.stockMI,(select  max(pm.vendorAgreedDate) from SciPurchaseMast pm,SciPurchItemMaster im,SciItemmiDetails immi,SciPurchaseItemdetails pmmd where immi.seqMiId =m.seqMiId and immi.sciPurchItemMaster.seaPuritemId=im.seaPuritemId and im.seaPuritemId=pmmd.seqItemId and pm.seqPurchId=pmmd.sciPurchaseMast.seqPurchId) ,m.miForIssue from SciMatindMaster m ,SciMattypeMaster mt  where mt.matCode  = substr(m.matcode,1,2)  ";
		// Query query = em.createQuery("Select * from SciMatindMaster m ");
		Map parameters = new HashMap();
		String whereClause = "";
		if (command.getMatDuedate() != null) {
			whereClause = whereClause + " and  m.matDuedate < :duedate ";
			// parameters.add(command.getMatDuedate());
			parameters.put("duedate", command.getMatDuedate());
		}
        if (command.getCreatedDate() != null) {
            whereClause = whereClause + " and  m.insertedDate < :insertedDate ";
            // parameters.add(command.getMatDuedate());
            parameters.put("insertedDate", command.getCreatedDate());
        }
		if (command.getFromdate() != null) {
			whereClause = whereClause + " and  m.insertedDate > :fromdate ";
			// parameters.add(command.getMatDuedate());
			parameters.put("fromdate", command.getFromdate());
		}
		if (command.getTodate() != null) {
			whereClause = whereClause + " and  m.insertedDate < :todate ";
			// parameters.add(command.getMatDuedate());
			parameters.put("todate", command.getTodate());
		}
		if (command.getProductCat() != null && !"".equals(command.getProductCat())) {
			whereClause = whereClause + " and m.matSpec = :mattype ";
			parameters.put("mattype", command.getProductCat());
		}
		if (command.getPurStatus() != null) {
			whereClause = whereClause + " and m.purStatus = :purstatus ";
			parameters.put("purstatus", command.getPurStatus());
		}
		if (command.getIsGroupMiId() != null &&  !"".equals(command.getIsGroupMiId())) {
			whereClause = whereClause + " and m.isGroupMiId = :isGroupMiId ";
			parameters.put("isGroupMiId", command.getIsGroupMiId());
		}
		if (command.getCancelStatus() != null) {
			whereClause = whereClause + " and m.purStatus <> :cancelstatus ";
			parameters.put("cancelstatus", command.getCancelStatusLov());
		}
		if (command.getApprovalStatus() != null) {
			whereClause = whereClause + " and m.approvedStatus = :appstatus ";
			parameters.put("appstatus", command.getApprovalStatus());
		}
		if (command.getMiPhase() != null && !"".equals(command.getMiPhase()) )  {
			whereClause = whereClause + " and m.miPhase = :miPhase ";
			parameters.put("miPhase", command.getMiPhase());
		}
		if (command.getMatCategory() != null && !"".equals(command.getMatCategory())) {
			whereClause = whereClause + " and substr(m.matcode,3,2) = :matcat ";
			parameters.put("matcat", command.getMatCategory());
		}
		if (command.getMatDept() != null && !"".equals(command.getMatDept())) {
			whereClause = whereClause + " and mt.matDept = :matdept ";
			parameters.put("matdept", command.getMatDept());
		}
		if (command.getDept() != null && !"".equals(command.getDept())) {
			whereClause = whereClause + " and mt.matDept = :matdept ";
			parameters.put("matdept", command.getDept());
		}
		if (command.getMatCodeselected() != null && !"".equals(command.getMatCodeselected().trim()) ) {
			whereClause = whereClause + " and m.matcode = :matcodefull ";
			parameters.put("matcodefull", command.getMatCodeselected());
		}
		if (command.getCreatedByUser() != null && !"".equals(command.getCreatedByUser().trim()) ) {
			whereClause = whereClause + " and m.insertedBy = :createdUser ";
			parameters.put("createdUser", command.getCreatedByUser());
		}

		if (command.getMatDescription() != null && !"".equals(command.getMatDescription().trim()) ) {
			whereClause = whereClause + " and UPPER(m.matSpec) like UPPER(:matSpec) ";
			parameters.put("matSpec", "%"+command.getMatDescription()+"%");
		}

		if (command.getWorkOrderKey() != null && !"".equals(command.getWorkOrderKey().trim()) ) {
			whereClause = whereClause + " and m.sciWorkorderMaster.seqWorkId in (Select seqWorkId from SciActiveWorkordersReportEntity where shortKey = :shortKey)";
			parameters.put("shortKey", command.getWorkOrderKey());
		}
		if (command.getSeqMatindid() != null)  {
			whereClause = whereClause + " and m.seqMiId = :seqMiId ";
			parameters.put("seqMiId", command.getSeqMatindid());
		}
		if (command.getEstunitCostStatus() != null && "Y".equals(command.getEstunitCostStatus()))  {
			whereClause = whereClause + " and nvl(m.estUnintCost,0) != 0 ";
			
		}
		if (command.getEstunitCostStatus() != null && "N".equals(command.getEstunitCostStatus()))  {
			whereClause = whereClause + " and nvl(m.estUnintCost,0) = 0 ";
			
		}
		if("Y".equals(command.getFilterChildMi())) {
			whereClause = whereClause + " and m.matGroupMiId is null ";
		}
		if ("Y".equals(command.getFilterIssued()))  {
			whereClause = whereClause + " and not exists (select 1 from SciStoresRequest r,SciStoreissueMaster im  where r.seqStreqId=im.strequest.seqStreqId and r.sciMiMaster.seqMiId =m.seqMiId) ";
			
		}
		if ("Y".equals(command.getStstatus()))  {
			whereClause = whereClause + " and  exists (select 1 from SciStoresRequest r  where  r.sciMiMaster.seqMiId =m.seqMiId and r.requestStatus = 'Y') ";

		}

		if ("N".equals(command.getStstatus()))  {
			whereClause = whereClause + " and  exists (select 1 from SciStoresRequest r  where  r.sciMiMaster.seqMiId =m.seqMiId and r.requestStatus = 'N') ";

		}
		if ("C".equals(command.getStstatus()))  {
			whereClause = whereClause + " and  exists (select 1 from SciStoresRequest r  where  r.sciMiMaster.seqMiId =m.seqMiId and r.requestStatus = 'C') ";

		}
		if ("P".equals(command.getStstatus()))  {
			whereClause = whereClause + " and  not exists (select 1 from SciStoresRequest r  where  r.sciMiMaster.seqMiId =m.seqMiId) ";

		}
		if ("R".equals(command.getStstatus()))  {
			whereClause = whereClause + " and  exists (select 1 from SciStoresRequest r  where  r.sciMiMaster.seqMiId =m.seqMiId and r.requestStatus = 'R') ";

		}
		if ("Y".equals(command.getFilterRejected()))  {
			whereClause = whereClause + " and  exists (select 1 from SciStoresRequest r  where   r.sciMiMaster.seqMiId =m.seqMiId and (r.prodApproval <> 'R' and r.purchApproval <> 'R')) ";
			
		}
		if (wmaster != null) {
			whereClause = whereClause + " and m.sciWorkorderMaster = :wm ";
			parameters.put("wm", wmaster);
		}
		Query wquery = null;
		if (parameters.size() > 0) {
			wquery = em.createQuery(query
					+ whereClause.replaceAll("where and", "where") + " order by m.insertedDate desc ");
		} else {
			System.out.println("myquery " + query);
			wquery = em.createQuery(query + " order by m.insertedDate desc ");
		}
		Iterator keyset = parameters.keySet().iterator();

		while (keyset.hasNext()) {
			String key = (String) keyset.next();
			wquery.setParameter(key, parameters.get(key));
		}
		wquery.setMaxResults(command.getSearchMax());
		List<Object[]> milist = wquery.getResultList();
		List<SciMatindMaster> finalList = new ArrayList<SciMatindMaster>();
		List<SciMatindMaster> deptList = new ArrayList<SciMatindMaster>();
		for(Object[]  obj :milist) {
			finalList.add(new SciMatindMaster(obj[0], obj[1], obj[2], obj[3], obj[4], obj[5], obj[6], obj[7], obj[8], obj[9], obj[10], obj[11], obj[12], obj[13], obj[14], obj[15],obj[16],obj[17],obj[18],obj[19],obj[20],obj[21],obj[22]));
		}

		Query totalAvailableqry = em.createNativeQuery("select nvl(sum(AVAIL_QTY),0) as availQty from sci_available_materials where matcode = :matcode");
		Query assigendStoresqry = em.createNativeQuery("select nvl(sum(mat_qty_mod),0) as availQty from sci_matind_master mi where pur_status in (select seq_lov_id from sci_lookup_master lm where lm.lov_name in ('MI_IN_STOCK','MI_APP_STOCKS')) and  exists (select 1 from SCI_STORES_REQUEST st where st.seq_mi_id = mi.seq_mi_id and request_Status = 'N') and matcode = :matcode");
		for(SciMatindMaster mi:finalList) {

			totalAvailableqry.setParameter("matcode",mi.getMatcode());
			BigDecimal  count = (BigDecimal )totalAvailableqry.getSingleResult();

			assigendStoresqry.setParameter("matcode",mi.getMatcode());
			BigDecimal  assignedCount = (BigDecimal )assigendStoresqry.getSingleResult();
			mi.setAssignedStock(assignedCount.floatValue());
			mi.setStockQty(count.floatValue());
			mi.setActualStock(count.floatValue()-assignedCount.floatValue());
			/*if(checkdept(mi.getMatcode(), command.getDept())){
				deptList.add(mi);
			}*/
		}
		int deptid = 0;
		if(command.getDept() != null){


		for(SciMatindMaster mi:finalList) {


			if(checkdept(mi.getMatcode(), command.getDept())){
				deptList.add(mi);
			}
		}
		}
		else {
			deptList.addAll(finalList);
		}
		
			
		
		System.out.println("myquery " +  finalList.size() );
		  Query storeqry = em.createQuery("Select m from SciStoresRequest m Join m.sciMiMaster ms where ms.seqMiId =:seqmiid"); 
	        Query issueqry = em.createQuery("Select m from SciStoreissueMaster m Join m.strequest ms where ms.seqStreqId =:seqStreqId");
	        StringBuffer buffer = new StringBuffer("");
/*		for(SciMatindMaster mi:finalList) {
			storeqry.setParameter("seqmiid", mi.getSeqMiId());
			List stlist = storeqry.getResultList();
			if(stlist.size() > 0) {
				SciStoresRequest request = (SciStoresRequest) stlist.get(0);
				String requestStatus = request.getRequestStatus();
				issueqry.setParameter("seqStreqId", request.getSeqStreqId());
				mi.setProdRequestStatus(request.getProdApproval());
				mi.setPurchRequestStatus(request.getPurchApproval());
				
				List<SciStoreissueMaster> requestlist = issueqry.getResultList();
				if(requestlist.size() > 0) {
					
					for(SciStoreissueMaster sm:requestlist) {
						buffer.append(sm.getSciMiMaster().getSeqMiId() +" ");
					}
					if(request.getRequestStatus().equals("R")) {
						mi.setRequestStatus("Returned");
					}
					else {
						mi.setRequestStatus("Issued from " + buffer.toString());
					}
					buffer.delete(0, buffer.toString().length());
					
					
				}
				else {
					mi.setRequestStatus("Pending");
				}
				mi.setStRequestStatus(request);
				
			}
			else {
				mi.setRequestStatus("NotRaised");
			}
		}*/
		return deptList;
	}

	@Override
	public List<SciMatindMaster> loadChildMi(Long seqParentGroupMIId) {
		String query = "Select m from SciMatindMaster m where m.matGroupMiId =:parentMiId";
		Query qry = em.createQuery(query);
		qry.setParameter("parentMiId",seqParentGroupMIId);

		return qry.getResultList();
	}


	public List searchMIByWorkOrder(SciMatindMaster command,MatindCommand command1) {
		String query = "Select distinct m from SciMatindMaster m ,SciMattypeMaster mt where mt.matCode  = substr(m.matcode,1,2)and  m.sciWorkorderMaster = :workmaster ";
		Query wquery = null;
		Map parameters = new HashMap();
		String whereClause = "";
		if (command1.getMatCategory() != null && !"".equals(command1.getMatCategory())) {
			whereClause = whereClause + " and substr(m.matcode,3,2) = :matcat ";
			parameters.put("matcat", command1.getMatCategory());
		}
		if (command1.getMatDept() != null && !"".equals(command1.getMatDept())) {
			whereClause = whereClause + " and mt.matDept = :matdept ";
			parameters.put("matdept", command1.getMatDept());
		}
		
		if (command1.getPurStatus() != null && command1.getPurStatus() != 0) {
			whereClause = whereClause + " and m.purStatus = :purStatus ";
			parameters.put("purStatus", command1.getPurStatus());
		}
		if (command1.getIsGroupMiId() != null &&  !"".equals(command1.getIsGroupMiId())) {
			whereClause = whereClause + " and m.isGroupMiId = :isGroupMiId ";
			parameters.put("isGroupMiId", command1.getIsGroupMiId());
		}
		if (command1.getMatGroupMiId() != null && command1.getMatGroupMiId() != 0) {
			whereClause = whereClause + " and m.matGroupMiId = :matGroupMiId ";
			parameters.put("matGroupMiId", command1.getMatGroupMiId());
		}
		System.out.println(whereClause);
		wquery = em.createQuery(query + whereClause);
		Iterator keyset = parameters.keySet().iterator();
		while (keyset.hasNext()) {
			String key = (String) keyset.next();
			wquery.setParameter(key, parameters.get(key));
		}
		
		
		  
        Query storeqry = em.createQuery("Select m from SciStoresRequest m Join m.sciMiMaster ms where ms.seqMiId =:seqmiid"); 
        Query issueqry = em.createQuery("Select m from SciStoreissueMaster m Join m.strequest ms where ms.seqStreqId =:seqStreqId");
        Query totalAvailableqry = em.createNativeQuery("select nvl(sum(AVAIL_QTY),0) as availQty from sci_available_materials where matcode = :matcode");
		Query assigendStoresqry = em.createNativeQuery("select nvl(sum(mat_qty_mod),0) as availQty from sci_matind_master mi where pur_status in (select seq_lov_id from sci_lookup_master lm where lm.lov_name in ('MI_IN_STOCK','MI_APP_STOCKS')) and  exists (select 1 from SCI_STORES_REQUEST st where st.seq_mi_id = mi.seq_mi_id and request_Status = 'N') and matcode = :matcode");
		wquery.setParameter("workmaster", command.getSciWorkorderMaster());
		wquery.setMaxResults(1000);
		List<SciMatindMaster> milist = wquery.getResultList();
		StringBuffer buffer = new StringBuffer("");
		for(SciMatindMaster mi:milist) {
			storeqry.setParameter("seqmiid", mi.getSeqMiId());
			List stlist = storeqry.getResultList();
			if(stlist.size() > 0) {
				SciStoresRequest request = (SciStoresRequest) stlist.get(0);
				issueqry.setParameter("seqStreqId", request.getSeqStreqId());
				List<SciStoreissueMaster> requestlist = issueqry.getResultList();
				mi.setProdRequestStatus(request.getProdApproval());
				mi.setPurchRequestStatus(request.getPurchApproval());
				if(requestlist.size() > 0) {
					
					for(SciStoreissueMaster sm:requestlist) {
						buffer.append(sm.getSciMiMaster().getSeqMiId() +" ");
					}
					if(request.getRequestStatus().equals("R")) {
						mi.setRequestStatus("Returned");
					}
					else {
						mi.setRequestStatus("Issued from " + buffer.toString());
					}
					buffer.delete(0, buffer.toString().length());
				}
				else {
					mi.setRequestStatus("Pending");
				}
				mi.setStRequestStatus(request);
				
			}
			else {
				mi.setRequestStatus("NotRaised");
			}
			totalAvailableqry.setParameter("matcode",mi.getMatcode());
			BigDecimal  count = (BigDecimal )totalAvailableqry.getSingleResult();

			assigendStoresqry.setParameter("matcode",mi.getMatcode());
			BigDecimal  assignedCount = (BigDecimal )assigendStoresqry.getSingleResult();
			mi.setAssignedStock(assignedCount.floatValue());
			mi.setStockQty(count.floatValue());
			mi.setActualStock(count.floatValue()-assignedCount.floatValue());
		}
		return milist;
	}

	public void approveMI(String seqmilist, String approvedBy) {
		Long seqLovId = (Long) em.createQuery("Select seqLovId from SciLookupMaster m where m.lovName = 'MI_APPROVED' ").getSingleResult();
		
		String query = "update SciMatindMaster set purStatus = :purstatus ,approvedStatus='Y', approvedBy= :approvedBy ,updatedBy=:updatedBy ,updatedDate= :updatedDate "
				+ " where seqMiId in ( " + seqmilist + ")";
		Query wquery = em.createQuery(query);
		wquery.setParameter("purstatus", seqLovId);
		wquery.setParameter("approvedBy", approvedBy);
		wquery.setParameter("updatedBy", approvedBy);
		wquery.setParameter("updatedDate", new Date());
		
		wquery.executeUpdate();
	}

	@Override
	public List<SciMatindMaster> loadLastPriced(MatindCommand command1) {
		String query = "Select distinct m from SciMatindMaster m ,SciMattypeMaster mt where mt.matCode  = substr(m.matcode,1,2) and exists (select 1 from SciRecdMaterials rm where rm.sciMiMaster.seqMiId = m.seqMiId ) ";
		Query wquery = null;
		Map parameters = new HashMap();
		String whereClause = "";
		if (command1.getMatCategory() != null && !"".equals(command1.getMatCategory())) {
			whereClause = whereClause + " and substr(m.matcode,3,2) = :matcat ";
			parameters.put("matcat", command1.getMatCategory());
		}
		if (command1.getMatDept() != null && !"".equals(command1.getMatDept())) {
			whereClause = whereClause + " and mt.matDept = :matdept ";
			parameters.put("matdept", command1.getMatDept());
		}
		if (command1.getMatCodeselected() != null && !"".equals(command1.getMatCodeselected().trim()) ) {
			whereClause = whereClause + " and m.matcode = :matcodefull ";
			parameters.put("matcodefull", command1.getMatCodeselected());
		}
		whereClause = whereClause + " order by m.updatedDate desc ";
		System.out.println(whereClause);
		wquery = em.createQuery(query + whereClause);
		Iterator keyset = parameters.keySet().iterator();
		while (keyset.hasNext()) {
			String key = (String) keyset.next();
			wquery.setParameter(key, parameters.get(key));
		}





		wquery.setMaxResults(3);
		List<SciMatindMaster> milist = wquery.getResultList();
		System.out.println("misearch size" + milist.size());

		return milist;
	}

	@Override
	public List<SciMatindMaster> searchMIForPrice(MatindCommand command) {

		return null;
	}

	public void cancelMI(SciMatindMaster master) {
		
		 
		String selectQuery = "Select m from SciStoresRequest m where  m.sciMiMaster.seqMiId=:seqmiid";
		
		Query querycst = em.createQuery(selectQuery);
		querycst.setParameter("seqmiid", master.getSeqMiId());
		int totalcount = querycst.getResultList().size();
		
		String stquery = "update SciStoresRequest set prodApproval = :status ,purchApproval = :status,updatedBy=:updatedBy ,updatedDt= :updatedDate "
				+ " where sciMiMaster.seqMiId = :seqmiid and seqStreqId not in (select r.seqStreqId from SciStoresRequest r,SciStoreissueMaster im  where r.seqStreqId=im.strequest.seqStreqId and r.sciMiMaster.seqMiId =:seqmiid ) ";
		Query queryst = em.createQuery(stquery);
		queryst.setParameter("status", "R");
		queryst.setParameter("updatedBy", master.getUpdatedBy());
		queryst.setParameter("updatedDate", new Date());
		queryst.setParameter("seqmiid", master.getSeqMiId());
		int count = queryst.executeUpdate();
		
		
		if(totalcount == 0 || count >=1) {
		String query = "update SciMatindMaster set purStatus = :purstatus ,updatedBy=:updatedBy ,updatedDate= :updatedDate "
				+ " where seqMiId = :seqmiid ";
		Query wquery = em.createQuery(query);
		wquery.setParameter("purstatus", master.getPurStatus());
		wquery.setParameter("updatedBy", master.getUpdatedBy());
		wquery.setParameter("updatedDate", new Date());
		wquery.setParameter("seqmiid", master.getSeqMiId());
		wquery.executeUpdate();
		}
	}

	public List selectDesignDocs(SciMatindMaster command) {
		String query = " select  dd.SEQ_DRW_DTLID, dd.ORIGINAL_DOC, dd.DOCUMENT_VERSION, dm.DOCUMENT_NAME, dm.DRAWING_DESC, dm.DRWING_REFNUM, dd.UPDATED_DATE   from   scigenics.SCI_DRAWING_REF dm, scigenics.SCI_DRWING_DETAILS dd  where  dm.SEQ_DRAWING_ID = dd.SCI_DRWING_ID and dm.SEQ_WORK_ID = :wmwork and dd.DRAWING_STATUS = 'Y'";
		Query wquery = null;
		 wquery = em.createNativeQuery(query);
		
		 wquery.setParameter("wmwork", command.getSciWorkorderMaster().getSeqWorkId());
		 List desobjlist =  wquery.getResultList();
	       List deslist = createVOList(desobjlist);
	
	return deslist;
	}

	public void updateMI(SciMatindMaster master) {
		String query = "update SciMatindMaster set isGroupMiId =:isGroupMiId ,recommend = :reco ,updatedBy=:updatedBy ,updatedDate= :updatedDate "
			+ " where seqMiId = :seqmiid";
	Query wquery = em.createQuery(query);
	wquery.setParameter("reco", master.getRecommend());
	wquery.setParameter("updatedBy", master.getUpdatedBy());
	wquery.setParameter("updatedDate", new Date());
	wquery.setParameter("seqmiid", master.getSeqMiId());

		wquery.setParameter("isGroupMiId", master.getIsGroupMiId());
	wquery.executeUpdate();

		
	}

	public String getMatType(String cat, String dept) {
		Query qry = em.createQuery("from SciMatcatMaster pm where pm.matcatCode=:matCode and pm.matDept =:matDept");
		qry.setParameter("matCode", cat);
		qry.setParameter("matDept", dept);
		SciMatcatMaster matcat = (SciMatcatMaster) qry.getSingleResult();
		return matcat.getMatcatType();
	}

	public List loadOpenMI(SciMatindMaster command) {
		String query = " Select m from SciMatindMaster m,SciLookupMaster lm  where m.purStatus =lm.seqLovId and lm.lovName in ( 'MI_OPEN','MI_APPROVED') and m.sciWorkorderMaster = :workmaster ";
		Query wquery = null;
		wquery = em.createQuery(query);

		wquery.setParameter("workmaster", command.getSciWorkorderMaster());
		List milist = wquery.getResultList();

		return milist;
		
	}

	public void addStoreRequest(SciStoresRequest request) {
		
		
		em.persist(request);
		
	}
	public List loadStoreIssueRequest(MatindCommand command) {
	
		
		
		
		

		String query = "Select distinct m from SciStoresRequest m  ,SciMattypeMaster mt where mt.matCode  = substr(m.matcode,1,2)  ";
		// Query query = em.createQuery("Select * from SciMatindMaster m ");
		Map parameters = new HashMap();
		String whereClause = "";
		
		if ("prodsupervisor".equals(command.getRolelevel()) && !"".equals(command.getStstatus())) {
			whereClause = whereClause + " and m.prodApproval =:prodapp ";
			parameters.put("prodapp", command.getStstatus());
		}
		if ("purchase".equals(command.getRolelevel()) && !"".equals(command.getStstatus())) {
			whereClause = whereClause + " and m.purchApproval = :purchapp ";
			parameters.put("purchapp", command.getStstatus());
		}
		if (command.getWm() != null) {
			whereClause = whereClause + " and m.sciMiMaster.sciWorkorderMaster = :wm ";
			parameters.put("wm", command.getWm());
		}
		if (command.getMatCategory() != null && !"".equals(command.getMatCategory())) {
			whereClause = whereClause + " and substr(m.matcode,3,2) = :matcat ";
			parameters.put("matcat", command.getMatCategory());
		}
		if (command.getMatDept() != null && !"".equals(command.getMatDept())) {
			whereClause = whereClause + " and mt.matDept = :matdept ";
			parameters.put("matdept", command.getMatDept());
		}
		if(command.getStIssued() != null) {
			if("Y".equals(command.getStIssued())) {
			whereClause = whereClause + " and exists (select 1 from SciStoreissueMaster sti where sti.strequest.seqStreqId = m.seqStreqId) ";
			}
			if("N".equals(command.getStIssued())) {
				whereClause = whereClause + " and not exists (select 1 from SciStoreissueMaster sti where sti.strequest.seqStreqId = m.seqStreqId) ";
				}
		}
		Query wquery = null;
		if (parameters.size() > 0) {
			wquery = em.createQuery(query
					+ whereClause.replaceAll("where and", "where"));
		} else {
			wquery = em.createQuery(query);
		}
		Iterator keyset = parameters.keySet().iterator();

		while (keyset.hasNext()) {
			String key = (String) keyset.next();
			wquery.setParameter(key, parameters.get(key));
		}
		List<SciStoresRequest> milist = wquery.getResultList();
		
		return milist;
	}
	public List loadStoreRequest(MatindCommand command) {
	
		
		
		
		

		String query = "Select distinct m from SciStoresRequest m  ,SciMattypeMaster mt where mt.matCode  = substr(m.matcode,1,2)  ";
		// Query query = em.createQuery("Select * from SciMatindMaster m ");
		Map parameters = new HashMap();
		String whereClause = "";
		
		if ("prodsupervisor".equals(command.getRolelevel()) && !"".equals(command.getStstatus())) {
			whereClause = whereClause + " and m.prodApproval =:prodapp ";
			parameters.put("prodapp", command.getStstatus());
		}
		if ("purchase".equals(command.getRolelevel()) && !"".equals(command.getStstatus())) {
			whereClause = whereClause + " and m.purchApproval = :purchapp ";
			parameters.put("purchapp", command.getStstatus());
		}
		if (command.getWm() != null) {
			whereClause = whereClause + " and m.sciMiMaster.sciWorkorderMaster = :wm ";
			parameters.put("wm", command.getWm());
		}
		if (command.getSeqWorkId() !=null  && Long.parseLong(command.getSeqWorkId()) != 0) {
			whereClause = whereClause + " and m.sciMiMaster.sciWorkorderMaster.seqWorkId = :workid ";
			parameters.put("workid", Long.parseLong(command.getSeqWorkId()));
		}
		if (command.getMatCategory() != null && !"".equals(command.getMatCategory())) {
			whereClause = whereClause + " and substr(m.matcode,3,2) = :matcat ";
			parameters.put("matcat", command.getMatCategory());
		}
		if (command.getFromdate() != null) {
			whereClause = whereClause + " and  m.insertedDate > :fromdate ";
			// parameters.add(command.getMatDuedate());
			parameters.put("fromdate", command.getFromdate());
		}
		if (command.getTodate() != null) {
			whereClause = whereClause + " and  m.insertedDate < :todate ";
			// parameters.add(command.getMatDuedate());
			parameters.put("todate", command.getTodate());
		}
		if (command.getMatDept() != null && !"".equals(command.getMatDept())) {
			whereClause = whereClause + " and mt.matDept = :matdept ";
			parameters.put("matdept", command.getMatDept());
		}
		if(command.getStIssued() != null) {
			if("Y".equals(command.getStIssued())) {
			whereClause = whereClause + " and exists (select 1 from SciStoreissueMaster sti where sti.strequest.seqStreqId = m.seqStreqId) ";
			}
			if("N".equals(command.getStIssued())) {
				whereClause = whereClause + " and not exists (select 1 from SciStoreissueMaster sti where sti.strequest.seqStreqId = m.seqStreqId) ";
				}
		}
		Query wquery = null;
		if (parameters.size() > 0) {
			wquery = em.createQuery(query
					+ whereClause.replaceAll("where and", "where"));
		} else {
			wquery = em.createQuery(query);
		}
		Iterator keyset = parameters.keySet().iterator();

		while (keyset.hasNext()) {
			String key = (String) keyset.next();
			wquery.setParameter(key, parameters.get(key));
		}
		
		wquery.setMaxResults(command.getSearchMax());
		List<SciStoresRequest> milist = wquery.getResultList();
		for(SciStoresRequest rq :milist ) {
			System.out.println(rq.getSciStoreIssueSet().size());
		}
	   
		
		return milist;
	}

	public List loadforAppProdStRequest(MatindCommand command) {
		
		return em.createQuery("from SciStoresRequest m where m.prodApproval='N' ").getResultList();
	}

	public List loadforAppPurchStRequest(MatindCommand command) {
		
		return 	 em.createQuery("from SciStoresRequest m where m.purchApproval='N' ").getResultList();
		
	}

	public SciMatindMaster loadMI(Long seqmiID) {
		SciMatindMaster master =  em.getReference(SciMatindMaster.class,seqmiID);
		master.getSciWorkorderMaster();
		return master;
	}

	public void updateStoreRequest(SciStoresRequest request) {
		em.merge(request);
		
	}
	
	private boolean checkdept(String matcode,String dept) {
		boolean cond = false;
		int deptcode = Integer.parseInt(matcode.substring(0,2));
		if("MECH".equals(dept) && deptcode < 80) {
			cond = true;
		}
		if("E&I".equals(dept) && deptcode >= 80) {
			cond = true;
		}
		
		return cond;
	}

	public void approveMilist(List<SciMatindMaster> milist) {
		for(SciMatindMaster mi:milist) {
			em.merge(mi);
		}
		
	}

	public List availableinStores(String matcode) {
		// TODO Auto-generated method stub
		
		Query qry = em.createQuery("from SciAvailableMaterials av where av.issuedFully = 'N' and av.matcode = :matcode");
		qry.setParameter("matcode", matcode);
		
		
		return qry.getResultList();
	}

	public List checkStockedMI(String matcode, Long lovid) {
		
		Query qry = em.createQuery("Select distinct mi from SciStoresRequest mi where av.issuedFully = 'N' and av.matcode = :matcode");
		qry.setParameter("matcode", matcode);
		
		
		return qry.getResultList();
	}
	private List createVOList(List objectList) {
		List drawinglist = new ArrayList();
		for(int idx=0;idx < objectList.size();idx++) {
			Object[] arr = (Object[]) objectList.get(idx);
			DrawingDetailsBean  bean = new DrawingDetailsBean();
			bean.setSeqDrwDtlid( new Long(((BigDecimal)arr[0]).toString()));
			bean.setOriginalDoc((String) arr[1]);
			bean.setDocumentVersion( new Long(((BigDecimal)arr[2]).toString()));
			
			SciDrawingRef ref = new SciDrawingRef();
			
			ref.setDocumentName((String) arr[3]);
			ref.setDrawingDesc((String) arr[4]);
			ref.setDrwingRefnum((String) arr[5]);
			bean.setSciDrawingRef(ref);
			bean.setUpdatedDate((Date) arr[6]);
			drawinglist.add(bean);
		}
		return drawinglist;
	}

	public void mergeMI(SciMatindMaster master) {
		em.merge(master);
		
	}

	public List loadStoreRequestIssue(MatindCommand command) {
		String query = "Select distinct m from SciStoreissueMaster m  ,SciMattypeMaster mt where mt.matCode  = substr(m.strequest.matcode,1,2)  ";
		// Query query = em.createQuery("Select * from SciMatindMaster m ");
		Map parameters = new HashMap();
		String whereClause = "";
		
	
		if (command.getWm() != null) {
			whereClause = whereClause + " and m.strequest.sciMiMaster.sciWorkorderMaster = :wm ";
			parameters.put("wm", command.getWm());
		}
		if (command.getMatCategory() != null && !"".equals(command.getMatCategory())) {
			whereClause = whereClause + " and substr(m.strequest.matcode,3,2) = :matcat ";
			parameters.put("matcat", command.getMatCategory());
		}
		if (command.getMatDept() != null && !"".equals(command.getMatDept())) {
			whereClause = whereClause + " and mt.matDept = :matdept ";
			parameters.put("matdept", command.getMatDept());
		}
		
		Query wquery = null;
		if (parameters.size() > 0) {
			wquery = em.createQuery(query
					+ whereClause.replaceAll("where and", "where"));
		} else {
			wquery = em.createQuery(query);
		}
		Iterator keyset = parameters.keySet().iterator();

		while (keyset.hasNext()) {
			String key = (String) keyset.next();
			wquery.setParameter(key, parameters.get(key));
		}
		List<SciStoreissueMaster> milist = wquery.getResultList();
		
		return milist;
	}

	public List loadStoreRequestFilters(MatindCommand command) {
		String query = "Select distinct m from SciStoresRequest m  ,SciMattypeMaster mt where mt.matCode  = substr(m.matcode,1,2)  ";
		// Query query = em.createQuery("Select * from SciMatindMaster m ");
		Map parameters = new HashMap();
		String whereClause = "";
		
		if (command.getProdAppStatus() != null && !"".equals(command.getProdAppStatus()) ) {
			whereClause = whereClause + " and m.prodApproval = :prodApproval ";
			parameters.put("prodApproval", command.getProdAppStatus());
		}
		
		if (command.getPurAppStatus() != null && !"".equals(command.getPurAppStatus())) {
			whereClause = whereClause + " and m.purchApproval = :purchApproval ";
			parameters.put("purchApproval", command.getPurAppStatus());
		}
		
		
		if (command.getWm() != null) {
			whereClause = whereClause + " and m.sciMiMaster.sciWorkorderMaster = :wm ";
			parameters.put("wm", command.getWm());
		}
		if (command.getSeqWorkId() !=null  && Long.parseLong(command.getSeqWorkId()) != 0) {
			whereClause = whereClause + " and m.sciMiMaster.sciWorkorderMaster.seqWorkId = :workid ";
			parameters.put("workid", Long.parseLong(command.getSeqWorkId()));
		}
		if (command.getMatCategory() != null && !"".equals(command.getMatCategory())) {
			whereClause = whereClause + " and substr(m.matcode,3,2) = :matcat ";
			parameters.put("matcat", command.getMatCategory());
		}
		if (command.getFromdate() != null) {
			whereClause = whereClause + " and  m.insertedDate > :fromdate ";
			// parameters.add(command.getMatDuedate());
			parameters.put("fromdate", command.getFromdate());
		}
		if (command.getTodate() != null) {
			whereClause = whereClause + " and  m.insertedDate < :todate ";
			// parameters.add(command.getMatDuedate());
			parameters.put("todate", command.getTodate());
		}
		if (command.getMatDept() != null && !"".equals(command.getMatDept())) {
			whereClause = whereClause + " and mt.matDept = :matdept ";
			parameters.put("matdept", command.getMatDept());
		}
		if(command.getStIssued() != null) {
			if("Y".equals(command.getStIssued())) {
			whereClause = whereClause + " and exists (select 1 from SciStoreissueMaster sti where sti.strequest.seqStreqId = m.seqStreqId) ";
			}
			if("N".equals(command.getStIssued())) {
				whereClause = whereClause + " and not exists (select 1 from SciStoreissueMaster sti where sti.strequest.seqStreqId = m.seqStreqId) ";
				}
		}
		Query wquery = null;
		if (parameters.size() > 0) {
			wquery = em.createQuery(query
					+ whereClause.replaceAll("where and", "where"));
		} else {
			wquery = em.createQuery(query);
		}
		Iterator keyset = parameters.keySet().iterator();

		while (keyset.hasNext()) {
			String key = (String) keyset.next();
			wquery.setParameter(key, parameters.get(key));
		}
		
		wquery.setMaxResults(command.getSearchMax());
		List<SciStoresRequest> milist = wquery.getResultList();
		for(SciStoresRequest rq :milist ) {
			System.out.println(rq.getSciStoreIssueSet().size());
		}
	   
		
		return milist;
	}

	public float loadLatestUnitCost(SciMatindMaster matid) {
		Query query = em.createNativeQuery("Select  unit_cost from SCI_MATIND_MASTER mt where mt.MATCODE  =:matcode and nvl(unit_cost,0) <> 0  order by cost_update_date desc" );
		query.setParameter("matcode",matid.getMatcode() );
		List milist =query.getResultList();
		
		if(milist.size() > 0) {
			BigDecimal decimal = (BigDecimal) milist.get(0);
			if(decimal != null) {
			return decimal.floatValue();
			}
		}
		return 0;
	}

	@Override
	public Date loadDeliverySchedule(Long seqPoId) {

		SciPurchaseMast pm = em.find(SciPurchaseMast.class,seqPoId);


		return pm.getPurchaseDueDate();
	}



	public void addSubContStatus(SciSubcontJobstatus jobstatus) {
		// TODO Auto-generated method stub
		em.merge(jobstatus);
	}

	public List<SciSubcontJobstatus> getRecentSubcontJobStatus(Long seqWorkID) {
		// TODO Auto-generated method stub
		
		Query query = em.createQuery("Select m from SciSubcontJobstatus m   where m.sciWorkorderMaster.seqWorkId=:seqWorkID ");
		
		query.setParameter("seqWorkID", seqWorkID);
		List<SciSubcontJobstatus> jobstatuslist = query.getResultList();
				
		return jobstatuslist;
	}

	public List<SciSubcontJobstatus> getLoadDetails(Long jobCode,
			String jobType, Long seqWorkId) {
		// TODO Auto-generated method stub
		
		Query query = em.createQuery("Select m from SciSubcontJobstatus m   where m.sciWorkorderMaster.seqWorkId=:seqWorkID  and m.jobType=:jobType and m.jobCode=:jobCode order by m.insertedDate desc");
		query.setParameter("seqWorkID", seqWorkId);
		query.setParameter("jobCode", jobCode);
		query.setParameter("jobType", jobType);
		return query.getResultList();
	}

	@Override
	public List<SciMIAdditionalInfoDTO> loadAdditionalInfoMaster(String categoryId, String dept) {
		Query query = em.createQuery("Select m from SCI_MATERIAL_ADDINFO_MASTER m where m.materialCatDept=:matcatDept and m.materialCatId=:matcatId");

		query.setParameter("matcatDept",dept);
		query.setParameter("matcatId",categoryId);
		List<SciMaterialAdditionalInfoMasterEntity> list = query.getResultList();
		if(list.size() ==1) {
			return new ArrayList<SciMIAdditionalInfoDTO>(Arrays.asList(list.get(0).getAdditionalInfo()));
		}
		return  null;
	}

	@Override
	public List loadMisWithoutStoresRequest(MatindCommand command) {
		String query = " Select m from SciMatindMaster m,SciLookupMaster lm  where m.purStatus =lm.seqLovId and lm.lovName  not in ( 'MI_CANCEL','MI_QC_REJECTED_ITEMS') " +
				" and not exists (select 1 from SciStoresRequest st where st.sciMiMaster.seqMiId = m.seqMiId ) " +
				" and m.sciWorkorderMaster.seqWorkId = :workid  ";
		Query wquery = null;
		wquery = em.createQuery(query);

		wquery.setParameter("workid", Long.parseLong(command.getSeqWorkId()));
		List milist = wquery.getResultList();

		return milist;
	}


}