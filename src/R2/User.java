package R2;
// Author : Ian Taylor


import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class User {

    private String username = "";
    private String password = "";
    private int id = IdGenorator.getInstance().getNewId();

    // Once entered the password is always encoded.
    public User(String username, String password) throws UnsupportedEncodingException {
	this.username = username;
	this.password = Base64.getEncoder().encodeToString(password.getBytes("utf-8"));
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getId() { return id; }
    
}
