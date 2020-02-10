package pl.mcapps.pojo;
public class Product {

	private String category;
	private String name;

	public Product(String category, String name) {
		super();
		this.category = category;
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}