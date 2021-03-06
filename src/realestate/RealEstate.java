/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realestate;

import java.lang.reflect.Constructor;
import java.sql.*;
import java.util.Scanner;

public class RealEstate {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        // TODO code application logic here
        //identify the townhouse, tenant or apartment; then the second input is the address/property
        String myDriver = "com.mysql.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/mynewdatabase";
        Class.forName(myDriver);
        // the mysql insert statement

        Connection conn = DriverManager.getConnection(myUrl, "kunaalbajwa", "Demonruler1");
        //I have set up each method
        String Username;
        Username = login(conn);

//call text menu:
        if (Username.equals("admin")) {
            adminMainMenu(conn);
        } else {
            userMainMenu(conn, Username);
        }
        conn.close();
    }

    //**can you explain differences between inputs and querys, I know they are not but can you explain the difference....maybe looking at it too hard
//query is addressing thea database; input is addressing the user^^
    
//menu will exist here below:
    public static void adminMainMenu(Connection conn) throws SQLException, ClassNotFoundException, NoSuchMethodException {
        boolean exit = false;
        while (!exit) {

            System.out.println("Please select an option.");
            System.out.println("1. Check Tenants");
            System.out.println("2. Bill Rent");
            System.out.println("3. Clear Database");
            System.out.println("4. Change Password");
            System.out.println("5. Exit");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            switch (input) {

                case "1":
                    String query = "SELECT * from tenant_info WHERE Rent> '0'";//it doesnt take teh admin username since they are 0

                    Statement Stmt = conn.createStatement();
                    ResultSet Rs = Stmt.executeQuery(query);
                    System.out.println("Name\t\tAddress\t\tRent\tRent Due");
                    System.out.println("----------------------------------------------------");

                    while (Rs.next()) {
                        System.out.print(Rs.getString("Name") + "\t");
                        System.out.print(Rs.getString("Property") + "\t");
                        System.out.print(Rs.getDouble("Rent") + "\t");
                        System.out.print(Rs.getDouble("RentDue") + "\n\n");
//RETURN HERE fix formatting later on (so that it prints out clearly?
                    }

                    break;        //call connection and print all data

                case "2":
                    System.out.print("Enter tenant name to bill: ");
// Enter tenant to bill:____ ; Make sure to type out full name with underscores
                    input = scanner.nextLine();
                    //get name from admin--done; search for name and grab rent and rentdue--done ; then add rent to rentdue--done; update databse--done
                    query = "Select * from tenant_info where Name= '" + input + "'";
                    Stmt = conn.createStatement();
                    Rs = Stmt.executeQuery(query);
                    if (Rs.next()) {
                        //**walk me through the changing of values being changed and why
                        query = "Update tenant_info Set RentDue ='" + String.valueOf(Rs.getDouble("Rent") + Rs.getDouble("RentDue")) + "' Where Name= '" + input + "'";
                        Stmt = conn.createStatement();
                        Stmt.execute(query);
                        System.out.println("Tenant successfully billed.");
                    } else {
                        System.out.println("User not found");
                    }
                    break;
                case "3":
                    System.out.println("If you are sure you want to do this, type YES");
                    input = scanner.nextLine();
                    if (input.equals("YES")) {

                        cleanSlate(conn); //initiates wipe protocol
                        Townhouse Sphinx = new Townhouse("Sphinx", "East_Vcompound_St", 4000, 666, 1200.00, conn);
                        Apartment DemonWorld = new Apartment("DemonWorld", "MakaiRealm_Ln", 1600, 424, 800.00, conn);
                        Tenant Son_Goku = new Tenant("Son_Goku", "DemonWorld", "9001", conn);
                        Tenant Meliodas = new Tenant("Meliodas", "Sphinx", "9002", conn);
                        Tenant Yusuke = new Tenant("Yusuke", "DemonWorld", "9003", conn);
                        Tenant Vash = new Tenant("Vash", "Sphinx", "9004", conn);
                        System.out.println("Database wiped.");
                    }
                    break;
                case "4":
                    System.out.println("Which user's password do you want to change?");
                    input=scanner.nextLine();
                    query = "Select * from tenant_info where Name= '" + input + "'";
                    Stmt = conn.createStatement();
                    Rs = Stmt.executeQuery(query);
                    
                    if (Rs.next()) {
                       String username=Rs.getString("Name"); 
                        passwordChange(conn, username);
                    }
                    else
                        System.out.println("Invalid username");
                    break;

                case "5":
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid request.");

            }

        }
    }
    public static void passwordChange(Connection conn, String username) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        
        //enter pass, enter new pass, re-enter pass
          String query = "SELECT Password from tenant_info WHERE Name= '" + username + "'";
