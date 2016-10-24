package rest.api;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.expect;
//import groovyx.net.http.ContentType;

import static org.hamcrest.Matchers.equalTo;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class CrudControllerTest_IT {

	String getCompList = "http://localhost:8080/MonitorStockPrice/rest/yahoostocks/companies_list";
    @Before
    public void setUp(){
        RestAssured.basePath = "";
    }
    
    @Test
    public void testCompaniesList(){
        //Assert.assertEquals(true,true);
    	expect().statusCode(200).contentType("").when().get(getCompList);       
    }
    
    @Test
    public void testgetStockHistory(){
     //hahaha	
    	expect().statusCode(404).contentType("application/json").when().get("http://localhost:8080/MonitorStockPrice/rest/yahoostocks/company_history/hahaha");
    }
    
    @Test
    public void testGetStockHistoryScenario1(){
    	//INTL
    	expect().statusCode(200).contentType("").when().get("http://localhost:8080/MonitorStockPrice/rest/yahoostocks/company_history/INTL");
    }
    
    @Test
    public void testAddCompany(){
    	//INTL
    	expect().statusCode(415).contentType("").when().post("http://localhost:8080/MonitorStockPrice/rest/yahoostocks/new_company");
    }
    

    

}
