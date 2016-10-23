package dao;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;;

public class CompanyCodes {

	@JsonProperty
	private ArrayList<String> companies;

	public ArrayList<String> getCompanies() {
		return companies;
	}

	public void setCompanies(ArrayList<String> companies) {
		this.companies = companies;
	}

	public CompanyCodes(ArrayList<String> companies) {
		super();
		this.companies = companies;
	}
	
	
}
