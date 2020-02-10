package pl.mcapps;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.mcapps.manager.ProductManager;
import pl.mcapps.pojo.Product;

@Controller

public class HomeController {
	@Autowired
	private ProductManager manager;
	@RequestMapping(value = "/")
	public String home(Model model) {
		List<Product> list=new ArrayList<Product>();
		list.add(new Product("pralka", "pralka Amica aa22"));
		list.add(new Product("pralka", "pralka Bosh aaa23"));
		list.add(new Product("pralka", "pralka Zanussi a2"));
		list.add(new Product("lodówka", "pralka Amica efef"));
		list.add(new Product("lodówka", "pralka Bosh efef"));
		list.add(new Product("lodówka", "pralka Zanussi efef"));
		model.addAttribute("nick", "Jan");
		model.addAttribute("products", list);
		model.addAttribute("size",new Integer(manager.getSize()));
		return "home";
	}

}
