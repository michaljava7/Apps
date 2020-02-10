package pl.mcapps;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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

public class LogoutController {
	
		
	

	@GetMapping (value = "/logout")
	public String logout(Model model, HttpSession session) {
		 
		session.invalidate();
		return "redirect:/";
	}



}
