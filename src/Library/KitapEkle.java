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
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class KitapEkle extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfKitapAdi;
	private JTextField tfYazar;
	private JLabel lblKitapAdi;
	private JLabel lblKitapTür;
	private JLabel lblYazar;
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
					KitapEkle frame = new KitapEkle("",1);
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
	public KitapEkle(String kullaniciAdi,int role) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KitapEkle.class.getResource("/Library/img/kütüp.png")));
		setTitle("Kitap Ekle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 232, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfKitapAdi = new JTextField();
		tfKitapAdi.setColumns(10);
		tfKitapAdi.setBounds(420, 212, 160, 19);
		contentPane.add(tfKitapAdi);
		
		tfYazar = new JTextField();
		tfYazar.setColumns(10);
		tfYazar.setBounds(420, 270, 160, 19);
		contentPane.add(tfYazar);
		
		lblKitapAdi = new JLabel("Kitap Adı:");
		lblKitapAdi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKitapAdi.setBounds(286, 218, 73, 13);
		contentPane.add(lblKitapAdi);
		
		lblKitapTür = new JLabel("Kitap Türü:");
		lblKitapTür.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKitapTür.setBounds(286, 246, 73, 13);
		contentPane.add(lblKitapTür);
		
		lblYazar = new JLabel("Yazar:");
		lblYazar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblYazar.setBounds(286, 269, 40, 19);
		contentPane.add(lblYazar);
		
		
		
		JComboBox cbKitapTür = new JComboBox();
		cbKitapTür.setFont(new Font("Tahoma", Font.BOLD, 15));
		cbKitapTür.setModel(new DefaultComboBoxModel(new String[] {"Türünü Seçiniz..."}));
		cbKitapTür.setBounds(420, 239, 160, 21);
		contentPane.add(cbKitapTür);
		try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
            String query = "SELECT Name FROM libraryautomation.category";
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
		btnEkle.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String kitapAdi = tfKitapAdi.getText();
		        String yazar = tfYazar.getText();
		        String kitapTuru = (String) cbKitapTür.getSelectedItem();

		        HashMap<String, Integer> kategoriIDMap = new HashMap<>();
		     try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		         String query = "SELECT id, Name FROM libraryautomation.category";
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
		        
		        try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		            String query = "INSERT INTO libraryautomation.books (BookName, Yazar, category_id) VALUES (?, ?, ?)";
		            try (PreparedStatement statement = connection.prepareStatement(query)) {
		                statement.setString(1, kitapAdi);
		                statement.setString(2, yazar);
		                statement.setInt(3, kategoriID);
		                
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
		btnEkle.setBounds(332, 299, 150, 42);
		contentPane.add(btnEkle);
		
		btnAnasayfa = new JButton("AnaSayfa");
		btnAnasayfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(role == 1) {
					AdminAnaSayfa admin = new AdminAnaSayfa(kullaniciAdi);
					admin.setVisible(true);
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

