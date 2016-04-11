package R2.Trans;


import R2.Equity;

/**
 * A transaction specifically used for Equities
 */
public class EquityTransaction extends Transaction {

    private Equity equity;
    private int numberOfStock = 0;

    /**
     * Constructor for EquityTransaction
     * @param equity
     * @param numberOfStock
     */
    public EquityTransaction(Equity equity, int numberOfStock) {
        this.equity = equity;
        this.numberOfStock = numberOfStock;
    }

    /**
     * A method used to affect the currrent state of the equities in the system
     * @throws Exception iff you are trying to sell more that you have
     */
    public void process() throws Exception{
        if(equity.getOwned() + numberOfStock < 0){
            throw new Exception("E002: Selling more stock then owned!");
        }else {
            equity.addToOwned(numberOfStock);
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
        if(equity.getOwned() > 0) {
            equity.addToOwned(-numberOfStock);
        }
        if(equity.getOwned() < 0){
            equity.setOwned(0);
        }
    }

    /**
     * Returns true if the transaction has a next transaction
     * @return
     */
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

    /**
     * Exports the Transaction as a CSV
     * @return CSV string
     */
    public String export(){
        return super.export() + "," + equity.getTicker() + "," + numberOfStock;
    }
}
