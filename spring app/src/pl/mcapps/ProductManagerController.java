package pl.mcapps;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import pl.mcapps.manager.ProductManager;
import pl.mcapps.pojo.Product;

@Controller
public class ProductManagerController {

	@Autowired
	private ProductManager manager;
	
	@GetMapping(value = "/products/{category}")
	public String products(@PathVariable("category") String category ,Model model,@RequestParam (value="count", required = false) String count) {
		List<Product> products;
		List<String> categories = manager.getCategories();
		model.addAttribute("categories",categories);
		if(category.equals("All")){
			products=manager.findAll();
		}
		else{
			products = manager.findByCategory(category);
		}
		if(count!= null){
			products=getByCount(count, products);
		}
		
		
		model.addAttribute("products",products);
		
		return "products";
	}
	
	private List<Product> getByCount(String count, List<Product> productsList){
		List<Product> result=new ArrayList<Product>();
		Integer range=Integer.valueOf(count);
		
		IntStream.range(0, range).forEach(index->result.add(productsList.get(index)));
		return result;
	}
	
	
}
