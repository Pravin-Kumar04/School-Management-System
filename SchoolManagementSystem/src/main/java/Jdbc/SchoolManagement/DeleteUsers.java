package Jdbc.SchoolManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import DatabaseConnectivity.DBConnection;

class DeleteUsers {
	
	public Connection connection;
	  public Connection getConnection() {
	      return connection;
	  }
	  DBConnection connect = new DBConnection(); 
	  Scanner scanner = new Scanner(System.in);
		
	  
	  public void userAccounts() { 
		  
		  
		  int mainChoice;
		  do {
		  System.out.println("User Accounts");
		  System.out.println("1. View User");
		  System.out.println("2. Delete User");
		  System.out.println("3. Exit");
		  System.out.print("Choose your Option: ");
		  mainChoice=scanner.nextInt();
		  switch (mainChoice) {
		  case 1:
		 	 viewUser();
		 	 break;
		  case 2:
		 	 deleteUser();
		 	 break;
		  case 3:
		 	 System.out.println("Exiting...");
		 	 break;
		  default:
		 	 System.out.println("Invalid option. Please choose a valid option.");
		 	 break;
		  }
		  } while (mainChoice != 3);}
	  
	  
	  public void viewUser() {
		    try {
		        connect.InitiateDB();

		        String sql = "SELECT * FROM USER_INFO";
		        PreparedStatement statement = connect.getConnection().prepareStatement(sql);
		        ResultSet resultSet = statement.executeQuery();

		        System.out.println("--------------------------------------");
		        System.out.println("\tList of USER ACCOUNT:");
		        System.out.println("--------------------------------------");

		        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		        System.out.println("USERNAME\tUSERID\t\tUSERPASS\t\tUSERSTANDARD\t\tUSERMAIL\t\tUSERDOB\t\t\t\tUSERCONTACT\t\tUSERLOCATION");
		        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		        while (resultSet.next()) {
		            String userName = resultSet.getString("USERNAME");
		            String userID = resultSet.getString("USERID");
		            String userPass = resultSet.getString("USERPASSWORD");
		            String userStandard = resultSet.getString("USERSTANDARD");
		            String userMail = resultSet.getString("USERMAIL");
		            String userDOB = resultSet.getString("USERDOB");
		            String userContact = resultSet.getString("USERCONTACT");
		            String userLocation = resultSet.getString("USERLOCATION");

		            System.out.println(userName + "\t\t" + userID + "\t\t" + userPass + "\t\t" + userStandard + "\t\t\t" + userMail + "\t\t" + userDOB + "\t\t" + userContact + "\t\t" + userLocation);
			        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		        }

		        resultSet.close();
		        statement.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

		 	
		 public void deleteUser() {
		 	try {
		 		System.out.print("Enter user id you want to delete: ");
		 		String ID = scanner.next();
		 		
		 		connect.InitiateDB();
		 		String sql = "DELETE FROM USER_INFO WHERE USERID = ?";
		 		PreparedStatement statement = connect.getConnection().prepareStatement(sql);
		 		statement.setString(1, ID);

		 		int rowsDeleted = statement.executeUpdate();
		 		if (rowsDeleted > 0) {
		 			System.out.println("----------------------------------------------");
		 			System.out.println("\tUser deleted successfully!");
		 			System.out.println("----------------------------------------------");

		 		}
		 	} catch (SQLException e) {
		 		e.printStackTrace();
		 	}
		 }

		 }