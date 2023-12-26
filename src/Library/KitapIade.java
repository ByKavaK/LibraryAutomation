package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class KitapIade extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfKitapId;
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
					KitapIade frame = new KitapIade("1");
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
	public KitapIade(String kullaniciAdi) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KitapIade.class.getResource("/Library/img/kütüp.png")));
		setTitle("Kitap İade");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(233, 150, 122));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfKitapId = new JTextField();
		tfKitapId.setBounds(401, 237, 131, 21);
		tfKitapId.setColumns(10);
		contentPane.add(tfKitapId);
		
		JLabel lblKitapId = new JLabel("ID:");
		lblKitapId.setBounds(247, 238, 60, 15);
		lblKitapId.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblKitapId);
		
		JButton btnIadeEt = new JButton("İade Et");
		btnIadeEt.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnIadeEt.setBounds(276, 268, 189, 44);
		btnIadeEt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kitapID = tfKitapId.getText();
		        try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		            String query = "DELETE FROM libraryautomation.borrowbooks WHERE id = ? and users_id = ?";
		            String query1 = "SELECT id FROM libraryautomation.users WHERE KullaniciAdi = ?";
		            try (PreparedStatement statement = connection.prepareStatement(query);
		            	 PreparedStatement statement1 = connection.prepareStatement(query1)) {
		                statement.setString(1, kitapID);
		                statement1.setString(1, kullaniciAdi);

		                ResultSet resultSet = statement1.executeQuery();
		                if (resultSet.next()) {
		                    int userID = resultSet.getInt("id");
		                    statement.setInt(2, userID);
		                }
		                
		                int affectedRows = statement.executeUpdate();
		                if (affectedRows > 0) {
		                    String mesaj = "Kitap başarıyla iade edildi!";
		                    JOptionPane.showMessageDialog(contentPane, mesaj);
		                } else {
		                    String mesaj = "Kitap iade edilirken bir hata oluştu.";
		                    JOptionPane.showMessageDialog(contentPane, mesaj);
		                }
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		contentPane.add(btnIadeEt);
		
		JButton btnAnasayfa = new JButton("AnaSayfa");
		btnAnasayfa.setBounds(626, 523, 150, 30);
		btnAnasayfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MusteriAnaSayfa müş = new MusteriAnaSayfa(kullaniciAdi);
				müş.setVisible(true);
        		dispose();
			}
		});
		btnAnasayfa.setForeground(Color.BLACK);
		btnAnasayfa.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(btnAnasayfa);
	}

}
