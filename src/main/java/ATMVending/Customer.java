package ATMVending;

public class Customer extends Thread {
    ATM atm;
    String name;
    int amount;

    public void run() {
        atm.useATM(500,this);
    }

//    public void useATM (int amount){
//        atm.checkBalance(this);
//        atm.withdraw(this, amount);
//    }
    public Customer(String name, int amount, ATM atm) {
        super();
        this.name = name;
        this.amount = amount;
        this.atm = atm;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCustomerName() {
        return name;
    }
}
