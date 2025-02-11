package com.jilaba.Form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import com.jilaba.Logic.LogicPurchase;
import com.jilaba.common.Common;
import com.jilaba.fileresource.Register;
import com.jilaba.fileresource.Server;
import com.jilaba.model.MenuHeadingPojo;

public class MDIForm extends JFrame implements ActionListener,MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	Common common=new Common();
	private JButton btnHide;
	JMenu menuTransaction,menuExit;
	private JMenuItem mnuItemPurchase;
	private JPanel mainJPanel,topInFoPanel,menuPanel,contenPanel,buttonPanel,bottomInfoPanel;
	private JLabel lblCompanyName,lblOperatorName,lblLocalIpAddress,lblServerName,lblAppName,lblFinancialYear,lblVersion,lblTime;
	private static JButton btnAdd,btnSave,btnView,btnEdit,btnClear,btnClose;
	
	public static JButton getBtnAdd() {
		return btnAdd;
	}
	public MDIForm() {
		try {
			
			getContentPane().setPreferredSize(new Dimension(common.getWidth(),common.getHeight()));
			setUndecorated(true);
			pack();
			
			
			componentCreation();
			clear();
			new Timer(500, this).start();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.exit(1);
		}
	}
	private void clear() throws Exception {
		LogicPurchase logicPurchase=new LogicPurchase();
		MenuHeadingPojo menuHeadingPojo=logicPurchase.getMenuHeading(4);
		
		lblOperatorName.setText(menuHeadingPojo.getOperatorName());
		lblFinancialYear.setText(menuHeadingPojo.getFinancialYear());
		lblVersion.setText(menuHeadingPojo.getVersion());
		lblAppName.setText(menuHeadingPojo.getModulename());
		lblLocalIpAddress.setText(Inet4Address.getLocalHost().getHostAddress());
		lblServerName.setText(Server.getServerName());
		
	}
	private void componentCreation() throws Exception{
		
		
		
		
		JMenuBar mnuBillingBar;
		
		
		
		mainJPanel=new JPanel(null);
		mainJPanel.setBounds(0, 0, common.getWidth(), common.getHeight());
//		mainJPanel.setBackground(Color.decode("#54657"));
		mainJPanel.setBackground(Color.decode("#E8E8E8"));
//		mainJPanel.setBackground(Color.WHITE);
		mainJPanel.setVisible(true);
		getContentPane().add(mainJPanel);
		
		topInFoPanel=new JPanel(null);
		topInFoPanel.setBounds(0, 0, common.getWidth(), 25);
//		topInFoPanel.setBackground(Color.decode("#b2ebf2"));
		topInFoPanel.setBackground(Color.decode("#00acc1"));
		topInFoPanel.setVisible(true);
		mainJPanel.add(topInFoPanel);
		
		menuPanel=new JPanel(null);
		menuPanel.setBounds(0, topInFoPanel.getHeight(), common.getWidth(), 80);
//		menuPanel.setBorder(new LineBorder(Color.decode("#00acc1"), 2));
		menuPanel.setBackground(Color.decode("#E8E8E8"));
//		menuPanel.setBackground(Color.decode("#4dd0e1"));
		menuPanel.setBorder(new LineBorder(Color.decode("#00acc1"), 2));
		menuPanel.setVisible(true);
		mainJPanel.add(menuPanel);	
		
		bottomInfoPanel=new JPanel(null);
		bottomInfoPanel.setBounds(0, common.getHeight()-25, common.getWidth(),25);
//		bottomInfoPanel.setBackground(Color.decode("#b2ebf2"));
		bottomInfoPanel.setBackground(Color.decode("#E8E8E8"));
		bottomInfoPanel.setBorder(new LineBorder(Color.decode("#00acc1"), 3));
		bottomInfoPanel.setVisible(true);
		mainJPanel.add(bottomInfoPanel);
		
		buttonPanel=new JPanel(null);
		buttonPanel.setBounds(0, bottomInfoPanel.getY()-48, common.getWidth(), 45);
//		buttonPanel.setBackground(Color.decode("#00acc1"));
		buttonPanel.setBackground(Color.decode("#00acc1"));
		buttonPanel.setVisible(false);
		mainJPanel.add(buttonPanel);
		
		contenPanel=new JPanel(null);
		contenPanel.setBounds(-5,menuPanel.getHeight()+topInFoPanel.getHeight()-5  , common.getWidth()+5,common.getHeight()-(topInFoPanel.getHeight()+bottomInfoPanel.getHeight()+menuPanel.getHeight()+buttonPanel.getHeight())+5);
//		contenPanel.setBackground(Color.RED);
		contenPanel.setBackground(Color.decode("#E8E8E8"));
		contenPanel.setBorder(new LineBorder(Color.decode("#00acc1"), 2));
		contenPanel.setVisible(true);
		mainJPanel.add(contenPanel);
		
		
		btnHide=new JButton("");
		btnHide.setBounds(common.getWidth()-10, 0, 10, 25);
		btnHide.setBackground(Color.DARK_GRAY);
		btnHide.setVerifyInputWhenFocusTarget(false);
		btnHide.addActionListener(this);
		btnHide.setVisible(true);
		bottomInfoPanel.add(btnHide);
		
		
		btnAdd=new JButton("ADD");
		btnAdd.setBounds(common.getWidth()*20/100, 10, 80, 30);
//		btnAdd.setBorder(new LineBorder(Color.BLUE, 1));
		btnAdd.setVerifyInputWhenFocusTarget(false);
		btnAdd.setMnemonic(KeyEvent.VK_A);
		btnAdd.addActionListener(this);
		btnAdd.setVisible(true);
		buttonPanel.add(btnAdd);
		
		btnSave=new JButton("SAVE");
		btnSave.setBounds(btnAdd.getX()+140, btnAdd.getY(), btnAdd.getWidth(), btnAdd.getHeight());
		btnSave.setMnemonic(KeyEvent.VK_S);
		btnSave.setVisible(true);
		buttonPanel.add(btnSave);
		
		btnView=new JButton("VIEW");
		btnView.setBounds(btnSave.getX()+140, btnSave.getY(), btnSave.getWidth(), btnSave.getHeight());
		btnView.setVerifyInputWhenFocusTarget(false);
		btnView.setMnemonic(KeyEvent.VK_V);
		btnView.setVisible(true);
		buttonPanel.add(btnView);
		
		
		btnEdit=new JButton("EDIT");
		btnEdit.setBounds(btnView.getX()+140, btnView.getY(), btnView.getWidth(), btnView.getHeight());
		btnEdit.setMnemonic(KeyEvent.VK_E);
		btnEdit.setVisible(true);
		buttonPanel.add(btnEdit);
		
		btnClear=new JButton("CLEAR");
		btnClear.setBounds(btnEdit.getX()+140, btnEdit.getY(), btnEdit.getWidth(), btnEdit.getHeight());
		btnClear.setVerifyInputWhenFocusTarget(false);
		btnClear.setMnemonic(KeyEvent.VK_C);
		btnClear.addActionListener(this);
		btnClear.setVisible(true);
		buttonPanel.add(btnClear);
		
		btnClose=new JButton("CLOSE");
		btnClose.setBounds(btnClear.getX()+140, btnClear.getY(), btnEdit.getWidth(), btnEdit.getHeight());
		btnClose.setVerifyInputWhenFocusTarget(false);
		btnClose.setMnemonic(KeyEvent.VK_L);
		btnClose.addActionListener(this);
		btnClose.setVisible(true);
		buttonPanel.add(btnClose);
		
		mnuBillingBar =new JMenuBar();
		mnuBillingBar.setBounds(0, 2, common.getWidth()/2, menuPanel.getHeight()-4);
		mnuBillingBar.setBackground(Color.decode("#E8E8E8"));
//		mnuBillingBar.setBorder(new LineBorder(Color.decode("#00acc1"), 2));
		mnuBillingBar.setVisible(true);
		menuPanel.add(mnuBillingBar);
		
		menuTransaction=new JMenu("Transaction");
//		menuTransaction.setIcon(null);
		menuTransaction.setAlignmentY(LEFT_ALIGNMENT);
		menuTransaction.setMnemonic(KeyEvent.VK_T);
		menuTransaction.setVisible(true);
		mnuBillingBar.add(menuTransaction);
		
		mnuItemPurchase=new JMenuItem("Purchase");
		mnuItemPurchase.addActionListener(this);
		mnuItemPurchase.setMnemonic(KeyEvent.VK_P);
		mnuItemPurchase.setVisible(true);
		menuTransaction.add(mnuItemPurchase);
		
		
		menuExit=new JMenu("Exit");
		menuExit.addMouseListener(this);
		menuExit.setAlignmentY(LEFT_ALIGNMENT);
		menuExit.setMnemonic(KeyEvent.VK_X);
		menuExit.setVisible(true);
		mnuBillingBar.add(menuExit);
		
		lblCompanyName=new JLabel(Register.getCompanyName());
		lblCompanyName.setBounds(common.getWidth()*70/100, 10, common.getWidth(), Common.lblHeight);
		lblCompanyName.setFont(new Font("TimesnewRoman",Font.BOLD, 17));
		lblCompanyName.setVisible(true);
		menuPanel.add(lblCompanyName);
		
		
		lblOperatorName=new JLabel("");
		lblOperatorName.setBounds(common.getWidth()*1/100,topInFoPanel.getHeight()*2/100, 200, Common.lblHeight);
		lblOperatorName.setFont(new Font("TimesnewRoman", Font.BOLD, 12));
		lblOperatorName.setForeground(Color.white);
		lblOperatorName.setVisible(true);
		topInFoPanel.add(lblOperatorName);
		
		lblFinancialYear=new JLabel("");
		lblFinancialYear.setBounds(common.getWidth()*17/100, lblOperatorName.getY(), lblOperatorName.getWidth(), lblOperatorName.getHeight());
		lblFinancialYear.setFont(lblOperatorName.getFont());
		lblFinancialYear.setForeground(lblOperatorName.getForeground());
		lblFinancialYear.setVisible(true);
		topInFoPanel.add(lblFinancialYear);
		
		lblLocalIpAddress=new JLabel("");
		lblLocalIpAddress.setBounds(common.getWidth()*30/100, lblOperatorName.getY(), lblOperatorName.getWidth(), lblOperatorName.getHeight());
		lblLocalIpAddress.setFont(lblOperatorName.getFont());
		lblLocalIpAddress.setForeground(lblOperatorName.getForeground());
		lblLocalIpAddress.setVisible(true);
		topInFoPanel.add(lblLocalIpAddress);
		
		lblAppName=new JLabel("");
		lblAppName.setBounds(common.getWidth()*45/100, lblOperatorName.getY(), lblOperatorName.getWidth(), lblOperatorName.getHeight());
		lblAppName.setFont(new Font("TimesnewRoman", Font.BOLD, 17));
		lblAppName.setForeground(lblOperatorName.getForeground());
		lblAppName.setVisible(true);
		topInFoPanel.add(lblAppName);
		
		lblVersion=new JLabel("");
		lblVersion.setBounds(common.getWidth()*58/100, lblOperatorName.getY(), lblOperatorName.getWidth(), lblOperatorName.getHeight());
		lblVersion.setFont(lblOperatorName.getFont());
		lblVersion.setForeground(lblOperatorName.getForeground());
		lblVersion.setVisible(true);
		topInFoPanel.add(lblVersion);
		
		lblServerName=new JLabel("");
		lblServerName.setBounds(common.getWidth()*71/100, lblOperatorName.getY(), lblOperatorName.getWidth(), lblOperatorName.getHeight());
		lblServerName.setFont(lblOperatorName.getFont());
		lblServerName.setForeground(lblOperatorName.getForeground());
		lblServerName.setVisible(true);
		topInFoPanel.add(lblServerName);
		
		
		lblTime=new JLabel("");
		lblTime.setBounds(common.getWidth()*89/100, lblOperatorName.getY(), lblOperatorName.getWidth(), lblOperatorName.getHeight());
		lblTime.setFont(lblOperatorName.getFont());
		lblTime.setForeground(lblOperatorName.getForeground());
		lblTime.setVisible(true);
		topInFoPanel.add(lblTime);
		
		
		Common.internalHeight=common.getHeight()-(topInFoPanel.getHeight()+bottomInfoPanel.getHeight()+menuPanel.getHeight()+buttonPanel.getHeight());
		Common.internalWidth=contenPanel.getWidth();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e){
		try {
			SimpleDateFormat spD=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a");
			lblTime.setText(spD.format(new Date()));
			
//			if(e.getSource()!="") {
//				switch (e.getActionCommand().toLowerCase()) {
//				case "": 
//					setState(ICONIFIED);
//					break;
//				case "purchase":
//					FrmPurchase frmPurchase = null;
//					frmPurchase = new FrmPurchase();
//					contenPanel.setVisible(true);
//					contenPanel.removeAll();
//					contenPanel.add(frmPurchase);
//					frmPurchase.setVisible(true);
//					
//					common.setFormAction(frmPurchase);
//					btnAdd.requestFocus();
//					
//					break;
//				case "add":
//					common.getFormAction().add();
//					break;
//				case "clear" :
//					common.getFormAction().clear();
//					btnAdd.requestFocus();
//					break;
//				case "close":
//					contenPanel.removeAll();
//					contenPanel.setVisible(false);
//					break;
//				}
//			}
						
			if(e.getSource()==btnHide) {
				setState(ICONIFIED);
			}else if(e.getSource()==mnuItemPurchase) {
				FrmPurchase frmPurchase = null;
				frmPurchase = new FrmPurchase();
				contenPanel.setVisible(true);
				contenPanel.removeAll();
				contenPanel.add(frmPurchase);
				frmPurchase.setVisible(true);
				
				common.setFormAction(frmPurchase);
				
				buttonPanel.setVisible(true);
				btnAdd.requestFocus();
				
			}else if(e.getSource()==btnAdd) {
				common.getFormAction().add();
			}else if(e.getSource()==btnClose) {
				contenPanel.removeAll();
				contenPanel.setVisible(false);
				buttonPanel.setVisible(false);

			}else if(e.getSource()==btnClear) {
				common.getFormAction().clear();
				btnAdd.requestFocus();
	
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource()==menuExit) {
			System.exit(0);
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
