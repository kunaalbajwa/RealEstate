/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realestate;

import java.sql.*;

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
            
            Tenant Yusuke = new Tenant("Yusuke", "DemonWorld", conn);
            Tenant Vash = new Tenant("Vash", "Sphinx", conn);
            Tenant Son_Goku = new Tenant("Son_Goku", "DemonWorld", conn);
            Tenant Meliodas = new Tenant("Meliodas", "Sphinx", conn);
            
            if (!Meliodas.move("DemonWorld", conn)) {
                System.out.println("Dammit Jim that's not a brick layer!");
            }
        
    }


    public static void cleanSlate(Connection conn) throws SQLException{
     
            String query = "DELETE FROM tenant_info WHERE rent>='0'";
//this is to check if the name is already in database^^
            Statement Stmt = conn.createStatement();
            Stmt.execute(query);
            query = "DELETE FROM property_info WHERE rent>='0'";
//this is to check if the name is already in database^^
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
Functions to enable:

Incorporate Tax??

 */


