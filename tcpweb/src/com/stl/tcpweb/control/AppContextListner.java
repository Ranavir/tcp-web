package com.stl.tcpweb.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.stl.tcpweb.dto.MeterDataModel;
import com.stl.tcpweb.service.MeterDataService;
import com.stl.tcpweb.util.ProjectConstants;
import com.stl.tcpweb.util.Utils;

/**
 * Application Lifecycle Listener implementation class AppContextListner
 *
 */
@WebListener
public class AppContextListner implements ServletContextListener {
	
	public static final long MINUTE_INTERVAL = 15L ;
	public static final long MILLISEC_INTERVAL = (MINUTE_INTERVAL * 60 * 1000) ;
	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// create the thread pool
		ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 50000L,TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));
		
		servletContextEvent.getServletContext().setAttribute("executor",executor);
		
		servletContextEvent.getServletContext().setAttribute("scValue","");//initialize the variable for first time b4 any one's usage
				
		//create scheduler
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		//initial delay millisecond error removed
	    scheduler.scheduleAtFixedRate(new SaveToDBTask(servletContextEvent), ( getIntitialDelayInMillis() - Calendar.getInstance().get(Calendar.MILLISECOND) % 1000 ), MILLISEC_INTERVAL, TimeUnit.MILLISECONDS);
		servletContextEvent.getServletContext().setAttribute("scheduler",scheduler);
	}

	private long getIntitialDelayInMillis() {
		
		Calendar calendar = Calendar.getInstance();
		System.out.println("Current Time::"+calendar.getTime());
		
        /*calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);*/
		long ldelayinmilli = 0L ; 
		
        long min = calendar.get(Calendar.MINUTE) % MINUTE_INTERVAL;
        long sec = calendar.get(Calendar.SECOND) % 60;
        //long milli = calendar.get(Calendar.MILLISECOND) % 1000;
        
        //System.out.println(min+" "+sec+" "+milli);
        
        ldelayinmilli = ((-min + MINUTE_INTERVAL) * 60 * 1000) - (sec * 1000) ;
        
        //calendar.add(Calendar.MILLISECOND, (int)ldelayinmilli) ;
        //System.out.println("First Scheduled Time::"+calendar.getTime());
        //System.out.println("Delay in millisecond:"+ldelayinmilli);
		return ldelayinmilli ;
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) servletContextEvent
                .getServletContext().getAttribute("executor");
        executor.shutdown();
        ScheduledExecutorService scheduler = (ScheduledExecutorService) servletContextEvent
                .getServletContext().getAttribute("scheduler");
        scheduler.shutdown();
	}
	public class SaveToDBTask extends TimerTask{
		ServletContextEvent servletContextEvent ;
		private MeterDataService mdService = null ;
		ArrayList<MeterDataModel> alMeterDataModels ;
	    public SaveToDBTask(ServletContextEvent servletContextEvent) {
			super();
			this.servletContextEvent = servletContextEvent;
			this.mdService = (MeterDataService)Utils.getService(ProjectConstants.METER_DATA_SERVICE) ;
			System.out.println("SaveToDBTask initialized...");
		}

		public void run() {
	        // Implement.
			//servletContextEvent.getServletContext().setAttribute("scValue","") ;
			alMeterDataModels = (ArrayList<MeterDataModel>)servletContextEvent.getServletContext().getAttribute("alMeterDataModels") ;
			if( null != alMeterDataModels && alMeterDataModels.size() > 0 ){
				mdService.saveMeterData(alMeterDataModels);
			}
			System.out.println("SaveToDBTask executed sucessfully...");
	    }
	}

}

