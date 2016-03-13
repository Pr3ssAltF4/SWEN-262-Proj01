package src.model;



public class Account {

    private int id;
    public String name;
    private double balance;

    public Account(String name, int id, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public int getId() { return id; }
    public double getBalance() { return balance; }
    public String getName() { return name; }

    // withdraws money from the account. Returns amount left.
    public double withdraw(double withdrawal) {
        if(this.balance - withdrawal >= 0)
            return this.balance -= withdrawal; // don't remember if this works in java lol. I may or may not
        // be drunk coding right now...
        else
            return -1;
    }

    // deposits money into the account.
    public double deposit(double deposit) {
        this.balance += deposit;
        return this.balance;
    }

    public String exportAccount() {
        return "" + id + "," + name + "," + balance;
    }
}
