package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
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

public class KitapOduncBekleyen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JButton btnNewButton;
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
					KitapOduncBekleyen frame = new KitapOduncBekleyen("1");
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
	public KitapOduncBekleyen(String kullaniciAdi) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KitapOduncBekleyen.class.getResource("/Library/img/kütüp.png")));
		setTitle("Ödünç Almayı Bekleyenler");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 222, 173));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(2, 80, 784, 435);
		contentPane.add(scrollPane);
		
		table = new JTable();
        table.setBackground(new Color(253, 245, 230));
        DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Kitap Adı", "Üye Adı" });
        table.setModel(model);
        scrollPane.setViewportView(table);
		
		try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
            String query = "SELECT bb.id, b.BookName, u.KullaniciAdi FROM libraryautomation.borrowbooks bb JOIN libraryautomation.books b ON bb.books_id = b.id JOIN libraryautomation.users u ON bb.users_id = u.id WHERE bb.status_id = '2' ORDER BY id DESC";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String bookName = resultSet.getString("BookName");
                    String userName = resultSet.getString("KullaniciAdi");
                    model.addRow(new Object[] { id, bookName, userName });
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		
		lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(60, 38, 45, 13);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(115, 37, 134, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("ONAYLA");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kitapID = textField.getText();
		        try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		            String query = "UPDATE libraryautomation.borrowbooks SET status_id = 1 WHERE id = ?";
		            try (PreparedStatement statement = connection.prepareStatement(query)) {
		                statement.setString(1, kitapID);
		                int affectedRows = statement.executeUpdate();
		                if (affectedRows > 0) {
		                    String mesaj = "Kitap başarıyla onaylandı!";
		                    JOptionPane.showMessageDialog(contentPane, mesaj);
		                } else {
		                    String mesaj = "Kitap onaylanırken bir hata oluştu.";
		                    JOptionPane.showMessageDialog(contentPane, mesaj);
		                }
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btnNewButton.setBounds(280, 38, 141, 18);
		contentPane.add(btnNewButton);
		
		btnAnasayfa = new JButton("AnaSayfa");
		btnAnasayfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VeznedarAnaSayfa vezn = new VeznedarAnaSayfa(kullaniciAdi);
				vezn.setVisible(true);
        		dispose();
			}
		});
		btnAnasayfa.setForeground(Color.BLACK);
		btnAnasayfa.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAnasayfa.setBounds(626, 523, 150, 30);
		contentPane.add(btnAnasayfa);
		
	}

}
