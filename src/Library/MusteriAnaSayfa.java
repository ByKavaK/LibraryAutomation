package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class MusteriAnaSayfa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusteriAnaSayfa frame = new MusteriAnaSayfa("1");
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
	public MusteriAnaSayfa(String kullaniciAdi) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MusteriAnaSayfa.class.getResource("/Library/img/kütüp.png")));
		setTitle("CustomerHomePage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(188, 143, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKAdi = new JLabel("Kullanıcı Adı:");
		lblKAdi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKAdi.setBounds(10, 32, 110, 28);
		contentPane.add(lblKAdi);
		
		JLabel lblUnvan = new JLabel("Ünvan:");
		lblUnvan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUnvan.setBounds(10, 80, 110, 28);
		contentPane.add(lblUnvan);
		
		JLabel lblKAdiDol = new JLabel(kullaniciAdi);
		lblKAdiDol.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKAdiDol.setBounds(130, 32, 90, 28);
		contentPane.add(lblKAdiDol);
		
		JLabel lblUnvanDol = new JLabel("Müşteri");
		lblUnvanDol.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUnvanDol.setBounds(130, 80, 90, 28);
		contentPane.add(lblUnvanDol);
		
		JPanel kitapPanel = new JPanel();
		kitapPanel.setForeground(new Color(51, 0, 255));
		kitapPanel.setBackground(new Color(255, 239, 213));
		kitapPanel.setBounds(10, 149, 766, 108);
		contentPane.add(kitapPanel);
		
		JButton btnKitapLis = new JButton("Kitap Listesi");
		btnKitapLis.setBounds(29, 46, 120, 32);
		btnKitapLis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapListe kitapListe = new KitapListe(kullaniciAdi,2); 
				kitapListe.setVisible(true);
				dispose();
			}
		});
		kitapPanel.setLayout(null);
		btnKitapLis.setBackground(Color.ORANGE);
		btnKitapLis.setFont(new Font("Tahoma", Font.BOLD, 10));
		kitapPanel.add(btnKitapLis);
		
		JButton btnKitapIade = new JButton("Kitap İade");
		btnKitapIade.setBounds(574, 46, 120, 32);
		btnKitapIade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapIade kitapIade = new KitapIade(kullaniciAdi);
				kitapIade.setVisible(true);
				dispose();
			}
		});
		btnKitapIade.setBackground(Color.ORANGE);
		btnKitapIade.setFont(new Font("Tahoma", Font.BOLD, 10));
		kitapPanel.add(btnKitapIade);
		
		JButton btnKitapOduncAl = new JButton("Kitap Ödünç Al");
		btnKitapOduncAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapOduncAl öduncAl = new KitapOduncAl(kullaniciAdi);
				öduncAl.setVisible(true);
				dispose();
			}
		});
		btnKitapOduncAl.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKitapOduncAl.setBackground(Color.ORANGE);
		btnKitapOduncAl.setBounds(303, 46, 120, 32);
		kitapPanel.add(btnKitapOduncAl);
		
		JLabel lblKitapIs = new JLabel("KİTAP İŞLEMLERİ");
		lblKitapIs.setForeground(new Color(0, 0, 0));
		lblKitapIs.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKitapIs.setBounds(10, 137, 125, 13);
		contentPane.add(lblKitapIs);
		
		JButton btnKütüpBilgi = new JButton("Kütüphane Bilgisi");
		btnKütüpBilgi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KutuphaneBilgi kütüpBil = new KutuphaneBilgi(kullaniciAdi,2);
				kütüpBil.setVisible(true);
				dispose();
			}
		});
		btnKütüpBilgi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnKütüpBilgi.setBounds(10, 508, 200, 45);
		contentPane.add(btnKütüpBilgi);
		
		JButton btnCikis = new JButton("Çıkış");
		btnCikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCikis.setForeground(Color.BLACK);
		btnCikis.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCikis.setBounds(665, 520, 100, 30);
		contentPane.add(btnCikis);
		
		JButton btnOduncKitap = new JButton("Ödünç Alınan Kitaplar");
		btnOduncKitap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OduncAlinan odunc = new OduncAlinan(kullaniciAdi);
				odunc.setVisible(true);
				dispose();
			}
		});
		btnOduncKitap.setBackground(new Color(60, 179, 113));
		btnOduncKitap.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnOduncKitap.setBounds(93, 287, 200, 60);
		contentPane.add(btnOduncKitap);
		
		JButton btndnAlmayBekleyen = new JButton("Ödünç Almayı Bekleyen");
		btndnAlmayBekleyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OduncBekleyen bekleyen = new OduncBekleyen(kullaniciAdi);
				bekleyen.setVisible(true);
				dispose();
			}
		});
		btndnAlmayBekleyen.setFont(new Font("Tahoma", Font.BOLD, 12));
		btndnAlmayBekleyen.setBackground(new Color(60, 179, 113));
		btndnAlmayBekleyen.setBounds(424, 287, 200, 60);
		contentPane.add(btndnAlmayBekleyen);
	}

}
