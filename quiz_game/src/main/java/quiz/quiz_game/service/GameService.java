package quiz.quiz_game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.quiz_game.model.Game;
import quiz.quiz_game.model.User;
import quiz.quiz_game.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RandQuestionService rQ;

    @Autowired
    private UpdateStatisticsService uSS;

    @Autowired
    private ResponseTimeService rTS;

    private boolean holdOpponent;

    private List<Game> gameList = new ArrayList<>();

    public Game startGame(User user) {
        Game game = alreadyPlaying(user);
        if (game == null) {
            game = checkForGame();
            if (game == null) {
                game = new Game();
                game.setUser1(user);
                gameList.add(game);
                return game;
            } else {
                setUser(user, game);
                return game;
            }
        } else {
            return game;
        }
    }

    private Game checkForGame() {
        if (gameList.isEmpty()) {
            return null;
        } else {
            for (Game game : gameList) {
                if (game.getUser2() == null) {
                    return game;
                }
            }
        }
        return null;
    }

    public void postOperation(HttpServletRequest req, String answer) throws InterruptedException {
        User user = findById(req);
        Game game = alreadyPlaying(user);
        holdOpponent = true;
        checkAnswer(game, user, answer);
        changeFlag(game);
        holdUntilOpponentAnswer();
        restoreDefault(game);
    }

    private void setUser(User user, Game game) {
        game.setUser2(user);
    }

    private Game alreadyPlaying(User user) {
        for (Game game : gameList) {
            if (game.getUser1() != null && game.getUser1().getEmail().equals(user.getEmail())) {
                return game;
            } else if (game.getUser2() != null && game.getUser2().getEmail().equals(user.getEmail())) {
                return game;
            }
        }
        return null;
    }

    private void endGame(Game game) {
        Thread task = new Thread(() -> {
            try {
                Thread.sleep(2000);
                gameList.remove(game);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        task.start();
    }

    private void checkForWin(User user, Game game) {
        if (user.getEmail().equals(game.getUser1().getEmail())) {
            if (game.getPointUser1() > game.getPointUser2()) {
                uSS.updateWin(game.getUser1());
            } else if (game.getPointUser1() < game.getPointUser2()) {
                uSS.updateLost(game.getUser1());
            }
        } else {
            if (game.getPointUser2() > game.getPointUser1()) {
                uSS.updateWin(game.getUser2());
            } else if (game.getPointUser2() < game.getPointUser1()) {
                uSS.updateLost(game.getUser2());
            }
        }
    }

    private void restoreDefault(Game game) {
        if (game.getAnswerUser1() != null && game.getAnswerUser2() != null) {
            game.setAnswerUser1(null);
            game.setAnswerUser2(null);
            game.setQuestion(null);
        }
    }

    private void checkAnswer(Game game, User user, String answer) {
        if (user.getEmail().equals(game.getUser1().getEmail())) {
            if (game.getAnswerUser1() == null) {
                game.setAnswerUser1(answer);
                if (game.getAnswerUser1().equals(game.getQuestion().getGoodAnswer()) && rTS.checkResponseTimePlayer1(game)) {
                    int point = game.getPointUser1();
                    game.setPointUser1(point + 1);
                    uSS.updateGoodA(user);
                } else {
                    uSS.updateBadA(user);
                }
            }
        } else {
            if (game.getAnswerUser2() == null) {
                game.setAnswerUser2(answer);
                if (game.getAnswerUser2().equals(game.getQuestion().getGoodAnswer()) && rTS.checkResponseTimePlayer2(game)) {
                    int point = game.getPointUser2();
                    game.setPointUser2(point + 1);
                    uSS.updateGoodA(user);
                } else {
                    uSS.updateBadA(user);
                }
            }
        }
    }

    public boolean playOrEnd(Game game, User user) {
        if (rQ.checkNumberOfQuestion(game)) {
            game.setQuestion(rQ.randQuestion(game.getQuestionThatFall()));
            game.getQuestionThatFall().add(game.getQuestion());
            return true;
        } else {
            checkForWin(user, game);
            endGame(game);
            return false;
        }
    }

    private void holdUntilOpponentAnswer() throws InterruptedException {
        while (holdOpponent) {
            Thread.sleep(1000);
        }
    }

    private void changeFlag(Game game) {
        if (game.getAnswerUser1() != null && game.getAnswerUser2() != null)
            holdOpponent = false;
    }

    private User findById(HttpServletRequest req) {

        User user = null;
        Integer id = (Integer) req.getSession().getAttribute("id");
        if (userRepository.findById(id).isPresent()) {
            user = userRepository.findById(id).get();
        }
        return user;
    }
}
