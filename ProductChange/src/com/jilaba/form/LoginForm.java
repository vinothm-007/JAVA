package com.jilaba.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RectangularShape;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.jilaba.common.Common;
import com.jilaba.control.JilabaButton;
import com.jilaba.control.JilabaComboBox;
import com.jilaba.control.JilabaPasswordField;

public class LoginForm extends JFrame implements KeyListener,ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5057245883351930489L;

	private JPanel panelLogin;
	private JilabaComboBox<String> cmbUserName;
	private JilabaPasswordField password;
	private JilabaButton btnLogin,btnExit;
	
	public LoginForm() {
		
		getContentPane().setPreferredSize(new Dimension(450,400));
//		getContentPane().setPreferredSize(new Dimension(Common.getScreenWidth()/2,Common.getScreenHeight()/2));
		setUndecorated(true);
		pack();
		
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Login");
		
		
		componentCreation();
		componentListener();
		initalize();
	}

	private void initalize() {
		cmbUserName.requestFocus();
		cmbUserName.removeAllItems();
		cmbUserName.addItem("Administrator");
	}

	private void componentListener() {
		
		password.setInputVerifier(new InputVerifier() {
			
			@Override
			public boolean verify(JComponent input) {
				
				if("".equalsIgnoreCase(String.valueOf(password.getPassword()))) {
					JOptionPane.showMessageDialog(null, "Password Is Empty...!");
					return false;
				}
				return true;
			}
		});
		
		
	}

	private void componentCreation() {
		
		int compWidth=170,compHeight=30;
		JLabel lblHeading,lblUserName,lblPassword;
		JPanel panelDesign;
		
		panelLogin=new JPanel(null);
		panelLogin.setBounds(0, 30, 450, 400);
		panelLogin.setBackground(Color.cyan);
		panelLogin.setVisible(true);
		getContentPane().add(panelLogin);
		
		
		lblHeading=new JLabel("PRODUCT CHANGE LOGIN");
		lblHeading.setBounds(90, 20, compWidth+80,compHeight);
		lblHeading.setFont(Common.getHeadingFont());
		lblHeading.setVisible(true);
		panelLogin.add(lblHeading);
		
		
		lblUserName=new JLabel("UserName : ");
		lblUserName.setBounds(lblHeading.getX()-10, lblHeading.getY()+150, compWidth, compHeight);
		lblUserName.setFont(Common.getLableFont());
		lblUserName.setVisible(true);
		panelLogin.add(lblUserName);
		
		
		lblPassword=new JLabel("PassWord : ");
		lblPassword.setBounds(lblUserName.getX(), lblUserName.getY()+70, compWidth, compHeight);
		lblPassword.setFont(Common.getLableFont());
		lblPassword.setVisible(true);
		panelLogin.add(lblPassword);
		
		
		cmbUserName=new JilabaComboBox<String>();
		cmbUserName.setBounds(lblUserName.getX()+110, lblUserName.getY(), compWidth, compHeight);
		cmbUserName.addKeyListener(this);
		cmbUserName.setVisible(true);
		panelLogin.add(cmbUserName);
		
		
		password=new JilabaPasswordField();
		password.setBounds(lblPassword.getX()+110, lblPassword.getY(), compWidth, compHeight);
		password.addKeyListener(this);
		password.setVisible(true);
		panelLogin.add(password);
		
		
		btnLogin=new JilabaButton("Login");
		btnLogin.setBounds(lblPassword.getX(), lblPassword.getY()+80, compWidth-60, compHeight);
		btnLogin.setMnemonic(KeyEvent.VK_L);
		btnLogin.addActionListener(this);
		btnLogin.setVisible(true);
		panelLogin.add(btnLogin);
		
		
		btnExit=new JilabaButton("Exit");
		btnExit.setBounds(btnLogin.getX()+170, btnLogin.getY(), compWidth-60, compHeight);
		btnExit.setMnemonic(KeyEvent.VK_E);
		btnExit.addActionListener(this);
		btnExit.setVerifyInputWhenFocusTarget(false);
		btnExit.setVisible(true);
		panelLogin.add(btnExit);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			
			if(e.getSource()==cmbUserName) {
				password.requestFocus();
			}else if(e.getSource()==password) {
				btnLogin.requestFocus();
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Exit": 
			System.exit(1);
		case "Login":
			
			if("".equalsIgnoreCase(String.valueOf(password.getPassword()))) {
				JOptionPane.showMessageDialog(null, "Password Is Empty...!");
				password.requestFocus();
				return;
			}
			
			if(!"jil".equals(String.valueOf(password.getPassword()))) {
				JOptionPane.showMessageDialog(null, "Invalid Password...!");
				password.setText("");
				password.requestFocus();
				return;
			}
			
			this.dispose();
			
			MdiForm mdiForm=new MdiForm();
			mdiForm.setVisible(true);
			
		}
		
	}
	
	

}
