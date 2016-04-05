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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class UnloadTruck extends JFrame {

	private JPanel contentPane;
	OfficeSuccess os;
	JComboBox comboBox;
	public UnloadTruck(OfficeSuccess os) {
		this.os=os;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 316, 154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Unlaod a Truck");
		lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 126, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblEnterTheTruck = new JLabel("Enter the truck ID to unload : ");
		lblEnterTheTruck.setBounds(10, 47, 154, 14);
		contentPane.add(lblEnterTheTruck);
		
		JButton btnUnload = new JButton("Unload");
		btnUnload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUnloadActionPerformed();
			}
		});
		btnUnload.setBounds(174, 75, 116, 23);
		contentPane.add(btnUnload);
		
		JButton btnBack = new JButton("<-");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackActionPerformed();
			}
		});
		btnBack.setBounds(226, 7, 64, 23);
		contentPane.add(btnBack);
		ArrayList<Integer> tr = new ArrayList<>();
		for(int i=0;i<os.prev.m.getTrucks().size();i++) tr.add(i);
		comboBox = new JComboBox(tr.toArray());
		comboBox.setBounds(174, 44, 116, 20);
		contentPane.add(comboBox);
		
		
	}
	public void btnBackActionPerformed(){
		os.setVisible(true);
		this.dispose();
	}
	public void btnUnloadActionPerformed(){
		if(comboBox.getSelectedIndex()==-1){
			JOptionPane.showMessageDialog(null,"Fields Left Empty");
		}
		else if(os.prev.m.getTruck((Integer)comboBox.getSelectedItem()).getDestination()!=os.id){
			JOptionPane.showMessageDialog(null,"The Truck wasn't sent to this Office. Enter a valid Truck ID");
		}
		else {
			System.out.println(os.prev.m.getTruck((Integer)comboBox.getSelectedItem()).getDestination());
		    os.prev.m.getOffice(os.id).unloadTruck((Integer)comboBox.getSelectedItem());
		    JOptionPane.showMessageDialog(null,"Truck Successfully Unloaded");
		    os.setVisible(true);
		    this.dispose();
		}
	}
}
