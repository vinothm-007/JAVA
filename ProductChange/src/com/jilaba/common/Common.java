package com.jilaba.common;

import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import com.jilaba.modle.DbNames;
import com.jilaba.service.FormAction;

public class Common {

	private static int screenWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
	private static int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
	private static int internalWidth=0;
	private static int internalHeight=0;
	private static FormAction frmAction;
	private static Connection conCompydb,conMasterdb,conTranDb,conSchemeDb;
	private static List<DbNames> lstDbNames=new LinkedList<DbNames>();

	private static Font headingFont=new Font("Agfa rotis semi serif",Font.BOLD,18);
	private static Font lableFont=new Font("Elephant",Font.PLAIN,18);
	
	
	
	public static int getInternalWidth() {
		return internalWidth;
	}

	public static void setInternalWidth(int internalWidth) {
		Common.internalWidth = internalWidth;
	}

	public static int getInternalHeight() {
		return internalHeight;
	}

	public static void setInternalHeight(int internalHeight) {
		Common.internalHeight = internalHeight;
	}

	
	
	
	
	public static Font getLableFont() {
		return lableFont;
	}

	public static Font getHeadingFont() {
		return headingFont;
	}

	public static int getScreenWidth() {
		return screenWidth;
	}

	public static int getScreenHeight() {
		return screenHeight;
	}
	public static FormAction getFrmAction() {
		return frmAction;
	}

	public static void setFrmAction(FormAction frmAction) {
		Common.frmAction = frmAction;
	}
	
	public static Connection getConCompydb() {
		return conCompydb;
	}

	public static void setConCompydb(Connection conCompydb) {
		Common.conCompydb = conCompydb;
	}

	public static Connection getConMasterdb() {
		return conMasterdb;
	}

	public static void setConMasterdb(Connection conMasterdb) {
		Common.conMasterdb = conMasterdb;
	}

	public static Connection getConTranDb() {
		return conTranDb;
	}

	public static void setConTranDb(Connection conTranDb) {
		Common.conTranDb = conTranDb;
	}

	public static Connection getConSchemeDb() {
		return conSchemeDb;
	}

	public static void setConSchemeDb(Connection conSchemeDb) {
		Common.conSchemeDb = conSchemeDb;
	}

	public static List<DbNames> getLstDbNames() {
		return lstDbNames;
	}

	public static void setLstDbNames(List<DbNames> lstDbNames) {
		Common.lstDbNames = lstDbNames;
	}
}
