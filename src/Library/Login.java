package Library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfKAdi;
	private JPasswordField tfSifre;

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kullanıcı Adı");
		lblNewLabel.setBounds(254, 174, 68, 28);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblNewLabel);
		
		JLabel lblSifre = new JLabel("Şifre");
		lblSifre.setBounds(254, 222, 68, 28);
		lblSifre.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblSifre);
		
		tfKAdi = new JTextField();
		tfKAdi.setBounds(366, 179, 96, 19);
		contentPane.add(tfKAdi);
		tfKAdi.setColumns(10);
		
		tfSifre = new JPasswordField();
		tfSifre.setBounds(366, 227, 96, 19);
		contentPane.add(tfSifre);
		
		JButton btnGiris = new JButton("Giriş");
		btnGiris.setBounds(312, 279, 117, 28);
		contentPane.add(btnGiris);
		
		JButton btnUyeOl = new JButton("Üye Ol");
		btnUyeOl.setBounds(312, 326, 117, 28);
		contentPane.add(btnUyeOl);
	}
	
	
}
