package pl.mcapps;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.mcapps.pojo.Product;
import pl.mcapps.pojo.User;
import pl.mcapps.validators.UserValidator;

@Controller
@SessionAttributes("loggedUser")
public class LoginController {
	
	@PostMapping(value = "/login")
	public String loginPost(Model model ,@ModelAttribute("user") User user ) {
		model.addAttribute("loggedUser", user);
		return "redirect:userpage";}
	
	

	@GetMapping (value = "/login")
	public String login(Model model) {
		 
		model.addAttribute("user", new User());
		return "login";
	}



}
