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

public class Signin extends JFrame {

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
					Signin frame = new Signin();
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
	public Signin() {
		setTitle("Signin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setName("Signin");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblad = new JLabel("Ad");
		lblad.setBounds(272, 106, 68, 28);
		lblad.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblad);
		lblkulad = new JLabel("Kullanıcı Adı");
		lblkulad.setBounds(272, 192, 68, 28);
		lblkulad.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblkulad);
		
		tfkulad = new JTextField();
		tfkulad.setBounds(388, 197, 96, 19);
		contentPane.add(tfkulad);
		tfkulad.setColumns(10);

		
		lblsifre = new JLabel("Şifre");
		lblsifre.setBounds(272, 235, 68, 28);
		lblsifre.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblsifre);
		
		tfsifre = new JTextField();
		tfsifre.setBounds(388, 240, 96, 19);
		contentPane.add(tfsifre);
		tfsifre.setColumns(10);
		
		lblemail = new JLabel("E-Mail");
		lblemail.setBounds(272, 278, 68, 28);
		lblemail.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblemail);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(388, 283, 96, 19);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		tftel = new JTextField();
		tftel.setBounds(388, 326, 96, 19);
		contentPane.add(tftel);
		tftel.setColumns(10);
		
		lbltel = new JLabel("Telefon No");
		lbltel.setBounds(272, 321, 68, 28);
		lbltel.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lbltel);

		JLabel lblsoyad = new JLabel("Soyad");
		lblsoyad.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblsoyad.setBounds(272, 149, 68, 28);
		contentPane.add(lblsoyad);
		
		tfsoyad = new JTextField();
		tfsoyad.setColumns(10);
		tfsoyad.setBounds(388, 154, 96, 19);
		contentPane.add(tfsoyad);
		
		tfad = new JTextField();
		tfad.setColumns(10);
		tfad.setBounds(388, 111, 96, 19);
		contentPane.add(tfad);
		
		JButton btnKayit = new JButton("Kayit Ol");
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
				            connection.setAutoCommit(false); 

				            String query = "INSERT INTO `mydb`.`customers`(`Name`,`Surname`,`TelephoneNo`,`Email`) VALUES (?, ?, ?, ?)";
				            String query1 = "INSERT INTO `mydb`.`users`(`KullanıcıAdı`,`Şifre`,`role_id`) VALUES (?, ?, '2')";
				            String query2 = "SELECT COUNT(*) AS count FROM `mydb`.`users` WHERE `KullanıcıAdı` = ?";
				            
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
				                    Login loginPage = new Login();
				                    loginPage.setVisible(true);
				                } else {
				                    connection.rollback(); 
				                    JOptionPane.showMessageDialog(null, "Kayıt oluşturulamadı. Lütfen tekrar deneyin.");
				                }
				            }
				        } catch (SQLException ex) {
				            ex.printStackTrace();
				            JOptionPane.showMessageDialog(null, "Kayıt oluşturma sırasında bir hata oluştu.");
				        }
				    }
				});
		btnKayit.setBounds(335, 381, 85, 21);
		contentPane.add(btnKayit);
		
	
	}
}
