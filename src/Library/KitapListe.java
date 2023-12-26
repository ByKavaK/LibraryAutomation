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
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

public class KitapListe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable kitapTable;
	private JLabel lblKitapAdi;
	private JTextField tfkitapAd;
	private JButton btnKitapAra;
	static final String DB="jdbc:mysql://127.0.0.1:3306/libraryautomation";
	static final String USER="root";
	static final String PASS="13577";
	private JButton btnAnasayfa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapListe frame = new KitapListe("",1);
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
	public KitapListe(String kullaniciAdi,int role) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KitapListe.class.getResource("/Library/img/kütüp.png")));
		setTitle("Kitap Liste");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 0));
		contentPane.setForeground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 77, 786, 436);
		contentPane.add(scrollPane);
		
		kitapTable = new JTable();
		try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		    String query = "SELECT books.id, books.BookName, books.Yazar, category.Name FROM libraryautomation.books INNER JOIN libraryautomation.category ON books.category_id = category.id ORDER BY id ASC";
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
		lblKitapAdi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKitapAdi.setBounds(25, 27, 105, 27);
		contentPane.add(lblKitapAdi);
		
		tfkitapAd = new JTextField();
		tfkitapAd.setBounds(140, 27, 139, 25);
		contentPane.add(tfkitapAd);
		tfkitapAd.setColumns(10);
		
		btnKitapAra = new JButton("Ara");
		btnKitapAra.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnKitapAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kitapAdi = tfkitapAd.getText();

		        try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		            String query = "SELECT books.id, books.BookName, books.Yazar, category.Name FROM libraryautomation.books INNER JOIN libraryautomation.category ON books.category_id = category.id WHERE books.BookName LIKE ?";
		            
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
		btnKitapAra.setBounds(308, 29, 120, 27);
		contentPane.add(btnKitapAra);
		
		btnAnasayfa = new JButton("AnaSayfa");
		btnAnasayfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(role == 1) {
					AdminAnaSayfa admin = new AdminAnaSayfa(kullaniciAdi);
					admin.setVisible(true);
	        		dispose();
				}
				else if(role == 2) {
					MusteriAnaSayfa müş = new MusteriAnaSayfa(kullaniciAdi);
					müş.setVisible(true);
	        		dispose();
				}
				else {
					VeznedarAnaSayfa vezn = new VeznedarAnaSayfa(kullaniciAdi);
					vezn.setVisible(true);
	        		dispose();
				}
			}
		});
		btnAnasayfa.setForeground(Color.BLACK);
		btnAnasayfa.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAnasayfa.setBounds(626, 523, 150, 30);
		contentPane.add(btnAnasayfa);
		
	}

}
