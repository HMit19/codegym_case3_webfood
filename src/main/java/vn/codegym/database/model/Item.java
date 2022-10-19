package vn.codegym.database.model;

public class Item {
	private int id_product;
	private int id_bill;
	private int quantity;
	private String note;
	private long price;
	private Product product;

	public Item() {
	}

	public Item(int id_product, int id_bill, int quantity, String note) {
		this.id_product = id_product;
		this.id_bill = id_bill;
		this.quantity = quantity;
		this.note = note;
	}

	public Item(int id_product, int id_bill, int quantity, String note, long price) {
		this.id_product = id_product;
		this.id_bill = id_bill;
		this.quantity = quantity;
		this.note = note;
		this.price = price;
	}

	public Item(int id_product, int quantity) {
		this.id_product = id_product;
		this.quantity = quantity;
	}

	public Item(int id_product, int id_bill, int quantity, String note, long price, Product product) {
		this.id_product = id_product;
		this.id_bill = id_bill;
		this.quantity = quantity;
		this.note = note;
		this.price = price;
		this.product = product;
	}

	public Item(int id_product, int quantity, long price) {
		this.id_product = id_product;
		this.quantity = quantity;
		this.price = price;
	}


	public int getId_product() {
		return id_product;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	public int getId_bill() {
		return id_bill;
	}

	public void setId_bill(int id_bill) {
		this.id_bill = id_bill;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Item{" +
				"id_product=" + id_product +
				", id_bill=" + id_bill +
				", quantity=" + quantity +
				", note='" + note + '\'' +
				", price=" + price +
				", product=" + product +
				'}';
	}
}
