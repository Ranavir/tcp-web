package com.stl.tcpweb.util;
/**
 * Defines all the Constants of the project at 
 * single places
 * 
 * @author ranavir
 * @date 24052016
 */
public class ProjectConstants {
		
	    //server related date:24052016
		public static final String SERVER_IP = "182.156.93.61" ;
		public static final int SERVER_PORT = 124 ;
		
		
		
		//global messages
		//public static final String MSG_DISCONNECT_FROM_CLIENT = "/quit" ;
		//public static final String MSG_DISCONNECT_FROM_SERVER = "Bye" ;
		public static final String MSG_BUSY = "/busy" ;
		public static final String MSG_BUSY1 = "Server too busy. Try later." ;
		
		
	
		//Date 24052016
		public static final int POSTGRESQL_DAO_FACTORY = 1 ;//1 FOR POSTGRES
		public static final int ORACLE_DAO_FACTORY = 2 ;//2 FOR ORACLE
		public static final int MYSQL_DAO_FACTORY = 3 ;//3 FOR MYSQL
		
		//Services
		public static final int METER_DATA_SERVICE = 1001;
		
		//Daos
		public static final int METER_DATA_DAO = 2001;
		
		//Query
		public static final String QUERY_INSERT_METER_DATA ="INSERT INTO meter_data(meter_id, VR, VY, VB, IR, IY, IB, KW, KV, PF, FQ, AI, AE, date, timestamp) "+
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_date, current_timestamp)";

		public static final String QUERY_FETCH_HISTORY = "SELECT meter_id, VR, VY, VB, IR, IY, IB, KW, KV, PF, FQ, AI, AE, date, to_char(date_trunc('second',timestamp),'DD-MM-YYYY HH24:MI:SS') FROM meter_data where meter_id = ? and date between ? and ? order by timestamp desc" ;

		/*public static final String QUERY_INSERT_METER_DATA ="INSERT INTO meter_data(meter_id, \"VR\", \"VY\", \"VB\", \"IR\", \"IY\", \"IB\", \"KW\", \"KV\", \"PF\", \"FQ\", \"AI\", \"AE\", date, timestamp) "+
															"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_date, current_timestamp)";
		
		public static final String QUERY_FETCH_HISTORY = "SELECT meter_id, \"VR\", \"VY\", \"VB\", \"IR\", \"IY\", \"IB\", \"KW\", \"KV\", \"PF\", \"FQ\", \"AI\", \"AE\", date, to_char(date_trunc('second',\"timestamp\" ),'DD-MM-YYYY HH24:MI:SS') FROM meter_data where meter_id = ? and date between ? and ? " ;*/

}//end class
