package R2.Trans.Commands;

import R2.Trans.Transaction;
import R2.Trans.UndoTransactionVisitor;

public class UndoTransactionCommand implements Command {

    private Transaction transaction;
    private int transactionId;

    /**
     * A constructor used to make an UndoTransactionCommand
     * @param headTransaction
     * @param targetTransactionId
     */
    public UndoTransactionCommand(Transaction headTransaction, int targetTransactionId) {
        this.transaction = headTransaction;
        this.transactionId = targetTransactionId;
    }

    /**
     * A method used to actually undo the a Transaction
     */
    @Override
    public void execute() {
        UndoTransactionVisitor visitor = new UndoTransactionVisitor();
        visitor.setTargetTransactionId(transactionId);
        visitor.visit(transaction);
    }
}
