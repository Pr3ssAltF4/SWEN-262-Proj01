package src;

import javax.swing.*;
import javax.swing.event.*;

import Trans.*;
import Equity;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
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
	private JPanel eFields;
	private JLabel tickerLabel;
	private JTextField tickerField;
	private JLabel stockNumberLabel;
	private JTextField stockNumberField;
	private JLabel ppsLabel;
	private JTextField ppsField;
	private JPanel aeButtons;
	private JButton ae;
	private JButton close;
	
	private JFrame eUpdate;
	private JPanel ueFields;
	private JLabel uTickerLabel;
	private JTextField utickerField;
	private JLabel ustockNumberLabel;
	private JTextField ustockNumberField;
	private JLabel uppsLabel;
	private JTextField uppsField;
	private JPanel ueButtons;
	private JButton ue;
	private JButton uclose;

	//transactions
	private JPanel transactions;
	private JList<Transaction> transaction_history;
	private JScrollPane tscroll;
	private JPanel tButtons;
	private JButton newTransaction;
	private JButton undoTransaction;
	
	private JFrame tAdd;
	private JPanel tFields;
	private JRadioButton AccountTransaction;
	private JRadioButton EquityTransaction;
	private ButtonGroup transButtons;
	private JList<Account> transAccounts;
	private JList<Equity> transEquities;
	private JScrollPane transaScroll;
	private JScrollPane transeScroll;
	private JRadioButton withdraw;
	private JRadioButton deposit;
	private JRadioButton transfer;
	private ButtonGroup transOptions;
	private JLabel amountLabel;
	private JTextField amountField;
	private JLabel numberOfStockLabel;
	private JTextField numberOfStockField;
	private JPanel atButtons;
	private JButton createTransaction;
	private JButton close2;
	
	//account
	private JPanel accounts;
	private JList<Account> ac;
	private JScrollPane ascroll;
	private JPanel aButtons;
	private JButton addAccount;
	private JButton removeAccount;
	private JButton updateAccount;
	
	private JFrame aAdd;
	private JPanel aFields;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel aamountLabel;
	private JTextField aamountTextField;
	private JPanel aaButtons;
	private JButton aaAdd;
	private JButton aClose;
	
	private JFrame aUpdate;
	private JPanel uaFields;
	private JLabel unameLabel;
	private JTextField unameField;
	private JLabel uaamountLabel;
	private JTextField uaamountTextField;
	private JPanel uaButtons;
	private JButton uaAdd;
	private JButton uaClose;
	
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
	private JPanel tiPanel;
	private JPanel rtPanel;
	private JScrollPane simScroll;
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
	private JRadioButton runStraightThrough;
	private JButton runSimulation;
	private JButton resetSim;
	private JLabel numStepsLabel;
	private JTextField numStepsText;
	private JLabel pricePerStockLabel;
	private JTextField pricePerStockText;
	private JLabel percentChangeLabel;
	private JTextField percentChangeText;
	
	//master button panel
	//import and export buttons
	private JPanel buttons;
	private JButton update;
	private JButton logout;
	private JButton timer;
	private JButton imp;
	private JButton exp;
	
	
	//User Portfolio info
	private Portfolio p;
	private Vector<Equity> equ;
	private Vector<Transaction> th;
	private Vector<Account> a;
	private JPanel comboBoxPane;
	private Controller controller;
	private JPanel atButtonsPanel;
	private JLabel atButtonsLabel;
	private String transOptionsString;
	private JScrollPane searchEquitiesPane;
	private JLabel simTypeLabel;
	private JTextField simTypeField;
	private JFrame timerFrame;
	private JPanel timerPanel;
	private JLabel setTimeLabel;
	private JTextField setTimeField;
	private JButton timerButton;
	

	public PortfolioGUI(Portfolio p){
		this.p = p;
		controller = new Controller();
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
		removeEquity.setEnabled(false);
		removeEquity.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Equity eq = equities.getSelectedValue();
				controller.removeEquity(eq);
				equ = new  Vector<Equity>(p.getEquities());
				equities.setListData(equ);
			}
			
		});
		updateEquity = new JButton("Update Equity");
		updateEquity.setEnabled(false);
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
				controller.searchEquities(searchEquities.getText());
				
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
		eAdd.setSize(500, 500);
		eAdd.setLayout(new BorderLayout());
		eFields = new JPanel();
		eFields.setLayout(new GridLayout(3,2));
		tickerLabel = new JLabel("Ticker: ");
		eFields.add(tickerLabel);
		tickerField = new JTextField();
		eFields.add(tickerField);
		stockNumberLabel = new JLabel("Number of Stock: ");
		eFields.add(stockNumberLabel);
		stockNumberField = new JTextField();
		eFields.add(stockNumberField);
		ppsLabel = new JLabel("Price per Stock: ");
		eFields.add(ppsLabel);
		ppsField = new JTextField();
		eFields.add(ppsField);
		aeButtons = new JPanel();
		ae = new JButton("Add Equity");
		ae.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int nos = Integer.parseInt(ustockNumberField.getText());
				double priceps = Double.parseDouble(uppsField.getText());
				controller.addEquity(tickerField.getText(), nos, priceps);
				equ = new  Vector<Equity>(p.getEquities());
				equities.setListData(equ);
				
			}
			
		});
		close = new JButton("Close");
		close.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				eAdd.setVisible(false);
				
			}
			
		});
		aeButtons.setLayout(new GridLayout());
		aeButtons.add(ae);
		aeButtons.add(close);
		eAdd.add(eFields, BorderLayout.CENTER);
		eAdd.add(aeButtons, BorderLayout.SOUTH);
		eAdd.setVisible(false);
		
		eUpdate = new JFrame();
		eUpdate.setLayout(new BorderLayout());
		eUpdate.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		eUpdate.setSize(300, 200);
		ueFields = new JPanel();
		ueFields.setLayout(new GridLayout(3,2));
		uTickerLabel = new JLabel("Ticker: ");
		utickerField = new JTextField();
		utickerField.setEditable(false);
		ueFields.add(uTickerLabel);
		ueFields.add(utickerField);
		ustockNumberLabel = new JLabel("Number of Stock: ");
		ustockNumberField = new JTextField();
		ueFields.add(ustockNumberLabel);
		ueFields.add(ustockNumberField);
		uppsLabel = new JLabel("Price per Stock: ");
		uppsField = new JTextField();
		ueFields.add(uppsLabel);
		ueFields.add(uppsField);
		ueButtons = new JPanel();
		ueButtons.setLayout(new GridLayout());
		ue = new JButton("Update Equity");
		ue.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Equity eq = equities.getSelectedValue();
				int nos = Integer.parseInt(ustockNumberField.getText());
				double priceps = Double.parseDouble(uppsField.getText());
				controller.updateEquity(eq, eq.getTicker(), nos, priceps);
				equ = new  Vector<Equity>(p.getEquities());
				equities.setListData(equ);
				
			}
			
		});
		uclose = new JButton("Close");
		uclose.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				eUpdate.setVisible(false);
				
			}
			
		});
		ueButtons.add(ue);
		ueButtons.add(uclose);
		eUpdate.add(ueFields, BorderLayout.CENTER);
		eUpdate.add(ueButtons, BorderLayout.SOUTH);
		eUpdate.setVisible(false);
		
		equ = new Vector<Equity>();
		equ.addAll(p.getEquities());
			for(int i = 0; i < equ.size(); i++){
				System.out.println(equ.get(i));
			}
		equities = new JList<>(equ);
		equities.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				removeEquity.setEnabled(true);
				updateEquity.setEnabled(true);
			}
			
		});
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
		undoTransaction.setEnabled(false);
		undoTransaction.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.undoTransaction(transaction_history.getSelectedValue());
				th = new Vector<Transaction>();
				th.add(p.getTransaction());
				transaction_history.setListData(new Vector<Transaction>(th));
			}
			
		});
		tButtons.add(newTransaction);
		tButtons.add(undoTransaction);
		transactions.add(tButtons, BorderLayout.SOUTH);
		
		tAdd = new JFrame();
		tAdd.setLayout(new BorderLayout());
		tAdd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		tAdd.setSize(300, 200);
		tFields = new JPanel();
		tFields.setLayout(new GridLayout(5,2));
		AccountTransaction = new JRadioButton("Account Transaction");
		AccountTransaction.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				transAccounts.setEnabled(true);
				transEquities.setEnabled(false);
				atButtonsPanel.setEnabled(true);
				amountField.setEditable(true);
				numberOfStockField.setEditable(false);
			}
			
		});
		EquityTransaction = new JRadioButton("Equity Transaction");
		EquityTransaction.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				transAccounts.setEnabled(false);
				transEquities.setEnabled(true);
				atButtonsPanel.setEnabled(false);
				amountField.setEditable(false);
				numberOfStockField.setEditable(true);
				
			}
			
		});
		transButtons = new ButtonGroup();
		transButtons.add(AccountTransaction);
		transButtons.add(EquityTransaction);
		tFields.add(AccountTransaction);
		tFields.add(EquityTransaction);
		transAccounts = new JList<Account>(new Vector<Account>(p.getAccounts()));
		transEquities = new JList<Equity>(new Vector<Equity>(p.getEquities()));
		transaScroll = new JScrollPane();
		transaScroll.add(transAccounts);
		transeScroll = new JScrollPane();
		transeScroll.add(transEquities);
		tFields.add(transaScroll);
		tFields.add(transeScroll);
		atButtonsPanel = new JPanel();
		atButtonsPanel.setLayout(new GridLayout());
		atButtonsLabel = new JLabel("Transaction Options: ");
		tFields.add(atButtonsLabel);
		transOptionsString = "";
		withdraw = new JRadioButton("Withdraw");
		withdraw.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				transOptionsString = "withdraw";
				
			}
			
		});
		deposit = new JRadioButton("Deposit");
		deposit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				transOptionsString= "deposit";
				
			}
			
		});
		transfer = new JRadioButton("Transfer");
		transfer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				transOptionsString = "transfer";
				
			}
			
		});
		transOptions = new ButtonGroup();
		transOptions.add(withdraw);
		transOptions.add(deposit);
		transOptions.add(transfer);
		atButtonsPanel.add(withdraw);
		atButtonsPanel.add(deposit);
		atButtonsPanel.add(transfer);
		tFields.add(atButtonsPanel);
		amountLabel = new JLabel("Amount");
		tFields.add(amountLabel);
		amountField = new JTextField();
		tFields.add(amountField);
		numberOfStockLabel = new JLabel("Number of Stocks");
		tFields.add(numberOfStockLabel);
		numberOfStockField = new JTextField();
		tFields.add(numberOfStockField);
		atButtons = new JPanel();
		createTransaction = new JButton("Create Transaction");
		createTransaction.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(AccountTransaction.isSelected()){
					Account aco = transAccounts.getSelectedValue();
					double amount = Double.parseDouble(amountField.getText());
					controller.newAccountTransaction(transOptionsString, aco, amount);
					th = new Vector<Transaction>();
					th.add(p.getTransaction());
					transaction_history.setListData(th);
				}
				if(EquityTransaction.isSelected()){
					Equity equ = transEquities.getSelectedValue();
					int nos = Integer.parseInt(numberOfStockField.getText());
					controller.newEquityTransaction(equ, nos);
					th = new Vector<Transaction>();
					th.add(p.getTransaction());
					transaction_history.setListData(new Vector<Transaction>(th));
				}
				
			}
			
		});
		close2 = new JButton("Close");
		close2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				tAdd.setVisible(false);
				
			}
			
		});
		atButtons.add(createTransaction);
		atButtons.add(close2);
		tAdd.add(atButtons, BorderLayout.SOUTH);
		tAdd.add(tFields, BorderLayout.CENTER);
		tAdd.setVisible(false);
		
		th = new Vector<>();
		//Collections.copy(th, p.transaction_history);
		th.add(p.getTransaction());
		transaction_history = new JList<>(th);
		transaction_history.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				undoTransaction.setEnabled(true);
				
			}
			
		});
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
		removeAccount.setEnabled(false);
		removeAccount.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Account a = ac.getSelectedValue();
				controller.removeAccount(a);
				ac.setListData(new Vector<Account>(p.getAccounts()));
			}
			
		});
		updateAccount = new JButton("Update Account");
		updateAccount.setEnabled(false);
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
		aAdd.setLayout(new BorderLayout());
		aAdd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		aAdd.setSize(300, 200);
		aFields = new JPanel();
		aFields.setLayout(new BorderLayout(2,2));
		nameLabel = new JLabel("Name: ");
		aFields.add(nameLabel);
		nameField = new JTextField();
		aFields.add(nameField);
		aamountLabel = new JLabel("Amount: ");
		aFields.add(aamountLabel);
		aamountTextField = new JTextField();
		aFields.add(aamountTextField);
		aaButtons = new JPanel();
		aaAdd = new JButton("Add Account");
		aaAdd.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				double balance = Double.parseDouble(amountField.getText());
				controller.addAccount(name, balance);
				ac.setListData(new Vector<Account>(p.getAccounts()));
			}
			
		});
		aaButtons.add(aaAdd);
		aClose = new JButton("Close");
		aClose.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				aAdd.setVisible(false);
				
			}
			
		});
		aaButtons.add(aClose);
		aAdd.add(aFields, BorderLayout.CENTER);
		aAdd.add(aaButtons, BorderLayout.SOUTH);
		aAdd.setVisible(false);
		
		aUpdate = new JFrame();
		aUpdate.setLayout(new BorderLayout());
		aUpdate.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		aUpdate.setSize(300, 200);
		uaFields = new JPanel();
		uaFields.setLayout(new BorderLayout(2,2));
		unameLabel = new JLabel("Name: ");
		uaFields.add(unameLabel);
		unameField = new JTextField();
		unameField.setEditable(false);
		uaFields.add(unameField);
		uaamountLabel = new JLabel("Amount: ");
		uaFields.add(uaamountLabel);
		uaamountTextField = new JTextField();
		uaFields.add(uaamountTextField);
		uaButtons = new JPanel();
		uaAdd = new JButton("Add Account");
		uaAdd.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				double balance = Double.parseDouble(amountField.getText());
				controller.addAccount(name, balance);
				ac.setListData(new Vector<Account>(p.getAccounts()));
			}
			
		});
		uaButtons.add(uaAdd);
		uaClose = new JButton("Close");
		uaClose.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				aUpdate.setVisible(false);
				
			}
			
		});
		uaButtons.add(uaClose);
		aUpdate.add(aFields, BorderLayout.CENTER);
		aUpdate.add(aaButtons, BorderLayout.SOUTH);
		aUpdate.setVisible(false);
		
		a = new Vector<>();
		//Collections.copy(a, p.accounts);
		a.addAll(p.getAccounts());
		ac = new JList<>(a);
		ac.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				removeAccount.setEnabled(true);
				updateAccount.setEnabled(true);
				
			}
			
		});
		ascroll = new JScrollPane(ac);
		accounts.add(ascroll);
		screen.add(accounts, ACCOUNTS);

		//simulation
		simulation = new JPanel();
		simulation.setLayout(new BorderLayout());
		simText = new Vector<String>();
		simResults = new JList<>(simText);
		simScroll = new JScrollPane();
		simScroll.add(simResults);
		simulation.add(simScroll, BorderLayout.CENTER);
		simSetup = new JPanel();
		simSetup.setLayout(new GridLayout(7,2));
		simTypeLabel = new JLabel("Simulation Type: ");
		simTypeField = new JTextField();
		simSetup.add(simTypeLabel);
		simSetup.add(simTypeField);
		ti = new JLabel("Time Interval: ");
		simSetup.add(ti);
		timeInterval = new ButtonGroup();
		day = new JRadioButton("Day");
		month = new JRadioButton("Month");
		year = new JRadioButton("Year");
		timeInterval.add(day);
		timeInterval.add(month);
		timeInterval.add(year);
		tiPanel = new JPanel();
		tiPanel.setLayout(new GridLayout(3,1));
		tiPanel.add(day);
		tiPanel.add(month);
		tiPanel.add(year);
		simSetup.add(tiPanel);
		rt = new JLabel("Run Time: ");
		simSetup.add(rt);
		runTime = new ButtonGroup();
		stepByStep = new JRadioButton("Step-By-Step");
		runStraightThrough = new JRadioButton("Run Straight Through");
		runTime.add(stepByStep);
		runTime.add(runStraightThrough);
		rtPanel = new JPanel();
		rtPanel.setLayout(new GridLayout(2,1));
		rtPanel.add(stepByStep);
		rtPanel.add(runStraightThrough);
		simSetup.add(rtPanel);		
		numStepsLabel = new JLabel("Number of Steps:");
		simSetup.add(numStepsLabel);
		numStepsText = new JTextField();
		simSetup.add(numStepsText);
		pricePerStockLabel = new JLabel("Price per Stock:");
		simSetup.add(pricePerStockLabel);
		pricePerStockText = new JTextField();
		simSetup.add(pricePerStockText);
		percentChangeLabel = new JLabel("Percent Change:");
		simSetup.add(percentChangeLabel);
		percentChangeText = new JTextField();
		simSetup.add(percentChangeText);		
		runSimulation = new JButton("Run");
		runSimulation.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String simType = simTypeField.getText();
				int steps = Integer.parseInt(numStepsText.getText());
				char stepSize = 'd';
				if(day.isSelected()){
					stepSize = 'd';
				}
				if(month.isSelected()){
					stepSize = 'm';
				}
				if(year.isSelected()){
					stepSize = 'y';
				}
				double pricePerStock = Double.parseDouble(pricePerStockText.getText());
				double percentChange = Double.parseDouble(percentChangeText.getText());;
				simText.add(String.valueOf(controller.runSim(simType, steps, stepSize, pricePerStock, percentChange)));
				simResults = new JList<String>(simText);
			}
			
		});
		resetSim = new JButton("Reset");
		resetSim.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.resetSim();
			}
			
		});
		simSetup.add(runSimulation);
		simSetup.add(resetSim);
		simulation.add(simSetup, BorderLayout.EAST);
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
				controller.update();
				
			}
			
		});
		logout = new JButton("LOGOUT");
		
		//needs fixing
		logout.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				LoginFrame lg = new LoginFrame();
				lg.setVisible(true);
			}
			
		});
		imp = new JButton("IMPORT");
		imp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.imp();
				
			}
			
		});
		exp = new JButton("EXPORT");
		exp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.exp();
				
			}
			
		});
		timer = new JButton("TIMER");
		timerFrame = new JFrame();
		timerFrame.setLayout(new BorderLayout());
		timerFrame.setVisible(false);
		timerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		timerFrame.setSize(300, 200);
		timerPanel = new JPanel();
		timerPanel.setLayout(new BorderLayout());
		setTimeLabel = new JLabel("Set Timer: ");
		setTimeField = new JTextField();
		timerPanel.add(setTimeLabel, BorderLayout.WEST);
		timerPanel.add(setTimeField, BorderLayout.CENTER);
		timerButton = new JButton("Set Timer");
		timerButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.YahooTimer(Integer.parseInt(setTimeField.getText()));
				equities.setListData(new Vector<Equity>(p.getEquities()));
				
			}
			
		});
		timerFrame.add(timerPanel, BorderLayout.CENTER);
		timerFrame.add(timerButton, BorderLayout.SOUTH);
		timer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				timerFrame.setVisible(true);
			}
			
		});
		
		buttons.add(update);
		buttons.add(logout);
		buttons.add(imp);
		buttons.add(exp);
		buttons.add(timer);
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