//this is to check if the name is already in database^^
        Statement Stmt = conn.createStatement();
        ResultSet Rs = Stmt.executeQuery(query);
        Rs.next();
        String correctpass=Rs.getString("Password");
        for (int p = 1; p <= 3; p++) { //here is the count and kickback
            //gives 3 shots, this kicks them back
            System.out.print("Enter current password: ");
            String oldpass = scanner.nextLine();
            System.out.print("Enter new password: ");
            String newpass1= scanner.nextLine();
            System.out.print("Re-enter new password: ");
            String newpass2= scanner.nextLine();

            if (correctpass.equals(oldpass) && newpass1.equals(newpass2)) {
                query = "Update tenant_info SET Password='" + newpass1 + "' WHERE Name='" + username + "'";
                Stmt = conn.createStatement();
                Stmt.execute(query);
                System.out.println("Password changed happily."); 
        break;

            }
           else
            System.out.println("Invalid Password, " + String.valueOf(3 - p) + " attempts left.");
        }
        
       

    }


    public static void userMainMenu(Connection conn, String name) throws SQLException, ClassNotFoundException, NoSuchMethodException {
        boolean exit = false;
        System.out.println("Welcome " + name + "!");
        while (!exit) {

            System.out.println("Please select an option.");
            System.out.println("1. Check Rent Due");
            System.out.println("2. Pay Rent");
            System.out.println("3. Change Password");
            System.out.println("4. Exit");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            switch (input) {

                case "1":
                    String query = "SELECT RentDue from tenant_info WHERE Name='" + name + "'";//it doesnt take teh admin username since they are 0

                    Statement Stmt = conn.createStatement();
                    ResultSet Rs = Stmt.executeQuery(query);

                    if (Rs.next()) {
                        System.out.print("Your rent due is: $");
                        System.out.print(Rs.getDouble("RentDue") + "\n\n");
//RETURN HERE fix formatting later on (so that it prints out clearly...if you want
                    }

                    break;        

                case "2":
                    System.out.print("How much are you going to pay? ");
// Enter tenant to bill:____
                    input = scanner.nextLine();
                    double payment = Double.valueOf(input);

                    query = "Select RentDue from tenant_info where Name= '" + name + "'";
                    Stmt = conn.createStatement();
                    Rs = Stmt.executeQuery(query);
                    if (Rs.next() && payment > 0) {
                        query = "Update tenant_info Set RentDue ='" + String.valueOf(Rs.getDouble("RentDue") - payment) + "' Where Name= '" + name + "'";
                        Stmt = conn.createStatement();
                        Stmt.execute(query);
                        System.out.println("Payment of $" + input + " entered successfully.");
                    } else {
                        System.out.println("Invalid payment. Please try again.");
                    }
                    break;
                case "3": 
                    passwordChange(conn, name);
                    break;
                case "4":
                    exit = true;
                    break;
//if it is true the while loop will close 
                default:
                    System.out.println("Invalid request.");

            }

        }
    }

    private static String login(Connection conn) throws SQLException {
        String Username = "";
        System.out.println("Please login");
        while (Username.equals("")) {
            System.out.print("Username: ");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();

            String query = "SELECT Name from tenant_info WHERE Name= '" + username + "'";
//this is to check if the name is already in database^^
            Statement Stmt = conn.createStatement();
            ResultSet Rs = Stmt.executeQuery(query);

            if (Rs.next() && username.equals(Rs.getString("Name"))) {
                Username = username;
            } else {
                System.out.println("This is not a username...");
            }
        }
        //give a query for  password and a check for the passwords from the databases; keep a count and then kick them back  
        System.out.println("Please enter password ");
        String query = "SELECT Password from tenant_info WHERE Name= '" + Username + "'";
//this is to check if the name is already in database^^
        Statement Stmt = conn.createStatement();
        ResultSet Rs = Stmt.executeQuery(query);
        Rs.next();

        String Password = Rs.getString("Password");
        for (int p = 1; p <= 3; p++) { //here is the count and kickback
            //gives 3 shots, this kicks them back
            System.out.print("Password: ");
            Scanner scanner = new Scanner(System.in);
            String password = scanner.nextLine();

            if (password.equals(Password)) {
                return Username;

            }
            System.out.println("Invalid Password, " + String.valueOf(3 - p) + " attempts left.");
        }
        return "";
        //returns null string if password doesn't match
    }

    private static void cleanSlate(Connection conn) throws SQLException {

        String query = "DELETE FROM tenant_info WHERE rent>='0'";
//this is to check if the name is already in database^^
        Statement Stmt = conn.createStatement();
        Stmt.execute(query);
        query = "DELETE FROM property_info WHERE rent>='0'";
//this is to check if the name is already in database^^
        Stmt = conn.createStatement();
        Stmt.execute(query);

        Tenant admin = new Tenant("admin", conn);
        query = "UPDATE tenant_info SET Password = 'adminpass' Where name ='admin'";
        Stmt = conn.createStatement();
        Stmt.execute(query);

    }
/*
    private static void billRent(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean tenant_info(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/
}

/*
Meliodas lives Liones
Kenshin lives in Kashima_Kashin_Ct
Spike lives in Bebop_Blue_Alley
Planet_Vegeeta_Dr
 */


