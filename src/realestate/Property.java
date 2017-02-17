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


public class Property {
   
    String propertyName;
    //String address;
    double rent;
    //int number;
    //^^use this as a boolean (mentally) to default to 0 to show if it is an 
    //aparment or not
    
    public Property(String propertyName, String address) throws ClassNotFoundException, SQLException{
        this.propertyName=propertyName;
      //  this.address=address;
        this.rent=600;
       String myDriver = "com.mysql.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/mynewdatabase";
        Class.forName(myDriver);
        // the mysql insert statement
        try (Connection conn = DriverManager.getConnection(myUrl, "kunaalbajwa", "Demonruler1")) {
            String query = "SELECT Name from property_info WHERE Name= '" + this.propertyName +"'";
//this is to check if the name is already in database^^
            Statement Stmt = conn.createStatement();
            ResultSet Rs = Stmt.executeQuery(query);

            if (Rs.next() && this.propertyName.equals(Rs.getString("Name"))) {
                System.out.println("WARNING: Propery " + this.propertyName + " already in Database");
            } else {

// the mysql insert statement
                query = " insert into property_info (Rent, Property Name)"
                        + " values (?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, this.propertyName);
                preparedStmt.setDouble(2, this.rent);
                //eventually ahve to figure out rent before we put this.rent in; for now keep it arbitrary
                //need a sql query on how to pull rent
                // execute the preparedstatement
                preparedStmt.execute();
            }
            conn.close();
}
    }
    
//plan for property.java:
    //search property info for a property that matches the tenant's info
    //SELECT rent from property _info WHERE Name= '" + this.propertyName +"'"
    //if matched, set tenant rent to property rent
    //if not , print warning and set rent to 0
//QUESTION: how would I set up a the choice between Apartment and Townhouse for the variable propertyName???? --if confused contact me
    
    /*
public String getAddress(){
 //   return this.address;
}
*/

}
