package quiz.quiz_game.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quiz.quiz_game.model.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> {

    List<Question> findAll();
}
