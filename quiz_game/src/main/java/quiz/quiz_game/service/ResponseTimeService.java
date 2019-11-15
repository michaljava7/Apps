package quiz.quiz_game.service;

import org.springframework.stereotype.Service;
import quiz.quiz_game.model.Game;
import quiz.quiz_game.model.User;

@Service
public class ResponseTimeService {

    private void setPlayer1Time(Game game) {
        game.setTimeAnswerUser1(System.currentTimeMillis());
    }

    private void setPlayer2Time(Game game) {
        game.setTimeAnswerUser2(System.currentTimeMillis());
    }

    public boolean checkResponseTimePlayer1(Game game) {
        long responseTime = System.currentTimeMillis() - game.getTimeAnswerUser1();
        responseTime /= 1000;
        return responseTime <= 30;
    }

    public boolean checkResponseTimePlayer2(Game game) {
        long responseTime = System.currentTimeMillis() - game.getTimeAnswerUser2();
        responseTime /= 1000;
        return responseTime <= 30;
    }

    public void startTime (User user, Game game){
        if (user.getEmail().equals(game.getUser1().getEmail())){
            setPlayer1Time(game);
        }else if (user.getEmail().equals(game.getUser2().getEmail())){
            setPlayer2Time(game);
        }
    }
}
