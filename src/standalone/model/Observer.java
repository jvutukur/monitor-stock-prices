package standalone.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import rest.api.CrudServices;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import dao.Company;


public class Observer {
	
	static Logger log = Logger.getLogger(CrudServices.class);
	
	private String company_code;
	private String company_name;
	
	public Observer(String company_code, String company_name) {		
		this.company_code = company_code;
		this.company_name = company_name;
	}

	public Observer(String company_code){
		this.company_code = company_code;
	}

	/*
	 * used api -- http://maven-repository.com/artifact/com.yahoofinance-api/YahooFinanceAPI/1.3.0
	 * reference for api - http://financequotes-api.com/
	 * Returns current stock value of companyName which corresponds to value in yahooFinance  
	 */
	public BigDecimal getStockPrice(String companyName){				
		BigDecimal price = null;
		try{			
			Stock stock = YahooFinance.get(companyName);
			price = stock.getQuote(true).getPrice();						
			log.info(stock.getName()+" : "+price.toPlainString());			
		}
		catch(Exception e){
			log.error(e.getMessage());		  		
		}
		finally{
				
		}	
		return price;
	}

	/*
	 * This method gets update from subject after every 5 minutes. 
	 * Gets the current stock value for company_code and adds to database
	 */
	public boolean addSingleStockToDB(){			
		boolean successMessage = false;
		Connection con = mySqlConnection.getConnection();
		BigDecimal stockValue = getStockPrice(this.company_code);
		Timestamp timeStamp = new java.sql.Timestamp(new java.util.Date().getTime());	
		if(!stockValue.toString().equals("0")){
			try{
				String query ="INSERT INTO `stocks`.`stock_values` (`company_code`, `time_stamp`, `value`) VALUES (?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, company_code.toUpperCase());
				pstmt.setTimestamp(2, timeStamp);
				pstmt.setBigDecimal(3,stockValue);
				pstmt.executeUpdate();
				successMessage = true;
			}
			catch(Exception e){
				successMessage = false;
				log.error(e.getMessage());						
			}			
		}
				
		return successMessage;
	}
	
	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	
	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
}
