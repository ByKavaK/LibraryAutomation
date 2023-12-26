package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class KayitOl extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfkulad;
	private JLabel lblsifre;
	private JTextField tfsifre;
	private JLabel lblemail;
	private JTextField tfEmail;
	private JTextField tftel;
	private JLabel lbltel;
	private JButton btnKayitOl;
	private JTextField tfsoyad;
	private JTextField tfad;
	private JLabel lblNewLabel_4;
	private JLabel lblkulad;
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
					KayitOl frame = new KayitOl();
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
	public KayitOl() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KayitOl.class.getResource("/Library/img/kütüp.png")));
		setTitle("Üye Ol");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setName("Signin");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblad = new JLabel("Ad:");
		lblad.setBounds(226, 104, 68, 28);
		lblad.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblad);
		lblkulad = new JLabel("Kullanıcı Adı:");
		lblkulad.setBounds(226, 190, 125, 28);
		lblkulad.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblkulad);
		
		tfkulad = new JTextField();
		tfkulad.setBounds(388, 197, 131, 19);
		contentPane.add(tfkulad);
		tfkulad.setColumns(10);

		
		lblsifre = new JLabel("Şifre:");
		lblsifre.setBounds(226, 233, 68, 28);
		lblsifre.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblsifre);
		
		tfsifre = new JPasswordField();
		tfsifre.setBounds(388, 240, 131, 19);
		contentPane.add(tfsifre);
		tfsifre.setColumns(10);
		
		lblemail = new JLabel("E-Mail:");
		lblemail.setBounds(226, 276, 68, 28);
		lblemail.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblemail);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(388, 283, 131, 19);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		tftel = new JTextField();
		tftel.setBounds(388, 326, 131, 19);
		contentPane.add(tftel);
		tftel.setColumns(10);
		
		lbltel = new JLabel("Telefon No:");
		lbltel.setBounds(226, 321, 114, 28);
		lbltel.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbltel);

		JLabel lblsoyad = new JLabel("Soyad:");
		lblsoyad.setBounds(226, 147, 68, 28);
		lblsoyad.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblsoyad);
		
		tfsoyad = new JTextField();
		tfsoyad.setBounds(388, 154, 131, 19);
		tfsoyad.setColumns(10);
		contentPane.add(tfsoyad);
		
		tfad = new JTextField();
		tfad.setBounds(388, 111, 131, 19);
		tfad.setColumns(10);
		contentPane.add(tfad);
		
		JButton btnKayit = new JButton("Kayit Ol");
		btnKayit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnKayit.setBounds(335, 381, 108, 28);
		btnKayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				        String Ad = tfad.getText(); 
				        String Soyad = tfsoyad.getText(); 
				        String kadi = tfkulad.getText();
				        String sifre = tfsifre.getText();
				        String email = tfEmail.getText();
				        String telefon = tftel.getText();

				        if (Ad.isEmpty() || Soyad.isEmpty() || kadi.isEmpty() || sifre.isEmpty() || email.isEmpty() || telefon.isEmpty()) {
				            JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun.");
				            return; 
				        }
				        
				        try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {	       
				            String query = "INSERT INTO `libraryautomation`.`customers`(`Name`,`Surname`,`TelephoneNo`,`Email`) VALUES (?, ?, ?, ?)";
				            String query1 = "INSERT INTO `libraryautomation`.`users`(`KullaniciAdi`,`Sifre`,`role_id`) VALUES (?, ?, '2')";
				            String query2 = "SELECT COUNT(*) AS count FROM `libraryautomation`.`users` WHERE `KullaniciAdi` = ?";
				            
				            try (PreparedStatement statement = connection.prepareStatement(query);
				                 PreparedStatement statement1 = connection.prepareStatement(query1);
				            	 PreparedStatement Statement2 = connection.prepareStatement(query2)) {

				            	Statement2.setString(1, kadi);
				            	
				            	try (ResultSet resultSet = Statement2.executeQuery()) {
				                    if (resultSet.next()) {
				                        int count = resultSet.getInt("count");
				                        if (count > 0) {
				                            JOptionPane.showMessageDialog(null, "Bu kullanıcı adı zaten mevcut. Lütfen farklı bir kullanıcı adı seçin.");
				                            return;
				                        }
				                      }
				                    }
				                statement.setString(1, Ad);
				                statement.setString(2, Soyad);
				                statement.setString(3, telefon);
				                statement.setString(4, email);

				                statement1.setString(1, kadi);
				                statement1.setString(2, sifre);

				                int affectedRows1 = statement.executeUpdate();
				                int affectedRows2 = statement1.executeUpdate();

				                if (affectedRows1 > 0 && affectedRows2 > 0) {
				                    connection.commit(); 
				                    JOptionPane.showMessageDialog(null, "Kayıt başarıyla oluşturuldu!");
				                    GirisYap loginPage = new GirisYap();
				                    loginPage.setVisible(true);
				                    dispose();
				                } else {
				                    connection.rollback(); 
				                    JOptionPane.showMessageDialog(null, "Kayıt oluşturulamadı. Lütfen tekrar deneyin.");
				                }
				            }
				        } catch (SQLException ex) {
				            ex.printStackTrace();
				        }
				    }
				});
		contentPane.add(btnKayit);
		
		JLabel lblSignIn = new JLabel("");
		lblSignIn.setBounds(150, 75, 674, 389);
		lblSignIn.setIcon(new ImageIcon(KayitOl.class.getResource("/Library/img/signin.jpg")));
		contentPane.add(lblSignIn);
		
	
	}
}
