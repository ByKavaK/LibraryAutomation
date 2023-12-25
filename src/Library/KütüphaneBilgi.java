package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.JDesktopPane;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

public class KütüphaneBilgi extends JFrame {

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
					KütüphaneBilgi frame = new KütüphaneBilgi();
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
	public KütüphaneBilgi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 250, 210));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setForeground(Color.WHITE);
		panel.setBounds(10, 10, 576, 96);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblkitapsayi = new JLabel("KİTAP SAYISI");
		lblkitapsayi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblkitapsayi.setBounds(71, 56, 134, 30);
		panel.add(lblkitapsayi);
		
		JLabel lbluyesayisi = new JLabel("ÜYE SAYISI");
		lbluyesayisi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbluyesayisi.setBounds(395, 56, 134, 30);
		panel.add(lbluyesayisi);
		
		JLabel lblKitapSayi = new JLabel("");
		try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		    
		    String kitapSorgu = "SELECT COUNT(*) AS kitap_sayisi FROM mydb.books";
		    try (PreparedStatement kitapStatement = connection.prepareStatement(kitapSorgu)) {
		        try (ResultSet kitapResultSet = kitapStatement.executeQuery()) {
		            if (kitapResultSet.next()) {
		                int kitapSayisi = kitapResultSet.getInt("kitap_sayisi");
		                lblKitapSayi.setText(String.valueOf(kitapSayisi));
		            }
		        }
		    }}
		    catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		lblKitapSayi.setBounds(102, 27, 58, 19);
		panel.add(lblKitapSayi);
		
		JLabel lbluyesayi = new JLabel("");
		try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		String uyeSorgu = "SELECT COUNT(*) AS uye_sayisi FROM mydb.users";
	    try (PreparedStatement uyeStatement = connection.prepareStatement(uyeSorgu)) {
	        try (ResultSet uyeResultSet = uyeStatement.executeQuery()) {
	            if (uyeResultSet.next()) {
	                int uyeSayisi = uyeResultSet.getInt("uye_sayisi");
	                lbluyesayi.setText(String.valueOf(uyeSayisi));
	            }
	        }
	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
		lbluyesayi.setBounds(428, 27, 58, 19);
		panel.add(lbluyesayi);
		
		JLabel lblKutupHak = new JLabel("              KÜTÜPHANE HAKKINDA");
		lblKutupHak.setForeground(new Color(139, 69, 19));
		lblKutupHak.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblKutupHak.setBounds(33, 116, 524, 53);
		contentPane.add(lblKutupHak);
		
		JTextPane txtpnAciklama = new JTextPane();
		txtpnAciklama.setBackground(new Color(211, 211, 211));
		txtpnAciklama.setForeground(new Color(165, 42, 42));
		txtpnAciklama.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnAciklama.setText("                                                                     FikirAtlası Kütüphanesi Üniversitemizin eğitim-öğretim ve araştırma faaliyetlerini desteklemek amacıyla 2023 yılında kurulmuştur. Bünyesinde birçok  kitap barındıran kütüphanemizden, aldığınız kitabı zamanında teslim etmek şartıyla istediğiniz kitabı ödünç alabilirsiniz. Keyifli ve bilgi dolu okumalar...");
		txtpnAciklama.setBounds(33, 165, 524, 207);
		contentPane.add(txtpnAciklama);
		
		JLabel lblIletisim = new JLabel("İLETİŞİM");
		lblIletisim.setForeground(new Color(255, 0, 0));
		lblIletisim.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIletisim.setBounds(33, 382, 103, 39);
		contentPane.add(lblIletisim);
		
		JLabel lblMailLogo = new JLabel("");
		lblMailLogo.setIcon(new ImageIcon("C:\\Users\\Hamza Polatçelik\\OneDrive - Bolu Abant İzzet Baysal Üniversitesi\\Masaüstü\\mail icon.png"));
		lblMailLogo.setBounds(33, 419, 50, 50);
		contentPane.add(lblMailLogo);
		
		JLabel lblBırMail = new JLabel("polatcelikhamza94@gmail.com");
		lblBırMail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBırMail.setBounds(93, 403, 246, 53);
		contentPane.add(lblBırMail);
		
		JLabel lblIkiMail = new JLabel("ysnkvk2002@gmail.com");
		lblIkiMail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIkiMail.setBounds(93, 431, 265, 53);
		contentPane.add(lblIkiMail);
	}
}
