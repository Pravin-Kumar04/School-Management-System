package Jdbc.SchoolManagement;

import java.util.Scanner;

public class SchoolManagementSystem {
	
    
    public void adminActions() {

    	Scanner objUser = new Scanner(System.in);
        
        System.out.println("\n---------------");
        System.out.println("Admin Actions:");
        System.out.println("---------------");
        System.out.println("\n1. Admin Registration");
        System.out.println("2. Admin Login");
        System.out.println("3. Logout");
        System.out.print("Choose action: ");
        int adminAction = objUser.nextInt();
        
        switch (adminAction) {
            case 1:
            	AdminRegistration admin = new AdminRegistration();
                admin.adminReg();
                adminActions();
                //main(null);
                break;
            case 2:
            	AdminLogin login = new AdminLogin();
                login.adminLogin();
                Admin dept = new Admin();
                dept.admin();
                //main(null);
                break;
            case 3:
                System.out.println("Logging out...");
                main(null); // Redirect to choose user type
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
                System.exit(0);
        }
        objUser.close();
    }
    
    
    public void studentActions() {
    	//StudentLogin student = new StudentLogin();
        Scanner objStud = new Scanner(System.in);
        int studentChoice;
//        do {
        System.out.println("----------------");
        System.out.println("Student Actions:");
        System.out.println("----------------");
        System.out.println("1. Student Registration");
        System.out.println("2. Student Login");
        System.out.println("3. Logout");
        System.out.println("Choose action: ");
        studentChoice = objStud.nextInt();
        
        
        if(studentChoice==1) {
        	StudentRegistration studentReg = new StudentRegistration();
            studentReg.studentReg();
            studentActions();
        }
        else if(studentChoice==2) {
        	StudentLogin studentLogin = new StudentLogin();
            studentLogin.StudentLogin();
           // studentLogin.studentDetails();
           
        }
        else if(studentChoice==3) {
        	System.out.println("Logging out...");
            main(null);
        }
        else {
        	System.out.println("Invalid choice. Exiting...");
            System.exit(0);
        }
        objStud.close();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SchoolManagementSystem action = new SchoolManagementSystem();
        System.out.println("------------------------------------");
        System.out.println("Welcome to School Management System");
        System.out.println("------------------------------------");
        System.out.println("\n1. Admin");
        System.out.println("2. Student");
        System.out.println("3. Exit");
        System.out.print("\nChoose User Type: ");
        int userType = scanner.nextInt();
        
        switch (userType) {
            case 1:
            	action.adminActions();
                break;
            case 2:
            	
                action.studentActions();
                break;
            case 3:
                System.out.println("Exiting...");
               // System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. \nExiting...");
                System.exit(0);
        }
        scanner.close();
    }
}
