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

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfKAdi;
	private JPasswordField tfSifre;
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
					Login frame = new Login();
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
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblkulad = new JLabel("Kullanıcı Adı");
		lblkulad.setForeground(Color.BLACK);
		lblkulad.setHorizontalAlignment(SwingConstants.CENTER);
		lblkulad.setBounds(243, 253, 117, 28);
		lblkulad.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblkulad);
		
		JLabel lblSifre = new JLabel("Şifre");
		lblSifre.setForeground(new Color(0, 0, 0));
		lblSifre.setHorizontalAlignment(SwingConstants.CENTER);
		lblSifre.setBounds(243, 289, 117, 28);
		lblSifre.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblSifre);
		
		tfKAdi = new JTextField();
		tfKAdi.setBounds(352, 254, 96, 19);
		contentPane.add(tfKAdi);
		tfKAdi.setColumns(10);
		
		tfSifre = new JPasswordField();
		tfSifre.setBounds(352, 296, 96, 19);
		contentPane.add(tfSifre);
		
		JButton btnGiris = new JButton("Giriş");
		btnGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = tfKAdi.getText();
		        String password = new String(tfSifre.getPassword());
		        
		        try (Connection connection = DriverManager.getConnection(DB,USER,PASS)) {
		            String query = "SELECT * FROM `mydb`.`users` WHERE KullanıcıAdı = ? AND Şifre = ?";
		            try (PreparedStatement statement = connection.prepareStatement(query)) {
		                statement.setString(1, username);
		                statement.setString(2, password);
		                
		                try (ResultSet resultSet = statement.executeQuery()) {
		                    if (resultSet.next()) {
		                    	JOptionPane.showMessageDialog(null, "Başarıyla giriş yaptınız!");
		                    	int roleID = resultSet.getInt("role_id");
		                    	if (roleID == 1) {
		                    	    AdminHomePage adminPage1 = new AdminHomePage(username);  
		                    	    adminPage1.setVisible(true);
		                    	} else if (roleID == 2) {
		                    	    CustomerHomePage userPage = new CustomerHomePage(username);
		                    	    userPage.setVisible(true);
		                    	} else if (roleID == 3) {
		                    	    TreasurerHomePage vezPage = new TreasurerHomePage(username);
		                    	    vezPage.setVisible(true);
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
		            JOptionPane.showMessageDialog(null, "Giriş işlemi sırasında bir hata oluştu.");
		        }
			}
		});
		btnGiris.setBounds(312, 350, 117, 28);
		contentPane.add(btnGiris);
		
		JButton btnUyeOl = new JButton("Üye Ol");
		btnUyeOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signin uyeOlmaSayfasi = new Signin(); 
		        uyeOlmaSayfasi.setVisible(true);
			}
		});
		btnUyeOl.setBounds(312, 405, 117, 28);
		contentPane.add(btnUyeOl);
		
		JLabel lbluyeol = new JLabel("");
		lbluyeol.setIcon(new ImageIcon("C:\\Users\\Hamza Polatçelik\\OneDrive - Bolu Abant İzzet Baysal Üniversitesi\\Masaüstü\\login.jpg"));
		lbluyeol.setBounds(21, -77, 962, 728);
		contentPane.add(lbluyeol);
		
		
		
		
	}
}
