package model;
import java.util.ArrayList;

//import model; // This needs to change. Visibility and such actually matters.

public class User {
    private int id;
    public String username; // username
    public String name; // first and last name
    public String password; // Might want to bit 64 encode or hash this shit
    public Portfolio portfolio;

    // ctor
    public User(String username, String name, int id) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.portfolio = new Portfolio(name, id, this);
    }

    // Some method stubs
    public String getUsername() { return username; }

    public int getId() { return id; }


}
