package standalone.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import junit.framework.Assert;

import org.junit.Test;

public class ObserverTest {

	private Observer observer;
	@Test
	public void getStockPriceTest_SuccessScenario() {
		observer = new Observer("INTL",null);
		BigDecimal stockPrice = observer.getStockPrice("INTL");
		Assert.assertNotSame("Got Valid Stock Price for INTL", "0",stockPrice.toString());		
	}
		
	@Test
	public void testGetStockPriceFailScenario(){
		observer = new Observer("hahaha",null);
		BigDecimal stockPrice = observer.getStockPrice("hahaha");
		Assert.assertEquals("0",stockPrice.toString());
	}

	@Test
	public void testAddSingleStockToDBSuccessScenario(){
		observer = new Observer("INTL",null);
		boolean successMessage  = observer.addSingleStockToDB();
		Assert.assertEquals(true,successMessage);
	}	
	
	@Test
	public void testAddSingleStockToDBFailScenario_2(){
		observer = new Observer("hahaha",null);
		boolean successMessage = observer.addSingleStockToDB();
		Assert.assertEquals(false, successMessage);
	}
	
}
