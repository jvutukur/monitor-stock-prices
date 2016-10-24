package dao;
import java.util.ArrayList;


public class StockHistory {
	
	private String company_code;
	private String company_fullName;
	private ArrayList<StockValue> stockHistory;
	public StockHistory(String company_code, String company_fullName,
			ArrayList<StockValue> stockHistory) {
		super();
		this.company_code = company_code;
		this.company_fullName = company_fullName;
		this.stockHistory = stockHistory;
	}
	public StockHistory(){
		
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getCompany_fullName() {
		return company_fullName;
	}
	public void setCompany_fullName(String company_fullName) {
		this.company_fullName = company_fullName;
	}
	public ArrayList<StockValue> getStockHistory() {
		return stockHistory;
	}
	public void setStockHistory(ArrayList<StockValue> stockHistory) {
		this.stockHistory = stockHistory;
	}
	

}
