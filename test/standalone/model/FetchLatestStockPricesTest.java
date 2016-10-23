package standalone.model;


import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import dao.Company;
import standalone.model.FetchLatestStockPrices;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

import junit.framework.Assert;

public class FetchLatestStockPricesTest{

	 private static FetchLatestStockPrices ftsp;
	
	@BeforeClass
	public  static void setUpBeforeClass() throws Exception {
		ftsp =  new FetchLatestStockPrices();		
	}

	@Test
	public void testGetStockPriceSuccessScenario(){
		BigDecimal stockPrice = ftsp.getStockPrice("INTL");
		Assert.assertNotSame("Got Valid Stock Price for INTL", "0",stockPrice.toString());
	}
	
	@Test
	public void testGetStockPriceFailScenario(){
		BigDecimal stockPrice = ftsp.getStockPrice("hahaha");
		Assert.assertEquals("0",stockPrice.toString());
	}
	
	@Test
	public void testGetCompanyNameSuccessScenario(){
		String companyName = ftsp.getCompanyName("INTL");
		Assert.assertEquals( "INTL FCStone Inc.",companyName);
	}
	
	@Test
	public void testGetCompanyNameFailScenario(){
		String companyName = ftsp.getCompanyName("hahaha");
		Assert.assertEquals("N/A",companyName);
	}
	
	
	@Test
	public void testAddSingleStockToDBFailScenario(){
		boolean successMessage  = ftsp.addSingleStockToDB(null, null, null);
		Assert.assertEquals(false,successMessage);
	}	
	
	@Test
	public void testAddSingleStockToDBFailScenario_2(){
		boolean successMessage = ftsp.addSingleStockToDB(null, new Timestamp(2016, 10, 21, 0, 0, 0, 0), new BigDecimal(37.34));
		Assert.assertEquals(false, successMessage);
	}
	
	@Test
	public void testAddSingleStockToDBFailScenario_3(){
		boolean successMessage = ftsp.addSingleStockToDB("INTL", new Timestamp(2016, 10, 21, 0, 0, 0, 0), null);
		Assert.assertEquals(false, successMessage);
	}

	@Test
	public void testAddSingleStockToDBFailScenario_4(){
		boolean successMessage = ftsp.addSingleStockToDB("INTL", null, new BigDecimal(37.34));
		Assert.assertEquals(false, successMessage);
	}
	
	
	@Test
	public void testGetCompaniesList() {
		ArrayList<Company> list = ftsp.getCompaniesList();
		Assert.assertEquals(true, list.size()>0);
	}
	
	@Test
	public void testAddCompanies(){
		boolean successMessage = ftsp.addStocksToDB();
		Assert.assertEquals(true, successMessage);
	}
	
	

}
