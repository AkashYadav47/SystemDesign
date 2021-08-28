package Splitwise.Resources;

import Splitwise.Entities.Expense;
import Splitwise.Exceptions.InvalidShareException;
import Splitwise.Repositories.SplitwiseDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Splitwise {
    private final SplitwiseDAO splitwiseDAO;

    public Splitwise() {
        splitwiseDAO = new SplitwiseDAO();
    }

    public String addExpense (String s) {
        //System.out.println(s);
        try {
            Expense expense = expenseParser(s);
            return "New Expense Added";
        } catch (InvalidShareException e) {
            return "Failed to create Expense. Reason : " + e.getMessage();
        }
    }
    public List<String> showBalances (String s) {
        HashMap<String,Double> balances = splitwiseDAO.showBalances(s);
        return balanceParser (balances);
    }

    public void createUser(String name) {
        splitwiseDAO.createUser(name);
    }

    private List<String> balanceParser (HashMap<String,Double> balances) {
        List<String> balanceString = new ArrayList<>();

        for(Map.Entry<String,Double> transaction : balances.entrySet()) {
            String key = transaction.getKey();
            Double amt = transaction.getValue();
            balanceString.add(generateBalance(key,amt));
        }
        if(balanceString.isEmpty()) {
            balanceString.add("No Balance");
        }
        return balanceString;
    }

    private String generateBalance (String key, Double amount) {
        String[] users = key.split("_");
        String result;

        if(amount < 0) {
            result = users[0] + " owes " + users[1] + ": " + String.format("%.2f",-amount);
        } else {
            result = users[1] + " owes " + users[0] + ": " + String.format("%.2f",amount);
        }
        return result;
    }


    private Expense expenseParser (String s) throws InvalidShareException {
        int argc =0;
        String[] args  = s.split(" ");
        String payer = args[argc++];
        double amount = Double.parseDouble(args[argc++]);
        int userCount = Integer.parseInt(args[argc++]);

        List<String> contributors = new ArrayList<>();
        for(int i =0; i<userCount; i++) {
            contributors.add(args[argc++]);
            //System.out.println(args[argc]);
        }

        String expenseType = args[argc++];

        List<Double> contributions = new ArrayList<>();
        if(!expenseType.equals("EQUAL")) {
            for (int i = 0; i < userCount; i++) {
                contributions.add(Double.parseDouble(args[argc++]));
            }
        }
        return createExpense(null,payer,amount,contributors,expenseType,contributions);
    }
    private Expense createExpense (String expenseName,String payer, double amount, List<String> contributors, String expenseType, List<Double> contributions) throws InvalidShareException {
        return splitwiseDAO.createExpense(expenseName, payer, amount, contributors, expenseType, contributions);
    }
}
