package src.model;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class PortfolioGUI extends JFrame implements ItemListener{

    private static final long serialVersionUID = 1L;
    static final String HOLDINGS = "Holdings";
    static final String TRANSACTIONS = "Transactions";
    static final String ACCOUNTS = "Accounts";
    static final String SIMULATIONS ="Simulations";

    //login
    private JFrame login;
    private JPanel loginScreen;
    private JLabel usernameLabel;
    private JTextField username;
    private JLabel passwordLabel;
    private JTextField password;
    private JButton signIn;
    private JButton newProfile;

    //create new profile
    private JPanel createProfile;
    private JLabel newNameLabel;
    private JTextField newName;
    private JLabel newUsernameLabel;
    private JTextField newUsername;
    private JLabel newPasswordLabel;
    private JTextField newPassword;
    private JButton create;
    private JButton backToLogin;

    //holds components
    private JPanel screen;
    private JComboBox<String> cb;

    //holdings
    private JPanel holdings;
    private JList<Equity> equities;
    private JScrollPane escroll;
    private JPanel hButtons;
    private JButton addEquity;
    private JButton removeEquity;
    private JButton updateEquity;
    private JTextField searchEquities;
    private JLabel search;
    private JPanel eSearch;
    private JFrame eAdd;
    private JFrame eUpdate;

    //transactions
    private JPanel transactions;
    private JList<Transaction> transaction_history;
    private JScrollPane tscroll;
    private JPanel tButtons;
    private JButton newTransaction;
    private JButton undoTransaction;
    private JFrame tAdd;

    //account
    private JPanel accounts;
    private JList<Account> ac;
    private JScrollPane ascroll;
    private JPanel aButtons;
    private JButton addAccount;
    private JButton removeAccount;
    private JButton updateAccount;
    private JFrame aAdd;
    private JFrame aUpdate;

    //simulations
    private JPanel simulation;
    private JButton runSimulation;

    //master button panel
    //import and export buttons
    private JPanel buttons;
    private JButton update;
    private JButton logout;
    private JButton imp;
    private JButton exp;


    //User Portfolio info
    private Portfolio p;
    private Vector<Equity> e;
    private Vector<Transaction> th;
    private Vector<Account> a;
    private JPanel comboBoxPane;

    public PortfolioGUI(Portfolio p){
        this.p = p;
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //login
        login = new JFrame();
        login.setVisible(false);
        loginScreen = new JPanel();
        usernameLabel = new JLabel("Username:");
        username = new JTextField();
        passwordLabel = new JLabel("Password");
        password = new JTextField();
        signIn = new JButton("SIGN IN");
        newProfile = new JButton("NEW PROFILE");

        //userName
        createProfile = new JPanel();
        newNameLabel = new JLabel("New Name:");
        newName = new JTextField();
        newUsernameLabel = new JLabel("New Username:");
        newUsername = new JTextField();
        newPasswordLabel= new JLabel("New Password:");
        newPassword = new JTextField();
        create = new JButton("Create");
        backToLogin = new JButton("Back");

        //screen
        //make sure its set up like the tutorial
        screen = new JPanel();
        CardLayout cl = new CardLayout();
        cl.preferredLayoutSize(screen);
        screen.setLayout(cl);
        comboBoxPane  = new JPanel();
        comboBoxPane.setLayout(new FlowLayout());
        String comboBoxItems[] = { HOLDINGS, TRANSACTIONS, ACCOUNTS, SIMULATIONS };
        cb = new JComboBox<>(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        add(comboBoxPane, BorderLayout.NORTH);
        add(screen, BorderLayout.CENTER);

        //holdings
        holdings = new JPanel();
        holdings.setLayout(new BorderLayout());
        hButtons = new JPanel();
        hButtons.setLayout(new GridLayout());
        addEquity = new JButton("Add Equity");
        addEquity.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                eAdd.setVisible(true);
            }

        });
        removeEquity = new JButton("Remove Equity");
        updateEquity = new JButton("Update Equity");
        updateEquity.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                eUpdate.setVisible(true);
            }

        });
        hButtons.add(addEquity);
        hButtons.add(removeEquity);
        hButtons.add(updateEquity);
        holdings.add(hButtons, BorderLayout.SOUTH);

        searchEquities = new JTextField();
        search = new JLabel("Search:");
        eSearch = new JPanel();
        eSearch.setLayout(new BorderLayout());
        eSearch.add(search, BorderLayout.WEST);
        eSearch.add(searchEquities, BorderLayout.CENTER);
        holdings.add(eSearch, BorderLayout.NORTH);

        eAdd = new JFrame();
        eAdd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        eAdd.setSize(300, 200);
        eAdd.setVisible(false);

        eUpdate = new JFrame();
        eUpdate.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        eUpdate.setSize(300, 200);
        eUpdate.setVisible(false);

        e = new Vector<>();
        //Collections.copy(e, p.getEquities());
        e.addAll(p.getEquities());
        for(int i = 0; i < e.size(); i++){
            System.out.println(e.get(i).getTicker());
        }
        equities = new JList<>(e);
        equities.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        equities.setLayoutOrientation(JList.VERTICAL);
        equities.setVisibleRowCount(-1);
        escroll = new JScrollPane(equities);
        escroll.setColumnHeaderView(new JLabel("Holdings", JLabel.CENTER));
        holdings.add(escroll, BorderLayout.CENTER);
        screen.add(holdings, HOLDINGS);

        //transactions
        transactions = new JPanel();
        transactions.setLayout(new BorderLayout());
        tButtons = new JPanel();
        tButtons.setLayout(new GridLayout());
        newTransaction = new JButton("New Transaction");
        newTransaction.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                tAdd.setVisible(true);
            }

        });
        undoTransaction = new JButton("UndoTransaction");
        tButtons.add(newTransaction);
        tButtons.add(undoTransaction);
        transactions.add(tButtons, BorderLayout.SOUTH);

        tAdd = new JFrame();
        tAdd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        tAdd.setSize(300, 200);
        tAdd.setVisible(false);

        th = new Vector<>();
        //Collections.copy(th, p.transaction_history);
        th.addAll(p.getTransaction_history());
        transaction_history = new JList<>(th);
        tscroll = new JScrollPane(transaction_history);
        transactions.add(tscroll);
        screen.add(transactions, TRANSACTIONS);

        //accounts
        accounts = new JPanel();
        accounts.setLayout(new BorderLayout());
        aButtons = new JPanel();
        aButtons.setLayout(new GridLayout());
        addAccount = new JButton("Add Account");
        addAccount.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                aAdd.setVisible(true);
            }

        });
        removeAccount = new JButton("Remove Account");
        updateAccount = new JButton("Update Account");
        updateAccount.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                aUpdate.setVisible(true);
            }

        });
        aButtons.add(addAccount);
        aButtons.add(removeAccount);
        aButtons.add(updateAccount);
        accounts.add(aButtons, BorderLayout.SOUTH);

        aAdd = new JFrame();
        aAdd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        aAdd.setSize(300, 200);
        aAdd.setVisible(false);

        aUpdate = new JFrame();
        aUpdate.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        aUpdate.setSize(300, 200);
        aUpdate.setVisible(false);

        a = new Vector<>();
        //Collections.copy(a, p.accounts);
        a.addAll(p.getAccounts());
        ac = new JList<>(a);
        ascroll = new JScrollPane(ac);
        accounts.add(ascroll);
        screen.add(accounts, ACCOUNTS);

        //simulation
        simulation = new JPanel();
        screen.add(simulation, SIMULATIONS);

        //buttons
        //needs an import and export button
        //not sure where to put it yet
        buttons = new JPanel();
        buttons.setLayout(new GridLayout());
        update = new JButton("UPDATE");
        logout = new JButton("LOGOUT");
        imp = new JButton("IMPORT");
        exp = new JButton("EXPORT");
        buttons.add(update);
        buttons.add(logout);
        buttons.add(imp);
        buttons.add(exp);
        add(buttons, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {

        String name = "Larry";

        Portfolio p = new Portfolio(name, "larry5", "larry123");
        p.addEquity("SSP", 10, 20.00);
        p.addEquity("SPP", 50, 1.00);
        Transaction t = new Transaction("BUY", 20.00, new Equity("SSP", 10, 20.00));
        p.addTransaction(t);
        p.addAccount(p.getName(), 100.00);
        PortfolioGUI pf = new PortfolioGUI(p);
        pf.setVisible(true);

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        CardLayout cl = (CardLayout)(screen.getLayout());
        cl.show(screen, (String)e.getItem());

    }

    public void run(){
        return;
    }

}
