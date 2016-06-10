/*Copyright (c) 2016 Silicon Tech Lab Pvt Ltd.  All rights reserved. 			           *	
 * This document is the property of Silicon Tech Lab Pvt Ltd..                          *
 * All ideas and information contained in this document are the intellectual property (IP) *
 * rights of Silicon Tech Lab Pvt Ltd.. This document is not for reference or general   *
 * distribution and is meant for use only for STL. This document shall not             *
 * be loaned to or shared with anyone, within or outside STL, including its customers. * 
 * Copying or unauthorized distribution of this document, in any form or means             *
 * including electronic, mechanical, photocopying or otherwise is illegal.                 * 
 * Use is subject to license terms only.                                                   *  
 *****************************************************************************************

 *****************************************************************************************
 *    Ver         Author                  Date        			Description		        *
 *    1.0         Ranavir               24-April-2016          	Initial Version		    *
 *****************************************************************************************
 */
package com.stl.tcpweb.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.stl.tcpweb.dto.MeterDataModel;
import com.stl.tcpweb.dto.ParamWiseRecord;
import com.stl.tcpweb.service.MeterDataService;
import com.stl.tcpweb.util.ProjectConstants;
import com.stl.tcpweb.util.Utils;
/*************************************************************
 * This class is responsible for Meter data representation &  
 * saving data into database
 * @author Ranavir
 * @date 26/05/2016
 *************************************************************/
public class MeterDataHelper {
	//String methodname = "" ;
	String userId = "";
	
	private static Logger logger = null;
	private static MeterDataHelper mdHelper = null;
	private static MeterDataService mdService = null ;
	
	
	
	
	
	private MeterDataHelper() {
		logger = Logger.getLogger(MeterDataHelper.class) ;
		mdService = (MeterDataService)Utils.getService(ProjectConstants.METER_DATA_SERVICE) ;
	}
	
