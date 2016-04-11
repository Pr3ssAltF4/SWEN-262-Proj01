package R2;// Author : Tyler Shingler, Ian Taylor

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {

    static DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
    private int id = IdGenorator.getInstance().getNewId();
    private String name;
    private double balance;
    private Date dateCreated;



    /**
     *
     * @param name - name of account owner
     * @param balance - amount on money in account
     */
    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.dateCreated = new Date();
    }

    /**
     *
     * @return id own account owner
     */
    public int getId() { return id; }
    public double getBalance() { return balance; }
    public String getName() { return name; }

    /**
     *
     * @param withdrawal - amount to withdraw from account
     * @return amount withdrawed or -1 if insufficent funds
     */
    public double withdraw(double withdrawal) {
        if(this.balance - withdrawal >= 0)
            return this.balance -= withdrawal; // don't remember if this works in java lol. I may or may not
        // be drunk coding right now...
        else
            return -1;
    }

    /**
     *
     * @param deposit - amount to deposit into account
     * @return balance after deposit is complete
     */
    public double deposit(double deposit) {
        this.balance += deposit;
        return this.balance;
    }

    /**
     *
     * @return - the date account was created
     */
    public Date getDateCreated(){
        return dateCreated;
    }

    public String exportAccount(){
        String export = "";
        export = id +","+ name +","+balance+","+dateFormat.format(dateCreated);
        return export;
    }

    public void importAccount(String accountString){
        String[] varables = accountString.split(",");
        id = Integer.parseInt(varables[0]);
        name = varables[1];
        balance = Double.parseDouble(varables[2]);
        try{
            dateCreated = dateFormat.parse(varables[3]);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("This is what we got:"+varables[3]);
        }finally {
            dateCreated = new Date();
        }
    }

}
