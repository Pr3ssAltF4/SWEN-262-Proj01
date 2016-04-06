package src.model.Trans;

public class UndoTransactionVisitor implements TransactionVisitor {

    private int transactionId = 0;
//    private Transaction prevTran = null;

    @Override
    public Transaction visit(Transaction transaction) {
//        prevTran = transaction;
//        if(transaction.getId() != transactionId){
//            transaction.getNext().accept(this);
//        }else {
//            // undo complex transactions
//            prevTran.setNext(transaction.getNext());
//
//        }

        //-----------------------------------
        if(transaction.getNext() != null) {
            if (transaction.getNextId() == transactionId) {
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

    public void visitBranchTransaction(Transaction tranaction){
        tranaction.undo();
        if(tranaction.getBranch() != null)tranaction.getBranch().accept(this);
    }

    public void setTransactionId(int tansactionId) {
        this.transactionId = tansactionId;
    }
}
