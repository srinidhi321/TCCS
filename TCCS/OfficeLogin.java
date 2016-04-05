package TCCS;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class OfficeLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	Manager m;
	LoginWindow prev;
	private JPasswordField passwordField;
	public OfficeLogin(Manager m,LoginWindow prev) {
		this.m = m;
		this.prev = prev;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 351, 187);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeToOffice = new JLabel("Welcome to Office Login : ");
		lblWelcomeToOffice.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblWelcomeToOffice.setBounds(10, 11, 192, 14);
		contentPane.add(lblWelcomeToOffice);
		
		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setBounds(10, 53, 68, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setBounds(10, 86, 68, 14);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(98, 50, 227, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoginActionPerformed();
			}
		});
		btnLogin.setBounds(98, 114, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnBackActionPerformed();
			}
		});
		btnBack.setBounds(236, 114, 89, 23);
		contentPane.add(btnBack);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(98, 83, 227, 20);
		contentPane.add(passwordField);
	}
    public void btnLoginActionPerformed(){
    	boolean login = false;
    	System.out.println(prev.m.getCountOfOffices());
    	for(int i=0;i<prev.m.getCountOfOffices();i++){
    		if(prev.m.getOffice(i).login(textField.getText(),passwordField.getText())) {
    			OfficeSuccess os = new OfficeSuccess(prev,i);
    			login = true;
    			os.setVisible(true);
    			this.dispose();
    		};
    	}
    	if(!login) JOptionPane.showMessageDialog(null,"Invalid UserName / Password");
    }
    public void btnBackActionPerformed(){
    prev.setVisible(true);;
    this.dispose();
    }
}
