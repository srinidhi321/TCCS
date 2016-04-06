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

public class IssueBill extends JFrame {

	private JPanel contentPane;
	OfficeSuccess os;
	long bill;
	private JTextField textField;
	public IssueBill(OfficeSuccess os,int id) {
		setResizable(false);
		this.os = os;
		this.bill = bill;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 381, 106);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBillForThe = new JLabel("Bill for the consignment is");
		lblBillForThe.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblBillForThe.setBounds(10, 11, 239, 15);
		contentPane.add(lblBillForThe);
		
		textField = new JTextField();
		textField.setBounds(10, 40, 178, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(os.prev.m.getConsignments().get(id).printBill());
		
		JLabel lblRs = new JLabel("Rs.");
		lblRs.setBounds(198, 43, 27, 14);
		contentPane.add(lblRs);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackActionPerformed();
			}
		});
		btnBack.setBounds(259, 39, 89, 23);
		contentPane.add(btnBack);
	}
	public void btnBackActionPerformed(){
		os.setVisible(true);
		this.dispose();
	}

}
