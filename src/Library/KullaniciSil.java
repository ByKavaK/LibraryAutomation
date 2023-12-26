package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class KullaniciSil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
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
					KullaniciSil frame = new KullaniciSil("1");
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
	public KullaniciSil(String kullaniciAdi) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KullaniciSil.class.getResource("/Library/img/kütüp.png")));
		setTitle("Kullanıcı Sil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("Kullanıcı ID:");
		lblId.setBounds(245, 264, 94, 13);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setBounds(441, 263, 150, 19);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		JButton btnSil = new JButton("SİL");
		btnSil.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSil.setBounds(315, 308, 150, 30);
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kulID = Integer.parseInt(tfId.getText());
                String kulAdi = "";
                try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
                    String query = "SELECT KullaniciAdi FROM libraryautomation.users WHERE id = ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setInt(1, kulID);
                        try (ResultSet resultSet = statement.executeQuery()) {
                            if (resultSet.next()) {
                                kulAdi = resultSet.getString("KullaniciAdi");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Silmek istediğiniz kullanıcı: " + kulAdi + "\nEmin misiniz?", "Kitap Sil",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
                        String query = "DELETE FROM libraryautomation.users WHERE id = ?";
                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.setInt(1, kulID);
                            int affectedRows = statement.executeUpdate();
                            if (affectedRows > 0) {
                                JOptionPane.showMessageDialog(null, "Kullanıcı başarıyla silindi.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Kullanıcı silinirken bir hata oluştu.");
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }}
			}
		});
		contentPane.add(btnSil);
		
		JButton btnAnasayfa = new JButton("AnaSayfa");
		btnAnasayfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAnaSayfa admin = new AdminAnaSayfa(kullaniciAdi);
				admin.setVisible(true);
        		dispose();
			}
		});
		btnAnasayfa.setForeground(Color.BLACK);
		btnAnasayfa.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAnasayfa.setBounds(626, 523, 150, 30);
		contentPane.add(btnAnasayfa);
	}

}
