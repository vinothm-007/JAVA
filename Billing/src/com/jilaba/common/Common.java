package com.jilaba.common;

import java.awt.Font;
import java.awt.Toolkit;
import java.text.DecimalFormat;

public class Common {

	private int width=Toolkit.getDefaultToolkit().getScreenSize().width;
	private int height=Toolkit.getDefaultToolkit().getScreenSize().height;
	public static int internalHeight,internalWidth;
	public static Font normalFont=new Font("TimesnewRoman",Font.PLAIN,12);
	public static Font normalFont1=new Font("TimesnewRoman",Font.PLAIN,14);
	public static Font normalFont1Bold=new Font("TimesnewRoman",Font.BOLD,15);
	private DecimalFormat amtFormat=new DecimalFormat("0.00");
	private DecimalFormat wtFormat=new DecimalFormat("0.000");
	private DecimalFormat pcsFormat=new DecimalFormat("0");
	public static int lblHeight=28,lblWidth=70;
	private FormAction formAction;

	public FormAction getFormAction() {
		return formAction;
	}
	public void setFormAction(FormAction formAction) {
		this.formAction = formAction;
	}
	public DecimalFormat getPcsFormat() {
		return pcsFormat;
	}
	public void setPcsFormat(DecimalFormat pcsFormat) {
		this.pcsFormat = pcsFormat;
	}
	public DecimalFormat getAmtFormat() {
		return amtFormat;
	}
	public void setAmtFormat(DecimalFormat amtFormat) {
		this.amtFormat = amtFormat;
	}
	public DecimalFormat getWtFormat() {
		return wtFormat;
	}
	public void setWtFormat(DecimalFormat wtFormat) {
		this.wtFormat = wtFormat;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	
}
