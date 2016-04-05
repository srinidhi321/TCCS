package TCCS;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class IdleTrucks extends JFrame {

	private JPanel contentPane;
	OfficeSuccess os;
	private JTable table;
	private Object[][] data;
	private ArrayList<Truck> it ; 
	public IdleTrucks(OfficeSuccess os) {
		it = os.prev.m.getOffice(os.id).getIdleTrucks();
		setResizable(false);
		this.os = os;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackActionPerformed();
			}
		});
		btnBack.setBounds(177, 239, 89, 23);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 424, 217);
		contentPane.add(scrollPane);
		
		data = new Object[it.size()][2];
		for(int i=0;i<it.size();i++){
			data[i][0] = (Integer) it.get(i).getId();
			data[i][1] = (LocalDateTime) it.get(i).getIdleStartTime();
			System.out.println(it.get(i).getIdleStartTime());
		}
	
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"Truck ID", "Arrival Time"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(141);
	}
    public void btnBackActionPerformed(){
    	os.setVisible(true);
    	this.dispose();
    }
}
