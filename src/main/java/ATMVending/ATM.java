package ATMVending;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ATM {
    int atmBalance;

    public ATM(int atmBalance) {
        this.atmBalance = atmBalance;
    }

    synchronized public void useATM (int amount,Customer cus){
        this.checkBalance(cus);
        this.withdraw(cus, amount);
    }

    public int checkBalance (Customer cus) {
        System.out.println(cus.getCustomerName() + " Checking");
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){}
        System.out.println(cus.getCustomerName() + " Checked");
        return cus.getAmount();
    }

    public int withdraw (Customer cus, int amount) {
        int prevBalance = cus.getAmount();
        prevBalance -= amount;
        cus.setAmount(prevBalance);
        this.atmBalance -= amount;
        System.out.println(cus.getCustomerName() + " Withdrawing ");
        System.out.println("ATMbal = " + this.atmBalance);
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){}
        System.out.println(cus.getCustomerName() + " Withdrawn ");
        return cus.getAmount();
    }
}
