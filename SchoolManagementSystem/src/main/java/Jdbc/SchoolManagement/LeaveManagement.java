package Jdbc.SchoolManagement;

import java.util.*;
class LeaveManagement {
	
	 public void handleLeave() {
       Scanner scanner = new Scanner(System.in);
       System.out.println("1. View Leave");
//       System.out.println("2. Approve Leave");
       System.out.println("2. Delete Leaves");
       System.out.println("3. Logout");
       System.out.println("Choose your option : ");
       int option = scanner.nextInt();
       switch(option) {
       
       case 1:
    	   ApplyLeaves view = new ApplyLeaves(null);
    	   view.viewLeave();
    	   handleLeave();
    	   break;
    	   
//       case 2:
//    	   ApplyLeaves approve = new ApplyLeaves(null);
//    		 approve.updateLeave();
//    		 handleLeave();
//    		 break;
       case 2:
    	   ApplyLeaves delete = new ApplyLeaves(null);
    	   delete.deleteLeave();
    	   handleLeave();
    	   break;
    	   
  		 
       case 3 :
    	   System.out.println("Exiting......");
    	   System.exit(0);
    	   
  		 default :
  			 System.out.println("Invalid Option....");
       }       
//       if(option==1) {
//       	viewLeaves();
//       	handleLeave();
//       }
//       else if(option==2) {
//       	approveLeave();
//       	handleLeave();
//       }
//       else if(option==3) {
//       	viewApprovedLeaves();
//       	handleLeave();
//       }
//       else {
//       	 System.out.println("Invalid choice. Exiting...");
//          System.exit(0);
       }     
   }






//import java.util.Scanner;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//class LeaveApplication {
//	
//	
//    private String name;
//    private String type;
//    private String startDate;
//    private String endDate;
//    private boolean approved;
//    private Connection connection;
//    public Connection ConnectDB() {
//        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
//        String username = "DATABASE";
//        String password = "6246";
//
//        try {
//            Class.forName("oracle.jdbc.OracleDriver");
//            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "DATABASE", "6246");
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//  		return connection;
//    }
//    
//    public int InsertDept(String sql, Object[] params) {
//        int result = 0;
//        try {
//            PreparedStatement statement = connection.prepareStatement(sql);
//            for (int i = 0; i < params.length; i++) {
//                statement.setObject(i + 1, params[i]); // Corrected index
//            }
//            result = statement.executeUpdate();
//            statement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public LeaveApplication(String name, String type, String startDate, String endDate) {
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
//    public boolean isApproved() {
//        return approved;
//    }
//
//    public void approveLeave() {
//        this.approved = true;
//    }
//}
//
//class LeaveManagement {
//
//    private List<LeaveApplication> leaveApplications;
//
//    public LeaveManagement() {
//        this.leaveApplications = new ArrayList<>();
//    }
//
//    public void LeaveManagement() {
//    	
//    	handleLeave();
////        Scanner scanner = new Scanner(System.in);
////
////        System.out.print("Enter your name : ");
////        String name = scanner.next();
////
////        System.out.print("Enter your leave type : ");
////        String type = scanner.next();
////
////        System.out.print("Enter your start leave date : ");
////        String startDate = scanner.next();
////
////        System.out.print("Enter your end leave date : ");
////        String endDate = scanner.next();
////
////        LeaveApplication application = new LeaveApplication(name, type, startDate, endDate);
////        leaveApplications.add(application);
////
////        System.out.println("--------------------------");
////        System.out.println("Leave applied successfully");
////        System.out.println("--------------------------");
////        handleLeave();
//        //Departments.department();
//    }
//        public void handleLeave() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("1. View Leave");
//        System.out.println("2. Approve Leave");
//        System.out.println("3. Approved Leaves");
//        System.out.println("Choose your option : ");
//        int option = scanner.nextInt();
//        
//        if(option==1) {
//        	viewLeaves();
//        	handleLeave();
//        }
//        else if(option==2) {
//        	approveLeave();
//        	handleLeave();
//        }
//        else if(option==3) {
//        	viewApprovedLeaves();
//        	handleLeave();
//        }
//        else {
//        	 System.out.println("Invalid choice. Exiting...");
//           System.exit(0);
//        }     
//    }
//    public void viewLeaves() {
//    	System.out.println("-------------------");
//        System.out.println("Leave Applications:");
//        System.out.println("-------------------");
//        for (LeaveApplication application : leaveApplications) {
//            System.out.println("Name: " + application.getName());
//            System.out.println("Leave Type: " + application.getType());
//            System.out.println("Start Date: " + application.getStartDate());
//            System.out.println("End Date: " + application.getEndDate());
//            System.out.println("Approved: " + (application.isApproved() ? "Yes\n" : "No\n"));
//            //System.out.println("--------------------------");
//            handleLeave();
//          //  Departments.department();
//        }
//    }
//
//    public void approveLeave() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("-------------------");
//        System.out.println("Leave Applications:");
//        System.out.println("-------------------");
//        
//        for (int i = 0; i < leaveApplications.size(); i++) {
//            LeaveApplication application = leaveApplications.get(i);
//            System.out.println((i + 1) + ". Name: " + application.getName());
//            System.out.println("   Leave Type: " + application.getType());
//            System.out.println("   Start Date: " + application.getStartDate());
//            System.out.println("   End Date: " + application.getEndDate());
//            System.out.println("--------------------------");
//            
//            handleLeave();
//            
//           // Departments.department();
//
//        }
//
//        System.out.print("Enter the number of leave to approve: ");
//        int leaveNumber = scanner.nextInt();
//
//        if (leaveNumber >= 1 && leaveNumber <= leaveApplications.size()) {
//            LeaveApplication selectedLeave = leaveApplications.get(leaveNumber - 1);
//            selectedLeave.approveLeave();
//            System.out.println("Leave approved successfully.");
//            handleLeave();
//        } else {
//            System.out.println("Invalid leave number.");
//        }
//    }
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
//                handleLeave();
//                
//               // Departments.department();
//
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter username: ");
//        String username = scanner.nextLine();
//
//        System.out.print("Enter password: ");
//        String password = scanner.nextLine();
//
//            System.out.println("Login successful. You have admin access.\n");
//            LeaveManagement leave = new LeaveManagement();
//
//            int choice;
//            do {
//                System.out.println("1. View Leave Applications");
//                System.out.println("2. Approve Leave");
//                System.out.println("3. View Approved Leaves");
//                System.out.println("4. Exit");
//                System.out.print("Enter your choice: ");
//                choice = scanner.nextInt();
//
//                switch (choice) {
//                    case 1:
//                        leave.viewLeaves();
//                        break;
//                    case 2:
//                        leave.approveLeave();
//                        break;
//                    case 3:
//                        leave.viewApprovedLeaves();
//                        break;
//                    case 4:
//                        System.out.println("Exiting...");
//                        break;
//                    default:
//                        System.out.println("Invalid choice.");
//                        break;
//                }
//            } while (choice != 4);
//        } 
//    }
//
