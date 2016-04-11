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




    /**
     * The acceptance of a visitor used in the visitor pattern
     * @param visitor
     * @return
     */
    public Transaction accept(TransactionVisitor visitor){
        return visitor.visit(this);
    }

    public int getBranchId(){
        if(branch != null){
            return branch.getId();
        }else {
            return -1;
        }
    }

    /**
     * The Id of the next transaction in the tree
     * @return in ID
     */
    public int getNextId(){
        if(hasNext()) {
            return next.getId();
        }else{
            return -1;
        }
    }

    /**
     * The Id of the current transaction
     * @return int ID
     */
    public int getId(){
        return id;
    }

    /**
     * Returns the next Transaction in the tree
     * @return
     */
    public Transaction getNext() {
        return next;
    }

    /**
     * Returns true if the transaction has a next transaction
     * @return
     */
    public boolean hasNext(){
        return next != null ? true:false;
    }

    /**
     * Sets the next transaction to the one you have specified
     * @param next
     */
    public void setNext(Transaction next) {
        this.next = next;
    }

    /**
     * Returns the branch (A AccountTransaction or a EquityTransaction)
     * @return
     */
    public Transaction getBranch() {
        return branch;
    }

    /**
     * Sets the branch (A AccountTransaction or a EquityTransaction) node for the current transaction
     * @param branch
     */
    public void setBranch(Transaction branch) {
        this.branch = branch;
    }

    /**
     * A method used to undo the effects of the current transaction
     */
    public void undo() {
    }

    /**
     * Exports the Transaction as a CSV
     * @return CSV string
     */
    public String export(){
        String export = "";
        export += id +","+dateFormat.format(date)+","+ getNextId()+","+getBranch().getId();
        return export;
    }
}
