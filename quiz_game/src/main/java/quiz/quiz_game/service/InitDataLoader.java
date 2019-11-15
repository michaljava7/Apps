package quiz.quiz_game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.quiz_game.model.Question;
import quiz.quiz_game.model.Statistics;
import quiz.quiz_game.model.User;
import quiz.quiz_game.repository.QuestionRepository;
import quiz.quiz_game.repository.StatisticsRepository;
import quiz.quiz_game.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class InitDataLoader {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    ReadQuestionService readQuestionService;

    @PostConstruct
    public void init() {

        statisticsRepository.save(new Statistics());
        statisticsRepository.save(new Statistics());
        statisticsRepository.save(new Statistics());

        userRepository.save(new User("Kamil", "Nowak", "kamil@gmail.com", "qwe".toCharArray(), "user", statisticsRepository.findById(1).get()));
        userRepository.save(new User("Admin", "Admin", "admin@gmail.com", "asd".toCharArray(), "user", statisticsRepository.findById(2).get()));

        List<String[]> readiedQuestion = readQuestionService.read();
        for (String[] element : readiedQuestion) {
            questionRepository.save(new Question(element[0], element[1], element[2], element[3], element[4]));
        }
    }
}
