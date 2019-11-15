package quiz.quiz_game.model;

import javax.persistence.*;

@Entity
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int win;
    private int lost;
    private int goodAnswers;
    private int badAnswers;

    @OneToOne(mappedBy = "statistics")
    private User user;

    public Statistics() {
        this.win = 0;
        this.lost = 0;
        this.goodAnswers = 0;
        this.badAnswers = 0;
        this.user = null;
    }

    public int getId() {
        return id;
    }

    public int getWin() {
        return win;
    }

    public int getLost() {
        return lost;
    }

    public int getGoodAnswers() {
        return goodAnswers;
    }

    public int getBadAnswers() {
        return badAnswers;
    }

    public User getUser() {
        return user;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public void setGoodAnswers(int goodAnswers) {
        this.goodAnswers = goodAnswers;
    }

    public void setBadAnswers(int badAnswers) {
        this.badAnswers = badAnswers;
    }
}
