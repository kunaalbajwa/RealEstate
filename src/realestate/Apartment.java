/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realestate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 600 sqft
 $400
 1/dwelling
 * @author kunaa
 */
public class Apartment extends Property{
      int sFoot;
    double rent;
    int number;
    //
    
    
    
     public Apartment(String PropName, String address, int sFoot, int number, double rent) throws ClassNotFoundException, SQLException{
    this.propertyName=PropName;
    this.address=address;
   this.sFoot=sFoot;
   this.number=number;
   this.rent=rent;
   //sql insert
        String myDriver = "com.mysql.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost:3306/mynewdatabase";
      Class.forName(myDriver);
        // the mysql insert statement
        try (Connection conn = DriverManager.getConnection(myUrl, "kunaalbajwa", "Demonruler1")) {
            // the mysql insert statement
            
              String query = "SELECT * from property_info WHERE Address= '" + this.address +"'";
//this is to check if the name is already in database^^
            Statement Stmt = conn.createStatement();
            ResultSet Rs = Stmt.executeQuery(query);

            int match_flag=0;
            
            while (match_flag==0 && Rs.next()) {
               if (this.address.equals(Rs.getString("Address"))&& this.number==Rs.getInt("Number")) {
                   match_flag=1;
                System.out.println("WARNING: Property at " + this.address+ " and number: " +this.number+ " are already in Database");
            } 
            } 
                
            if(match_flag==0){
           query = " insert into property_info (Number, Address, Rent, Name)"
                    + " values (?, ?, ?, ?)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, this.number);
            preparedStmt.setString (2, this.address);
            preparedStmt.setDouble   (3, this.rent);
            preparedStmt.setString (4, this.propertyName);
            //eventually ahve to figure out rent before we put this.rent in; for now keep it arbitrary
            
            
            // execute the preparedstatement
            preparedStmt.execute();
            }
            conn.close();
        }
   }
     public double getRent(){
       return this.rent;
       
    
}
}
