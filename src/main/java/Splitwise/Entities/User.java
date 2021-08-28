package Splitwise.Entities;

public class User {
    final public int userID;
    final private String name;
    private String email;
    private String mobNumber;
    private Double balance;

    public User(int userID, String name) {
        this.userID = userID;
        this.name = name;
        balance = 0.0;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }
}
