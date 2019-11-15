package quiz.quiz_game.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String LastName;
    private String email;
    private char[] password;
    private String role;

    @OneToOne
    private Statistics statistics;

    public User() {
    }

    public User(String firstName, String lastName, String email, char[] password, String role, Statistics statistics) {
        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.statistics = statistics;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return email;
    }

    public char[] getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
