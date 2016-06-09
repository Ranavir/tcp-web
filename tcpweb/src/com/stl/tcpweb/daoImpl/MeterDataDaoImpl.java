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
package com.stl.tcpweb.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.stl.tcpweb.dao.MeterDataDao;
import com.stl.tcpweb.dto.MeterDataModel;
import com.stl.tcpweb.util.DaoFactory;
import com.stl.tcpweb.util.ProjectConstants;

/**************************************************************
 * @author Ranvir
 * Date : May 24, 2016
 **************************************************************/
public class MeterDataDaoImpl implements MeterDataDao {
	private static MeterDataDaoImpl mdDaoImpl = null;
	private static DaoFactory daoFactory ;
	private static Logger logger = null;
	String methodname="";
	String userId = "";
	
	public MeterDataDaoImpl() {
		logger = Logger.getLogger(MeterDataDaoImpl.class) ;
		daoFactory = DaoFactory.getDAOFactory(ProjectConstants.POSTGRESQL_DAO_FACTORY);
	}
	
	public static MeterDataDaoImpl getInstance(){
		if(mdDaoImpl == null){
			mdDaoImpl = new MeterDataDaoImpl();
			logger.info("MeterDataDaoImpl Instantiated...");
		}
		return mdDaoImpl;
	}
	
	public static void main(String[] args) {
		getInstance();
		daoFactory.getConnection() ;
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
		methodname = "saveMeterData" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		int iuc[] ;
		Connection con = null;
		PreparedStatement pst = null ;
		String strCondQuery = "" ;
		try{
			con = daoFactory.getConnection();
			if (con != null) {
				
				strCondQuery = ProjectConstants.QUERY_INSERT_METER_DATA ;
				logger.info("query:"+strCondQuery);
				//create the batch queries
				pst = con.prepareStatement(strCondQuery) ;
				
				//Set auto-commit to false
				con.setAutoCommit(false);
				
				//INSERT INTO meter_data(meter_id, VR, VY, VB, IR, IY, IB, KW, KV, PF, FQ, AI, AE, date, timestamp) "+
				//"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_date, current_timestamp)";
				if( null != alMeterDataModels && alMeterDataModels.size() > 0){
					for (MeterDataModel model : alMeterDataModels) {
						// Set the variables
						pst.setString(1, model.getMeter_id());
						pst.setDouble(2, model.getVR().doubleValue());
						pst.setDouble(3, model.getVY().doubleValue());
						pst.setDouble(4, model.getVB().doubleValue());
						pst.setDouble(5, model.getIR().doubleValue());
						pst.setDouble(6, model.getIY().doubleValue());
						pst.setDouble(7, model.getIB().doubleValue());
						pst.setDouble(8, model.getKW().doubleValue());
						pst.setDouble(9, model.getKV().doubleValue());
						pst.setDouble(10, model.getPF().doubleValue());
						pst.setDouble(11, model.getFQ().doubleValue());
						pst.setLong(12, model.getAI().longValue());
						pst.setLong(13, model.getAE().longValue());
						// Add it to the batch
						pst.addBatch();
						
					}//end inner for loop
					
					iuc = pst.executeBatch() ;
					con.commit();
					for (int i : iuc) {
						logger.info("update count::"+i);
					}
				}//end if
			}
		} catch (Exception e) {
			logger.error(e.toString(), e.fillInStackTrace());
			//e.printStackTrace();
		} finally {
			if(pst != null){
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}//end of try / catch
		
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
		methodname = "fetchHistoryData" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		Connection con = null;
		PreparedStatement pst = null ;
		ResultSet rs = null ;
		String strQuery = "" ;
		ArrayList<MeterDataModel> alMeterDataModels = new ArrayList<MeterDataModel>() ;
		MeterDataModel objDataModel = null ;
		try{
			con = daoFactory.getConnection();
			if (con != null) {
				//SELECT meter_id, \"VR\", \"VY\", \"VB\", \"IR\", \"IY\", \"IB\", \"KW\", \"KV\", \"PF\", \"FQ\", \"AI\", \"AE\", date, \"timestamp\" 
				//FROM meter_data where meter_id = ? and date between ? and ? ;
				strQuery = ProjectConstants.QUERY_FETCH_HISTORY ;
				logger.info("query:"+strQuery); 
				
				//create the batch queries
				pst = con.prepareStatement(strQuery) ;
				pst.setString(1, mId);
				pst.setDate(2, java.sql.Date.valueOf(fd));
				pst.setDate(3,java.sql.Date.valueOf(td));
				rs = pst.executeQuery() ;
				while(rs.next()){
					objDataModel = new MeterDataModel() ;
					objDataModel.setMeter_id(rs.getString(1));
					objDataModel.setVR(rs.getBigDecimal(2));
					objDataModel.setVY(rs.getBigDecimal(3));
					objDataModel.setVB(rs.getBigDecimal(4));
					objDataModel.setIR(rs.getBigDecimal(5));
					objDataModel.setIY(rs.getBigDecimal(6));
					objDataModel.setIB(rs.getBigDecimal(7));
					objDataModel.setKW(rs.getBigDecimal(8));
					objDataModel.setKV(rs.getBigDecimal(9));
					objDataModel.setPF(rs.getBigDecimal(10));
					objDataModel.setFQ(rs.getBigDecimal(11));
					objDataModel.setAI(rs.getBigDecimal(12));
					objDataModel.setAE(rs.getBigDecimal(13));
					objDataModel.setDate(rs.getString(14));
					objDataModel.setTimestamp(rs.getString(15));
					
					alMeterDataModels.add(objDataModel) ;
				}//end while
					/*pst.setString(1, model.getMeter_id());
					pst.setDouble(2, model.getVR().doubleValue());
					pst.setDouble(3, model.getVY().doubleValue());
					pst.setDouble(4, model.getVB().doubleValue());
					pst.setDouble(5, model.getIR().doubleValue());
					pst.setDouble(6, model.getIY().doubleValue());
					pst.setDouble(7, model.getIB().doubleValue());
					pst.setDouble(8, model.getKW().doubleValue());
					pst.setDouble(9, model.getKV().doubleValue());
					pst.setDouble(10, model.getPF().doubleValue());
					pst.setDouble(11, model.getFQ().doubleValue());
					pst.setLong(12, model.getAI().longValue());
					pst.setLong(13, model.getAE().longValue());*/
					
				
				
				
				
			}
		} catch (Exception e) {
			logger.error(e.toString(), e.fillInStackTrace());
			//e.printStackTrace();
		} finally {
			if(pst != null){
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}//end of try / catch
		
		logger.info("EXIT---> methodname : "+methodname);
		return alMeterDataModels ;
		
	}
	/**************************************************            Business Logic Ends              *********************************************/
	
}
