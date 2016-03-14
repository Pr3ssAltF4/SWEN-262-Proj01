package src.model;

import src.util.IdGenorator;

import java.util.ArrayList;
//import models; // Needs to change

public class Transaction {

    public int id;
    
    public Transaction() {
	this.id = IdGenorator.getInstance().getNewId();
    }

    public int getId() { return this.id; }

    public String exportTransaction(){
        return ""+ id;
    }

    public static Transaction importTransaction(String line) {
        return new Transaction();
    }
}
