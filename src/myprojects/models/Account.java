/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;

public class Account {
    String Account_name;
    String Account_no;
    
    public Account(){
        Account_name="";
        Account_no="";
    }
    public Account(String new_Account_name,String new_Account_no){
      set_Account_Name(new_Account_name);
      set_Account_no(new_Account_no);
    }
    private void set_Account_Name(String new_Account_name){
        Account_name=new_Account_name;
    }
    private void set_Account_no(String set_Account_no){
        Account_no=set_Account_no;
    }
    public String getAccountName(){
        return Account_name;
    }
    public String getAccountNo(){
        return Account_no;
    }
}
