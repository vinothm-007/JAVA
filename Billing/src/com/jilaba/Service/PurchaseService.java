package com.jilaba.Service;

import java.util.List;

import com.jilaba.model.DbNamePojo;
import com.jilaba.model.MenuHeadingPojo;
import com.jilaba.model.PurchasePojo;

public interface PurchaseService {
	public DbNamePojo getDbName() throws Exception;
	public int getTranno(String controlCode) throws Exception;
	public MenuHeadingPojo getMenuHeading(int modulecode) throws Exception;
	public List<PurchasePojo> getProductType(String tblName) throws Exception;
	public List<PurchasePojo> getNature() throws Exception;
	public List<PurchasePojo> getMetal() throws Exception;
	public List<PurchasePojo> getCategory(int natid,String metalCode) throws Exception;
	public List<PurchasePojo> getTagType() throws Exception;
	public List<PurchasePojo> getUnit(String unitCode) throws Exception;
	public PurchasePojo getProduct(int catCode) throws Exception;
	public Double getPurchaseRate(String metalCode,Double purity) throws Exception;
	public List<PurchasePojo> getEmployee(int empId) throws Exception;
	
}
