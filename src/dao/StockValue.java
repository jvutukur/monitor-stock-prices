package dao;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonProperty;

public class StockValue implements Comparable<StockValue> {
	private Timestamp timeStamp;
	
	@JsonProperty
	private BigDecimal stockPrice;
	
	@JsonProperty
	private String time; 
	
	public StockValue(Timestamp timestamp, BigDecimal stockPrice){
		this.timeStamp = timestamp;
		this.stockPrice = stockPrice;
		this.time = this.timeStamp.toString();
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}
	
	
	public int compareTo(StockValue secondStock) {

		

		//ascending order
		if(this.timeStamp.getTime() > secondStock.timeStamp.getTime()){
			return 1;
		}
		else if(this.timeStamp.getTime() < secondStock.timeStamp.getTime()){
			return -1;
		}
		else{
			return 0;
		}


	}
	
	
	
	
	
}
