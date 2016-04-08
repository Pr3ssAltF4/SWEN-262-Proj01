package src.model;

// Author : Ian Taylor

import src.util.IdGenorator;

public class User {

    private String username = "";
    private String password = "";
    private int id = IdGenorator.getInstace().getNewId();

    public User(String username, String password) {
	this.username = username;
	this.password = 0 // base 64 encoded password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getId() { return id; }
    
}
