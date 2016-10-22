package standalone.dao;

	public class Company {
		private String companyCode;
		private String companyName;
		
		public Company(String companyCode, String companyName){
			this.companyCode = companyCode;
			this.companyName = companyName;		
		}

		public String getCompanyCode() {
			return companyCode;
		}

		public void setCompanyCode(String companyCode) {
			this.companyCode = companyCode;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		
		
	}
