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
 * @date May 23, 2016
 *
 */
public class MysqlDaoFactory extends DaoFactory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * This method is used to get Mysql DB Connection
	 * 
	 * @return Connection
	 */
	public  Connection getConnection() {
		Connection conn = null;
		try {

			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			conn = DriverManager.getConnection("jdbc:mysql://182.156.93.60:3306/hlc_parking_v1", "root", "passw0rd");//182.156.93.61
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
	/*public static void main(String...args){
		new MyOracleDaoFactory().getConnection() ;
	}*/
	        /*'database_type' => 'mysql',
	        'database_name' => 'hlc_parking_v1',
	        'server' => '182.156.93.60',
	        'username' => 'root',
	        'password' => 'passw0rd',
	        'port'=> '3306'*/
		
}
