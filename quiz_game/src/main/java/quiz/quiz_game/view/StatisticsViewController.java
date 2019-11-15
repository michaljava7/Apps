package quiz.quiz_game.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import quiz.quiz_game.model.Statistics;
import quiz.quiz_game.model.User;
import quiz.quiz_game.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StatisticsViewController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("statistics")
    public ModelAndView statisticsView(HttpServletRequest req, Model model) {
        User user;
        Statistics statistics = null;

        Integer id = (Integer) req.getSession().getAttribute("id");
        if (userRepository.findById(id).isPresent()) {
            user = userRepository.findById(id).get();
            statistics = user.getStatistics();
        }

        model.addAttribute("statistics", statistics);
        return new ModelAndView("statistics");
    }
}
