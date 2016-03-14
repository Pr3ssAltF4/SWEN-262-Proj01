package src.model;

import java.util.ArrayList;

import src.model.Account; // This needs to change. Visibility and such actually matters.
import src.model.Transaction;
import src.model.Portfolio;
import src.util.IdGenorator;

public class User {
    private int id;
    public String username; // username
    public String name; // first and last name
    public String password; // Might want to bit 64 encode or hash this shit

    /**
     *
     * @param username - Log in name
     * @param name - name of the Users
     */
    public User(String username, String name) {
        this.id = IdGenorator.getInstance().getNewId();
        this.username = username;
        this.name = name;
    }

    /**
     *
     * @return returns password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password - new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return returns username
     */
    public String getUsername() { return username; }

    /**
     *
     * @return return User's id
     */
    public int getId() { return id; }

    public String getName() {
        return name;
    }

    /**
     *
     * @param cost - cost of equities
     * @param accounts - list of cash accounts
     * @return list of account with enough balance to buy equities
     */
    public ArrayList<Account> canPurchase(double cost, ArrayList<Account> accounts){
        ArrayList<Account> canPurchase = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++){
            if (accounts.get(i).getBalance() >= cost){
                canPurchase.add(accounts.get(i));
            }
        }
        return canPurchase;
    }

    public String exportUser(){
        return "" + username + "," + name + "," +id +","+ password;
    }
}
