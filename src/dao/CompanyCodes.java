package dao;

import org.codehaus.jackson.annotate.JsonProperty;



public class CompanyCodes {

	@JsonProperty
	private String companyCode;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public CompanyCodes(String companyCode) {		
		this.companyCode = companyCode;
	}
	
	public CompanyCodes(){
		
	}
	
}
