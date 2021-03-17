/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;

public class Cashier {
   String userName;
   String last_name;
   int password;
   
 public Cashier(){
     userName="";
     last_name="";
     password=0;
 }
 public Cashier(String newUserName,String new_last_name,int newPassword){
     setUserName(newUserName);
     setPassword(newPassword);
     setLastName(new_last_name);
 }
 public void setUserName(String newUserName){
     userName=newUserName;
 }
 public void setLastName(String new_last_name){
     last_name=new_last_name;
 }
 public String getLastName(){
     return last_name;
 }
 
 public void setPassword(int newPassword){
     password=newPassword;
 }
 public int getPassword(){
     return password;
 }
 public String getUserName(){
     return userName;
 }
    
}
