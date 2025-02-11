package com.jilaba.ServiceImpl;

import java.util.List;

import com.jilaba.DaoImpl.PurchaseDaoImpl;
import com.jilaba.Service.PurchaseService;
import com.jilaba.model.DbNamePojo;
import com.jilaba.model.MenuHeadingPojo;
import com.jilaba.model.PurchasePojo;

public class PurchaseServiceImpl implements PurchaseService{
	PurchaseDaoImpl purchaseDaoImpl=new PurchaseDaoImpl();
	@Override
	public List<PurchasePojo> getProductType(String tblName) throws Exception {
		return purchaseDaoImpl.getProductType(tblName);
	}
	@Override
	public DbNamePojo getDbName() throws Exception {
		return purchaseDaoImpl.getDbName();
	}
	@Override
	public List<PurchasePojo> getNature() throws Exception {
		return purchaseDaoImpl.getNature();
	}
	@Override
	public List<PurchasePojo> getMetal() throws Exception {
		return purchaseDaoImpl.getMetal();
	}
	@Override
	public List<PurchasePojo> getCategory(int natid,String metalCode) throws Exception {
		return purchaseDaoImpl.getCategory(natid,metalCode);
	}
	@Override
	public List<PurchasePojo> getTagType() throws Exception {
		return purchaseDaoImpl.getTagType();
	}
	@Override
	public List<PurchasePojo> getUnit(String unitCode) throws Exception {
		return purchaseDaoImpl.getUnit(unitCode);
	}
	@Override
	public MenuHeadingPojo getMenuHeading(int modulecode) throws Exception {
		return purchaseDaoImpl.getMenuHeading(modulecode);
	}
	@Override
	public int getTranno(String controlCode) throws Exception {
		return purchaseDaoImpl.getTranno(controlCode);
	}
	@Override
	public PurchasePojo getProduct(int catCode) throws Exception {
		return purchaseDaoImpl.getProduct(catCode);
	}
	@Override
	public Double getPurchaseRate(String metalCode, Double purity) throws Exception {
		return purchaseDaoImpl.getPurchaseRate(metalCode, purity);
	}
	@Override
	public List<PurchasePojo> getEmployee(int empId) throws Exception {
		return purchaseDaoImpl.getEmployee(empId);
	}

}
