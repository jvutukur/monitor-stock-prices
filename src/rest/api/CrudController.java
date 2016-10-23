package rest.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.Company;
import dao.CompanyCodes;
import dao.StockValue;


//http://www.java2blog.com/2016/03/restful-web-services-jaxrs-crud-example.html

//url -- http://localhost:8082/MonitorStockPrice/rest/yahoostocks/getCompanies
@Path("/yahoostocks")
public class CrudController {
	
	 CrudServices crudServices = new CrudServices();

	 @GET
	 @Path("/companies_list")	 
	 @Produces(MediaType.APPLICATION_JSON)  
	 public List<Company> companies()  
	 {	  
	  return crudServices.getComaniesList();  
	 }
	 	 
	 @GET
	 @Path("/company_history/{company_code}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public ArrayList<StockValue> companyHistory(@PathParam("company_code") String company_code){
		 return crudServices.getComanyHistorty(company_code);
	 }
	 
	 @POST
	 @Path("/new_company/")	 
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response newCompany(CompanyCodes company){
	        Response response = null;	        
	        String message = crudServices.addCompany(company.getCompanyCode());
	        response = Response.status(Response.Status.OK).entity(message).build(); 
	        return response; 		 
	 }
	 
	 @DELETE
	 @Path("/delete_company/{company_code}")
	 public String deleteCompany(@PathParam("company_code") String company_code){
		 return crudServices.deleteCompany(company_code);
	 }
	 
	 //add list of companies
	 
	 //delete list of companies
		 
		
}
