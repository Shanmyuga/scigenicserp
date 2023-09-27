package com.sci.bpm.dao.item;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sci.bpm.db.model.*;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseItemMasterDAOImpl implements ISciPurchItemMasterDAO {

	@PersistenceContext
	private EntityManager em;
	
	
	public void delete(SciPurchItemMaster entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SciRawMIDetails> loadRawMI(Long subContractMI) {
Query query =		em.createQuery("from SciRawMIDetails rawmi where rawmi.subcontractMIMaster.seqMiId=:seqSubContMIID");
query.setParameter("seqSubContMIID",subContractMI);
return query.getResultList();


	}

	public List<SciPurchItemMaster> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public SciPurchItemMaster findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SciPurchItemMaster> findByItemActualCost(Object itemActualCost) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SciPurchItemMaster> findByItemDescription(Object itemDescription) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SciPurchItemMaster> findByItemDimen(Object itemDimen) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SciPurchItemMaster> findByItemEstimatedCost(
			Object itemEstimatedCost) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SciPurchItemMaster> findByItemQty(Object itemQty) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<SciPurchItemMaster> findByItemStatus(Object itemStatus) {
		Query query = null;
		if(itemStatus == null) {
			
			query = em.createQuery("from SciPurchItemMaster pm ");
			
		}
		else {
			query = em.createQuery("from SciPurchItemMaster pm where pm.itemStatus= :itemstat");
			query.setParameter("itemstat", itemStatus);
		}
		return query.getResultList();
	}

	public List<SciPurchItemMaster> findByProperty(String propertyName,
			Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SciPurchItemMaster> findByUpdatedBy(Object updatedBy) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(SciPurchItemMaster entity) {
	   em.persist(entity);

	}

	public SciPurchItemMaster update(SciPurchItemMaster entity) {
		return em.merge(entity);
	}

	public List<SciPurchItemMaster> loadPODetails(SciPurchaseMast master) {
		// TODO Auto-generated method stub
		
		Query query = null;
		
	
			query = em.createQuery("select pm from SciPurchItemMaster pm,SciPurchaseItemdetails pi where pi.seqItemId = pm.seaPuritemId and pi.sciPurchaseMast = :scipomaster");
			query.setParameter("scipomaster",master );
		
		return query.getResultList();
		
	}

	public void updatePOStatus(SciPurchaseMast master) {
		em.merge(master);
		
	}

	public boolean checkRawMI(Long rawMI) {
		// TODO Auto-generated method stub
		Query query = null;
		query = em.createQuery("from SciQcMiMaster im where im.sciMiMaster.seqMiId=:seqMIID");
		query.setParameter("seqMIID", rawMI);
		if(query.getResultList() != null && query.getResultList().size() >0 ) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkEstCost(SciMatindMaster mi) {

		String matcode = mi.getMatcode();
		String matcat = mi.getMatcode().substring(2, 4);
		BigDecimal currcost = null;
		BigDecimal estiCost = null;
		Query deptqry = em.createQuery("Select mtm from SciMattypeMaster  mtm where mtm.matCode=:matcode");
		deptqry.setParameter("matcode", matcode.substring(1, 3));
		SciMattypeMaster mtm = (SciMattypeMaster) deptqry.getSingleResult();
		String dept = mtm.getMatDept();


		Query qry = em.createNativeQuery("Select nvl(current_cost,0) from MATCAT_CURRENT_COST_VIEW mtm where seq_work_id=:seqworkid and mtm.matcat_Code=:matcatcode and mtm.mat_dept=:matdept");
		qry.setParameter("matcatcode", matcat);
		qry.setParameter("seqworkid", mi.getSciWorkorderMaster().getSeqWorkId());
		qry.setParameter("matdept", dept);
		List<BigDecimal> sizes = qry.getResultList();
		if (sizes.size() > 0) {
			BigDecimal objects = sizes.get(0);

			currcost = objects.add(mi.getUnitCost());
		} else {
			currcost = new BigDecimal(0);
			currcost = currcost.add(mi.getUnitCost());
		}


		Query estcostQuery = em.createNativeQuery("Select estimated_cost from SCI_MATCAT_WO_EST_COST mtm where mtm.mat_Dept=:matdept and mtm.matcat_Code=:matcatcode and mtm.seq_Work_Id=:seqworkId");
		estcostQuery.setParameter("matcatcode", matcat);
		estcostQuery.setParameter("seqworkId", mi.getSciWorkorderMaster().getSeqWorkId());
		estcostQuery.setParameter("matdept", dept);
		List<BigDecimal> estcostList = estcostQuery.getResultList();
		for (BigDecimal object : estcostList) {


			 estiCost = object;
			if (estiCost.compareTo(currcost) == 1) {
				return true;
			}
			if (estiCost.compareTo(currcost) == -1) {
				return false;
			}
			if (estiCost.compareTo(currcost) == 0) {
				return true;
			}
		}

		if(currcost != null && estiCost == null) {
			return true;
		}
		return false;

	}

	public void addRawMI(SciRawMIDetails midetails) {
		em.persist(midetails);
		
	}

	@Override
	public void deleteRawMI(Long seqMiId,Long seqVendorId,Long rawMiId,String stageDesc) {
		Query qry = em.createQuery("delete from SciRawMIDetails rawmi where rawmi.subcontractMIMaster.seqMiId=:seqSubContractId and rawmi.sciVendorMaster.seqVendorId=:seqVendorId and rawmi.rawMIMaster.seqMiId=:seqrawMiId and rawmi.stageDesc=:stageDesc");
		qry.setParameter("seqSubContractId",seqMiId);
		qry.setParameter("seqVendorId",seqVendorId);
		qry.setParameter("seqrawMiId",rawMiId);
		qry.setParameter("stageDesc",stageDesc);
		qry.executeUpdate();
	}


}
