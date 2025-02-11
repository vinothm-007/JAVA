package com.jilaba.logic;

import java.util.List;
import java.util.Map;

import com.jilaba.modle.DbNames;
import com.jilaba.service.ProductChange;
import com.jilaba.serviceimpl.ProductChangeServiceImpl;

public class Logic {
	ProductChange productChangeService=new ProductChangeServiceImpl();
	
	public List<DbNames> getDbName() throws Exception {
		return productChangeService.getDbName();
	}
	public List<Map<String, Object>> getsubProductDetails(int procode) throws Exception{
		return productChangeService.getSubProductDetails(procode);
	}
	public void productUpdate(String tblName) throws Exception{
		productChangeService.productUpdate(tblName);
	}
}
