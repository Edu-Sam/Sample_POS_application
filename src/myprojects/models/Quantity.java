/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;
public class Quantity {
    float adjust_quantity;
    public Quantity(){
        adjust_quantity=0;
    }
    public Quantity(float new_qty){
        change_qty(new_qty);
    }
    public void change_qty(float new_qty){
        adjust_quantity=new_qty;
    }
    public float getQuantity(){
        return adjust_quantity;
    }
    
}
