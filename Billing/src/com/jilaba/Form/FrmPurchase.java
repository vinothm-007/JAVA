package com.jilaba.Form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.InputVerifier;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.jilaba.Logic.LogicPurchase;
import com.jilaba.common.Common;
import com.jilaba.common.FormAction;
import com.jilaba.control.JTextFieldEnum.TextInputType;
import com.jilaba.control.JilabaButton;
import com.jilaba.control.JilabaColumn;
import com.jilaba.control.JilabaComboBox;
import com.jilaba.control.JilabaTable;
import com.jilaba.control.JilabaTextField;
import com.jilaba.control.ListItem;
import com.jilaba.model.PurchasePojo;

public class FrmPurchase extends JInternalFrame implements KeyListener,ActionListener,FormAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel purchasePanel,infoPanel,totalAmountPanel;
	private JLabel lblLastBillno;
	public static JLabel lblSalesPersonName;
	private JilabaTextField txtEstNo,txtDescription,txtPurity,txtPiece,txtGrsWt,txtLessWt,txtNetwt,txtStudAmount,txtPureWWt,txtDustWt,txtwastagePer,txtWastage,
	txtCalWt,txtRate,txtRateId,txtAmount,txtSalesPerson;
	private JilabaComboBox<String> cmbPurchaseType,cmbMake,cmbNature,cmbMetal,cmbCategory,cmbPurCalType,cmbProduct,cmbSubProduct,cmbUnit,cmbTagType;
	private JilabaButton btnAdd,btnClear;
	private JilabaTable tblPurchaseDetails,tblStoneDetails;
	private LogicPurchase logicPurchase=new LogicPurchase();
	private Common common=new Common();
	
	public FrmPurchase() throws Exception{
		try {
			((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).setNorthPane(null);
			getContentPane().setPreferredSize(new Dimension(Common.internalWidth,Common.internalHeight));
			pack();
			
			componentCreation();
			componentListener();
			initalize();
			componentDisable();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	private void initalize() throws Exception {
		txtEstNo.setText("");
		loadTranno("PURCHASENO");
		loadPurchaseType();
		loadNature();
		loadPurchaseMakingType();
		loadMetal();
		loadPurchaseCalType();
		loadCategory(0,"");
		loadTagType();
		loadUnit("");
		txtPurity.setText(common.getAmtFormat().format(0));
		txtGrsWt.setText(common.getWtFormat().format(0));
		txtNetwt.setText(common.getWtFormat().format(0));
		txtLessWt.setText(common.getWtFormat().format(0));
		txtDustWt.setText(common.getWtFormat().format(0));
		txtPureWWt.setText(common.getWtFormat().format(0));
		txtWastage.setText(common.getWtFormat().format(0));
		txtCalWt.setText(common.getWtFormat().format(0));
		txtStudAmount.setText(common.getAmtFormat().format(0));
		txtRate.setText(common.getAmtFormat().format(0));
		txtRateId.setText(common.getAmtFormat().format(0));
		txtAmount.setText(common.getAmtFormat().format(0));
		txtPiece.setText(common.getPcsFormat().format(0));
		txtSalesPerson.setText("");
		txtwastagePer.setText(common.getPcsFormat().format(0));
		txtEstNo.requestFocus();
		//		componentDisable();
	}
	private void loadTranno(String controlCode) throws Exception {
		lblLastBillno.setText("Last Billno : ");
		lblLastBillno.setText(lblLastBillno.getText()+logicPurchase.getTranno(controlCode));
	}
	private void loadUnit(String unitCode) throws Exception {
		List<PurchasePojo> lstPurchasePojos=new LinkedList<PurchasePojo>();
		lstPurchasePojos=logicPurchase.getUnit(unitCode);
		
		cmbUnit.removeAllItems();
		for (PurchasePojo purchasePojo : lstPurchasePojos) {
			cmbUnit.addListItem(new ListItem(purchasePojo.getUnitName(), purchasePojo.getUnitCode()));
		}
		
	}
	private void loadTagType() throws Exception {
		List<PurchasePojo> lstPurchasePojos=new LinkedList<PurchasePojo>();
		lstPurchasePojos=logicPurchase.getTagType();
		
		cmbTagType.removeAllItems();
		for (PurchasePojo purchasePojo : lstPurchasePojos) {
			cmbTagType.addListItem(new ListItem(purchasePojo.getTagTypeName(), purchasePojo.getTagTypeCode()));
		}
		
	}
	private void loadCategory(int natid,String MetalCOde) throws Exception {
		
		List<PurchasePojo> lstPurchasePojos=new LinkedList<PurchasePojo>();
		lstPurchasePojos=logicPurchase.getCategory(natid,MetalCOde);
		
		cmbCategory.removeAllItems();
		for (PurchasePojo purchasePojo : lstPurchasePojos) {
			cmbCategory.addListItem(new ListItem(purchasePojo.getCategoryName(), purchasePojo.getCatCode()));
		}
	}
	private void loadPurchaseMakingType() throws Exception {
		
		List<PurchasePojo> lstPurchasePojos=new LinkedList<PurchasePojo>();
		
		lstPurchasePojos=logicPurchase.getProductType("Fn_PurchaseMakingType()");
		
		cmbMake.removeAllItems();
		for (PurchasePojo purchasePojo : lstPurchasePojos) {
			cmbMake.addListItem(new ListItem(purchasePojo.getPurchaseType(), purchasePojo.getPurchaseTypeCode()));
		}
		
	}
	private void loadPurchaseType() throws Exception {
		
		List<PurchasePojo> lstPurchaseType=new LinkedList<PurchasePojo>();
		lstPurchaseType=logicPurchase.getProductType("Fn_PurchaseTypeTable()");
		cmbPurchaseType.removeAllItems();
		for (PurchasePojo purchasePojo : lstPurchaseType) {
			cmbPurchaseType.addListItem(new ListItem(purchasePojo.getPurchaseType(), purchasePojo.getPurchaseTypeCode()));
		}
	}
	private void loadPurchaseCalType() throws Exception{
		
		List<PurchasePojo> lstpPurchasePojos=new LinkedList<PurchasePojo>();
		lstpPurchasePojos=logicPurchase.getProductType("Fn_PurCalcType()");
		
		cmbPurCalType.removeAllItems();
		for (PurchasePojo purchasePojo : lstpPurchasePojos) {
			cmbPurCalType.addListItem(new ListItem(purchasePojo.getPurchaseType(), purchasePojo.getPurchaseTypeCode()));
		}
		
	}
	private void loadNature() throws Exception{
		
		List<PurchasePojo> lstPurchasePojos=new LinkedList<PurchasePojo>();
		lstPurchasePojos=logicPurchase.getNature();
		
		cmbNature.removeAllItems();
		for (PurchasePojo purchasePojo : lstPurchasePojos) {
			cmbNature.addListItem(new ListItem(purchasePojo.getNatureName(), purchasePojo.getNatId()));
		}
	}
	private void loadMetal() throws Exception{
		
		List<PurchasePojo> lstPurchasePojos=new LinkedList<PurchasePojo>();
		lstPurchasePojos=logicPurchase.getMetal();
		
		cmbMetal.removeAllItems();
		for (PurchasePojo purchasePojo : lstPurchasePojos) {
			cmbMetal.addListItem(new ListItem(purchasePojo.getMetalName(),purchasePojo.getMetalCode()));
		}
	}
	private void loadProduct() throws Exception{
		
		PurchasePojo purchasePojo=new PurchasePojo();
		purchasePojo=logicPurchase.getProduct(Integer.parseInt(cmbCategory.getSelectedItemValue().toString()));
		
		txtDescription.setText(purchasePojo.getProductName());
		cmbProduct.removeAllItems();
		cmbProduct.addListItem(new ListItem(purchasePojo.getProductName(), purchasePojo.getProductCode()));
		
		txtPurity.setText(common.getAmtFormat().format(purchasePojo.getPurity()));
		txtRate.setText(common.getAmtFormat().format(purchasePojo.getRate()));
		
		loadUnit(purchasePojo.getUnitCode());
	}

	private void componentListener() {
		
		txtGrsWt.setInputVerifier(new InputVerifier() {
			
			@Override
			public boolean verify(JComponent input) {
				if(Double.parseDouble(txtGrsWt.getText())<=0) {
					JOptionPane.showMessageDialog(null, "GrossWt Is Empty...!");
					return false;
				}
				return true;
			}
		});
		
		txtRate.setInputVerifier(new InputVerifier() {
			
			@Override
			public boolean verify(JComponent input) {
				if(Double.parseDouble(txtRate.getText())<=0) {
					JOptionPane.showMessageDialog(null, "Rate Is Empty...!");
					return false;
				}
				return true;
			}
		});
		
		txtSalesPerson.setInputVerifier(new InputVerifier() {
			
			@Override
			public boolean verify(JComponent input) {
				if(txtSalesPerson.getText().equalsIgnoreCase("") || Integer.parseInt(txtSalesPerson.getText())<=0) {
					JOptionPane.showMessageDialog(null, "SalesPerson is Empty...!");
					return false;
				}
				return true;
			}
		});
	
		
	}
	private void componentCreation() {
		
		
		JLabel lblEstno,lblPurchaseType,lblSalesPerson,lblMake,lblNature,lblMetal,lblCategory,lblPurCalType,lblDescription,lblProduct,lblSubProduct,lblUnit,lblTagType,
		lblPurity,lblPiece,lblGrsWt,lblLessWt,lblNetwt,lblStudAmount,lblPureWt,lblDustWt,lblWastageper,lblwastage,lblCalWt,lblRate,lblRateId,
		lblAmount,lblStoneHeading,lbltableInformation,lblinfoTotalPcs,lblinfoTotalWeight,lblinfoSgstAmt,lblinfoCgstAmt,lblinfoAmount,
		lblinfoRoundof,lblinfoTotal,lblinfoPcsValue,lblinfoWeightValue,lblinfoAmtValue,lblinfoCgstValue,lblinfoSgstValue,lblinfoRoundValue,lblinfoTotAmtValue;
		
		
		
		JScrollPane scpPurchaseDetails,scpStoneDetails;
		JCheckBox chkDiscount,chkRateChange,chkCustomerDetail,chkStuddedView;
		
		purchasePanel=new JPanel(null);
		purchasePanel.setBounds(0, 0, Common.internalWidth, Common.internalHeight);
		purchasePanel.setBackground(Color.decode("#E8E8E8"));
		purchasePanel.setBorder(new LineBorder(Color.decode("#00acc1"), 2));
		purchasePanel.setVisible(true);
		getContentPane().add(purchasePanel);
		
		lblEstno=new JLabel("Est No");
		lblEstno.setBounds(Common.internalWidth*1/100, 5, Common.lblWidth, Common.lblHeight);
		lblEstno.setFont(Common.normalFont);
		lblEstno.setVisible(true);
		purchasePanel.add(lblEstno);
		
		txtEstNo=new JilabaTextField();
		txtEstNo.setBounds(lblEstno.getX()+100, lblEstno.getY(), Common.internalWidth*5/100, Common.lblHeight);
		txtEstNo.addKeyListener(this);
		txtEstNo.setTextType(TextInputType.NUMBER);
		txtEstNo.setVisible(true);
		purchasePanel.add(txtEstNo);
		
		lblPurchaseType=new JLabel("Purchase Type");
		lblPurchaseType.setBounds(txtEstNo.getX()+210, txtEstNo.getY(),Common.lblWidth+100, Common.lblHeight);
		lblPurchaseType.setFont(Common.normalFont);
		lblPurchaseType.setVisible(true);
		purchasePanel.add(lblPurchaseType);
		
		
		cmbPurchaseType=new JilabaComboBox<String>();
		cmbPurchaseType.setBounds(lblPurchaseType.getX()+lblPurchaseType.getWidth()+10, lblPurchaseType.getY(), Common.internalWidth*15/100, Common.lblHeight);
		cmbPurchaseType.addKeyListener(this);
		cmbPurchaseType.setVisible(true);
		purchasePanel.add(cmbPurchaseType);
		
		lblMake=new JLabel("Make");
		lblMake.setBounds(cmbPurchaseType.getX()+cmbPurchaseType.getWidth()+20, cmbPurchaseType.getY(), Common.lblWidth, Common.lblHeight);
		lblMake.setFont(Common.normalFont);
		lblMake.setVisible(true);
		purchasePanel.add(lblMake);
		
		
		cmbMake=new JilabaComboBox<String>();
		cmbMake.setBounds(lblMake.getX()+120, lblMake.getY(), Common.lblWidth+130, Common.lblHeight);
		cmbMake.addKeyListener(this);
		cmbMake.setVisible(true);
		purchasePanel.add(cmbMake);
		
		
		lblNature=new JLabel("Nature");
		lblNature.setBounds(lblEstno.getX(), lblEstno.getY()+38, Common.lblWidth, Common.lblHeight);
		lblNature.setFont(Common.normalFont);
		lblNature.setVisible(true);
		purchasePanel.add(lblNature);
		
		cmbNature=new JilabaComboBox<String>();
		cmbNature.setBounds(txtEstNo.getX(), lblNature.getY(), cmbPurchaseType.getWidth(), Common.lblHeight);
		cmbNature.addKeyListener(this);
		cmbNature.setVisible(true);
		purchasePanel.add(cmbNature);
		
		lblMetal=new JLabel("Metal");
		lblMetal.setBounds(lblPurchaseType.getX(), cmbNature.getY(), Common.lblWidth, Common.lblHeight);
		lblMetal.setFont(Common.normalFont);
		lblMetal.setVisible(true);
		purchasePanel.add(lblMetal);
		
		
		cmbMetal=new JilabaComboBox<String>();
		cmbMetal.setBounds(cmbPurchaseType.getX(), lblMetal.getY(), cmbPurchaseType.getWidth(), Common.lblHeight);
		cmbMetal.addKeyListener(this);
		cmbMetal.setVisible(true);
		purchasePanel.add(cmbMetal);
		
		lblCategory=new JLabel("Category");
		lblCategory.setBounds(lblMake.getX(), cmbMetal.getY(), Common.lblWidth, Common.lblHeight);
		lblCategory.setFont(Common.normalFont);
		lblCategory.setVisible(true);
		purchasePanel.add(lblCategory);
		
		cmbCategory=new JilabaComboBox<String>();
		cmbCategory.setBounds(cmbMake.getX(), lblCategory.getY(), cmbMake.getWidth(), Common.lblHeight);
		cmbCategory.addKeyListener(this);
		cmbCategory.setVisible(true);
		purchasePanel.add(cmbCategory);
		
		lblPurCalType=new JLabel("PurCalType");
		lblPurCalType.setBounds(cmbCategory.getX()+cmbCategory.getWidth()+10, cmbCategory.getY(), Common.lblWidth, Common.lblHeight);
		lblPurCalType.setFont(Common.normalFont);
		lblPurCalType.setVisible(true);
		purchasePanel.add(lblPurCalType);
		
		cmbPurCalType=new JilabaComboBox<String>();
		cmbPurCalType.setBounds(lblPurCalType.getX()+lblPurCalType.getWidth()+10, lblPurCalType.getY(), cmbPurchaseType.getWidth(), Common.lblHeight);
		cmbPurCalType.addKeyListener(this);
		cmbPurCalType.setVisible(true);
		purchasePanel.add(cmbPurCalType);
		
		lblDescription=new JLabel("Description");
		lblDescription.setBounds(lblNature.getX(), lblNature.getY()+38, Common.lblWidth, Common.lblHeight);
		lblDescription.setFont(Common.normalFont);
		lblDescription.setVisible(true);
		purchasePanel.add(lblDescription);
		
		txtDescription=new JilabaTextField();
		txtDescription.setBounds(cmbNature.getX(), lblDescription.getY(), Common.internalWidth*20/100, Common.lblHeight);
		txtDescription.addKeyListener(this);
		txtDescription.setTextType(TextInputType.ALPHANUMBER);
		txtDescription.setVisible(true);
		purchasePanel.add(txtDescription);
		
		
		lblProduct=new JLabel("Product");
		lblProduct.setBounds(lblDescription.getX(), lblDescription.getY()+38, Common.lblWidth, Common.lblHeight);
		lblProduct.setFont(Common.normalFont);
		lblProduct.setVisible(true);
		purchasePanel.add(lblProduct);
		
		cmbProduct=new JilabaComboBox<String>();
		cmbProduct.setBounds(txtDescription.getX(), lblProduct.getY(), txtDescription.getWidth(),Common.lblHeight);
		cmbProduct.addKeyListener(this);
		cmbProduct.setVisible(true);
		purchasePanel.add(cmbProduct);
		
		lblSubProduct=new JLabel("SubProduct");
		lblSubProduct.setBounds(lblProduct.getX(), lblProduct.getY()+38, Common.lblWidth, Common.lblHeight);
		lblSubProduct.setFont(Common.normalFont);
		lblSubProduct.setVisible(true);
		purchasePanel.add(lblSubProduct);
		
		cmbSubProduct=new JilabaComboBox<String>();
		cmbSubProduct.setBounds(txtDescription.getX(), lblSubProduct.getY(), txtDescription.getWidth(), Common.lblHeight);
		cmbSubProduct.addKeyListener(this);
		cmbSubProduct.setVisible(true);
		purchasePanel.add(cmbSubProduct);
//																																																																																																																																																																																																																																																																																																																											)
		lblUnit=new JLabel("Unit");
		lblUnit.setBounds(lblSubProduct.getX(), lblSubProduct.getY()+38, Common.lblWidth, Common.lblHeight);
		lblUnit.setFont(Common.normalFont);
		lblUnit.setVisible(true);
		purchasePanel.add(lblUnit);
		
		cmbUnit=new JilabaComboBox<String>();
		cmbUnit.setBounds(cmbSubProduct.getX(), lblUnit.getY(), cmbSubProduct.getWidth(), Common.lblHeight);
		cmbUnit.addKeyListener(this);
		cmbUnit.setVisible(true);
		purchasePanel.add(cmbUnit);
		
		lblTagType=new JLabel("TagType");
		lblTagType.setBounds(lblUnit.getX(), lblUnit.getY()+38,Common.lblWidth, Common.lblHeight);
		lblTagType.setFont(Common.normalFont);
		lblTagType.setVisible(true);
		purchasePanel.add(lblTagType);
		
		cmbTagType=new JilabaComboBox<String>();
		cmbTagType.setBounds(cmbUnit.getX(), lblTagType.getY(), cmbUnit.getWidth(), Common.lblHeight);
		cmbTagType.addKeyListener(this);
		cmbTagType.setVisible(true);
		purchasePanel.add(cmbTagType);
		
		
		lblPurity=new JLabel("Purity");
		lblPurity.setBounds(lblTagType.getX(), lblTagType.getY()+32, Common.lblWidth, Common.lblHeight);
		lblPurity.setFont(Common.normalFont);
		lblPurity.setVisible(true);
		purchasePanel.add(lblPurity);
		
		txtPurity=new JilabaTextField();
		txtPurity.setBounds(cmbTagType.getX(), lblPurity.getY(), Common.internalWidth*7/100, Common.lblHeight);
		txtPurity.addKeyListener(this);
		txtPurity.setHorizontalAlignment(JTextField.RIGHT);
		txtPurity.setTextType(TextInputType.NUMERIC);
		txtPurity.setVisible(true);
		purchasePanel.add(txtPurity);
		
		lblPiece=new JLabel("Piece");
		lblPiece.setBounds(txtPurity.getX()+txtPurity.getWidth()+10, lblPurity.getY(), Common.lblWidth, Common.lblHeight);
		lblPiece.setFont(Common.normalFont);
		lblPiece.setVisible(true);
		purchasePanel.add(lblPiece);
		
		txtPiece=new JilabaTextField();
		txtPiece.setBounds(lblPiece.getX()+lblPiece.getWidth()+5, lblPiece.getY(), txtPurity.getWidth(), Common.lblHeight);
		txtPiece.addKeyListener(this);
		txtPiece.setTextType(TextInputType.NUMBER);
		txtPiece.setHorizontalAlignment(JTextField.RIGHT);
		txtPiece.setVisible(true);
		purchasePanel.add(txtPiece);
		
		lblGrsWt=new JLabel("GrossWt");
		lblGrsWt.setBounds(lblPurity.getX(), lblPurity.getY()+32, Common.lblWidth, Common.lblHeight);
		lblGrsWt.setFont(Common.normalFont);
		lblGrsWt.setVisible(true);
		purchasePanel.add(lblGrsWt);
		
		txtGrsWt=new JilabaTextField();
		txtGrsWt.setBounds(txtPurity.getX(),lblGrsWt.getY(), txtPurity.getWidth(), Common.lblHeight);
		txtGrsWt.addKeyListener(this);
		txtGrsWt.setTextType(TextInputType.NUMERIC);
		txtGrsWt.setHorizontalAlignment(JTextField.RIGHT);
		txtGrsWt.setVisible(true);
		purchasePanel.add(txtGrsWt);
		
		lblLessWt=new JLabel("LessWt");
		lblLessWt.setBounds(lblPiece.getX(),lblGrsWt.getY(), Common.lblWidth, Common.lblHeight);
		lblLessWt.setFont(Common.normalFont);
		lblLessWt.setVisible(true);
		purchasePanel.add(lblLessWt);
		
		txtLessWt=new JilabaTextField();
		txtLessWt.setBounds(txtPiece.getX(), lblGrsWt.getY(), txtPurity.getWidth(), Common.lblHeight);
		txtLessWt.setTextType(TextInputType.NUMERIC);
		txtLessWt.setHorizontalAlignment(JTextField.RIGHT);
		txtLessWt.setVisible(true);
		purchasePanel.add(txtLessWt);
		
		lblNetwt=new JLabel("NetWt");
		lblNetwt.setBounds(lblGrsWt.getX(), lblGrsWt.getY()+32, Common.lblWidth, Common.lblHeight);
		lblNetwt.setFont(Common.normalFont);
		lblNetwt.setVisible(true);
		purchasePanel.add(lblNetwt);
		
		txtNetwt=new JilabaTextField();
		txtNetwt.setBounds(txtGrsWt.getX(), lblNetwt.getY(), txtPurity.getWidth(), Common.lblHeight);
		txtNetwt.setTextType(TextInputType.NUMERIC);
		txtNetwt.setHorizontalAlignment(JTextField.RIGHT);
		txtNetwt.setVisible(true);
		purchasePanel.add(txtNetwt);
		
		
		lblStudAmount=new JLabel("Studded Amt");
		lblStudAmount.setBounds(lblLessWt.getX(), lblNetwt.getY(), Common.lblWidth, Common.lblHeight);
		lblStudAmount.setFont(Common.normalFont);
		lblStudAmount.setVisible(true);
		purchasePanel.add(lblStudAmount);
		
		txtStudAmount=new JilabaTextField();
		txtStudAmount.setBounds(txtLessWt.getX(), lblNetwt.getY(),txtPurity.getWidth(), Common.lblHeight);
		txtStudAmount.setTextType(TextInputType.NUMERIC);
		txtStudAmount.setHorizontalAlignment(JTextField.RIGHT);
		txtStudAmount.setVisible(true);
		purchasePanel.add(txtStudAmount);
		
		
		lblPureWt=new JLabel("PureWt");
		lblPureWt.setBounds(lblNetwt.getX(), lblNetwt.getY()+32, Common.lblWidth, Common.lblHeight);
		lblPureWt.setFont(Common.normalFont);
		lblPureWt.setVisible(true);
		purchasePanel.add(lblPureWt);
		
		txtPureWWt=new JilabaTextField();
		txtPureWWt.setBounds(txtNetwt.getX(), lblPureWt.getY(), txtPurity.getWidth(), Common.lblHeight);
		txtPureWWt.setTextType(TextInputType.NUMERIC);
		txtPureWWt.setHorizontalAlignment(JTextField.RIGHT);
		txtPureWWt.setVisible(true);
		purchasePanel.add(txtPureWWt);
		
		
		lblDustWt=new JLabel("DustWt");
		lblDustWt.setBounds(lblStudAmount.getX(), lblPureWt.getY(),Common.lblWidth, Common.lblHeight);
		lblDustWt.setFont(Common.normalFont);
		lblDustWt.setVisible(true);
		purchasePanel.add(lblDustWt);
		
		txtDustWt=new JilabaTextField();
		txtDustWt.setBounds(txtStudAmount.getX(), lblDustWt.getY(),txtPurity.getWidth(),Common.lblHeight);
		txtDustWt.addKeyListener(this);
		txtDustWt.setTextType(TextInputType.NUMERIC);
		txtDustWt.setHorizontalAlignment(JTextField.RIGHT);
		txtDustWt.setVisible(true);
		purchasePanel.add(txtDustWt);
		
		
		lblWastageper=new JLabel("Wast.Per");
		lblWastageper.setBounds(lblPureWt.getX(), lblPureWt.getY()+32, Common.lblWidth, Common.lblHeight);
		lblWastageper.setFont(Common.normalFont);
		lblWastageper.setVisible(true);
		purchasePanel.add(lblWastageper);
		
		txtwastagePer=new JilabaTextField();
		txtwastagePer.setBounds(txtPureWWt.getX(), lblWastageper.getY(), txtPurity.getWidth(), Common.lblHeight);
		txtwastagePer.addKeyListener(this);
		txtwastagePer.setTextType(TextInputType.NUMERIC);
		txtwastagePer.setHorizontalAlignment(JTextField.RIGHT);
		txtwastagePer.setVisible(true);
		purchasePanel.add(txtwastagePer);
		
		lblwastage=new JLabel("Wastage");
		lblwastage.setBounds(lblDustWt.getX(), lblWastageper.getY(), Common.lblWidth, Common.lblHeight);
		lblwastage.setFont(Common.normalFont);
		lblwastage.setVisible(true);
		purchasePanel.add(lblwastage);
		
		txtWastage=new JilabaTextField();
		txtWastage.setBounds(txtDustWt.getX(), lblwastage.getY(), txtPurity.getWidth(), Common.lblHeight);
		txtWastage.addKeyListener(this);
		txtWastage.setTextType(TextInputType.NUMERIC);
		txtWastage.setHorizontalAlignment(JTextField.RIGHT);
		txtWastage.setVisible(true);
		purchasePanel.add(txtWastage);
		
		
		lblCalWt=new JLabel("Calc Wt");
		lblCalWt.setBounds(lblWastageper.getX(), lblWastageper.getY()+32, Common.lblWidth, Common.lblHeight);
		lblCalWt.setFont(Common.normalFont);
		lblCalWt.setVisible(true);
		purchasePanel.add(lblCalWt);
		
		txtCalWt=new JilabaTextField();
		txtCalWt.setBounds(txtwastagePer.getX(), lblCalWt.getY(), txtPurity.getWidth()*2, Common.lblHeight);
		txtCalWt.addKeyListener(this);
		txtCalWt.setTextType(TextInputType.NUMERIC);
		txtCalWt.setHorizontalAlignment(JTextField.RIGHT);
		txtCalWt.setVisible(true);
		purchasePanel.add(txtCalWt);
		
		lblRate=new JLabel("Rate");
		lblRate.setBounds(lblCalWt.getX(), lblCalWt.getY()+32, Common.lblWidth, Common.lblHeight);
		lblRate.setFont(Common.normalFont);
		lblRate.setVisible(true);
		purchasePanel.add(lblRate);
		
		
		txtRate=new JilabaTextField();
		txtRate.setBounds(txtCalWt.getX(), lblRate.getY(), txtPurity.getWidth(), Common.lblHeight);
		txtRate.setTextType(TextInputType.NUMERIC);
		txtRate.setHorizontalAlignment(JTextField.RIGHT);
		txtRate.addKeyListener(this);
		txtRate.setVisible(true);
		purchasePanel.add(txtRate);
		
		lblRateId=new JLabel("RateId");
		lblRateId.setBounds(lblwastage.getX(), lblRate.getY(), Common.lblWidth, Common.lblHeight);
		lblRateId.setFont(Common.normalFont);
		lblRateId.setVisible(true);
		purchasePanel.add(lblRateId);
		
		txtRateId=new JilabaTextField();
		txtRateId.setBounds(txtWastage.getX(), lblRateId.getY(), txtPurity.getWidth(),Common.lblHeight);
		txtRateId.addKeyListener(this);
		txtRateId.setTextType(TextInputType.NUMERIC);
		txtRateId.setHorizontalAlignment(JTextField.RIGHT);
		txtRateId.setVisible(true);
		purchasePanel.add(txtRateId);
		
		lblAmount=new JLabel("Amount");
		lblAmount.setBounds(lblRate.getX(), lblRate.getY()+32, Common.lblWidth, Common.lblHeight);
		lblAmount.setFont(Common.normalFont);
		lblAmount.setVisible(true);
		purchasePanel.add(lblAmount);
		
		txtAmount=new JilabaTextField();
		txtAmount.setBounds(txtRate.getX(), lblAmount.getY(), txtPurity.getWidth()*2, Common.lblHeight);
		txtAmount.addKeyListener(this);
		txtAmount.setTextType(TextInputType.NUMERIC);
		txtAmount.setHorizontalAlignment(JTextField.RIGHT);
		txtAmount.setVisible(true);
		purchasePanel.add(txtAmount);
		
		lblSalesPerson=new JLabel("Sales Person");
		lblSalesPerson.setBounds(lblAmount.getX(), lblAmount.getY()+32, Common.lblWidth+10, Common.lblHeight);
		lblSalesPerson.setFont(Common.normalFont);
		lblSalesPerson.setVisible(true);
		purchasePanel.add(lblSalesPerson);
		
		txtSalesPerson=new JilabaTextField();
		txtSalesPerson.setBounds(txtAmount.getX(), lblSalesPerson.getY(), txtPurity.getWidth(), Common.lblHeight);
		txtSalesPerson.addKeyListener(this);
		txtSalesPerson.setTextType(TextInputType.NUMBER);
		txtSalesPerson.setHorizontalAlignment(JTextField.RIGHT);
		txtSalesPerson.setVisible(true);
		purchasePanel.add(txtSalesPerson);
		
		lblSalesPersonName=new JLabel("");
		lblSalesPersonName.setBounds(txtSalesPerson.getX()+txtSalesPerson.getWidth()+10, txtSalesPerson.getY(), Common.lblWidth+50,Common.lblHeight);
		lblSalesPersonName.setVisible(true);
		purchasePanel.add(lblSalesPersonName);
		
		btnAdd=new JilabaButton("Add");
		btnAdd.setBounds(lblRateId.getX(), lblSalesPerson.getY()+32, txtPurity.getWidth()/2+20, Common.lblHeight);
		btnAdd.setFont(Common.normalFont);
		btnAdd.setBackground(Color.decode("#00acc1"));
		btnAdd.addActionListener(this);
		btnAdd.setVisible(true);
		purchasePanel.add(btnAdd);
		
		btnClear=new JilabaButton("Clear");
		btnClear.setBounds(txtRateId.getX()+btnAdd.getWidth()/2, btnAdd.getY(), btnAdd.getWidth(), Common.lblHeight);
		btnClear.setFont(Common.normalFont);
		btnClear.setBackground(Color.decode("#00acc1"));
		btnClear.addActionListener(this);
		btnClear.setVisible(true);
		purchasePanel.add(btnClear);
		
		
		
		tblPurchaseDetails=new JilabaTable(purchaseColumn());
		scpPurchaseDetails=new JScrollPane(tblPurchaseDetails);
		scpPurchaseDetails.setBounds(txtDescription.getX()+txtDescription.getWidth()+10, txtDescription.getY(), purchasePanel.getWidth()/2+cmbPurCalType.getWidth()+txtEstNo.getWidth(), txtDescription.getHeight()*4-5);
		scpPurchaseDetails.setVisible(true);
		purchasePanel.add(scpPurchaseDetails);
		
		lblStoneHeading=new JLabel("Stone Details");
		lblStoneHeading.setBounds(scpPurchaseDetails.getX(), cmbUnit.getY(), Common.lblWidth+50, Common.lblHeight);
		lblStoneHeading.setFont(Common.normalFont);
		lblStoneHeading.setVisible(true);
		purchasePanel.add(lblStoneHeading);
		
		lblLastBillno=new JLabel("Last Billno :");
		lblLastBillno.setBounds(((scpPurchaseDetails.getWidth()/3)+scpPurchaseDetails.getX()), lblStoneHeading.getY(), lblStoneHeading.getWidth(), Common.lblHeight);
		lblLastBillno.setFont(new Font("Arial",Font.BOLD, 14));
		lblLastBillno.setForeground(Color.red);
		lblLastBillno.setVisible(true);
		purchasePanel.add(lblLastBillno);
		
		lbltableInformation=new JLabel("Press D For Delete Row | E For Edit Row");
		lbltableInformation.setBounds(lblLastBillno.getX()+lblLastBillno.getWidth()+cmbPurCalType.getWidth(), lblLastBillno.getY(), 350, Common.lblHeight);
		lbltableInformation.setFont(new Font("Arial",Font.PLAIN, 14));
		lbltableInformation.setVisible(true);
		purchasePanel.add(lbltableInformation);
		
		
		tblStoneDetails=new JilabaTable();
		tblStoneDetails.setBounds(scpPurchaseDetails.getX(), lblTagType.getY(), scpPurchaseDetails.getWidth(), cmbTagType.getHeight()+txtPiece.getHeight()+txtLessWt.getHeight());
		tblStoneDetails.setVisible(true);
		purchasePanel.add(tblStoneDetails);
		
		
		infoPanel=new JPanel(null);
		infoPanel.setBounds(tblStoneDetails.getX(),tblStoneDetails.getY()+tblStoneDetails.getHeight()+10, tblStoneDetails.getWidth()/2+100, purchasePanel.getHeight()/3+Common.lblHeight*2);
		infoPanel.setBackground(Color.white);
		infoPanel.setBorder(new LineBorder(Color.black,1));
		infoPanel.setVisible(true);
		purchasePanel.add(infoPanel);
		
		totalAmountPanel=new JPanel(null);
		totalAmountPanel.setBounds(infoPanel.getX()+infoPanel.getWidth()+10, infoPanel.getY(), tblStoneDetails.getWidth()-infoPanel.getWidth()-10, infoPanel.getHeight());
		totalAmountPanel.setBackground(Color.white);
		totalAmountPanel.setBorder(new LineBorder(Color.black, 1));
		totalAmountPanel.setVisible(true);
		purchasePanel.add(totalAmountPanel);
		
		chkDiscount=new JCheckBox("Alt+F2 -> Discount");
		chkDiscount.setBounds(10, infoPanel.getHeight()/2, lbltableInformation.getWidth(), Common.lblHeight);
		chkDiscount.setBackground(Color.white);
		chkDiscount.setFont(Common.normalFont);
		chkDiscount.setVisible(true);
		infoPanel.add(chkDiscount);
		
		chkRateChange=new JCheckBox("Alt+F4 -> Rate Change");
		chkRateChange.setBounds(chkDiscount.getX(), chkDiscount.getY()+32, chkDiscount.getWidth(), Common.lblHeight);
		chkRateChange.setBackground(Color.white);
		chkRateChange.setFont(Common.normalFont);
		chkRateChange.setVisible(true);
		infoPanel.add(chkRateChange);
		
		chkCustomerDetail=new JCheckBox("F11 -> Customer Details");
		chkCustomerDetail.setBounds(chkRateChange.getX(),chkRateChange.getY()+32, chkRateChange.getWidth(), Common.lblHeight);
		chkCustomerDetail.setBackground(Color.white);
		chkCustomerDetail.setFont(Common.normalFont);
		chkCustomerDetail.setVisible(true);
		infoPanel.add(chkCustomerDetail);
		
		chkStuddedView=new JCheckBox("Ctrl+S -> Studded View/Edit");
		chkStuddedView.setBounds(chkCustomerDetail.getX(), chkCustomerDetail.getY()+32, chkCustomerDetail.getWidth(), Common.lblHeight);
		chkStuddedView.setBackground(Color.white);
		chkStuddedView.setFont(Common.normalFont);
		chkStuddedView.setVisible(true);
		infoPanel.add(chkStuddedView);
		
		lblinfoTotalPcs=new JLabel("Total Pcs");
		lblinfoTotalPcs.setBounds(10, totalAmountPanel.getHeight()*2/100, Common.lblWidth, Common.lblHeight);
		lblinfoTotalPcs.setFont(Common.normalFont1);
		lblinfoTotalPcs.setForeground(Color.decode("#00acc1"));
		lblinfoTotalPcs.setVisible(true);
		totalAmountPanel.add(lblinfoTotalPcs);
		
		lblinfoTotalWeight=new JLabel("Total Weight");
		lblinfoTotalWeight.setBounds(lblinfoTotalPcs.getX(),  totalAmountPanel.getHeight()*16/100, 100, Common.lblHeight);
		lblinfoTotalWeight.setFont(Common.normalFont1);
		lblinfoTotalWeight.setForeground(Color.decode("#00acc1"));
		lblinfoTotalWeight.setVisible(true);
		totalAmountPanel.add(lblinfoTotalWeight);
		
		lblinfoAmount=new JLabel("Amount");
		lblinfoAmount.setBounds(lblinfoTotalPcs.getX(), totalAmountPanel.getHeight()*30/100, 100, Common.lblHeight);
		lblinfoAmount.setFont(Common.normalFont1);
		lblinfoAmount.setForeground(Color.decode("#00acc1"));
		lblinfoAmount.setVisible(true);
		totalAmountPanel.add(lblinfoAmount);
		
		lblinfoSgstAmt=new JLabel("SGST Amount");
		lblinfoSgstAmt.setBounds(lblinfoTotalWeight.getX(),totalAmountPanel.getHeight()*44/100, 100, Common.lblHeight);
		lblinfoSgstAmt.setFont(Common.normalFont1);
		lblinfoSgstAmt.setForeground(Color.decode("#00acc1"));
		lblinfoSgstAmt.setVisible(true);
		totalAmountPanel.add(lblinfoSgstAmt);
		
		lblinfoCgstAmt=new JLabel("CGST Amount");
		lblinfoCgstAmt.setBounds(lblinfoSgstAmt.getX(), totalAmountPanel.getHeight()*58/100, 100, Common.lblHeight);
		lblinfoCgstAmt.setFont(Common.normalFont1);
		lblinfoCgstAmt.setForeground(Color.decode("#00acc1"));
		lblinfoCgstAmt.setVisible(true);
		totalAmountPanel.add(lblinfoCgstAmt);
		
		
		lblinfoRoundof=new JLabel("RoundOff");
		lblinfoRoundof.setBounds(lblinfoCgstAmt.getX(), totalAmountPanel.getHeight()*72/100, 100, Common.lblHeight);
		lblinfoRoundof.setFont(Common.normalFont1);
		lblinfoRoundof.setForeground(Color.decode("#00acc1"));
		lblinfoRoundof.setVisible(true);
		totalAmountPanel.add(lblinfoRoundof);
		
		lblinfoTotal=new JLabel("Total");
		lblinfoTotal.setBounds(lblinfoRoundof.getX(), totalAmountPanel.getHeight()*86/100, 100, Common.lblHeight);
		lblinfoTotal.setFont(Common.normalFont1Bold);
		lblinfoTotal.setForeground(Color.decode("#00acc1"));
		lblinfoTotal.setVisible(true);
		totalAmountPanel.add(lblinfoTotal);
		
		
		lblinfoPcsValue=new JLabel("0.00");
		lblinfoPcsValue.setBounds(infoPanel.getWidth()/2, lblinfoTotalPcs.getY(), 100, Common.lblHeight);
		lblinfoPcsValue.setFont(Common.normalFont1);
//		lblinfoPcsValue.setHorizontalTextPosition(JLabel.RIGHT);
		lblinfoPcsValue.setAlignmentX(LEFT_ALIGNMENT);
		lblinfoPcsValue.setForeground(Color.decode("#00acc1"));
		lblinfoPcsValue.setVisible(true);
		totalAmountPanel.add(lblinfoPcsValue);
		
		
		lblinfoWeightValue=new JLabel("0.00");
		lblinfoWeightValue.setBounds(infoPanel.getWidth()/2, lblinfoTotalWeight.getY() ,100, Common.lblHeight);
		lblinfoWeightValue.setFont(Common.normalFont1);
		lblinfoWeightValue.setHorizontalAlignment(JLabel.LEFT);
		lblinfoWeightValue.setForeground(Color.decode("#00acc1"));
		lblinfoWeightValue.setVisible(true);
		totalAmountPanel.add(lblinfoWeightValue);
		
		
		lblinfoAmtValue=new JLabel("0.00");
		lblinfoAmtValue.setBounds(infoPanel.getWidth()/2,lblinfoAmount.getY(), 100, Common.lblHeight);
		lblinfoAmtValue.setFont(Common.normalFont1);
		lblinfoAmtValue.setForeground(Color.decode("#00acc1"));
		lblinfoAmtValue.setHorizontalAlignment(JLabel.LEFT);
		lblinfoAmtValue.setVisible(true);
		totalAmountPanel.add(lblinfoAmtValue);
		
		lblinfoSgstValue=new JLabel("0.00");
		lblinfoSgstValue.setBounds(infoPanel.getWidth()/2, lblinfoSgstAmt.getY(), 100, Common.lblHeight);
		lblinfoSgstValue.setFont(Common.normalFont1);
		lblinfoSgstValue.setForeground(Color.decode("#00acc1"));
		lblinfoSgstValue.setHorizontalAlignment(JLabel.LEFT);
		lblinfoSgstValue.setVisible(true);
		totalAmountPanel.add(lblinfoSgstValue);
		
		lblinfoCgstValue=new JLabel("0.00");
		lblinfoCgstValue.setBounds(infoPanel.getWidth()/2, lblinfoCgstAmt.getY(), 100, Common.lblHeight);
		lblinfoCgstValue.setFont(Common.normalFont1);
		lblinfoCgstValue.setForeground(Color.decode("#00acc1"));
		lblinfoCgstValue.setHorizontalAlignment(JLabel.LEFT);
		lblinfoCgstValue.setVisible(true);
		totalAmountPanel.add(lblinfoCgstValue);
		
		lblinfoRoundValue=new JLabel("0.00");
		lblinfoRoundValue.setBounds(infoPanel.getWidth()/2, lblinfoRoundof.getY(), 100, Common.lblHeight);
		lblinfoRoundValue.setFont(Common.normalFont1);
		lblinfoRoundValue.setForeground(Color.decode("#00acc1"));
		lblinfoRoundValue.setHorizontalAlignment(JLabel.LEFT);
		lblinfoRoundValue.setVisible(true);
		totalAmountPanel.add(lblinfoRoundValue);
		
		lblinfoTotAmtValue=new JLabel("0.00");
		lblinfoTotAmtValue.setBounds(infoPanel.getWidth()/2, lblinfoTotal.getY(), 100, Common.lblHeight);
		lblinfoTotAmtValue.setFont(Common.normalFont1);
		lblinfoTotAmtValue.setForeground(Color.decode("#00acc1"));
		lblinfoTotAmtValue.setHorizontalAlignment(JLabel.LEFT);
		lblinfoTotAmtValue.setVisible(true);
		totalAmountPanel.add(lblinfoTotAmtValue);
		
	}
	private List<JilabaColumn> purchaseColumn() {
		
		List<JilabaColumn> lstColumns=new LinkedList<JilabaColumn>();
		
		lstColumns.add(new JilabaColumn("Product", String.class, 150, SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("SubProduct",String.class,150,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("Piece",Integer.class,100,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("GrossWt",Double.class,150,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("NetWt",Double.class,150,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("Rate",Double.class,200,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("RateId",Double.class,200,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("GrossAmount",Double.class,250,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("Discount",Double.class,200,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("NetAmount",Double.class,250,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("TaxPer",Double.class,150,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("TaxAmount",Double.class,250,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("UnitName",String.class,200,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("SaleDesc",String.class,250,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("WastagPer",Double.class,250,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("Wastage",Double.class,200,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("McPerGrm",Double.class,200,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("MkCharge",Double.class,250,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("AmtCalcType",String.class,250,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("WastageCalcType",String.class,250,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("TouchPurity",Double.class,250,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("StudStnPcs",Integer.class,100,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("StudStnAmt",Double.class,250,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("StudDiaPcs",Double.class,100,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("StudDiaAmt",Double.class,100,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("CutName",String.class,200,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("ColorName",String.class,200,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("ShapeName",String.class,200,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("ClarityName",String.class,200,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("SieveName",String.class,200,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("OtherCharge",Double.class,250,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("TotalAmount",Double.class,250,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("PerPcsStkValue",Double.class,250,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("PerGrmStkValue",Double.class,250,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("McCalcType",String.class,200,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("HSNCode",String.class,200,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("SGSTPer",Double.class,200,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("SGSTAmt",Double.class,250,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("CGSTPer",Double.class,200,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("CGSTAmt",Double.class,200,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("IGSTPer",Double.class,200,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("IGSTAmt",Double.class,250,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("RCM",String.class,200,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("GSTBillno",String.class,250,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("CompanyCode",String.class,200,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("OrgCompanyCode",String.class,200,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("BillStatus",String.class,100,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("BrefNo",String.class,250,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("RowSign",String.class,250,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("EntryOrder",Integer.class,200,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("EstUniqukey",String.class,250,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("LessWt",Double.class,200,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("PurCalcWt",Double.class,200,SwingConstants.LEFT));
		lstColumns.add(new JilabaColumn("SalesPerson",String.class,0,SwingConstants.RIGHT));
		lstColumns.add(new JilabaColumn("SalesPersonId",Integer.class,0,SwingConstants.LEFT));
		
		
		return lstColumns;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			switch (e.getActionCommand()) {
			
			case "Add":
				
				List<PurchasePojo> lstPurchasePojos=new LinkedList<PurchasePojo>();
				
				PurchasePojo purchasePojo=new PurchasePojo();
				
				purchasePojo.setProductName(String.valueOf(cmbProduct.getSelectedItem()));
				purchasePojo.setProductCode(Integer.parseInt(cmbProduct.getSelectedItemValue().toString()));
				purchasePojo.setCategoryName(String.valueOf(cmbCategory.getSelectedItem()));
				purchasePojo.setCatCode(Integer.parseInt(cmbCategory.getSelectedItemValue().toString()));
				purchasePojo.setNatureName(String.valueOf(cmbNature.getSelectedItem()));
				purchasePojo.setNatId(Integer.parseInt(cmbNature.getSelectedItemValue().toString()));
				purchasePojo.setGrossWt(Double.parseDouble(txtGrsWt.getText()));
				purchasePojo.setNetWt(Double.parseDouble(txtNetwt.getText()));
				purchasePojo.setLessWt(Double.parseDouble(txtLessWt.getText()));
				purchasePojo.setPureWt(Double.parseDouble(txtPureWWt.getText()));
				purchasePojo.setPurity(Double.parseDouble(txtPurity.getText()));
				purchasePojo.setWastage(Double.parseDouble(txtWastage.getText()));
				purchasePojo.setWastageper(Double.parseDouble(txtwastagePer.getText()));
				purchasePojo.setPurCalWt(Double.parseDouble(txtCalWt.getText()));
				purchasePojo.setRate(Double.parseDouble(txtRate.getText()));
				purchasePojo.setRateid(Double.parseDouble(txtRateId.getText()));
				purchasePojo.setGrossAmount(Double.parseDouble(txtAmount.getText()));
				purchasePojo.setNetAmount(Double.parseDouble(txtAmount.getText()));
				purchasePojo.setTotalAmount(Double.parseDouble(txtAmount.getText()));
				purchasePojo.setEmpId(txtSalesPerson.getText());
				purchasePojo.setEmpName(lblSalesPersonName.getText());
				
				lstPurchasePojos.add(purchasePojo);
				
				
				List<Object> lstObjects=new LinkedList<Object>();
				
				lstObjects.add(purchasePojo.getProductName());
//				lstObjects.add("");
//				lstObjects.add(lstObjects)
				
				tblPurchaseDetails.addRow(lstObjects);
				
				break;
			
			case "Clear":
					initalize();	
					break;
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e){
		try {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				
				if(e.getSource()==txtEstNo) {
					cmbPurchaseType.requestFocus();
				}else if(e.getSource()==cmbPurchaseType) {
					cmbMake.requestFocus();
				}else if(e.getSource()==cmbMake) {
					cmbNature.requestFocus();
				}else if(e.getSource()==cmbNature) {
					if(String.valueOf(cmbNature.getSelectedItemValue()).toString().equalsIgnoreCase("1")) {
						cmbMetal.setEnabled(true);
						cmbMetal.requestFocus();
					}else {
						cmbMetal.setEnabled(false);
						cmbCategory.requestFocus();
					}
				}else if(e.getSource()==cmbMetal) {
					loadCategory(Integer.parseInt(cmbNature.getSelectedItemValue().toString()),String.valueOf(cmbMetal.getSelectedItemValue()));
					cmbCategory.requestFocus();
				}else if(e.getSource()==cmbCategory) {
					loadProduct();
					cmbPurCalType.requestFocus();
				}else if(e.getSource()==cmbPurCalType) {
					if(String.valueOf(cmbPurCalType.getSelectedItemValue()).equalsIgnoreCase("T")) {
						txtRate.setText(common.getAmtFormat().format(logicPurchase.getPurchaseRate(String.valueOf(cmbMetal.getSelectedItemValue()), 100)));
					}
					txtDescription.requestFocus();
				}else if(e.getSource()==txtDescription) {
					cmbProduct.requestFocus();
				}else if(e.getSource()==cmbProduct) {
					cmbSubProduct.requestFocus();
				}else if(e.getSource()==cmbSubProduct) {
					cmbUnit.requestFocus();
				}else if(e.getSource()==cmbUnit) {
					cmbTagType.requestFocus();
				}else if(e.getSource()==cmbTagType) {
					txtPurity.requestFocus();
				}else if(e.getSource()==txtPurity) {
					txtPurity.setText(common.getAmtFormat().format(Double.parseDouble(txtPurity.getText())));
					txtPiece.requestFocus();
				}else if(e.getSource()==txtPiece) {
					txtGrsWt.requestFocus();
				}else if(e.getSource()==txtGrsWt) {
					txtGrsWt.setText(common.getWtFormat().format(Double.parseDouble(txtGrsWt.getText())));
					txtNetwt.setText(txtGrsWt.getText());
					if(String.valueOf(cmbPurCalType.getSelectedItemValue()).equalsIgnoreCase("N")) {
						txtPureWWt.setText(txtGrsWt.getText());
						txtCalWt.setText(txtGrsWt.getText());
					}else if(String.valueOf(cmbPurCalType.getSelectedItemValue()).equalsIgnoreCase("W")) {
						txtPureWWt.setText(common.getWtFormat().format(Double.parseDouble(txtGrsWt.getText())*Double.parseDouble(txtPurity.getText())/100));
						txtWastage.setText(common.getWtFormat().format(Double.parseDouble(txtGrsWt.getText())-Double.parseDouble(txtPureWWt.getText())));
						txtwastagePer.setText(String.valueOf((Double.parseDouble(txtWastage.getText())*100)/Double.parseDouble(txtGrsWt.getText())));
						txtCalWt.setText(txtPureWWt.getText());
					}else if(String.valueOf(cmbPurCalType.getSelectedItemValue()).equalsIgnoreCase("T")) {
						txtPureWWt.setText(common.getWtFormat().format(Double.parseDouble(txtGrsWt.getText())*Double.parseDouble(txtPurity.getText())/100));
						txtCalWt.setText(txtPureWWt.getText());
					}
					txtAmount.setText(String.valueOf(Double.parseDouble(txtCalWt.getText())*Double.parseDouble(txtRate.getText())));
					txtDustWt.requestFocus();
				}else if(e.getSource()==txtDustWt) {
					
					if(Double.parseDouble(txtGrsWt.getText())<=Double.parseDouble(txtDustWt.getText())) {
						JOptionPane.showMessageDialog(null, "DustWeight GraterThan the GrossWeight....!");
						return;
					}
					
					txtDustWt.setText(common.getWtFormat().format(Double.parseDouble(txtDustWt.getText())));
					if(String.valueOf(cmbPurCalType.getSelectedItemValue()).equalsIgnoreCase("N")) {
						txtPureWWt.setText(common.getWtFormat().format(Double.parseDouble(txtGrsWt.getText())-Double.parseDouble(txtDustWt.getText())));
						txtCalWt.setText(txtPureWWt.getText());
						
						txtwastagePer.requestFocus();
					}else if(String.valueOf(cmbPurCalType.getSelectedItemValue()).equalsIgnoreCase("W")) {
						txtPureWWt.setText(common.getWtFormat().format((Double.parseDouble(txtGrsWt.getText())-Double.parseDouble(txtDustWt.getText())) * Double.parseDouble(txtPurity.getText())/100));
						txtWastage.setText(common.getWtFormat().format((Double.parseDouble(txtNetwt.getText())-Double.parseDouble(txtDustWt.getText()))-Double.parseDouble(txtPureWWt.getText())));
						txtwastagePer.setText(common.getWtFormat().format((Double.parseDouble(txtWastage.getText())*100)/(Double.parseDouble(txtNetwt.getText())-Double.parseDouble(txtDustWt.getText()))));
						txtCalWt.setText(txtPureWWt.getText());
						
						txtCalWt.requestFocus();
					}else if(String.valueOf(cmbPurCalType.getSelectedItemValue()).contentEquals("T")) {
						txtPureWWt.setText(common.getWtFormat().format((Double.parseDouble(txtGrsWt.getText())-Double.parseDouble(txtDustWt.getText())) * Double.parseDouble(txtPurity.getText())/100));
						txtCalWt.setText(txtPureWWt.getText());
						
						txtCalWt.requestFocus();
					}
					txtAmount.setText(common.getAmtFormat().format(Double.parseDouble(txtCalWt.getText())*Double.parseDouble(txtRate.getText())));
					
				}else if(e.getSource()==txtwastagePer) {
					txtWastage.setText(common.getWtFormat().format(Double.parseDouble(txtPureWWt.getText())*Double.parseDouble(txtwastagePer.getText())/100));
					txtCalWt.setText(common.getWtFormat().format(Double.parseDouble(txtPureWWt.getText())-Double.parseDouble(txtWastage.getText())));
					txtPureWWt.setText(txtCalWt.getText());
					txtAmount.setText(common.getAmtFormat().format(Double.parseDouble(txtCalWt.getText())*Double.parseDouble(txtRate.getText())));
					txtWastage.requestFocus();
				}else if(e.getSource()==txtWastage) {
					txtCalWt.requestFocus();
				}else if(e.getSource()==txtCalWt) {
					txtRate.requestFocus();
				}else if(e.getSource()==txtRate) {
					txtAmount.setText(common.getAmtFormat().format(Double.parseDouble(txtCalWt.getText())*Double.parseDouble(txtRate.getText())));
					txtRate.setText(common.getAmtFormat().format(Double.parseDouble(txtRate.getText())));
					txtRateId.requestFocus();
				}else if(e.getSource()==txtRateId) {
					txtAmount.requestFocus();
				}else if(e.getSource()==txtAmount) {
					txtSalesPerson.requestFocus();
				}else if(e.getSource()==txtSalesPerson) {
					
					if(!txtSalesPerson.getText().equalsIgnoreCase(""))
						lblSalesPersonName.setText(logicPurchase.getEmployee(Integer.parseInt(txtSalesPerson.getText())).get(0).getEmpName());
					btnAdd.requestFocus();
				}else if(e.getSource()==btnAdd) {
					
					
									
					
					
					
					
				}
			}
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void add() throws Exception {
		componentEnable();
		txtEstNo.requestFocus();
	}
	@Override
	public void save() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void view() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void clear() throws Exception {
		initalize();
		componentDisable();
	}
	
    private void componentDisable() {
		
		for(int i=0;i<purchasePanel.getComponentCount();i++) {
			purchasePanel.getComponent(i).setEnabled(false);
		}
		purchasePanel.setEnabled(false);
	}
	private void componentEnable() {
		
		for(int i=0;i<purchasePanel.getComponentCount();i++) {
			purchasePanel.getComponent(i).setEnabled(true);
		}
		purchasePanel.setEnabled(true);
	}
}
