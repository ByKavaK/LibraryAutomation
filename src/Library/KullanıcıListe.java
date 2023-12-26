package Library;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

public class KullanıcıListe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable kulTable;
	private JLabel lblKulAd;
	private JTextField tfKulAd;
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
					KullanıcıListe frame = new KullanıcıListe("");
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
	public KullanıcıListe(String kullaniciAdi) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KullanıcıListe.class.getResource("/Library/img/kütüp.png")));
		setTitle("Kullanıcı Listesi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 51, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 70, 786, 426);
		contentPane.add(scrollPane);
		
		kulTable = new JTable();
		try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		    String query = "SELECT `libraryautomation`.`users`.id,`libraryautomation`.`users`.KullaniciAdi,`libraryautomation`.`role`.Role_Name FROM `libraryautomation`.`users` JOIN `libraryautomation`.`role` ON `users`.`role_id` =  `libraryautomation`.`role`.id ORDER BY id ASC";
		    try (PreparedStatement statement = connection.prepareStatement(query)) {
		        try (ResultSet resultSet = statement.executeQuery()) {
		            ResultSetMetaData metaData = resultSet.getMetaData();
		            int columnCount = metaData.getColumnCount();
		            String[] columnNames = new String[columnCount];
		            for (int i = 1; i <= columnCount; i++) {
		                columnNames[i - 1] = metaData.getColumnName(i);
		            }
		            ArrayList<Object[]> data = new ArrayList<>();
		            while (resultSet.next()) {
		                Object[] row = new Object[columnCount];
		                for (int i = 1; i <= columnCount; i++) {
		                    row[i - 1] = resultSet.getObject(i);
		                }
		                data.add(row);
		            }
		            Object[][] dataArray = data.toArray(new Object[0][0]);
		            DefaultTableModel model = new DefaultTableModel(dataArray, columnNames);
		            kulTable.setModel(model);
		        }
		    }
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
		kulTable.setBounds(241, 465, 157, 62);
		//contentPane.add(table);
		scrollPane.setViewportView(kulTable);
		
		lblKulAd = new JLabel("Kullanıcı Adı");
		lblKulAd.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKulAd.setBounds(44, 28, 107, 19);
		contentPane.add(lblKulAd);
		
		tfKulAd = new JTextField();
		tfKulAd.setBounds(161, 28, 160, 19);
		contentPane.add(tfKulAd);
		tfKulAd.setColumns(10);
		
		JButton btnKulAra = new JButton("Ara");
		btnKulAra.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnKulAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kulAd = tfKulAd.getText();

		        try (Connection connection = DriverManager.getConnection(DB, USER, PASS)) {
		            String query = "SELECT users.id, users.KullaniciAdi, libraryautomation.role.RoleName FROM libraryautomation.users JOIN libraryautomation.role ON users.role_id = libraryautomation.role.id WHERE users.KullanıcıAdı LIKE ? ORDER BY id ASC";
		            
		            try (PreparedStatement statement = connection.prepareStatement(query)) {
		                statement.setString(1, "%" + kulAd + "%");

		                try (ResultSet resultSet = statement.executeQuery()) {
		                    ResultSetMetaData metaData = resultSet.getMetaData();
		                    int columnCount = metaData.getColumnCount();
		                    String[] columnNames = new String[columnCount];
		                    for (int i = 1; i <= columnCount; i++) {
		                        columnNames[i - 1] = metaData.getColumnName(i);
		                    }
		                    ArrayList<Object[]> data = new ArrayList<>();
		                    while (resultSet.next()) {
		                        Object[] row = new Object[columnCount];
		                        for (int i = 1; i <= columnCount; i++) {
		                            row[i - 1] = resultSet.getObject(i);
		                        }
		                        data.add(row);
		                    }
		                    Object[][] dataArray = data.toArray(new Object[0][0]);
		                    DefaultTableModel model = new DefaultTableModel(dataArray, columnNames);
		                    kulTable.setModel(model);
		                }
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btnKulAra.setBounds(346, 27, 125, 21);
		contentPane.add(btnKulAra);
		
		btnAnasayfa = new JButton("AnaSayfa");
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
