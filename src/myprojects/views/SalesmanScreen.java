/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.*;
import java.io.*;
public class SalesmanScreen extends MyProjects{
 //   private static final String Url="jdbc:sqlserver://localhost:49243;DatabaseName=EDWIN;method=Cursor";
    private String Url;   
    private String host_name="";
    private String sql_database_name="";
    private String host_port="";
    private static final String user_name="sa";
    private static final String password="123456";
    String salesman;
    String Cashier_name="";
    Timer timer2;
    int pass_text=0;
    private PreparedStatement get_users1;
    private Connection con1;
    public SalesmanScreen(String x,String a){
        super(x,a);
        try{
            InputStream in=new FileInputStream("eclipse.ini");
            Properties properties=new Properties();
            properties.load(in);
            host_name=properties.getProperty("host_name");
            host_port=properties.getProperty("host_port");
            sql_database_name=properties.getProperty("sql_database_name");
            Url="jdbc:sqlserver://"+ host_name + ":" + host_port + ";" + "DatabaseName=" + sql_database_name + ";" + "method=Cursor";
            
            
            }
            catch(IOException ex){
            JOptionPane.showMessageDialog((java.awt.Component)null,"ERROR:Unable to connect to database",
                            "ERROR",JOptionPane.ERROR_MESSAGE);
            close();
            System.exit(0);
      //      ex.printStackTrace();
        }
        
        Cashier_name=a;
        try{
            con1=DriverManager.getConnection(Url,user_name,password);
        get_users1=con1.prepareStatement("SELECT Salesman_name,SalesmanID FROM Salesman WHERE Password=?"
                + " AND isDisabled=0");
        }
        
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            for(UIManager.LookAndFeelInfo info:  UIManager.getInstalledLookAndFeels()){
                if(("Windows").equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    SwingUtilities.updateComponentTreeUI(this);
                    
                }
            }
        }
        catch(Exception e){
            
        }
        super.okButton.addActionListener(new ActionListener(){
           @Override
           
            public void actionPerformed(ActionEvent e){
               try{
                pass_text=Integer.parseInt(field2.getText());
                get_Salesman_details(pass_text);}
               catch(Exception ex){
              /*     JOptionPane.showMessageDialog((java.awt.Component)null,"ERROR: "+ ex.getMessage(),
                           "ERROR",JOptionPane.ERROR_MESSAGE);*/
                   JOptionPane.showMessageDialog((java.awt.Component)null,"ERROR:Wrong password format.Consult your system admin",
                           "Error",JOptionPane.ERROR_MESSAGE);
               }
            }
        });
        
  //      String get_new_Users=
        }
    @Override
    public List<Cashier> get_user_details(int pass){
       return null; 
    }
    private List<SalesmanModel> get_Salesman_details(int pass){
        List<SalesmanModel> user_details=null;
        ResultSet set=null;
        try{
         get_users1.setInt(1,pass);
         set=get_users1.executeQuery();
         user_details=new ArrayList<SalesmanModel>();
         while(set.next()){
             user_details.add(new SalesmanModel(set.getString("Salesman_name"),set.getString("SalesmanID")));
         }
         
         if(user_details.isEmpty()){
             JOptionPane.showMessageDialog(null,"INCORRECT PASSWORD","ERROR",JOptionPane.ERROR_MESSAGE);
         }
         else if(super.field2.getPassword()==null){
             JOptionPane.showMessageDialog(null,"INCORRECT PASSWORD","ERROR",
                         JOptionPane.ERROR_MESSAGE);
         }
         else{
             String use_section="";
             try{
                 InputStream in=new FileInputStream("eclipse.ini");
                 Properties properties=new Properties();
                 properties.load(in);
                  use_section=properties.getProperty("enable_sections");
                 
             }
             catch(IOException e){
                 e.printStackTrace();
                 
             }
            
             super.Current_cashier=Cashier_name;
             super.Cashier_last_name=user_details.get(0).getUserName();
             UserTransaction user_transaction=new UserTransaction();
             user_transaction.set_cashier_name(super.Current_cashier);
             user_transaction.set_salesman_name(super.Cashier_last_name);
             user_transaction.set_salesman_id(user_details.get(0).get_Salesman_id());
             user_transaction.set_location_id("0002");
             user_transaction.set_till_id("04");
             user_transaction.set_is_use_section(use_section);
             //Trial SalesScreen=new Trial("FRONT OFFICE UPDATE 1:  LOCATION:RESTAURANT    TILL NO:1",user_transaction);
             UserDashBoard SalesScreen=new UserDashBoard("",user_transaction);
             SalesScreen.setUndecorated(true);
             SalesScreen.putTitle(user_transaction.get_cashier_name(),user_transaction.get_salesman_name());
             SalesScreen.set_section_state();
             SalesScreen.setVisible(true);
             close();
             
         }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog((java.awt.Component)null,"ERROR:Unable to connect to database",
                            "ERROR",JOptionPane.ERROR_MESSAGE);
            close();
            System.exit(0);
       //     e.printStackTrace();
        }
        finally{
            try{
                set.close();
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog((java.awt.Component)null,"ERROR:Unable to connect to database",
                            "ERROR",JOptionPane.ERROR_MESSAGE);
            close();
            System.exit(0);
            //    ex.printStackTrace();
            }
        }
        return user_details;
        
    }
    private void close(){
        this.dispose();
    }
    
    
    
    public static void main(String[] args){
        try{
            for(UIManager.LookAndFeelInfo info:  UIManager.getInstalledLookAndFeels()){
                if(("Windows").equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                }
            }
        }
        catch(Exception e){
            
        }
        SalesmanScreen c=new SalesmanScreen("Easyllite BMS: [ --Salesman--]","");
        c.setVisible(true);
    }
    }
    

