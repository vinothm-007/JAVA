package com.jilaba.Logic;

import java.util.List;

import com.jilaba.ServiceImpl.PurchaseServiceImpl;
import com.jilaba.model.DbNamePojo;
import com.jilaba.model.MenuHeadingPojo;
import com.jilaba.model.PurchasePojo;

public class LogicPurchase {
	PurchaseServiceImpl purchaseServiceImpl=new PurchaseServiceImpl();
	
	public DbNamePojo getDbName() throws Exception{
		return purchaseServiceImpl.getDbName();
	}
	public int getTranno(String controlCode) throws Exception{
		return purchaseServiceImpl.getTranno(controlCode);
	}
	public MenuHeadingPojo getMenuHeading(int moduleid) throws Exception {
		return purchaseServiceImpl.getMenuHeading(moduleid);
	}
	public List<PurchasePojo> getProductType(String tblName) throws Exception{
		return purchaseServiceImpl.getProductType(tblName);
	}
	public List<PurchasePojo> getNature()throws Exception{
		return purchaseServiceImpl.getNature();
	}
	public List<PurchasePojo> getMetal() throws Exception{
		return purchaseServiceImpl.getMetal();
	}
	public List<PurchasePojo> getCategory(int natid,String metalCode) throws Exception{
		return purchaseServiceImpl.getCategory(natid,metalCode);
	}
	public List<PurchasePojo> getTagType() throws Exception{
		return purchaseServiceImpl.getTagType();
	}
	public List<PurchasePojo> getUnit(String unitCode) throws Exception{
		return purchaseServiceImpl.getUnit(unitCode);
	}
	public PurchasePojo getProduct(int catCode) throws Exception{
		return purchaseServiceImpl.getProduct(catCode);
	}
	public Double getPurchaseRate(String metalCode,double purity) throws Exception{
		return purchaseServiceImpl.getPurchaseRate(metalCode, purity);
	}
	public List<PurchasePojo> getEmployee(int empId) throws Exception{
		return purchaseServiceImpl.getEmployee(empId);
	}
}
