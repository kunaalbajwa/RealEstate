/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realestate;

import java.sql.*;


/**

 * @author kunaa
 */
public class Townhouse extends Property {
   int sqFoot;
   int number;
   double rent;
   
   
     //depending on how much info I want to be found out these two sub classses will be used
 
   public Townhouse(int sqFoot, int number, double rent){
   this.sqFoot=sqFoot;
   this.number=number;
   this.rent=rent;
  
   }
   public Townhouse(String PropName, String address, int sqFoot, int number, double rent, Connection conn) throws SQLException{
    this.propertyName=PropName;
    this.address=address;
   this.sqFoot=sqFoot;
   this.number=number;
   this.rent=rent;
  
   //sql insert
    
            // the mysql insert statement
             String query = "SELECT Address from property_info WHERE Address= '" + this.address +"'";
//this is to check if the name is already in database^^
            Statement Stmt = conn.createStatement();
            ResultSet Rs = Stmt.executeQuery(query);

            if (Rs.next() && this.address.equals(Rs.getString("Address"))) {
                System.out.println("WARNING: Property at " + this.address+ " already in Database");
            } else {
                
            
            query = " insert into property_info (Number, Address, Rent, Name, SqFoot)"
                    + " values (?, ?, ?, ?, ?)";
            
            // create the mysql insert preparedstatement 
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, this.number);
            preparedStmt.setString (2, this.address);
            preparedStmt.setDouble (3, this.rent);
            preparedStmt.setString (4, this.propertyName);
            preparedStmt.setInt(5, this.sqFoot);
                   
            //eventually ahve to figure out rent before we put this.rent in; for now keep it arbitrary
            
            
            // execute the preparedstatement
            preparedStmt.execute();
            }
            
            
        
   
   }
   public double getRent(){
       return this.rent;
       //if we have called it here we just need to hook it up with a SQL query
    
}
/*
   public double propertyTax(double taxRate){
    double propertyTax;
    propertyTax= ;
    }  
*/
   
}
