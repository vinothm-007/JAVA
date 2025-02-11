package com.jilaba.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.plaf.BorderUIResource;

import com.jilaba.common.Common;

public class MdiForm extends JFrame implements ActionListener,MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel,buttonPanel,contentPanel,infoPanell,menuPanel;
	private JLabel lblMenuHeading,lblMenuItemHeading;
	private JButton btnAdd,btnSave,btnView,btnEdit,btnClear,btnClose;
	private JMenuBar mnuBar;
	private JMenu mnuMaster,mnuExit;
	public MdiForm() {
		
		getContentPane().setPreferredSize(new Dimension(Common.getScreenWidth(), Common.getScreenHeight()));
		setUndecorated(true);
		pack();
		
		setResizable(false);
		setTitle("MDI");
		
		componentCreation();
	}

	private void componentCreation() {
		
		JMenuItem mnuItmProductChange;
		
		mainPanel =new JPanel(null);
		mainPanel.setBounds(0, 0, Common.getScreenWidth(), Common.getScreenHeight());
		mainPanel.setBackground(Color.getHSBColor(652, 10, 20));
		mainPanel.setVisible(true);
		getContentPane().add(mainPanel);
		
		
		infoPanell=new JPanel(null);
		infoPanell.setBounds(0, 0, Common.getScreenWidth(), 40);
//		menuPanel.setBackground(Color.getHSBColor(10, 10, 20));
		infoPanell.setBackground(Color.lightGray);
		infoPanell.setBorder(LineBorder.createBlackLineBorder());
		infoPanell.setVisible(true);
		mainPanel.add(infoPanell);
		
		menuPanel=new JPanel(null);
		menuPanel.setBounds(0, infoPanell.getHeight(), Common.getScreenWidth(), 50);
//		menuPanel.setBackground(Color.getHSBColor(10, 10, 20));
		menuPanel.setBackground(Color.yellow);
		menuPanel.setBorder(new BorderUIResource(LineBorder.createBlackLineBorder()));
		menuPanel.setVisible(true);
		mainPanel.add(menuPanel);
		
		
		buttonPanel=new JPanel(null);
		buttonPanel.setBounds(0, Common.getScreenHeight()-50, Common.getScreenWidth(), 50);
		buttonPanel.setBackground(Color.cyan);
		buttonPanel.setVisible(false);
		buttonPanel.setBorder(LineBorder.createBlackLineBorder());
		buttonPanel.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		mainPanel.add(buttonPanel);
		
		
		contentPanel=new JPanel(null);
		contentPanel.setBounds(0, infoPanell.getHeight()+menuPanel.getHeight(), Common.getScreenWidth(), Common.getScreenHeight()-(infoPanell.getHeight()+buttonPanel.getHeight()+menuPanel.getHeight()));
		contentPanel.setBackground(Color.green);
		contentPanel.setVisible(true);
		mainPanel.add(contentPanel);
		
		Common.setInternalHeight( Common.getScreenHeight()-(infoPanell.getHeight()+buttonPanel.getHeight()+menuPanel.getHeight()));
		Common.setInternalWidth(Common.getScreenWidth());
		
		
		lblMenuHeading=new JLabel("");
		lblMenuHeading.setBounds(10, 10, 110, 30);
		lblMenuHeading.setVisible(false);
		menuPanel.add(lblMenuHeading);
		
		lblMenuItemHeading=new JLabel("");
		lblMenuItemHeading.setBounds(lblMenuHeading.getX()+90, lblMenuHeading.getY(), 120, 30);
		lblMenuItemHeading.setVisible(true);
		menuPanel.add(lblMenuItemHeading);
		
		mnuBar=new JMenuBar();
		mnuBar.setBounds(0, 0, menuPanel.getWidth(), menuPanel.getHeight());
//		mnuBar.setBounds(0, 0, infoPanell.getWidth(), 50);
		mnuBar.setBackground(Color.white);
		mnuBar.setVisible(true);
		menuPanel.add(mnuBar);
		
		mnuMaster=new JMenu("Master");
		mnuMaster.setVisible(true);
		mnuBar.add(mnuMaster);
		
		mnuExit=new JMenu("Exit");
		mnuExit.addMouseListener(this);
		mnuExit.setVisible(true);
		mnuBar.add(mnuExit);
		
		mnuItmProductChange=new JMenuItem("ProductChange");
		mnuItmProductChange.addActionListener(this);
		mnuItmProductChange.setVisible(true);
		mnuMaster.add(mnuItmProductChange);
		
		btnAdd=new JButton("Add");
		btnAdd.setBounds(100, 10, 120, 30);
		btnAdd.addActionListener(this);
		btnAdd.setVerifyInputWhenFocusTarget(false);
		btnAdd.setVisible(true);
		buttonPanel.add(btnAdd);
		
		
		btnSave=new JButton("Save");
		btnSave.setBounds(btnAdd.getX()+210, btnAdd.getY(),120, 30);
		btnSave.setVisible(true);
		buttonPanel.add(btnSave);
		
		btnView=new JButton("View");
		btnView.setBounds(btnSave.getX()+210, btnAdd.getY(), 120, 30);
		btnView.setVerifyInputWhenFocusTarget(false);
		btnView.setVisible(true);
		buttonPanel.add(btnView);
		
		btnEdit=new JButton("Edit");
		btnEdit.setBounds(btnView.getX()+210, btnView.getY(), 120, 30);
		btnEdit.setVisible(true);
		buttonPanel.add(btnEdit);
		
		btnClear=new JButton("Clear");
		btnClear.setBounds(btnEdit.getX()+210, btnEdit.getY(), 120, 30);
		btnClear.addActionListener(this);
		btnClear.setVerifyInputWhenFocusTarget(false);
		btnClear.setVisible(true);
		buttonPanel.add(btnClear);
		
		btnClose=new JButton("Close");
		btnClose.setBounds(btnClear.getX()+210, btnClear.getY(), 120, 30);
		btnClose.addActionListener(this);
		btnClose.setVerifyInputWhenFocusTarget(false);
		btnClose.setVisible(true);
		buttonPanel.add(btnClose);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand().toLowerCase()) {
		case "Exit": 
			this.dispose();
			LoginForm logicForm=new LoginForm();
			logicForm.setVisible(true);
			break;
		case "productchange":
			contentPanel.setVisible(true);
			buttonPanel.setVisible(true);
			contentPanel.removeAll();
			
			mnuBar.setVisible(false);
			lblMenuHeading.setText("MASTER >>>>>");
			lblMenuItemHeading.setText(e.getActionCommand().toUpperCase());
			lblMenuHeading.setVisible(true);
			lblMenuItemHeading.setVisible(true);
			FrmProductChange frmProductChange=new FrmProductChange();
			frmProductChange.setVisible(true);
//			contentPanel.add(frmProductChange,JDesktopPane.POPUP_LAYER);
			contentPanel.add(frmProductChange);
			
			Common.setFrmAction(frmProductChange);
			
			btnAdd.requestFocus();
			break;
		case "add":
			Common.getFrmAction().Add();
			break;
		case "clear":
			Common.getFrmAction().clear();
			btnAdd.requestFocus();
			break;
		case "close":
//			contentPanel.setVisible(false);
			contentPanel.removeAll();
			buttonPanel.setVisible(false);
			lblMenuHeading.setVisible(false);
			lblMenuItemHeading.setVisible(false);
			mnuBar.setVisible(true);
			break;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource()==mnuExit) {
			this.dispose();
			LoginForm loginForm=new LoginForm();
			loginForm.setVisible(true);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
