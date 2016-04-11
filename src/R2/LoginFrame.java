package R2;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class LoginFrame extends JFrame{

	private ArrayList<Portfolio> portfolios;
	private PortfolioGUI pg;
	private Portfolio p;
	private String name;
	private String uName;
	private String pWord;
	private User u;
	
	//login
	//private JFrame login;
	private JPanel loginScreen;
	private JPanel loginButtons;
	private JPanel un;
	private JPanel pw;
	private JLabel usernameLabel;
	private JTextField username;
	private JLabel passwordLabel;
	private JTextField password;
	private JButton signIn;
	private JButton newProfile;

	//create new profile
	private JFrame newUser;
	private JPanel createProfile;
	private JPanel nn;
	private JPanel nun;
	private JPanel npw;
	private JPanel nuButtons;
	private JLabel newNameLabel;
	private JTextField newName;
	private JLabel newUsernameLabel;
	private JTextField newUsername;
	private JLabel newPasswordLabel;
	private JTextField newPassword;
	private JButton create;
	private JButton backToLogin;

	public LoginFrame(){

		//login
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(500,500);
		
		portfolios = new ArrayList<>();
		name = "";
		uName = "";
		pWord = "";

		loginScreen = new JPanel();
		loginScreen.setLayout(new GridLayout(2,1));
		loginButtons = new JPanel();
		loginButtons.setLayout(new GridLayout());
		un = new JPanel();
		un.setLayout(new BorderLayout());
		pw = new JPanel();
		pw.setLayout(new BorderLayout());

		usernameLabel = new JLabel("Username:");
		username = new JTextField();
		un.add(usernameLabel, BorderLayout.WEST);
		un.add(username, BorderLayout.SOUTH);
		loginScreen.add(un);
		passwordLabel = new JLabel("Password: ");
		password = new JTextField();
		pw.add(passwordLabel, BorderLayout.WEST);
		pw.add(password, BorderLayout.SOUTH);
		loginScreen.add(pw);
		signIn = new JButton("SIGN IN");
		newProfile = new JButton("NEW PROFILE");
		loginButtons.add(signIn);
		loginButtons.add(newProfile);
		add(loginScreen, BorderLayout.CENTER);
		add(loginButtons, BorderLayout.SOUTH);
		//setVisible(true);
		signIn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				uName = username.getText();
				pWord = password.getText();
				User u = null;
				try {
					u = new User(uName, pWord);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(uName);
				for(int i = 0; i < portfolios.size(); i++){
					System.out.println("checking list item #" + i);
					if(u.getUsername().equals(portfolios.get(i).getUser().getUsername())){
						System.out.println("username correct");
						if(u.getPassword().equals(portfolios.get(i).getUser().getPassword())){
							System.out.println("password correct");
							System.out.println("found it");
							p = portfolios.get(i);
							pg = new PortfolioGUI(p);
							setVisible(false);
							pg.setVisible(true);
							return;
						}
						System.out.println("password incorrect or not found");
						return;
					}
					System.out.println("username incorrect or not found");
				}
			}

		});
		newProfile.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				newUser.setVisible(true);
			}

		});

		//userName
		newUser = new JFrame();
		newUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newUser.setLayout(new BorderLayout());
		newUser.setSize(500,500);

		nn = new JPanel();
		nn.setLayout(new BorderLayout());
		nun = new JPanel();
		nun.setLayout(new BorderLayout());
		npw = new JPanel();
		npw.setLayout(new BorderLayout());
		nuButtons = new JPanel();
		nuButtons.setLayout(new GridLayout());

		createProfile = new JPanel();
		createProfile.setLayout(new GridLayout(3,1));
		newNameLabel = new JLabel("New Name:");
		newName = new JTextField();
		nn.add(newNameLabel, BorderLayout.WEST);
		nn.add(newName, BorderLayout.SOUTH);
		createProfile.add(nn);
		newUsernameLabel = new JLabel("New Username:");
		newUsername = new JTextField();
		nun.add(newUsernameLabel, BorderLayout.WEST);
		nun.add(newUsername, BorderLayout.SOUTH);
		createProfile.add(nun);
		newPasswordLabel= new JLabel("New Password:");
		newPassword = new JTextField();
		npw.add(newPasswordLabel, BorderLayout.WEST);
		npw.add(newPassword, BorderLayout.SOUTH);
		createProfile.add(npw);
		create = new JButton("Create");
		create.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				name = newName.getText();
				uName = newUsername.getText();
				pWord = newPassword.getText();
				
				try {
					u = new User(uName, pWord);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				
				p = new Portfolio(u, uName);
				portfolios.add(p);
				
				for(int i = 0; i < portfolios.size(); i++){
					System.out.println(portfolios.get(i).toString());
					System.out.println(portfolios.get(i).getUser().getUsername());
				}
				
				newUser.setVisible(false);
				setVisible(true);
			}

		});
		backToLogin = new JButton("Back");
		backToLogin.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				newUser.setVisible(false);
				setVisible(true);				
			}

		});
		nuButtons.add(create);
		nuButtons.add(backToLogin);
		newUser.add(createProfile, BorderLayout.CENTER);
		newUser.add(nuButtons, BorderLayout.SOUTH);
		newUser.setVisible(false);

	}
	
	public static void main(String[] args) {
		LoginFrame lg = new LoginFrame();
		lg.setVisible(true);
	}

	
	public void setPortfolios(ArrayList<Portfolio> portfolios){
		this.portfolios = portfolios;
	}

}
