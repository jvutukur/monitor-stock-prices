package standalone.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import rest.api.CrudServices;


public class CreateDB {
	
	static Logger log = Logger.getLogger(CrudServices.class);
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
			log.info("data base schema created");			
			
			String companiesTable = "CREATE TABLE  IF NOT exists `stocks`.`companies` (`company_code` VARCHAR(15) NOT NULL,`company_name` VARCHAR(100) NOT NULL,PRIMARY KEY (`company_code`));";
			preparedStatement = con
					.prepareStatement(companiesTable);
			preparedStatement.execute();

			String stocksTable = "CREATE TABLE  IF NOT exists `stocks`.`stock_values` (`company_code` VARCHAR(15) NOT NULL,"
					+ "`time_stamp` DATETIME NOT NULL, `value` DOUBLE(20, 2),PRIMARY KEY (`company_code`, `time_stamp`),"
					+ "CONSTRAINT `company_code`FOREIGN KEY (`company_code`)REFERENCES `stocks`.`companies` (`company_code`)ON DELETE CASCADE ON UPDATE NO ACTION);";
			preparedStatement = con.prepareStatement(stocksTable);
			preparedStatement.execute();
			
			log.info("data base tables created");
			String dummyData ="INSERT INTO `stocks`.`companies` (`company_code`, `company_name`) SELECT * FROM (SELECT 'INTL', 'INTL FCStone Inc.') AS tmp where NOT EXISTS( SELECT `company_code` from `stocks`.`companies` WHERE `company_code`='INTL') LIMIT 1;"; 
										
			preparedStatement = con.prepareStatement(dummyData);
			preparedStatement.executeUpdate();
						
			log.info("added dummy data to DB");		
					/*
					 * INTL FCStone Inc., INTL
					 *  ,TSLA
					 *  ,GOOG
					 *  ,YHOO 
					 *  TWTR, Twitter, Inc. Common Stock
					 */
			success = true;
		} catch (Exception e) {			
			log.error(e.getMessage());
		}

		return success;

	}

}
