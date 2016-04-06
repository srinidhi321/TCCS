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

public class GetRevenue extends JFrame {

	private JPanel contentPane;
	ManagerSuccess ms;
	JComboBox comboBox;
	int index;
	public GetRevenue(ManagerSuccess ms) {
		setResizable(false);
		this.ms = ms;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 305, 131);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsignmentStatus = new JLabel("Office revenue :");
		lblConsignmentStatus.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblConsignmentStatus.setBounds(10, 11, 159, 14);
		contentPane.add(lblConsignmentStatus);
		
		JLabel lblSelectTheConsignment = new JLabel("Select the Office :");
		lblSelectTheConsignment.setBounds(10, 44, 120, 14);
		contentPane.add(lblSelectTheConsignment);
	    
		ArrayList <String> oo = new ArrayList<>();
		for(int i=0; i<ms.prev.m.getCountOfOffices();i++){
			oo.add(ms.prev.m.getOffice(i).getName());
		}
		comboBox = new JComboBox(oo.toArray());
		comboBox.setBounds(149, 41, 140, 20);
		contentPane.add(comboBox);
		
		JButton btnGo = new JButton("GO");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGoActionPerformed();
			}
		});
		btnGo.setBounds(149, 68, 75, 23);
		contentPane.add(btnGo);
		
		JButton btnNewButton = new JButton("<-");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButtonActionPerformed();
			}
		});
		btnNewButton.setBounds(244, 7, 45, 23);
		contentPane.add(btnNewButton);
	}
	public void btnNewButtonActionPerformed(){
		ms.setVisible(true);
		this.dispose();
	}
	public void btnGoActionPerformed(){
		if(comboBox.getSelectedItem()!=null){
			OfficeRevenue or = new OfficeRevenue(ms,(String)comboBox.getSelectedItem(),ms.prev.m.getOffice(comboBox.getSelectedIndex()).getId());
			or.setVisible(true);
			this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(null,"No Selected Consignments");
		}
	}
}
