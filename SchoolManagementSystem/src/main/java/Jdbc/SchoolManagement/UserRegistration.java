package Jdbc.SchoolManagement;

import java.lang.Exception;
import java.util.*;
import DatabaseConnectivity.*;
import java.sql.SQLException;
import java.util.*;
class UserRegistration 
{
	
	InsertData insert = new InsertData();
	
	public void userReg() {
		 try 
		 {		
			 Scanner user = new Scanner(System.in);
			 System.out.print("Enter username : ");
			 String userName = user.nextLine();
         
	         System.out.print("Enter user ID : ");
	         int userId = user.nextInt();
	         
	         System.out.print("Enter user password : ");
	         String userPass = user.nextLine();
	         
	         System.out.print("Enter your standard : ");
	         String userStandard = user.nextLine();
	         user.nextLine();
	         
	         System.out.print("Enter your email : ");
	         String userMail = user.nextLine();
	         
	         System.out.print("Enter DOB : ");
	          String userDOB = user.nextLine();
	         
	         System.out.print("Enter your contact number : ");
	         String userContact = user.nextLine();
         
	         System.out.print("Enter your location : ");
	         String userLocation = user.nextLine();
	         
	         insert.ConnectDB();
	         
	         String sql = "INSERT INTO user_info (USERNAME, USERID, USERPASS, USERSTANDARD, USERMAIL, USERDOB, USERCONTACT, USERLOCATION) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	         Object[] params = { userName, userPass, userStandard, userMail, userDOB, userContact, userLocation};

	         int rowsInserted = insert.InsertDB(sql, params);
	         if (rowsInserted > 0) 
	         {
	             System.out.println("User was inserted successfully!");
	         }
        
		 } catch (Exception e) {
//			 throw new RuntimeException(e);
             e.printStackTrace();
         }

         
         System.out.println("----------------------------");
         System.out.println("User Registration Completed");
         System.out.println("----------------------------");
         
	}
}
