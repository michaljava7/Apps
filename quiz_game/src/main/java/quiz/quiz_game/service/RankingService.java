package quiz.quiz_game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.quiz_game.model.User;
import quiz.quiz_game.repository.UserRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class RankingService {

    @Autowired
    private UserRepository userRepository;

    public List<User> sortRanking() {
        List<User> users = userRepository.findAll();

        users.sort(Comparator.comparingInt(o -> o.getStatistics().getWin()));

        Collections.reverse(users);
        return users;
    }
}
