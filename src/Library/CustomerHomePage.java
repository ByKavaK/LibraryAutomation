package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;

public class CustomerHomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerHomePage frame = new CustomerHomePage();
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
	public CustomerHomePage() {
		setTitle("CustomerHomePage");
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
		kitapPanel.setForeground(new Color(51, 0, 255));
		kitapPanel.setBackground(new Color(102, 102, 255));
		kitapPanel.setBounds(10, 149, 766, 108);
		contentPane.add(kitapPanel);
		kitapPanel.setLayout(null);
		
		JButton btnKitapLis = new JButton("Kitap Listesi");
		btnKitapLis.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKitapLis.setBounds(29, 46, 118, 32);
		kitapPanel.add(btnKitapLis);
		
		JButton btnKitapAra = new JButton("Kitap Ara");
		btnKitapAra.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKitapAra.setBounds(223, 46, 99, 32);
		kitapPanel.add(btnKitapAra);
		
		JButton btnKitapOduncAl = new JButton("Kitap Ödünç Al");
		btnKitapOduncAl.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKitapOduncAl.setBounds(386, 46, 118, 32);
		kitapPanel.add(btnKitapOduncAl);
		
		JButton btnKitapIade = new JButton("Kitap İade");
		btnKitapIade.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKitapIade.setBounds(574, 46, 128, 32);
		kitapPanel.add(btnKitapIade);
		
		JLabel lblKitapIs = new JLabel("KİTAP İŞLEMLERİ");
		lblKitapIs.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblKitapIs.setBounds(10, 137, 125, 13);
		contentPane.add(lblKitapIs);
		
		JButton btnKütüpBilgi = new JButton("Kütüphane Bilgisi");
		btnKütüpBilgi.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKütüpBilgi.setBounds(10, 508, 150, 45);
		contentPane.add(btnKütüpBilgi);
		
		JButton btnCikis = new JButton("Çıkış");
		btnCikis.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCikis.setBounds(676, 520, 100, 30);
		contentPane.add(btnCikis);
		
		JButton btnOduncKitap = new JButton("Ödünç Alınan Kitaplar");
		btnOduncKitap.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnOduncKitap.setBounds(93, 287, 200, 60);
		contentPane.add(btnOduncKitap);
		
		JButton btnBekleyenKitap = new JButton("Ödünç Almayı Bekleyen");
		btnBekleyenKitap.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnBekleyenKitap.setBounds(459, 287, 200, 60);
		contentPane.add(btnBekleyenKitap);
	}

}
