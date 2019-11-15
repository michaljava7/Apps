package quiz.quiz_game.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String question;
    private String goodAnswer;
    private String badAnswer1;
    private String badAnswer2;
    private String badAnswer3;

    public Question() {
    }

    public Question(String question, String goodAnswer, String badAnswer1, String badAnswer2, String badAnswer3) {
        this.question = question;
        this.goodAnswer = goodAnswer;
        this.badAnswer1 = badAnswer1;
        this.badAnswer2 = badAnswer2;
        this.badAnswer3 = badAnswer3;
    }

    public String getQuestion() {
        return question;
    }

    public String getGoodAnswer() {
        return goodAnswer;
    }

    public String getBadAnswer1() {
        return badAnswer1;
    }

    public String getBadAnswer2() {
        return badAnswer2;
    }

    public String getBadAnswer3() {
        return badAnswer3;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj == this)
            return true;

        if (obj.getClass() != getClass())
            return false;

        Question question = (Question) obj;
        return this.question.equals(question.getQuestion());
    }
}
