package src.model.Trans;

import src.model.Account;

/**
 * A transaction specifically used to for accounts
 */
public class AccountTransaction extends Transaction {

    private Account account;
    private double ammount = 0;

    /**
     * Constructor for AccountTransaction
     * @param account
     * @param amount
     */
    public AccountTransaction(Account account, double amount) {
        this.account = account;
        this.ammount = amount;
    }

    /**
     * A method used to affect the currrent state of the equities in the system
     * @throws Exception iff you are trying to sell more that you have
     */
    public void process() throws Exception{
        if(ammount < 0) {
            if(account.getBalance() + ammount >= 0){
                account.deposit(ammount);
            }else{
                throw new Exception("E001: Taking out more money then can be taken out!");
            }
        }else{
            account.deposit(ammount);
        }
    }

    /**
     * The acceptance of a visitor used in the visitor pattern
     * @param visitor
     * @return
     */
    public Transaction accept(TransactionVisitor visitor){
        visitor.visitBranchTransaction(this);
        return null;
    }

    /**
     * A method used to undo the effects of the current transaction
     */
    public void undo(){
        account.deposit(-ammount);
    }

    /**
     * Returns true if the transaction has a next transaction
     * @return
     */
    public boolean hasNext(){
        return getBranch() != null;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    /**
     * Exports the Transaction as a CSV
     * @return CSV string
     */
    public String export(){
        return super.export()+"," + account.getId() +","+ ammount;
    }
}
