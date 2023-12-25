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

public class KitapSil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfKitapId;
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
					KitapSil frame = new KitapSil();
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
	public KitapSil() {
		setTitle("Kitap Sil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfKitapId = new JTextField();
		tfKitapId.setBounds(276, 255, 96, 19);
		contentPane.add(tfKitapId);
		tfKitapId.setColumns(10);
		
		JLabel lblKitapId = new JLabel("ID");
		lblKitapId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKitapId.setBounds(195, 257, 56, 13);
		contentPane.add(lblKitapId);
		
		JButton btnSil = new JButton("SİL");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kitapID = Integer.parseInt(tfKitapId.getText());
                String kitapAdi = "";
                try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
                    String query = "SELECT BookName FROM mydb.books WHERE id = ?";
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
                        String query = "DELETE FROM mydb.books WHERE id = ?";
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
		btnSil.setBounds(234, 296, 85, 21);
		contentPane.add(btnSil);
	}

}
