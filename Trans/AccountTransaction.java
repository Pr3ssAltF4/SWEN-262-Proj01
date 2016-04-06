package src.model.Trans;

import src.model.Account;

public class AccountTransaction extends Transaction {

    private Account account;
    private double ammount = 0;

    public AccountTransaction(Account account, double amount) {
        this.account = account;
        this.ammount = amount;
    }

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

    public Transaction accept(TransactionVisitor visitor){
        visitor.visitBranchTransaction(this);
        return null;
    }


    public void undo(){
        account.deposit(-ammount);
    }

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
}
