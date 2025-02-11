package com.jilaba.applicationstart;

import javax.swing.JOptionPane;

import com.jilaba.common.DbConnection;
import com.jilaba.common.ReturnStatus;
import com.jilaba.fileresource.FileRead;
import com.jilaba.fileresource.JilabaFile;
import com.jilaba.form.LoginForm;

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
			
			LoginForm logicForm=new LoginForm();
			logicForm.setVisible(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}

}
