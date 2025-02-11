package com.jilaba.DaoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.jilaba.Dao.PurchaeDao;
import com.jilaba.Form.FrmPurchase;
import com.jilaba.common.DbConnection;
import com.jilaba.model.DbNamePojo;
import com.jilaba.model.MenuHeadingPojo;
import com.jilaba.model.PurchasePojo;
import com.jilaba.query.PurchaseQuery;

public class PurchaseDaoImpl implements PurchaeDao{
	PurchaseQuery purchaseQuery=new PurchaseQuery();
	@Override
	public List<PurchasePojo> getProductType(String tableName) throws Exception {
		Connection connection=DbConnection.masterCon;
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(purchaseQuery.getPurchaseType(tableName));
		
		List<PurchasePojo> lstProductType=new LinkedList<PurchasePojo>();
		while (resultSet.next()) {
			PurchasePojo productTypePojo=new PurchasePojo();
			
			productTypePojo.setPurchaseTypeCode(resultSet.getString("Typecode"));
			productTypePojo.setPurchaseType(resultSet.getString("TypeName"));
			
			lstProductType.add(productTypePojo);
		}
		
		return lstProductType;
	}
	@Override
	public DbNamePojo getDbName() throws Exception {
		Connection connection=DbConnection.compyDbCon;
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(purchaseQuery.getDbName());
		
		DbNamePojo dbNamePojo=new DbNamePojo();
		
		if(resultSet.next()) {
			
			dbNamePojo.setCompid(resultSet.getString("Compid"));
			dbNamePojo.setMasterdb(resultSet.getString("masterdb"));
			dbNamePojo.setTrandb(resultSet.getString("trandb"));
			dbNamePojo.setStockdb(resultSet.getString("stockdb"));
			dbNamePojo.setSchemedb(resultSet.getString("schemedb"));
			
		}
		return dbNamePojo;
	}
	@Override
	public List<PurchasePojo> getNature() throws Exception {
		
		Connection connection=DbConnection.masterCon;
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(purchaseQuery.getNature());
		
		List<PurchasePojo> lstPurchasePojos=new LinkedList<PurchasePojo>();
		while(resultSet.next()) {
			PurchasePojo purchasePojo=new PurchasePojo();
			
			purchasePojo.setNatId(resultSet.getInt("Natid"));
			purchasePojo.setNatureName(resultSet.getString("NatName"));
			
			lstPurchasePojos.add(purchasePojo);
		}
		
		return lstPurchasePojos;
	}
	@Override
	public List<PurchasePojo> getMetal() throws Exception {
		
		Connection connection=DbConnection.masterCon;
		Statement statement =connection.createStatement();
		ResultSet resultSet=statement.executeQuery(purchaseQuery.getMetal());
		
		List<PurchasePojo> lstPurchasePojos=new LinkedList<PurchasePojo>();
		while(resultSet.next()) {
			PurchasePojo purchasePojo=new PurchasePojo();
			
			purchasePojo.setMetalCode(resultSet.getString("metalcode"));
			purchasePojo.setMetalName(resultSet.getString("metalname"));
			
			lstPurchasePojos.add(purchasePojo);
			
		}
		
		return lstPurchasePojos;
	}
	@Override
	public List<PurchasePojo> getCategory(int natid,String metalCode) throws Exception {
		Connection connection=DbConnection.masterCon;
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(purchaseQuery.getCategory(natid,metalCode));
		
		List<PurchasePojo> lstPurchasePojos=new LinkedList<PurchasePojo>();
		
		if(!resultSet.isBeforeFirst()) {
			throw new Exception("Category Product Not Found...!");
		}
		
		while (resultSet.next()) {
			PurchasePojo purchasePojo=new PurchasePojo();
			
			purchasePojo.setCatCode(resultSet.getInt("CatCode"));
			purchasePojo.setCategoryName(resultSet.getString("CatName"));
	
			lstPurchasePojos.add(purchasePojo);
		}
		return lstPurchasePojos;
	}
	@Override
	public List<PurchasePojo> getTagType() throws Exception {
		Connection connection=DbConnection.masterCon;
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(purchaseQuery.getTagType());
		
		List<PurchasePojo> lstPurchasePojos=new LinkedList<PurchasePojo>();
		while (resultSet.next()) {
			
			PurchasePojo purchasePojo=new PurchasePojo();
			
			purchasePojo.setTagTypeCode(resultSet.getInt("TagTypeCode"));
			purchasePojo.setTagTypeName(resultSet.getString("TagTypeName"));
			
			lstPurchasePojos.add(purchasePojo);
		}
		return lstPurchasePojos;
	}
	@Override
	public List<PurchasePojo> getUnit(String unitCode) throws Exception {
		Connection connection=DbConnection.masterCon;
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(purchaseQuery.getUnit(unitCode));
		
		List<PurchasePojo> lstPurchasePojos=new LinkedList<PurchasePojo>();
		while(resultSet.next()) {
			PurchasePojo purchasePojo=new PurchasePojo();
			
			purchasePojo.setUnitCode(resultSet.getString("unitCode"));
			purchasePojo.setUnitName(resultSet.getString("UnitName"));
			
			lstPurchasePojos.add(purchasePojo);
		}
		return lstPurchasePojos;
	}
	@Override
	public MenuHeadingPojo getMenuHeading(int modulecode) throws Exception {
		Connection connection=DbConnection.compyDbCon;
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(purchaseQuery.getMenuHeading(modulecode));
		
		MenuHeadingPojo menuHeadingPojo=new MenuHeadingPojo();
		if (resultSet.next()) {
			menuHeadingPojo.setOperatorName(resultSet.getString("opername"));
			menuHeadingPojo.setModulename(resultSet.getString("modulename"));
			menuHeadingPojo.setVersion(resultSet.getString("CurrentVersion"));
			menuHeadingPojo.setFinancialYear(resultSet.getString("FinancialYear"));
		}
		return menuHeadingPojo;
	}
	@Override
	public int getTranno(String controlcode) throws Exception {
		Connection connection=DbConnection.tranCon;
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(purchaseQuery.getTranno(controlcode));
		
		int tranno=0;
		if (resultSet.next()) {
			tranno=resultSet.getInt("Ctrl_long");
		}
		return tranno;
	}
	@Override
	public PurchasePojo getProduct(int catCode) throws Exception {
		Connection connection=DbConnection.masterCon;
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(purchaseQuery.getProduct(catCode));
		
		PurchasePojo purchasePojo=new PurchasePojo();
		if(resultSet.next()) {
			purchasePojo.setProductCode(resultSet.getInt("ProCode"));
			purchasePojo.setProductName(resultSet.getString("ProName"));
			purchasePojo.setPurity(resultSet.getDouble("purity"));
			purchasePojo.setRate(resultSet.getDouble("rate"));
			purchasePojo.setUnitCode(resultSet.getString("unit"));
		}
		return purchasePojo;
	}
	@Override
	public Double getPurchaseRate(String metalCode, double purity) throws Exception {
		Connection connection=DbConnection.masterCon;
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(purchaseQuery.getPurchaseRate(metalCode, purity));
		
		double purityRate=0;
		if(resultSet.next()) {
			purityRate=resultSet.getDouble("Rate");
		}
		return purityRate;
	}
	@Override
	public List<PurchasePojo> getEmployee(int empId) throws Exception {
		Connection connection=DbConnection.masterCon;
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(purchaseQuery.getEmployeeDetails(empId));
		
		List<PurchasePojo> lstPurchasePojos=new LinkedList<PurchasePojo>();
		
		if(!resultSet.isBeforeFirst()) {
			FrmPurchase.lblSalesPersonName.setText("");
			throw new Exception("Invalid Sales Persion");
		}
		
		while (resultSet.next()) {
			
			PurchasePojo purchasePojo=new PurchasePojo();
			
			purchasePojo.setEmpId(resultSet.getString("EmpUID"));
			purchasePojo.setEmpName(resultSet.getString("EmpName"));
			
			lstPurchasePojos.add(purchasePojo);
			
		}
		
		return lstPurchasePojos;
	}

}
