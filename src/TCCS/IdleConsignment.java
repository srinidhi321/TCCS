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

public class IdleConsignment extends JFrame {

	private JPanel contentPane;
	ManagerSuccess ms;
	JComboBox comboBox;
	public IdleConsignment(ManagerSuccess ms) {
		setResizable(false);
		this.ms = ms;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 131);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsignmentStatus = new JLabel("WaitingTime of a Consignment :");
		lblConsignmentStatus.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblConsignmentStatus.setBounds(10, 11, 263, 14);
		contentPane.add(lblConsignmentStatus);
		
		JLabel lblSelectTheConsignment = new JLabel("Select the Consignment :");
		lblSelectTheConsignment.setBounds(10, 44, 148, 14);
		contentPane.add(lblSelectTheConsignment);
	    
		ArrayList <Consignments> c = ms.prev.m.getConsignments();
		ArrayList <Integer> cc = new ArrayList<>();
		for(int i=0; i<c.size();i++){
			cc.add(c.get(i).getId());
		}
		comboBox = new JComboBox(cc.toArray());
		comboBox.setBounds(255, 41, 75, 20);
		contentPane.add(comboBox);
		
		JButton btnGo = new JButton("GO");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGoActionPerformed();
			}
		});
		btnGo.setBounds(255, 68, 75, 23);
		contentPane.add(btnGo);
		
		JButton btnNewButton = new JButton("<-");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButtonActionPerformed();
			}
		});
		btnNewButton.setBounds(283, 7, 45, 23);
		contentPane.add(btnNewButton);
	}
	public void btnNewButtonActionPerformed(){
		ms.setVisible(true);
		this.dispose();
	}
	public void btnGoActionPerformed(){
		if(comboBox.getSelectedIndex()!=-1){
			ConsignmentIdleView ci = new ConsignmentIdleView(ms,(Integer)comboBox.getSelectedItem());
			ci.setVisible(true);
			this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(null,"No Selected Consignments");
		}
	}
}
