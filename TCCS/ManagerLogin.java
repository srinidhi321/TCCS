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

public class ManagerLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	Manager m;
	LoginWindow prev;
    	public ManagerLogin(Manager m,LoginWindow prev) {
    		this.m = m;
    		this.prev = prev;
    		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 377, 171);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeToManger = new JLabel("Welcome to Manger Login :");
		lblWelcomeToManger.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblWelcomeToManger.setBounds(10, 11, 232, 14);
		contentPane.add(lblWelcomeToManger);
		
		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setBounds(10, 48, 74, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setBounds(10, 85, 74, 14);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(106, 45, 242, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(106, 82, 242, 20);
		contentPane.add(textField_1);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnLoginActionPerformed();
			}
		});
		btnLogin.setBounds(106, 113, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackActionPerformed(); 
			}
		});
		btnBack.setBounds(240, 113, 89, 23);
		contentPane.add(btnBack);
	}
    public void btnLoginActionPerformed(){
    	if(m.login(textField.getText(),textField_1.getText())){
    		JOptionPane.showMessageDialog(null,"Success");
    	}
    	else{
    		JOptionPane.showMessageDialog(null,"Error");
    	}
    }
    public void btnBackActionPerformed(){
    	prev.setVisible(true);
    	this.dispose();
    }
}
