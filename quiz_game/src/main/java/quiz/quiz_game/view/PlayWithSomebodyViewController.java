package quiz.quiz_game.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import quiz.quiz_game.model.Game;
import quiz.quiz_game.model.User;
import quiz.quiz_game.repository.UserRepository;
import quiz.quiz_game.service.GameService;
import quiz.quiz_game.service.RandQuestionService;
import quiz.quiz_game.service.ResponseTimeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PlayWithSomebodyViewController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RandQuestionService rQ;

    @Autowired
    private ResponseTimeService rTS;

    @GetMapping("playWithSomebody")
    public ModelAndView playWithSomebodyView(Model model, HttpServletRequest req) {
        return gameView(model, req);
    }

    @PostMapping("playWithSomebody")
    public String playWithSomebody(HttpServletRequest req, String answer) throws InterruptedException {
        gameService.postOperation(req, answer);
        return "redirect:playWithSomebody";
    }

    private ModelAndView gameView(Model model, HttpServletRequest req) {
        Game game = gameService.startGame(findById(req));
        User user = findById(req);
        if (game.getUser2() != null) {
            if (game.getQuestion() == null) {
                if (!gameService.playOrEnd(game, user)) {
                    setEndOfGameView(game,model);
                    return new ModelAndView("playWithSomebody");
                }
            }
            return setGameView(model, game, req);
        } else {
            model.addAttribute("player1", game.getUser1());
            model.addAttribute("player2", game.getUser2());
            return new ModelAndView("playWithSomebody");
        }
    }

    private ModelAndView setGameView(Model model, Game game, HttpServletRequest req) {

        List<String> answers = rQ.randAnswers(game.getQuestion());
        model.addAttribute("player1", game.getUser1());
        model.addAttribute("player2", game.getUser2());
        model.addAttribute("pointPlayer1", game.getPointUser1());
        model.addAttribute("pointPlayer2", game.getPointUser2());
        model.addAttribute("questionToEnd", game.getQuestionToEnd());

        model.addAttribute("question", game.getQuestion().getQuestion());
        model.addAttribute("a", answers.get(0));
        model.addAttribute("b", answers.get(1));
        model.addAttribute("c", answers.get(2));
        model.addAttribute("d", answers.get(3));

        rTS.startTime(findById(req), game);
        return new ModelAndView("playWithSomebody");
    }

    private User findById(HttpServletRequest req) {

        User user = null;
        Integer id = (Integer) req.getSession().getAttribute("id");
        if (userRepository.findById(id).isPresent()) {
            user = userRepository.findById(id).get();
        }
        return user;
    }

    private void setEndOfGameView(Game game, Model model) {
        if (game.getPointUser1() > game.getPointUser2()) {
            model.addAttribute("messageType1", "success");
            model.addAttribute("message1", "win");
            model.addAttribute("messageType2", "danger");
            model.addAttribute("message2", "lost");
        } else if (game.getPointUser2() > game.getPointUser1()) {
            model.addAttribute("messageType1", "danger");
            model.addAttribute("message1", "lost");
            model.addAttribute("messageType2", "success");
            model.addAttribute("message2", "win");
        } else {
            model.addAttribute("message1", "draw");
            model.addAttribute("message2", "draw");
        }

        model.addAttribute("player1", game.getUser1());
        model.addAttribute("pointPlayer1", game.getPointUser1());
        model.addAttribute("player2", game.getUser2());
        model.addAttribute("pointPlayer2", game.getPointUser2());
    }
}
