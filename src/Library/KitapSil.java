package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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

public class KitapSil extends JFrame {

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
					KitapSil frame = new KitapSil("");
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
	public KitapSil(String kullaniciAdi) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KitapSil.class.getResource("/Library/img/kütüp.png")));
		setTitle("Kitap Sil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfKitapId = new JTextField();
		tfKitapId.setBounds(405, 261, 161, 19);
		contentPane.add(tfKitapId);
		tfKitapId.setColumns(10);
		
		JLabel lblKitapId = new JLabel("ID:");
		lblKitapId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKitapId.setBounds(253, 263, 56, 13);
		contentPane.add(lblKitapId);
		
		JButton btnSil = new JButton("SİL");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kitapID = Integer.parseInt(tfKitapId.getText());
                String kitapAdi = "";
                try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
                    String query = "SELECT BookName FROM libraryautomation.books WHERE id = ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setInt(1, kitapID);
                        try (ResultSet resultSet = statement.executeQuery()) {
                            if (resultSet.next()) {
                                kitapAdi = resultSet.getString("BookName");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Silmek istediğiniz kitap: " + kitapAdi + "\nEmin misiniz?", "Kitap Sil",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
                        String query = "DELETE FROM libraryautomation.books WHERE id = ?";
                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.setInt(1, kitapID);
                            int affectedRows = statement.executeUpdate();
                            if (affectedRows > 0) {
                                JOptionPane.showMessageDialog(null, "Kitap başarıyla silindi.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Kitap silinirken bir hata oluştu.");
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }}
			}
		});
		btnSil.setBounds(302, 304, 179, 30);
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
