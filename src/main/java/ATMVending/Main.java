package ATMVending;

public class Main {
    public static void main (String [] args) {
        ATM atm = new ATM(10000);
        Customer c1 = new Customer("ak",1000, atm);
        Customer c2 = new Customer("sam",1000, atm);
        Customer c3 = new Customer("lobo",1000, atm);
        c1.start();
        c2.start();
        c3.start();
    }
}
