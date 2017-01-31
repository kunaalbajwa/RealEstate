/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realestate;

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

    public Tenant(String tenant_name, String property){
    this.tenant_name=tenant_name;
    this.property=property;
    }
}
