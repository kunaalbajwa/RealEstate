/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realestate;

import java.sql.SQLException;

/*rent will be tied to property---how do I do that? utilize them through
foreign keys?
would we be extending the Property.java file? In terms of using an interface how
would we do so? My guess would be to make another file to 'extend' to the other files?

 * @author kunaa
 */
public class RealEstate {
//add deleting phrase for the database to be reset to avoid repopulation
    //tenant anmes and property names, make a list
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // TODO code application logic here
         //identify the townhouse, tenant or apartment; then the second input is the address/property
    Townhouse Sphinx=new Townhouse("Sphinx", "East_Vcompound_St", 4, 666, 1200);
    Apartment DemonWorld= new Apartment("Demonworld","MakaiRealm_Ln", 1600, 424, 800);
    Tenant Yusuke = new Tenant("Yusuke", "MakaiRealm_Ln");
    Tenant Vash=new Tenant("Vash", "East_Vcompound_St");
    Tenant Son_Goku=new Tenant("Son_Goku", "MakaiRealm_Ln");
    Tenant Meliodas= new Tenant("Meliodas", "East_Vcompound_St");
    
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

The rent is not changing on the tenant_info SQL
*/