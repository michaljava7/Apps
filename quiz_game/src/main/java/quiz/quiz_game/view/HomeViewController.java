package quiz.quiz_game.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeViewController {

    @GetMapping("/home")
    public ModelAndView home (){
        return new ModelAndView("home");
    }
}
