package Jdbc.SchoolManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentRegistration {

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
            connection = DriverManager.getConnection(jdbcUrl, username, password);
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
                statement.setObject(i, params[i - 1]);
            }
            result = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void studentReg() {
    	
    	ConnectDB();
        Scanner student = new Scanner(System.in);

        System.out.println("---------------------");
        System.out.println("Student Registration");
        System.out.println("---------------------");

        System.out.print("Enter your Student Name : ");
        String userName = student.next();

        System.out.print("Enter Student Class : ");
        String userStandard = student.next();

        System.out.print("Enter Student Contact : ");
        String userContact = student.next();
        
        System.out.print("Enter Student Password : ");
        String userPass = student.next();
        
        System.out.print("Enter Student ID : ");
        String userId = student.next();

        System.out.print("Enter Student DOB : ");
        String userDOB = student.next();

        System.out.print("Enter Student Mail : ");
        String userMail = student.next();

        System.out.print("Enter Student Location : ");
        String userLocation = student.next();

        String sql = "INSERT INTO user_info ( userName, userId, userPassword, userStandard, userMail,userDOB, userContact, userLocation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = { userName, userId, userPass, userStandard, userMail,userDOB, userContact, userLocation };

        int rowsInserted = InsertDB(sql, params);
        if (rowsInserted > 0)
        	System.out.println("---------------------------------------");
            System.out.println("\tRegistration successful!");
        	System.out.println("---------------------------------------");


        student.close();
        
        try {
           connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}