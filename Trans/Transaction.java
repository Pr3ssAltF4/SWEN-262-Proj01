package src.model.Trans;

import src.util.IdGenorator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Transaction {

    private int id = IdGenorator.getInstance().getNewId();
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
    private Date date = new Date();

    private Transaction next = null;
    private Transaction branch = null;

    public Transaction accept(TransactionVisitor visitor){
        return visitor.visit(this);
    }

    public int getNextId(){
        return next.getId();
    }

    public int getId(){
        return id;
    }

    public Transaction getNext() {
        return next;
    }

    public boolean hasNext(){
        return next != null ? true:false;
    }


    public void setNext(Transaction next) {
        this.next = next;
    }

    public Transaction getBranch() {
        return branch;
    }

    public void setBranch(Transaction branch) {
        this.branch = branch;
    }

    public void undo() {
    }
}
