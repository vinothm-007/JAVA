package com.jilaba.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.jilaba.common.Common;
import com.jilaba.control.JTextFieldEnum.TextInputType;
import com.jilaba.control.JilabaColumn;
import com.jilaba.control.JilabaTable;
import com.jilaba.control.JilabaTextField;
import com.jilaba.logic.Logic;
import com.jilaba.service.FormAction;

public class FrmProductChange extends JInternalFrame implements FormAction,KeyListener,ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel proChangePanel;
	private JLabel lblProName;
	public static JilabaTextField txtNewProCode,txtprocode;
	private JButton btnUpdate,btnView;
	JilabaTable tblProcode,tblNewProcode,tblSubProcode,tblNewSubProcode;
	
	private Logic logic=new Logic();
	
	public FrmProductChange() {
		((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).setNorthPane(null);
		getContentPane().setPreferredSize(new Dimension(Common.getInternalWidth(),Common.getInternalHeight()));
		pack();
		componentCreation();
		componentListener();
		initalize();
		componentDisable();
	}

	private void componentListener() {
		
		txtprocode.setInputVerifier(new InputVerifier() {
			
			@Override
			public boolean verify(JComponent input) {
				if("".equalsIgnoreCase(txtprocode.getText())) {
					JOptionPane.showMessageDialog(null, "Product Code is Empty...!");
					return false;
				}
				return true;
			}
		});
		
		txtNewProCode.setInputVerifier(new InputVerifier() {
			
			@Override
			public boolean verify(JComponent input) {
				if("".equalsIgnoreCase(txtNewProCode.getText())) {
					JOptionPane.showMessageDialog(null, "New Product Code is Empty...!");
					return false;
				}
				return true;
			}
		});
		
	}

	private void initalize() {
		txtprocode.requestFocus();
		txtprocode.setText("");
		tblProcode.clear();
		txtNewProCode.setText("");
		lblProName.setVisible(false);
	}

	private void componentDisable() {
		
		for(int i=0;i<proChangePanel.getComponentCount();i++) {
			proChangePanel.getComponent(i).setEnabled(false);
		}
		proChangePanel.setEnabled(false);
	}
	private void componentEnable() {
		
		for(int i=0;i<proChangePanel.getComponentCount();i++) {
			proChangePanel.getComponent(i).setEnabled(true);
		}
		proChangePanel.setEnabled(true);
	}
	private void componentCreation() {
		
		
		
		JLabel lblProCode,lblNewProCode;
		
		JScrollPane scrProcode,scrNewProcode,scrSubproCode,scrNewSubProcode;
		
		
		proChangePanel=new JPanel(null);
		proChangePanel.setBounds(0, 0, Common.getInternalWidth(), Common.getInternalHeight());
		proChangePanel.setBackground(Color.pink);
		proChangePanel.setVisible(true);
		getContentPane().add(proChangePanel);
		
		
		lblProCode=new JLabel("PRODUCT CODE : ");
		lblProCode.setBounds(40, 120, 120, 30);
		lblProCode.setVisible(true);
		proChangePanel.add(lblProCode);
		
		txtprocode=new JilabaTextField();
		txtprocode.setBounds(lblProCode.getX()+140, lblProCode.getY(), 150, 30);
		txtprocode.setTextType(TextInputType.NUMERIC);
		txtprocode.setMaxLength(5);
		txtprocode.addKeyListener(this);
		txtprocode.setVisible(true);
		proChangePanel.add(txtprocode);
		
		
		lblProName=new JLabel("ProName");
		lblProName.setBounds(txtprocode.getX(),txtprocode.getY()+25, 350, 30);
		lblProName.setVisible(false);
		proChangePanel.add(lblProName);
		
		
		lblNewProCode=new JLabel("NEW PRODUCT CODE : ");
		lblNewProCode.setBounds(txtprocode.getX()+220, txtprocode.getY(), 170, 30);
		lblNewProCode.setVisible(true);
		proChangePanel.add(lblNewProCode);
		
		
		txtNewProCode=new JilabaTextField();
		txtNewProCode.setBounds(lblNewProCode.getX()+160, lblNewProCode.getY(), 150, 30);
		txtNewProCode.setTextType(TextInputType.NUMERIC);
		txtNewProCode.setMaxLength(5);
		txtNewProCode.addKeyListener(this);
		txtNewProCode.setVisible(true);
		proChangePanel.add(txtNewProCode);
		
		
		btnView=new JButton("View");
		btnView.setBounds(txtNewProCode.getX()+270, txtNewProCode.getY(), 120, 30);
		btnView.addActionListener(this);
		btnView.setVisible(true);
		proChangePanel.add(btnView);
		
		btnUpdate=new JButton("Update");
		btnUpdate.setBounds(btnView.getX()+160, btnView.getY(), 120, 30);
		btnUpdate.addActionListener(this);
		btnUpdate.setVisible(true);
		proChangePanel.add(btnUpdate);
		
		
		tblProcode=new JilabaTable(proCodeColumn());
		tblProcode.addKeyListener(this);
		scrProcode=new JScrollPane(tblProcode);
		scrProcode.setBounds(txtprocode.getX()-50, lblProCode.getY()+150, 950, 250);
		scrProcode.setVisible(true);
		proChangePanel.add(scrProcode);
		
	}

	private List<JilabaColumn> proCodeColumn() {
		
		List<JilabaColumn> lstSubProcodeColumn=new LinkedList<JilabaColumn>();
		
		lstSubProcodeColumn.add(new JilabaColumn("SNO",Integer.class,50,SwingConstants.RIGHT));
		lstSubProcodeColumn.add(new JilabaColumn("OLD PROCODE",Integer.class,180,SwingConstants.RIGHT));
		lstSubProcodeColumn.add(new JilabaColumn("NEW PROCODE",String.class,180,SwingConstants.RIGHT));
		lstSubProcodeColumn.add(new JilabaColumn("SUBPROCODE",Integer.class,180,SwingConstants.RIGHT));
		lstSubProcodeColumn.add(new JilabaColumn("SUBPRODUCT NAME",String.class,355,SwingConstants.LEFT));
		
		return lstSubProcodeColumn;
	}

	@Override
	public void Add() {
		componentEnable();
		initalize();
	}

	@Override
	public void clear() {
		initalize();
		componentDisable();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		try {
			
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				
				if(e.getSource()==txtprocode) {		
					
					if(!"".equalsIgnoreCase(txtprocode.getText())) {
						
						List<Map<String, Object>> lstTemp=new LinkedList<Map<String,Object>>();
						lstTemp=tblview();
						
						
						lblProName.setText(String.valueOf(lstTemp.get(0).get("proName")));
						lblProName.setForeground(Color.red);
						lblProName.setVisible(true);
					}
					txtNewProCode.requestFocus();
				}else if(e.getSource()==txtNewProCode) {
					btnView.requestFocus();
				}
			}else if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
				btnUpdate.requestFocus();
				btnUpdate.setBackground(Color.decode("#1234"));
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			switch (e.getActionCommand().toLowerCase())	{
				case "view": 			
					
					int i=1;
					tblProcode.clear();
					for (Map<String, Object> map : tblview()) {
						
						List<Object> lstObjects=new ArrayList<Object>();
						
						lstObjects.add(i++);
						lstObjects.add(map.get("Procode"));
						lstObjects.add(map.get("NewProcode"));
						lstObjects.add(map.get("subcode"));
						lstObjects.add(map.get("subProName").toString().toUpperCase());
						
						tblProcode.addRow(lstObjects);
					}
					
					tblProcode.requestFocus();
					break;
					
				case "update":
					update();
					initalize();
					break;
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	private void update() throws Exception {
		
		if("".equalsIgnoreCase(txtprocode.getText())) {
			JOptionPane.showMessageDialog(null, "Product Code is Empty.....!");
			txtprocode.requestFocus();
			return;
		}
		if("".equalsIgnoreCase(txtNewProCode.getText())) {
			JOptionPane.showMessageDialog(null, "New Product Code is Empty.....!");
			txtNewProCode.requestFocus();
			return;
		}
		
		logic.productUpdate("SubProduct");
		logic.productUpdate("Product");
		
		
	}

	private List<Map<String, Object>> tblview() throws Exception {
		
		return logic.getsubProductDetails(Integer.parseInt(txtprocode.getText()));
		
	}

}
