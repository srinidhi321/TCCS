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

public class OfficeRevenue extends JFrame {

	private JPanel contentPane;
	ManagerSuccess ms;
	String name;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblRs;
	private JButton btnBack;
	int index;
	private JTextField textField_2;
	private JLabel lblCubicMeters;
	private JLabel lblTotalVolume;
	public OfficeRevenue(ManagerSuccess ms,String name,int index) {
		setResizable(false);
		this.ms = ms;
		this.name = name;
		this.index = index;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRevenueOfOffice = new JLabel("Revenue of Office  ");
		lblRevenueOfOffice.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblRevenueOfOffice.setBounds(10, 11, 160, 14);
		contentPane.add(lblRevenueOfOffice);
		
		textField = new JTextField();
		textField.setFont(new Font("Stencil", Font.PLAIN, 14));
		textField.setBounds(186, 8, 221, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(name);
		
		textField_1 = new JTextField();
		textField_1.setBounds(186, 39, 110, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(Long.valueOf(ms.prev.m.getOffice(index).getRevenue()).toString());
		
		lblRs = new JLabel("RS.");
		lblRs.setBounds(296, 42, 35, 14);
		contentPane.add(lblRs);
		
		btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackActionPerformed();
			}
		});
		btnBack.setBounds(186, 107, 89, 23);
		contentPane.add(btnBack);
		
		textField_2 = new JTextField();
		textField_2.setBounds(186, 70, 110, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(Long.valueOf(ms.prev.m.getOffice(index).getNetVolume()).toString());
		
		lblCubicMeters = new JLabel("cubic meters");
		lblCubicMeters.setBounds(296, 73, 72, 14);
		contentPane.add(lblCubicMeters);
		
		lblTotalVolume = new JLabel("Total Volume : ");
		lblTotalVolume.setBounds(81, 73, 89, 14);
		contentPane.add(lblTotalVolume);
	}
	public void btnBackActionPerformed(){
		ms.setVisible(true);
		this.dispose();
	}
}
