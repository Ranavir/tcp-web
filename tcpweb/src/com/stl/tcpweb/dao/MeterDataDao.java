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
package com.stl.tcpweb.dao;

import java.io.Serializable;
import java.util.ArrayList;

import com.stl.tcpweb.dto.MeterDataModel;
/**
 * @author Ranvir
 *
 */
public interface MeterDataDao extends Serializable{
	/*********************************************
	 * This method saves meter data to database
	 * @param alMeterDataModels
	 * @author ranavir
	 * @date 27052016
	 ********************************************/
	void saveMeterData(ArrayList<MeterDataModel> alMeterDataModels);
	/*********************************************
	 * This method fetches  meter history data from 
	 * database by meter id and date intervals
	 * @param fromdate
	 * @param todate
	 * @param meterid
	 * @author ranavir
	 * @date 27052016
	 ********************************************/
	ArrayList<MeterDataModel> fetchHistoryData(String fd, String td, String mId);
	
	
}
