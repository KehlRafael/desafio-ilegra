
public class Item {
	
	private String id;
	private int quantity;
	private double price;
	
	// Mesma situacao do salario no price
	public Item(String id, int quantity, double price) {
		this.setId(id);
		this.setQuantity(quantity);
		this.setPrice(price);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
