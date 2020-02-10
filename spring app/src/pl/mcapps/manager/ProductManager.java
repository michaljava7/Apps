 package pl.mcapps.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import pl.mcapps.pojo.Product;

@Component
public class ProductManager {
	
	private List<Product> products;

	public ProductManager() {

		this.products = prepareProducts();
	}
	public int getSize() {
		return products.size();
	}
	private List<Product> prepareProducts() {

		List<Product> products = new ArrayList<>();
		Product prod = new Product("koszulki", "Koszulka1");
		Product secondProd = new Product("koszulki", "Koszulka2");
		Product thirdProd = new Product("spodnie", "Spodnie1");
		Product forthProd = new Product("spodnie", "Spodnie2");
		Product a5 = new Product("bluzy", "Bluza1");
		Product a6 = new Product("bluzy", "Koszulka2");
		Product a7 = new Product("bluzy", "Bluza3");
		Product a8 = new Product("bluzy", "Bluza4");
		products.add(prod);
		products.add(secondProd);
		products.add(thirdProd);
		products.add(forthProd);
		products.add(a5);
		products.add(a6);
		products.add(a7);
		products.add(a8);
		return products;
	}

	public List<Product> findAll() {

		return products;
	}

	public List<Product> findByCategory(String categoryName) {

		return products.stream() //
				.filter(product -> categoryMatch(categoryName, product)) 
//				.filter(p -> p.getCategory().equals(categoryName)) //
				.collect(Collectors.toList());
	}

	private boolean categoryMatch(String category, Product product) {

		String productCategory = product.getCategory();
		return category.equals(productCategory);
	}

	public List<String> getCategories() {

		List<String> result = new ArrayList<>();
		result.add("koszulki");
		result.add("spodnie");
		result.add("bluzy");
		return result;
	}
}
