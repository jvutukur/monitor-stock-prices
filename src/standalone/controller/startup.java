package standalone.controller;

import javax.servlet.ServletContextEvent;  
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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
    }
	
	@Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Shutting down!");
    }
}


