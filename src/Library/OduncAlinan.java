package Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class OduncAlinan extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    static final String DB="jdbc:mysql://127.0.0.1:3306/libraryautomation";
	static final String USER="root";
	static final String PASS="13577";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OduncAlinan frame = new OduncAlinan("bla");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public OduncAlinan(String kullaniciAdi) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(OduncAlinan.class.getResource("/Library/img/kütüp.png")));
    	setTitle("Ödünç Alınan Kitaplar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(2, 0, 783, 500);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setBackground(new Color(253, 245, 230));
        DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Kitap Adı", "Üye Adı" });
        table.setModel(model);
        scrollPane.setViewportView(table);
        
        JButton btnAnasayfa = new JButton("AnaSayfa");
        btnAnasayfa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MusteriAnaSayfa cust = new MusteriAnaSayfa(kullaniciAdi);
        		cust.setVisible(true);
        		dispose();
        	}
        });
        btnAnasayfa.setForeground(Color.BLACK);
        btnAnasayfa.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAnasayfa.setBounds(635, 523, 150, 30);
        contentPane.add(btnAnasayfa);

        try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
            String query = "SELECT bb.id, b.BookName, u.KullaniciAdi FROM libraryautomation.borrowbooks bb JOIN libraryautomation.books b ON bb.books_id = b.id JOIN libraryautomation.users u ON bb.users_id = u.id WHERE u.KullaniciAdi = ? and bb.status_id = '1' ORDER BY id ASC";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, kullaniciAdi);
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
    }
}
