package com.stl.tcpweb.control;

import java.util.ArrayList;

import javax.servlet.AsyncContext;

import org.apache.log4j.Logger;

import com.stl.tcpweb.dto.MeterDataModel;
import com.stl.tcpweb.service.MeterDataService;
import com.stl.tcpweb.util.ProjectConstants;
import com.stl.tcpweb.util.Utils;

public class AsyncDBProcessor implements Runnable {
	private AsyncContext asyncContext;
	ArrayList<MeterDataModel> alMeterDataModels ;
	private MeterDataService mdService = null ;
	private static Logger logger = null;
	
	public AsyncDBProcessor() {
	}
	public AsyncDBProcessor(AsyncContext asyncCtx, ArrayList<MeterDataModel> alMeterDataModels) {
		logger = Logger.getLogger(AsyncDBProcessor.class) ;
        this.asyncContext = asyncCtx;
        this.alMeterDataModels = alMeterDataModels;
        this.mdService = (MeterDataService)Utils.getService(ProjectConstants.METER_DATA_SERVICE) ;
        logger.info("AsyncDBProcessor instantiated...");
    }
	
	
	@Override
	public void run() {
		System.out.println("Async Supported? "+ asyncContext.getRequest().isAsyncSupported());
        saveMeterData(alMeterDataModels);
        
        /*try {
            PrintWriter out = asyncContext.getResponse().getWriter();
            out.write("Processing done for " + secs + " milliseconds!!");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //complete the processing
        asyncContext.complete();
	}//end of run method
	/*********************************************
	 * This method saves meter data to database
	 * @param alMeterDataModels
	 * @author ranavir
	 * @date 27052016
	 ********************************************/
	private void saveMeterData(ArrayList<MeterDataModel> alMeterDataModels) {
		String methodname = "saveMeterData" ;
		logger.info("ENTRY---> methodname : "+methodname);
		
		mdService.saveMeterData(alMeterDataModels);
		
		logger.info("EXIT---> methodname : "+methodname);
    }//end of saveMeterData
}
