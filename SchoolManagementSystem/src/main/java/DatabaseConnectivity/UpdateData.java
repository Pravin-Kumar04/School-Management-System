package DatabaseConnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateData {

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

    public int updateData(String sql, Object[] params) {
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            result = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        UpdateData objUD = new UpdateData();
        Scanner scanner = new Scanner(System.in);
        objUD.ConnectDB();

        System.out.print("Enter the user ID to update: ");
        int userIdToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter username : ");
		String userName = scanner.next();
   
       System.out.print("Enter user ID : ");
       int userId = scanner.nextInt();
       
       System.out.print("Enter user password : ");
       String userPass = scanner.next();
       
       System.out.print("Enter your standard : ");
       String userStandard = scanner.next();
       
       System.out.print("Enter your email : ");
       String userMail = scanner.next();
       
       System.out.print("Enter DOB : ");
        String userDOB = scanner.next();
       
       System.out.print("Enter your contact number : ");
       String userContact = scanner.next();
   
       System.out.print("Enter your location : ");
       String userLocation = scanner.next();
       
        System.out.print("Enter the new email: ");
        String newUserMail = scanner.nextLine();

        String sql = "UPDATE user_info SET USERMAIL = ? WHERE USERID = ?";
        Object[] params = { newUserMail, userIdToUpdate };

        int rowsUpdated = objUD.updateData(sql, params);

        if (rowsUpdated > 0)
            System.out.println("User with ID " + userIdToUpdate + " was updated successfully!");
        else
            System.out.println("No user with ID " + userIdToUpdate + " found.");

        scanner.close();
        try {
            objUD.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
