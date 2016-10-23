package rest.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.Company;
import dao.StockValue;
import standalone.model.FetchLatestStockPrices;
import standalone.model.mySqlConnection;

import org.apache.log4j.Logger;

public class CrudServices {
	
	static Logger log = Logger.getLogger(CrudServices.class);

	public String deleteCompany(String company_code) {
		String message = "";
		String companyName = new FetchLatestStockPrices()
				.getCompanyName(company_code);
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
				message = "Deleted company successfully";
				log.info("Deleted "+company_code+" successfully" );
			} catch (Exception e) {
				message = "Internal Server Error";				
				System.out.println(e.getMessage());
				log.error(e.getMessage());
			}

		}
		return message;
	}

	public String addCompany(String company_code) {
		String message = "";
		String companyName = new FetchLatestStockPrices()
				.getCompanyName(company_code);
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
					message = companyName + " is successfully regiseterd";
					log.info(company_code+" is successfully added to database");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		return message;
	}

	public List<Company> getComaniesList() {
		FetchLatestStockPrices flsp = new FetchLatestStockPrices();
		ArrayList<Company> companyList = flsp.getCompaniesList();
		log.info("user requested for list of comapanies in DB");
		return companyList;
	}
	
	

	public ArrayList<StockValue> getComanyHistorty(String company_code) {
		ArrayList<StockValue> stocksHistory = new ArrayList<StockValue>();
		try {
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
			log.info(company_code+" stocks history sent to user successfully");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e.getMessage());
		} finally {

		}

		return stocksHistory;

	}

}
