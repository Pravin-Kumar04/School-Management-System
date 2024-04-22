package Jdbc.SchoolManagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import javax.management.InvalidApplicationException;
import DatabaseConnectivity.InsertData;

class AdminRegistration {
    Connection connection;
    InsertData insert = new InsertData();

    public void adminReg() {
//        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter admin username : ");
            String userName = scanner.nextLine();
//            if (!isValidUsername(userName)) {
//                throw new InvalidApplicationException("Invalid username format.");
//            }

            System.out.print("Enter admin password : ");
            String userPass = scanner.nextLine();
//            if (!isValidPassword(userPass)) {
//                throw new InvalidApplicationException("Invalid password format.");
//            }

            System.out.print("Enter admin email : ");
            String userMail = scanner.nextLine();
//            if (!isValidEmail(userMail)) {
//                throw new InvalidApplicationException("Invalid email format.");
//            }
            
            System.out.print("Enter admin standard if not type 'N' : ");
            String userStandard = scanner.nextLine();
            
            System.out.print("Enter admin ID : ");
            String userID = scanner.next();
            
            System.out.print("Enter admin DOB : ");
            String userDOB = scanner.next();

            System.out.print("Enter your contact number : ");
            String userContact = scanner.next();
//            if (!isValidContactNumber(userContact)) {
//                throw new InvalidApplicationException("Invalid contact number format.");
//            }
            
            System.out.print("Enter your location : ");
            String userLocation = scanner.next();

            connection = insert.ConnectDB(); // Initialize connection
            System.out.println("------------------------------");
            System.out.println("Admin Registration Successful");
            System.out.println("------------------------------");

            String sql = "INSERT INTO USER_INFO (USERNAME, USERID, USERPASSWORD, USERSTANDARD, USERMAIL, USERDOB, USERCONTACT, USERLOCATION) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, userName);
                statement.setString(2, userID);
                statement.setString(3, userPass);
                statement.setString(4, userStandard);
                statement.setString(5, userMail);
                statement.setString(6, userDOB);
                statement.setString(7, userContact);
                statement.setString(8, userLocation);

                int result = statement.executeUpdate();
                if (result > 0) {
                    System.out.println("User was inserted Successfully");
                } else {
                    System.out.println("Failed to insert user");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
//        } catch (InvalidApplicationException e) {
//            System.err.println("Error: " + e.getMessage());
//        }
    }

    public boolean isValidUsername(String adminName) {
        String adminNameRegex = "^[,1a-zA-Z0-9_-]{35}$";
        return adminName.matches(adminNameRegex);
    }

    public boolean isValidPassword(String adminPass) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&] 8,}$";
        return adminPass.matches(passwordRegex);
    }

    public boolean isValidEmail(String adminMail) {
        String emailRegex = "^[\\w.-]+@gmail\\.com$";
        return adminMail.matches(emailRegex);
    }

    public boolean isValidContactNumber(String adminNumber) {
        String contactNumberRegex = "^[6-9]\\d{9}$";
        return adminNumber.matches(contactNumberRegex);
    }
}