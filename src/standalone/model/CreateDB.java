package standalone.model;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class CreateDB {
	/*
	 * 
	 * CREATE SCHEMA `stocks` ;
	 */
	public boolean createSchema() {

		boolean success = false;
		try {
			Connection con = mySqlConnection.getConnection();
			
			String schema = "CREATE DATABASE IF NOT exists stocks";
			PreparedStatement preparedStatement = con.prepareStatement(schema);
			preparedStatement.execute();
			System.out.println("data base schema created");
			
			String companiesTable = "CREATE TABLE  IF NOT exists `stocks`.`companies` (`company_code` VARCHAR(15) NOT NULL,`company_name` VARCHAR(100) NOT NULL,PRIMARY KEY (`company_code`));";
			preparedStatement = con
					.prepareStatement(companiesTable);
			preparedStatement.execute();

			String stocksTable = "CREATE TABLE  IF NOT exists `stocks`.`stock_values` (`company_code` VARCHAR(15) NOT NULL,"
					+ "`time_stamp` DATETIME NOT NULL, `value` DOUBLE(20, 2),PRIMARY KEY (`company_code`, `time_stamp`),"
					+ "CONSTRAINT `company_code`FOREIGN KEY (`company_code`)REFERENCES `stocks`.`companies` (`company_code`)ON DELETE CASCADE ON UPDATE NO ACTION);";
			preparedStatement = con.prepareStatement(stocksTable);
			preparedStatement.execute();
			
			System.out.println("data base tables created");
			
			String dummyData = "INSERT INTO `stocks`.`companies` (`company_code`, `company_name`) "
					+ "VALUES"
					+ " ('INTL', 'Intel Corporation');";
			preparedStatement = con.prepareStatement(dummyData);
			preparedStatement.executeUpdate();
			
			System.out.println("added dummy data to DB");
					
					/*
					 * Intel Corporation, INTC
					 *  ,TSLA
					 *  ,GOOG
					 *  ,YHOO 
					 *  TWTR, Twitter, Inc. Common Stock
					 */
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return success;

	}

}
