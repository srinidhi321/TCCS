package TCCS;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import databases.GD;
import databases.Id;
import databases.Serialize;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OfficeSuccess extends JFrame {

	private JPanel contentPane;
	public LoginWindow prev;
	public int id;
	private JTextField textField;
    	public OfficeSuccess(LoginWindow prev,int id) {
    		setResizable(false);
    		this.prev=prev;
    		this.id = id;
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
		btnAddConsignment.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAddConsignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAddConsignmentActionPerformed();
			}
		});
		btnAddConsignment.setBounds(125, 60, 195, 23);
		contentPane.add(btnAddConsignment);
		
		JButton btnUnloadTruck = new JButton("Unload Truck");
		btnUnloadTruck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUnloadTruckActionPerformed();
			}
		});
		btnUnloadTruck.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnUnloadTruck.setBounds(125, 94, 195, 23);
		contentPane.add(btnUnloadTruck);
		
		JButton btnNewButton = new JButton("View Loading Trucks");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButtonActionPerformed();
			}
		});
		btnNewButton.setBounds(125, 128, 195, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("View Unassigned Consignments");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButton_1ActionPerformed();
			}
		});
		btnNewButton_1.setBounds(125, 160, 195, 23);
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
		btnViewIdleTrucks.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnViewIdleTrucks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnViewIdleTrucksActionPerformed();
			}
		});
		btnViewIdleTrucks.setBounds(125, 194, 195, 23);
		contentPane.add(btnViewIdleTrucks);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnSaveActionPerformed();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSave.setBounds(340, 34, 89, 23);
		contentPane.add(btnSave);
		
	}
    public void btnLogoutActionPerformed(){
    	prev.setVisible(true);
    	this.dispose();
    }
    public void btnAddConsignmentActionPerformed(){
    	AddConsignment ac = new AddConsignment(this);
    	ac.setVisible(true);
    	this.setVisible(false);
    }
    public void btnUnloadTruckActionPerformed(){
    	UnloadTruck ut = new UnloadTruck(this);
    	ut.setVisible(true);
    	this.setVisible(false);
    }
    public void btnNewButtonActionPerformed(){
    	LoadingTrucks lt = new LoadingTrucks(this);
    	lt.setVisible(true);
    	this.setVisible(false);
    }
    public void btnNewButton_1ActionPerformed(){
    	UnassignedConsignments uc = new UnassignedConsignments(this);
    	uc.setVisible(true);
    	this.setVisible(false);
    }
    public void btnViewIdleTrucksActionPerformed(){
    	IdleTrucks it = new IdleTrucks(this);
    	it.setVisible(true);
    	this.setVisible(false);
    }
    public void btnSaveActionPerformed() throws Exception{
    	GD.id = (int) Serialize.writeJavaObject(GD.conn, prev.m);
    	Id.updateId(GD.id);
    	System.out.println("Saved Successfully");
    }
}
