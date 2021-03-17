/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;


public class TenderedAdjusted {
    double clientAmount=0.0;
    String customer_no;
    public TenderedAdjusted(){
    clientAmount=0.0; 
    customer_no="";
    }
    public TenderedAdjusted(double newClientAmount){
        changeTendered(newClientAmount);
    }
    public TenderedAdjusted(double newClientAmount,String customer_no){
        change_Tendered_With_Customer(newClientAmount,customer_no);
    }
   // public TenderedAdjusted(double newClientAmount,double amountWithstanding,)
    public void changeTendered(double newClientAmount){
        clientAmount=newClientAmount;
    }
    public double getTendered(){
        return clientAmount;
    }
    public String getCustomer(){
        return customer_no;
    }
    public void change_Tendered_With_Customer(double newClientAmount,String customer_no){
        clientAmount=newClientAmount;
        this.customer_no=customer_no;
    }
}
