
package myprojects;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.event.TextEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.text.DecimalFormat;


public class Trial extends JFrame implements QuantityInterface,Transaction,Order_interface{
//private static final String url="jdbc:sqlserver://192.168.8.100:49243;DatabaseName=EDWIN;selectMethod=cursor";
 private String url;   
 private String host_name="";
 private String sql_database_name="";
 private String host_port="";
 private String sql_instance_name="";
private static final String userName="sa";
private static final String password="123456";

//int a=0;
int number=0;
int number2=0;
int k=0;
int group_name;
private Connection connection=null;
int increase_number=1;
DefaultTableModel model;
JButton page;
double f;
Vector row1;
JTextField field;
double total=0.0;
JPanel pagePanel,pa;
JPanel panel1;
JButton itemButton;
ResultSet resultSet=null;
Vector row;
private List<Item> itemList,itemList_1; 
private PreparedStatement getItems=null;
private PreparedStatement getStockGroups=null;
private List<StockGroups>   groupList;
private PreparedStatement get_new_items=null;
private PreparedStatement runItem=null;
Item an_item;
StockGroups a_group;
private JDesktopPane desktopPane;
//JButton item_button;
CustomColorButton item_button;
JInternalFrame internal;
JLabel newLabel2;
JTable myTable;
int qty=1,transaction_detail_entry=0,database_parsed=0,new_database_parsed=0,docno_database_parsed=0;
int order_detail_entry=0;
String till_id="04",loc_id="0002",from_database,section_state="",section_id="";
private AlterTransaction alter_transaction;
private List<transaction_model> transaction_model,order_model,invoice_model;
private Quantity quantity;
double table_value=1.0;
JTabbedPane tabbedpane,stock_tabbed_pane,item_tabbed_pane;
private List<Item> items;
private UserTransaction new_user_transaction;
private List<Account> sales_account,purchase_account,cash_account,stock_value_account;
private DecimalFormat field_format;
private List<String> order_docno_list;
private Transaction_Catalogue transaction_catalogue;
HashMap <JButton,Integer> map=new HashMap<JButton,Integer>();
    
    public Trial(String title,UserTransaction user_transaction){
        super(title);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        try{
            InputStream in=new FileInputStream("eclipse.ini");
            Properties properties=new Properties();
            properties.load(in);
            host_name=properties.getProperty("host_name");
            host_port=properties.getProperty("host_port");
            sql_database_name=properties.getProperty("sql_database_name");
            sql_instance_name=properties.getProperty("sql_instance_name");
            //url="jdbc:sqlserver://"+ host_name + ":" + host_port + ";" + "DatabaseName=" + sql_database_name + ";" + "method=Cursor";
            url="jdbc:sqlserver://"+ host_name + "\\" + sql_instance_name + ";" + "DatabaseName=" + sql_database_name + ";" + "method=Cursor";
            
            }
            catch(IOException ex){
            JOptionPane.showMessageDialog((java.awt.Component)null,"ERROR:Unable to connect to database",
                            "ERROR",JOptionPane.ERROR_MESSAGE);
            Cancel();
            System.exit(0);
            ex.printStackTrace();
        }
  //      getContentPane().setLayout(new GridLayout(2,1,1,1));
        alter_transaction=new AlterTransaction();
        new_user_transaction=user_transaction;
        order_docno_list=new ArrayList<String>();
       
        try{
            connection=DriverManager.getConnection(url,userName,password);
    //        getItems=connection.prepareStatement("SELECT Purchase_Des,price1,default_quantity,Qty_on_order,Scancode"
    //                + "FROM Stock_Catalogue");
            getItems=connection.prepareStatement("SELECT * FROM Stock_Catalogue WHERE StockGroupID=? AND deactivate_front_office='false'");
            getStockGroups=connection.prepareStatement("SELECT Group_Name,Group_ID FROM STOCK_GROUPS");
            runItem=connection.prepareStatement("SELECT cat.ItemCode,cat.item_id,cat.Scancode,cat.Purchase_Des,Grp.Group_Name,u.Unit_desc,pc.pack_des,p.Vat_rate AS Purchase,\n" +
"v.Vat_rate AS Sales,o.Vat_rate AS other,cat.UnitCost,cat.price1,cat.price2,cat.price3,cat.price4,cat.price5,\n" +
"cat.Qty_on_order,cat.isPromote,cat.prom_price,cat.leadtime,cat.Maxstock,cat.Minstock,pur.account_nm AS Purchase_Account,\n" +
"sal.account_nm AS Sales_Account,stck.account_nm AS Stock_Value_Account\n" +
"FROM Stock_Catalogue cat\n" +
"JOIN STOCK_GROUPS Grp\n" +
"ON cat.StockGroupID=Grp.Group_ID\n" +
"JOIN Vat v\n" +
"ON cat.Vat_ID=v.VatID\n" +
"JOIN Vat p\n" +
"ON cat.PurchaseVat_ID=p.VatID\n" +
"JOIN Vat o\n" +
"ON cat.Other_Tax_ID=o.VatID\n" +
"JOIN Units u\n" +
"ON cat.Unit_ID=u.UnitID\n" +
"JOIN Pack pc\n" +
"ON cat.Pack_ID=pc.Pack_ID\n" +
"JOIN Account pur\n" +
"ON cat.planalcode=pur.account_no\n" +
"JOIN Account sal\n" +
"ON cat.slanalcode=sal.account_no\n" +
"JOIN Account stck\n" +
"ON cat.glaccno=stck.account_no");
  //          get_new_items=connection.prepareStatement("")
            
        }
        catch(SQLException sqlexception){
            sqlexception.printStackTrace();
        }
      try{
           for(UIManager.LookAndFeelInfo info:    UIManager.getInstalledLookAndFeels()){
               if(("Windows").equals(info.getName())){
                   UIManager.setLookAndFeel(info.getClassName());
       //            SwingUtilities.updateComponentTreeUI(this);
       //            getContentPane().validate();
      //             pack();
               }
          }
      }
       catch(Exception e){
           
      }
     
        desktopPane=new JDesktopPane();
         field_format=new DecimalFormat("#0.0000");
        internal=new JInternalFrame("CASHIER:",false,false,false,false);
        internal.setLayout(new GridLayout(2,1,0,0));
        internal.setBorder(BorderFactory.createLoweredSoftBevelBorder());
    //    getContentPane().setLayout(new GridLayout(2,1,0,0));
        JPanel panel2=new JPanel(new BorderLayout(2,2));
        JPanel overallPanel=new JPanel(new GridLayout(1,2,1,1));
   //     JPanel overallPanel2=new JPanel();
   //     overallPanel.setLayout(new GridLayout(1,2,1,1));
       String header[]={"Description","Price","Qty","Scancode","Total"};
        model=new DefaultTableModel(header,50);
        myTable=new JTable(model){
        @Override
        public boolean isCellEditable(int args0,int args1){
 
           return args1==2;
       }
    };
       JScrollPane pane=new JScrollPane(myTable);
       myTable.getModel().addTableModelListener(new TableModelListener(){
           @Override
           public void tableChanged(TableModelEvent e){
             int p=model.getRowCount();
             
            double total=0.0;
            for(int k=0;k<p;k++){
            if(model.getValueAt(k,4)!=null){
               String q=String.valueOf(model.getValueAt(k,4));
                f=Double.parseDouble(q);
                total=total+f;
        
               }

            }
            field.setText(""+field_format.format(total));
            
           }
       });
       CellEditorListener cell=new CellEditorListener(){
           public void editingCanceled(ChangeEvent e){
               changeQuantity();
           }
           public void editingStopped(ChangeEvent e){
               changeQuantity();
           }
       };
       myTable.getDefaultEditor(String.class).addCellEditorListener(cell);
       myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
       myTable.getColumnModel().getColumn(0).setPreferredWidth(230);
       myTable.getColumnModel().getColumn(1).setPreferredWidth(120);
       myTable.getColumnModel().getColumn(2).setPreferredWidth(100);
       myTable.getColumnModel().getColumn(3).setPreferredWidth(100);
    //   myTable.getColumnModel().getColumn(4).setPreferredWidth(105);
       myTable.getColumnModel().getColumn(4).setPreferredWidth(250);
       
       myTable.setRowHeight(18);
       panel2.add(BorderLayout.CENTER,pane);
       panel2.setBackground(new Color(0,0,30));
   //    panel2.setLocation(0,0);
       panel2.setSize(925,500);
       pa=new JPanel();
       pa.setLayout(new BorderLayout());
   //     pa.setLayout(null);
   //     pa.setLocation(0,800);
  //      pa.setBorder(BorderFactory.createRaisedBevelBorder());
       pa.setBackground(new Color(0,0,30));
       JPanel pa_inner=new JPanel();
       pa_inner.setLayout(null);
       pa_inner.setBorder(BorderFactory.createTitledBorder(""));
       pa_inner.setBackground(Color.YELLOW);
    //    JButton button1=new JButton("PAY");
        CustomColorButton button1=new CustomColorButton(Color.GREEN,Color.YELLOW);
        button1.setText("PAY");
        button1.setSize(200,50);
        button1.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
        button1.setLocation(0,30);
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
             
                getGrossTotal();
                CalculateTotal();}
            
        });
        pa_inner.add(button1);
        
     //   JButton button2=new JButton("QUIT");
        CustomColorButton button2=new CustomColorButton(Color.RED,Color.YELLOW);
        button2.setText("QUIT");
        button2.setSize(200,50);
        button2.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
        button2.setLocation(0,150);
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
           int option=JOptionPane.showConfirmDialog(null,"ARE YOU SURE YOU WANT TO QUIT",
                  "QUIT",JOptionPane.YES_NO_OPTION);
           if(option==0){
               Cancel();
               System.exit(0);
           }
            }
        });
        pa_inner.add(button2);
        
   //     JButton button3=new JButton("CLEAR SCREEN");
        CustomColorButton button3=new CustomColorButton(Color.MAGENTA,Color.YELLOW);
        button3.setText("CLEAR SCREEN");
        button3.setLocation(230,30);
        button3.setSize(200,50);
  //      button3.setBackground(Color.MAGENTA);
        button3.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
 //       button3.setFont(new Font("TIMES NEW ROMAN",30,20));
  //      button3.setForeground(Color.YELLOW);
  //      button3.setBorder(BorderFactory.createRaisedBevelBorder());
        button3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                model.setRowCount(0);
                model.setRowCount(50);
            }
        });
        pa_inner.add(button3);
       
   //     JButton button4=new JButton("RETRIEVE ORDER");
        CustomColorButton button4=new CustomColorButton(new Color(255,228,225),Color.BLACK);
        button4.setText("RETRIEVE ORDER");
        button4.setLocation(470,30);
        button4.setSize(200,50);
 //       button4.setBackground(new Color(255,228,225));
        button4.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
 //       button4.setFont(new Font("TIMES NEW ROMAN",30,20));
