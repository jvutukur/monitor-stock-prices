package rest.api;

import static org.junit.Assert.*;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.expect;
import groovyx.net.http.ContentType;
import com.jayway.restassured.RestAssured;

public class CrudControllerTest {

	
    @Before
    public void setUp(){
        RestAssured.basePath = "http://localhost:8080";
    }
    
    @Test
    public void testGetProducts(){
        expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/yahoostocks/companies_list");
    }

}
