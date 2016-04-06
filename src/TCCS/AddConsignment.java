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
import javax.swing.JList;
import javax.swing.JComboBox;

public class AddConsignment extends JFrame {

	private JPanel contentPane;
	OfficeSuccess os;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JComboBox comboBox;
	public AddConsignment(OfficeSuccess os) {
		setResizable(false);
		this.os = os;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddAConsignment = new JLabel("Add a Consignment");
		lblAddAConsignment.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblAddAConsignment.setBounds(10, 11, 157, 14);
		contentPane.add(lblAddAConsignment);
		
		JLabel lblSendersName = new JLabel("Sender's Name : ");
		lblSendersName.setBounds(10, 47, 111, 14);
		contentPane.add(lblSendersName);
		
		JLabel lblSendersAddress = new JLabel("Sender's Address : ");
		lblSendersAddress.setBounds(10, 72, 111, 14);
		contentPane.add(lblSendersAddress);
		
		JLabel lblReceiversAddress_1 = new JLabel("Receiver's Name : ");
		lblReceiversAddress_1.setBounds(10, 97, 111, 14);
		contentPane.add(lblReceiversAddress_1);
		
		JLabel lblReceiversAddress = new JLabel("Receiver's Address : ");
		lblReceiversAddress.setBounds(10, 122, 122, 14);
		contentPane.add(lblReceiversAddress);
		
		JLabel lblFrom = new JLabel("From :");
		lblFrom.setBounds(10, 147, 111, 14);
		contentPane.add(lblFrom);
		
		JLabel lblTo = new JLabel("To :");
		lblTo.setBounds(10, 172, 111, 14);
		contentPane.add(lblTo);
		
		JLabel lblVolume = new JLabel("Volume : ");
		lblVolume.setBounds(10, 197, 111, 14);
		contentPane.add(lblVolume);
		
		textField = new JTextField();
		textField.setBounds(142, 44, 292, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(142, 69, 292, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(142, 94, 292, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(142, 119, 292, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(142, 144, 292, 20);
		contentPane.add(textField_4);
		textField_4.setText(Integer.valueOf(os.id).toString());
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(142, 194, 292, 20);
		contentPane.add(textField_5);
		
		JButton btnAdd = new JButton("BACK");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddActionPerformed();
			}
		});
		btnAdd.setBounds(282, 237, 89, 23);
		contentPane.add(btnAdd);
		
		JButton button = new JButton("ADD");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonActionPerformed();
			}
		});
		button.setBounds(142, 237, 89, 23);
		contentPane.add(button);
		
		ArrayList<Integer> to = new ArrayList<>();
		for(int i=0;i<os.prev.m.getCountOfOffices();i++){
			if(os.id!=i) to.add(Integer.valueOf(i));
		}
		comboBox = new JComboBox(to.toArray());
		comboBox.setBounds(142, 169, 292, 20);
		contentPane.add(comboBox);
	}
	
	public void btnAddActionPerformed(){
		os.setVisible(true);
		this.dispose();
	}
	public void buttonActionPerformed(){
		if(textField.getText().isEmpty()||textField_1.getText().isEmpty()||textField_2.getText().isEmpty()||textField_3.getText().isEmpty()||textField_4.getText().isEmpty()||textField_5.getText().isEmpty()){
			JOptionPane.showMessageDialog(null,"Fields left Empty");
		}
		else if(!(comboBox.getSelectedItem() instanceof Integer)){
			JOptionPane.showMessageDialog(null,"Fields left Empty");
		}
		else{
			os.prev.m.getOffice(os.id).addConsignment(textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),Integer.parseInt(textField_4.getText()),((Integer) comboBox.getSelectedItem()).intValue(),Integer.parseInt(textField_5.getText()),os.prev.m.getCountOfConsignments());
			try {
				os.btnSaveActionPerformed();
			} catch (Exception e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null,"Consignment Added");
			if(os.prev.m.getConsignments().get(os.prev.m.getConsignments().size()-1).getStatus() == ConsignmentStatus.sent ){
				SentTruck st = new SentTruck(os,os.prev.m.getConsignments().get(os.prev.m.getCountOfConsignments()-1).getTruckAllocated());
				st.setVisible(true);
			}
			IssueBill ib = new IssueBill(os,os.prev.m.getCountOfConsignments()-1);
			ib.setVisible(true);
			this.dispose();
		}
	}
}
