package Jdbc.SchoolManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import DatabaseConnectivity.DBConnection;

class StudentLogin {
	Scanner scanner = new Scanner(System.in);
	  DBConnection connect = new DBConnection(); 
    private Connection connection;

    public StudentLogin() {
        connectDB();
    }
    
    public void adminLogin() {
        System.out.println("-------------------------------------");
        System.out.println("\t Welcome user");
        System.out.println("-------------------------------------");
        StudentLogin();
        }

    public void connectDB() {
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

    public void StudentLogin() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the username: ");
        String userName = scan.next();
        System.out.print("Enter the password: ");
        String userPass = scan.next();
        logMain(userName, userPass);
    }

    public void logMain(String name, String password) {

        if (canUserLogin(name, password)) {
            System.out.println("-------------------------------------");
            System.out.println("\tLogin successful!");
            System.out.println("-------------------------------------");
            
            //SchoolManagementSystem action = new SchoolManagementSystem();
            studentDetails();         
            
        } else {
            System.out.println("Login failed. Invalid username or password.");
            StudentLogin();
        }
    }

    public void studentDetails() {
		 Scanner scanner = new Scanner(System.in);

		 System.out.println("1. View Department");
		 System.out.println("2. Apply Leave");
		 System.out.println("3. View Leave");
//		 System.out.println("4. Update Leave");
		 System.out.println("5. Delete Leave");
		 System.out.println("6. Change password");
		 System.out.println("7. Logout");
		 
		 System.out.println("Enter the action you want to do : ");
		 
		 int action = scanner.nextInt();
		 switch(action) {
		 case 1:
//			 Admin admin = new Admin();
//			 admin.viewDepartments();
			 Admin dept = new Admin();
			 dept.viewDepartments();
			 studentDetails();
			 break;
		 case 2:
			 ApplyLeaves leave = new ApplyLeaves(null);
			 leave.ApplyLeaves();
			 studentDetails();
			 break;
			 
		 case 3:
			 ApplyLeaves view = new ApplyLeaves(null);
			 view.viewLeave();
			 studentDetails();
			 break;
//		 case 4:
//			 ApplyLeaves update = new ApplyLeaves(null);
//			 update.updateLeave();
//			 studentDetails();
//			 break;
		 case 5:
			 ApplyLeaves delete = new ApplyLeaves(null);
			 delete.deleteLeave();
			 studentDetails(); 
			break;
		case 6:
			changePassword();
			studentDetails();
			break;
		case 7:
			System.out.println("Logging out.....");
			System.exit(0);
			break;
       default:
           System.out.println("Invalid choice. Exiting...");
           System.exit(0);
		 }
    }
		 
		 
    public boolean canUserLogin(String userName, String password) {
        boolean isValidUser = false;

        try {
            String sql = "SELECT * FROM user_info WHERE userName = ? AND userPassword = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, userName);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            isValidUser = resultSet.next();

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isValidUser;
    }
public void changePassword() {
	try {
		System.out.println("Enter your id to change password : ");
		String id = scanner.next();
		
		System.out.println("Enter your new password : ");
		String password = scanner.next();
		
		connect.InitiateDB();
		
		String sql = "UPDATE USER_INFO SET USERPASSWORD = ? WHERE USERID = ?";
        PreparedStatement statement = connect.getConnection().prepareStatement(sql);
        
        statement.setString(1, password);
        statement.setString(2, id);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0)
        	System.out.println("---------------------------------------------");
            System.out.println("\tPassword updated successfully!");
        	System.out.println("---------------------------------------------");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
