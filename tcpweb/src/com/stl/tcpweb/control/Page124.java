package com.stl.tcpweb.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.stl.tcpweb.dto.ParamWiseRecord;
import com.stl.tcpweb.helper.MeterDataHelper;
import com.stl.tcpweb.helper.MultiThreadChatClient;
import com.stl.tcpweb.util.ProjectConstants;
import com.stl.tcpweb.util.Utils;

/**
 * Servlet implementation class Page124
 */
/*@WebServlet(
		description = "Receives Client messages @ port 124 and sends messages to connected clients", 
		urlPatterns = { "/page124" },
		loadOnStartup = 1
		)*/
public class Page124 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * Declare and initialize  required variables
	 */
	String methodname = "" ;
	String userId = "";
	
	private static Logger logger = null;
	private static MeterDataHelper mdHelper = null ;
	//static StringBuilder scValue = new StringBuilder() ;
	String scValue  ;
	TreeMap<String, ArrayList<ParamWiseRecord>> tm ;	
	@Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	try
		{
			
			logger = Logger.getLogger(Page124.class) ;
			mdHelper = MeterDataHelper.getInstance() ;
		}catch (Exception ge) {
			logger.error(ge.toString(), ge.fillInStackTrace());
		}
		logger.info("Page124 servlet initialized successfully...");
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Page124() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodname = "doGet" ;
		//logger.info("ENTRY---> methodname : "+methodname);
		
		doPost(request, response);

		//logger.info("EXIT---> methodname : "+methodname);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodname = "doPost" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		//System.out.println("scValue:"+scValue);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		//"serverData="+text+"&ownReq=yes"
		if(null!=request.getParameter("ownReq")){//own client request data available
			String ownReq = request.getParameter("ownReq");
			System.out.println("ownReq:"+ownReq);
			String serverData = request.getParameter("serverData") ;
			System.out.println("serverData:"+serverData);
			
				//scValue = scValue.append("<br>") ;
				//scValue = scValue.append(serverData) ;
			if(""!=serverData){
				//scValue = "<br>" + serverData + scValue ;
				//Do here chat client call to send the data
				String[] strArr = new String[]{ ProjectConstants.SERVER_IP , ProjectConstants.SERVER_PORT+"" ,serverData};
				MultiThreadChatClient.main(strArr);
				//MultiThreadChatServerSync.globalMsg = serverData ;
			}
			//System.out.println("scValue:"+scValue);
			
			//out.print(scValue);
			//out.flush();
	        //out.close();
			//RequestDispatcher rd = request.getRequestDispatcher("124.jsp");
			//rd.forward(request,response);
		
		}else {
			if(null != request.getParameter("reqData")){//other client request data available
		
				
				//scValue = "<br>" + request.getParameter("reqData") + scValue ;
				/*newValue = Utils.generateFormattedOutput(request.getParameter("reqData")); 
				scValue =Utils.generateTrimmedOutput(newValue + scValue) ;*/
				//logger.info("scValue:: "+scValue);
				
				//parse the data and update the values in page
				/*Get the String meter data*/
				String strReqData = request.getParameter("reqData") ;
				
				/*Get the populated hash map*/
				if(null != strReqData){
					//tm = mdHelper.getDataMap(strReqData) ;
				}//end map population
				
				/*Get the formatted output String in scValue*/
				
				//scValue = mdHelper.getFormattedOutput(tm) ;
				
				
				System.out.println("scValue:"+scValue);
				
			}else{//only forwards to the page by setting output variable to request scope
				request.setAttribute("scValue", scValue);//now not required as the data to be displayed in status page from context scope @date 27052016 changed
				RequestDispatcher rd = request.getRequestDispatcher("124.jsp");
				rd.forward(request,response);	
			}
				
		}
			
		
			
		
		logger.info("EXIT---> methodname : "+methodname);
	}//end of dopost method

	@Override
	public void destroy() {
		super.destroy();
		logger.info("Page124 servlet destroyed successfully...");
	}
	

}
