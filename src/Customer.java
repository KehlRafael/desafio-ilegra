
public class Customer {
	
	private String cnpj;
	private String name;
	private String businessArea;
	
	// Nao sera feito aritmetica com cnpj
	public Customer(String cnpj, String name, String business) {
		this.setCnpj(cnpj);
		this.setName(name);
		this.setBusinessArea(business);
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

}
