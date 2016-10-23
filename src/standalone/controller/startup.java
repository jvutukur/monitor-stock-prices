package standalone.controller;

import javax.servlet.ServletContextEvent;  
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContext;
import java.io.File;
import org.apache.log4j.PropertyConfigurator;

import standalone.model.AddStockValuesJob;
import standalone.model.CreateDB;


@WebListener
public class startup implements ServletContextListener{

	@Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Starting up!");
        new CreateDB().createSchema();        
        AddStockValuesJob job = new AddStockValuesJob();
        job.startStockUpdateJob();
        
        ServletContext context = servletContextEvent.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String path = context.getRealPath("/");
        System.setProperty("rootPath", context.getRealPath("/"));
        String fullPath = context.getRealPath("") + log4jConfigFile;
         
        PropertyConfigurator.configure(fullPath);
    }
	
	@Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Shutting down!");
    }
}


