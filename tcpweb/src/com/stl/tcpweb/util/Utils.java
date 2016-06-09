package com.stl.tcpweb.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.stl.tcpweb.daoImpl.MeterDataDaoImpl;
import com.stl.tcpweb.dto.ParamWiseRecord;
import com.stl.tcpweb.serviceImpl.MeterDataServiceImpl;

public class Utils {
	private static Logger logger = Logger.getLogger(Utils.class) ;
	static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	
	
	/*****************************************************************************
	 * This method provides the ServiceImpl object based on requirement
	 * @param whichService
	 * @return
	 *****************************************************************************/
	public static Object getService(int whichService) {
		switch (whichService) {
		case ProjectConstants.METER_DATA_SERVICE:
			return MeterDataServiceImpl.getInstance() ;
		
		default:
			return null;
		}
	}//end of getService
	/******************************************************************************
	 * This method gives the DaoImpl object based on requirement
	 * @param whichDao
	 * @return
	 *****************************************************************************/
	public static Object getDao(int whichDao) {
		switch (whichDao) {
		case ProjectConstants.METER_DATA_DAO :
			return MeterDataDaoImpl.getInstance() ;
		default:
			return null;
		}
	}//end of getDao
	
	/**
	 * Sample testing main method
	 * @param args
	 */
	public static void main(String[] args) {
		
		
				 
	}//end of main method
	
	
	
	
}//end of Utils class


















/**
 * This method generates the output  so that the output will be in a table with required tr and td
 * @author Ranvir Dash
 * @param String
 * @return String
 */
/*public static String generateFormattedOutput(String strNewData) {
	String strFormattedData = "" ;
	
	if(null!=strNewData){
		if(strNewData.indexOf( ProjectConstants.MSG_BUSY )==0){
			strFormattedData += "<tr><td class='tdIp'>"+""+"</td>";
			strFormattedData += "<td class='tdTime'>"+sdf.format(Calendar.getInstance().getTime())+"</td>";
			strFormattedData += "<td class='tdBytes'>"+"0 Bytes"+"</td>";
			strFormattedData += "<td class='tdData'>"+ProjectConstants.MSG_BUSY1+"</td></tr>";
			System.out.println(strFormattedData);
			return strFormattedData ;
		}
		else if(strNewData.indexOf('/')==0){
			//System.out.println("got / in 1st pos");
			String strArr[] = strNewData.split(" ");
			String ipAddr = strArr[0].substring(1,strArr[0].length()) ;
			System.out.println(ipAddr);
			String data = strNewData.substring(ipAddr.length()+2, strNewData.length()) ;
			strFormattedData += "<tr><td class='tdIp'>"+ipAddr+"</td>";
			strFormattedData += "<td class='tdTime'>"+sdf.format(Calendar.getInstance().getTime())+"</td>";
			strFormattedData += "<td class='tdBytes'>"+data.getBytes().length+" Bytes"+"</td>";
			strFormattedData += "<td class='tdData'>"+data+"</td></tr>";
		strFormattedData += "<tr><td class='tdIp'>"+"IP Address"+"</td>";
		strFormattedData += "<td class='tdTime'>"+sdf.format(Calendar.getInstance().getTime())+"</td>";
		strFormattedData += "<td class='tdBytes'>"+strNewData.getBytes().length+" Bytes"+"</td>";
		strFormattedData += "<td class='tdData'>"+strNewData+"</td></tr>";
		System.out.println(strFormattedData);
		return strFormattedData ;
		}
		else{
			//System.out.println("new connection/Lost connection msg");
			String strArr[] = strNewData.split("/");
			String strInitMsg = strArr[0];
			System.out.println(strInitMsg);
			String ipAddr = strArr[1];
			System.out.println(ipAddr);
			strFormattedData += "<tr><td class='tdIp'>"+"</td>";
			strFormattedData += "<td class='tdTime'>"+sdf.format(Calendar.getInstance().getTime())+"</td>";
			strFormattedData += "<td class='tdBytes'>"+"0 Bytes"+"</td>";
			strFormattedData += "<td class='tdData'>"+strInitMsg+" "+ipAddr+"</td></tr>";
			System.out.println(strFormattedData);
			return strFormattedData ;
		}
			
	}
	else
		return "" ;
}*/
/**
 * This method does only the trimming operation of the required output to decrease the size of the StringBuilder object
 * @author Ranvir Dash
 * @param String
 * @return String
 */
/*public static String generateTrimmedOutput(String scValue) {
	String strTrimmedVal = "" ;
	String strTr[] = scValue.split("</tr>");
	System.out.println(strTr.length);
	if(strTr.length > 30){//no of rows > 5 prepare only 5 last 5 lines
		for(int i = 0; i < 30 ; i++ ){
			strTrimmedVal += strTr[i].trim()+"</tr>";
		}
		System.out.println(strTrimmedVal);
	//System.out.println(strTr[strTr.length-1].trim()+"</tr>");
	}
	else
		strTrimmedVal = scValue ;
	return strTrimmedVal;
}*/