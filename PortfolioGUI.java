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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.util.stream.Collectors;

/*
 * Currently working on simulation components. Test for idea for other windows.
 * adding panel for options and equities to choose from.  Might change frames to panels.
 * 
 * Also, need to fill in frames for each window. remember to use grid and border.
 * 
 * Last, get Portfolio to work between profiles. Probably will take the most time 
 * to figure out so be ready to think :)
 */


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
	private JPanel hButtons;
	private JButton addEquity;
	private JButton removeEquity;
	private JButton updateEquity;
	private JTextField searchEquities;
	private Vector<Equity> searchList;
	private JLabel search;
	private JPanel eSearch;
	private JButton searchButton;
	
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
	/*
	 * use survey buttons to do the different options for simulation
	 * 
	 * need time step, time interval per step
	 * 
	 * time interval = day, month, year
	 * run step-by-step, or run through to end
	 * 
	 * can run with post sim data or reset to og data
	 * 
	 * three sims: no-growth-market, bear, bull
	 */
	private JPanel simulation;
	private JList<Portfolio> simPortfolios;
	private Vector<String> simText;
	private JList<String> simResults;
	private JPanel simSetup;
	private JLabel ti;
	private ButtonGroup timeInterval;
	private JRadioButton day;
	private JRadioButton month;
	private JRadioButton year;
	private JLabel rt;
	private ButtonGroup runTime;
	private JRadioButton stepByStep;
	private JRadioButton runStrightThrough;
	private JButton runSimulation;
	private JButton resetSim;
	
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
		setVisible(false);
		
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
		
		searchList = new Vector<>(p.getEquities());
		searchEquities = new JTextField();
		search = new JLabel("Search:");
		searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		
		});
		eSearch = new JPanel();
		eSearch.setLayout(new BorderLayout());
		eSearch.add(search, BorderLayout.WEST);
		eSearch.add(searchEquities, BorderLayout.CENTER);
		eSearch.add(searchButton, BorderLayout.EAST);
		holdings.add(eSearch, BorderLayout.NORTH);
		
		eAdd = new JFrame();
		eAdd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		eAdd.setSize(300, 200);
		eAdd.setVisible(false);
		
		eUpdate = new JFrame();
		eUpdate.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		eUpdate.setSize(300, 200);
		eUpdate.setVisible(false);
		
		e = new Vector<Equity>();
		//Collections.copy(e, p.getEquities());
		e.addAll(p.getEquities());
			for(int i = 0; i < e.size(); i++){
				System.out.println(e.get(i));
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
		th.addAll(p.transaction_history);
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
		a.addAll(p.accounts);
		ac = new JList<>(a);
		ascroll = new JScrollPane(ac);
		accounts.add(ascroll);
		screen.add(accounts, ACCOUNTS);

		//simulation
		//work on tomorrow************
		simulation = new JPanel();
		simText = new Vector<String>();
		simResults = new JList<>(simText);
		simSetup = new JPanel();
		ti = new JLabel("Time Interval");
		timeInterval = new ButtonGroup();
		day = new JRadioButton("Day");
		month = new JRadioButton("Month");
		year = new JRadioButton("Year");
		rt = new JLabel("Run Time");
		runTime = new ButtonGroup();
		stepByStep = new JRadioButton("Step-By-Step");
		runStrightThrough = new JRadioButton("Run Straight Through");
		runSimulation = new JButton("Run");
		resetSim = new JButton("Reset");
		screen.add(simulation, SIMULATIONS);

		//buttons
		//needs an import and export button
		//not sure where to put it yet
		buttons = new JPanel();
		buttons.setLayout(new GridLayout());
		update = new JButton("UPDATE");
		update.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		logout = new JButton("LOGOUT");
		
		//needs fixing
		logout.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//login.setVisible(true);
			}
			
		});
		imp = new JButton("IMPORT");
		imp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		exp = new JButton("EXPORT");
		exp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		buttons.add(update);
		buttons.add(logout);
		buttons.add(imp);
		buttons.add(exp);
		add(buttons, BorderLayout.SOUTH);

	}

	public static void main(String[] args) {
		
		LoginFrame lg = new LoginFrame();
		lg.setVisible(true);


	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		CardLayout cl = (CardLayout)(screen.getLayout());
		cl.show(screen, (String)e.getItem());

	}

}