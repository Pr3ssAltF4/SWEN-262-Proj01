package src.model;

import java.util.ArrayList;
import java.util.Base64;

public class User {
    private int id = 0;
    private String username = "";
    private String name = "";
    private String password = "";
    
    
    /**
     *
     * @param username - Log in name
     * @param name - name of the Users
     */
    public User(String username, String name) {
        this.id = id;
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
     * Should encrypt the password using a standard Base64 Java hash.
     * Needs the entered password (when the user logs in) to be hashed and compared.
     * @param password - new password
     * Author : Ian Taylor
     */
    public void setPassword(String password) {
	byte[] new_password = password.getBytes();
	password = Base64.Encoder.encode(new_password);
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



    /**
     *
     * @return name of user
     */

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
