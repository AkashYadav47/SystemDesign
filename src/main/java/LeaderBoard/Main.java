package LeaderBoard;

public class Main {

    public static void main(String[] args) {
        BoardOutput bo = new BoardOutput();
        bo.addUser("Nikhil","India", "nikhil@fk");
        bo.addUser("Rahul","India", "rahul@fk");
        bo.updateScore("rahul@fk",1);
        bo.updateScore("nikhil@fk",5);
        bo.addUser("Karan","Argentina", "karan@fk");
        bo.updateScore("karan@fk",1);
        bo.topUsers(3);
        bo.getUsersWithScore(1);
        bo.topUsers(2,"India");
        bo.search("Nikhil",null,"India");
        bo.search(null,null,"India");

        bo.searchRange(1,2);
        bo.searchName("Nik");
    }
}
