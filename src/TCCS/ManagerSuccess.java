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

public class ManagerSuccess extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public LoginWindow prev;
    	public ManagerSuccess(LoginWindow prev) {
    		setResizable(false);
    		this.prev = prev;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHi = new JLabel("Hi ");
		lblHi.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblHi.setBounds(10, 11, 28, 14);
		contentPane.add(lblHi);
		
		textField = new JTextField();
		textField.setFont(new Font("Stencil", Font.PLAIN, 14));
		textField.setEditable(false);
		textField.setBounds(48, 8, 212, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(prev.m.getname());
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogoutActionPerformed();
			}
		});
		btnLogout.setBounds(336, 7, 89, 23);
		contentPane.add(btnLogout);
		
		JButton btnNewButton = new JButton("View Consignment Status");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButtonActionPerformed();
			}
		});
		btnNewButton.setBounds(130, 53, 190, 23);
		contentPane.add(btnNewButton);
		
		JButton btnViewTruckStatus = new JButton("View Truck Status");
		btnViewTruckStatus.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnViewTruckStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnViewTruckStatusActionPerformed();
			}
		});
		btnViewTruckStatus.setBounds(130, 87, 190, 23);
		contentPane.add(btnViewTruckStatus);
		
		JButton btnGetRevenueOf = new JButton("Get Revenue of an Office ");
		btnGetRevenueOf.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnGetRevenueOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGetRevenueOfActionPerformed();
			}
		});
		btnGetRevenueOf.setBounds(130, 121, 190, 23);
		contentPane.add(btnGetRevenueOf);
		
		JButton btnGetRevenueOf_1 = new JButton("Get Idle Time of a Truck ");
		btnGetRevenueOf_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnGetRevenueOf_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGetRevenueOf_1ActionPerformed();
			}
		});
		btnGetRevenueOf_1.setBounds(130, 155, 190, 23);
		contentPane.add(btnGetRevenueOf_1);
		
		JButton btnGetWaitingTime = new JButton("Get Waiting Time of Cosignment ");
		btnGetWaitingTime.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnGetWaitingTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGetWaitingTimeActionPerformed();
			}
		});
		btnGetWaitingTime.setBounds(130, 189, 190, 23);
		contentPane.add(btnGetWaitingTime);
	}
    
    public void btnLogoutActionPerformed(){
    	prev.setVisible(true);
    	this.dispose();
    }
    public void btnNewButtonActionPerformed(){
    	ViewConsignment vc = new ViewConsignment(this);
    	vc.setVisible(true);
    	this.setVisible(false);
    }
    public void btnViewTruckStatusActionPerformed(){
    	ViewTruck vt = new ViewTruck(this);
    	vt.setVisible(true);
    	this.setVisible(false);
    }
    public void btnGetRevenueOfActionPerformed(){
    	GetRevenue gr = new GetRevenue(this);
    	gr.setVisible(true);
    	this.setVisible(false);
    }
    public void btnGetRevenueOf_1ActionPerformed(){
    ShowIdle si = new ShowIdle(this);
    si.setVisible(true);
    this.setVisible(false);
    }
    public void btnGetWaitingTimeActionPerformed(){
    	IdleConsignment ic = new IdleConsignment(this);
    	ic.setVisible(true);
    	this.setVisible(false);
    }
//    public void btnSaveActionPerformed() throws Exception{
//    	
//    	GD.id = (int) Serialize.writeJavaObject(GD.conn, prev.m);
//    	Id.updateId(GD.id);
//    	System.out.println("Saved Successfully");
//    	 }
}
