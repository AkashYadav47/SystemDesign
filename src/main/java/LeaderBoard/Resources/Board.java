package LeaderBoard.Resources;

import LeaderBoard.Entities.User;

import java.util.*;

public class Board {
    private List<User> pq;
    private HashMap<String,User> map;
    public Board () {
        pq = new ArrayList<>();
        map = new HashMap<>();
    }
    public String addUser (String name, String country, String email) {
        User u = new User(name,email,country);
        pq.add(u);
        map.put(email,u);
        return name+" added to the leaderboard.";
    }
    public void updateScore (String email,int score){
        User u = map.get(email);
        u.setScore(score);
        pq.remove(u);
        addInList(u);
        //pq.add(u);
    }

    public List<User> topUsers (int k) {
        List<User> top = new ArrayList<>();
        for(int i=0; i<k; i++){
            top.add(pq.get(i));
        }
        return top;
    }

    public List<User> topUsers (int k, String country) {
        List<User> top = new ArrayList<>();
        int i =0;
        while(k>0) {
            User u = pq.get(i);
            if (u.getCountry().equals(country)) {
                top.add(u);
                k--;
            }
            i++;
        }
        return top;
    }

    public List<User> getUsersWithScore (int score) {
        List<User> res = new ArrayList<>();
        for(User u:pq){
            if(u.getScore() == score) {
                res.add(u);
            } else if(u.getScore() < score) {
                break;
            }
        }
        return res;
    }

    //Integer Class
    public List<User> search (String name, Integer score, String country) {
        List<User> res = new ArrayList<>();
        for(User u:pq){
            if(name != null && !u.getName().equals(name) ) {
                continue;
            }
            if(score != null && u.getScore() != score) {
                continue;
            }
            if(country!=null && !u.getCountry().equals(country) ) {
                continue;
            }
            res.add(u);
        }
        return res;
    }

    public List<User> searchRange (int low, int high) {
        List<User> res = new ArrayList<>();
        for(int i=low-1; i<high && i<pq.size(); i++){
            res.add(pq.get(i));
        }
        return res;
    }

    public List<User> searchName (String partialName) {
        List<User> res = new ArrayList<>();
        for(User u:pq){
            if(partialName != null && u.getName().contains(partialName) ) {
                res.add(u);
            }
        }
        return res;
    }

    private void addInList (User u) {
        int i;
        for(i=0; i<pq.size(); i++) {
            if(pq.get(i).getScore()<u.getScore())
                break;
        }
        pq.add(i,u);
    }
//    private void addInList (User u) {
//        int  i = Collections.binarySearch(pq,u,new SortbyScore());
//        pq.add(i+1,u);
//    }
//    class SortbyScore implements Comparator<User> {
//        // Used for sorting in ascending order of name
//        public int compare(User a, User b) {
//            if(a.getScore() < b.getScore())
//                return 1;
//            else
//                return 0;
//        }
//    }
}
