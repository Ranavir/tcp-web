package com.stl.tcpweb.dto;

import java.math.BigDecimal;

public class ParamWiseRecord {

	public ParamWiseRecord() {
		
	}
	private String factor ;
	private String param ;
	private String hexval ;
	private BigDecimal decval ;
	private int startIndex ;
	private int bytesRead ;
	public ParamWiseRecord(String factor, String param, String hexval,
			BigDecimal decval, int startIndex, int bytesRead) {
		super();
		this.factor = factor;
		this.param = param;
		this.hexval = hexval;
		this.decval = decval;
		this.startIndex = startIndex;
		this.bytesRead = bytesRead;
	}
	public ParamWiseRecord(String param, String hexval, BigDecimal decval,
			int startIndex, int bytesRead) {
		super();
		this.param = param;
		this.hexval = hexval;
		this.decval = decval;
		this.startIndex = startIndex;
		this.bytesRead = bytesRead;
	}
	@Override
	public String toString() {
		return param+"Record [factor=" + factor + ", param=" + param
				+ ", hexval=" + hexval + ", decval=" + decval + ", startIndex="
				+ startIndex + ", bytesRead=" + bytesRead + "]";
	}
	public String getFactor() {
		return factor;
	}
	public void setFactor(String factor) {
		this.factor = factor;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getHexval() {
		return hexval;
	}
	public void setHexval(String hexval) {
		this.hexval = hexval;
	}
	public BigDecimal getDecval() {
		return decval;
	}
	public void setDecval(BigDecimal decval) {
		this.decval = decval;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getBytesRead() {
		return bytesRead;
	}
	public void setBytesRead(int bytesRead) {
		this.bytesRead = bytesRead;
	}
	
}//end of 
