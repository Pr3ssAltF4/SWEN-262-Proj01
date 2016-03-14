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

<<<<<<< HEAD
    /**
     *
     * @return returns the user portfolio
     */
    public Portfolio getPortfolio() {
        return portfolio;
    }

    /**
     *
     * @return name of user
     */
=======
>>>>>>> 31e79b5298a1b384a1098254cca902842eb699ab
    public String getName() {
        return name;
    }

    /**
     * convert user object into string
     * @return string with user values
     */
    public String exportUser(){
        return "" + username + "," + name + "," +id +","+ password;
    }
}
