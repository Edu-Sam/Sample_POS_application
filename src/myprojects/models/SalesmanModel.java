/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;

public class SalesmanModel {
   String userName;
   int password;
   String salesman_id;
   
   public SalesmanModel(){
       userName="";
       password=0;
       salesman_id="";
   }
   public SalesmanModel(String new_userName,String new_salesman_id){
       setUserName(new_userName);
       set_salesman_id(new_salesman_id);
   }
   public void setUserName(String new_userName){
       userName=new_userName;
   }
   private void setpassword(int new_password){
       password=new_password;
   }
   public void set_salesman_id(String new_salesman_id){
       salesman_id=new_salesman_id;
   }
   public String getUserName(){
       return userName;
   }
   private int getPassword(){
       return password;
   }
   public String get_Salesman_id(){
       return salesman_id;
   }
}
