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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KitapListe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable kitapTable;
	private JLabel lblKitapAdi;
	private JTextField tfkitapAd;
	private JButton btnKitapAra;
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
					KitapListe frame = new KitapListe();
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
	public KitapListe() {
		setTitle("Kitap Liste");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 77, 785, 533);
		contentPane.add(scrollPane);
		
		kitapTable = new JTable();
		try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		    String query = "SELECT books.id, books.BookName, books.Yazar, books.EklemeTarihi, books.DüzenlemeTarihi, category.Name FROM mydb.books INNER JOIN mydb.category ON books.category_id = category.id";
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
		            kitapTable.setModel(model);
		        }
		    }
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
		kitapTable.setBounds(161, 426, 169, 103);
		//contentPane.add(table);
		scrollPane.setViewportView(kitapTable);
		
		lblKitapAdi = new JLabel("Kitap Adı");
		lblKitapAdi.setBounds(25, 27, 105, 27);
		contentPane.add(lblKitapAdi);
		
		tfkitapAd = new JTextField();
		tfkitapAd.setBounds(101, 31, 88, 19);
		contentPane.add(tfkitapAd);
		tfkitapAd.setColumns(10);
		
		btnKitapAra = new JButton("Ara");
		btnKitapAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kitapAdi = tfkitapAd.getText();

		        try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		            String query = "SELECT books.id, books.BookName, books.Yazar, books.EklemeTarihi, books.DüzenlemeTarihi, category.Name " +
		                           "FROM mydb.books " +
		                           "INNER JOIN mydb.category ON books.category_id = category.id " +
		                           "WHERE books.BookName LIKE ?";
		            
		            try (PreparedStatement statement = connection.prepareStatement(query)) {
		                statement.setString(1, "%" + kitapAdi + "%");

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
		                    kitapTable.setModel(model);
		                }
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btnKitapAra.setBounds(229, 30, 85, 21);
		contentPane.add(btnKitapAra);
		
	}

}
