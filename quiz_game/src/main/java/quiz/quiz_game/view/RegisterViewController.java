package quiz.quiz_game.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import quiz.quiz_game.model.Statistics;
import quiz.quiz_game.model.User;
import quiz.quiz_game.repository.StatisticsRepository;
import quiz.quiz_game.repository.UserRepository;

@Controller
public class RegisterViewController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @GetMapping("/register")
    public ModelAndView registerView() {
        return new ModelAndView("register");
    }

    @PostMapping("register")
    public String register(String firstName, String lastName, String email, String password, String password2, Model model) {

        if (password.equals(password2)) {
            Statistics statistics = new Statistics();
            statisticsRepository.save(statistics);

            User user = new User(firstName, lastName, email, password.toCharArray(), "user", statistics);
            userRepository.save(user);
            return "redirect:login";
        }

        model.addAttribute("messageType", "danger");
        model.addAttribute("message", "invalid data");
        return "register";
    }
}
