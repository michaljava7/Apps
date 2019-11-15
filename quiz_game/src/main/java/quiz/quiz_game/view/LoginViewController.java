package quiz.quiz_game.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import quiz.quiz_game.model.User;
import quiz.quiz_game.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class LoginViewController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("login")
    public ModelAndView loginView() {
        return new ModelAndView("login");
    }

    @PostMapping("login")
    public String login(String email, String password, HttpServletRequest req, Model model) {

        User user = userRepository.findByEmail(email);

        if (user != null) {
            char[] passwordAtChar = user.getPassword();

            if (Arrays.equals(passwordAtChar, password.toCharArray())) {
                req.getSession().setAttribute("id", user.getId());
                return "redirect:home";
            }
        }

        model.addAttribute("messageType", "danger");
        model.addAttribute("message", "Invalid data");
        return "login";
    }
}
