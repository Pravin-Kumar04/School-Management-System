package DatabaseConnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteData {

    Connection connection;

    public void ConnectDB() {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "DATABASE";
        String password = "6246";

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public int deleteData(String sql, int userId) {
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            result = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        DeleteData objDD = new DeleteData();
        Scanner scanner = new Scanner(System.in);
        objDD.ConnectDB();

        System.out.print("Enter the user ID to delete: ");
        int userIdToDelete = scanner.nextInt();

        String sql = "DELETE FROM user_info WHERE USERID = ?";
        int rowsDeleted = objDD.deleteData(sql, userIdToDelete);

        if (rowsDeleted > 0)
            System.out.println("User with ID " + userIdToDelete + " was deleted successfully!");
        else
            System.out.println("No user with ID " + userIdToDelete + " found.");

        scanner.close();
        try {
            objDD.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
