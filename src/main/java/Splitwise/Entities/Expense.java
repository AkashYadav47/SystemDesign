package Splitwise.Entities;

import Splitwise.Exceptions.InvalidShareException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Expense {
    private String name;
    private User payer;
    private Double amount;
    List<User> contributors;
    List<Double> contributions;

    public enum ExpenseType {
        EQUAL,
        EXACT,
        PERCENT
    }

    public Expense(String name, User payer, Double amount, List<User> contributors, ExpenseType type, List<Double> shares) throws InvalidShareException {
        this.name = Objects.requireNonNullElse(name, "NewExpense");
        this.payer = payer;
        this.amount = amount;
        this.contributors = contributors;
        this.contributions = new ArrayList<>();
        updateExpense(type,shares);
    }

    private void updateExpense (ExpenseType type, List<Double> shares) throws InvalidShareException {
        switch (type){
            case EQUAL:
                updateEqual();
                break;
            case EXACT:
                updateExact(shares);
                break;
            case PERCENT:
                updatePercent(shares);
                break;
        }
    }
    private void updateEqual() {
        Double split = amount/contributors.size();

        for(int i=0; i < contributors.size(); i++) {
            contributions.add(split);
        }
    }
    private void updateExact (List<Double> shares) throws InvalidShareException {
        Double total = 0.0;
        for (Double share : shares) {
            total += share;
        }
        if (! total.equals(amount) ) {
            throw new InvalidShareException("Individual share " + total + " doesnt add up to total expense " + amount);
        }
        contributions.addAll(shares);
    }

    private void updatePercent (List<Double> shares) throws InvalidShareException {
        Double totalPercent = 0.0;
        for (Double percent : shares) {
            totalPercent += percent;
        }
        if (totalPercent != 100.0) {
            throw new InvalidShareException("Percent share doesnt add up to 100");
        }
        for (Double percent : shares) {
            Double split = amount * percent / 100;
            contributions.add(split);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getPayer() {
        return payer;
    }

    public Double getAmount() {
        return amount;
    }

    public List<User> getContributors() {
        return contributors;
    }

    public List<Double> getContributions() {
        return contributions;
    }

}
