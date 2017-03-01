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

/*
  @author kunaa
 */
/*

"Would you like to move a tenant to a different location?"
public moveTenantTown {
this.moveTenantTown= will move tenant
INSERT INTO different  row in property and tenant_info
SELECT <columns> //or would it be rows?
FROM apartment 
WHERE <condition>; if query is done

DELETE FROM Table1
WHERE <condition>;

COMMIT;

}

public moveTenantApt{
INSERT INTO Apartment (<columns>)
SELECT <columns>
FROM Table1
WHERE <condition>;

DELETE FROM Table1
WHERE <condition>;

COMMIT;
}

public changeRent{}
public incInterest{}
public removeInterest{}
*/
public class RealEstate {
//add deleting phrase for the database to be reset to avoid repopulation
    //tenant anmes and property names, make a list
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // TODO code application logic here
         //identify the townhouse, tenant or apartment; then the second input is the address/property
   cleanSlate();
   Townhouse Sphinx=new Townhouse("Sphinx", "East_Vcompound_St", 4, 666, 1200);
    Apartment DemonWorld= new Apartment("Demonworld","MakaiRealm_Ln", 1600, 424, 800);
    Tenant Yusuke = new Tenant("Yusuke", "Demonworld");
    Tenant Vash=new Tenant("Vash", "Sphinx");
    Tenant Son_Goku=new Tenant("Son_Goku", "Demonworld");
    Tenant Meliodas= new Tenant("Meliodas", "Sphinx");
    
    if(!Meliodas.move("Demonworld"))
    System.out.println("Dammit that's wrong.");
        }
    
    
    
    public static void cleanSlate() throws ClassNotFoundException, SQLException{
         String myDriver = "com.mysql.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/mynewdatabase";
        Class.forName(myDriver);
        // the mysql insert statement
        
        try (Connection conn = DriverManager.getConnection(myUrl, "kunaalbajwa", "Demonruler1")) {
            String query = "DELETE FROM tenant_info WHERE rent>'0'";
//this is to check if the name is already in database^^
            Statement Stmt = conn.createStatement();
            Stmt.execute(query);
 query = "DELETE FROM property_info WHERE rent>'0'";
//this is to check if the name is already in database^^
            Stmt = conn.createStatement();
            Stmt.execute(query);
            
            conn.close();
    }
    }
}
    /*

delete from tenant_info where rent > 0;
delete from property_info where rent > 0;

*/

/*
Meliodas lives Liones
Kenshin lives in Kashima_Kashin_Ct
Spike lives in Bebop_Blue_Alley
MakaiRealm_Ln
Planet_Vegeeta_Dr
*/

/*
Functions to enable:
Move tenant between townhouse and apartment
Change Rent function
Incorporate Tax?
*/

/*
Questions:

The rent is not changing on the tenant_info SQL 2-26-17
would we place the functions in the static void main?
*/