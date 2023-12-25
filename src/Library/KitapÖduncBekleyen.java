package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class KitapÖduncBekleyen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapÖduncBekleyen frame = new KitapÖduncBekleyen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KitapÖduncBekleyen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 222, 173));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(2, 80, 783, 500);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(240, 255, 240));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		table.setBounds(112, 620, 50, 50);
		//contentPane.add(table);
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(60, 38, 45, 13);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(110, 36, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("ONAYLA");
		btnNewButton.setBounds(248, 35, 85, 21);
		contentPane.add(btnNewButton);
		
	}

}