	public static MeterDataHelper getInstance(){
		if(mdHelper == null){
			mdHelper = new MeterDataHelper();
			logger.info("MeterDataHelper Instantiated...");
		}
		return mdHelper;
	}
	/**
	 * Sample testing main method
	 * @param args
	 */
	public static void main(String[] args) {
		
		String st1 = "01030FA00012C6F101032400050205020534404D414230303032390141363044473032171313183141505830303032B74501030FBE001766F401032E0001FA8A0001FF020001FEE40000344000003A2B000033C4FFFF33D2000034570000D2C800000402FC37C34C0003B72A01031004001800C10103300004BB5E000547D40004BB66000547C000000BFE000284E6000332030000000000056E0A00064CD70000D35C00000001C994010310360013E0C90103260000094800000509137C00000FE806140C2000046BA00004DEFC00008C430000AC00355AF7C12BC907030FA00012C69707032400020102010233404D464D30303030360141363044303241171313183141505830303032B9E107030FBE0017669207032E000370FA0003757A00036F0E0001967000019FDB00019277000639C0FFFFA76C00063C3400000402FC1AC34C00035AB207031004001800A7070330005324FE000493F20053272C000493D8000CAE7D000598A8000A84120000E91F005A37C1000C9B100003D1F3009893A16FBF070310360013E0AF07032644F8000028140000137B8A8C000496D421BC0020853C00077F0000013279000017FF355AF7C206FD09030FA00012C7B909032400020102010233404D464D30303030370141363044303241171313183141505830303032D07809030FBE001767BC09032E000370F2000375D400036F6800008E610000A0BB000094CF00024B2AFFFFD39500024CD800000402FC1BC34E0003263C0903100400180189090330004E790100016E18004606500000F5CC0058BF940017DC3600001EEE0000B468005BE4830001D84B008CB5BA009893D2CF01090310360013E1810903261A7400000EB00000137C358800005588094400302DC000048E180001201A00001818355AF7C47CB5" ;
		String str = "01030FA00012C6F101032400050205020534404D414230303032390141363044473032171313183141505830303032B74501030FBE001766F401032E0001FA8A0001FF020001FEE40000344000003A2B000033C4FFFF33D2000034570000D2C800000402FC37C34C0003B72A01031004001800C10103300004BB5E000547D40004BB66000547C000000BFE000284E6000332030000000000056E0A00064CD70000D35C00000001C994010310360013E0C90103260000094800000509137C00000FE806140C2000046BA00004DEFC00008C430000AC00355AF7C12BC907030FA00012C69707032400020102010233404D464D30303030360141363044303241171313183141505830303032B9E107030FBE0017669207032E000370FA0003757A00036F0E0001967000019FDB00019277000639C0FFFFA76C00063C3400000402FC1AC34C00035AB207031004001800A7070330005324FE000493F20053272C000493D8000CAE7D000598A8000A84120000E91F005A37C1000C9B100003D1F3009893A16FBF070310360013E0AF07032644F8000028140000137B8A8C000496D421BC0020853C00077F0000013279000017FF355AF7C206FD09030FA00012C7B909032400020102010233404D464D30303030370141363044303241171313183141505830303032D07809030FBE001767BC09032E000370F2000375D400036F6800008E610000A0BB000094CF00024B2AFFFFD39500024CD800000402FC1BC34E0003263C0903100400180189090330004E790100016E18004606500000F5CC0058BF940017DC3600001EEE0000B468005BE4830001D84B008CB5BA009893D2CF01090310360013E1810903261A7400000EB00000137C358800005588094400302DC000048E180001201A00001818355AF7C47CB5" ;
		//System.out.println(str.length());
		/*System.out.println( getVR(str,121,8,1000) );
		System.out.println( getVY(str,129,8,1001) );
		System.out.println( getVB(str,137,8,1002) );
		System.out.println( getIR(str,145,8,0.02) );
		System.out.println( getIY(str,153,8,0.03) );
		System.out.println( getIB(str,161,8,0.04) ); 
		System.out.println( getKW(str,169,8) ); 
		System.out.println( getKV(str,177,8) );
		System.out.println( getPF(str,201,4) ); 
		System.out.println( getFQ(str,205,4,1000) ); 
		System.out.println( getActiveImport(str,239,8,10) );
		System.out.println( getActiveExport(str,247,8,10) );*/
		
		System.out.println( getVR(str,561,8,1000) );
		System.out.println( getVY(str,569,8,1001) );
		System.out.println( getVB(str,577,8,1002) );
		System.out.println( getIR(str,585,8,0.02) );
		System.out.println( getIY(str,593,8,0.03) );
		System.out.println( getIB(str,601,8,0.04) ); 
		System.out.println( getKW(str,609,8) ); 
		System.out.println( getKV(str,617,8) );
		System.out.println( getPF(str,641,4) ); 
		System.out.println( getFQ(str,645,4,1000) ); 
		System.out.println( getActiveImport(str,679,8,1) );
		System.out.println( getActiveExport(str,687,8,10) );
		
		/*System.out.println( getVR(str,1001,8,1000) );
		System.out.println( getVY(str,1009,8,1000) );
		System.out.println( getVB(str,1017,8,1000) );
		System.out.println( getIR(str,1025,8,0.02) );
		System.out.println( getIY(str,1033,8,0.02) );
		System.out.println( getIB(str,1041,8,0.02) ); 
		System.out.println( getKW(str,1049,8) ); 
		System.out.println( getKV(str,1057,8) );
		System.out.println( getPF(str,1081,4) ); 
		System.out.println( getFQ(str,1085,4,1000) ); 
		System.out.println( getActiveImport(str,1119,8,1) );
		System.out.println( getActiveExport(str,1127,8,100) );*/
		
		
		System.out.println(st1.substring(0,2));
		System.out.println(st1.substring(1 * 440 , 1 * 440 + 2));
		System.out.println(st1.substring(2 * 440 , 2 * 440 + 2));
		
		//generateFormattedOutput("A new connection from /192.168.137.1:50715");
		//generateFormattedOutput("/192.168.137.1:50715 gotMsg hi to Ranvir");
		//generateFormattedOutput("Connection Lost to /192.168.137.1:50715");
		/*generateTrimmedOutput("<tr><td class='tdIp'></td><td class='tdTime'>19:51:33</td><td class='tdBytes'>0 Bytes</td><td class='tdData'>Connection Lost to  192.168.137.1:50854</td></tr>"
				+ "			<tr><td class='tdIp'>192.168.137.1:50841</td><td class='tdTime'>19:51:33</td><td class='tdBytes'>6 Bytes</td><td class='tdData'>hi all</td></tr>"
				+ "			<tr><td class='tdIp'>192.168.137.1:50854</td><td class='tdTime'>19:51:32</td><td class='tdBytes'>7 Bytes</td><td class='tdData'>hi all#</td></tr>"
				+ "			<tr><td class='tdIp'>192.168.137.1:50818</td><td class='tdTime'>19:51:32</td><td class='tdBytes'>6 Bytes</td><td class='tdData'>hi all</td></tr>"
				+ "			<tr><td class='tdIp'>192.168.137.1:50809</td><td class='tdTime'>19:51:32</td><td class='tdBytes'>6 Bytes</td><td class='tdData'>hi all</td></tr>"
				+ "			<tr><td class='tdIp'>192.168.137.1:50805</td><td class='tdTime'>19:51:32</td><td class='tdBytes'>6 Bytes</td><td class='tdData'>hi all</td></tr>"
				+ "			<tr><td class='tdIp'>192.168.137.1:50796</td><td class='tdTime'>19:51:32</td><td class='tdBytes'>6 Bytes</td><td class='tdData'>hi all</td></tr>" );*/
				 
	}//end of main method
	/**************************************************            Business Logic Starts            *********************************************/
	/****************************************************************
	 * This method parses the data into a List 
	 * @param strReqData
	 * @return HashMap<String, ArrayList<ParamWiseRecord>>
	 * @author ranavir
	 * @date 27052016
	 ***************************************************************/
	public ArrayList<MeterDataModel> getDataList(String strReqData) {
		String methodname = "getDataMap" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		ArrayList<MeterDataModel> alMeterDataModels = null ;
		MeterDataModel objMeterDataModel = null ;
		ArrayList<String> alId = new ArrayList<String>() ;
		
		//System.out.println("strReqData: " +strReqData+" length: "+strReqData.length() );
		strReqData = strReqData.substring(strReqData.indexOf(" ") + 1) ;//skip ip address
		logger.info("ReqData length: "+strReqData.length() );
		//System.out.println("strReqData: " +strReqData+" length: "+strReqData.length() );
		
		
		
		
		//Get length of the String data and divide by 440 to get the no of meters
		int iNoOfMeters = strReqData.length() / 440 ;
		logger.info("iNoOfMeters :: "+iNoOfMeters);
		for( int i = 0 ; i < iNoOfMeters ; i++ ){
			//Get the  meter id from string
			alId.add(strReqData.substring(i * 440 , i * 440 + 2)) ;
		}
		System.out.println(alId);
		if(alId.size() > 0 ){
			alMeterDataModels = new ArrayList<MeterDataModel>() ;
		}
		/* Populate the map with the values */
		for (String id : alId) {
			if( id.equalsIgnoreCase("01")){
				//System.out.println("ID 01 Getting values...");
				objMeterDataModel = new MeterDataModel() ;
				
				//get the values populated
				objMeterDataModel.setMeter_id("01");
				objMeterDataModel.setVR(getVR(strReqData,121,8,1000).getDecval());
				objMeterDataModel.setVY(getVY(strReqData,129,8,1001).getDecval());
				objMeterDataModel.setVB(getVB(strReqData,137,8,1002).getDecval());
				objMeterDataModel.setIR(getIR(strReqData,145,8,0.02).getDecval());
				objMeterDataModel.setIY(getIY(strReqData,153,8,0.03).getDecval());
				objMeterDataModel.setIB(getIB(strReqData,161,8,0.04).getDecval());
				objMeterDataModel.setKW(getKW(strReqData,169,8).getDecval());
				objMeterDataModel.setKV(getKV(strReqData,177,8).getDecval());
				objMeterDataModel.setPF(getPF(strReqData,201,4).getDecval());
				objMeterDataModel.setFQ(getFQ(strReqData,205,4,1000).getDecval());
				objMeterDataModel.setAI(getActiveImport(strReqData,239,8,10).getDecval());
				objMeterDataModel.setAE(getActiveExport(strReqData,247,8,10).getDecval());
				
				//add to list
				alMeterDataModels.add(objMeterDataModel) ;
				
				
			}else if( id.equalsIgnoreCase("07")){
				//System.out.println("ID 07 Getting values...");
				objMeterDataModel = new MeterDataModel() ;
				
				//get the values populated
				objMeterDataModel.setMeter_id("07");
				objMeterDataModel.setVR(getVR(strReqData,561,8,1000).getDecval());
				objMeterDataModel.setVY(getVY(strReqData,569,8,1001).getDecval());
				objMeterDataModel.setVB(getVB(strReqData,577,8,1002).getDecval());
				objMeterDataModel.setIR(getIR(strReqData,585,8,0.02).getDecval());
				objMeterDataModel.setIY(getIY(strReqData,593,8,0.03).getDecval());
				objMeterDataModel.setIB(getIB(strReqData,601,8,0.04).getDecval());
				objMeterDataModel.setKW(getKW(strReqData,609,8).getDecval());
				objMeterDataModel.setKV(getKV(strReqData,617,8).getDecval());
				objMeterDataModel.setPF(getPF(strReqData,641,4).getDecval());
				objMeterDataModel.setFQ(getFQ(strReqData,645,4,1000).getDecval());
				objMeterDataModel.setAI(getActiveImport(strReqData,679,8,10).getDecval());
				objMeterDataModel.setAE(getActiveExport(strReqData,687,8,10).getDecval());
				
				//add to list
				alMeterDataModels.add(objMeterDataModel) ;
				
			}else if( id.equalsIgnoreCase("09")){
				//System.out.println("ID 09 Getting values...");
				objMeterDataModel = new MeterDataModel() ;
				
				//get the values populated
				objMeterDataModel.setMeter_id("09");
				objMeterDataModel.setVR(getVR(strReqData,1001,8,1000).getDecval());
				objMeterDataModel.setVY(getVY(strReqData,1009,8,1000).getDecval());
				objMeterDataModel.setVB(getVB(strReqData,1017,8,1000).getDecval());
				objMeterDataModel.setIR(getIR(strReqData,1025,8,0.02).getDecval());
				objMeterDataModel.setIY(getIY(strReqData,1033,8,0.02).getDecval());
				objMeterDataModel.setIB(getIB(strReqData,1041,8,0.02).getDecval());
				objMeterDataModel.setKW(getKW(strReqData,1049,8).getDecval());
				objMeterDataModel.setKV(getKV(strReqData,1057,8).getDecval());
				objMeterDataModel.setPF(getPF(strReqData,1081,4).getDecval());
				objMeterDataModel.setFQ(getFQ(strReqData,1085,4,1000).getDecval());
				objMeterDataModel.setAI(getActiveImport(strReqData,1119,8,10).getDecval());
				objMeterDataModel.setAE(getActiveExport(strReqData,1127,8,10).getDecval());
				
				//add to list
				alMeterDataModels.add(objMeterDataModel) ;
				
			}
		}//end iterating all keys list
		
		
		logger.info("EXIT---> methodname : "+methodname);
		return alMeterDataModels;
	}//end of getDataList
	
