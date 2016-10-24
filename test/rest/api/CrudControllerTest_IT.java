package rest.api;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.expect;
//import groovyx.net.http.ContentType;



import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class CrudControllerTest_IT {

	String getProd = "http://localhost:8080/MonitorStockPrice/rest/yahoostocks/companies_list";
    @Before
    public void setUp(){
        RestAssured.basePath = "";
    }
    
    @Test
    public void testGetProducts(){
        //Assert.assertEquals(true,true);
    	expect().statusCode(200).contentType("").when().get(getProd);
        
    }

}
