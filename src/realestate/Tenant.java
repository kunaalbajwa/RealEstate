/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realestate;

import java.sql.*;

/*
 
 6 total people: Vash Stampeede, Ken Shiro, Son Goku, Wong Feihung, 
 * Yusuke Urameshi, Spike Speigel,
 *
 * @author kunaa
 
 */
public class Tenant {

    double rent;
    String tenant_name;
    String property;

    public Tenant(String tenant_name, String property, Connection conn) throws SQLException{
        this.tenant_name = tenant_name;
        this.property = property;
        this.rent = 600;
        //^^placeholder for time being

        //sql insert
            String query = "SELECT Name from tenant_info WHERE Name= '" + this.tenant_name +"'";
//this is to check if the name is already in database^^
            Statement Stmt = conn.createStatement();
            ResultSet Rs = Stmt.executeQuery(query);

            if (Rs.next() && this.tenant_name.equals(Rs.getString("Name"))) {
                System.out.println("WARNING: Tenant Name " + this.tenant_name+ " already in Database");
            } else {
//sql query pulling the rent:
query = "SELECT Rent from property_info WHERE Name= '" + this.property +"'";
//this is to check if the name is already in database^^
             Stmt = conn.createStatement();
             Rs = Stmt.executeQuery(query);
             //read the results:
             if (Rs.next()){
                 this.rent=(Rs.getDouble("Rent"));
             }
             else {
                 System.out.println("WARNING: No match in property found for " +this.property);
             }
// the mysql insert statement
                query = " insert into tenant_info (Name, Property, Rent)"
                        + " values (?, ?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, this.tenant_name);
                preparedStmt.setString(2, this.property);
                preparedStmt.setDouble(3, this.rent);
                //eventually ahve to figure out rent before we put this.rent in; for now keep it arbitrary
                
                // execute the preparedstatement
                preparedStmt.execute();
            }
            
        
    }

    public  Boolean move(String property, Connection conn) throws SQLException{
        
//sql query pulling the rent:
String query = "SELECT Rent from property_info WHERE Name= '" + property +"'";
//this is to check if the name is already in database^^
             Statement Stmt = conn.createStatement();
             ResultSet Rs = Stmt.executeQuery(query);
             //read the results:
             if (Rs.next()){
                 this.rent=(Rs.getDouble("Rent"));
                 this.property=property;
             query = "UPDATE tenant_info SET Rent=?, Property=? WHERE Name=?";
//updates the move and rent here^^
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(3, this.tenant_name);
                preparedStmt.setString(2, this.property);
                preparedStmt.setDouble(1, this.rent);
                //eventually ahve to figure out rent before we put this.rent in; for now keep it arbitrary
                 
// execute the preparedstatement
                preparedStmt.execute();
             }
             else {
             return false;
             }
                
                return true;
     
}


}
 
