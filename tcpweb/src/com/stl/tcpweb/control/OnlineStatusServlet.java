package com.stl.tcpweb.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.stl.tcpweb.dto.MeterDataModel;
import com.stl.tcpweb.helper.MeterDataHelper;

/**
 * Servlet implementation class Page124
 */
@WebServlet(
		description = "This servlet called in every n seconds by page refresh and gets the data string from session scope and formats it then prints it also saves it into database", 
		urlPatterns = {"/status" },
		loadOnStartup = 1
		)
public class OnlineStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * Declare and initialize  required variables
	 */
	String methodname = "" ;
	String userId = "";
	
	private static Logger logger = null;
	private static MeterDataHelper mdHelper = null ;
	//static StringBuilder scValue = new StringBuilder() ;
	//String scValue  ;
		
	@Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	try
		{
			
			logger = Logger.getLogger(OnlineStatusServlet.class) ;
			mdHelper = MeterDataHelper.getInstance() ;
		}catch (Exception ge) {
			logger.error(ge.toString(), ge.fillInStackTrace());
		}
		logger.info("OnlineStatusServlet initialized successfully...");
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OnlineStatusServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
		//TreeMap<String, ArrayList<ParamWiseRecord>> tm = null ;
		ArrayList<MeterDataModel> alMeterDataModels = null ;
		String scValue = "" ;
		if(null != request.getParameter("reqData")){//other client request data available only set the formatted data in session
	
			
			//parse the data and update the values in session
			/*Get the String meter data*/
			String strReqData = request.getParameter("reqData") ;
			
			if(null != strReqData){
				if(strReqData.indexOf("connection") == -1 && strReqData.indexOf("Connection") == -1 && strReqData.indexOf("quit") == -1){
					strReqData = strReqData.substring(strReqData.indexOf(" ") + 1) ;//skip ip address
					getServletContext().setAttribute("reqData", strReqData);//set value in application scope
				}
				/*Get the populated hash map*/
				//tm = mdHelper.getDataMap(strReqData) ;
				alMeterDataModels = mdHelper.getDataList(strReqData) ;
			}//end list population
			
			
			/*Get the formatted output String in scValue*/
			
			//String scValue = mdHelper.getFormattedOutput(tm) ;
			if(null != alMeterDataModels){
				//set the value to request object so that it can be saved to database in 15 mins interval
				getServletContext().setAttribute("alMeterDataModels", alMeterDataModels);
				
				scValue = mdHelper.getFormattedData(alMeterDataModels) ;
				getServletContext().setAttribute("scValue", scValue);//set value in application scope
				//getServletContext().setAttribute("scValue", getServletContext().getAttribute("scValue").toString()+scValue);//set value in application scope
			}
			
			
			//System.out.println("scValue:"+scValue);
			
		}else{//only forwards to the page 
			//request.setAttribute("scValue", scValue);//now not required as the data to be displayed in status page from context scope @date 27052016 changed
			String data = null != getServletContext().getAttribute("scValue")? getServletContext().getAttribute("scValue").toString() : "" ;
			//System.out.println("data from context ::"+data);
			RequestDispatcher rd = request.getRequestDispatcher("./onlinestatus.jsp");
			rd.forward(request,response);	
			
		}
				
			
		
		logger.info("EXIT---> methodname : "+methodname);
	}//end of dopost method

	@Override
	public void destroy() {
		super.destroy();
		logger.info("OnlineStatusServlet destroyed successfully...");
	}
	

}//end of servlet






/****************************************************
 BEGIN Asynch Database operation
	save the data into database
***************************************************/
/*@WebServlet(
		description = "This servlet called in every n seconds by page refresh and gets the data string from session scope and formats it then prints it also saves it into database", 
		urlPatterns = {"/status" },
		loadOnStartup = 1,
		asyncSupported = true //important
		)*/
/*long startTime = System.currentTimeMillis();
System.out.println("AsynchProcess Start::Name="
  + Thread.currentThread().getName() + "::ID="
  + Thread.currentThread().getId());

request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);

AsyncContext asyncCtx = request.startAsync();
asyncCtx.addListener(new AppAsyncListener());
asyncCtx.setTimeout(9000);

ThreadPoolExecutor executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");

executor.execute(new AsyncDBProcessor(asyncCtx, alMeterDataModels));

long endTime = System.currentTimeMillis();
System.out.println("AsynchProcess End::Name="
      + Thread.currentThread().getName() + "::ID="
      + Thread.currentThread().getId() + "::Time Taken="
      + (endTime - startTime) + " ms.");*/
	/***************************************************
	 			END Asynch Database operation
	 ***************************************************/
