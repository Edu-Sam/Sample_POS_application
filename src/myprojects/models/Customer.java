/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;

/**
 *
 * @author EDWIN
 */
public class Customer {
    String account_no;
    String account_nm;
    String address1;
    String address2;
    String email;
    String telephone;
    double amount;
    double outstanding_amount;
    String notes;
    
    public Customer(){
        account_no="";
        account_nm="";
        address1="";
        address2="";
        email="";
        telephone="";
        amount=0.0;
        outstanding_amount=0.0;
        String notes="";
    }
    public Customer(String account_no,String account_nm,String address1,String address2,String email,
            String telephone,double amount,double outstanding_amount,String notes){
        setAccount_no(account_no);
        setAccount_nm(account_nm);
        setAddress1(address1);
        setAddress2(address2);
        setEmail(email);
        setTelephone(telephone);
        setAmount(amount);
        setOutstanding_amount(outstanding_amount);
        setNotes(notes);
    }
  public void setAccount_no(String account_no){
      this.account_no=account_no;
  }
  
  public void setAccount_nm(String account_nm){
      this.account_nm=account_nm;
  }
  public void setAddress1(String address1){
      this.address1=address1;
  }
  public void setAddress2(String address2){
      this.address2=address2;
  }
  public void setEmail(String email){
      this.email=email;
  }
  public void setTelephone(String telephone){
      this.telephone=telephone;
  }
  public void setAmount(double amount){
      this.amount=amount;
  }
  public void setOutstanding_amount(double outstanding_amount){
      this.outstanding_amount=outstanding_amount;
  }
  public void setNotes(String notes){
      this.notes=notes;
  }
  public String getAccount_no(){
      return account_no;
  }
  public String getAccount_nm(){
      return account_nm;
  }
  public String getAddress1(){
      return address1;
  }
  public String getAddress2(){
      return address2;
  }
  public String getEmail(){
      return email;
  }
  public String getTelephone(){
      return telephone;
  }
 public double getAmount(){
     return amount;
 }
  
  public double getOutstanding_amount(){
      return outstanding_amount;
  }
  
  public String getNotes(){
      return notes;
  }
    
}
