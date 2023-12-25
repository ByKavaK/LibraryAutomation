package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JScrollBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Canvas;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHomePage extends JFrame {

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
					AdminHomePage frame = new AdminHomePage("1");
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
	public AdminHomePage(String kullaniciAdi) {
		setTitle("AdminHomePage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 222, 173));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKAdi = new JLabel("Kullanıcı Adı:");
		lblKAdi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKAdi.setBounds(10, 32, 125, 28);
		contentPane.add(lblKAdi);
		
		JLabel lblUnvan = new JLabel("Ünvan");
		lblUnvan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUnvan.setBounds(10, 80, 109, 28);
		contentPane.add(lblUnvan);
		
		JLabel lblKAdiDol = new JLabel(kullaniciAdi);
		lblKAdiDol.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblKAdiDol.setBounds(120, 32, 90, 28);
		contentPane.add(lblKAdiDol);
	
		JLabel lblUnvanDol = new JLabel("ADMİN");
		lblUnvanDol.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblUnvanDol.setBounds(110, 80, 90, 28);
		contentPane.add(lblUnvanDol);
		
		JPanel kitapPanel = new JPanel();
		kitapPanel.setBounds(10, 149, 766, 108);
		kitapPanel.setBackground(new Color(102, 102, 255));
		kitapPanel.setForeground(new Color(51, 0, 255));
		contentPane.add(kitapPanel);
		
		JButton btnKitapLis = new JButton("Kitap Listesi");
		btnKitapLis.setBounds(29, 46, 118, 32);
		btnKitapLis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapListe kitapListe = new KitapListe(); 
				kitapListe.setVisible(true);
			}
		});
		kitapPanel.setLayout(null);
		btnKitapLis.setForeground(SystemColor.desktop);
		btnKitapLis.setFont(new Font("Tahoma", Font.BOLD, 10));
		kitapPanel.add(btnKitapLis);
		
		JButton btnKitapEkle = new JButton("Kitap Ekle");
		btnKitapEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapEkle kitapEkle = new KitapEkle();
				kitapEkle.setVisible(true);
			}
		});
		btnKitapEkle.setBounds(329, 46, 99, 32);
		btnKitapEkle.setFont(new Font("Tahoma", Font.BOLD, 10));
		kitapPanel.add(btnKitapEkle);
		
		JButton btnKitapSil = new JButton("Kitap Sil");
		btnKitapSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapSil kitapSil = new KitapSil();
				kitapSil.setVisible(true);;
			}
		});
		btnKitapSil.setBounds(629, 46, 99, 32);
		btnKitapSil.setFont(new Font("Tahoma", Font.BOLD, 10));
		kitapPanel.add(btnKitapSil);
		
		JLabel lblKitapIs = new JLabel("KİTAP İŞLEMLERİ");
		lblKitapIs.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKitapIs.setForeground(new Color(0, 0, 0));
		lblKitapIs.setBounds(10, 129, 125, 21);
		contentPane.add(lblKitapIs);
		
		JPanel kullaniciPanel = new JPanel();
		kullaniciPanel.setBounds(10, 357, 766, 108);
		kullaniciPanel.setLayout(null);
		kullaniciPanel.setForeground(new Color(51, 0, 255));
		kullaniciPanel.setBackground(new Color(255, 204, 0));
		contentPane.add(kullaniciPanel);
		
		JButton btnKullaniciLis = new JButton("Kullanıcı Listesi");
		btnKullaniciLis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KullanıcıListe kulList = new KullanıcıListe();
				kulList.setVisible(true);
			}
		});
		btnKullaniciLis.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKullaniciLis.setBounds(29, 46, 150, 32);
		kullaniciPanel.add(btnKullaniciLis);
		
		JButton btnKullancSil = new JButton("Kullanıcı Sil");
		btnKullancSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KullanıcıSil kulSil = new KullanıcıSil();
				kulSil.setVisible(true);
			}
		});
		btnKullancSil.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKullancSil.setBounds(569, 46, 150, 32);
		kullaniciPanel.add(btnKullancSil);
		
		JLabel lblKullaniciIs = new JLabel("KULLANICI İŞLEMLERİ");
		lblKullaniciIs.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblKullaniciIs.setBounds(10, 344, 219, 13);
		contentPane.add(lblKullaniciIs);
		
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
		btnCikis.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCikis.setBounds(676, 520, 100, 30);
		contentPane.add(btnCikis);
	}
}
