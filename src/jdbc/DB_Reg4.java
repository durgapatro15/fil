package jdbc;
import java.util.*;
import java.sql.*;

public class DB_Reg4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DbConn db = new DbConn();
        
        while (true) {
            System.out.println("\n====== Employee Management System ======");
            System.out.println("1. New Registration");
            System.out.println("2. Search Employee by emp_id");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerNewUser(sc, db);
                    break;
                case 2:
                    System.out.print("Enter Employee ID: ");
                    String emp_id = sc.nextLine().trim();
                    handleExistingUser(emp_id, sc, db);
                    break;
                case 3:
                    System.out.println("Exiting program. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Handles existing employee data (Update/Delete)
    private static void handleExistingUser(String emp_id, Scanner sc, DbConn db) {
        try {
            String checkQuery = "SELECT * FROM Reg WHERE emp_id = ?";
            db.pstat = db.conn.prepareStatement(checkQuery);
            db.pstat.setString(1, emp_id);
            db.rs = db.pstat.executeQuery();

            if (!db.rs.next()) {
                System.out.println("Error: Employee ID not found.");
                return;
            }

            System.out.println("\n=== Employee Record Found ===");
            System.out.println("1. Name: " + db.rs.getString("name"));
            System.out.println("2. Email: " + db.rs.getString("email"));
            System.out.println("3. Mobile: " + db.rs.getString("mobile"));
            System.out.println("4. Password: (hidden)");

            System.out.println("\nChoose an option:");
            System.out.println("1. Update Employee");
            System.out.println("2. Delete Employee");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    updateEmployee(emp_id, sc, db);
                    break;
                case 2:
                    deleteEmployee(emp_id, db);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    // Allows user to update specific fields
 // Allows user to update specific fields
    private static void updateEmployee(String emp_id, Scanner sc, DbConn db) {
        boolean continueUpdating = true;

        while (continueUpdating) {
            try {
                System.out.println("\nSelect what you want to update:");
                System.out.println("1. Name");
                System.out.println("2. Email");
                System.out.println("3. Mobile");
                System.out.println("4. Password");
                System.out.println("5. Exit Update");
                int option = sc.nextInt();
                sc.nextLine(); // Consume newline

                String field = "";
                String newValue = "";

                switch (option) {
                    case 1:
                        System.out.print("Enter new Name: ");
                        field = "name";
                        newValue = sc.nextLine();
                        break;
                    case 2:
                        System.out.print("Enter new Email: ");
                        field = "email";
                        newValue = sc.nextLine();
                        break;
                    case 3:
                        System.out.print("Enter new Mobile (10 digits): ");
                        field = "mobile";
                        newValue = sc.nextLine();
                        break;
                    case 4:
                        System.out.print("Enter new Password: ");
                        field = "password";
                        newValue = sc.nextLine();
                        break;
                    case 5:
                        System.out.println("Exiting update menu.");
                        return;
                    default:
                        System.out.println("Invalid choice! Try again.");
                        continue;
                }

                // Update query
                String updateQuery = "UPDATE Reg SET " + field + " = ? WHERE emp_id = ?";
                db.pstat = db.conn.prepareStatement(updateQuery);
                db.pstat.setString(1, newValue);
                db.pstat.setString(2, emp_id);
                db.pstat.executeUpdate();
                System.out.println("Successfully updated " + field + "!");
                
                // Ask if the user wants to update anything else
                while (true) {
                    System.out.print("Do you want to update anything else? (yes/no): ");
                    String response = sc.nextLine().trim().toLowerCase();
                    if (response.equals("yes")) {
                        break;  // Continue updating
                    } else if (response.equals("no")) {
                        continueUpdating = false;
                        System.out.println("Update process completed.");
                        return;
                    } else {
                        System.out.println("Invalid input! Please enter 'yes' or 'no'.");
                    }
                }

            } catch (SQLException e) {
                System.out.println("Database Error: " + e.getMessage());
            }
        }
    }

    private static boolean isMobileExists(String newValue, DbConn db) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean isEmailExists(String newValue, DbConn db) {
		// TODO Auto-generated method stub
		return false;
	}

	// Deletes an employee record
    private static void deleteEmployee(String emp_id, DbConn db) {
        try {
            String deleteQuery = "DELETE FROM Reg WHERE emp_id = ?";
            db.pstat = db.conn.prepareStatement(deleteQuery);
            db.pstat.setString(1, emp_id);

            int rows = db.pstat.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee record deleted successfully.");
            } else {
                System.out.println("Failed to delete employee record.");
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    // Registers a new user
    private static void registerNewUser(Scanner sc, DbConn db) {
        try {
            System.out.print("Enter Name: ");
            String nm = sc.nextLine();
            System.out.print("Enter Password: ");
            String pass = sc.nextLine();
            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            System.out.print("Enter Mobile (10 digits): ");
            String mobile = sc.nextLine();

            if (isEmailExists(email, db)) {
                System.out.println("Error: Email is already registered.");
                return;
            }

            if (isMobileExists(mobile, db)) {
                System.out.println("Error: Mobile number is already registered.");
                return;
            }

            String emp_id = generateEmpId(db);

            String insertQuery = "INSERT INTO Reg (emp_id, name, password, email, mobile) VALUES (?, ?, ?, ?, ?)";
            db.pstat = db.conn.prepareStatement(insertQuery);
            db.pstat.setString(1, emp_id);
            db.pstat.setString(2, nm);
            db.pstat.setString(3, pass);
            db.pstat.setString(4, email);
            db.pstat.setString(5, mobile);
            db.pstat.executeUpdate();

            System.out.println("Registration Successful! Remember EID - " + emp_id);

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    private static String generateEmpId(DbConn db) throws SQLException {
        String query = "SELECT emp_id FROM Reg ORDER BY emp_id DESC LIMIT 1";
        db.stat = db.conn.createStatement();
        db.rs = db.stat.executeQuery(query);
        return db.rs.next() ? String.format("E%04d", Integer.parseInt(db.rs.getString("emp_id").substring(1)) + 1) : "E0001";
    }
}
