package com.jilaba.service;

import java.util.List;
import java.util.Map;

import com.jilaba.modle.DbNames;

public interface ProductChange {

	public List<DbNames> getDbName() throws Exception;
	public List<Map<String, Object>> getSubProductDetails(int procode) throws Exception;
	public void productUpdate(String tblName) throws Exception;
}
