package Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	public static Connection connect() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/mydb";
        String user = "root";
        String password = "13577";

        return DriverManager.getConnection(url, user, password);
    }
}