//        button4.setForeground(Color.BLACK);
        button4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                call_order_screen();
            }
        });
  //      button4.setBorder(BorderFactory.createRaisedBevelBorder());
        pa_inner.add(button4);
        
  //      JButton button5=new JButton("LOCK SCREEN");
        CustomColorButton button5=new CustomColorButton(new Color(255,215,0),Color.BLACK);
        button5.setText("LOCK SCREEN");
        button5.setLocation(470,150);
        button5.setSize(200,50);
  //      button5.setBackground(new Color(255,215,0));
//        button5.setFont(new Font("TIMES NEW ROMAN",30,20));
        button5.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
 //       button5.setForeground(Color.BLACK);
 //       button5.setBorder(BorderFactory.createRaisedBevelBorder());
        button5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
 //          MyProjects project=new MyProjects("ECLIPSE ENTERPRISE");
 //          project.setVisible(true);     
 //          getContentPane().setVisible(false);
 //          getContentPane().setEnabled(false);
          lockScreen();      
            Cancel();
            }
        });
        pa_inner.add(button5);
        
        CustomColorButton button6=new CustomColorButton(Color.BLACK,Color.WHITE);
        button6.setText("SAVE ORDER");
        button6.setLocation(230,150);
        button6.setSize(200,50);
        button6.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
        button6.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int option=JOptionPane.showConfirmDialog(null,"Save Order?",
                  "Save Order",JOptionPane.YES_NO_OPTION);
           if(option==0){
               save_order_option();
               
           }
            }
        });
        pa_inner.add(button6);
     
        
        Box location_box=Box.createHorizontalBox();
        
        field=new JTextField("0.0000");
        
        field.setHorizontalAlignment(SwingConstants.RIGHT);
        field.setSize(200,30);
        field.setEditable(false);
        field.setBackground(Color.BLACK);
        field.setForeground(new Color(247,220,111));
        field.setFont(new Font("SERIF",Font.BOLD,28));
  
        
        
        JLabel label1=new JLabel("TOTAL:");
        label1.setFont(new Font("TAHOMA",Font.BOLD,18));
       // label1.setForeground(new Color(177,171,97));
        label1.setForeground(new Color(247,220,111));
        label1.setSize(200,30);
        
        
         JLabel label2=new JLabel("SH");
        label2.setFont(new Font("TAHOMA",Font.BOLD,18));
     //   label2.setLocation(480,510);
        label2.setForeground(new Color(247,220,111));
        label2.setSize(200,30);
   //     pa.add(label2);
        JButton qty_button=new JButton("QUANTITY");
        qty_button.setSize(200,50);
        qty_button.setForeground(Color.BLACK);
        qty_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
        qty_button.setLocation(0,260);
        qty_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int selected=myTable.getSelectedRowCount();
                if((myTable.getSelectedRowCount()>0) && ((myTable.getValueAt(myTable.getSelectedRow(),0))!=null)){
                   displayQtyScreen();
                }
            }
        });
        pa_inner.add(qty_button);
        JPanel location_panel=new JPanel();
        location_panel.setLayout(new BorderLayout());
        location_panel.setBackground(new Color(0,0,30));
        location_panel.setBorder(BorderFactory.createRaisedBevelBorder());
        Box pa_box=Box.createHorizontalBox();
  //      pa_box.setBackground(new Color(0,0,30));
        JLabel location_label=new JLabel("LOCATION: RESTAURANT   TILL NO:1");
        location_label.setForeground(Color.GREEN);
        location_label.setBackground(Color.GREEN);
        location_label.setSize(200,70);
        location_label.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
        location_panel.add(BorderLayout.NORTH,location_label);
        pa.add(BorderLayout.NORTH,location_panel);
        
        Box Box1=Box.createHorizontalBox();
        Box1.add(label1);
        Box1.add(label2);
        Box1.add(Box.createHorizontalStrut(20));
        Box1.add(field);
        
 //       Box1.add(label3);
  //      Box1.add(field2);
        panel2.add(BorderLayout.SOUTH,Box1);
        
        Box box2=Box.createHorizontalBox();
        JLabel newLabel=new JLabel("SALESMAN: ");
 //     newLabel.setFont(new Font("TIMES NEW ROMAN",30,20));
        newLabel.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
        newLabel.setForeground(Color.GREEN);
        newLabel.setSize(200,30);
        
         newLabel2=new JLabel("");
 //       newLabel2.setFont(new Font("TIMES NEW ROMAN",30,20));
         newLabel2.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
        newLabel2.setForeground(Color.WHITE);
        newLabel2.setSize(200,30);
        box2.add(newLabel);
        box2.add(newLabel2);
     //   panel2.add(BorderLayout.NORTH,box2);
        Box box3=Box.createHorizontalBox();
        JLabel newLabel3=new JLabel("CASHIER:");
  //      newLabel3.setFont(new Font("TIMES NEW ROMAN",50,30));
        newLabel3.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
        newLabel3.setForeground(Color.GREEN);
        newLabel3.setSize(200,30);
        
        JLabel label4=new JLabel("");
 //       label4.setFont(new Font("TIMES NEW ROMAN",50,30));
        label4.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
        label4.setForeground(Color.WHITE);
        label4.setSize(200,30);
        
 //       box3.add(newLabel3);
 //       box3.add(label4);
        
        Box bigBox=Box.createHorizontalBox();
 //       bigBox.add(box3);
        bigBox.add(box2);
        panel2.add(BorderLayout.NORTH,bigBox);
        JPanel lastPanel=new JPanel();
        lastPanel.setLayout(new GridLayout(3,3,0,0));
        lastPanel.setBackground(Color.YELLOW);
        overallPanel.add(panel2);
    //    getContentPane().add(panel1);
        pa.add(BorderLayout.CENTER,pa_inner);
        overallPanel.add(pa);
        internal.add(overallPanel);
        
        
    //    getContentPane().add(lastPanel);
        panel1=new JPanel();
        panel1.setBorder(BorderFactory.createRaisedBevelBorder());
 //       panel1.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        panel1.setLayout(null);
        
        
   //     JButton price_1_button=new JButton("Price 1:");
        CustomColorButton price_1_button=new CustomColorButton(Color.BLACK,Color.YELLOW);
        price_1_button.setText("Price 1:");
        price_1_button.setLocation(1150,25);
        price_1_button.setSize(200,35);
        price_1_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
   //     price_1_button.setForeground(Color.YELLOW);
  //      price_1_button.setBackground(Color.BLACK);
        panel1.add(price_1_button);
        
 //       JButton price_2_button=new JButton("price 2:");
        CustomColorButton price_2_button=new CustomColorButton(Color.BLACK,Color.YELLOW);
        price_2_button.setText("Price 2:");
        price_2_button.setLocation(1150,65);
        price_2_button.setSize(200,35);
        price_2_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
   //     price_2_button.setForeground(Color.YELLOW);
   //     price_2_button.setBackground(Color.BLACK);
        panel1.add(price_2_button);
        
