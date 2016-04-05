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

public class TruckStatusView extends JFrame {

	private JPanel contentPane;
	ManagerSuccess ms;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public TruckStatusView(ManagerSuccess ms,int index) {
		this.ms = ms;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 288, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsignmentStatus = new JLabel("Truck Status :");
		lblConsignmentStatus.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblConsignmentStatus.setBounds(10, 11, 126, 14);
		contentPane.add(lblConsignmentStatus);
		
		JLabel lblFrom = new JLabel("Source : ");
		lblFrom.setBounds(10, 48, 74, 14);
		contentPane.add(lblFrom);
		
		JLabel lblDestination = new JLabel("Destination :");
		lblDestination.setBounds(10, 73, 74, 14);
		contentPane.add(lblDestination);
		
		textField = new JTextField();
		textField.setBounds(104, 45, 158, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(ms.prev.m.getOffice(ms.prev.m.getTruck(index).getSource()).getName());
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(104, 70, 158, 20);
		contentPane.add(textField_1);
		if(ms.prev.m.getTruck(index).getDestination()!=-1)textField_1.setText(ms.prev.m.getOffice(ms.prev.m.getTruck(index).getDestination()).getName());
		else textField_1.setText("-");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(104, 95, 158, 20);
		contentPane.add(textField_2);
		textField_2.setText(ms.prev.m.getTruck(index).getStatus().toString());
		
		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setBounds(10, 98, 74, 14);
		contentPane.add(lblStatus);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackActionPerformed();
			}
		});
		btnBack.setBounds(104, 144, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblVolumeFilled = new JLabel("Volume Filled :");
		lblVolumeFilled.setBounds(10, 123, 89, 14);
		contentPane.add(lblVolumeFilled);
		
		textField_3 = new JTextField();
		textField_3.setText((String) null);
		textField_3.setColumns(10);
		textField_3.setBounds(104, 120, 158, 20);
		contentPane.add(textField_3);
		textField_3.setText(Integer.valueOf(ms.prev.m.getTruck(index).getVolumeFilled()).toString());
	}
	public void btnBackActionPerformed(){
		ms.setVisible(true);
		this.dispose();
	}
}
