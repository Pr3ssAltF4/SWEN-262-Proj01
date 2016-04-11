package src.Trans;

public class UndoTransactionVisitor implements TransactionVisitor {

    private int targetTransactionId = 0;

    /**
     * Handles how the visitor manages to travers the tree.
     * @param transaction the current transaction that it is visiting.
     * @return
     */
    @Override
    public Transaction visit(Transaction transaction) {
        if(transaction.getNext() != null) {
            if (transaction.getNextId() == targetTransactionId) {
                Transaction middle = transaction.getNext();
                transaction.setNext(middle.getNext());
                if(middle.getBranch() != null) {
                    middle.getBranch().accept(this);
                }
                return middle;
            } else {
                transaction.getNext().accept(this);
            }
        }
        return null;
    }

    /**
     * Handles how the visitor manages to visit a branch of a tree.
     * @param transaction a section of the branch (A AccountTransaction or a EquityTransaction
     */
    public void visitBranchTransaction(Transaction transaction){
        transaction.undo();
        if(transaction.getBranch() != null)transaction.getBranch().accept(this);
    }

    /**
     * Sets the target transaction that you want to visit.
     * @param transactionId
     */
    public void setTargetTransactionId(int transactionId) {
        this.targetTransactionId = transactionId;
    }
}
