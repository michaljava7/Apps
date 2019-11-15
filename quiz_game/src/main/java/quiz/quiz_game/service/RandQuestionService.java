package quiz.quiz_game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.quiz_game.model.Game;
import quiz.quiz_game.model.Question;
import quiz.quiz_game.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class RandQuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    private Random random = new Random();

    public Question randQuestion(List<Question> questionsThatFall) {
        List<Question> questionList = questionRepository.findAll();
        Question question;

        while (true) {
            int rand = random.nextInt(questionList.size());
            question = questionList.get(rand);

            if (!questionsThatFall.contains(question))
                break;
        }

        return question;
    }

    public List<String> randAnswers(Question question) {
        List<String> answers = new ArrayList<>();
        if (question != null) {
            answers.add(question.getGoodAnswer());
            answers.add(question.getBadAnswer1());
            answers.add(question.getBadAnswer2());
            answers.add(question.getBadAnswer3());
            Collections.shuffle(answers);
        }
        return answers;
    }

    public boolean checkNumberOfQuestion(Game game) {
        int number = game.getQuestionToEnd();
        if (number > 0) {
            game.setQuestionToEnd(number - 1);
            return true;
        }else{
            return false;
        }
    }
}
