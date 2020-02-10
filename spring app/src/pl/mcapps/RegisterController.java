package pl.mcapps;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.mcapps.pojo.Product;
import pl.mcapps.pojo.User;
import pl.mcapps.validators.UserValidator;

@Controller

public class RegisterController {
	@Autowired
	private UserValidator validator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	@RequestMapping(value = "/register")
	public String register(Model model) {

		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping(value = "/register")
	public String postRegister(@ModelAttribute("user") @Validated User user, BindingResult result) {
		if(result.hasErrors()) {
			return "register";
		}
		return "home";
	}

}
