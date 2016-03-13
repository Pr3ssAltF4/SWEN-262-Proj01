package src.model;

import java.util.ArrayList;
//import models; // Needs to change

public class Transaction {

    public int id;
    
    public Transaction(int id) {
	this.id = id;
    }

    public int getId() { return this.id; }

    public String exportTransaction(){
        return ""+ id;
    }

}
