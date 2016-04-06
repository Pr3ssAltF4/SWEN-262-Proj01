package src.model.Trans;

//import org.junit.Test;

//import static org.junit.Assert.*;

import src.model.Account;
import src.model.Equity;
import src.model.Trans.Commands.*;
import java.util.ArrayList;

public class UndoTransactionVisitorTest {

    public static void main(String args[]) {
        testBasicLinkCorection();
        testComplexTransactionUndo();
        testCommand();
    }

    private static void testCommand() {
        Transaction t1 = new Transaction();
        Transaction t2 = new Transaction();
        Transaction t3 = new Transaction();

        t1.setNext(t2);
        t2.setNext(t3);

        Account account = new Account("One",100);
        AccountTransaction a11 = new AccountTransaction(account,50);
        Equity equity = new Equity("JAVA", 500, 10);
        EquityTransaction e12 =  new EquityTransaction(equity,2);
        equity.addToOwned(2);

        t2.setBranch(a11);
        a11.setBranch(e12);


        ArrayList<Command> list = new ArrayList<Command>();
        list.add(new UndoTransactionCommand(t1,t2.getId()));
        for(Command command: list){
            try {
                command.execute();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static void testComplexTransactionUndo() {
        Transaction t1 = new Transaction();
        Transaction t2 = new Transaction();
        Transaction t3 = new Transaction();

        t1.setNext(t2);
        t2.setNext(t3);

        Account account = new Account("One",100);
        AccountTransaction a11 = new AccountTransaction(account,50);
        Equity equity = new Equity("JAVA", 500, 10);
        EquityTransaction e12 =  new EquityTransaction(equity,2);
        equity.addToOwned(2);

        t2.setBranch(a11);
        a11.setBranch(e12);

        UndoTransactionVisitor visitor = new UndoTransactionVisitor();
        visitor.setTransactionId(t2.getId());


        check(visitor.visit(t1) == t2,"Test 2-1");
        check(account.getBalance() == 50,"Test 2-2");
        check(equity.getOwned()== 0,"Test 2-3");
    }

    //    @Test
    public static void testBasicLinkCorection(){

        Transaction t1 = new Transaction();
        Transaction t2 = new Transaction();
        Transaction t3 = new Transaction();

        t1.setNext(t2);
        t2.setNext(t3);

        UndoTransactionVisitor visitor = new UndoTransactionVisitor();
        visitor.setTransactionId(t2.getId());
        visitor.visit(t1);

        check(t1.getNext().getId() == t3.getId(),"Test 1");
    }

    private static void check(boolean b, String string){
        if(b){
            System.out.println(string +" passed");
        }else{
            System.out.println(string +" failed");
        }
    }

}