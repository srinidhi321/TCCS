package TCCS;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class SentTruck extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Object[][] data;
	public SentTruck(OfficeSuccess os,int truck) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 689, 141);
		contentPane.add(scrollPane);
		
		ArrayList<Consignments> temp = os.prev.m.getTruck(truck).getAllConsignments();
		data = new Object[temp.size()][1];
		for(int i=0;i<temp.size();i++){
			data[i][0] = temp.get(i).print();
		}
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"Details"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(332);
		table.setFont(new Font("Stencil", Font.PLAIN, 11));
	}
}
