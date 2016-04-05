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

public class ConsignmentIdleView extends JFrame {

	private JPanel contentPane;
	ManagerSuccess ms;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblIs;
	private JLabel lblHrs;
	private JButton btnBack;
	public ConsignmentIdleView(ManagerSuccess ms,int index) {
		setResizable(false);
		this.ms = ms;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 374, 143);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsignmentStatus = new JLabel("Waiting Time Of Consignment");
		lblConsignmentStatus.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblConsignmentStatus.setBounds(10, 11, 241, 14);
		contentPane.add(lblConsignmentStatus);
		
		textField = new JTextField();
		textField.setBounds(68, 45, 168, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(Long.valueOf(ms.prev.m.getConsignments().get(index).getWaitingTime()).toString());
		
		textField_1 = new JTextField();
		textField_1.setBounds(272, 8, 47, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(Integer.valueOf(index).toString());
		lblIs = new JLabel("IS");
		lblIs.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblIs.setBounds(336, 11, 22, 14);
		contentPane.add(lblIs);
		
		lblHrs = new JLabel("Mins.");
		lblHrs.setBounds(240, 48, 46, 14);
		contentPane.add(lblHrs);
		
		btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackActionPerformed();
			}
		});
		btnBack.setBounds(147, 77, 89, 23);
		contentPane.add(btnBack);
	}
	public void btnBackActionPerformed(){
		ms.setVisible(true);
		this.dispose();
	}
}
