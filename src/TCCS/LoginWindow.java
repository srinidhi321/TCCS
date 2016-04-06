package TCCS;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import databases.GD;
import databases.Id;
import databases.Serialize;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	public Manager m;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD gd = new GD();
					GD.id=Id.getId();
					Connection conn = (Connection) Serialize.getConnection();
					GD.conn=conn;
			        Manager m = (Manager)Serialize.readJavaObject(conn,GD.id);
			       
			        System.out.println(m.getCountOfOffices());
					LoginWindow frame = new LoginWindow(m);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginWindow(Manager m) {
		this.m=m;
		System.out.println(this.m.getCountOfOffices());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 213);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnManager = new JButton("Manager");
		btnManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnManagerActionPerformed(); 
			}
		});
		btnManager.setBounds(60, 66, 89, 47);
		contentPane.add(btnManager);
		
		JButton btnOffice = new JButton("Office");
		btnOffice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOfficeActionPerformed();
			}
		});
		btnOffice.setBounds(300, 66, 89, 47);
		contentPane.add(btnOffice);
		
		JLabel lblNewLabel = new JLabel("CHOOSE YOUR LOGIN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(21, 11, 402, 44);
		contentPane.add(lblNewLabel);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnExitActionPerformed();
			}
		});
		btnExit.setBounds(179, 143, 89, 23);
		contentPane.add(btnExit);
	}
	public void btnOfficeActionPerformed(){
		OfficeLogin t = new OfficeLogin(m,this);
		t.setVisible(true);
		this.setVisible(false);
	}
	public void btnManagerActionPerformed(){
		ManagerLogin t = new ManagerLogin(m,this);
		t.setVisible(true);
		this.setVisible(false);
	}
	public void btnExitActionPerformed(){
		this.dispose();
	}
}