	/****************************************************************
	 * Gets the VR value
	 * @param strReqData
	 * @param iStartIndex
	 * @param iBytes
	 * @param iFactor
	 * @return
	 ***************************************************************/
	private static ParamWiseRecord getVR(String strReqData, int iStartIndex, int iBytes, int iFactor) {
		String methodname = "getVR" ;
		logger.info("ENTRY---> methodname : "+methodname);
		ParamWiseRecord objParamWiseRecord = new ParamWiseRecord() ;
		objParamWiseRecord.setParam("VR");
		objParamWiseRecord.setFactor("/"+iFactor);
		objParamWiseRecord.setHexval(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)));
		objParamWiseRecord.setDecval(getDivDecimalValue(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)) , iFactor));
		objParamWiseRecord.setStartIndex(iStartIndex);
		objParamWiseRecord.setBytesRead(iBytes);
		
		logger.info("EXIT---> methodname : "+methodname);
		return objParamWiseRecord;
	}//end of getVR method
	/****************************************************************
	 * Gets the VY value
	 * @param strReqData
	 * @param iStartIndex
	 * @param iBytes
	 * @param iFactor
	 * @return
	 ***************************************************************/
	private static ParamWiseRecord getVY(String strReqData, int iStartIndex, int iBytes, int iFactor) {
		String methodname = "getVY" ;
		logger.info("ENTRY---> methodname : "+methodname);
		ParamWiseRecord objParamWiseRecord = new ParamWiseRecord() ;
		objParamWiseRecord.setParam("VY");
		objParamWiseRecord.setFactor("/"+iFactor);
		objParamWiseRecord.setHexval(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)));
		objParamWiseRecord.setDecval(getDivDecimalValue(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)),iFactor));
		objParamWiseRecord.setStartIndex(iStartIndex);
		objParamWiseRecord.setBytesRead(iBytes);
		
		logger.info("EXIT---> methodname : "+methodname);
		return objParamWiseRecord;
	}//end of getVY method
	
	
	/****************************************************************
	 * Gets the VB value
	 * @param strReqData
	 * @param iStartIndex
	 * @param iBytes
	 * @param iFactor
	 * @return
	 ***************************************************************/
	private static ParamWiseRecord getVB(String strReqData, int iStartIndex, int iBytes, int iFactor) {
		String methodname = "getVB" ;
		logger.info("ENTRY---> methodname : "+methodname);
		ParamWiseRecord objParamWiseRecord = new ParamWiseRecord() ;
		objParamWiseRecord.setParam("VB");
		objParamWiseRecord.setFactor("/"+iFactor);
		objParamWiseRecord.setHexval(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)));
		objParamWiseRecord.setDecval(getDivDecimalValue(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)),iFactor));
		objParamWiseRecord.setStartIndex(iStartIndex);
		objParamWiseRecord.setBytesRead(iBytes);
		
		logger.info("EXIT---> methodname : "+methodname);
		return objParamWiseRecord;
	}//end of getVB method
	/****************************************************************
	 * Gets the IR value
	 * @param strReqData
	 * @param iStartIndex
	 * @param iBytes
	 * @param iFactor
	 * @return
	 ***************************************************************/
	private static ParamWiseRecord getIR(String strReqData, int iStartIndex, int iBytes, double iFactor) {
		String methodname = "getIR" ;
		logger.info("ENTRY---> methodname : "+methodname);
		ParamWiseRecord objParamWiseRecord = new ParamWiseRecord() ;
		objParamWiseRecord.setParam("IR");
		objParamWiseRecord.setFactor("*"+iFactor);
		objParamWiseRecord.setHexval(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)));
		objParamWiseRecord.setDecval(getMultDecimalValue(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)),iFactor));
		objParamWiseRecord.setStartIndex(iStartIndex);
		objParamWiseRecord.setBytesRead(iBytes);
		
		logger.info("EXIT---> methodname : "+methodname);
		return objParamWiseRecord;
	}//end of getIR method
	/****************************************************************
	 * Gets the IY value
	 * @param strReqData
	 * @param iStartIndex
	 * @param iBytes
	 * @param iFactor
	 * @return
	 ***************************************************************/
	private static ParamWiseRecord getIY(String strReqData, int iStartIndex, int iBytes, double iFactor) {
		String methodname = "getIY" ;
		logger.info("ENTRY---> methodname : "+methodname);
		ParamWiseRecord objParamWiseRecord = new ParamWiseRecord() ;
		objParamWiseRecord.setParam("IY");
		objParamWiseRecord.setFactor("*"+iFactor);
		objParamWiseRecord.setHexval(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)));
		objParamWiseRecord.setDecval(getMultDecimalValue(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)),iFactor));
		objParamWiseRecord.setStartIndex(iStartIndex);
		objParamWiseRecord.setBytesRead(iBytes);
		
		logger.info("EXIT---> methodname : "+methodname);
		return objParamWiseRecord;
	}//end of getIY method
	
	/****************************************************************
	 * Gets the IB value
	 * @param strReqData
	 * @param iStartIndex
	 * @param iBytes
	 * @param iFactor
	 * @return
	 ***************************************************************/
	private static ParamWiseRecord getIB(String strReqData, int iStartIndex, int iBytes, double iFactor) {
		String methodname = "getIB" ;
		logger.info("ENTRY---> methodname : "+methodname);
		ParamWiseRecord objParamWiseRecord = new ParamWiseRecord() ;
		objParamWiseRecord.setParam("IB");
		objParamWiseRecord.setFactor("*"+iFactor);
		objParamWiseRecord.setHexval(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)));
		objParamWiseRecord.setDecval(getMultDecimalValue(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)),iFactor));
		objParamWiseRecord.setStartIndex(iStartIndex);
		objParamWiseRecord.setBytesRead(iBytes);
		
		logger.info("EXIT---> methodname : "+methodname);
		return objParamWiseRecord;
	}//end of getVR method
	/****************************************************************
	 * Gets the FQ value
	 * @param strReqData
	 * @param iStartIndex
	 * @param iBytes
	 * @param iFactor
	 * @return
	 ***************************************************************/
	private static ParamWiseRecord getFQ(String strReqData, int iStartIndex, int iBytes, int iFactor) {
		String methodname = "getFQ" ;
		logger.info("ENTRY---> methodname : "+methodname);
		ParamWiseRecord objParamWiseRecord = new ParamWiseRecord() ;
		objParamWiseRecord.setParam("FQ");
		objParamWiseRecord.setFactor("/"+iFactor);
		objParamWiseRecord.setHexval(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)));
		objParamWiseRecord.setDecval(getDivDecimalValue(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)),iFactor));
		objParamWiseRecord.setStartIndex(iStartIndex);
		objParamWiseRecord.setBytesRead(iBytes);
		
		logger.info("EXIT---> methodname : "+methodname);
		return objParamWiseRecord;
	}//end of getFQ method
	/****************************************************************
	 * Gets the ActiveImport value
	 * @param strReqData
	 * @param iStartIndex
	 * @param iBytes
	 * @param iFactor
	 * @return
	 ***************************************************************/
	private static ParamWiseRecord getActiveImport(String strReqData, int iStartIndex, int iBytes, int iFactor) {
		String methodname = "getActiveImport" ;
		logger.info("ENTRY---> methodname : "+methodname);
		ParamWiseRecord objParamWiseRecord = new ParamWiseRecord() ;
		objParamWiseRecord.setParam("Active Import");
		objParamWiseRecord.setFactor("*"+iFactor);
		objParamWiseRecord.setHexval(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)));
		objParamWiseRecord.setDecval(getAIAEValue(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)),iFactor));
		objParamWiseRecord.setStartIndex(iStartIndex);
		objParamWiseRecord.setBytesRead(iBytes);
		
		logger.info("EXIT---> methodname : "+methodname);
		return objParamWiseRecord;
	}//end of getActiveImport method
	/****************************************************************
	 * Gets the getActiveExport value
	 * @param strReqData
	 * @param iStartIndex
	 * @param iBytes
	 * @param iFactor
	 * @return
	 ***************************************************************/
	private static ParamWiseRecord getActiveExport(String strReqData, int iStartIndex, int iBytes, int iFactor) {
		String methodname = "getActiveExport" ;
		logger.info("ENTRY---> methodname : "+methodname);
		ParamWiseRecord objParamWiseRecord = new ParamWiseRecord() ;
		objParamWiseRecord.setParam("Active Export");
		objParamWiseRecord.setFactor("*"+iFactor);
		objParamWiseRecord.setHexval(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)));
		objParamWiseRecord.setDecval(getAIAEValue(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)),iFactor));
		objParamWiseRecord.setStartIndex(iStartIndex);
		objParamWiseRecord.setBytesRead(iBytes);
		
		logger.info("EXIT---> methodname : "+methodname);
		return objParamWiseRecord;
	}//end of getActiveExport method
	/****************************************************************
	 * Gets the KW value
	 * @param strReqData
	 * @param iStartIndex
	 * @param iBytes
	 * @return
	 ***************************************************************/
	private static ParamWiseRecord getKW(String strReqData, int iStartIndex, int iBytes) {
		String methodname = "getKW" ;
		logger.info("ENTRY---> methodname : "+methodname);
		ParamWiseRecord objParamWiseRecord = new ParamWiseRecord() ;
		objParamWiseRecord.setParam("KW");
		objParamWiseRecord.setFactor("");//Not Required
		objParamWiseRecord.setHexval(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)));
		objParamWiseRecord.setDecval(getKWKVDecimalValue(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1))));
		objParamWiseRecord.setStartIndex(iStartIndex);
		objParamWiseRecord.setBytesRead(iBytes);
		
		logger.info("EXIT---> methodname : "+methodname);
		return objParamWiseRecord;
	}//end of getKW method
	/****************************************************************
	 * Gets the KV value
	 * @param strReqData
	 * @param iStartIndex
	 * @param iBytes
	 * @return
	 ***************************************************************/
	private static ParamWiseRecord getKV(String strReqData, int iStartIndex, int iBytes) {
		String methodname = "getKV" ;
		logger.info("ENTRY---> methodname : "+methodname);
		ParamWiseRecord objParamWiseRecord = new ParamWiseRecord() ;
		objParamWiseRecord.setParam("KV");
		objParamWiseRecord.setFactor("");//Not Required
		objParamWiseRecord.setHexval(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)));
		objParamWiseRecord.setDecval(getKWKVDecimalValue(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1))));
		objParamWiseRecord.setStartIndex(iStartIndex);
		objParamWiseRecord.setBytesRead(iBytes);
		
		logger.info("EXIT---> methodname : "+methodname);
		return objParamWiseRecord;
	}//end of getKV method
	/****************************************************************
	 * Gets the PF value
	 * @param strReqData
	 * @param iStartIndex
	 * @param iBytes
	 * @return
	 ***************************************************************/
	private static ParamWiseRecord getPF(String strReqData, int iStartIndex, int iBytes) {
		String methodname = "getPF" ;
		logger.info("ENTRY---> methodname : "+methodname);
		ParamWiseRecord objParamWiseRecord = new ParamWiseRecord() ;
		objParamWiseRecord.setParam("PF");
		objParamWiseRecord.setFactor("");//Not Required
		objParamWiseRecord.setHexval(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1)));
		objParamWiseRecord.setDecval(getPFDecimalvalue(strReqData.substring(iStartIndex - 1, iStartIndex + (iBytes - 1))));
		objParamWiseRecord.setStartIndex(iStartIndex);
		objParamWiseRecord.setBytesRead(iBytes);
		
		logger.info("EXIT---> methodname : "+methodname);
		return objParamWiseRecord;
	}//end of getPF method
	/****************************************************************
	 * This method creates the table design from
	 * the data of the list and stores in a String
	 * @param ArrayList<MeterDataModel>
	 * @return formatted String
	 ***************************************************************/
	public static String getFormattedData(
			ArrayList<MeterDataModel> alMeterDataModels) {
		String methodname = "getFormattedData" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		StringBuilder sb = new StringBuilder() ;
		for (MeterDataModel meterDataModel : alMeterDataModels) {
			sb.append("<tr>"+
						"<td>"+meterDataModel.getMeter_id()+"</td>"+
						"<td>"+meterDataModel.getFQ()+"</td>"+
						"<td>"+meterDataModel.getVR()+"</td>"+
						"<td>"+meterDataModel.getVY()+"</td>"+
						"<td>"+meterDataModel.getVB()+"</td>"+
						"<td>"+meterDataModel.getIR()+"</td>"+
						"<td>"+meterDataModel.getIY()+"</td>"+
						"<td>"+meterDataModel.getIB()+"</td>"+
						"<td>"+meterDataModel.getKW()+"</td>"+
						"<td>"+meterDataModel.getKV()+"</td>"+
						"<td>"+meterDataModel.getPF()+"</td>"+
						"<td>"+meterDataModel.getAI()+"</td>"+
						"<td>"+meterDataModel.getAE()+"</td>"+
					"</tr>");
		}//end of for loop 
		
		/*<tr>
		<td>01</td>
		<td>49.996</td>
		<td>129.674</td>
		<td>130.818</td>
		<td>130.788</td>
		<td>267.52</td>
		<td>297.82</td>
		<td>265.04</td>
		<td>104.538</td>
		<td>133.99</td>
		<td>-0.968</td>
		<td>3101100</td>
		<td>3460680</td>
	</tr>*/
		
		logger.info("EXIT---> methodname : "+methodname);
		return sb.toString();
	}//end of getFormattedData
	/****************************************************************
	 * This method creates the table design from
	 * the data of the list and stores in a String
	 * @param ArrayList<MeterDataModel>
	 * @return formatted String
	 * @author ranavir
	 * @date 27052016
	 ***************************************************************/
	public static String getFormattedHistoryData(
			ArrayList<MeterDataModel> alMeterDataModels) {
		String methodname = "getFormattedHistoryData" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		StringBuilder sb = new StringBuilder() ;
		for (MeterDataModel meterDataModel : alMeterDataModels) {
			sb.append("<tr>"+
						"<td>"+meterDataModel.getTimestamp()+"</td>"+
						"<td>"+meterDataModel.getFQ()+"</td>"+
						"<td>"+meterDataModel.getVR()+"</td>"+
						"<td>"+meterDataModel.getVY()+"</td>"+
						"<td>"+meterDataModel.getVB()+"</td>"+
						"<td>"+meterDataModel.getIR()+"</td>"+
						"<td>"+meterDataModel.getIY()+"</td>"+
						"<td>"+meterDataModel.getIB()+"</td>"+
						"<td>"+meterDataModel.getKW()+"</td>"+
						"<td>"+meterDataModel.getKV()+"</td>"+
						"<td>"+meterDataModel.getPF()+"</td>"+
						"<td>"+meterDataModel.getAI()+"</td>"+
						"<td>"+meterDataModel.getAE()+"</td>"+
					"</tr>");
		}//end of for loop 
		logger.info("EXIT---> methodname : "+methodname);
		return sb.toString();
	}//end of getFormattedData
	
	/****************************************************************************************************************************
	 * 													Few Helper methods
	 ****************************************************************************************************************************/
	private static BigDecimal getDivDecimalValue(String str, int iFactor) {
		
		double bdResult = 0.0 ;
		bdResult = Integer.parseInt(str.trim(), 16 ) / (double)iFactor ;
		BigDecimal bd = new BigDecimal(bdResult, new MathContext(6)) ; 
		return bd ;
	}//end of getDecimalValue
	private static BigDecimal getMultDecimalValue(String str, double iFactor) {
		
		double bdResult = 0.0 ;
		bdResult = Integer.parseInt(str.trim(), 16 ) * iFactor ;
		BigDecimal bd = new BigDecimal(bdResult, new MathContext(6)) ; 
		return bd ;
	}//end of getDecimalValue
	private static BigDecimal getKWKVDecimalValue(String str) {
		
		double bdResult = 0.0 ;
		BigInteger bi = new BigInteger(str, 16)  ;
		if( bi.longValue() >  Integer.parseInt("7FFFFFFF", 16 )){
			
			bdResult = ( bi.doubleValue() - new BigInteger("FFFFFFFF", 16).doubleValue() ) / 500 * -1;
		}else{
			bdResult = bi.doubleValue() / 500 ;
		}
		BigDecimal bd = new BigDecimal(bdResult, new MathContext(6)) ; 
		return bd ;
	}//end of getDecimalValue getPFDecimalvalue
	private static BigDecimal getPFDecimalvalue(String str) {
		
		double bdResult = 0.0 ;
		bdResult = (Integer.parseInt(str.trim(), 16 ) - 65535 ) / (double)1000 ;
		BigDecimal bd = new BigDecimal(bdResult, new MathContext(3)) ; 
		return bd ;
	}//end of getDecimalValue
	private static BigDecimal getAIAEValue(String str, double iFactor) {
		
		double bdResult = 0.0 ;
		bdResult = Integer.parseInt(str.trim(), 16 ) * iFactor ;
		//System.out.println(bdResult);
		BigDecimal bd = new BigDecimal(bdResult) ; 
		return bd ;
	}//end of getDecimalValue
	/**************************************************            Business Logic Ends              *********************************************/



	
	
}//end of MeterDataHelper Class













