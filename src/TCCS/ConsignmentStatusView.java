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

public class ConsignmentStatusView extends JFrame {

	private JPanel contentPane;
	ManagerSuccess ms;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public ConsignmentStatusView(ManagerSuccess ms,int index) {
		setResizable(false);
		this.ms = ms;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 288, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsignmentStatus = new JLabel("Consignment Status :");
		lblConsignmentStatus.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblConsignmentStatus.setBounds(10, 11, 181, 14);
		contentPane.add(lblConsignmentStatus);
		
		JLabel lblFrom = new JLabel("Source : ");
		lblFrom.setBounds(10, 48, 74, 14);
		contentPane.add(lblFrom);
		
		JLabel lblDestination = new JLabel("Destination :");
		lblDestination.setBounds(10, 73, 74, 14);
		contentPane.add(lblDestination);
		
		textField = new JTextField();
		textField.setBounds(94, 45, 168, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(ms.prev.m.getOffice(ms.prev.m.getConsignments().get(index).getSource()).getName());
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(94, 70, 168, 20);
		contentPane.add(textField_1);
		textField_1.setText(ms.prev.m.getOffice(ms.prev.m.getConsignments().get(index).getDestination()).getName());
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(94, 95, 168, 20);
		contentPane.add(textField_2);
		textField_2.setText(ms.prev.m.getConsignments().get(index).getStatus().toString());
		
		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setBounds(10, 98, 74, 14);
		contentPane.add(lblStatus);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackActionPerformed();
			}
		});
		btnBack.setBounds(94, 126, 89, 23);
		contentPane.add(btnBack);
	}
	public void btnBackActionPerformed(){
		ms.setVisible(true);
		this.dispose();
	}
}
