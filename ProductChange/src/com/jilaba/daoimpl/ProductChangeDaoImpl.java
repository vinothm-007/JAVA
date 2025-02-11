package com.jilaba.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.jilaba.common.Common;
import com.jilaba.dao.ProductChangeDao;
import com.jilaba.form.FrmProductChange;
import com.jilaba.modle.DbNames;
import com.jilaba.query.Query;

public class ProductChangeDaoImpl implements ProductChangeDao{
	Query query=new Query();
	@Override
	public List<DbNames> getDbName() throws Exception {
		Connection connection=Common.getConCompydb();
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(query.getProdctChangeQuery());
		
		if(!resultSet.isBeforeFirst()) {
			throw new Exception("Database Details Not Found...!");
		}
		
		List<DbNames> lstDbNames=new LinkedList<DbNames>();
		while (resultSet.next()) {
			
			DbNames dbNames=new DbNames();
			
			dbNames.setMasterdb(resultSet.getString("MasterDB"));
			dbNames.setTrandb(resultSet.getString("TranDB"));
			dbNames.setSchemedb(resultSet.getString("SchemeDB"));
			
			lstDbNames.add(dbNames);
		}
		
		return lstDbNames;
	}
	@Override
	public List<Map<String, Object>> getSubProductDetails(int procode) throws Exception {
		
		Connection connection=Common.getConMasterdb();
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(query.getSubProductDetails(procode));
		
		if (!resultSet.isBeforeFirst()) {
			throw new Exception("Invalid Product...!");
		}
		
		List<Map<String, Object>> lstSubProductDetails=new LinkedList<Map<String,Object>>();
		while(resultSet.next()) {
			
			Map<String, Object> maps=new HashMap<String, Object>();
			
			maps.put("Procode", resultSet.getInt("Procode"));
			maps.put("NewProcode", FrmProductChange.txtNewProCode.getText());
			maps.put("subcode", resultSet.getInt("subcode"));
			maps.put("subProName", resultSet.getString("subProName"));
			maps.put("proName", resultSet.getString("proName"));
			
			lstSubProductDetails.add(maps);
		}
		
		return lstSubProductDetails;
	}
	@Override
	public void productUpdate(String tblName) throws Exception {
		Connection connection=Common.getConMasterdb();
		try {
			
			
			Statement statement;
			statement = connection.createStatement();
			connection.setAutoCommit(false);
			statement.executeUpdate(query.productUpdate(tblName));
			connection.commit();
			connection.setAutoCommit(true);
			
		} catch (SQLException e) {
			connection.setAutoCommit(false);
			connection.rollback();
			throw new Exception(e.getMessage());
		}
		
	}


}
