package com.stl.tcpweb.control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet(
		description = "Initialize the server environment", 
		urlPatterns = { "/InitServlet" },
		loadOnStartup = 0,
		initParams = @WebInitParam(name = "config", value = "/WEB-INF/config/system.properties")
		)
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * Declare and initialize  required variables
	 */
	String userId = "";
	
	private static Logger logger = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext ctx = config.getServletContext();
		InputStream fis = ctx.getResourceAsStream(config.getInitParameter("config"));
		Properties props = new Properties();
		
		try
		{
			props.load(fis);
			File aFile = new File( props.getProperty("logfile") );
			boolean success = aFile.exists();

			if (!success)
				success = aFile.mkdir();
		
		
			logger = Logger.getLogger(InitServlet.class) ;
			
		}catch (IOException ioe)
		{
			logger.error(ioe.toString(), ioe.fillInStackTrace());
		}
		catch (Exception ge) {
			logger.error(ge.toString(), ge.fillInStackTrace());
		}
		
		/*Start the Chating Server application to handle the connections*/
		/*(new Thread() {
			  public void run() {
			    // do stuff
				MultiThreadChatServerSync.main(new String[]{});
			  }
		}).start();*/
		
		
		logger.info("InitServlet initialization successfull...");
	}//end of init method
	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		super.destroy();
		logger.info("InitServlet instance destroy sucessfull...");
	}//end of destroy method

}
