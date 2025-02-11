package com.jilaba.ApplicationStart;

import javax.swing.JOptionPane;

import com.jilaba.Form.MDIForm;
import com.jilaba.common.DbConnection;
import com.jilaba.common.ReturnStatus;
import com.jilaba.fileresource.FileRead;
import com.jilaba.fileresource.JilabaFile;

public class ApplicationStart {
	
	
	public static void main(String[] args) {
		try {
			
			FileRead fileRead=new FileRead();
			ReturnStatus returnStatus=fileRead.read(ApplicationStart.class, JilabaFile.REGISTER);
			
			if(!returnStatus.isStatus()) {
				throw new Exception(returnStatus.getDescription());
			}
			
			ReturnStatus returnStatus1=fileRead.read(ApplicationStart.class, JilabaFile.SERVER);
			
			if(!returnStatus1.isStatus()) {
				throw new Exception(returnStatus1.getDescription());
			}
			
			new DbConnection();
			MDIForm mdiForm=new MDIForm();
			mdiForm.setVisible(true);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
