package TCCS;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class LoadingTrucks extends JFrame {

	private JPanel contentPane;
	private JTable table;
    private Object data[][];
    private ArrayList<Loading> tr;
    OfficeSuccess os;
    private JScrollPane scrollPane;
	public LoadingTrucks(OfficeSuccess os) {
		setResizable(false);
		this.os = os;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tr = os.prev.m.getOffice(os.id).getLoadingTrucks();
		data = new Object[tr.size()][3];
		for(int i=0;i<tr.size();i++){
				data[i][0] = (Integer) tr.get(i).getTruckId();
				data[i][1] = (String) os.prev.m.getOffice(tr.get(i).getDestination()).getName();
				data[i][2] = (Integer) os.prev.m.getTruck(tr.get(i).getTruckId()).getVolumeFilled();
		}
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 226);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"Truck ID", "Destination ", "Volume Filled"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(149);
		table.getColumnModel().getColumn(2).setResizable(false);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButtonActionPerformed();
			}
		});
		btnNewButton.setBounds(165, 248, 89, 23);
		contentPane.add(btnNewButton);
	}
	public void btnNewButtonActionPerformed(){
		os.setVisible(true);
		this.dispose();
	}
}