/****************************************************************
 * This method parses the data into a map and stores with 
 * IDs as the key of the map
 * @param strReqData
 * @return HashMap<String, ArrayList<ParamWiseRecord>>
 * @author ranavir
 * @date 25052016
 ***************************************************************/
/*public static TreeMap<String, ArrayList<ParamWiseRecord>> getDataMap(
		String strReqData) {
	String methodname = "getDataMap" ;
	logger.info("ENTRY---> methodname : "+methodname);
	
	//System.out.println(strReqData);
	//trim the first contents /192.168.0.1:49184 01
	strReqData = strReqData.substring(strReqData.indexOf(" ") + 1) ;
	//System.out.println(strReqData);
	
	TreeMap<String, ArrayList<ParamWiseRecord>> tm = new TreeMap<String, ArrayList<ParamWiseRecord>>() ;
	ArrayList<String> alId = new ArrayList<String>() ;
	ArrayList<ParamWiseRecord> alParamWiseRecords = null ;
	ParamWiseRecord objVRRecord = null ;
	ParamWiseRecord objVYRecord = null ;
	ParamWiseRecord objVBRecord = null ;
	ParamWiseRecord objIRRecord = null ;
	ParamWiseRecord objIYRecord = null ;
	ParamWiseRecord objIBRecord = null ;
	ParamWiseRecord objKWRecord = null ;
	ParamWiseRecord objKVRecord = null ;
	ParamWiseRecord objPFRecord = null ;
	ParamWiseRecord objFQRecord = null ;
	ParamWiseRecord objAIRecord = null ;
	ParamWiseRecord objAERecord = null ;
	
	//Get length of the String data and divide by 440 to get the no of meters
	int iNoOfMeters = strReqData.length() / 440 ;
	logger.info("iNoOfMeters :: "+iNoOfMeters);
	for( int i = 0 ; i < iNoOfMeters ; i++ ){
		//Get the  meter id from string
		alId.add(strReqData.substring(i * 440 , i * 440 + 2)) ;
	}
	System.out.println(alId);
	 Populate the map with the values 
	for (String id : alId) {
		if( id.equalsIgnoreCase("01")){
			//System.out.println("ID 01 Getting values...");
			alParamWiseRecords = new ArrayList<ParamWiseRecord>() ;
			
			//get the param values as records into param list
			objVRRecord = getVR(strReqData,121,8,1000) ;
			objVYRecord = getVY(strReqData,129,8,1001) ;
			objVBRecord = getVB(strReqData,137,8,1002) ;
			objIRRecord = getIR(strReqData,145,8,0.02) ;
			objIYRecord = getIY(strReqData,153,8,0.03) ;
			objIBRecord = getIB(strReqData,161,8,0.04) ; 
			objKWRecord = getKW(strReqData,169,8) ; 
			objKVRecord = getKV(strReqData,177,8) ;
			objPFRecord = getPF(strReqData,201,4) ; 
			objFQRecord = getFQ(strReqData,205,4,1000) ; 
			objAIRecord = getActiveImport(strReqData,239,8,10) ;
			objAERecord = getActiveExport(strReqData,247,8,10) ;
			
			alParamWiseRecords.add(objVRRecord) ;
			alParamWiseRecords.add(objVYRecord);
			alParamWiseRecords.add(objVBRecord);
			alParamWiseRecords.add(objIRRecord);
			alParamWiseRecords.add(objIYRecord);
			alParamWiseRecords.add(objIBRecord);
			alParamWiseRecords.add(objKWRecord);
			alParamWiseRecords.add(objKVRecord);
			alParamWiseRecords.add(objPFRecord);
			alParamWiseRecords.add(objFQRecord);
			alParamWiseRecords.add(objAIRecord);
			alParamWiseRecords.add(objAERecord);
			//populate the map with key and value as param list
			tm.put(id,alParamWiseRecords) ;
		}else if( id.equalsIgnoreCase("07")){
			//System.out.println("ID 07 Getting values...");
			alParamWiseRecords = new ArrayList<ParamWiseRecord>() ;
			
			//get the param values as records into param list
			objVRRecord = getVR(strReqData,561,8,1000) ;
			objVYRecord = getVY(strReqData,569,8,1001) ;
			objVBRecord = getVB(strReqData,577,8,1002) ;
			objIRRecord = getIR(strReqData,585,8,0.02) ;
			objIYRecord = getIY(strReqData,593,8,0.03) ;
			objIBRecord = getIB(strReqData,601,8,0.04); 
			objKWRecord = getKW(strReqData,609,8) ; 
			objKVRecord = getKV(strReqData,617,8) ;
			objPFRecord = getPF(strReqData,641,4) ; 
			objFQRecord = getFQ(strReqData,645,4,1000) ; 
			objAIRecord = getActiveImport(strReqData,679,8,1) ;
			objAERecord = getActiveExport(strReqData,687,8,10) ;
			
			alParamWiseRecords.add(objVRRecord) ;
			alParamWiseRecords.add(objVYRecord);
			alParamWiseRecords.add(objVBRecord);
			alParamWiseRecords.add(objIRRecord);
			alParamWiseRecords.add(objIYRecord);
			alParamWiseRecords.add(objIBRecord);
			alParamWiseRecords.add(objKWRecord);
			alParamWiseRecords.add(objKVRecord);
			alParamWiseRecords.add(objPFRecord);
			alParamWiseRecords.add(objFQRecord);
			alParamWiseRecords.add(objAIRecord);
			alParamWiseRecords.add(objAERecord);
			//populate the map with key and value as param list
			tm.put(id,alParamWiseRecords) ;
		}else if( id.equalsIgnoreCase("09")){
			//System.out.println("ID 09 Getting values...");
			alParamWiseRecords = new ArrayList<ParamWiseRecord>() ;
			
			//get the param values as records into param list
			objVRRecord = getVR(strReqData,1001,8,1000) ;
			objVYRecord = getVY(strReqData,1009,8,1000) ;
			objVBRecord = getVB(strReqData,1017,8,1000) ;
			objIRRecord = getIR(strReqData,1025,8,0.02) ;
			objIYRecord = getIY(strReqData,1033,8,0.02) ;
			objIBRecord = getIB(strReqData,1041,8,0.02) ; 
			objKWRecord = getKW(strReqData,1049,8) ; 
			objKVRecord = getKV(strReqData,1057,8) ;
			objPFRecord = getPF(strReqData,1081,4) ; 
			objFQRecord = getFQ(strReqData,1085,4,1000) ; 
			objAIRecord = getActiveImport(strReqData,1119,8,1) ;
			objAERecord = getActiveExport(strReqData,1127,8,100) ;
			
			alParamWiseRecords.add(objVRRecord) ;
			alParamWiseRecords.add(objVYRecord);
			alParamWiseRecords.add(objVBRecord);
			alParamWiseRecords.add(objIRRecord);
			alParamWiseRecords.add(objIYRecord);
			alParamWiseRecords.add(objIBRecord);
			alParamWiseRecords.add(objKWRecord);
			alParamWiseRecords.add(objKVRecord);
			alParamWiseRecords.add(objPFRecord);
			alParamWiseRecords.add(objFQRecord);
			alParamWiseRecords.add(objAIRecord);
			alParamWiseRecords.add(objAERecord);
			//populate the map with key and value as param list
			tm.put(id,alParamWiseRecords) ;
		}
	}//end iterating all keys list
	
	
	logger.info("EXIT---> methodname : "+methodname);
	return tm ;
}//end of getDataMap
*/
/****************************************************************
 * This method creates the table design from
 * the data of the map and stores in a String
 * @param hm
 * @return formatted String
 ***************************************************************/
