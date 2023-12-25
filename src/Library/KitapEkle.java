package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class KitapEkle extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfKitapAdi;
	private JTextField tfYazar;
	private JLabel lblKitapAdi;
	private JLabel lblKitapTür;
	private JLabel lblYazar;
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
					KitapEkle frame = new KitapEkle();
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
	public KitapEkle() {
		setTitle("Kitap Ekle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 232, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfKitapAdi = new JTextField();
		tfKitapAdi.setColumns(10);
		tfKitapAdi.setBounds(312, 170, 96, 19);
		contentPane.add(tfKitapAdi);
		
		tfYazar = new JTextField();
		tfYazar.setColumns(10);
		tfYazar.setBounds(312, 230, 96, 19);
		contentPane.add(tfYazar);
		
		lblKitapAdi = new JLabel("Kitap Adı");
		lblKitapAdi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKitapAdi.setBounds(197, 172, 73, 13);
		contentPane.add(lblKitapAdi);
		
		lblKitapTür = new JLabel("Kitap Türü");
		lblKitapTür.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKitapTür.setBounds(197, 195, 73, 13);
		contentPane.add(lblKitapTür);
		
		lblYazar = new JLabel("Yazar");
		lblYazar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblYazar.setBounds(197, 218, 45, 13);
		contentPane.add(lblYazar);
		
		
		
		JComboBox cbKitapTür = new JComboBox();
		cbKitapTür.addItem("Kategorisini Seçiniz...");
		cbKitapTür.setBounds(312, 199, 96, 21);
		contentPane.add(cbKitapTür);
		try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
            String query = "SELECT Name FROM mydb.category";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String categoryName = resultSet.getString("Name");
                        cbKitapTür.addItem(categoryName);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		JButton btnEkle = new JButton("EKLE");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String kitapAdi = tfKitapAdi.getText();
		        String yazar = tfYazar.getText();
		        String kitapTuru = (String) cbKitapTür.getSelectedItem();

		        HashMap<String, Integer> kategoriIDMap = new HashMap<>();
		     try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		         String query = "SELECT id, Name FROM mydb.category";
		         try (PreparedStatement statement = connection.prepareStatement(query)) {
		             try (ResultSet resultSet = statement.executeQuery()) {
		                 while (resultSet.next()) {
		                     int categoryID = resultSet.getInt("id");
		                     String categoryName = resultSet.getString("Name");
		                     kategoriIDMap.put(categoryName, categoryID); 
		                 }
		             }
		         }
		     } catch (SQLException ex) {
		         ex.printStackTrace();
		     }
		     	int kategoriID = kategoriIDMap.getOrDefault(kitapTuru, -1);
		     	long suankiZaman = System.currentTimeMillis();
		        java.sql.Timestamp eklenmeTarihi = new java.sql.Timestamp(suankiZaman);
		        java.sql.Timestamp duzenlenmeTarihi = new java.sql.Timestamp(suankiZaman);
		        
		        try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		            String query = "INSERT INTO mydb.books (BookName, Yazar, EklemeTarihi, DüzenlemeTarihi, category_id) VALUES (?, ?, ?, ?, ?)";
		            try (PreparedStatement statement = connection.prepareStatement(query)) {
		                statement.setString(1, kitapAdi);
		                statement.setString(2, yazar);
		                statement.setTimestamp(3, eklenmeTarihi); 
		                statement.setTimestamp(4, duzenlenmeTarihi);
		                statement.setInt(5, kategoriID);
		                
		                int affectedRows = statement.executeUpdate();
		                if (affectedRows > 0) {
		                	JOptionPane.showMessageDialog(null, "Kitap başarıyla eklendi!");
		                } else {
		                	JOptionPane.showMessageDialog(null, "Kitap eklenirken hata oluştu!");
		                }
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btnEkle.setBounds(253, 259, 85, 21);
		contentPane.add(btnEkle);
	}
	
	
}

