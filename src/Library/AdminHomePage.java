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
import java.awt.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import java.awt.Font;

public class AdminHomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHomePage frame = new AdminHomePage();
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
	public AdminHomePage() {
		setTitle("AdminHomePage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKAdi = new JLabel("Kullanıcı Adı:");
		lblKAdi.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblKAdi.setBounds(10, 32, 90, 28);
		contentPane.add(lblKAdi);
		
		JLabel lblUnvan = new JLabel("Ünvan");
		lblUnvan.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblUnvan.setBounds(10, 80, 90, 28);
		contentPane.add(lblUnvan);
		
		JLabel lblKAdiDol = new JLabel("");
		lblKAdiDol.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblKAdiDol.setBounds(110, 32, 90, 28);
		contentPane.add(lblKAdiDol);
		
		JLabel lblUnvanDol = new JLabel("");
		lblUnvanDol.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblUnvanDol.setBounds(110, 80, 90, 28);
		contentPane.add(lblUnvanDol);
		
		JPanel kitapPanel = new JPanel();
		kitapPanel.setBounds(10, 149, 766, 108);
		kitapPanel.setBackground(new Color(102, 102, 255));
		kitapPanel.setForeground(new Color(51, 0, 255));
		contentPane.add(kitapPanel);
		kitapPanel.setLayout(null);
		
		JButton btnKitapLis = new JButton("Kitap Listesi");
		btnKitapLis.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKitapLis.setBounds(29, 46, 118, 32);
		kitapPanel.add(btnKitapLis);
		
		JButton btnKitapAra = new JButton("Kitap Ara");
		btnKitapAra.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKitapAra.setBounds(179, 46, 99, 32);
		kitapPanel.add(btnKitapAra);
		
		JButton btnKitapEkle = new JButton("Kitap Ekle");
		btnKitapEkle.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKitapEkle.setBounds(329, 46, 99, 32);
		kitapPanel.add(btnKitapEkle);
		
		JButton btnKitapDuzenle = new JButton("Kitap Düzenle");
		btnKitapDuzenle.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKitapDuzenle.setBounds(472, 46, 128, 32);
		kitapPanel.add(btnKitapDuzenle);
		
		JButton btnKitapSil = new JButton("Kitap Sil");
		btnKitapSil.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKitapSil.setBounds(629, 46, 99, 32);
		kitapPanel.add(btnKitapSil);
		
		JLabel lblKitapIs = new JLabel("KİTAP İŞLEMLERİ");
		lblKitapIs.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblKitapIs.setBounds(10, 137, 125, 13);
		contentPane.add(lblKitapIs);
		
		JPanel kullaniciPanel = new JPanel();
		kullaniciPanel.setBounds(10, 357, 766, 108);
		kullaniciPanel.setLayout(null);
		kullaniciPanel.setForeground(new Color(51, 0, 255));
		kullaniciPanel.setBackground(new Color(255, 204, 0));
		contentPane.add(kullaniciPanel);
		
		JButton btnKullaniciLis = new JButton("Kullanıcı Listesi");
		btnKullaniciLis.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKullaniciLis.setBounds(29, 46, 150, 32);
		kullaniciPanel.add(btnKullaniciLis);
		
		JButton btnKullaniciAra = new JButton("Kullanıcı Ara");
		btnKullaniciAra.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKullaniciAra.setBounds(288, 46, 150, 32);
		kullaniciPanel.add(btnKullaniciAra);
		
		JButton btnKullancSil = new JButton("Kullanıcı Sil");
		btnKullancSil.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKullancSil.setBounds(569, 46, 150, 32);
		kullaniciPanel.add(btnKullancSil);
		
		JLabel lblKullaniciIs = new JLabel("KULLANICI İŞLEMLERİ");
		lblKullaniciIs.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblKullaniciIs.setBounds(10, 344, 219, 13);
		contentPane.add(lblKullaniciIs);
		
		JButton btnKütüpBilgi = new JButton("Kütüphane Bilgisi");
		btnKütüpBilgi.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKütüpBilgi.setBounds(10, 508, 150, 45);
		contentPane.add(btnKütüpBilgi);
		
		JButton btnCikis = new JButton("Çıkış");
		btnCikis.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCikis.setBounds(676, 520, 100, 30);
		contentPane.add(btnCikis);
	}
}
