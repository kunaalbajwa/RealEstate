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

    public Tenant(String tenant_name, String property) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
    this.tenant_name=tenant_name;
    this.property=property;
    this.rent=600;
    //^^placeholder for time being
    
    
    //sql insert
        String myDriver = "com.mysql.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost:3306/mynewdatabase";
      Class.forName(myDriver);
        // the mysql insert statement
        try (Connection conn = DriverManager.getConnection(myUrl, "kunaalbajwa", "Demonruler1")) {
            // the mysql insert statement
            String query = " insert into tenant_info (Name, Property, Rent)"
                    + " values (?, ?, ?)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, this.tenant_name);
            preparedStmt.setString (2, this.property);
            preparedStmt.setDouble   (3, this.rent);
            //eventually ahve to figure out rent before we put this.rent in; for now keep it arbitrary
            
            
            // execute the preparedstatement
            preparedStmt.execute();
            conn.close();
            
        }
    }
    
    }

