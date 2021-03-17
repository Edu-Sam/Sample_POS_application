/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;
import javax.swing.*;
import java.util.List;
import java.util.Vector;
import java.util.*;

public class UserTransaction {
    String cashier_name;
    String salesman_name;
    String salesman_id;
    double Total_amount;
    double tendered_amount;
    double change;
    String location;
    String location_id;
    String till_id;
    List<Vector> items;
    Vector row_item;
 /*   String section_id;
    String section_name;*/
    String is_use_section;
    String section_id;
    String customer_no;
    TransactionType transaction_type;
    public UserTransaction(){
        cashier_name="";
        salesman_name="";
        salesman_id="";
        Total_amount=0.0;
        tendered_amount=0.0;
        change=0.0;
        location="";
        location_id="";
        till_id="";
        is_use_section="";
        section_id="";
        customer_no="";
        transaction_type=TransactionType.NONE;
    }
    public UserTransaction(String new_cashier_name,String new_salesman_name,String new_salesman_id,
            double new_total_Amount,double new_tendered_Amount,double new_change,String new_location,
            String location_id,String new_location_id,String new_till_id,String new_is_use_section,String customer_no){
        set_cashier_name(new_cashier_name);
        set_salesman_name(new_salesman_name);
        set_salesman_id(new_salesman_id);
        set_total_amount(new_total_Amount);
        set_tendered_Amount(new_tendered_Amount);
        set_change(new_change);
        set_location(new_location);
        set_location_id(new_location_id);
        set_till_id(new_till_id);
        set_is_use_section(new_is_use_section);
        set_customer_no(customer_no);
    }
    public void set_cashier_name(String new_cashier_name){
        cashier_name=new_cashier_name;
    }
    public void set_salesman_name(String new_salesman_name){
        salesman_name=new_salesman_name;
    }
    public void set_salesman_id(String new_salesman_id){
        salesman_id=new_salesman_id;
    }
    public void set_total_amount(double new_total_amount){
        Total_amount=new_total_amount;
    }
    public void set_tendered_Amount(double new_tendered_Amount){
        tendered_amount=new_tendered_Amount;
    }
    public void set_change(double new_change){
        change=new_change;
    }
    public void set_location(String new_location){
        location=new_location;
    }
    public void set_location_id(String new_location_id){
        location_id=new_location_id;
    }
    public void set_till_id(String new_till_id){
        till_id=new_till_id;
    }
    public void set_section_id(String new_section_id){
        section_id=new_section_id;
    }
 /*   public void set_section_name(String new_section_name){
        section_name=new_section_name;
    }*/
    public void set_is_use_section(String new_is_use_section){
        is_use_section=new_is_use_section;
    }
    public void set_customer_no(String customer_no){
        this.customer_no=customer_no;
    }
    public void set_transaction_type(TransactionType transaction_type){
        this.transaction_type=transaction_type;
    }
    public String get_cashier_name(){
        return cashier_name;
    }
    public String get_salesman_name(){
        return salesman_name;
    }
    public String get_salesman_id(){
        return salesman_id;
    }
    public double get_total_amount(){
        return Total_amount;
    }
    public double get_tendered_amount(){
        return tendered_amount;
    }
    public double get_change(){
        return change;
    }
    public String get_location(){
        return location;
    }
    public String get_location_id(){
        return location_id;
    }
    public String get_till_id(){
        return till_id;
    }
    public String get_is_use_section(){
        return is_use_section;
    }
    public String get_section_id(){
        return section_id;
    }
    public String get_customer_no(){
        return customer_no;
    }
    public TransactionType get_transaction_type(){
        return transaction_type;
    }
 /*   public String get_section_name(){
        return section_name;
    }
    */
    //The loop will be in the main program
private List<Vector> get_item(String description,double price,int qty,String scancode,double total){
        items=new ArrayList<Vector>();
        row_item=new Vector(5);
//        for(int i=0;i<table_rows;i++){
            row_item.add(0,description);
            row_item.add(1,price);
            row_item.add(2,qty);
            row_item.add(3,scancode);
            row_item.add(4,total);
            items.add(row_item);
 //       }
    return items;    
    }

}
