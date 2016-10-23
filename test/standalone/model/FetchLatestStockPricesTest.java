package standalone.model;


import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import dao.Company;
import standalone.model.FetchLatestStockPrices;

import java.util.ArrayList;

import junit.framework.Assert;

public class FetchLatestStockPricesTest{

	 private static FetchLatestStockPrices ftsp;
	
	@BeforeClass
	public  static void setUpBeforeClass() throws Exception {
		ftsp =  new FetchLatestStockPrices();		
	}

	@Test
	public void test() {
		ArrayList<Company> list = ftsp.getCompaniesList();
		Assert.assertEquals(true, list.size()>0);
	}
	
	@Test
	public void test_1() {
		ArrayList<Company> list = ftsp.getCompaniesList();
		Assert.assertEquals(true, list.size()>0);
	}

}
