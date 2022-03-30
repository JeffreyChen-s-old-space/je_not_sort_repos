package insert.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCompanyData {
    static final String jdbcDriver = "org.mariadb.jdbc.Driver";
    static final String dbURL = "jdbc:mariadb://140.127.74.170/410877027";
    static final String user = "410877027";
    static final String password = "410877027";

    public static void main(String[] argv) {
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(dbURL, user, password);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("REPLACE INTO " +
                            "company(company_id,company_name) " +
                            "VALUES (?,?)");
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "沃爾瑪");
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