/*public static String getFormattedOutput(
		TreeMap<String, ArrayList<ParamWiseRecord>> tm) {
	String methodname = "getFormattedOutput" ;
	logger.info("ENTRY---> methodname : "+methodname);
	
	StringBuilder sb = new StringBuilder() ;
	ArrayList<ParamWiseRecord> alParamWiseRecords = null ;
	Iterator<Map.Entry<String, ArrayList<ParamWiseRecord>>> entries = tm.entrySet().iterator();
	while (entries.hasNext()) {
		//Get one entry from map
	    Map.Entry<String, ArrayList<ParamWiseRecord>> entry = entries.next();
	    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
	    
	    //Populate a table row for the header with ID
	    sb.append("<tr>"+
	    			"<td style='text-align:left;'>factor</td>"+
	    			"<td class='dataId' style='text-align:left;'>For ID:"+entry.getKey()+"</td>"+
	    			"<td colspan='4' class='dataHeader' ></td>"+
	    		  "</tr>") ;
	    //Get the ArrayList and iterate to get the param records and populate table rows
	    alParamWiseRecords = entry.getValue() ;
	    for (ParamWiseRecord paramWiseRecord : alParamWiseRecords) {//if param is Active Import / Active Export do the font color #0000FF
	    	sb.append("<tr>"+
	    					((paramWiseRecord.getParam().equalsIgnoreCase("Active Import") || paramWiseRecord.getParam().equalsIgnoreCase("Active Export"))?"<td style='text-align:left;color:#0000FF;'>"+paramWiseRecord.getFactor()+"</td>" :"<td style='text-align:left;'>"+paramWiseRecord.getFactor()+"</td>") +
	    					"<td style='text-align:left;'>"+paramWiseRecord.getParam()+"</td>"+
	    					"<td style='text-align:left;'>"+paramWiseRecord.getHexval()+"</td>"+
	    					"<td style='text-align:right;'>"+paramWiseRecord.getDecval()+"</td>"+
	    					"<td style='text-align:right;'>"+paramWiseRecord.getStartIndex()+"</td>"+
	    					"<td style='text-align:right;'>"+paramWiseRecord.getBytesRead()+"</td>"+
	    			  "</tr>");
		}//end for loop ArrayList iteration
	    
	    //here save the record into database
	    
	}//end while Map iteration
	logger.info("EXIT---> methodname : "+methodname);
	return sb.toString();
}//end of getFormattedOutput*/