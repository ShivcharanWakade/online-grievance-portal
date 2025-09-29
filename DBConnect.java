package complaint.system;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/complaint_system",
            "root",     // your MySQL user
            "tiger"  // your MySQL password
        );
    }
}
