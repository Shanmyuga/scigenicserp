package com.sci.bpm.dao.product;

import java.util.List;

import com.sci.bpm.db.model.SciMatcatMaster;
import com.sci.bpm.db.model.SciMatspecMaster;
import com.sci.bpm.db.model.SciMattypeMaster;
import com.sci.bpm.db.model.SciProductMaster;

public interface ProductMasterDAO {

	
	public List selectProdCat();
	
	public List<SciMatspecMaster> selectProducts(String cat);
	public List<SciMattypeMaster> getMatType(String matCode);

	public SciProductMaster loadProd(Long seqprodid);
	public List selectProdType();
	public List selectProducts(String cat,String matDept);
	public String getMatDept(String matTypeCode);
	public String getMatType(String cat,String dept);

	public List selectCategory(String mattype,String matDept);

	public List selectCategory(String matDept);
	public SciMatcatMaster getMatTypeCat(String cat, String dept);
}
