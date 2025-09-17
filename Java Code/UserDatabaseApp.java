import java.sql.*;
import java.util.Scanner;

public class UserDatabaseApp {
    // DB Connection info
    static final String DB_URL = "jdbc:mysql://userdb1.c1aogm4k237g.eu-north-1.rds.amazonaws.com:3306/userdb1";
    static final String DB_USER = "admin";
    static final String DB_PASSWORD = "AWS123456"; // replace with your MySQL root password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    addUser(scanner);
                    break;
                case 2:
                    viewUsers();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void addUser(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter address: ");
            String address = scanner.nextLine();

            System.out.print("Enter contact number: ");
            String contact = scanner.nextLine();

            String sql = "INSERT INTO users (name, address, contact) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, contact);
            stmt.executeUpdate();

            System.out.println("User added successfully!");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static void viewUsers() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM users";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n--- Users ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Address: " + rs.getString("address") +
                        ", Contact: " + rs.getString("contact"));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}

