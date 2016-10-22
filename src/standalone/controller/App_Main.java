package standalone.controller;

import standalone.model.*;

public class App_Main 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );        
        new CreateDB().createSchema();        
        AddStockValuesJob job = new AddStockValuesJob();
        job.startStockUpdateJob();
        
    }

}
