package standalone.model;

import java.util.Timer;

public class AddStockValuesJob {
	
	public void startStockUpdateJob(){
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new FetchLatestStockPrices(), 0L, (300*1000L));		
	}

}
