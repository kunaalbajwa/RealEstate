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
         
    Townhouse Sphinx=new Townhouse("Sphinx", "Vcompound", 4, 666, 1200);
    Tenant Vash=new Tenant("Vash", "Gunsmoke");
    Tenant Son_Goku=new Tenant("Son_Goku", "Planet_Vegeeta");

    Apartment SnakeWay= new Apartment("SnakeWay","Outworld", 1600, 424, 800);
    Tenant Yusuke = new Tenant("Yusuke", "SnakeWay");
    }
    
}
