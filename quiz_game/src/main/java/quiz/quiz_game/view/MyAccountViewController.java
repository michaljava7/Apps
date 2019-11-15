package quiz.quiz_game.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import quiz.quiz_game.model.User;
import quiz.quiz_game.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyAccountViewController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("myAccount")
    public ModelAndView myAccountView(HttpServletRequest req, Model model) {

        Integer id = (Integer) req.getSession().getAttribute("id");
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            model.addAttribute("user", user);
            return new ModelAndView("myAccount");
        }

        model.addAttribute("messageType", "danger");
        model.addAttribute("message", "Not Found");
        return new ModelAndView("myAccount");
    }
}
