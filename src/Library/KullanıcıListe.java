package Library;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KullanıcıListe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable kulTable;
	private JLabel lblKulAd;
	private JTextField tfKulAd;
	static final String DB="jdbc:mysql://127.0.0.1:3306/mydb";
	static final String USER="root";
	static final String PASS="13577";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KullanıcıListe frame = new KullanıcıListe();
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
	public KullanıcıListe() {
		setTitle("Kullanıcı Liste");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 70, 808, 584);
		contentPane.add(scrollPane);
		
		kulTable = new JTable();
		try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		    String query = "SELECT `mydb`.`users`.id,`mydb`.`users`.KullanıcıAdı,`mydb`.`role`.RoleName FROM `mydb`.`users` JOIN `mydb`.`role` ON `users`.`role_id` =  `mydb`.`role`.id";
		    try (PreparedStatement statement = connection.prepareStatement(query)) {
		        try (ResultSet resultSet = statement.executeQuery()) {
		            ResultSetMetaData metaData = resultSet.getMetaData();
		            int columnCount = metaData.getColumnCount();
		            String[] columnNames = new String[columnCount];
		            for (int i = 1; i <= columnCount; i++) {
		                columnNames[i - 1] = metaData.getColumnName(i);
		            }
		            ArrayList<Object[]> data = new ArrayList<>();
		            while (resultSet.next()) {
		                Object[] row = new Object[columnCount];
		                for (int i = 1; i <= columnCount; i++) {
		                    row[i - 1] = resultSet.getObject(i);
		                }
		                data.add(row);
		            }
		            Object[][] dataArray = data.toArray(new Object[0][0]);
		            DefaultTableModel model = new DefaultTableModel(dataArray, columnNames);
		            kulTable.setModel(model);
		        }
		    }
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
		kulTable.setBounds(241, 465, 157, 62);
		//contentPane.add(table);
		scrollPane.setViewportView(kulTable);
		
		lblKulAd = new JLabel("Kullanıcı Adı");
		lblKulAd.setBounds(44, 28, 107, 19);
		contentPane.add(lblKulAd);
		
		tfKulAd = new JTextField();
		tfKulAd.setBounds(134, 28, 96, 19);
		contentPane.add(tfKulAd);
		tfKulAd.setColumns(10);
		
		JButton btnKulAra = new JButton("Ara");
		btnKulAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kulAd = tfKulAd.getText();

		        try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		            String query = "SELECT users.id, users.KullanıcıAdı, mydb.role.RoleName FROM mydb.users JOIN mydb.role ON users.role_id = mydb.role.id WHERE users.KullanıcıAdı LIKE ?";
		            
		            try (PreparedStatement statement = connection.prepareStatement(query)) {
		                statement.setString(1, "%" + kulAd + "%");

		                try (ResultSet resultSet = statement.executeQuery()) {
		                    ResultSetMetaData metaData = resultSet.getMetaData();
		                    int columnCount = metaData.getColumnCount();
		                    String[] columnNames = new String[columnCount];
		                    for (int i = 1; i <= columnCount; i++) {
		                        columnNames[i - 1] = metaData.getColumnName(i);
		                    }
		                    ArrayList<Object[]> data = new ArrayList<>();
		                    while (resultSet.next()) {
		                        Object[] row = new Object[columnCount];
		                        for (int i = 1; i <= columnCount; i++) {
		                            row[i - 1] = resultSet.getObject(i);
		                        }
		                        data.add(row);
		                    }
		                    Object[][] dataArray = data.toArray(new Object[0][0]);
		                    DefaultTableModel model = new DefaultTableModel(dataArray, columnNames);
		                    kulTable.setModel(model);
		                }
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btnKulAra.setBounds(271, 27, 85, 21);
		contentPane.add(btnKulAra);
	}
}
