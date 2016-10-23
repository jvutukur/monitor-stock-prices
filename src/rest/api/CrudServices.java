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

public class CrudServices {

	public String deleteCompany(String company_code) {
		String message = "";
		String companyName = new FetchLatestStockPrices()
				.getCompanyName(company_code);
		if (companyName.equals("") || companyName.equals("N/A")) {
			message = "No such company exist";
		} else {
			try {
				Connection con = mySqlConnection.getConnection();
				String query = "DELETE FROM `stocks`.`companies` WHERE `company_code`=?";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, company_code);
				pstmt.execute();
				message = "Deleted company successfully";
			} catch (Exception e) {
				System.out.println(e.getMessage());
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
		} else {
			try {
				Connection con = mySqlConnection.getConnection();
				String query = "SELECT `company_name` FROM `stocks`.`companies` where `company_code` = ?";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, company_code.toUpperCase());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					message = companyName + " already exists in database";
				} else {
					pstmt.close();
					query = "INSERT INTO `stocks`.`companies` (`company_code`, `company_name`) VALUES (?, ?)";
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, company_code.toUpperCase());
					pstmt.setString(2, companyName);
					pstmt.executeUpdate();
					message = companyName + " is successfully regiseterd";
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

		}

		return stocksHistory;

	}

}
