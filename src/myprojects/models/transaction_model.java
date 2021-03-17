/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;


public class transaction_model {
int VoucherID=0;
String Voucher_ID;
String VoucherType_ID;
String VoucherDescription;
String Type_id;
String Type_Prefix;
String Docno;
boolean isPrimary;
boolean isDontReconcile;
String company_id;
String till_id;
String loca_id;
String distributor_id;
boolean isDisabled;
String section_id;
String section_name;

public transaction_model(String new_Voucher_id,String new_VoucherType_id,String new_Voucher_description,String new_Type_id,
          String new_Type_Prefix,String new_docno,boolean new_is_Primary,boolean new_isDontReconcile,
          String new_company_id,String new_till_id,String new_loca_id,String new_distributor_id,boolean 
                  new_isDisabled){
      set_Voucher_ID(new_Voucher_id);
      set_VoucherType_id(new_VoucherType_id);
      set_Voucher_description(new_Voucher_description);
      set_Type_id(new_Type_id);
      set_Type_Prefix(new_Type_Prefix);
      set_DocNo(new_docno);
      set_isPrimary(new_is_Primary);
      set_isDontReconcile(new_isDontReconcile);
      set_Company_id(new_company_id);
      set_till_id(new_till_id);
      set_loca_id(new_loca_id);
      set_distributor_id(new_distributor_id);
      set_isDisabled(new_isDisabled);
      
  }
  private void set_VoucherID(int new_VoucherID){
      VoucherID=new_VoucherID;
  }
  private void set_Voucher_ID(String new_Voucher_id){
      Voucher_ID=new_Voucher_id;
  }
  private void set_VoucherType_id(String new_VoucherType_id){
      VoucherType_ID=new_VoucherType_id;
  }
  private void set_Voucher_description(String new_Voucher_description){
      VoucherDescription=new_Voucher_description;
  }
  private void set_Type_id(String new_Type_id){
      Type_id=new_Type_id;
  }
  private void set_Type_Prefix(String new_Type_Prefix){
      Type_Prefix=new_Type_Prefix;
  }
  private void set_DocNo(String new_doc_no){
      Docno=new_doc_no;
  }
  private void set_isPrimary(boolean new_isPrimary){
      isPrimary=new_isPrimary;
  }
  private void set_isDontReconcile(boolean new_isDontReconcile){
      isDontReconcile=new_isDontReconcile;
  }
  private void set_Company_id(String new_company_id){
      company_id=new_company_id;
  }
  private void set_till_id(String new_till_id){
      till_id=new_till_id;
  }
  private void set_loca_id(String new_loca_id){
      loca_id=new_loca_id;
  }
  private void set_distributor_id(String new_distributor_id){
      distributor_id=new_distributor_id;
  }
  private void set_isDisabled(boolean new_is_disabled){
      isDisabled=new_is_disabled;
  }
  public void set_section_id(String new_section_id){
      section_id=new_section_id;
  }
  public void set_section_name(String new_section_name){
      section_name=new_section_name;
  }
  public int getVoucherID(){
      return VoucherID;
  }
  public String get_Voucher_ID(){
      return Voucher_ID;
  }
  public String getVoucherType_ID(){
      return VoucherType_ID;
  }
  public String get_Voucher_Description(){
      return VoucherDescription;
  }
  public String get_Type_Id(){
      return Type_id;
  }
  public String get_Type_prefix(){
      return Type_Prefix;
  }
  public String get_DocNo(){
      return Docno;
  }
  public boolean get_isPrimary(){
      return isPrimary;
  }
  public boolean get_isDontReconcile(){
      return isDontReconcile;
  }
  public String get_Company_Id(){
      return company_id;
  }
  public String get_till_Id(){
      return till_id;
  }
  public String get_Distributor_id(){
      return distributor_id;
  }
  public boolean getIsDisabled(){
      return isDisabled;
  }
  public String get_section_id(){
      return section_id;
  }
  public String get_section_name(){
      return section_name;
  }
}
