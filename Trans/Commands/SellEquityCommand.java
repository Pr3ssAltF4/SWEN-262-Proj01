package src.model.Trans.Commands;

import src.model.Account;
import src.model.Equity;
import src.model.Trans.AccountTransaction;
import src.model.Trans.EquityTransaction;
import src.model.Trans.Transaction;

public class SellEquityCommand implements Command {

    private Transaction headNode;
    private Account account;
    private Equity equity;
    private int numberOfStock;

    public SellEquityCommand(Account account, Equity equity, int numberOfStock, Transaction headNode) {
        this.account = account;
        this.equity = equity;
        this.numberOfStock = Math.abs(numberOfStock);
        this.headNode = headNode;
    }

    @Override
    public void execute() throws Exception {
        AccountTransaction accountDeduction = new AccountTransaction(account, equity.getPricePerStock() * numberOfStock);
        EquityTransaction equityTransaction = new EquityTransaction(equity, (-1) * numberOfStock);
        Transaction node = new Transaction();

        try {
            accountDeduction.process();
            equityTransaction.process();

            node.setBranch(accountDeduction);
            accountDeduction.setBranch(equityTransaction);

            Transaction next = headNode.getNext();
            headNode.setNext(node);
            node.setNext(next);

        } catch (Exception e) {
            throw e;
        }
    }
}
