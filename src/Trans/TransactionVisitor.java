package src.Trans;

/**
 * An interface used to specify what a TransactionVisitor needs to function
 */
public interface TransactionVisitor {
    Transaction visit(Transaction transaction);
    void visitBranchTransaction(Transaction tranaction);
}
