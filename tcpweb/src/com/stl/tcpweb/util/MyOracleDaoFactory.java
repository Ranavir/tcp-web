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
package com.stl.tcpweb.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




/**
 * @author Ranavir
 * @description
 * @date Apr 24, 2016
 *
 */
public class MyOracleDaoFactory extends DaoFactory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * This method is used to get Oracle DB Connection
	 * 
	 * @return Connection
	 */
	public  Connection getConnection() {
		Connection conn = null;
		try {

			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@182.156.93.61:1521/XE", "EVALUATDB", "password");//182.156.93.61
			if (conn != null) {
				System.out.println("Connection Established Successfully");
			}

		} catch (SQLException sqe) {
			System.out.println("Not able to Establish Connection ");
		} catch (Exception exe) {
			System.out.println("Not able to Establish Connection ");
		}
		return conn;
	}


		
}
