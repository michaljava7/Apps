package quiz.quiz_game.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quiz.quiz_game.model.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);
    List<User> findAll();
}
