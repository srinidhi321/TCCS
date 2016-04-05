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

public class IdleView extends JFrame {

	private JPanel contentPane;
	ManagerSuccess ms;
	int index;
	private JTextField textField;
	private JTextField textField_1;
	public IdleView(ManagerSuccess ms,int index) {
		setResizable(false);
		this.index= index;
		this.ms = ms;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 303, 127);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdleTimeOf = new JLabel("Idle time of the truck");
		lblIdleTimeOf.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblIdleTimeOf.setBounds(10, 11, 192, 14);
		contentPane.add(lblIdleTimeOf);
		
		textField = new JTextField();
		textField.setBounds(200, 8, 56, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(Integer.valueOf(index).toString());
		
		JLabel lblIs = new JLabel("IS");
		lblIs.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblIs.setBounds(266, 11, 32, 14);
		contentPane.add(lblIs);
		
		textField_1 = new JTextField();
		textField_1.setBounds(49, 36, 135, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField.setText(Long.valueOf(ms.prev.m.getTruck(index).getIdleTime()).toString());
		
		JLabel lblHrs = new JLabel("Mins.");
		lblHrs.setBounds(186, 39, 46, 14);
		contentPane.add(lblHrs);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackActionPerformed();
			}
		});
		btnBack.setBounds(95, 67, 89, 23);
		contentPane.add(btnBack);
	}
	public void btnBackActionPerformed(){
		ms.setVisible(true);
		this.dispose();
	}

}
