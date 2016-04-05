package TCCS;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class UnassignedConsignments extends JFrame {

	private JPanel contentPane;
	OfficeSuccess os;
	private JTable table;
	private Object data[][];
	private ArrayList<Consignments> uc ;
	public UnassignedConsignments(OfficeSuccess os) {
		uc = os.prev.m.getOffice(os.id).getUnassignedConsignment();
		setResizable(false);
		this.os = os;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 491, 223);
		contentPane.add(scrollPane);
		
		data = new Object [uc.size()][5];
		
		for(int i=0;i<uc.size();i++){
			data[i][0] = (Integer) uc.get(i).getId();
			data[i][1] = (String) uc.get(i).getSenderName();
			data[i][2] = (String) uc.get(i).getReceiverName();
			data[i][3] = (String) os.prev.m.getOffice(uc.get(i).getDestination()).getName();
			data[i][4] = (Integer) uc.get(i).getVolume();		
			}
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"Consignment ID", "Sender's Name", "Receiver's Name", "Destination", "Volume"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackActionPerformed();
			}
		});
		btnBack.setBounds(213, 237, 89, 23);
		contentPane.add(btnBack);
		table.getColumnModel().getColumn(0).setPreferredWidth(97);
		table.getColumnModel().getColumn(1).setPreferredWidth(93);
		table.getColumnModel().getColumn(2).setPreferredWidth(96);
		table.getColumnModel().getColumn(3).setPreferredWidth(103);
		table.getColumnModel().getColumn(4).setResizable(false);
	}
	public void btnBackActionPerformed(){
		os.setVisible(true);
		this.dispose();
	}

}
