package src.model.Trans;


import src.model.Equity;

public class EquityTransaction extends Transaction {

    private Equity equity;
    private int numberOfStock = 0;

    public EquityTransaction(Equity equity, int numberOfStock) {
        this.equity = equity;
        this.numberOfStock = numberOfStock;
    }

    public void process() throws Exception{
        if(equity.getOwned() + numberOfStock < 0){
            throw new Exception("E002: Selling more stock then owned!");
        }else {
            equity.addToOwned(numberOfStock);
        }
    }

    public Transaction accept(TransactionVisitor visitor){
        visitor.visitBranchTransaction(this);
        return null;
    }

    public void undo(){
        if(equity.getOwned() > 0) {
            equity.addToOwned(-numberOfStock);
        }
        if(equity.getOwned() < 0){
            equity.setOwned(0);
        }
    }

    public boolean hasNext(){
        return getBranch() != null;
    }

    public Equity getEquity() {
        return equity;
    }

    public void setEquity(Equity equity) {
        this.equity = equity;
    }

    public int getNumberOfStock() {
        return numberOfStock;
    }

    public void setNumberOfStock(int numberOfStock) {
        this.numberOfStock = numberOfStock;
    }
}
