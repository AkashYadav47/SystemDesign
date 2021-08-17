package LeaderBoard.Entities;

public class User {
    private final String name;
    private final String email;
    private String country;
    private int score;

    public User (String name, String email, String country){
        this.name = name;
        this.email = email;
        this.country = country;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
