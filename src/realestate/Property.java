/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realestate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Property {
   
    String propertyName;
    String address;
    //int number;
    //^^use this as a boolean (mentally) to default to 0 to show if it is an 
    //aparment or not
    
    
    
    
        
    
public String getAddress(){
    return this.address;
}



}
