	package Jdbc.SchoolManagement;
	
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Scanner;
	
	class AdminLogin {
	    private Connection connection; 
	
	    public AdminLogin() {
	       
	        connectDB();
	    }
	
	    public void adminLogin() {
	        System.out.println("-------------------------------------");
	        System.out.println("\t Welcome Admin");
	        System.out.println("-------------------------------------");
	        log();
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
	
	    public void log() {
	        Scanner scan = new Scanner(System.in);
	        System.out.print("Enter the username: ");
	        String userName = scan.next();
	        System.out.print("Enter the password: ");
	        String userPass = scan.next();
	        logMain(userName,userPass);
	    }
	    public void logMain(String name, String password) {
	
	        if (canUserLogin(name, password)) {
		        System.out.println("-------------------------------------");
	            System.out.println("\tLogin successful!");
		        System.out.println("-------------------------------------");
		        Admin admin = new Admin();
		        admin.admin();
	        } else {
	            System.out.println("Login failed. Invalid username or password.");
	            log();
	        }
	    }
	
	    public boolean canUserLogin(String userid, String password) {
	        boolean isValidUser = false;
	
	        try {
	            String sql = "SELECT * FROM user_info WHERE userName = ? AND userPassword = ?";
	            PreparedStatement statement = connection.prepareStatement(sql);
	
	            statement.setString(1, userid);
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
	
	  
	    }