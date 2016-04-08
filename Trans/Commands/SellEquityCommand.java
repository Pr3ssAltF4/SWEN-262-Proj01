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

    /**
     * The Constructor
     * @param account The account for money to be put into
     * @param equity the type of equity to be sold
     * @param numberOfStock the number of stock to be sold
     * @param headNode the head transaction node of the profile
     */
    public SellEquityCommand(Account account, Equity equity, int numberOfStock, Transaction headNode) {
        this.account = account;
        this.equity = equity;
        this.numberOfStock = Math.abs(numberOfStock);
        this.headNode = headNode;
    }

    /**
     * The method used to actually sell an Equity
     * @throws Exception
     */
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
