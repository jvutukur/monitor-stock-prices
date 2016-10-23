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

@Path("/yahoostocks")
public class CrudController {
	
	 CrudServices crudServices = new CrudServices();

	 @GET
	 @Path("/companies_list")	 
	 @Produces(MediaType.APPLICATION_JSON)  
	 public Response companies()  
	 {	  
	  Response response  = null;
	  List<Company> companyList = crudServices.getComaniesList();
	  if(companyList.size() > 0 ){
		  response = Response.status(Response.Status.OK).entity(companyList).build();  
	  }
	  else{
		  response = Response.status(Response.Status.NO_CONTENT).entity(companyList).build();
	  }
	  return response;
	  
	 }
	 	 
	 @GET
	 @Path("/company_history/{company_code}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response companyHistory(@PathParam("company_code") String company_code){
		 ArrayList<StockValue> stockHistory = new ArrayList<StockValue>();
		 stockHistory= crudServices.getComanyHistorty(company_code);
		 Response response = null;
		 if(stockHistory.size()>0){
			 response = Response.status(Response.Status.OK).entity(stockHistory).build();
		 }
		 else{
			 response = Response.status(Response.Status.NOT_FOUND).entity(stockHistory).build();
		 }
		 return response;
	 }
	 
	 @POST
	 @Path("/new_company/")	 
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response newCompany(CompanyCodes company){
	        Response response = null;	        
	        String message = crudServices.addCompany(company.getCompanyCode());
	        if(message.equals("No such company exist")){
	        	response = Response.status(Response.Status.BAD_REQUEST).entity(message).build();
	        }
	        else if(message.contains("already exists in database")){
	        	response = Response.status(Response.Status.FORBIDDEN).entity(message).build();
        	}
	        else{
	        	response = Response.status(Response.Status.CREATED).entity(message).build();
	        }	         
	        return response; 		 
	 }
	 
	 @DELETE
	 @Path("/delete_company/{company_code}")
	 public Response deleteCompany(@PathParam("company_code") String company_code){
		 String message = crudServices.deleteCompany(company_code);
		 Response response = null;
		 if(message.equals("No such company exist")){
			 response = Response.status(Response.Status.NOT_FOUND).entity(message).build();
		 }
		 else{
			 response = Response.status(Response.Status.OK).entity(message).build();
		 }
		 
		 return response;
	 }
	 
		 
		
}
