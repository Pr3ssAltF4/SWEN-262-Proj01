package src.model.Trans;

public interface TransactionVisitor {
    Transaction visit(Transaction transaction);
    void visitBranchTransaction(Transaction tranaction);

}
