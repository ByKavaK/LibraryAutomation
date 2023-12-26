package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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

public class KitapOduncAl extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfKitapId;
	private JButton btnOduncAl;
	static final String DB="jdbc:mysql://127.0.0.1:3306/libraryautomation";
	static final String USER="root";
	static final String PASS="13577";
	private JButton btnAnasayfa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapOduncAl frame = new KitapOduncAl("1");
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
	public KitapOduncAl(String kullaniciAdi) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KitapOduncAl.class.getResource("/Library/img/kütüp.png")));
		setTitle("Kitap Ödünç Al");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKitapId = new JLabel("ID:");
		lblKitapId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKitapId.setBounds(245, 240, 60, 13);
		contentPane.add(lblKitapId);
		
		tfKitapId = new JTextField();
		tfKitapId.setBounds(404, 239, 143, 19);
		contentPane.add(tfKitapId);
		tfKitapId.setColumns(10);
		
		btnOduncAl = new JButton("Ödünç Al");
		btnOduncAl.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnOduncAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kitapID = tfKitapId.getText();
			    try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
			        String query = "INSERT INTO libraryautomation.borrowbooks (books_id, users_id, status_id) VALUES (?, ?, ?)";
			        String query1 = "SELECT id FROM libraryautomation.users WHERE KullaniciAdi = ?";
			        String query2 = "SELECT BookName FROM libraryautomation.books WHERE id = ?";
			        try (PreparedStatement statement = connection.prepareStatement(query);
			                PreparedStatement statement1 = connection.prepareStatement(query1);
			                PreparedStatement statement2 = connection.prepareStatement(query2);) {
			            statement1.setString(1, kullaniciAdi);
			            ResultSet resultSet = statement1.executeQuery();
			            if (resultSet.next()) {
			                int musteriId = resultSet.getInt("id");
			                statement2.setString(1, kitapID);
			                ResultSet bookResultSet = statement2.executeQuery();
			                if (bookResultSet.next()) {
			                    String kitapAdi = bookResultSet.getString("BookName");

			                    int onay = JOptionPane.showConfirmDialog(contentPane,
			                            "Kitap adı: " + kitapAdi + "\nBu kitabı ödünç almak istediğinize emin misiniz?",
			                            "Ödünç Alma Onayı", JOptionPane.YES_NO_OPTION);
			                    if (onay == JOptionPane.YES_OPTION) {
			                        statement.setString(1, kitapID);
			                        statement.setInt(2, musteriId);
			                        statement.setInt(3, 2);

			                        int affectedRows = statement.executeUpdate();
			                        if (affectedRows > 0) {
			                            String mesaj = kitapAdi + " başarıyla ödünç alındı!";
			                            JOptionPane.showMessageDialog(contentPane, mesaj);
			                        } else {
			                            String mesaj = "Kitap ödünç alınırken bir hata oluştu.";
			                            JOptionPane.showMessageDialog(contentPane, mesaj);
			                        }
			                    } else {
			                        String mesaj = "İşlem iptal edildi.";
			                        JOptionPane.showMessageDialog(contentPane, mesaj);
			                    }
			                } else {
			                    String mesaj = "Kitap bulunamadı.";
			                    JOptionPane.showMessageDialog(contentPane, mesaj);
			                }
			                bookResultSet.close();
			            } else {
			                String mesaj = "Kullanıcı bulunamadı.";
			                JOptionPane.showMessageDialog(contentPane, mesaj);
			            }
			            resultSet.close();
			        }
			    } catch (SQLException ex) {
			        ex.printStackTrace();
			    }
		    }
		});
		btnOduncAl.setBounds(295, 271, 150, 38);
		contentPane.add(btnOduncAl);
		
		btnAnasayfa = new JButton("AnaSayfa");
		btnAnasayfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MusteriAnaSayfa müş = new MusteriAnaSayfa(kullaniciAdi);
				müş.setVisible(true);
        		dispose();
			}
		});
		btnAnasayfa.setForeground(Color.BLACK);
		btnAnasayfa.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAnasayfa.setBounds(626, 523, 150, 30);
		contentPane.add(btnAnasayfa);
	}

}
