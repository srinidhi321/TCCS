package TCCS;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewTruck extends JFrame {

	private JPanel contentPane;
	ManagerSuccess ms;
	JComboBox comboBox;
	public ViewTruck(ManagerSuccess ms) {
		setResizable(false);
		this.ms = ms;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 217, 131);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsignmentStatus = new JLabel("Truck Status :");
		lblConsignmentStatus.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblConsignmentStatus.setBounds(10, 11, 133, 14);
		contentPane.add(lblConsignmentStatus);
		
		JLabel lblSelectTheConsignment = new JLabel("Select the Truck :");
		lblSelectTheConsignment.setBounds(10, 44, 104, 14);
		contentPane.add(lblSelectTheConsignment);
	    
		ArrayList <Truck> t = ms.prev.m.getTrucks();
		ArrayList <Integer> tt = new ArrayList<>();
		for(int i=0; i<t.size();i++){
			tt.add(t.get(i).getId());
		}
		comboBox = new JComboBox(tt.toArray());
		comboBox.setBounds(124, 41, 75, 20);
		contentPane.add(comboBox);
		
		JButton btnGo = new JButton("GO");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGoActionPerformed();
			}
		});
		btnGo.setBounds(124, 68, 75, 23);
		contentPane.add(btnGo);
		
		JButton btnNewButton = new JButton("<-");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButtonActionPerformed();
			}
		});
		btnNewButton.setBounds(153, 7, 45, 23);
		contentPane.add(btnNewButton);
	}
	public void btnNewButtonActionPerformed(){
		ms.setVisible(true);
		this.dispose();
	}
	public void btnGoActionPerformed(){
		if(comboBox.getSelectedItem()!=null){
			TruckStatusView ts = new TruckStatusView(ms,(Integer)comboBox.getSelectedItem());
			ts.setVisible(true);
			this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(null,"No Selected Consignments");
		}
	}
}
