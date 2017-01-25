/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realestate;

/**
 600 sqft
 $400
 1/dwelling
 * @author kunaa
 */
public class Apartment extends Property{
      int sFoot;
    double rent;
    int number;
    //
    
    
    
     public Apartment(String PropName, String address, int sFoot, int number, double rent){
    this.propertyName=PropName;
    this.address=address;
   this.sFoot=sFoot;
   this.number=number;
   this.rent=rent;
  
   }
     public double getRent(){
       return this.rent;
       
    
}
}
