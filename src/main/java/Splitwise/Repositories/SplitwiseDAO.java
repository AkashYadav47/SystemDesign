package Splitwise.Repositories;

import Splitwise.Entities.Expense;
import Splitwise.Entities.User;
import Splitwise.Exceptions.InvalidShareException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitwiseDAO {
    private int userIDs;
    HashMap<String,User> group;
    HashMap<String, Double> ledger;

    public SplitwiseDAO() {
        userIDs = 0;
        group = new HashMap<>();
        ledger = new HashMap<>();
    }

    public void createUser (String name) {
        int id = generateUserId();
        User user = new User(id,name);
        group.put(name,user);
    }

    public Expense createExpense(String expenseName, String payerName, double amount, List<String> contributorNames, String expenseType, List<Double> contributions) throws InvalidShareException {
        User payer = group.get(payerName);
        List<User> contributors = new ArrayList<>();
        for(String contributorName : contributorNames) {
            contributors.add(group.get(contributorName));
        }
        Expense.ExpenseType type;
        switch (expenseType) {
            case "EXACT": type = Expense.ExpenseType.EXACT; break;
            case "PERCENT":type = Expense.ExpenseType.PERCENT; break;
            default: type = Expense.ExpenseType.EQUAL; break;
        }

        Expense expense =  new Expense(expenseName,payer,amount,contributors,type,contributions);

        updateLedger(expense);
        return expense;
    }

    private void updateLedger (Expense expense) {
        User payer = expense.getPayer();
        int payerId = payer.getUserID();
        String payerName = payer.getName();
        List<Double> contributions =  expense.getContributions();
        List<User> contributors = expense.getContributors();
        for(int i=0; i<contributors.size(); i++) {
            if( !contributors.get(i).equals(payer)) {
                int userID = contributors.get(i).getUserID();
                String userName = contributors.get(i).getName();
                Double amt = contributions.get(i);
                String key;
                if(payerId < userID) {
                    key = payerName + "_" + userName;
                } else {
                    key = userName + "_" + payerName;
                    amt = -amt;
                }
                if(ledger.containsKey(key)) {
                    amt += ledger.get(key);
                    if(amt == 0) {
                        ledger.remove(key);
                    }
                }
                if(amt != 0) {
                    ledger.put(key, amt);
                }
            }
        }
    }

    public HashMap<String,Double>  showBalances(String s) {
        HashMap<String,Double> result = new HashMap<>();
        if(s==null) {
            result = ledger;
            //Make copy
        } else {
            User user = group.get(s);
            for(Map.Entry<String,User> u : group.entrySet()) {
                if(!user.equals(u.getValue())) {
                    int userID = user.getUserID();
                    int uID = u.getValue().getUserID();
                    String key;
                    if(userID < uID) {
                        key = user.getName() + "_" + u.getValue().getName();
                    } else {
                        key = u.getValue().getName() + "_" + user.getName();
                    }
                    if(ledger.containsKey(key)) {
                        result.put(key,ledger.get(key));
                    }
                }
            }
        }
        return result;
    }

    private int generateUserId () {
        return this.userIDs++;
    }

}
