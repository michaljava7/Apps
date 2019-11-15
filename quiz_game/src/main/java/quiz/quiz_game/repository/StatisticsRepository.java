package quiz.quiz_game.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quiz.quiz_game.model.Statistics;
import quiz.quiz_game.model.User;

@Repository
public interface StatisticsRepository extends CrudRepository<Statistics, Integer> {
    Statistics findByUser(User user);
}
