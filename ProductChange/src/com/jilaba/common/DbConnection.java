package com.jilaba.common;

import java.sql.Connection;
import java.sql.DriverManager;

import com.jilaba.fileresource.Register;
import com.jilaba.fileresource.Server;
import com.jilaba.logic.Logic;

public class DbConnection {

	private String drivername,driverManager,compyDb;
	
	@SuppressWarnings("deprecation")
	public DbConnection() throws Exception {
		
		drivername="net.sourceforge.jtds.jdbc.Driver"; 	
		Class.forName(drivername).newInstance();
		
		compyDb=Register.getCompanyId()+"CompyDb";
		
		Common.setConCompydb(getConnection(compyDb));
		
		Logic logic=new Logic();
		Common.setLstDbNames(logic.getDbName());
		
		Common.setConMasterdb(getConnection(Common.getLstDbNames().get(0).getMasterdb()));
		Common.setConTranDb(getConnection(Common.getLstDbNames().get(0).getTrandb()));
		Common.setConSchemeDb(getConnection(Common.getLstDbNames().get(0).getSchemedb()));
		
	}
	private Connection getConnection(String DbName) throws Exception {
		
		driverManager="jdbc:jtds:SqlServer://"+ Server.getServerName()+ ":" + Server.getPortNo() + "/" + DbName;
		return DriverManager.getConnection(driverManager, Server.getUserName(), Server.getPassword());
	}
	
	
}