//        JButton price_3_button=new JButton("price 3:");
        CustomColorButton price_3_button=new CustomColorButton(Color.BLACK,Color.YELLOW);
        price_3_button.setText("Price 3:");
        price_3_button.setLocation(1150,105);
        price_3_button.setSize(200,35);
        price_3_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
 //       price_3_button.setForeground(Color.YELLOW);
 //       price_3_button.setBackground(Color.BLACK);
        panel1.add(price_3_button);
        
//        JButton price_4_button=new JButton("price 4:");
        CustomColorButton price_4_button=new CustomColorButton(Color.BLACK,Color.YELLOW);
        price_4_button.setText("Price 4:");
        price_4_button.setLocation(1150,145);
        price_4_button.setSize(200,35);
        price_4_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
  //      price_4_button.setForeground(Color.YELLOW);
 //       price_4_button.setBackground(Color.BLACK);
        panel1.add(price_4_button);
        
  //      JButton price_5_button=new JButton("price 5:");
        CustomColorButton price_5_button=new CustomColorButton(Color.BLACK,Color.YELLOW);
        price_5_button.setText("Price 5:");
        price_5_button.setLocation(1150,185);
        price_5_button.setSize(200,35);
        price_5_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
    //    price_5_button.setForeground(Color.YELLOW);
    //    price_5_button.setBackground(Color.BLACK);
        panel1.add(price_5_button);
        
        
        tabbedpane=new JTabbedPane();
        stock_tabbed_pane=new JTabbedPane();
        
        stock_tabbed_pane=CreateStockPanel(0,1,16);
        panel1.add(stock_tabbed_pane);
        Box lower_box=Box.createHorizontalBox();
        JButton previousbutton=new JButton("BACK");
        previousbutton.setSize(250,50);
        previousbutton.setLocation(0,0);
        lower_box.add(previousbutton);
  
        
        panel1.setSize(575,500);
        panel1.setBackground(new Color(255,228,196));
        internal.add(panel1);
       
        internal.setSize(1362,740);
        internal.setSize(screenSize.width - 4,screenSize.height-28);
    //    internal.setSize(1352,700);
        internal.setResizable(false);
        internal.setVisible(true);
        internal.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        UIManager.put("InternalFrame.activeTitleBackground",Color.BLACK);
        UIManager.put("InternalFrame.activeTitleForeground",Color.BLACK);
        UIManager.put("InternalFrame.inactiveTitleBackground",Color.BLACK);
        UIManager.put("InternalFrame.inactiveTitleForeground",Color.BLACK);
        desktopPane.add(internal);
        getContentPane().add(desktopPane);
        
        
        
     
     JButton back_button=new JButton("BACK");
     back_button.setSize(50,20);
     back_button.setFont(new Font("TIMES NEW ROMAN",25,15));
     back_button.setForeground(Color.BLACK);
     back_button.setBackground(Color.BLACK);
     
     
     itemList_1=run_items();
     transaction_model=alter_transaction.get_transactions();
     order_model=alter_transaction.get_orders();
     invoice_model=alter_transaction.get_invoice_transactions();
     sales_account=alter_transaction.get_Sales_Account();
     purchase_account=alter_transaction.get_Purchase_Account();
     cash_account=alter_transaction.get_Cash_Account();
     stock_value_account=alter_transaction.get_StockValue_Account();
    
 /*    addWindowFocusListener(new WindowAdapter(){
        public void windowActivated(WindowEvent e){
            try{
           for(UIManager.LookAndFeelInfo info:    UIManager.getInstalledLookAndFeels()){
               if(("Nimbus").equals(info.getName())){
                   UIManager.setLookAndFeel(info.getClassName());
     //              SwingUtilities.updateComponentTreeUI(this);
     //              pack();
     //              getContentPane().validate();
     //              getContentPane().repaint();
               }
          }
      }
       catch(Exception ex){
           
      }
        }
        
     });*/
         
     
        
        this.setSize(screenSize.width,screenSize.height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
 
    
    
    public List<Item> getSaleItems(int g){
        
        List<Item> saleItems=null;
        
        try{
            getItems.setInt(1,g);
            resultSet=getItems.executeQuery();
            saleItems=new ArrayList<Item>();
            while(resultSet.next()){
                saleItems.add(new Item(resultSet.getString("Purchase_Des"),
                        resultSet.getDouble("price1"),
                        resultSet.getFloat("default_quantity"),
                        resultSet.getFloat("Qty_on_order"),
                        resultSet.getString("Scancode")));    
  /*              saleItems.add(new Item(resultSet.getInt("ItemCode"),resultSet.getString("item_id"),resultSet.getString("Scancode"),resultSet.getString("Purchase_Des"),resultSet.getString("Group_Name"),
                    resultSet.getString("Unit_desc"),resultSet.getString("pack_des"),resultSet.getString("Purchase"),resultSet.getString("Sales"),resultSet.getString("Other"),resultSet.getDouble("UnitCost"),
                    resultSet.getDouble("price1"),resultSet.getDouble("price2"),resultSet.getDouble("price3"),resultSet.getDouble("price4"),resultSet.getDouble("price5"),
                    resultSet.getInt("Qty_on_order"),resultSet.getBoolean("isPromote"),resultSet.getDouble("prom_price"),resultSet.getDouble("leadtime"),
                    resultSet.getDouble("Maxstock"),resultSet.getDouble("Minstock"),resultSet.getString("Purchase_Account"),
                    resultSet.getString("Sales_Account"),resultSet.getString("Stock_Value_Account")));*/
                
                
            }
        }
        catch(SQLException sqlexception){
            sqlexception.printStackTrace();
        }
        finally{
            try{
                resultSet.close();
            }
            catch(SQLException sqlexception){
                sqlexception.printStackTrace();
            }
        }
     return saleItems; 
     
    }
    public List<Item> run_items(){
       List<Item> saleItems=null;
        
        try{
  //          getItems.setInt(1,g);
            resultSet=runItem.executeQuery();
            saleItems=new ArrayList<Item>();
            while(resultSet.next()){
   /*             saleItems.add(new Item(resultSet.getString("Purchase_Des"),
                        resultSet.getDouble("price1"),
                        resultSet.getInt("default_quantity"),
                        resultSet.getInt("Qty_on_order"),
                        resultSet.getString("Scancode")));    */
                saleItems.add(new Item(resultSet.getInt("ItemCode"),resultSet.getString("item_id"),resultSet.getString("Scancode"),resultSet.getString("Purchase_Des"),resultSet.getString("Group_Name"),
                    resultSet.getString("Unit_desc"),resultSet.getString("pack_des"),resultSet.getString("Purchase"),resultSet.getString("Sales"),resultSet.getString("Other"),resultSet.getDouble("UnitCost"),
                    resultSet.getDouble("price1"),resultSet.getDouble("price2"),resultSet.getDouble("price3"),resultSet.getDouble("price4"),resultSet.getDouble("price5"),
                    resultSet.getFloat("Qty_on_order"),resultSet.getBoolean("isPromote"),resultSet.getDouble("prom_price"),resultSet.getDouble("leadtime"),
                    resultSet.getDouble("Maxstock"),resultSet.getDouble("Minstock"),resultSet.getString("Purchase_Account"),
                    resultSet.getString("Sales_Account"),resultSet.getString("Stock_Value_Account")));
                
                
            }
        }
        catch(SQLException sqlexception){
            sqlexception.printStackTrace();
        }
        finally{
            try{
                resultSet.close();
            }
            catch(SQLException sqlexception){
                sqlexception.printStackTrace();
            }
        }
     return saleItems; 
      
    }
    private List<StockGroups> get_Stock_Groups(){
        List<StockGroups>   stock_groups=null;
        try{
            resultSet=getStockGroups.executeQuery();
            stock_groups=new ArrayList<StockGroups>();
            while(resultSet.next()){
                stock_groups.add(new StockGroups(resultSet.getString("Group_Name"),resultSet.getInt("Group_ID")));
                
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try{
                resultSet.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return stock_groups;
    }
    
    
    public void Cancel(){
        this.dispose();
    }
    public void enableScreen(){
        this.setEnabled(true);
    }
    private void displayQtyScreen(){
        quantity=new Quantity();
        QuantityScreen qtyscreen=new QuantityScreen(this,true,quantity);
        qtyscreen.setUndecorated(true);
        qtyscreen.setVisible(true);
       
    }
    private void call_order_screen(){
        order_docno_list=new ArrayList<String>();
        OrderScreen order_screen=new OrderScreen(this,true,order_docno_list);
        order_screen.setUndecorated(true);
        
  
        order_screen.setVisible(true);
        
            
        
    }
   
    public void StartTransact(){
   
        switch(new_user_transaction.get_transaction_type()){
            case CASH:
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                transaction_catalogue=new Transaction_Catalogue(this,TransactionType.CASH);
                transaction_catalogue.execute();
                this.setCursor(Cursor.getDefaultCursor());
                break;
                
            case INVOICE:
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                transaction_catalogue=new Transaction_Catalogue(this,TransactionType.INVOICE);
                transaction_catalogue.execute();
                this.setCursor(Cursor.getDefaultCursor());
                break;
        }
        
           
 
    }
    public void stopTransact(){
        
    }
    public void start_transact_with_section(){
        SectionDialog section_dialog=new SectionDialog(this,true,new_user_transaction);
        section_dialog.setUndecorated(true);
        section_dialog.setVisible(true);
    }
    public void changeQuantity(){
        int row_numbers=model.getRowCount();
        for(int i=0;i<row_numbers;i++){
            if(model.getValueAt(i,2)!=null){
                try{
                String d=String.valueOf(model.getValueAt(i,2));
                double f=Double.parseDouble(d);
                String a=String.valueOf(model.getValueAt(i,1));
                double b=Double.parseDouble(a);
                double total=b*f;
                model.setValueAt(total, i,4);
                myTable.clearSelection();
                
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog((java.awt.Component)null,"ERROR: "+e.getMessage(),
                            "ERROR",javax.swing.JOptionPane.DEFAULT_OPTION);
     //               model.setRowCount(0);
      //              model.setRowCount(50);
                    
                }
            }
        }
       
    }
    private void CalculateTotal(){
        PayScreen pay=new PayScreen(this,true,total,new_user_transaction);
        pay.setUndecorated(true);
        pay.setVisible(true);
 //       pay.receiveTotal(total);
    }
   
   public void lockScreen(){
    MyProjects project=new MyProjects("ECLIPSE ENTERPRISE: CASHIER POINT","");
           project.setVisible(true);     
           getContentPane().setVisible(false);
           getContentPane().setEnabled(false);}
    public void putTitle(String title,String title2){
       internal.setTitle("CASHIER: "+title);
       internal.setFont(new Font("TIMES NEW ROMAN",30,40));
       newLabel2.setText(title2);
       
    }
    public void disable_Sales_Screen(){
        this.setEnabled(false);
    }
    public void setQuantity(){
        int z=model.getRowCount();
        int[] rowarray=new int[z];
        for(int j=0;j<z;j++){
        rowarray[j]=myTable.getSelectedRow();
        for(int i=0;i<z;i++){
        int row=myTable.getSelectedRow();
        if((row>-1)&&(i==rowarray[i])){
          myTable.getModel().setValueAt(quantity.getQuantity(),i,2);  
        }
  //      else{
            
  //      }
        }
    }
    changeQuantity();
    }
       
    public void unsetQuantity(){
        
    }
    
    private JTabbedPane CreatePanel(int x,int y,int groupID,int number_layout){
        
     itemList=getSaleItems(groupID);   
        JPanel tabbedPanel=new JPanel();
        tabbedPanel.setLayout(new GridLayout(4,5,10,10));
        tabbedPanel.setLocation(0,0);
        tabbedPanel.setSize(500,300);
        tabbedPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
       tabbedPanel.setBackground(new Color(255,222,173));
        
        tabbedpane.addTab("   PAGE "+y+"             " ,null,tabbedPanel,"FIRST PANEL");
        tabbedpane.setLocation(0,0);
        tabbedpane.setSize(1100,300);
        for(int i=x;i<itemList.size();i++){
            if((i+1)%22==0){
            an_item=itemList.get(i);
    //    item_button=new JButton(an_item.getName());
        item_button=new CustomColorButton(new Color(0,128,0),Color.WHITE);
        item_button.setText(an_item.getName());
        row=new Vector(5);
        item_button.setPreferredSize(new Dimension(40,10));
         map.put(item_button,new Integer(i));
        item_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,12));
       item_button.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               row=new Vector(5);
               Integer index=map.get(e.getSource());
               row.add(0,itemList.get(index).getName());
               row.add(1,itemList.get(index).getPrice());
               row.add(2,itemList.get(index).getQuantity());
               row.add(3,itemList.get(index).getScancode());
               row.add(4,itemList.get(index).getPrice()*itemList.get(index).getQuantity());
               model.insertRow(0,row);
           }
       });
        tabbedPanel.add(item_button); 
            CreatePanel((x+22),(y+1),groupID,number_layout+22);
  //          a=a+13;
  //         y=y+1;
            
            break;
            }
            
            else{
 //      itemList=getSaleItems(groupID); 
        an_item=itemList.get(i);
 //       item_button=new JButton(an_item.getName());
        item_button=new CustomColorButton(new Color(0,128,0),Color.WHITE);
        item_button.setText(an_item.getName());
        row=new Vector(5);
        item_button.setPreferredSize(new Dimension(40,10));
         map.put(item_button,new Integer(i));
         item_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,12));
       item_button.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               row=new Vector(5);
               Integer index=map.get(e.getSource());
               row.add(0,itemList.get(index).getName());
               row.add(1,itemList.get(index).getPrice());
               row.add(2,itemList.get(index).getQuantity());
               row.add(3,itemList.get(index).getScancode());
               row.add(4,itemList.get(index).getPrice()*itemList.get(index).getQuantity());
               model.insertRow(0,row);
   //            model.addRow(row);
         
           }
       });
        tabbedPanel.add(item_button);
  
     }
   
    number2=i;  
 //   System.out.println(""+i);
 //number=number_layout-i;
           
        }
   
      JButton back_button=new JButton("BACK");
     back_button.setSize(50,20);
     back_button.setFont(new Font("TIMES NEW ROMAN",25,15));
     back_button.setForeground(Color.BLACK);
     back_button.setBackground(Color.BLACK);
     back_button.setBorder(BorderFactory.createRaisedBevelBorder());
     back_button.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
             stock_tabbed_pane.removeAll();
             stock_tabbed_pane=CreateStockPanel(0,1,22);
         }
     });
    tabbedPanel.add(back_button); 
    number=number_layout-number2;
    for(int j=0;j<number-1;j++){
    JButton imaginary_button=new JButton();
    imaginary_button.setPreferredSize(new Dimension(50,20));
    imaginary_button.setVisible(false);
    imaginary_button.setEnabled(false);
    tabbedPanel.add(imaginary_button);
   }
