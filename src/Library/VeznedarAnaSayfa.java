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
import java.awt.Toolkit;

public class VeznedarAnaSayfa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VeznedarAnaSayfa frame = new VeznedarAnaSayfa("1");
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
	public VeznedarAnaSayfa(String kullaniciAdi) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VeznedarAnaSayfa.class.getResource("/Library/img/kütüp.png")));
		setTitle("Veznedar AnaSayfa");
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
		lblKAdiDol.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKAdiDol.setBounds(110, 32, 90, 28);
		contentPane.add(lblKAdiDol);
		
		JLabel lblUnvanDol = new JLabel("Veznedar");
		lblUnvanDol.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUnvanDol.setBounds(110, 80, 90, 28);
		contentPane.add(lblUnvanDol);
		
		JPanel kitapPanel = new JPanel();
		kitapPanel.setBounds(10, 149, 766, 108);
		kitapPanel.setForeground(new Color(51, 0, 255));
		kitapPanel.setBackground(new Color(153, 204, 255));
		contentPane.add(kitapPanel);
		kitapPanel.setLayout(null);
		
		JButton btnKitapLis = new JButton("Kitap Listesi");
		btnKitapLis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapListe kitapLis = new KitapListe(kullaniciAdi,3);
				kitapLis.setVisible(true);	
				dispose();}
		});
		btnKitapLis.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnKitapLis.setBackground(Color.ORANGE);
		btnKitapLis.setBounds(10, 46, 159, 32);
		kitapPanel.add(btnKitapLis);
		
		JButton btnKitapEkle = new JButton("Kitap Ekle");
		btnKitapEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapEkle kitapEk = new KitapEkle(kullaniciAdi,3);
				kitapEk.setVisible(true);
				dispose();
			}
		});
		btnKitapEkle.setBackground(Color.ORANGE);
		btnKitapEkle.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnKitapEkle.setBounds(597, 46, 159, 32);
		kitapPanel.add(btnKitapEkle);
		
		JLabel lblKitapIs = new JLabel("KİTAP İŞLEMLERİ");
		lblKitapIs.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKitapIs.setBounds(10, 137, 125, 13);
		contentPane.add(lblKitapIs);
		
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(211, 211, 211));
		panel.setBounds(0, 10, 776, 583);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnKitapBekleyen = new JButton("Kitap Ödünç Bekleyen");
		btnKitapBekleyen.setBounds(286, 277, 223, 60);
		panel.add(btnKitapBekleyen);
		btnKitapBekleyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapOduncBekleyen bekle = new KitapOduncBekleyen(kullaniciAdi);
				bekle.setVisible(true);
				dispose();
			}
		});
		btnKitapBekleyen.setBackground(Color.WHITE);
		btnKitapBekleyen.setForeground(Color.BLACK);
		btnKitapBekleyen.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnKütüpBilgi = new JButton("Kütüphane Bilgisi");
		btnKütüpBilgi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KutuphaneBilgi kutupBil = new KutuphaneBilgi(kullaniciAdi,3);
				kutupBil.setVisible(true);
				dispose();
			}
		});
		btnKütüpBilgi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnKütüpBilgi.setBounds(10, 495, 175, 45);
		panel.add(btnKütüpBilgi);
	}
}
