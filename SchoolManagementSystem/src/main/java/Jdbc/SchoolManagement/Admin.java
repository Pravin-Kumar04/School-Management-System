package Jdbc.SchoolManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import DatabaseConnectivity.*;

class Admin {
	
	public Connection connection;
  public Connection getConnection() {
      return connection;
  }
  DBConnection connect = new DBConnection(); 
  Scanner scanner = new Scanner(System.in);
	
  
  public void admin() {
 
 
 int mainChoice;
 do {
 System.out.println("Admin Menu");
 System.out.println("1. Add Student");
 System.out.println("2. View Student");
 System.out.println("3. Update Student");
 System.out.println("4. Delete Student");
 System.out.println("5. Leave Management");
 System.out.println("6. User Accounts");
 System.out.println("7. Update Attendance");
 System.out.println("8. Approve Student Leave");
 System.out.println("9. Change Password");
 System.out.println("10. All Report");
 System.out.println("11. Exit");
 System.out.print("Choose your Option: ");
 mainChoice=scanner.nextInt();
 switch (mainChoice) {
 case 1:
	 addStudent();
	 break;
 case 2:
	 viewStudents();
	 break;
 case 3:
	 updateStudent();
	 break;
 case 4:
	 deleteStudent();
	 break;
 case 5:
	 LeaveManagement manage = new LeaveManagement();
	 manage.handleLeave();
//	 ApplyLeaves view = new ApplyLeaves(null);
//	 view.viewLeave();
	 break;
 case 6:
	 DeleteUsers user = new DeleteUsers();
	 user.userAccounts();
	 break;
	 
 case 7:
	updateAttendance();
	break;
	
 case 8:
	 approveLeave();
	 break;
	 
	
 case 9 :
	 
	 changePassword();
	 break;
	 
 case 10 :
	 viewDepartments();
	 ApplyLeaves view = new ApplyLeaves(null);
	   view.viewLeave();
	 viewStudents();
	break;
 case 11:
	 System.out.println("Exiting...");
	 System.exit(0);
	 break;
	 
 default:
	 System.out.println("Invalid option. Please choose a valid option.");
	 break;
 }
 } while (mainChoice != 8);
 }
  
 
 
 
public void addStudent() {
	try {
    Scanner depts = new Scanner(System.in);

    System.out.print("\nEnter student department : ");
    String department = depts.next();

    System.out.print("Enter student name : ");
    String name = depts.next();

    System.out.print("Enter student id : ");
    String id = depts.next();

    System.out.print("Enter student's contact number : ");
    String contactNumber = depts.next();

    System.out.print("Enter student's location : ");
    String location  = depts.next();
    
    
    connect.InitiateDB();
	
    String sql = "INSERT INTO DEPTS (DEPARTMENT, NAME, USERID, CONTACT_NUMBER, LOCATION) VALUES (?, ?, ?, ?, ?)";
	Object[] params = { department, name, id, contactNumber, location };
	int rowsInserted = connect.InsertDB(sql, params);
	if (rowsInserted > 0) {
		System.out.println("---------------------------------------");
		System.out.println("\tNew data added successfully");
		System.out.println("---------------------------------------");

	}
} catch (SQLException e) {
	e.printStackTrace();
}

}


public void viewDepartments() {
    try {
        connect.InitiateDB();
        
        String sql = "SELECT * FROM DEPARTMENTS"; // Corrected table name
        PreparedStatement statement = connect.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        System.out.println("--------------------------------------");
        System.out.println("\tList of Departments");
        System.out.println("--------------------------------------");
        
        System.out.println("\n----------------------------------------");
        System.out.println("Standards\t|\tAvailable_slots"); // Adjusted column names
        System.out.println("----------------------------------------");
        
        while (resultSet.next()) {
            //String departmentID = resultSet.getString("DepartmentID"); // Adjusted column name
            String standard = resultSet.getString("Standards");
            String availableSlots = resultSet.getString("Available_slots"); // Adjusted column name
           
            System.out.println(standard + "\t\t|\t" + availableSlots);
            System.out.println("----------------------------------------");
        }
        
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



public void viewStudents() {
    try {

    	connect.InitiateDB();
        
        String sql = "SELECT * FROM DEPTS";
        PreparedStatement statement = connect.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        System.out.println("--------------------------------------");
        System.out.println("\tList of Departments:");
        System.out.println("--------------------------------------");
        
        System.out.println("\n---------------------------------------------------------------------------------");
        System.out.println("USERID\t\tDEPARTMENT\tNAME\t\tCONTACT_NUMBER\t\tLOCATION");
        System.out.println("---------------------------------------------------------------------------------");
        
        while (resultSet.next()) {
            String userid = resultSet.getString("USERID");

            String department = resultSet.getString("DEPARTMENT");
            String name = resultSet.getString("NAME");
//            String id = resultSet.getString("ID");
            String contactNumber = resultSet.getString("CONTACT_NUMBER");
            String location = resultSet.getString("LOCATION");
            
            
            System.out.println(userid+"\t\t"+department+"\t\t"+name+"\t\t"+contactNumber+"\t\t"+location);
            System.out.println("---------------------------------------------------------------------------------");
        }
        
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



public void approveLeave() {
    try {
        System.out.println("Enter student name to approve leave : ");
        String name = scanner.next();
        
        System.out.println("Enter approve/disapproved for this leave : ");
        String approve = scanner.next();
        
        connect.InitiateDB();

        // Check if the student name exists in the table
        String checkSql = "SELECT Name FROM LEAVE WHERE Name = ?";
        PreparedStatement checkStatement = connect.getConnection().prepareStatement(checkSql);
        checkStatement.setString(1, name); // Correct index
        ResultSet resultSet = checkStatement.executeQuery();

        if (resultSet.next()) {
            // If the student name exists, proceed with the update
            String updateSql = "UPDATE LEAVE SET Approved = ? WHERE Name = ?";
            PreparedStatement statement = connect.getConnection().prepareStatement(updateSql);
            statement.setString(1, approve);
            statement.setString(2, name);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("---------------------------------------------");
                System.out.println("\t     Leave Approved successfully!");
                System.out.println("---------------------------------------------");
            } else {
                System.out.println("Failed to approve leave.");
            }
        } else {
            // If the student name doesn't exist, inform the user
            System.out.println("Student name " + name + " not found.");
        }

        admin();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



public void updateAttendance() {
    try {
        System.out.println("Enter student id to update attendance: ");
        String id = scanner.next();
        System.out.println("Enter the attendance for the student id " + id + ": ");
        String attendance = scanner.next();
        connect.InitiateDB();

        // Check if the student ID exists in the table
        String checkSql = "SELECT USERId FROM DEPTS WHERE USERId = ?";
        PreparedStatement checkStatement = connect.getConnection().prepareStatement(checkSql);
        checkStatement.setString(1, id);
        ResultSet resultSet = checkStatement.executeQuery();

        if (resultSet.next()) {
            // If the student ID exists, proceed with the update
            String updateSql = "UPDATE DEPTS SET ATTENDANCE = ? WHERE USERId = ?";
            PreparedStatement statement = connect.getConnection().prepareStatement(updateSql);
            statement.setString(1, attendance);
            statement.setString(2, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("---------------------------------------------");
                System.out.println("\t     Attendance updated successfully!");
                System.out.println("---------------------------------------------");
            } else {
                System.out.println("Failed to update attendance.");
            }
        } else {
            // If the student ID doesn't exist, inform the user
            System.out.println("Student ID " + id + " not found.");
        }

        admin();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void updateStudent() {
	
    try {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student ID you want to update: ");
        String id = scanner.next();
        System.out.print("Enter the new department: ");
        String department = scanner.next();
        
        System.out.print("Enter the new name: ");
        String name = scanner.next();
        //scanner.nextLine(); // Consume extra newline character
        
        System.out.print("Enter the new id: ");
        String deptid = scanner.next();
        
        System.out.print("Enter the new contact number : ");
        String number = scanner.next();
        
        System.out.print("Enter the new location: ");
        String location = scanner.next();

        // Assuming connect is an instance of a class managing the database connection
        connect.InitiateDB();
        
        String sql = "UPDATE DEPTS SET USERID = ?, DEPARTMENT = ?, Name = ?, CONTACT_NUMBER = ?, LOCATION = ? WHERE USERId = ?";
        PreparedStatement statement = connect.getConnection().prepareStatement(sql);
      statement.setString(1, deptid);
      statement.setString(2, department);
      statement.setString(3, name);
      statement.setString(4, number);
      statement.setString(5, location);
      statement.setString(6, id);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0)
        	System.out.println("---------------------------------------------");
            System.out.println("\tDepartment updated successfully!");
        	System.out.println("---------------------------------------------");

        admin();
    } catch (SQLException e) {
        e.printStackTrace();
    }
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

        admin();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
	

public void deleteStudent() {
	try {
		System.out.print("Enter the student id you want to delete: ");
		String id = scanner.next();
		
		connect.InitiateDB();
		String sql = "DELETE FROM DEPTS WHERE userid = ?";
		PreparedStatement statement = connect.getConnection().prepareStatement(sql);
		statement.setString(1, id);

		int rowsDeleted = statement.executeUpdate();
		if (rowsDeleted > 0) {
			System.out.println("----------------------------------------------");
			System.out.println("\tDepartment deleted successfully!");
			System.out.println("----------------------------------------------");

		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

}
