package com.jilaba.common;

import java.sql.Connection;
import java.sql.DriverManager;

import com.jilaba.Logic.LogicPurchase;
import com.jilaba.fileresource.Register;
import com.jilaba.fileresource.Server;
import com.jilaba.model.DbNamePojo;

public class DbConnection {
	public static Connection compyDbCon,masterCon,tranCon,stockCon,schemeCon;
	private String drivername,driverManager,compyDb;
	private static DbNamePojo dbNamePojo=null;
	@SuppressWarnings("deprecation")
	public DbConnection() throws Exception {
		
		drivername="net.sourceforge.jtds.jdbc.Driver"; 	
		Class.forName(drivername).newInstance();
		
		compyDb=Register.getCompanyId()+"CompyDb";
		compyDbCon=getConnection(compyDb);
		
		LogicPurchase logicPurchase=new LogicPurchase();
		dbNamePojo=logicPurchase.getDbName();
		
		masterCon=getConnection(dbNamePojo.getMasterdb());
		tranCon=getConnection(dbNamePojo.getTrandb());
		schemeCon=getConnection(dbNamePojo.getSchemedb());
		stockCon=getConnection(dbNamePojo.getStockdb());
		
	}
	private Connection getConnection(String DbName) throws Exception {
		
		driverManager="jdbc:jtds:SqlServer://"+ Server.getServerName()+ ":" + Server.getPortNo() + "/" + DbName;
		return DriverManager.getConnection(driverManager, Server.getUserName(), Server.getPassword());
	}
	public static DbNamePojo getDbNamePojo() {
		return dbNamePojo;
	}
	
}
