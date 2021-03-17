/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;


public class Total {
double tendered;
double total;
double change;

public Total(){
 tendered=0.00;
 total=0.00;
 change=0.00;
}
public Total(double new_Total,double new_tendered){
    setTotal(new_Total);
    setTendered(new_tendered);
    setChange();
}
public void setTendered(double new_tendered){
    tendered=new_tendered;
}
public void setTotal(double new_Total){
    total=new_Total;
}
public double getTotal(){
    return total;
}     
public double getTendered(){
    return tendered;
}
public double setChange(){
  change=total-tendered;
  return change;
}
    
}
