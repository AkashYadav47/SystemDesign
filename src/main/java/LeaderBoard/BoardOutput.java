package LeaderBoard;

import LeaderBoard.Entities.User;
import LeaderBoard.Resources.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BoardOutput {
    private Board b;
    public BoardOutput () {
        b = new Board();
    }
    public void addUser (String name, String country, String email) {
        System.out.println(b.addUser(name,country,email));
    }
    public void updateScore (String email,int score){
        b.updateScore(email,score);
    }

    public void topUsers (int k) {
        List<User> top = b.topUsers(k);
        System.out.println("Top "+ k+ " users are ");
        for(User u : top)
            System.out.print(u.getEmail()+" ");
        System.out.println();
    }

    public void topUsers (int k, String country) {
        List<User> top = b.topUsers(k,country);
        System.out.println("Top "+ k+ " users from "+country+" are ");
        for(User u : top)
            System.out.print(u.getEmail()+" ");
        System.out.println();
    }

    public void getUsersWithScore (int score) {
        List<User> top = b.getUsersWithScore(score);
        System.out.println("Users with score "+ score + " are ");
        for(User u : top)
            System.out.print(u.getEmail()+" ");
        System.out.println();
    }

    //Integer Class
    public void search (String name, Integer score, String country) {
        List<User> top = b.search(name,score,country);
        for(User u : top)
            System.out.print(u.getEmail()+" ");
        System.out.println();
    }
    public void searchRange (int low, int high) {
        List<User> res = b.searchRange(low,high);
        System.out.println("Users with rank between " + low + " and " + high +" are ");
        for(User u:res) {
            System.out.print(u.getEmail() + " ");
        }
        System.out.println();
    }

    public void searchName (String partialName) {
        List<User> res = b.searchName(partialName);
        System.out.println("Users with name matching " + partialName + " are ");
        for(User u:res) {
            System.out.print(u.getEmail() + " ");
        }
        System.out.println();
    }

}
