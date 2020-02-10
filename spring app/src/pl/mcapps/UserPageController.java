package pl.mcapps;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.mcapps.pojo.Product;
import pl.mcapps.pojo.User;
import pl.mcapps.validators.UserValidator;

@Controller
@SessionAttributes("loggedUser")
public class UserPageController {
	
	@GetMapping(value = "/userpage")
	public String userPage(Model model, @SessionAttribute("loggedUser") User user) {
		
		model.addAttribute("usr", user);
		return "userpage";
	}
@ExceptionHandler(ServletRequestBindingException.class)
public String handle() {
	 return "redirect:/login";
}
	
}
