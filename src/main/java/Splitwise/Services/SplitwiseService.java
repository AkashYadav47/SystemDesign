package Splitwise.Services;

import Splitwise.Resources.Splitwise;

import java.io.BufferedReader;
import java.io.FileReader;

public class SplitwiseService {
    public static void main (String[] args) {
        Splitwise splitwise = new Splitwise();
        for(int i=1; i<5; i++) {
            String s = "u" + i;
            splitwise.createUser(s);
        }
        try {
            BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\akashyad\\IdeaProjects\\MachineCoding\\src\\test\\SplitwiseTest.txt"));
            String s;
            while ( (s=bf.readLine() ) != null) {
                String[] api = s.split(" ", 2);
                switch (api[0]) {
                    case "EXPENSE":
                        System.out.println(splitwise.addExpense(api[1]));
                        break;
                    case "SHOW":
                        if (api.length == 1)
                            System.out.println(splitwise.showBalances(null));
                        else
                            System.out.println(splitwise.showBalances(api[1]));
                        break;
                        default: System.out.println("Invalid API call "+ api[0]);
                }
            }
        } catch (Exception e) { System.out.println("Invalid Input"+ e.getMessage());}
    }
}
