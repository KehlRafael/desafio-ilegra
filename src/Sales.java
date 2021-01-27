import java.util.List;

public class Sales {
	
	private String saleId;
	private List<Item> items;
	private String salespersonName;
	private double totalSold;
	
	// Ja adicionei o totalSold pra facilitar a extracao dos dados
	// Mesma situacao do salario no totalSold.
	public Sales(String saleId, List<Item> items, String salespersonName, double totalSold) {
		this.setSaleId(saleId);
		this.setItems(items);
		this.setSalespersonName(salespersonName);
		this.setTotalSold(totalSold);
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getSalespersonName() {
		return salespersonName;
	}

	public void setSalespersonName(String salespersonName) {
		this.salespersonName = salespersonName;
	}

	public double getTotalSold() {
		return totalSold;
	}

	public void setTotalSold(double totalSold) {
		this.totalSold = totalSold;
	}

}
