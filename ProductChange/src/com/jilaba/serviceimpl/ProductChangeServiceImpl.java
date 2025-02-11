package com.jilaba.serviceimpl;

import java.util.List;
import java.util.Map;

import com.jilaba.dao.ProductChangeDao;
import com.jilaba.daoimpl.ProductChangeDaoImpl;
import com.jilaba.modle.DbNames;
import com.jilaba.service.ProductChange;

public class ProductChangeServiceImpl implements ProductChange {
	ProductChangeDao productChangeDao=new ProductChangeDaoImpl();
	@Override
	public List<DbNames> getDbName() throws Exception {
		return productChangeDao.getDbName();
	}
	@Override
	public List<Map<String, Object>> getSubProductDetails(int procode) throws Exception {
		return productChangeDao.getSubProductDetails(procode);
	}
	@Override
	public void productUpdate(String tblName) throws Exception {
		 productChangeDao.productUpdate(tblName);
	}

}
