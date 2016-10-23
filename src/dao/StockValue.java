package dao;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonProperty;

public class StockValue implements Comparable<StockValue> {
	
	private Timestamp timestamp;
	
	@JsonProperty
	private BigDecimal stockPrice;
	
	@JsonProperty
	private String time; 
	
	public StockValue(Timestamp timestamp, BigDecimal stockPrice){
	
		this.stockPrice = stockPrice;
		this.time = timestamp.toString();
		this.time = this.time.substring(0,this.time.length()-2);
		this.timestamp = timestamp;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	

	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}
	
	
	public int compareTo(StockValue secondStock) {

		

		//ascending order
		if(Timestamp.valueOf(this.time).getTime() > Timestamp.valueOf(secondStock.time).getTime()){
			return 1;
		}
		else if(Timestamp.valueOf(this.time).getTime() < Timestamp.valueOf(secondStock.time).getTime()){
			return -1;
		}
		else{
			return 0;
		}


	}
	
	
	
	
	
}
