package src.model;

import javax.swing.*;

import javax.swing.event.*;



import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.Vector;


public class PortfolioGUI extends JFrame implements ItemListener{


    private static final long serialVersionUID = 1L;
    static final String HOLDINGS = "Holdings";
    static final String TRANSACTIONS = "Transactions";
    static final String ACCOUNTS = "Accounts";
    static final String SIMULATIONS ="Simulations";

//holds components

    private JPanel screen;
    private JComboBox<String> cb;

//holdings

    private JPanel holdings;
    private JList<Equity> equities;
    private JScrollPane escroll;

//transactions

    private JPanel transactions;
    private JList<Transaction> transaction_history;
    private JScrollPane tscroll;

//account

    private JPanel accounts;
    private JList<Account> ac;
    private JScrollPane ascroll;

//simulations

    private JPanel simulation;

//button panel **will need some more thinking

//import and export buttons

    private JPanel buttons;

//User Portfolio info

    private Portfolio p;
    private Vector<Equity> e;
    private Vector<Transaction> th;
    private Vector<Account> a;
    private JPanel comboBoxPane;

    public PortfolioGUI(Portfolio p){

        this.p = p;
        setSize(1000, 500);
        setMinimumSize(new Dimension(2000,1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new JLabel(p.getName() + "'s Portfolio"), BorderLayout.NORTH);


//screen

//make sure its set up like the tutorial

        screen = new JPanel();
        screen.setLayout(new CardLayout());
        comboBoxPane  = new JPanel();
        comboBoxPane.setLayout(new FlowLayout());
        String comboBoxItems[] = { HOLDINGS, TRANSACTIONS, ACCOUNTS, SIMULATIONS };
        cb = new JComboBox<>(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        add(screen, BorderLayout.CENTER);

//holdings

        holdings = new JPanel();
        e = new Vector<>();
        Collections.copy(e, p.getEquities());
        equities = new JList<>(e);
        escroll = new JScrollPane();
        escroll.add(equities);
        holdings.add(escroll);
        screen.add(holdings, HOLDINGS);

//transactions

        transactions = new JPanel();
        transactions.setLayout(new BorderLayout());
        th = new Vector<>();
        Collections.copy(th, p.transaction_history);
        transaction_history = new JList<>(th);
        tscroll = new JScrollPane();
        tscroll.add(transaction_history);
        transactions.add(tscroll);
        screen.add(transactions, TRANSACTIONS);

//user

        accounts = new JPanel();
        accounts.setLayout(new BorderLayout());
        a = new Vector<>();
        Collections.copy(a, p.accounts);
        ac = new JList<>(a);
        ascroll = new JScrollPane();
        ascroll.add(ac);
        accounts.add(tscroll);
        screen.add(accounts, ACCOUNTS);

//simulation

        simulation = new JPanel();
        screen.add(simulation, SIMULATIONS);

//buttons

//needs an import and export button

//not sure where to put it yet

        buttons = new JPanel();
        add(buttons, BorderLayout.SOUTH);
    }



    public static void main(String[] args) {
        //PortfolioGUI pf = new PortfolioGUI();

        //pf.setVisible(true);


    }



    @Override
    public void itemStateChanged(ItemEvent e) {

        CardLayout cl = (CardLayout)(screen.getLayout());

        cl.show(screen, (String)e.getItem());


    }

}