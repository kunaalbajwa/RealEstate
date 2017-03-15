/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realestate;

import java.sql.*;

/**
 * 600 sqft $400 1/dwelling
 *
 * @author kunaa
 */
public class Apartment extends Property {

    int sqFoot;
    double rent;
    int number;
    //

    public Apartment(String PropName, String address, int sqFoot, int number, double rent, Connection conn) throws SQLException {
        this.propertyName = PropName;
        this.address = address;
        this.sqFoot = sqFoot;
        this.number = number;
        this.rent = rent;
        //sql insert
       
          

            String query = "SELECT * from property_info WHERE Address= '" + this.address + "'";
//this is to check if the name is already in database^^
            Statement Stmt = conn.createStatement();
            ResultSet Rs = Stmt.executeQuery(query);

            int match_flag = 0;

            while (match_flag == 0 && Rs.next()) {
                if (this.address.equals(Rs.getString("Address")) && this.number == Rs.getInt("Number")) {
                    match_flag = 1;
                    System.out.println("WARNING: Property at " + this.address + " and number: " + this.number + " are already in Database");
                }
            }

            if (match_flag == 0) {
                query = " insert into property_info (Number, Address, Rent, Name, SqFoot)"
                        + " values (?, ?, ?, ?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, this.number);
                preparedStmt.setString(2, this.address);
                preparedStmt.setDouble(3, this.rent);
                preparedStmt.setString(4, this.propertyName);
                 preparedStmt.setInt(5, this.sqFoot);
                //eventually ahve to figure out rent before we put this.rent in; for now keep it arbitrary

                // execute the preparedstatement
                preparedStmt.execute();
            }
            
    }

    public double getRent() {
        return this.rent;

    }
}
