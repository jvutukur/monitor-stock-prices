package rest.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.Company;
import dao.StockHistory;
import dao.StockValue;
import standalone.model.Observer;
import standalone.model.Subject;
import standalone.model.mySqlConnection;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import org.apache.log4j.Logger;

public class CrudServices {
	
	static Logger log = Logger.getLogger(CrudServices.class);
	/*
	 * deletes the company_code corresponding record in data base and unregisters Subject.
	 * Returns message which explains status of the request
	 */
	public String deleteCompany(String company_code) {
		String message = "";
		String companyName = getCompanyName(company_code);
		if (companyName.equals("") || companyName.equals("N/A")) {
			message = "No such company exist";
			log.warn(" User requested for "+company_code+" to delete. But no such company exist" );
		} else {
			try {
				Connection con = mySqlConnection.getConnection();
				String query = "DELETE FROM `stocks`.`companies` WHERE `company_code`=?";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, company_code);
				pstmt.execute();
				message = "Deleted "+companyName+"  successfully";
				Subject.getSubject().unregister(new Observer(company_code,companyName));
				log.info("Deleted "+company_code+" successfully" );
				
			} catch (Exception e) {
				message = "Internal Server Error";				
				System.out.println(e.getMessage());
				log.error(e.getMessage());
			}

		}
		return message;
	}
	
	/*
	 * Returns full company name corresponding to company_code
	 */
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

	/*
	 *Adds company corresponding to company_code to data base and registers to subject 
	 */
	public String addCompany(String company_code) {
		String message = "";
		String companyName = getCompanyName(company_code);
		if (companyName.equals("") || companyName.equals("N/A")) {
			message = "No such company exist";
			log.warn(" User requested for "+company_code+" to Add. But no such company exist" );
		} else {
			try {
				Connection con = mySqlConnection.getConnection();
				String query = "SELECT `company_name` FROM `stocks`.`companies` where `company_code` = ?";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, company_code.toUpperCase());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					message = companyName + " already exists in database";
					log.info("Used requested to add "+company_code+" which is already in Database" );
				} else {
					pstmt.close();
					query = "INSERT INTO `stocks`.`companies` (`company_code`, `company_name`) VALUES (?, ?)";
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, company_code.toUpperCase());
					pstmt.setString(2, companyName);
					pstmt.executeUpdate();
					Subject.getSubject().register(new Observer(company_code,null));
					message = companyName + " is successfully regiseterd";
					log.info(company_code+" is successfully added to database");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		return message;
	}

	/*
	 * Returns list of companies in database
	 */
	public List<Company> getComaniesList() {		
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
		log.info("user requested for list of comapanies in DB");
		return companiesList;
	}
	
	
	/*
	 * Returns list of previous stock values for the given specific company_code
	 */
	public StockHistory getComanyHistorty(String company_code) {
		StockHistory sh = null;
		try {
			ArrayList<StockValue> stocksHistory = new ArrayList<StockValue>();
			Connection con = mySqlConnection.getConnection();
			String query = "SELECT `time_stamp`, `value` FROM `stocks`.`stock_values`"
					+ "where `company_code` =?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, company_code);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StockValue oneValue = new StockValue(
						rs.getTimestamp("time_stamp"),
						rs.getBigDecimal("value"));
				stocksHistory.add(oneValue);
			}
			Collections.sort(stocksHistory);
			
			pstmt.close();
			query = "SELECT `company_name` from `stocks`.`companies` where `company_code`=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,company_code.toUpperCase());
			ResultSet rs1 = pstmt.executeQuery();
			String companyFullName = "";
			if(rs1.next()){
				companyFullName = rs1.getString("company_name");
			}
			
			sh = new StockHistory(company_code,companyFullName,stocksHistory);
 			log.info(company_code+" stocks history requested by user");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e.getMessage());
		} finally {

		}

		return sh;

	}

}
