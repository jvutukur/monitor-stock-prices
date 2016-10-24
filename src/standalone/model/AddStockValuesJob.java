package standalone.model;

import java.util.Timer;

public class AddStockValuesJob {
	
	public void startStockUpdateJob(){
		Timer timer = new Timer();
		Subject subject = Subject.getSubject();
		timer.scheduleAtFixedRate(subject, 0L, (300*1000L));		
	}

}
