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
package com.stl.tcpweb.serviceImpl;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.stl.tcpweb.dao.MeterDataDao;
import com.stl.tcpweb.dto.MeterDataModel;
import com.stl.tcpweb.service.MeterDataService;
import com.stl.tcpweb.util.ProjectConstants;
import com.stl.tcpweb.util.Utils;
/*************************************************************
 * @author Ranvir
 * Date : May 24, 2016
 *************************************************************/
public class MeterDataServiceImpl implements MeterDataService {
	private static MeterDataServiceImpl mdServiceImpl = null;
	private static MeterDataDao mdDao = null ;
	private static Logger logger = null;
	String methodname="";
	String userId = "";
	
	
	public MeterDataServiceImpl() {
		logger = Logger.getLogger(MeterDataServiceImpl.class) ;
		mdDao = (MeterDataDao)Utils.getDao(ProjectConstants.METER_DATA_DAO) ;
	}
	
	public static MeterDataServiceImpl getInstance(){
		if(mdServiceImpl == null){
			mdServiceImpl = new MeterDataServiceImpl();
			logger.info("MeterDataServiceImpl Instantiated...");
		}
		return mdServiceImpl;
	}
	
	
	/**************************************************            Business Logic Starts            *********************************************/
	/*********************************************
	 * This method saves meter data to database
	 * @param alMeterDataModels
	 * @author ranavir
	 * @date 27052016
	 ********************************************/
	@Override
	public void saveMeterData(ArrayList<MeterDataModel> alMeterDataModels) {
		String methodname = "saveMeterData" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		mdDao.saveMeterData(alMeterDataModels);
		
		logger.info("EXIT---> methodname : "+methodname);
	}//end of saveMeterData
	/*********************************************
	 * This method fetches  meter history data from 
	 * database by meter id and date intervals
	 * @param fromdate
	 * @param todate
	 * @param meterid
	 * @author ranavir
	 * @date 27052016
	 ********************************************/
	@Override
	public ArrayList<MeterDataModel> fetchHistoryData(String fd, String td,
			String mId) {
		String methodname = "fetchHistoryData" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		ArrayList<MeterDataModel> alMeterDataModels = null ;
		alMeterDataModels = mdDao.fetchHistoryData(fd,td,mId);
		
		logger.info("EXIT---> methodname : "+methodname);
		return alMeterDataModels ;
	}//end of fetchHistoryData
	
	/**************************************************            Business Logic Ends              *********************************************/
}
