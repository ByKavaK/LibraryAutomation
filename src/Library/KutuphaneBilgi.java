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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class KutuphaneBilgi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static final String DB="jdbc:mysql://127.0.0.1:3306/libraryautomation";
	static final String USER="root";
	static final String PASS="13577";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KutuphaneBilgi frame = new KutuphaneBilgi("",1);
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
	public KutuphaneBilgi(String kullaniciAdi, int role) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KutuphaneBilgi.class.getResource("/Library/img/kütüp.png")));
		setTitle("Kütüphane Bilgi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
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
		lblkitapsayi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblkitapsayi.setBounds(71, 56, 152, 30);
		panel.add(lblkitapsayi);
		
		JLabel lbluyesayisi = new JLabel("ÜYE SAYISI");
		lbluyesayisi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbluyesayisi.setBounds(395, 56, 134, 30);
		panel.add(lbluyesayisi);
		
		JLabel lblKitapSayi = new JLabel("");
		lblKitapSayi.setFont(new Font("Tahoma", Font.BOLD, 20));
		try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		    
		    String kitapSorgu = "SELECT COUNT(*) AS kitap_sayisi FROM libraryautomation.books";
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
		lblKitapSayi.setBounds(107, 27, 95, 19);
		panel.add(lblKitapSayi);
		
		JLabel lbluyesayi = new JLabel("");
		lbluyesayi.setFont(new Font("Tahoma", Font.BOLD, 20));
		try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		String uyeSorgu = "SELECT COUNT(*) AS uye_sayisi FROM libraryautomation.users";
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
		lblMailLogo.setIcon(new ImageIcon(KutuphaneBilgi.class.getResource("/Library/img/mail.png")));
		lblMailLogo.setBounds(33, 419, 50, 56);
		contentPane.add(lblMailLogo);
		
		JLabel lblBırMail = new JLabel("polatcelikhamza94@gmail.com");
		lblBırMail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBırMail.setBounds(93, 419, 329, 30);
		contentPane.add(lblBırMail);
		
		JLabel lblIkiMail = new JLabel("ysnkvk2002@gmail.com");
		lblIkiMail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIkiMail.setBounds(93, 445, 314, 30);
		contentPane.add(lblIkiMail);
		
		JButton btnAnasayfa = new JButton("AnaSayfa");
		btnAnasayfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(role == 1) {
					AdminAnaSayfa admin = new AdminAnaSayfa(kullaniciAdi);
					admin.setVisible(true);
	        		dispose();
				}
				else if(role == 2) {
					MusteriAnaSayfa müş = new MusteriAnaSayfa(kullaniciAdi);
					müş.setVisible(true);
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
