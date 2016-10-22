package rest.api;

import javax.ws.rs.DELETE;  
import javax.ws.rs.GET;  
import javax.ws.rs.POST;  
import javax.ws.rs.PUT;  

import javax.ws.rs.Path;  
import javax.ws.rs.PathParam;  
import javax.ws.rs.Produces;  
import javax.ws.rs.core.MediaType;

import standalone.model.FetchLatestStockPrices;
import standalone.dao.Company;

import java.util.List;
import java.util.ArrayList;


//http://www.java2blog.com/2016/03/restful-web-services-jaxrs-crud-example.html

//url -- http://localhost:8082/MonitorStockPrice/rest/yahoostocks/getCompanies
@Path("/yahoostocks")
public class CrudController {

	 @GET
	 @Path("/getCompanies")
	 @Produces(MediaType.APPLICATION_JSON)  
	 public List<Company> getCountries()  
	 {
	  FetchLatestStockPrices flsp = new FetchLatestStockPrices();
	  ArrayList<Company> companyList = flsp.getCompaniesList();  
	  return companyList;  
	 }  
		
}
