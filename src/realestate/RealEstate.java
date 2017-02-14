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
//database connection should start here
    /*
    //INSERT into Tenant?
        double rent;
    String tenant_name;
    String property;

    //INSERT into Property?
    String propertyName;
    String address;
    
    //INSERT into Townhouse?
    int sFoot;
    double rent;
    int number;
 1200 sqft
 $800
 1/dwelling

   //INSERT into Apartment?
    600 sqft
 $400
 1/dwelling
    */
    //OR would I just invoke property_info and tenant_info
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // TODO code application logic here
         
    Townhouse Sphinx=new Townhouse("Sphinx", "Vcompound", 4, 666, 1200);
    Tenant Vash=new Tenant("Vash", "Gunsmoke");
    
    
    }
    
}
