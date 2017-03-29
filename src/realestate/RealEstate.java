/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realestate;

import java.sql.*;
import java.util.Scanner;

public class RealEstate {
//add deleting phrase for the database to be reset to avoid repopulation
    //tenant anmes and property names, make a list

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // TODO code application logic here
        //identify the townhouse, tenant or apartment; then the second input is the address/property
        String myDriver = "com.mysql.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/mynewdatabase";
        Class.forName(myDriver);
        // the mysql insert statement

        Connection conn = DriverManager.getConnection(myUrl, "kunaalbajwa", "Demonruler1");

        cleanSlate(conn);
        Townhouse Sphinx = new Townhouse("Sphinx", "East_Vcompound_St", 4000, 666, 1200.00, conn);
        Apartment DemonWorld = new Apartment("DemonWorld", "MakaiRealm_Ln", 1600, 424, 800.00, conn);
        Tenant Son_Goku = new Tenant("Son_Goku", "DemonWorld", "9001", conn);
        Tenant Meliodas = new Tenant("Meliodas", "Sphinx", "9002", conn);
        Tenant Yusuke = new Tenant("Yusuke", "DemonWorld", "9003", conn);
        Tenant Vash = new Tenant("Vash", "Sphinx", "9004", conn);

        if (!Meliodas.move("DemonWorld", conn)) {
            System.out.println("Dammit Jim that's not a brick layer!");
        }

        Son_Goku.payRent(750.0, conn);
        Yusuke.billRent(conn);
        String Username;
        Username = "admin";
//   Username = login(conn);
//^^bring back when done 
//call text menu
        if(Username.equals("admin")){
        adminMainMenu(conn);
        }
        //remember to add menu for tenants 
        conn.close();
    }

            //menu will exist here
    public static void adminMainMenu(Connection conn){
        boolean exit= false;
        while(!exit){
        
        System.out.println("Please select an option.");
        System.out.println("1. Check Tenants");
        System.out.println("2. Manipulate Rent");
        System.out.println("3. Exit");
 Scanner scanner = new Scanner(System.in);
 String input= scanner.nextLine();
 
switch(input){ 
 
    case "1": break;        //call connection and print all data

    case "2": break;
    case "3": exit=true;
    break;
    
    default: System.out.println("Invalid request.");
 
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
            //else THIS IS NOT A USERNAME 
        }
        //give a query for  password and a check for the passwords for it from the databases; keep a count and then kick them back  
        System.out.println("Please enter password ");
        String query = "SELECT Password from tenant_info WHERE Name= '" + Username + "'";
//this is to check if the name is already in database^^
        Statement Stmt = conn.createStatement();
        ResultSet Rs = Stmt.executeQuery(query);
        Rs.next();
        
        String Password=Rs.getString("Password");
        for (int p = 1; p <= 3; p++) {
            //gives 3 shots
            System.out.print("Password: ");
            Scanner scanner = new Scanner(System.in);
            String password = scanner.nextLine();

            if (password.equals(Password)) {
                        return Username;

            } 
            System.out.println("Invalid Password, " + String.valueOf(3-p)+ " attempts left.");
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

}

/*
Meliodas lives Liones
Kenshin lives in Kashima_Kashin_Ct
Spike lives in Bebop_Blue_Alley
Planet_Vegeeta_Dr
 */

 /*
 magic rent button for the landlord; tenant can check how much is owed 
menu option 
password
 */
