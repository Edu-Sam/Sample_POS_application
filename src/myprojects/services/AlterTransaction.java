
package myprojects;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.sql.*;
import javax.swing.JOptionPane;
public class AlterTransaction {
 //   private static final String Url="jdbc:sqlserver://localhost:49243;DatabaseName=EDWIN;selectMethod=cursor";
 private String Url;   
 private String host_name="";
 private String sql_database_name="";
 private String host_port="";
 private String sql_instance_name="";
   private static final String UserName="sa";
   private static final String password="123456";
   Connection con=null;
   private ResultSet set=null;
   private PreparedStatement getSalesAccount=null;
   private PreparedStatement getPurchaseAccount=null;
   private PreparedStatement getStockValueAccount=null;
   private PreparedStatement getCashAccount=null;
   private PreparedStatement getLocation=null;
   private PreparedStatement getTransaction=null;
   private PreparedStatement insertTransaction_cash=null;
   private PreparedStatement insertTransaction_sales=null;
   private PreparedStatement insertTransaction_stockValue=null;
   private PreparedStatement insertTransaction_purchase=null;
   private PreparedStatement check_transaction_table=null;
   private PreparedStatement getDocNo=null;
   private PreparedStatement insertTransact=null;
   private PreparedStatement insertTransactionSummary=null;
   private PreparedStatement deleteVoucherTransaction=null;
   private PreparedStatement insertVoucherTransaction=null;
   private PreparedStatement adjust_transact_qty=null;
   private PreparedStatement insert_transactiondetail=null;
   private PreparedStatement getDate=null;
   private PreparedStatement adjust_transaction_qty=null;
   private PreparedStatement getDailyDocNo=null;
   private PreparedStatement getSections=null;
   private PreparedStatement getSupplierAccount=null;
   private PreparedStatement getVatAccount=null;
   private PreparedStatement insertOrderDetail=null;
   private PreparedStatement insertOrderSummary=null;
   private PreparedStatement check_order_table=null;
   private PreparedStatement get_order_docno=null;
   private PreparedStatement get_order_date=null;
   private PreparedStatement getorderdailydocno=null;
   private PreparedStatement getOrders=null;
   private PreparedStatement getSalesman=null;
   private Vector salesman_vector=null;
   private PreparedStatement getAllSalesmanByName=null;
   private Vector get_all_salesman_data_vector;
   private PreparedStatement get_individual_salesman=null;
   private Vector get_individual_salesman_vector=null;
   private Savepoint save_point=null;
   private PreparedStatement getOrderedItems=null;
   private Vector order_items_vector=null;
   private PreparedStatement getOrdersByDocno=null;
   private Vector getOrdersByDocno_vector=null;
   private Vector get_imported_orders_vector=null;
   private PreparedStatement getImportedOrders=null;
   private PreparedStatement setIsFullySupplied_detail=null;
   private PreparedStatement setIsFullySupplied_summary=null;
   private PreparedStatement getCustomerInfo=null;
   private PreparedStatement getCustomerInfoByName=null;
   private PreparedStatement getVoucherTransactions=null;
   private PreparedStatement getInvoiceTransactions=null;
   
  
   public AlterTransaction(){
        try{
            InputStream in=new FileInputStream("eclipse.ini");
            Properties properties=new Properties();
            properties.load(in);
            host_name=properties.getProperty("host_name");
            host_port=properties.getProperty("host_port");
            sql_database_name=properties.getProperty("sql_database_name");
            sql_instance_name=properties.getProperty("sql_instance_name");
        //    Url="jdbc:sqlserver://"+ host_name + ":" + host_port + ";" + "DatabaseName=" + sql_database_name + ";" + "method=Cursor";
            Url="jdbc:sqlserver://"+ host_name + "\\" + sql_instance_name + ";" + "DatabaseName=" + sql_database_name + ";" + "method=Cursor";
            
            
            }
            catch(IOException ex){
            JOptionPane.showMessageDialog((java.awt.Component)null,"ERROR:Unable to connect to database",
                            "ERROR",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            ex.printStackTrace();
        }
       try{
           con=DriverManager.getConnection(Url,UserName,password);
       //    con.setAutoCommit(false);
           getSalesAccount=con.prepareStatement("SELECT account_nm,account_no FROM Account WHERE account_nm='Sales'");
           getPurchaseAccount=con.prepareStatement("SELECT account_nm,account_no FROM Account WHERE account_nm='Purchase'");
           getStockValueAccount=con.prepareStatement("SELECT account_nm,account_no FROM Account WHERE account_nm='Stock Value'");
           getCashAccount=con.prepareStatement("SELECT account_nm,account_no FROM Account WHERE account_nm='Cash'");
           getSupplierAccount=con.prepareStatement("SELECT account_nm,account_no FROM Account WHERE account_nm=?");
           getVatAccount=con.prepareStatement("SELECT account_nm,account_no FROM Account WHERE account_nm='Vat Control'");
           insertTransaction_cash=con.prepareStatement("INSERT INTO Transact(voucher_type_name,Loca_id,Till_ID,"
                   + "Type_ID,Voucher_ID,transno,acc_no,amount)VALUES(?,?,?,?,?,?,?,?)");
           insertTransaction_sales=con.prepareStatement("INSERT INTO Transact(voucher_type_name,Loca_id,Till_ID,"
                   + "Type_ID,Voucher_ID,transno,acc_no,amount)VALUES(?,?,?,?,?,?,?,?)");
           insertTransaction_stockValue=con.prepareStatement("INSERT INTO Transact(voucher_type_name,Loca_id,Till_ID,"
                   + "Type_ID,Voucher_ID,transno,acc_no,amount)VALUES(?,?,?,?,?,?,?,?)");
           insertTransaction_purchase=con.prepareStatement("INSERT INTO Transact(voucher_type_name,Loca_id,Till_ID,"
                   + "Type_ID,Voucher_ID,transno,acc_no,amount)VALUES(?,?,?,?,?,?,?,?)");
           getLocation=con.prepareStatement("SELECT loca_id,loca_des FROM Location WHERE loca_id=?");
           insert_transactiondetail=con.prepareStatement("INSERT INTO TransactionDetail(Salesman_id,Voucher_ID,DocNo,InputLineno,"
                   + "Type_ID,Stockcode,Scancode,Startcode,Unitcost,Qty,UnitPrice,TotalAmount,loca_id,"
                   + "DailyDocNo,stock_f_Unitcost,stock_f_Unitprice,section_id)"
                   + "Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
           getDate=con.prepareStatement("SELECT MAX(InputDate) AS database_date FROM TransactionDetail WHERE Type_ID=? AND DocNo LIKE ? ");
           get_order_date=con.prepareStatement("SELECT MAX(InputDate) AS database_date FROM OrderDetail WHERE Type_ID='SO'");
           insertTransactionSummary=con.prepareStatement("INSERT INTO TransactionSummary(Loca_id,Till_id,Type_ID,"
                   + "Voucher_ID,DocNo,Salesman_ID,Acc_No,Daily_DocNo,TotalAmount,isPosted,section_id) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
           deleteVoucherTransaction=con.prepareStatement("DELETE FROM VoucherTransactions where TypeID=? AND loca_id=? AND till_id=?");
           insertVoucherTransaction=con.prepareStatement("INSERT INTO VoucherTransactions(Voucher_ID,VoucherDescription,"
                   + "TypeID,TypePrefix,docno,isPrimary,loca_id,till_id) VALUES(?,?,?,?,?,?,?,?)");
           check_transaction_table=con.prepareStatement("SELECT * FROM TransactionDetail WHERE Type_ID=? AND"
                   + " DocNo LIKE ?");
           getDocNo=con.prepareStatement("SELECT MAX(DocNo) AS DocNo FROM TransactionDetail WHERE Type_ID=? "
                   + " AND DocNo LIKE ?");
           get_order_docno=con.prepareStatement("SELECT MAX(DocNo) AS DocNo FROM OrderDetail WHERE Type_ID='SO'\n" +
"                    AND DocNo LIKE ?");
   //        getTransaction=con.prepareStatement("SELECT * FROM Vouchers WHERE VoucherType_ID='01'");
           getTransaction=con.prepareStatement("SELECT * FROM Vouchers WHERE Type_Id='TCS'");
           getInvoiceTransactions=con.prepareStatement("SELECT * FROM Vouchers WHERE Type_Id='TIS'");
           getOrders=con.prepareStatement("SELECT * FROM Vouchers WHERE Type_Id='SO'");
           adjust_transaction_qty=con.prepareStatement("UPDATE Stock_Catalogue SET Qty_on_order=Qty_on_order - ? "
                   + "WHERE item_id=?");
           getDailyDocNo=con.prepareStatement("SELECT DailyDocNo FROM TransactionDetail WHERE InputDate=? AND Type_ID=? AND DocNo LIKE ?" );
           getorderdailydocno=con.prepareStatement("SELECT Daily_docno FROM OrderDetail WHERE InputDate=? AND Type_ID='SO'");
           getSections=con.prepareStatement("SELECT Section_ID,SectionDescription FROM Section WHERE isDisabled='false'");
           insertOrderDetail=con.prepareStatement("INSERT INTO OrderDetail(Voucher_ID,DocNo,InputLineno,Type_ID,salesman_id,stockcode,scancode,\n" +
"startcode,unitcost,qty,isFullySupplied,unitprice,TotalAmount,loca_id,Daily_docno,stock_f_unitcost,\n" +
"stock_f_unitprice,AmountsDontIncludeVat) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
           insertOrderSummary=con.prepareStatement("INSERT INTO OrderSummary(Loca_id,Till_id,Type_ID,Voucher_ID,DocNo,Ref,Salesman_ID,TotalAmount,isFullySupplied,DailyDocno\n" +
") VALUES(?,?,?,?,?,?,?,?,?,?)");
           check_order_table=con.prepareStatement("SELECT * FROM OrderDetail WHERE Type_ID='SO' AND\n" +
"                   +  DocNo LIKE ?");
           getSalesman=con.prepareStatement("SELECT Salesman_name,SalesmanID FROM Salesman WHERE isDisabled=0");
           getAllSalesmanByName=con.prepareStatement("SELECT sal.Salesman_name,order_sum.DocNo,order_sum.TotalAmount,order_sum.TransactionDate\n" +
"FROM OrderSummary order_sum\n" +
"LEFT JOIN Salesman sal\n" +
"ON order_sum.Salesman_ID=sal.SalesmanID WHERE order_sum.isFullySupplied=0");
           get_individual_salesman=con.prepareStatement("SELECT sal.Salesman_name,order_sum.DocNo,order_sum.TotalAmount,order_sum.TransactionDate\n" +
"FROM OrderSummary order_sum\n" +
"LEFT JOIN Salesman sal\n" +
"ON order_sum.Salesman_ID=sal.SalesmanID\n" +
"WHERE sal.Salesman_name=? AND order_sum.isFullySupplied=0 ");
           getOrderedItems=con.prepareStatement("SELECT stock.Purchase_Des,detail.UnitPrice,detail.Qty,detail.TotalAmount\n" +
"FROM OrderDetail detail\n" +
"LEFT JOIN Stock_Catalogue stock\n" +
"ON detail.scancode=stock.item_id\n" +
"WHERE detail.DocNo=?");
    //       save_point=con.setSavepoint("save_point");
           getOrdersByDocno=con.prepareStatement("SELECT detail.DocNo,stock.Purchase_Des,detail.qty,detail.unitprice,detail.UnitDiscountAmount,\n" +
"detail.TotalAmount FROM OrderDetail detail\n" +
"LEFT JOIN Stock_Catalogue stock\n" +
"ON detail.scancode=stock.item_Id\n" +
"WHERE detail.DocNo=?");
           getImportedOrders=con.prepareStatement("SELECT stock.Purchase_Des,detail.unitprice,detail.qty,stock.Scancode,\n" +
"detail.TotalAmount FROM\n" +
"OrderDetail detail\n" +
"LEFT JOIN Stock_Catalogue stock\n" +
"ON detail.scancode=stock.item_id\n" +
"WHERE detail.DocNo=?");
           setIsFullySupplied_detail=con.prepareStatement("UPDATE OrderDetail\n" +
"SET isFullySupplied=1 WHERE DocNo=?");
           setIsFullySupplied_summary=con.prepareStatement("UPDATE OrderSummary\n" +
"SET isFullySupplied=1 WHERE DocNo=?");
           getCustomerInfo=con.prepareStatement("SELECT A.account_no,A.account_nm,A.Address1,A.Address2,A.email,\n" +
"A.Telno,notes FROM Account A \n" +
"WHERE A.balance_sheet_id='0003'");
           getCustomerInfoByName=con.prepareStatement("SELECT A.account_no,A.account_nm,A.Address1,A.Address2,A.email,\n" +
"A.Telno,notes FROM Account A \n" +
"WHERE A.account_nm=?");
           getVoucherTransactions=con.prepareStatement("SELECT COUNT(docno) AS no_of_transactions "
                   + "FROM VoucherTransactions WHERE TypeID=? AND loca_id=? AND till_id=?");
       
           
       }
       catch(SQLException ex){
           ex.printStackTrace();
       }
   }
   public void enable_auto_commit(){
       try{
       con.setAutoCommit(true);
       }
       catch(SQLException e){
           e.printStackTrace();
       }
   }
   public List<transaction_model> get_transactions(){
       List<transaction_model> result=null;
       try{
           set=getTransaction.executeQuery();
           result=new ArrayList<transaction_model>();
           while(set.next()){
               result.add(new transaction_model(set.getString("Voucher_ID"),set.getString("VoucherType_ID"),set.getString("VoucherDescription"),
                       set.getString("Type_Id"),set.getString("Type_prefix"),set.getString("docno"),
                       set.getBoolean("isPrimary"),set.getBoolean("isDontReconcile"),set.getString("CompanyID"),
                       set.getString("till_Id"),set.getString("loca_Id"),set.getString("distributor_Id"),
                       set.getBoolean("isDisabled")));
           }
       }
       catch(SQLException ex){
           ex.printStackTrace();
       }
       finally{
           try{
               set.close();
           }
           catch(SQLException e){
               e.printStackTrace();
           }
       }
       return result;
   }
   
   public List<transaction_model> get_invoice_transactions(){
       List<transaction_model> result=null;
       try{
           set=getInvoiceTransactions.executeQuery();
           result=new ArrayList<transaction_model>();
           while(set.next()){
               result.add(new transaction_model(set.getString("Voucher_ID"),set.getString("VoucherType_ID"),set.getString("VoucherDescription"),
                       set.getString("Type_Id"),set.getString("Type_prefix"),set.getString("docno"),
                       set.getBoolean("isPrimary"),set.getBoolean("isDontReconcile"),set.getString("CompanyID"),
                       set.getString("till_Id"),set.getString("loca_Id"),set.getString("distributor_Id"),
                       set.getBoolean("isDisabled")));
           }
       }
       catch(SQLException ex){
           ex.printStackTrace();
       }
       finally{
           try{
               set.close();
           }
           catch(SQLException e){
               e.printStackTrace();
           }
       }
       return result;
   }
   public List<transaction_model> get_orders(){
       List<transaction_model> result=null;
       try{
           set=getOrders.executeQuery();
           result=new ArrayList<transaction_model>();
           while(set.next()){
               result.add(new transaction_model(set.getString("Voucher_ID"),set.getString("VoucherType_ID"),set.getString("VoucherDescription"),
                       set.getString("Type_Id"),set.getString("Type_prefix"),set.getString("docno"),
                       set.getBoolean("isPrimary"),set.getBoolean("isDontReconcile"),set.getString("CompanyID"),
                       set.getString("till_Id"),set.getString("loca_Id"),set.getString("distributor_Id"),
                       set.getBoolean("isDisabled")));
           }
       }
       catch(SQLException ex){
           ex.printStackTrace();
       }
       finally{
           try{
               set.close();
           }
           catch(SQLException e){
               e.printStackTrace();
           }
       }
       return result;
   }
   public List<Account> get_Sales_Account(){
    List<Account> result=null;
    try{
        set=getSalesAccount.executeQuery();
        result=new ArrayList<Account>();
        while(set.next()){
            result.add(new Account(set.getString("account_nm"),set.getString("account_no")));
        }
    }
    catch(SQLException e){
        e.printStackTrace();
    }
    finally{
        try{
            set.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
 //           close();
        }
    }
    return result;
}
   public List<Account> get_Purchase_Account(){
    List<Account> result=null;
    try{
        set=getPurchaseAccount.executeQuery();
        result=new ArrayList<Account>();
        while(set.next()){
            result.add(new Account(set.getString("account_nm"),set.getString("account_no")));
        }
    }
    catch(SQLException e){
        e.printStackTrace();
    }
    finally{
        try{
            set.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
 //           close();
        }
    }
    return result;
}
   public List<Account> get_StockValue_Account(){
    List<Account> result=null;
    try{
        set=getStockValueAccount.executeQuery();
        result=new ArrayList<Account>();
        while(set.next()){
            result.add(new Account(set.getString("account_nm"),set.getString("account_no")));
        }
    }
    catch(SQLException e){
        e.printStackTrace();
    }
    finally{
        try{
            set.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
 //           close();
        }
    }
    return result;
}
  public List<Account> get_Cash_Account(){
    List<Account> result=null;
    try{
        set=getCashAccount.executeQuery();
        result=new ArrayList<Account>();
        while(set.next()){
            result.add(new Account(set.getString("account_nm"),set.getString("account_no")));
        }
    }
    catch(SQLException e){
        e.printStackTrace();
    }
    finally{
        try{
            set.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
   //         close();
        }
    }
    return result;
}
  
  public List<Account> get_Vat_Account(){
      List<Account> result=null;
      try{
          set=getVatAccount.executeQuery();
          result=new ArrayList<Account>();
          while(set.next()){
              result.add(new Account(set.getString("account_nm"),set.getString("account_no")));
          }
      }
      catch(SQLException e){
          e.printStackTrace();
      }
      finally{
          try{
              set.close();
          }
          catch(SQLException ex){
              ex.printStackTrace();
          }
      }
      return result;
  }
  public List<Account> get_Supplier_Account(String supplier_name){
      List<Account> result=null;
      try{
          getSupplierAccount.setString(1,supplier_name);
          set=getSupplierAccount.executeQuery();
          result=new ArrayList<Account>();
          while(set.next()){
              result.add(new Account(set.getString("account_nm"),set.getString("account_no")));
          }
      }
      catch(SQLException e){
          e.printStackTrace();
      }
      finally{
          try{
              set.close();
          }
          catch(SQLException ex){
              ex.printStackTrace();
          }
      }
      return result;
  }
  public int adjust_transaction_qty(float qty,String itemcode){
       int result=0;
       try{
           adjust_transaction_qty.setFloat(1,qty);
           adjust_transaction_qty.setString(2,itemcode);
           result=adjust_transaction_qty.executeUpdate();
       }
       catch(SQLException e){
           e.printStackTrace();
       }
       return result;
   }
  public String get_Date(String type_id,String DocNo){
      String date="";
      try{
      getDate.setString(1,type_id);
      getDate.setString(2,DocNo);
      set=getDate.executeQuery();
      while(set.next()){
          date=set.getString("database_date");
      }
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      if(date==null){
          date="";
      }
      return date;
  }
  public String get_order_date(){
      String date="";
      try{
      set=get_order_date.executeQuery();
      while(set.next()){
          date=set.getString("database_date");
      }
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      if(date==null){
          date="";
      }
      return date;
  }
  public List<Vector> get_salesman(){
      List<Vector> result=null;
      try{
          set=getSalesman.executeQuery();
          result=new ArrayList<Vector>();
          while(set.next()){
              salesman_vector=new Vector(2);
              salesman_vector.add(0,set.getString("Salesman_name"));
              salesman_vector.add(1,set.getString("SalesmanID"));
              result.add(salesman_vector);
              
          }
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      finally{
          try{
              set.close();
          }
          catch(SQLException e){
              e.printStackTrace();
          }
      }
      return result;
  }
  
  public String get_daily_docno(String date,String type_id,String new_docno){
      String docno="";
      try{
          getDailyDocNo.setString(1,date);
          getDailyDocNo.setString(2,type_id);
          getDailyDocNo.setString(3, new_docno);
          set=getDailyDocNo.executeQuery();
          while(set.next()){
              docno=set.getString("DailyDocNo");
          }
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      return docno;
  }
  
  public String get_order_daily_docno(String date){
      String docno="";
      try{
          getorderdailydocno.setString(1,date);
          set=getorderdailydocno.executeQuery();
          while(set.next()){
              docno=set.getString("Daily_docno");
          }
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      return docno;
  }
  public int check_transact_table(String type_id,String docno){
       String result="";
       int result2=0;
       try{
       check_transaction_table.setString(1,type_id);
       check_transaction_table.setString(2,docno);
       set=check_transaction_table.executeQuery();
       while(set.next()){
           result=set.getString("Type_ID");
           result2++;
       }
       }
       catch(SQLException ex){
           ex.printStackTrace();
       }
       finally{
           try{
               set.close();
           }
           catch(SQLException e){
               e.printStackTrace();
           }
       }
       return result2;
   }
 /* public int adjust_transact_quantity(int qty,String itemcode){
      int result=0;
       try{
           adjust_transact_qty.setInt(1,qty);
           adjust_transact_qty.setString(2,itemcode);
           result=adjust_transact_qty.executeUpdate();
       }
       catch(SQLException e){
           e.printStackTrace();
       }
       return result;
  }*/
  public int insert_transact(String voucher_type_name,String loca_id,String till_id,String Type_id,
         String voucher_id,String transno,String cash_Account,String sales_Account,String stock_value_Account,
         String purchase_account,double total_amount,double total_unit_cost){
      int result=0;
      int result_1=0;
      int result_2=0;
      int result_3=0;
      try{
          insertTransaction_cash.setString(1,voucher_type_name);
          insertTransaction_cash.setString(2,loca_id);
          insertTransaction_cash.setString(3,till_id);
          insertTransaction_cash.setString(4,Type_id);
          insertTransaction_cash.setString(5,voucher_id);
          insertTransaction_cash.setString(6,transno);
          insertTransaction_cash.setString(7,cash_Account);
          insertTransaction_cash.setDouble(8,total_amount);
          result=insertTransaction_cash.executeUpdate();
          
         insertTransaction_sales.setString(1,voucher_type_name);
         insertTransaction_sales.setString(2,loca_id);
         insertTransaction_sales.setString(3,till_id);
         insertTransaction_sales.setString(4,Type_id);
         insertTransaction_sales.setString(5,voucher_id);
         insertTransaction_sales.setString(6,transno);
         insertTransaction_sales.setString(7,sales_Account);
         insertTransaction_sales.setDouble(8,-total_amount);
         result_1=insertTransaction_sales.executeUpdate();
         
         insertTransaction_stockValue.setString(1,voucher_type_name);
         insertTransaction_stockValue.setString(2,loca_id);
         insertTransaction_stockValue.setString(3,till_id);
         insertTransaction_stockValue.setString(4,Type_id);
         insertTransaction_stockValue.setString(5,voucher_id);
         insertTransaction_stockValue.setString(6,transno);
         insertTransaction_stockValue.setString(7,stock_value_Account);
         insertTransaction_stockValue.setDouble(8,-total_unit_cost);
         result_2=insertTransaction_stockValue.executeUpdate();
         
         insertTransaction_purchase.setString(1,voucher_type_name);
         insertTransaction_purchase.setString(2,loca_id);
         insertTransaction_purchase.setString(3,till_id);
         insertTransaction_purchase.setString(4,Type_id);
         insertTransaction_purchase.setString(5,voucher_id);
         insertTransaction_purchase.setString(6,transno);
         insertTransaction_purchase.setString(7,purchase_account);
         insertTransaction_purchase.setDouble(8,total_unit_cost);
         result_3=insertTransaction_purchase.executeUpdate();
         
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      return result_3;
  }
  public int insert_transaction_detail(String salesman_id,String voucher_id,String Docno,int inputLineno,String Type_id,String 
           stockcode,String scancode,String startcode,double unitCost,double qty,double unitPrice,double TotalAmount,String loca_id,
           String daily_docno,double stock_f_unitcost,double stock_f_unitprice,String section_id){
       int result=0;
       try{
 //          con.setAutoCommit(false);
 //          save_point=con.setSavepoint("SavePoint");
           insert_transactiondetail.setString(1,salesman_id);
           insert_transactiondetail.setString(2,voucher_id);
           insert_transactiondetail.setString(3,Docno);
           insert_transactiondetail.setInt(4,inputLineno);
           insert_transactiondetail.setString(5,Type_id);
           insert_transactiondetail.setString(6,stockcode);
           insert_transactiondetail.setString(7,scancode);
           insert_transactiondetail.setString(8,startcode);
           insert_transactiondetail.setDouble(9,unitCost);
           insert_transactiondetail.setDouble(10,qty);
           insert_transactiondetail.setDouble(11,unitPrice);
           insert_transactiondetail.setDouble(12,TotalAmount);
           insert_transactiondetail.setString(13,loca_id);
           insert_transactiondetail.setString(14,daily_docno);
           insert_transactiondetail.setDouble(15,stock_f_unitcost);
           insert_transactiondetail.setDouble(16,stock_f_unitprice);
           insert_transactiondetail.setString(17,section_id);
           result=insert_transactiondetail.executeUpdate();
       }
       catch(SQLException e){
           e.printStackTrace();
       }
       return result;
       
   }
  public int insert_Transaction_summary(String loca_id,String till_id,String Type_id,String voucher_id,String 
           docno,String salesman_id,String acc_no,String daily_docno,double Totalamount,boolean isPosted,String section_id){
       int result=0;
       try{
 //          con.setAutoCommit(false);
 //          save_point=con.setSavepoint("save_point");
           insertTransactionSummary.setString(1,loca_id);
           insertTransactionSummary.setString(2,till_id);
           insertTransactionSummary.setString(3,Type_id);
           insertTransactionSummary.setString(4,voucher_id);
           insertTransactionSummary.setString(5,docno);
           insertTransactionSummary.setString(6,salesman_id);
           insertTransactionSummary.setString(7,acc_no);
           insertTransactionSummary.setString(8,daily_docno);
           insertTransactionSummary.setDouble(9,Totalamount);
           insertTransactionSummary.setBoolean(10,isPosted);
           insertTransactionSummary.setString(11,section_id);
           result=insertTransactionSummary.executeUpdate();
       }
       catch(SQLException e){
           e.printStackTrace();
       }
       return result;
       
   }
  public int insert_order_detail(String voucher_id,String docno,int InputLineno,String Type_ID,String salesman_id,
          String stockcode,String scancode,String startcode,double unitcost,double qty,boolean isFullySupplied,
          double unitprice,double TotalAmount,String loca_id,String daily_docno,double stock_f_unitcost,double stock_f_unitprice,
          boolean amountsdontincludevat){
      
      int result=0;
      try{
   //       con.setAutoCommit(false);
  //        save_point=con.setSavepoint("save_point");
          insertOrderDetail.setString(1,voucher_id);
          insertOrderDetail.setString(2,docno);
          insertOrderDetail.setInt(3,InputLineno);
          insertOrderDetail.setString(4,Type_ID);
          insertOrderDetail.setString(5,salesman_id);
          insertOrderDetail.setString(6,stockcode);
          insertOrderDetail.setString(7,scancode);
          insertOrderDetail.setString(8,startcode);
          insertOrderDetail.setDouble(9,unitcost);
          insertOrderDetail.setDouble(10,qty);
          insertOrderDetail.setBoolean(11,isFullySupplied);
          insertOrderDetail.setDouble(12,unitprice);
          insertOrderDetail.setDouble(13,TotalAmount);
          insertOrderDetail.setString(14,loca_id);
          insertOrderDetail.setString(15,daily_docno);
          insertOrderDetail.setDouble(16,stock_f_unitcost);
          insertOrderDetail.setDouble(17,stock_f_unitprice);
          insertOrderDetail.setBoolean(18,amountsdontincludevat);
          result=insertOrderDetail.executeUpdate();
      }
      catch(SQLException e){
          rollback_to_savepoint();
          e.printStackTrace();
      }
      return result;
  }
  public int insert_order_summary(String loca_id,String till_id,String Type_id,String voucher_id,String docno,
          String ref,String salesman_id,double total_Amount,boolean isFullySupplied,String daily_docno){
      int result=0;
      try{
          insertOrderSummary.setString(1,loca_id);
          insertOrderSummary.setString(2,till_id);
          insertOrderSummary.setString(3,Type_id);
          insertOrderSummary.setString(4,voucher_id);
          insertOrderSummary.setString(5,docno);
          insertOrderSummary.setString(6,ref);
          insertOrderSummary.setString(7,salesman_id);
          insertOrderSummary.setDouble(8,total_Amount);
          insertOrderSummary.setBoolean(9,isFullySupplied);
          insertOrderSummary.setString(10,daily_docno);
          result=insertOrderSummary.executeUpdate();
      }
      catch(SQLException ex){
          rollback_to_savepoint();
          JOptionPane.showMessageDialog(null,"CANNOT SAVE TRANSACTION","ERROR",JOptionPane.ERROR_MESSAGE);
      }
      return result;
  }
  public int check_order_table(String docno){
       String result="";
       int result2=0;
       try{
       check_order_table.setString(1,docno);
       set=check_order_table.executeQuery();
       while(set.next()){
           result=set.getString("Type_ID");
           result2++;
       }
       }
       catch(SQLException ex){
           ex.printStackTrace();
       }
       finally{
           try{
               set.close();
           }
           catch(SQLException e){
               e.printStackTrace();
           }
       }
       return result2;
   }
  public List<Vector> get_orders_by_docno(String docno){
      List<Vector> result=null;
      try{
          getOrdersByDocno.setString(1,docno);
          set=getOrdersByDocno.executeQuery();
          result=new ArrayList<Vector>();
          while(set.next()){
              getOrdersByDocno_vector=new Vector(6);
              getOrdersByDocno_vector.add(0,set.getString("DocNo"));
              getOrdersByDocno_vector.add(1,set.getString("Purchase_Des"));
              getOrdersByDocno_vector.add(2,set.getDouble("qty"));
              getOrdersByDocno_vector.add(3,set.getDouble("unitprice"));
              getOrdersByDocno_vector.add(4,set.getDouble("UnitDiscountAmount"));
              getOrdersByDocno_vector.add(5,set.getDouble("TotalAmount"));
              result.add(getOrdersByDocno_vector);
          }
          
      }
      catch(SQLException e){
          e.printStackTrace();
      }
      finally{
          try{
              set.close();
          }
          catch(SQLException ex){
              ex.printStackTrace();
          }
      }
      return result;
  }
  public int delete_voucher_Transaction(String Type_id,String loca_id,String till_id){
       int result=0;
       try{
           deleteVoucherTransaction.setString(1,Type_id);
           deleteVoucherTransaction.setString(2,loca_id);
           deleteVoucherTransaction.setString(3,till_id);
           result=deleteVoucherTransaction.executeUpdate();
       }
       catch(SQLException ex){
           rollback_to_savepoint();
           ex.printStackTrace();
       }
       return result;
   }
  
   public int insert_voucher_transaction(String voucher_id,String voucher_description,String Type_id,String TypePrefix,
           String docno,boolean isPrimary,String loca_id,String till_id){
       int result=0;
       try{
           insertVoucherTransaction.setString(1,voucher_id);
           insertVoucherTransaction.setString(2,voucher_description);
           insertVoucherTransaction.setString(3,Type_id);
           insertVoucherTransaction.setString(4,TypePrefix);
           insertVoucherTransaction.setString(5,docno);
           insertVoucherTransaction.setBoolean(6,isPrimary);
           insertVoucherTransaction.setString(7,loca_id);
           insertVoucherTransaction.setString(8,till_id);
           result=insertVoucherTransaction.executeUpdate();
           con.commit();
       }
       catch(SQLException e){
           rollback_to_savepoint();
           e.printStackTrace();
       }
       return result;
   }
   public List<Account> get_section(){
       List<Account> result=null;
       try{
           set=getSections.executeQuery();
           result=new ArrayList<Account>();
           while(set.next()){
               result.add(new Account(set.getString("SectionDescription"),set.getString("Section_ID")));
           }
       }
       catch(SQLException e){
           e.printStackTrace();
       }
       finally{
           try{
               set.close();
           }
           catch(Exception ex){
               ex.printStackTrace();
           }
       }
       return result;
   }
   public List<Vector> get_order_items(String docno){
       List<Vector> result=null;
       try{
           getOrderedItems.setString(1,docno);
           set=getOrderedItems.executeQuery();
           result=new ArrayList<Vector>();
           while(set.next()){
               order_items_vector=new Vector(4);
               order_items_vector.add(0,set.getString("Purchase_Des"));
               order_items_vector.add(1,set.getDouble("UnitPrice"));
               order_items_vector.add(2,set.getDouble("Qty"));
               order_items_vector.add(3,set.getDouble("TotalAmount"));
               result.add(order_items_vector);
               
           }
       }
       catch(SQLException e){
           e.printStackTrace();
       }
       finally{
           try{
               set.close();
           }
           catch(SQLException ex){
               ex.printStackTrace();
           }
       }
       return result;
   }
   public String get_DocNo(String type_id,String pc_id){
       String result="";
       try{
            getDocNo.setString(1,type_id);
            getDocNo.setString(2,pc_id);
           set=getDocNo.executeQuery();
           while(set.next()){
               result=set.getString("DocNo");
           }
       }
       catch(SQLException ex){
           ex.printStackTrace();
       }
       finally{
           try{
               set.close();
           }
           catch(SQLException e){
               e.printStackTrace();
           }
       }
       return result;
   }
   public String get_order_docno(String pc_id){
       String result="";
       try{
            get_order_docno.setString(1,pc_id);
           set=get_order_docno.executeQuery();
           while(set.next()){
               result=set.getString("DocNo");
           }
       }
       catch(SQLException ex){
           ex.printStackTrace();
       }
       finally{
           try{
               set.close();
           }
           catch(SQLException e){
               e.printStackTrace();
           }
       }
       return result;
   }
   
   public void commit_transaction(){
       try{
           con.commit();
       }
       catch(SQLException e){
           e.printStackTrace();
       }
   }
   public void disable_commit(){
       try{
           con.setAutoCommit(false);
       }
       catch(SQLException e){
           e.printStackTrace();
       }
   }
   public void rollback_transaction(){
       try{
           con.rollback();
       }
       catch(SQLException ex){
           ex.printStackTrace();
       }
   }
   public List<Vector> get_all_salesman_by_name(){
       List<Vector> result=null;
       try{
           set=getAllSalesmanByName.executeQuery();
           result=new ArrayList<Vector>();
           while(set.next()){
               get_all_salesman_data_vector=new Vector(4);
               get_all_salesman_data_vector.add(0,set.getString("Salesman_name"));
               get_all_salesman_data_vector.add(1,set.getString("DocNo"));
               get_all_salesman_data_vector.add(2,set.getDouble("TotalAmount"));
               get_all_salesman_data_vector.add(3,set.getString("TransactionDate"));
               result.add(get_all_salesman_data_vector);
           }
       }
       catch(SQLException ex){
           ex.printStackTrace();
       }
       finally{
           try{
               set.close();
           }
           catch(SQLException e){
               e.printStackTrace();
           }
       }
       return result;
   }
   public List<Vector> get_individual_salesman(String salesman_name){
       List<Vector> result=null;
       try{
           get_individual_salesman.setString(1,salesman_name);
           set=get_individual_salesman.executeQuery();
           result=new ArrayList<Vector>();
           while(set.next()){
               get_individual_salesman_vector=new Vector(4);
               get_individual_salesman_vector.add(0,set.getString("Salesman_name"));
               get_individual_salesman_vector.add(1,set.getString("DocNo"));
               get_individual_salesman_vector.add(2,set.getDouble("TotalAmount"));
               get_individual_salesman_vector.add(3,set.getString("TransactionDate"));
               result.add(get_individual_salesman_vector);
               
           }
       
   }
       catch(SQLException e){
           e.printStackTrace();
       }
       finally{
           try{
               set.close();
           }
           catch(SQLException ex){
               ex.printStackTrace();
           }
       }
       return result;
   }
  
   public List<Customer> get_customer_info(){
      List<Customer> result=null;
      
      try{
          set=getCustomerInfo.executeQuery();
          result=new ArrayList<Customer>();
          while(set.next()){
              result.add(new Customer(set.getString("account_no"),set.getString("account_nm"),set.getString("Address1"),set.getString("Address2"),set.getString("email"),
                      set.getString("Telno"),0.0,0.0,set.getString("notes")));
          }
         
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      
      finally{
          try{
              set.close();
          }
          catch(SQLException e){
              e.printStackTrace();
          }
      }
      
      return result;
   }
   
   public List<Customer> get_customer_info(String name){
       List<Customer> result=null;
       
       try{
           getCustomerInfoByName.setString(1,name);
           set=getCustomerInfoByName.executeQuery();
           result=new ArrayList<Customer>();
           
           while(set.next()){
               result.add(new Customer(set.getString("account_no"),set.getString("account_nm"),set.getString("Address1"),set.getString("Address2"),set.getString("email"),
                      set.getString("Telno"),0.0,0.0,set.getString("notes")));
               
           }
       }
       
       catch(SQLException e){
           e.printStackTrace();
       }
       
       finally{
           try{
               set.close();
           }
           catch(SQLException ex){
               ex.printStackTrace();
           }
       }
       
       return result;
   }
   
   public int get_voucher_transactions(String type_id,String loca_id,String till_id){
       int result=0;
       
       try{
           getVoucherTransactions.setString(1,type_id);
           getVoucherTransactions.setString(2,loca_id);
           getVoucherTransactions.setString(3,till_id);
           set=getVoucherTransactions.executeQuery();
           
           while(set.next()){
               result=set.getInt("no_of_transactions");
           }
       }
       catch(SQLException e){
           e.printStackTrace();
       }
       
       finally{
           try{
               set.close();
           }
           catch(SQLException ex){
               ex.printStackTrace();
               
           }
       }
       
       return result;
   }
  
   
  public List<Vector> get_imported_orders(String docno){
      List<Vector> result=null;
      try{
          getImportedOrders.setString(1,docno);
          set=getImportedOrders.executeQuery();
          result=new ArrayList<Vector>();
          while(set.next()){
              get_imported_orders_vector=new Vector(5);
              get_imported_orders_vector.add(0,set.getString("Purchase_Des"));
              get_imported_orders_vector.add(1,set.getDouble("unitprice"));
              get_imported_orders_vector.add(2,set.getDouble("qty"));
              get_imported_orders_vector.add(3,set.getString("scancode"));
              get_imported_orders_vector.add(4,set.getDouble("TotalAmount"));
              result.add(get_imported_orders_vector);
          }
          
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      finally{
          try{
              set.close();
          }
          catch(SQLException e){
              e.printStackTrace();
          }
      }
      return result;
  }
  public int set_is_fully_supplied_detail(String docno){
      int result=0;
      try{
          setIsFullySupplied_detail.setString(1,docno);
          result=setIsFullySupplied_detail.executeUpdate();
      }
      catch(SQLException e){
          e.printStackTrace();
      }
      finally{
          try{
              set.close();
          }
          catch(SQLException ex){
              ex.printStackTrace();
          }
      }
      return result;
  }
  public int set_is_fully_supplied_summary(String docno){
      int result=0;
      try{
          setIsFullySupplied_summary.setString(1,docno);
          result=setIsFullySupplied_summary.executeUpdate();
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      finally{
          try{
              set.close();
          }
          catch(SQLException e){
              e.printStackTrace();
          }
      }
      return result;
  }
  public void rollback_to_savepoint(){
      try{
      con.rollback(save_point);
  }
      catch(SQLException e){
          e.printStackTrace();
      }
  }
  
  public void set_savePoint(){
      try{
      save_point=con.setSavepoint("save_point");
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
  }
  public static void main(String [] args){
      AlterTransaction d=new AlterTransaction();
  //    String f=d.get_Date();
 //     System.out.println(f);
  }
}
