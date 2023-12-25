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

public class KitapÖdüncAl extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfKitapId;
	private JButton btnOduncAl;
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
					KitapÖdüncAl frame = new KitapÖdüncAl("1");
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
	public KitapÖdüncAl(String kullaniciAdi) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKitapId = new JLabel("ID");
		lblKitapId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKitapId.setBounds(165, 242, 60, 13);
		contentPane.add(lblKitapId);
		
		tfKitapId = new JTextField();
		tfKitapId.setBounds(259, 240, 96, 19);
		contentPane.add(tfKitapId);
		tfKitapId.setColumns(10);
		
		btnOduncAl = new JButton("Ödünç Al");
		btnOduncAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kitapID = tfKitapId.getText();
			    try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
			        String query = "INSERT INTO mydb.borrowbooks (books_id, customers_id, status_id) VALUES (?, ?, ?)";
			        String query1 = "SELECT id FROM mydb.users WHERE KullanıcıAdı = ?";
			        String query2 = "SELECT BookName FROM mydb.books WHERE id = ?";
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
			                        statement.setInt(3, 3);

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
		btnOduncAl.setBounds(211, 285, 85, 21);
		contentPane.add(btnOduncAl);
	}

}
