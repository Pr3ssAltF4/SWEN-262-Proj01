package src.model.Trans.Commands;

import src.model.Trans.Transaction;
import src.model.Trans.UndoTransactionVisitor;

public class UndoTransactionCommand implements Command {

    private Transaction transaction;
    private int transactionId;

    public UndoTransactionCommand(Transaction transaction, int transactionId) {
        this.transaction = transaction;
        this.transactionId = transactionId;
    }

    @Override
    public void execute() {
        UndoTransactionVisitor visitor = new UndoTransactionVisitor();
        visitor.setTransactionId(transactionId);
        visitor.visit(transaction);
    }
}
