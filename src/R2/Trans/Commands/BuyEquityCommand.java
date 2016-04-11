package R2.Trans.Commands;


import R2.Account;
import R2.Equity;
import R2.Trans.AccountTransaction;
import R2.Trans.EquityTransaction;
import R2.Trans.Transaction;

public class BuyEquityCommand implements Command {

    private Transaction headNode;
    private Account account;
    private Equity equity;
    private int numberOfStock;

    /**
     * The constructor
     * @param account Account money is taken from
     * @param equity The Equity bought
     * @param numberOfStock The number of stock
     * @param headNode the head node of the transaction list
     */
    public BuyEquityCommand(Account account, Equity equity, int numberOfStock, Transaction headNode) {
        this.account = account;
        this.equity = equity;
        this.numberOfStock = Math.abs(numberOfStock);
        this.headNode = headNode;
    }

    /**
     * The method used to actually Buy the Equity given
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        AccountTransaction accountDeduction = new AccountTransaction(account, (-1)*equity.getPricePerStock()*numberOfStock);
        EquityTransaction equityTransaction = new EquityTransaction(equity, numberOfStock);
        Transaction node = new Transaction();

        try {
            accountDeduction.process();
            equityTransaction.process();

            node.setBranch(accountDeduction);
            accountDeduction.setBranch(equityTransaction);

            Transaction next = headNode.getNext();
            headNode.setNext(node);
            node.setNext(next);

        }catch (Exception e){
            throw e;
        }
    }
}
