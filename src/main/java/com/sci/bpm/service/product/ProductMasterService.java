package com.sci.bpm.service.product;

import java.util.List;
import java.util.Map;

import com.sci.bpm.db.model.SciMatcatMaster;
import com.sci.bpm.db.model.SciMatindMaster;
import com.sci.bpm.db.model.SciMatspecMaster;
import com.sci.bpm.db.model.SciMattypeMaster;

public interface ProductMasterService {
	public List selectProdCat();
	
	public List selectProducts(String cat);
	public List<SciMatspecMaster> selectProducts(String cat, String matDept);
	
	public List selectProdType();

	public List<SciMattypeMaster> getMatType(String matCode);
	public String getMatDept(String matTypeCode);
	
	public List selectCategory(String mattype,String matDept);
	public List selectCategory(String matDept);
	
	public String getMatType(String cat,String dept);
	public SciMatcatMaster getMatTypeCat(String cat, String dept);
}
