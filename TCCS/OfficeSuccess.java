package TCCS;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OfficeSuccess extends JFrame {

	private JPanel contentPane;
	private LoginWindow prev;
	private JTextField textField;
    	public OfficeSuccess(LoginWindow prev,int id) {
    		setResizable(false);
    		this.prev=prev;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHiWelcome = new JLabel("Hi ! Welcome to");
		lblHiWelcome.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblHiWelcome.setBounds(10, 11, 123, 14);
		contentPane.add(lblHiWelcome);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("Stencil", Font.PLAIN, 14));
		textField.setBounds(143, 8, 169, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(prev.m.getOffice(id).getName());
		
		JButton btnAddConsignment = new JButton("Add Consignment");
		btnAddConsignment.setBounds(130, 60, 185, 23);
		contentPane.add(btnAddConsignment);
		
		JButton btnUnloadTruck = new JButton("Unload Truck");
		btnUnloadTruck.setBounds(130, 94, 185, 23);
		contentPane.add(btnUnloadTruck);
		
		JButton btnNewButton = new JButton("View Loading Trucks");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(130, 128, 185, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("View Unassigned Consignments");
		btnNewButton_1.setBounds(130, 160, 185, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogoutActionPerformed();
			}
		});
		btnLogout.setBounds(340, 7, 89, 23);
		contentPane.add(btnLogout);
		
		JButton btnViewIdleTrucks = new JButton("View Idle Trucks");
		btnViewIdleTrucks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnViewIdleTrucks.setBounds(127, 194, 185, 23);
		contentPane.add(btnViewIdleTrucks);
		
	}
    public void btnLogoutActionPerformed(){
    	prev.setVisible(true);
    	this.dispose();
    }
}
