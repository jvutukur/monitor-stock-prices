package standalone.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.TimerTask;

import dao.Company;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class FetchLatestStockPrices extends TimerTask{
		

	
	
	public BigDecimal getStockPrice(String companyName){
		
		//http://maven-repository.com/artifact/com.yahoofinance-api/YahooFinanceAPI/1.3.0
		//http://financequotes-api.com/
		BigDecimal price = null;
		try{			
			Stock stock = YahooFinance.get(companyName);
			price = stock.getQuote().getPrice();						
			System.out.println(stock.getName()+" : "+price.toPlainString());			
		}
		catch(Exception e){
		  	System.out.println(e.getMessage());			
		}
		finally{
				
		}	
		return price;
	}
	
	public String getCompanyName(String company_code){
		String companyName = "";
		try{
			Stock stock = YahooFinance.get(company_code);
			companyName = stock.getName();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return companyName;
	}
	
	public boolean addSingleStockToDB(String company_code, Timestamp timeStamp, BigDecimal stockPrice){			
		Connection con = mySqlConnection.getConnection();
		try{
			String query ="INSERT INTO `stocks`.`stock_values` (`company_code`, `time_stamp`, `value`) VALUES (?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, company_code.toUpperCase());
			pstmt.setTimestamp(2, timeStamp);
			pstmt.setBigDecimal(3,stockPrice);
			pstmt.executeUpdate();
		}
		catch(Exception e){
			
		}
		finally{
			
		}
		
		return true;
	}
	
	public ArrayList<Company> getCompaniesList(){
		ArrayList<Company> companiesList = new ArrayList<Company>();
		try{
			Connection con = mySqlConnection.getConnection();
			String query = "Select * from stocks.companies";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String company_code = rs.getString("company_code");
				String company_name = rs.getString("company_name");
				companiesList.add(new Company(company_code,company_name));				
			}			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			
		}
			
		return companiesList;
	}
	
	public boolean addStocksToDB(){
		
		ArrayList<Company> companiesList = getCompaniesList();
		
		for(Company currentCompany : companiesList){
		 BigDecimal stockValue = getStockPrice(currentCompany.getCompanyCode());
		 Timestamp timeStamp = new java.sql.Timestamp(new java.util.Date().getTime());
		 addSingleStockToDB(currentCompany.getCompanyCode(),timeStamp,stockValue);		 
		}
				
		return true;
	}
	
	public void run(){
		addStocksToDB();
	}
	
	
	
	
}
