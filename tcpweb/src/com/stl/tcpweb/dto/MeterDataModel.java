package com.stl.tcpweb.dto;

import java.math.BigDecimal;

/**
 * This class encapsulate the data for all parameters 
 * of a meter at a particular time interval
 * @author ranavir
 * @date 27052016
 */
public class MeterDataModel {

	public MeterDataModel() {
		super() ;
	}
	public MeterDataModel(String meter_id, BigDecimal vR, BigDecimal vY,
			BigDecimal vB, BigDecimal iR, BigDecimal iY, BigDecimal iB,
			BigDecimal kW, BigDecimal kV, BigDecimal pF, BigDecimal fQ,
			BigDecimal aI, BigDecimal aE) {
		super();
		this.meter_id = meter_id;
		VR = vR;
		VY = vY;
		VB = vB;
		IR = iR;
		IY = iY;
		IB = iB;
		KW = kW;
		KV = kV;
		PF = pF;
		FQ = fQ;
		AI = aI;
		AE = aE;
		this.date = date;
		this.timestamp = timestamp;
	}
	private String meter_id ;
	private BigDecimal VR ;
	private BigDecimal VY ;
	private BigDecimal VB ;
	private BigDecimal IR ;
	private BigDecimal IY ;
	private BigDecimal IB ;
	private BigDecimal KW ;
	private BigDecimal KV ;
	private BigDecimal PF ;
	private BigDecimal FQ ;
	private BigDecimal AI ;
	private BigDecimal AE ;
	String date ;
	String timestamp ;
	public String getMeter_id() {
		return meter_id;
	}
	public void setMeter_id(String meter_id) {
		this.meter_id = meter_id;
	}
	public BigDecimal getVR() {
		return VR;
	}
	public void setVR(BigDecimal vR) {
		VR = vR;
	}
	public BigDecimal getVY() {
		return VY;
	}
	public void setVY(BigDecimal vY) {
		VY = vY;
	}
	public BigDecimal getVB() {
		return VB;
	}
	public void setVB(BigDecimal vB) {
		VB = vB;
	}
	public BigDecimal getIR() {
		return IR;
	}
	public void setIR(BigDecimal iR) {
		IR = iR;
	}
	public BigDecimal getIY() {
		return IY;
	}
	public void setIY(BigDecimal iY) {
		IY = iY;
	}
	public BigDecimal getIB() {
		return IB;
	}
	public void setIB(BigDecimal iB) {
		IB = iB;
	}
	public BigDecimal getKW() {
		return KW;
	}
	public void setKW(BigDecimal kW) {
		KW = kW;
	}
	public BigDecimal getKV() {
		return KV;
	}
	public void setKV(BigDecimal kV) {
		KV = kV;
	}
	public BigDecimal getPF() {
		return PF;
	}
	public void setPF(BigDecimal pF) {
		PF = pF;
	}
	public BigDecimal getFQ() {
		return FQ;
	}
	public void setFQ(BigDecimal fQ) {
		FQ = fQ;
	}
	public BigDecimal getAI() {
		return AI;
	}
	public void setAI(BigDecimal aI) {
		AI = aI;
	}
	public BigDecimal getAE() {
		return AE;
	}
	public void setAE(BigDecimal aE) {
		AE = aE;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "MeterDataModel :: \nmeter_id=" + meter_id + "\n VR=" + VR + "\n VY="
				+ VY + "\n VB=" + VB + "\n IR=" + IR + "\n IY=" + IY + "\n IB="
				+ IB + "\n KW=" + KW + "\n KV=" + KV + "\n PF=" + PF + "\n FQ="
				+ FQ + "\n AI=" + AI + "\n AE=" + AE + "\n date=" + date
				+ "\n timestamp=" + timestamp;
	}
	
	
}
