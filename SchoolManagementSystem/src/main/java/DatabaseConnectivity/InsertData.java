package DatabaseConnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertData {
    
    private Connection connection;
    public Connection getConnection() {
        return connection;
    }
    
    public Connection ConnectDB() {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "DATABASE";
        String password = "6246";

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "DATABASE", "6246");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
		return connection;
    }
    
    public int InsertDB(String sql, Object[] params) {
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 1; i <= params.length; i++) {
                statement.setObject(i, params[i]);
            }
            result = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
        	
            e.printStackTrace();
        }
        return result;
    }
    
    public static void main(String[] args) {
        InsertData objID = new InsertData();
       // Scanner scanner = new Scanner(System.in);
        objID.ConnectDB();
        
        Scanner user = new Scanner(System.in);
		 System.out.print("Enter username : ");
		 String userName = user.next();
    
        System.out.print("Enter user ID : ");
        int userId = user.nextInt();
        
        System.out.print("Enter user password : ");
        String userPass = user.next();
        
        System.out.print("Enter your standard : ");
        String userStandard = user.next();
        
        System.out.print("Enter your email : ");
        String userMail = user.next();
        
        System.out.print("Enter DOB : ");
         String userDOB = user.next();
        
        System.out.print("Enter your contact number : ");
        String userContact = user.next();
    
        System.out.print("Enter your location : ");
        String userLocation = user.next();

        String sql = "INSERT INTO user_info (USERNAME, USERID, USERPASS, USERSTANDARD, USERMAIL, USERDOB, USERCONTACT, USERLOCATION) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = { userName, userId, userPass, userStandard, userMail,userDOB, userContact, userLocation };

        int rowsInserted = objID.InsertDB(sql, params);
        if (rowsInserted > 0) 
            System.out.println("User was inserted successfully!");
        
       // scanner.close();
        try {
            objID.connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}