package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Signin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfAd;
	private JLabel lblNewLabel_1;
	private JTextField tfSoyad;
	private JLabel lblNewLabel_2;
	private JTextField tfEmail;
	private JTextField tfTelefonNo;
	private JLabel lblNewLabel_3;
	private JButton btnKayitOl;

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
		contentPane.setName("Signin");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ad");
		lblNewLabel.setBounds(272, 192, 68, 28);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblNewLabel);
		
		tfAd = new JTextField();
		tfAd.setBounds(388, 197, 96, 19);
		contentPane.add(tfAd);
		tfAd.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Soyad");
		lblNewLabel_1.setBounds(272, 235, 68, 28);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblNewLabel_1);
		
		tfSoyad = new JTextField();
		tfSoyad.setBounds(388, 240, 96, 19);
		contentPane.add(tfSoyad);
		tfSoyad.setColumns(10);
		
		lblNewLabel_2 = new JLabel("E-Mail");
		lblNewLabel_2.setBounds(272, 278, 68, 28);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblNewLabel_2);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(388, 283, 96, 19);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		tfTelefonNo = new JTextField();
		tfTelefonNo.setBounds(388, 326, 96, 19);
		contentPane.add(tfTelefonNo);
		tfTelefonNo.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Telefon No");
		lblNewLabel_3.setBounds(272, 321, 68, 28);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblNewLabel_3);
		
		btnKayitOl = new JButton("Kayıt Ol");
		btnKayitOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnKayitOl.setBounds(324, 387, 117, 28);
		btnKayitOl.setForeground(Color.RED);
		btnKayitOl.setBackground(Color.BLACK);
		contentPane.add(btnKayitOl);
	}

}
