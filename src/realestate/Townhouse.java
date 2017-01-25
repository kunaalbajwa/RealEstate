/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realestate;

/**
 1200 sqft
 $800
 1/dwelling
 * @author kunaa
 */
public class Townhouse extends Property {
   int sFoot;
   int number;
   double rent;
   
   
     //depending on how much info I want to be found out these two sub classses will be used
 
   public Townhouse(int sFoot, int number, double rent){
   this.sFoot=sFoot;
   this.number=number;
   this.rent=rent;
  
   }
   public Townhouse(String PropName, String address, int sFoot, int number, double rent){
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
