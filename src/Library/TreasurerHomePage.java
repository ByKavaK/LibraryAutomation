package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

public class TreasurerHomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
					TreasurerHomePage frame = new TreasurerHomePage("1");
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
	public TreasurerHomePage(String kullaniciAdi) {
		setTitle("TreasurerHomePage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKAdi = new JLabel("Kullanıcı Adı:");
		lblKAdi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKAdi.setBounds(10, 32, 90, 28);
		contentPane.add(lblKAdi);
		
		JLabel lblUnvan = new JLabel("Ünvan:");
		lblUnvan.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnvan.setBounds(10, 80, 90, 28);
		contentPane.add(lblUnvan);
		
		JLabel lblKAdiDol = new JLabel(kullaniciAdi);
		lblKAdiDol.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblKAdiDol.setBounds(110, 32, 90, 28);
		contentPane.add(lblKAdiDol);
		
		JLabel lblUnvanDol = new JLabel("");
		try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		    String query = "SELECT role_id FROM mydb.users WHERE KullanıcıAdı = ?";
		    try (PreparedStatement statement = connection.prepareStatement(query)) {
		        statement.setString(1, kullaniciAdi); 
		        
		        try (ResultSet resultSet = statement.executeQuery()) {
		            if (resultSet.next()) {
		                String unvan = resultSet.getString("role_id");
		                if(unvan == "1") {
		                	lblUnvanDol.setText("Admin");
		                }
		                else if(unvan == "2"){
		                	lblUnvanDol.setText("Müşteri");
		                }
		                else {
		                	lblUnvanDol.setText("Veznedar");
		                } 
		            } else {
		                lblUnvanDol.setText("Ünvan bulunamadı");
		            }
		        }
		    }
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    lblUnvanDol.setText("Bilgi alınamadı");
		}
		lblUnvanDol.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblUnvanDol.setBounds(110, 80, 90, 28);
		contentPane.add(lblUnvanDol);
		
		JPanel kitapPanel = new JPanel();
		kitapPanel.setBounds(10, 149, 766, 108);
		kitapPanel.setForeground(new Color(51, 0, 255));
		kitapPanel.setBackground(new Color(102, 102, 255));
		contentPane.add(kitapPanel);
		kitapPanel.setLayout(null);
		
		JButton btnKitapLis = new JButton("Kitap Listesi");
		btnKitapLis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapListe kitapLis = new KitapListe();
				kitapLis.setVisible(true);			}
		});
		btnKitapLis.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKitapLis.setBackground(new Color(95, 158, 160));
		btnKitapLis.setBounds(29, 46, 118, 32);
		kitapPanel.add(btnKitapLis);
		
		JButton btnKitapEkle = new JButton("Kitap Ekle");
		btnKitapEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapEkle kitapEk = new KitapEkle();
				kitapEk.setVisible(true);
			}
		});
		btnKitapEkle.setBackground(new Color(233, 150, 122));
		btnKitapEkle.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKitapEkle.setBounds(405, 46, 99, 32);
		kitapPanel.add(btnKitapEkle);
		
		JLabel lblKitapIs = new JLabel("KİTAP İŞLEMLERİ");
		lblKitapIs.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKitapIs.setBounds(10, 137, 125, 13);
		contentPane.add(lblKitapIs);
		
		JButton btnKütüpBilgi = new JButton("Kütüphane Bilgisi");
		btnKütüpBilgi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KütüphaneBilgi kütüpBil = new KütüphaneBilgi();
				kütüpBil.setVisible(true);
			}
		});
		btnKütüpBilgi.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKütüpBilgi.setBounds(10, 508, 150, 45);
		contentPane.add(btnKütüpBilgi);
		
		JButton btnCikis = new JButton("Çıkış");
		btnCikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCikis.setForeground(new Color(255, 0, 0));
		btnCikis.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCikis.setBounds(665, 520, 100, 30);
		contentPane.add(btnCikis);
		
		JButton btnKitapBekleyen = new JButton("Kitap Ödünç Bekleyen");
		btnKitapBekleyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapÖduncBekleyen bekle = new KitapÖduncBekleyen();
				bekle.setVisible(true);
			}
		});
		btnKitapBekleyen.setBackground(new Color(250, 235, 215));
		btnKitapBekleyen.setForeground(new Color(34, 139, 34));
		btnKitapBekleyen.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnKitapBekleyen.setBounds(93, 287, 200, 60);
		contentPane.add(btnKitapBekleyen);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(211, 211, 211));
		panel.setBounds(0, 10, 776, 583);
		contentPane.add(panel);
	}
}
