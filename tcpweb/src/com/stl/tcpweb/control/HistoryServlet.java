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

import com.stl.tcpweb.dto.MeterDataModel;
import com.stl.tcpweb.dto.ParamWiseRecord;
import com.stl.tcpweb.helper.MeterDataHelper;
import com.stl.tcpweb.helper.MultiThreadChatClient;
import com.stl.tcpweb.service.MeterDataService;
import com.stl.tcpweb.util.ProjectConstants;
import com.stl.tcpweb.util.Utils;

/**
 * Servlet implementation class Page124
 */
@WebServlet(
		description = "Retrieves history Meter data based on timestamp and meter id", 
		urlPatterns = { "/history" },
		loadOnStartup = 1
		)
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * Declare and initialize  required variables
	 */
	String methodname = "" ;
	String userId = "";
	
	private static Logger logger = null;
	private static MeterDataHelper mdHelper = null ;
	private static MeterDataService mdService = null ;
	//static StringBuilder scValue = new StringBuilder() ;
	String historyValue  ;
	@Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	try
		{
			
			logger = Logger.getLogger(HistoryServlet.class) ;
			mdHelper = MeterDataHelper.getInstance() ;
			mdService = (MeterDataService)Utils.getService(ProjectConstants.METER_DATA_SERVICE) ;
		}catch (Exception ge) {
			logger.error(ge.toString(), ge.fillInStackTrace());
		}
		logger.info("History servlet initialized successfully...");
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryServlet() {
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
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "nocache");
        response.setCharacterEncoding("utf-8"); 
        PrintWriter out = null ;
        
		String fd  = (null != request.getParameter("fd"))?request.getParameter("fd"):"" ;
		String td  = (null != request.getParameter("td"))?request.getParameter("td"):"" ;
		String mId = (null != request.getParameter("mId"))?request.getParameter("mId"):"";
		System.out.println("fd:"+fd+" td:"+td+" mId:"+mId);
		ArrayList<MeterDataModel> alMeterDataModels = null ;
		String historyValue = "" ;
		try {
			if( !"".equals(fd) && !"".equals(td) && !"".equals(mId)){
				//fetch the history data set in historyValue using mdService 
				alMeterDataModels = mdService.fetchHistoryData(fd,td,mId);
				
				//format history data
				historyValue = mdHelper.getFormattedHistoryData(alMeterDataModels) ;
				
				out = response.getWriter();
				 
			}
		} catch (IOException e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
		}
		logger.info("EXIT---> methodname : "+methodname);
		out.print(historyValue);
	}//end of do post
			
	@Override
	public void destroy() {
		super.destroy();
		logger.info("History servlet destroyed successfully...");
	}
	

}
