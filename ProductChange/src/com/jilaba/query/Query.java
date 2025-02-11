package com.jilaba.query;

import com.jilaba.form.FrmProductChange;

public class Query {

	public String getProdctChangeQuery() {
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("select MasterDB,TranDB,SchemeDB from FILEMAIN as F \n");
		sb.append("left join Computer as c on c.CompCode=f.CompId \n");
		sb.append("where  c.IPAdd='192.168.1.227' and c.LogId<>0 \n");
		sb.append("and GETDATE() between FromDate and ToDate");
		
		return sb.toString();
	}

	public String getSubProductDetails(int procode) {
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("select p.Procode,(case when p.CategoryProduct='Y' then p.ProName + ' CATEGORY PRODUCT' else p.ProName end) as ProName,subcode,subProName from subproduct as su \n");
		sb.append("left join Product as p \n");
		sb.append("on p.ProCode=su.ProCode \n");
		sb.append("where su.procode=" + procode);
		
		return sb.toString();
		
	}
	public String productUpdate(String tblName) {
		StringBuilder sb=new StringBuilder();
		
		sb.append("update "+ tblName +" set procode=" + Integer.parseInt(FrmProductChange.txtNewProCode.getText())  +" where procode=" + Integer.parseInt(FrmProductChange.txtprocode.getText()));
		
		return sb.toString();
		
	}
	
	
}
