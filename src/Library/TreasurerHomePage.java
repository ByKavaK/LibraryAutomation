package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class TreasurerHomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TreasurerHomePage frame = new TreasurerHomePage();
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
	public TreasurerHomePage() {
		setTitle("TreasurerHomePage");
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
		kitapPanel.setForeground(new Color(51, 0, 255));
		kitapPanel.setBackground(new Color(102, 102, 255));
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
		
		JButton btnKitapEkle = new JButton("Kitap Ekle");
		btnKitapEkle.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKitapEkle.setBounds(405, 46, 99, 32);
		kitapPanel.add(btnKitapEkle);
		
		JButton btnKitapDuzenle = new JButton("Kitap Düzenle");
		btnKitapDuzenle.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKitapDuzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnKitapDuzenle.setBounds(574, 46, 128, 32);
		kitapPanel.add(btnKitapDuzenle);
		
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
		
		JButton btnKitapBekleyen = new JButton("Kitap Ödünç Bekleyen");
		btnKitapBekleyen.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKitapBekleyen.setBounds(93, 287, 200, 60);
		contentPane.add(btnKitapBekleyen);
		
		JButton btnKitapVerilen = new JButton("Kitap Ödünç Verilen");
		btnKitapVerilen.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnKitapVerilen.setBounds(459, 287, 200, 60);
		contentPane.add(btnKitapVerilen);
	}

}