//     }
   
    return tabbedpane;   
    }
    private JTabbedPane CreateStockPanel(int x,int y,int number_layout){
        
   //  itemList=getSaleItems();   
        groupList=get_Stock_Groups();
        JPanel tabbedPanel=new JPanel();
        tabbedPanel.setLayout(new GridLayout(4,5,10,10));
        tabbedPanel.setLocation(0,0);
        tabbedPanel.setSize(500,300);
        tabbedPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        tabbedPanel.setBackground(new Color(255,222,173));
        tabbedpane.addTab("   PAGE "+y+"             ",null,tabbedPanel,"FIRST PANEL");
        tabbedpane.setLocation(0,0);
        tabbedpane.setSize(1100,300);
        for(int i=x;i<groupList.size();i++){
            if((i+1)%22==0){
            a_group=groupList.get(i);
  //      item_button=new JButton(a_group.get_StockGroup_Name());
            item_button=new CustomColorButton(new Color(0,128,0),Color.WHITE);
        item_button.setText(a_group.get_StockGroup_Name());
        row=new Vector(5);
        item_button.setPreferredSize(new Dimension(20,10));
        item_button.setSize(20,10);
         map.put(item_button,new Integer(i));
          item_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,12));
       item_button.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
   //            row=new Vector(5);
  //             System.out.println(""+group_name);
               Integer index=map.get(e.getSource());
               group_name=groupList.get(index).get_StockGroup_Id();
                stock_tabbed_pane.removeAll();
                stock_tabbed_pane=CreatePanel(0,1,group_name,22);
           }
       });
        tabbedPanel.add(item_button);   
            CreateStockPanel((x+22),(y+1),number_layout+22);
  //          a=a+13;
  //         y=y+1;
            break;
            }
            
            else{
        a_group=groupList.get(i);
  //      item_button=new JButton(a_group.get_StockGroup_Name());
        item_button=new CustomColorButton(new Color(0,128,0),Color.WHITE);
        item_button.setText(a_group.get_StockGroup_Name());
        row=new Vector(5);
        item_button.setPreferredSize(new Dimension(40,10));
 //       item_button.setSize(50,20);
 //       item_button.setBackground(new Color(173,255,47));
          item_button.setBackground(new Color(0,128,0));
         map.put(item_button,new Integer(i));
 //       model.insertRow(0,row);
 //       item_button.setFont(new Font("TIMES NEW ROMAN",13,14));
         item_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,12));
         item_button.setForeground(Color.WHITE);
       item_button.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
 //              row=new Vector(5);
               Integer index=map.get(e.getSource());
    //           int group_index=groupList.get(index).get_StockGroup_Id();
               group_name=groupList.get(index).get_StockGroup_Id();
    
                 stock_tabbed_pane.removeAll();
                 stock_tabbed_pane=CreatePanel(0,1,group_name,22);
           }
       });
        tabbedPanel.add(item_button);
        
     }
            number2=i;
       
        }
        JButton back_button=new JButton();
     back_button.setSize(50,20);
     back_button.setVisible(false);
     back_button.setVisible(false);
    tabbedPanel.add(back_button); 
        number=number_layout-number2;
 //       System.out.println(""+number);
    for(int j=0;j<number-1;j++){
    JButton imaginary_button=new JButton();
    imaginary_button.setPreferredSize(new Dimension(50,20));
    imaginary_button.setVisible(false);
    imaginary_button.setEnabled(false);
    tabbedPanel.add(imaginary_button);
   }    
        
    return tabbedpane;    
    }
    public void save_Option(){
       
        try{
     //       alter_transaction.disable_commit();
              alter_transaction.disable_commit();
              alter_transaction.set_savePoint();
        String pc_id=new_user_transaction.get_location_id().substring(2,4)+new_user_transaction.get_till_id()+"%";
        String pc_full_id=new_user_transaction.get_location_id();
        System.out.println(""+pc_id);
        transaction_detail_entry=alter_transaction.check_transact_table("TCS",pc_id);
        if(transaction_detail_entry>0){
            from_database=alter_transaction.get_DocNo("TCS",pc_id);
            String date=alter_transaction.get_Date("TCS",pc_id);
            String retrieve_daily_docno=alter_transaction.get_daily_docno(date,"TCS",pc_id);
//            System.out.println("It is" +retrieve_daily_docno);
            String generate_docno=ex_set_till(from_database);
            String extract_docno=extract_docno(from_database);
            String daily_docno=gen_daily_docno(date,retrieve_daily_docno);
            double total_unit_cost_amount=0.0;
            for(int i=0;i<model.getRowCount();i++){
                String search_code=String.valueOf(myTable.getValueAt(i,3));
              for(int j=0;j<itemList_1.size();j++){
      //            System.out.println("search code from Order is "+search_code);
                  if(search_code.equals(itemList_1.get(j).getScancode())){
        //              System.out.println("search code is"+search_code);
        //              System.out.println("Item List is "+itemList_1.get(j).getScancode());
                      String string_qty=String.valueOf(myTable.getValueAt(i,2));
                      float float_qty=Float.parseFloat(string_qty);
                      total_unit_cost_amount=total_unit_cost_amount+(float_qty*itemList_1.get(j).getUnitCost());
                      alter_transaction.insert_transaction_detail(new_user_transaction.get_salesman_id(),
                              transaction_model.get(0).getVoucherType_ID(),generate_docno,i+1,transaction_model.get(0).get_Type_Id(),
                              itemList_1.get(j).get_gen_Itemcode(),itemList_1.get(j).get_gen_Itemcode(),itemList_1.get(j).get_gen_Itemcode(),
                              itemList_1.get(j).getUnitCost(),float_qty,itemList_1.get(j).getPrice1(),(itemList_1.get(j).getPrice1()*float_qty),
                              new_user_transaction.get_location_id(),daily_docno,itemList_1.get(j).getUnitCost(),
                              itemList_1.get(j).getPrice1(),new_user_transaction.get_section_id());
                         alter_transaction.adjust_transaction_qty(float_qty,itemList_1.get(j).get_gen_Itemcode());
                  }
              }
            }
            if(order_docno_list.size()>0){
                for(int i=0;i<order_docno_list.size();i++){
                    alter_transaction.set_is_fully_supplied_detail(order_docno_list.get(i));
                    alter_transaction.set_is_fully_supplied_summary(order_docno_list.get(i));
                }
            }
            double total_price_Amount=Double.parseDouble(field.getText());
            alter_transaction.insert_transact(transaction_model.get(0).get_Voucher_Description(),new_user_transaction.get_location_id(),
                              new_user_transaction.get_till_id(),transaction_model.get(0).get_Type_Id(),transaction_model.get(0).get_Voucher_ID(),
                              generate_docno,cash_account.get(0).getAccountNo(),sales_account.get(0).getAccountNo(),
                              stock_value_account.get(0).getAccountNo(),purchase_account.get(0).getAccountNo(),
                              total_price_Amount,total_unit_cost_amount);
            alter_transaction.insert_Transaction_summary(new_user_transaction.get_location_id(),new_user_transaction.get_till_id(),
                    transaction_model.get(0).get_Type_Id(),transaction_model.get(0).get_Voucher_ID(),generate_docno,new_user_transaction.get_salesman_id(),
                    cash_account.get(0).getAccountNo(),daily_docno,total_price_Amount,false,new_user_transaction.get_section_id());
            int voucher_transaction_index=alter_transaction.get_voucher_transactions(transaction_model.get(0).get_Type_Id(),
                                          new_user_transaction.get_location_id(),new_user_transaction.get_till_id());
            if(voucher_transaction_index > 0){
            /*  Supposed to insert code to pick location and string data and put it into alterTransactions.getVoucherTransactions()*/
            alter_transaction.delete_voucher_Transaction("TCS",new_user_transaction.get_location_id(),new_user_transaction.get_till_id());
            
            }
            alter_transaction.insert_voucher_transaction(transaction_model.get(0).get_Voucher_ID(),transaction_model.get(0).get_Voucher_Description(),
                    transaction_model.get(0).get_Type_Id(),transaction_model.get(0).get_Type_prefix(),extract_docno,true,
                    new_user_transaction.get_location_id(),new_user_transaction.get_till_id());
 //           alter_transaction.commit_transaction();
 //           alter_transaction.save_transactions();
            JOptionPane.showMessageDialog((java.awt.Component)null,"Transaction "+ generate_docno + " has been saved","Transaction"
                   + "Successful",JOptionPane.INFORMATION_MESSAGE);
            next_transaction();
            System.out.println("The size of order list is " + order_docno_list.size());
            order_docno_list.clear();
            alter_transaction.enable_auto_commit();
            
        }
        else{
          String date=alter_transaction.get_Date("TCS",pc_id);
     //     String retrieve_daily_docno=alter_transaction.get_daily_docno(date);
            String generate_docno=ex_set_till("0000000028");
            String extract_docno=extract_docno("0000000028");
            String daily_docno=gen_daily_docno(date,"0000000000");
            double total_unit_cost_amount=0.0;
            for(int i=0;i<model.getRowCount();i++){
                String search_code=String.valueOf(myTable.getValueAt(i,3));
              for(int j=0;j<itemList_1.size();j++){
                  if(search_code.equals(itemList_1.get(j).getScancode())){
                      String string_qty=String.valueOf(myTable.getValueAt(i,2));
                      float float_qty=Float.parseFloat(string_qty);//1
                      total_unit_cost_amount=total_unit_cost_amount+(float_qty*itemList_1.get(j).getUnitCost());
                      alter_transaction.insert_transaction_detail(new_user_transaction.get_salesman_id(),
                              transaction_model.get(0).getVoucherType_ID(),generate_docno,i+1,transaction_model.get(0).get_Type_Id(),
                              itemList_1.get(j).get_gen_Itemcode(),itemList_1.get(j).get_gen_Itemcode(),itemList_1.get(j).get_gen_Itemcode(),
                              itemList_1.get(j).getUnitCost(),float_qty,itemList_1.get(j).getPrice1(),(itemList_1.get(j).getPrice1()*float_qty),
                              new_user_transaction.get_location_id(),daily_docno,itemList_1.get(j).getUnitCost(),
                              itemList_1.get(j).getPrice1(),new_user_transaction.get_section_id());
                      alter_transaction.adjust_transaction_qty(float_qty,itemList_1.get(j).get_gen_Itemcode());//
                         
                  }
              }
            }
            if(order_docno_list.size()>0){
                for(int i=0;i<order_docno_list.size();i++){
                    alter_transaction.set_is_fully_supplied_detail(order_docno_list.get(i));
                    alter_transaction.set_is_fully_supplied_summary(order_docno_list.get(i));
                }
            }
            double total_price_Amount=Double.parseDouble(field.getText());
            alter_transaction.insert_transact(transaction_model.get(0).get_Voucher_Description(),new_user_transaction.get_location_id(),
                              new_user_transaction.get_till_id(),transaction_model.get(0).get_Type_Id(),transaction_model.get(0).get_Voucher_ID(),
                              generate_docno,cash_account.get(0).getAccountNo(),sales_account.get(0).getAccountNo(),
                              stock_value_account.get(0).getAccountNo(),purchase_account.get(0).getAccountNo(),
                              total_price_Amount,total_unit_cost_amount);
            alter_transaction.insert_Transaction_summary(new_user_transaction.get_location_id(),new_user_transaction.get_till_id(),
                    transaction_model.get(0).get_Type_Id(),transaction_model.get(0).get_Voucher_ID(),generate_docno,new_user_transaction.get_salesman_id(),
                    cash_account.get(0).getAccountNo(),daily_docno,total_price_Amount,false,new_user_transaction.get_section_id());
            int voucher_transaction_index=alter_transaction.get_voucher_transactions(transaction_model.get(0).get_Type_Id(),
                                          new_user_transaction.get_location_id(),new_user_transaction.get_till_id());
            if(voucher_transaction_index > 0){
            alter_transaction.delete_voucher_Transaction("TCS",new_user_transaction.get_location_id(),new_user_transaction.get_till_id());
            }
            alter_transaction.insert_voucher_transaction(transaction_model.get(0).get_Voucher_ID(),transaction_model.get(0).get_Voucher_Description(),
                    transaction_model.get(0).get_Type_Id(),transaction_model.get(0).get_Type_prefix(),extract_docno,true,
                    new_user_transaction.get_location_id(),new_user_transaction.get_till_id());
    //        alter_transaction.commit_transaction();
     //       alter_transaction.save_transactions();
            
            JOptionPane.showMessageDialog((java.awt.Component)null,"Transaction "+ generate_docno + " has been saved","Transaction"
                   + "Successful",JOptionPane.INFORMATION_MESSAGE);
            next_transaction();  
            order_docno_list.clear();
            alter_transaction.enable_auto_commit();
        }
        }
        catch(Exception e){
            
            JOptionPane.showMessageDialog(null,"CANNOT SAVE TRANSACTION:"+e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
            alter_transaction.rollback_to_savepoint();
            alter_transaction.enable_auto_commit();
            e.printStackTrace();
        }
    }
    public void save_order_option(){
        try{
       //     alter_transaction.disable_commit();
       //     alter_transaction.set_save_point();
            alter_transaction.disable_commit();
            alter_transaction.set_savePoint();
        String pc_id=new_user_transaction.get_location_id().substring(2,4)+new_user_transaction.get_till_id()+"%";
        System.out.println(""+pc_id);
        order_detail_entry=alter_transaction.check_order_table(pc_id);
        if(order_detail_entry>0){
            from_database=alter_transaction.get_order_docno(pc_id);
            String date=alter_transaction.get_order_date();
            String retrieve_daily_docno=alter_transaction.get_order_daily_docno(date);
            String generate_docno=ex_set_till(from_database);
            String extract_docno=extract_docno(from_database);
            String daily_docno=gen_daily_docno(date,retrieve_daily_docno);
            double total_unit_cost_amount=0.0;
            
            for(int i=0;i<model.getRowCount();i++){
                String search_code=String.valueOf(myTable.getValueAt(i,3));
              for(int j=0;j<itemList_1.size();j++){
                  if(search_code.equals(itemList_1.get(j).getScancode())){
                      String string_qty=String.valueOf(myTable.getValueAt(i,2));
                      float float_qty=Float.parseFloat(string_qty);
                      total_unit_cost_amount=total_unit_cost_amount+(float_qty*itemList_1.get(j).getUnitCost());
                      alter_transaction.insert_order_detail(order_model.get(0).get_Voucher_ID(),generate_docno,i+1,order_model.get(0).get_Type_Id(),
                              new_user_transaction.get_salesman_id(),itemList_1.get(j).get_gen_Itemcode(),itemList_1.get(j).get_gen_Itemcode(),
                              itemList_1.get(j).get_gen_Itemcode(),itemList_1.get(j).getUnitCost(),-float_qty,false,itemList_1.get(j).getPrice1(),(itemList_1.get(j).getPrice1()*float_qty),
                              new_user_transaction.get_location_id(),daily_docno,itemList_1.get(j).getUnitCost(),itemList_1.get(0).getPrice1(),true);
                      
                      /*alter_transaction.insert_transaction_detail(new_user_transaction.get_salesman_id(),
                              transaction_model.get(0).getVoucherType_ID(),generate_docno,i+1,transaction_model.get(0).get_Type_Id(),
                              itemList_1.get(j).get_gen_Itemcode(),itemList_1.get(j).get_gen_Itemcode(),itemList_1.get(j).get_gen_Itemcode(),
                              itemList_1.get(j).getUnitCost(),int_qty,itemList_1.get(j).getPrice1(),(itemList_1.get(j).getPrice1()*int_qty),
                              new_user_transaction.get_location_id(),daily_docno,itemList_1.get(j).getUnitCost(),
                              itemList_1.get(j).getPrice1(),new_user_transaction.get_section_id());*/
              //           alter_transaction.adjust_transaction_qty(int_qty,itemList_1.get(j).get_gen_Itemcode());
                  }
              }
            }
         double total_price_Amount=Double.parseDouble(field.getText());
         alter_transaction.insert_order_summary(new_user_transaction.get_location_id(),new_user_transaction.get_till_id(),
                 order_model.get(0).get_Type_Id(),order_model.get(0).get_Voucher_ID(),generate_docno,"",new_user_transaction.get_salesman_id(),
                total_price_Amount,false,daily_docno);
         int voucher_order_index=alter_transaction.get_voucher_transactions(order_model.get(0).get_Type_Id(),
                 new_user_transaction.get_location_id(),new_user_transaction.get_till_id());
         if(voucher_order_index > 0){
              alter_transaction.delete_voucher_Transaction("SO",new_user_transaction.get_location_id(),new_user_transaction.get_till_id());
         }
           
            alter_transaction.insert_voucher_transaction(order_model.get(0).get_Voucher_ID(),order_model.get(0).get_Voucher_Description(),
                    order_model.get(0).get_Type_Id(),order_model.get(0).get_Type_prefix(),extract_docno,true,new_user_transaction.get_location_id(),
                    new_user_transaction.get_till_id());
    //        alter_transaction.commit_transaction();
    //        alter_transaction.save_orders();
            JOptionPane.showMessageDialog((java.awt.Component)null,"Transaction "+ generate_docno + " has been saved","Transaction"
                   + "Successful",JOptionPane.INFORMATION_MESSAGE);
            next_transaction(); 
            alter_transaction.enable_auto_commit();
    }
        else{
            String date=alter_transaction.get_order_date();
      //      String retrieve_daily_docno=alter_transaction.get_order_daily_docno(date);
            String generate_docno=ex_set_till("0000000013");
            String extract_docno=extract_docno("0000000013");
            String daily_docno=gen_daily_docno(date,"0000000000");
            double total_unit_cost_amount=0.0;
            
            for(int i=0;i<model.getRowCount();i++){
                String search_code=String.valueOf(myTable.getValueAt(i,3));
              for(int j=0;j<itemList_1.size();j++){
                  if(search_code.equals(itemList_1.get(j).getScancode())){
                      String string_qty=String.valueOf(myTable.getValueAt(i,2));
                      float float_qty=Float.parseFloat(string_qty);
                      total_unit_cost_amount=total_unit_cost_amount+(float_qty*itemList_1.get(j).getUnitCost());
                      alter_transaction.insert_order_detail(order_model.get(0).get_Voucher_ID(),generate_docno,i+1,order_model.get(0).get_Type_Id(),
                              new_user_transaction.get_salesman_id(),itemList_1.get(j).get_gen_Itemcode(),itemList_1.get(j).get_gen_Itemcode(),
                              itemList_1.get(j).get_gen_Itemcode(),itemList_1.get(j).getUnitCost(),-float_qty,false,itemList_1.get(j).getPrice1(),(itemList_1.get(j).getPrice1()*float_qty),
                              new_user_transaction.get_location_id(),daily_docno,itemList_1.get(j).getUnitCost(),itemList_1.get(0).getPrice1(),true);
                      
                      /*alter_transaction.insert_transaction_detail(new_user_transaction.get_salesman_id(),
                              transaction_model.get(0).getVoucherType_ID(),generate_docno,i+1,transaction_model.get(0).get_Type_Id(),
                              itemList_1.get(j).get_gen_Itemcode(),itemList_1.get(j).get_gen_Itemcode(),itemList_1.get(j).get_gen_Itemcode(),
                              itemList_1.get(j).getUnitCost(),int_qty,itemList_1.get(j).getPrice1(),(itemList_1.get(j).getPrice1()*int_qty),
                              new_user_transaction.get_location_id(),daily_docno,itemList_1.get(j).getUnitCost(),
                              itemList_1.get(j).getPrice1(),new_user_transaction.get_section_id());*/
              //           alter_transaction.adjust_transaction_qty(int_qty,itemList_1.get(j).get_gen_Itemcode());
                  }
              }
            }
         double total_price_Amount=Double.parseDouble(field.getText());
         alter_transaction.insert_order_summary(new_user_transaction.get_location_id(),new_user_transaction.get_till_id(),
                 order_model.get(0).get_Type_Id(),order_model.get(0).get_Voucher_ID(),generate_docno,"",new_user_transaction.get_salesman_id(),
                 total_price_Amount,false,daily_docno);
            int voucher_order_index=alter_transaction.get_voucher_transactions(order_model.get(0).get_Type_Id(),
                 new_user_transaction.get_location_id(),new_user_transaction.get_till_id());
         if(voucher_order_index > 0){
              alter_transaction.delete_voucher_Transaction("SO",new_user_transaction.get_location_id(),new_user_transaction.get_till_id());
         }
            alter_transaction.insert_voucher_transaction(order_model.get(0).get_Voucher_ID(),order_model.get(0).get_Voucher_Description(),
                    order_model.get(0).get_Type_Id(),order_model.get(0).get_Type_prefix(),extract_docno,true,new_user_transaction.get_location_id(),
                    new_user_transaction.get_till_id());
   //         alter_transaction.commit_transaction();
   //         alter_transaction.save_orders();
            JOptionPane.showMessageDialog((java.awt.Component)null,"Transaction "+ generate_docno + " has been saved","Transaction"
                   + "Successful",JOptionPane.INFORMATION_MESSAGE);
            next_transaction();
            alter_transaction.enable_auto_commit();
        
        }
        }
        catch(Exception er){
            alter_transaction.rollback_to_savepoint();
           JOptionPane.showMessageDialog(null,"CANNOT SAVE TRANSACTION","ERROR",JOptionPane.ERROR_MESSAGE);
           alter_transaction.enable_auto_commit();
        }
           
    }
    
    private void save_invoice_option(){
        try{
            alter_transaction.disable_commit();
            alter_transaction.set_savePoint();
            String pc_id=new_user_transaction.get_location_id().substring(2,4)+new_user_transaction.get_till_id()+"%";
            String pc_full_id=new_user_transaction.get_location_id();
            System.out.println(""+pc_id);
            transaction_detail_entry=alter_transaction.check_transact_table("TIS",pc_id);
            if(transaction_detail_entry>0){
            from_database=alter_transaction.get_DocNo("TIS",pc_id);
            String date=alter_transaction.get_Date("TIS",pc_id);
            String retrieve_daily_docno=alter_transaction.get_daily_docno(date,"TIS",pc_id);
//            System.out.println("It is" +retrieve_daily_docno);
            String generate_docno=ex_set_till(from_database);
            String extract_docno=extract_docno(from_database);
            String daily_docno=gen_daily_docno(date,retrieve_daily_docno);
            double total_unit_cost_amount=0.0;
            for(int i=0;i<model.getRowCount();i++){
                String search_code=String.valueOf(myTable.getValueAt(i,3));
              for(int j=0;j<itemList_1.size();j++){
      //            System.out.println("search code from Order is "+search_code);
                  if(search_code.equals(itemList_1.get(j).getScancode())){
        //              System.out.println("search code is"+search_code);
        //              System.out.println("Item List is "+itemList_1.get(j).getScancode());
                      String string_qty=String.valueOf(myTable.getValueAt(i,2));
                      float float_qty=Float.parseFloat(string_qty);
                      total_unit_cost_amount=total_unit_cost_amount+(float_qty*itemList_1.get(j).getUnitCost());
                      alter_transaction.insert_transaction_detail(new_user_transaction.get_salesman_id(),
                              invoice_model.get(0).getVoucherType_ID(),generate_docno,i+1,invoice_model.get(0).get_Type_Id(),
                              itemList_1.get(j).get_gen_Itemcode(),itemList_1.get(j).get_gen_Itemcode(),itemList_1.get(j).get_gen_Itemcode(),
                              itemList_1.get(j).getUnitCost(),float_qty,itemList_1.get(j).getPrice1(),(itemList_1.get(j).getPrice1()*float_qty),
                              new_user_transaction.get_location_id(),daily_docno,itemList_1.get(j).getUnitCost(),
                              itemList_1.get(j).getPrice1(),new_user_transaction.get_section_id());
                         alter_transaction.adjust_transaction_qty(float_qty,itemList_1.get(j).get_gen_Itemcode());
                  }
              }
            }
            
            if(order_docno_list.size()>0){
                for(int i=0;i<order_docno_list.size();i++){
                    alter_transaction.set_is_fully_supplied_detail(order_docno_list.get(i));
                    alter_transaction.set_is_fully_supplied_summary(order_docno_list.get(i));
                }
            }
            
            double total_price_Amount=Double.parseDouble(field.getText());
            alter_transaction.insert_transact(invoice_model.get(0).get_Voucher_Description(),new_user_transaction.get_location_id(),
                              new_user_transaction.get_till_id(),invoice_model.get(0).get_Type_Id(),invoice_model.get(0).get_Voucher_ID(),
                              generate_docno,new_user_transaction.get_customer_no(),sales_account.get(0).getAccountNo(),
                              stock_value_account.get(0).getAccountNo(),purchase_account.get(0).getAccountNo(),
                              total_price_Amount,total_unit_cost_amount);
            
            alter_transaction.insert_Transaction_summary(new_user_transaction.get_location_id(),new_user_transaction.get_till_id(),
                    invoice_model.get(0).get_Type_Id(),invoice_model.get(0).get_Voucher_ID(),generate_docno,new_user_transaction.get_salesman_id(),
                    new_user_transaction.get_customer_no(),daily_docno,total_price_Amount,false,new_user_transaction.get_section_id());
            
             int voucher_transaction_index=alter_transaction.get_voucher_transactions(invoice_model.get(0).get_Type_Id(),
                                          new_user_transaction.get_location_id(),new_user_transaction.get_till_id());
             if(voucher_transaction_index > 0){
            /*  Supposed to insert code to pick location and string data and put it into alterTransactions.getVoucherTransactions()*/
            alter_transaction.delete_voucher_Transaction("TIS",new_user_transaction.get_location_id(),new_user_transaction.get_till_id());
            
            }
              alter_transaction.insert_voucher_transaction(invoice_model.get(0).get_Voucher_ID(),invoice_model.get(0).get_Voucher_Description(),
                    invoice_model.get(0).get_Type_Id(),invoice_model.get(0).get_Type_prefix(),extract_docno,true,
                    new_user_transaction.get_location_id(),new_user_transaction.get_till_id());
              JOptionPane.showMessageDialog((java.awt.Component)null,"Transaction "+ generate_docno + " has been saved","Transaction"
                   + "Successful",JOptionPane.INFORMATION_MESSAGE);
            next_transaction();
            System.out.println("The size of order list is " + order_docno_list.size());
            order_docno_list.clear();
            alter_transaction.enable_auto_commit();
            }
            
            else{
                String date=alter_transaction.get_Date("TIS",pc_id);
     //     String retrieve_daily_docno=alter_transaction.get_daily_docno(date);
            String generate_docno=ex_set_till("0000000000");
            String extract_docno=extract_docno("0000000000");
            String daily_docno=gen_daily_docno(date,"0000000000");
            double total_unit_cost_amount=0.0;
            for(int i=0;i<model.getRowCount();i++){
                String search_code=String.valueOf(myTable.getValueAt(i,3));
              for(int j=0;j<itemList_1.size();j++){
      //            System.out.println("search code from Order is "+search_code);
                  if(search_code.equals(itemList_1.get(j).getScancode())){
        //              System.out.println("search code is"+search_code);
        //              System.out.println("Item List is "+itemList_1.get(j).getScancode());
                      String string_qty=String.valueOf(myTable.getValueAt(i,2));
                      float float_qty=Float.parseFloat(string_qty);
                      total_unit_cost_amount=total_unit_cost_amount+(float_qty*itemList_1.get(j).getUnitCost());
                      alter_transaction.insert_transaction_detail(new_user_transaction.get_salesman_id(),
                              invoice_model.get(0).getVoucherType_ID(),generate_docno,i+1,invoice_model.get(0).get_Type_Id(),
                              itemList_1.get(j).get_gen_Itemcode(),itemList_1.get(j).get_gen_Itemcode(),itemList_1.get(j).get_gen_Itemcode(),
                              itemList_1.get(j).getUnitCost(),float_qty,itemList_1.get(j).getPrice1(),(itemList_1.get(j).getPrice1()*float_qty),
                              new_user_transaction.get_location_id(),daily_docno,itemList_1.get(j).getUnitCost(),
                              itemList_1.get(j).getPrice1(),new_user_transaction.get_section_id());
                         alter_transaction.adjust_transaction_qty(float_qty,itemList_1.get(j).get_gen_Itemcode());
                  }
              }
            }
            
            if(order_docno_list.size()>0){
                for(int i=0;i<order_docno_list.size();i++){
                    alter_transaction.set_is_fully_supplied_detail(order_docno_list.get(i));
                    alter_transaction.set_is_fully_supplied_summary(order_docno_list.get(i));
                }
            }
            
            double total_price_Amount=Double.parseDouble(field.getText());
            alter_transaction.insert_transact(invoice_model.get(0).get_Voucher_Description(),new_user_transaction.get_location_id(),
                              new_user_transaction.get_till_id(),invoice_model.get(0).get_Type_Id(),invoice_model.get(0).get_Voucher_ID(),
                              generate_docno,new_user_transaction.get_customer_no(),sales_account.get(0).getAccountNo(),
                              stock_value_account.get(0).getAccountNo(),purchase_account.get(0).getAccountNo(),
                              total_price_Amount,total_unit_cost_amount);
            
            alter_transaction.insert_Transaction_summary(new_user_transaction.get_location_id(),new_user_transaction.get_till_id(),
                    invoice_model.get(0).get_Type_Id(),invoice_model.get(0).get_Voucher_ID(),generate_docno,new_user_transaction.get_salesman_id(),
                    new_user_transaction.get_customer_no(),daily_docno,total_price_Amount,false,new_user_transaction.get_section_id());
            
             int voucher_transaction_index=alter_transaction.get_voucher_transactions(invoice_model.get(0).get_Type_Id(),
                                          new_user_transaction.get_location_id(),new_user_transaction.get_till_id());
             if(voucher_transaction_index > 0){
            /*  Supposed to insert code to pick location and string data and put it into alterTransactions.getVoucherTransactions()*/
            alter_transaction.delete_voucher_Transaction("TIS",new_user_transaction.get_location_id(),new_user_transaction.get_till_id());
            
            }
              alter_transaction.insert_voucher_transaction(invoice_model.get(0).get_Voucher_ID(),invoice_model.get(0).get_Voucher_Description(),
                    invoice_model.get(0).get_Type_Id(),invoice_model.get(0).get_Type_prefix(),extract_docno,true,
                    new_user_transaction.get_location_id(),new_user_transaction.get_till_id());
              JOptionPane.showMessageDialog((java.awt.Component)null,"Transaction "+ generate_docno + " has been saved","Transaction"
                   + "Successful",JOptionPane.INFORMATION_MESSAGE);
            next_transaction();
            System.out.println("The size of order list is " + order_docno_list.size());
            order_docno_list.clear();
            alter_transaction.enable_auto_commit();
            }
            
            
        }
        catch(Exception er){
            JOptionPane.showMessageDialog(null,"CANNOT SAVE TRANSACTION:"+er.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
            alter_transaction.rollback_to_savepoint();
            alter_transaction.enable_auto_commit();
            er.printStackTrace();
        }
    }
    public String ex_set_till(String from_database){

        String sub_from_database=from_database.substring(4);
   //     String sub_sub=String.valueOf(sub_from_database);
        
        database_parsed=Integer.parseInt(sub_from_database);
        database_parsed++;
//        String new_till=loc_id.substring(1);
        String k=String.format("%06d",database_parsed);
   //     String gen_doc=loc_id.substring(2)+till_id+k;
        String gen_doc=new_user_transaction.get_location_id().substring(2)+new_user_transaction.get_till_id()+k;
        return gen_doc;
    }
    public String extract_docno(String from_database){
        String sub_from_database=from_database.substring(4);
        new_database_parsed=Integer.parseInt(sub_from_database);
        new_database_parsed++;
        String k=String.format("%06d",new_database_parsed);
        return k;
    }
    public String gen_daily_docno(String date,String docno){
        String new_date="";
        if(date.equals("")){
            new_date="";
        }else{
         new_date=date.substring(0,10);
        }
        DateFormat dateFormat=new SimpleDateFormat("yyy-MM-dd");
        Date gen_date=new Date();
        String program_date=dateFormat.format(gen_date);
        if(program_date.equals(new_date)){
       //     String sub_from_database=docno.substring(4);
            System.out.println(docno);
            docno_database_parsed=Integer.parseInt(docno);
     //       docno_database_parsed=Integer.parseInt(docno);
            docno_database_parsed++;
            String k=String.format("%06d",docno_database_parsed);
            return k;
        }
        else{
            String k="000001";
            return k;
        }
    }
    private void next_transaction(){
        model.setRowCount(0);
        model.setRowCount(50);
    }
    
    public double getGrossTotal(){
       total=Double.parseDouble(field.getText());
       return total;
    }
    
    public void process_order_option(){
        import_orders();
    }
    private void import_orders(){
        int row_number=0;
        for(int i=0;i<order_docno_list.size();i++){
            List<Vector> selected_orders_by_docno=alter_transaction.get_imported_orders(order_docno_list.get(i));
            String row_data=String.valueOf(model.getValueAt(row_number,0));
 //           if(row_data.length()==0){
            for(int j=0;j<selected_orders_by_docno.size();j++){
    //        if(row_data==null){
                Vector order_row_vector=new Vector(5);
                order_row_vector.add(0,selected_orders_by_docno.get(j).get(0));
                order_row_vector.add(1,selected_orders_by_docno.get(j).get(1));
                order_row_vector.add(2,selected_orders_by_docno.get(j).get(2).toString().substring(1));
                order_row_vector.add(3,selected_orders_by_docno.get(j).get(3));
                order_row_vector.add(4,selected_orders_by_docno.get(j).get(4));
                
      //          System.out.println(""+selected_orders_by_docno.get(j).get(0));
                model.insertRow(row_number,order_row_vector);
                row_number++;
            }
 //           }
  //          else{
  //              row_number++;
  //          }
 
        }
    }
    public void process_no_order_option(){
        
    }
    public void set_sales_look_and_feel(){
        getContentPane().revalidate();
        getContentPane().repaint();
  
    }
    //Enables and disables Sections
    public void set_section_state(){
        section_state=new_user_transaction.get_is_use_section();
        System.out.println(""+section_state);
    }
    
  class Transaction_Catalogue extends SwingWorker<Boolean,Integer>{
      private TransactionType transaction_type;
      private JFrame Owner;
      public Transaction_Catalogue(JFrame Owner,TransactionType transaction_type){
          this.Owner=Owner;
          this.transaction_type=transaction_type;
          this.Owner.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      }
      
      @Override
      public Boolean doInBackground() throws Exception{
             setProgress(0);
             publish(20);
             switch(transaction_type){
                 case CASH:
                     /*waitFor(5000);
                     publish(100);
                     setProgress(100);*/
                     save_Option();
                     
                     
                     break;
                 case INVOICE:
                   /*  waitFor(5000);
                     publish(100);
                     setProgress(100);*/
                     save_invoice_option();
                     
                     break;
             }
           
          return true;       
      }
      
      @Override
      public void process(List<Integer> chunks){
          
      }
      
      @Override
      protected void done(){
          this.Owner.setCursor(Cursor.getDefaultCursor());
      }
      
        private void waitFor(int millis){
           try{
               Thread.sleep(millis);
           }
           catch(Exception ex){
               System.exit(0);
     
           }
       }
  }
   
    public static void main(String[] args){
      try{
           for(UIManager.LookAndFeelInfo info:    UIManager.getInstalledLookAndFeels()){
               if(("Nimbus").equals(info.getName())){
                   UIManager.setLookAndFeel(info.getClassName());
               }
          }
      }
       catch(Exception e){
           
      }
  //   new Trial("SALES SCREEN").setVisible(true);
   Trial trial=new Trial("",null);
   trial.setVisible(true);
   
    
}}

