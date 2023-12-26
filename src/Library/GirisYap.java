package Library;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class GirisYap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfKAdi;
	private JPasswordField tfSifre;
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
					GirisYap frame = new GirisYap();
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
	public GirisYap() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GirisYap.class.getResource("/Library/img/kütüp.png")));
		setTitle("Giriş Yap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblkulad = new JLabel("Kullanıcı Adı:");
		lblkulad.setBackground(Color.RED);
		lblkulad.setBounds(243, 253, 117, 28);
		lblkulad.setForeground(Color.BLACK);
		lblkulad.setHorizontalAlignment(SwingConstants.CENTER);
		lblkulad.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblkulad);
		
		JLabel lblSifre = new JLabel("Şifre:");
		lblSifre.setBounds(243, 289, 117, 28);
		lblSifre.setForeground(Color.BLACK);
		lblSifre.setHorizontalAlignment(SwingConstants.CENTER);
		lblSifre.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblSifre);
		
		tfKAdi = new JTextField();
		tfKAdi.setBounds(370, 260, 145, 19);
		contentPane.add(tfKAdi);
		tfKAdi.setColumns(10);
		
		tfSifre = new JPasswordField();
		tfSifre.setBounds(370, 296, 145, 19);
		contentPane.add(tfSifre);
		
		JButton btnGiris = new JButton("Giriş");
		btnGiris.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnGiris.setBounds(312, 350, 117, 28);
		btnGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = tfKAdi.getText();
		        String password = new String(tfSifre.getPassword());
		        
		        try (Connection connection = DriverManager.getConnection(DB,USER,PASS)) {
		            String query = "SELECT * FROM `libraryautomation`.`users` WHERE KullaniciAdi = ? AND Sifre = ? ORDER BY id ASC";
		            try (PreparedStatement statement = connection.prepareStatement(query)) {
		                statement.setString(1, username);
		                statement.setString(2, password);
		                
		                try (ResultSet resultSet = statement.executeQuery()) {
		                    if (resultSet.next()) {
		                    	JOptionPane.showMessageDialog(null, "Başarıyla giriş yaptınız!");
		                    	int roleID = resultSet.getInt("role_id");
		                    	if (roleID == 1) {
		                    	    AdminAnaSayfa adminPage1 = new AdminAnaSayfa(username);  
		                    	    adminPage1.setVisible(true);
		                    	    dispose();
		                    	} else if (roleID == 2) {
		                    	    MusteriAnaSayfa userPage = new MusteriAnaSayfa(username);
		                    	    userPage.setVisible(true);
		                    	    dispose();
		                    	} else if (roleID == 3) {
		                    	    VeznedarAnaSayfa vezPage = new VeznedarAnaSayfa(username);
		                    	    vezPage.setVisible(true);
		                    	    dispose();
		                    	}
		                    	else {
		                    	    JOptionPane.showMessageDialog(null, "Bu role sahip bir sayfa bulunamadı.");
		                    	}
		                    } else {
		                    	JOptionPane.showMessageDialog(null, "Kullanıcı adı veya şifre hatalı. Lütfen kontrol edin veya kayıt olun.");
		                    }
		                }
		            }
		        } catch (SQLException ee) {
		            ee.printStackTrace();
		        }
			}
		});
		contentPane.add(btnGiris);
		
		JButton btnUyeOl = new JButton("Üye Ol");
		btnUyeOl.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUyeOl.setBounds(312, 405, 117, 28);
		btnUyeOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KayitOl uyeOlmaSayfasi = new KayitOl(); 
		        uyeOlmaSayfasi.setVisible(true);
		        dispose();
			}
		});
		contentPane.add(btnUyeOl);
		
		JLabel lbluyeol = new JLabel("");
		lbluyeol.setBounds(21, -77, 962, 728);
		lbluyeol.setIcon(new ImageIcon(KayitOl.class.getResource("/Library/img/login.jpg")));
		contentPane.add(lbluyeol);
		
		
		
		
	}
}
