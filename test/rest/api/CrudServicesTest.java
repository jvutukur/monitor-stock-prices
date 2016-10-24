package rest.api;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import dao.Company;
import dao.StockHistory;
import dao.StockValue;


public class CrudServicesTest {

	 private static CrudServices  cs;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cs = new CrudServices();
	}

	@Test
	public void testAddCompany_FailScenario1() {
		cs.addCompany("INTL");
		String message = cs.addCompany("INTL");
		Assert.assertEquals(true, message.contains("already exists in database"));
	}
	
	@Test
	public void testAddCompany_FailScenario2(){		
		String message = cs.addCompany("hahaha");
		Assert.assertEquals("No such company exist", message);
	}

	@Test
	public void testGetComaniesList() {
		List<Company> companyList= cs.getComaniesList();
		Assert.assertEquals(true,(companyList.size()>0));
	}

	@Test
	public void testGetComaniesList_scenario2() {
		List<Company> companyList= cs.getComaniesList();
		Assert.assertEquals(true,(companyList.size()>=1));
	}

	@Test
	public void testGetCompanyHistorty_fail1() {
		StockHistory stockHistory = cs.getComanyHistorty("hahaha");
		Assert.assertEquals(true,(stockHistory.getCompany_fullName().equals("")));
	}
	
	@Test
	public void testGetCompanyHistorty() {
		StockHistory stockHistory = cs.getComanyHistorty("INTL");
		Assert.assertEquals(true,(stockHistory!=null));
	}
	
	@Test
	public void testDelelteCompany(){
		String message = cs.deleteCompany("hahaha");
		Assert.assertEquals("No such company exist", message);
	}
	
	@Test
	public void testGetCompanyNameSuccessScenario(){
		String companyName = cs.getCompanyName("INTL");
		Assert.assertEquals( "INTL FCStone Inc.",companyName);
	}
	
	@Test
	public void testGetCompanyNameFailScenario(){
		String companyName = cs.getCompanyName("hahaha");
		Assert.assertEquals("N/A",companyName);
	}
	

}
