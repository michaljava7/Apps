package quiz.quiz_game.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private User user1;
    private User user2;
    private String answerUser1;
    private String answerUser2;
    private int pointUser1;
    private int pointUser2;
    private int questionToEnd;
    private long timeAnswerUser1;
    private long timeAnswerUser2;
    private Question question;
    private List<Question> questionThatFall;

    public Game() {
        this.user1 = null;
        this.user2 = null;
        this.answerUser1 = null;
        this.answerUser2 = null;
        this.pointUser1 = 0;
        this.pointUser2 = 0;
        this.questionToEnd = 10;
        this.timeAnswerUser1 = 0;
        this.timeAnswerUser2 = 0;
        this.question = null;
        this.questionThatFall = new ArrayList<>();
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public String getAnswerUser1() {
        return answerUser1;
    }

    public void setAnswerUser1(String answerUser1) {
        this.answerUser1 = answerUser1;
    }

    public String getAnswerUser2() {
        return answerUser2;
    }

    public void setAnswerUser2(String answerUser2) {
        this.answerUser2 = answerUser2;
    }

    public int getPointUser1() {
        return pointUser1;
    }

    public void setPointUser1(int pointUser1) {
        this.pointUser1 = pointUser1;
    }

    public int getPointUser2() {
        return pointUser2;
    }

    public void setPointUser2(int pointUser2) {
        this.pointUser2 = pointUser2;
    }

    public int getQuestionToEnd() {
        return questionToEnd;
    }

    public void setQuestionToEnd(int questionToEnd) {
        this.questionToEnd = questionToEnd;
    }

    public long getTimeAnswerUser1() {
        return timeAnswerUser1;
    }

    public void setTimeAnswerUser1(long timeAnswerUser1) {
        this.timeAnswerUser1 = timeAnswerUser1;
    }

    public long getTimeAnswerUser2() {
        return timeAnswerUser2;
    }

    public void setTimeAnswerUser2(long timeAnswerUser2) {
        this.timeAnswerUser2 = timeAnswerUser2;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Question> getQuestionThatFall() {
        return questionThatFall;
    }

    public void setQuestionThatFall(List<Question> questionThatFall) {
        this.questionThatFall = questionThatFall;
    }
}
