package com.jilaba.query;

import java.net.Inet4Address;

import com.jilaba.common.DbConnection;

public class PurchaseQuery {
	
	public String getDbName() throws Exception {
		StringBuilder sb=new StringBuilder();
		sb.append("select compid,MasterDB,TranDB,SchemeDB,StockDB from FILEMAIN as f\n");
		sb.append("left join Computer as c on c.FinYearFromDate between f.FromDate and f.ToDate\n");
		sb.append("where c.IPAdd='"+ Inet4Address.getLocalHost().getHostAddress() +"'");

		return sb.toString();
	}
	public String getMenuHeading(int moduleid) throws Exception{
		StringBuilder sb=new StringBuilder();
		
		sb.append("select o.opername,c.CompCode+'('+substring(Convert(varchar(10),Year(c.FinYearFromDate)),3,3) + '-' + substring(Convert(varchar(10),Year(c.FinYearFromDate)+1),3,3)+')' as FinancialYear\n");
		sb.append(",m.CurrentVersion,m.modulename\n");
		sb.append("from Computer as c\n");
		sb.append("left join " + DbConnection.getDbNamePojo().getMasterdb() + ".dbo.operator as o on o.opercode=c.opercode\n");
		sb.append("left join " + DbConnection.getDbNamePojo().getMasterdb() + ".dbo.modules as m on m.ModuleId="+ moduleid +"\n");
		
		return sb.toString();
	}
	public String getPurchaseType(String tblName)throws Exception {
		StringBuilder sb=new StringBuilder();
		sb.append("select TypeCode,TypeName from " + tblName);
		return sb.toString();
	}
	public String getNature() throws Exception {
		StringBuilder sb=new StringBuilder();
		sb.append("select NatId,NatName from ProdNature with (nolock) where NatType='RM'");
		return sb.toString();
	}
	public String getMetal()  throws Exception{
		StringBuilder sb=new StringBuilder();
		sb.append("select MetalCode,MetalName from Metal order by metalcode desc");
		return sb.toString();
	}
	public String getCategory(int natid,String metalcode) throws Exception {
		StringBuilder sb=new  StringBuilder();
		sb.append("select  CatCode,CatName from category where active<>'N' and EstReq<>'N' \n");
		if(natid>0) {
			sb.append("and NatId=" + natid + " and metalcode='"+ metalcode +"'");
		}
		return sb.toString();
	}
	public String getTagType() throws Exception {
		StringBuilder sb=new StringBuilder();
		sb.append("select  TagTypeCode,TagTypeName from TagType where Active='Y'");
		return sb.toString();
	}
	public String getUnit(String unitCode) throws Exception {
		StringBuilder sb=new StringBuilder();
		sb.append("select UnitCode,UnitName from Unit \n");
		
		if(!unitCode.equalsIgnoreCase("")) {
			sb.append("where unitcode='" + unitCode + "'");
		}
		
		return sb.toString();
	}
	public String getTranno(String controlCode) throws Exception{
		StringBuilder sb=new StringBuilder();
		sb.append("select Ctrl_Long from NumberControlCostCentre where Ctrl_Code='" + controlCode +"'");
    
		return sb.toString();
	}
	public String getProduct(int catCode)throws Exception{
		StringBuilder sb=new StringBuilder();
//		sb.append("select ProCode,ProName from product where catcode=" + catCode);
		
		sb.append("select p.ProCode,p.ProName,c.Purity,r.Rate,p.unit from product as p\n");
		sb.append("left join Category as c on c.catcode=p.CatCode \n");
		sb.append("left join RateMaster as r on RateGroup>=(select max(RateGroup) from RateMaster) and r.Purity=c.Purity and r.MetalCode=c.MetalCode \n");
		sb.append("where p.CatCode=" + catCode);
		
		return sb.toString();
	}
	public String getPurchaseRate(String metalCode,Double purity) {
		StringBuilder sb=new StringBuilder();
		sb.append("select Rate from ratemaster where rategroup >= (select max(RateGroup) from RateMaster) and MetalCode='" + metalCode +"' and Purity="+ purity);
		return sb.toString();
	}
	
	public String getEmployeeDetails(int EmpUid) {
		StringBuilder sb=new StringBuilder();
		
		sb.append("Select E.EmpUID,E.EmpName,E.Active From Employee E\n");
		sb.append("Left Join EmpDesignation ED On ED.EmpUID=E.EmpUID\n");
		sb.append("Left Join Designation D On D.DesignationCode=ED.DesignationCode\n");
		sb.append("Where D.DesignationCode = 1\n");
		sb.append("And ED.OrgCompanyCode='" + DbConnection.getDbNamePojo().getCompid() +"'\n");
		sb.append("And E.Active='Y'\n");
		if(EmpUid>0) {
			sb.append("And E.EmpUid like '%" + EmpUid + "'");
		}
		
		return sb.toString();
	}
}
