package rest.api;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import dao.Company;
import dao.StockValue;
import standalone.model.FetchLatestStockPrices;

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
	public void testGetComanyHistorty_fail1() {
		List<StockValue> stocksList = cs.getComanyHistorty("hahaha");
		Assert.assertEquals(true,(stocksList.size()==0));
	}
	
	@Test
	public void testGetComanyHistorty() {
		List<StockValue> stocksList = cs.getComanyHistorty("INTL");
		Assert.assertEquals(true,(stocksList.size()>0));
	}

}
