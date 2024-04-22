package Jdbc.SchoolManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import DatabaseConnectivity.DBConnection;

class ApplyLeave {
    DBConnection connect = new DBConnection();

	
    private String name;
    private String type;
    private String startDate;
    private String endDate;
    private boolean approved;

    public ApplyLeave(String name, String type, String startDate, String endDate) {
        this.name = name;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = false; // Initially leave is not approved
    }

//    public String getName() {
//        return name;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public String getStartDate() {
//        return startDate;
//    }
//
//    public String getEndDate() {
//        return endDate;
//    }
//
//    public boolean isApproved() {
//        return approved;
//    }
//
//    public void approveLeave() {
//        this.approved = true;
//    }
}

class ApplyLeaves {
	
    Connection connection;

    public ApplyLeaves(Connection connection) {
        this.connection = connection;
    }

    public void ApplyLeaves() {
    	   

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your name : ");
            String name = scanner.next();

            
            System.out.print("Enter your leave type : ");
            String type = scanner.next();

            System.out.print("Enter your start leave date : ");
            String startDate = scanner.next();

            System.out.print("Enter your end leave date : ");
            String endDate = scanner.next();

            System.out.println("--------------------------");
            System.out.println("Leave applied successfully");
            System.out.println("--------------------------");

            System.out.println("Type 'yes' if you want to view your applied leave history : ");
            String history = scanner.next();

            if (history.toLowerCase().equals("yes")) {
                System.out.println("-------------------");
                System.out.println("Leave Applications:");
                System.out.println("-------------------");

                System.out.println("Name : " + name);
                System.out.println("Leave Type : " + type);
                System.out.println("Start Date : " + startDate);
                System.out.println("End Date : " + endDate);
                System.out.println("Approved: " + (isApproved() ? "Yes\n" : "No\n"));
                
                
                DBConnection connect = new DBConnection();
                connect.InitiateDB();
                
                
              String sql = "INSERT INTO LEAVE (NAME, TYPE, START_DATE, END_DATE) VALUES (?, ?, ?, ?)";
                Object[] params = { name, type, startDate, endDate };

                int rowsInserted = connect.InsertDB(sql, params);
                if (rowsInserted > 0) {
                	System.out.println("---------------------------------------------------");
                    System.out.println("\tApplied leave was added successfully!");
                	System.out.println("---------------------------------------------------");
                }
                else
                    System.out.println("Failed to insert the product.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
       
    
    //******************************************************
    
    public void viewLeave() {
    	DBConnection connect = new DBConnection();

        Scanner scanner = new Scanner(System.in);
        try {

        	connect.InitiateDB();
            
            String sql = "SELECT * FROM LEAVE";
            PreparedStatement statement = connect.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("--------------------------------------");
            System.out.println("\tList of Applied Leaves:");
            System.out.println("--------------------------------------");
            
            System.out.println("\n---------------------------------------------------------------------------------");
            System.out.println("NAME\t\tTYPE\t\tSTART_DATE\t\tEND_DATE\t\tAPPROVE");
            System.out.println("---------------------------------------------------------------------------------");
            
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                String type = resultSet.getString("TYPE");
                String startDate = resultSet.getString("START_DATE");
                String endDate = resultSet.getString("END_DATE");
                String approve = resultSet.getString("APPROVED");

//                String location = resultSet.getString("Location");
                
                
                System.out.println(name+"\t\t"+type+"\t\t"+startDate+"\t\t"+endDate+"\t\t"+approve);
                System.out.println("---------------------------------------------------------------------------------");
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void updateLeave() {
        DBConnection connect = new DBConnection();

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the name you want to update: ");
            String nameToUpdate = scanner.next();

            System.out.println("Enter leave is approved or not : ");
            String approve = scanner.next();

            connect.InitiateDB();
            String sql = "UPDATE LEAVE SET APPROVED = ? WHERE NAME = ?";
            PreparedStatement statement = connect.getConnection().prepareStatement(sql);
            statement.setString(1, approve);
            statement.setString(2, nameToUpdate); // Corrected index to 2

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("------------------------------------------");
                System.out.println("\tLeave Approved successfully!");
                System.out.println("------------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // Method for deleting leave
    
    
    
    
    public void deleteLeave() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the leave type you want to delete: ");
            String type = scanner.next();
            
            DBConnection connection = new DBConnection(); // Creating an instance of DBConnection
            
            connection.InitiateDB(); // Initializing the database connection
            
            String sql = "DELETE FROM LEAVE WHERE TYPE = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql); // Using the connection object to prepare the statement
            statement.setString(1, type); // Setting the value of the parameter
            
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("----------------------------------------------");
                System.out.println("\tLeave deleted successfully!");
                System.out.println("----------------------------------------------");
            } else {
                System.out.println("No leave found with the specified type.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }

    
   
    private boolean isApproved() {
		// TODO Auto-generated method stub
		return false;
	}

	public void viewApprovedLeaves() {
        // Logic to view approved leaves
    }
}





 class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Connection connection = null; // Initialize your database connection

//        ApplyLeaves leave = new ApplyLeaves(connection);
//
//        int choice;
//        do {
//            System.out.println("1. Apply for Leave");
//            System.out.println("2. View Approved Leaves");
//            System.out.println("3. Exit");
//
//            System.out.print("Enter your choice: ");
//            choice = scanner.nextInt();
//
//            switch (choice) {
//                case 1:
//                    leave.ApplyLeaves();
//                    break;
//                case 2:
//                    leave.viewApprovedLeaves();
//                    break;
//                case 3:
//                    System.out.println("Exiting...");
//                    break;
//                default:
//                    System.out.println("Invalid choice.");
//                    break;
//            }
//        } while (choice != 3);
    }
}

//import java.util.Scanner;
//
//import javax.management.InvalidApplicationException;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//class ApplyLeave {
//    private String name;
//    private String type;
//    private String startDate;
//    private String endDate;
//    private boolean approved;
//
//    public ApplyLeave(String name, String type, String startDate, String endDate) {
//        this.name = name;
//        this.type = type;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.approved = false; // Initially leave is not approved
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public String getStartDate() {
//        return startDate;
//    }
//
//    public String getEndDate() {
//        return endDate;
//    }
//
////    public boolean isApproved() {
////        return approved;
////    }
////
//    public void approveLeave() {
//        this.approved = true;
//    }
//}
//
//class ApplyLeaves {
//	
//    Connection connection;
//
//
//    private List<LeaveApplication> leaveApplications;
//
//    public ApplyLeaves() {
//        this.leaveApplications = new ArrayList<>();
//    }
//    public void ApplyLeaves() {
//    	try {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter your name : ");
//        String name = scanner.next();
//
//        System.out.print("Enter your leave type : ");
//        String type = scanner.next();
//
//        System.out.print("Enter your start leave date : ");
//        String startDate = scanner.next();
//
//        System.out.print("Enter your end leave date : ");
//        String endDate = scanner.next();
//
//        System.out.println("--------------------------");
//        System.out.println("Leave applied successfully");
//        System.out.println("--------------------------");
//        
//        System.out.println("Type yes if you want to view your applied leave history : ");
//        String history = scanner.next();
//
//        if(history.toLowerCase().equals("yes")) {
//        	    	System.out.println("-------------------");
//        	        System.out.println("Leave Applications:");
//        	        System.out.println("-------------------");
//        	        
//        	        System.out.println("Name : "+name);
//        	        System.out.println("Leave Type : "+type);
//        	        System.out.println("Start Date : "+startDate);
//        	        System.out.println("End Date : "+endDate);
//        	        System.out.print("Approved : ");
//        	        System.out.println(isApproved() != null ? "Yes\n" : "No\n");
//        	    
//
//                    String sql = "INSERT INTO Leave (NAME, TYPE, START_DATE, END_DATE) VALUES (?, ?, ?, ?)";
//                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                        statement.setString(1, name);
//                        statement.setString(2, type);
//                        statement.setString(3, startDate);
//                        statement.setString(4, endDate);
//                        
//
//                        int result = statement.executeUpdate();
//                        if (result > 0) {
//                            System.out.println("Leave was inserted to database Successfully");
//                        } else {
//                            System.out.println("Failed to insert leave..");
//                        }
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                catch (InvalidApplicationException e) {
//                    System.err.println("Error: " + e.getMessage());
//                }
//            }
//
//        
//       private String isApproved() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//       
//    public void viewApprovedLeaves() {
//        System.out.println("Approved Leaves:");
//        for (LeaveApplication application : leaveApplications) {
//            if (application.isApproved()) {
//                System.out.println("Name: " + application.getName());
//                System.out.println("Leave Type: " + application.getType());
//                System.out.println("Start Date: " + application.getStartDate());
//                System.out.println("End Date: " + application.getEndDate());
//                System.out.println("--------------------------");
//               // handleLeave();
//                
//               // Departments.department();
//
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//            LeaveManagement leave = new LeaveManagement();
//
//            int choice;
//            do {
//                System.out.println("1. View Leave Applications");
//                System.out.println("2. Exit");
//
//                System.out.print("Enter your choice: ");
//                choice = scanner.nextInt();
//
//                switch (choice) {
//                    case 1:
//                        leave.viewLeaves();
//                        break;
//                    case 2:
//                    	 System.out.println("Exiting...");
//                         break;
//                    default:
//                        System.out.println("Invalid choice.");
//                        break;
//                }
//            } while (choice != 2);
//        }
//    }