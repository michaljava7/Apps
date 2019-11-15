package quiz.quiz_game.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import quiz.quiz_game.service.RankingService;

@Controller
public class RankingViewController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("ranking")
    public ModelAndView ranking(Model model){

        model.addAttribute("ranking", rankingService.sortRanking());
        return new ModelAndView("ranking");
    }
}
