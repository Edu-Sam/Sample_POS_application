/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.text.DecimalFormat;
import javax.swing.event.*;
public class OrderScreen extends JDialog {
    private DefaultTableModel model,model_1,model_2;
    private JTable myTable,myTable_1,myTable_2;
    private AlterTransaction alter_transaction;
    private DefaultComboBoxModel salesman_combo_model;
    private Vector salesman_vector,data_summary_vector,ordered_items_vector,orders_by_docno_vector;
    private List<Vector> salesman_list,data_summary_list,ordered_items_list,orders_by_docno_list;
    private JComboBox salesman_box;
//    private HashMap<JCheckBox,Integer> map=new HashMap<JCheckBox,Integer>();
    private List<String> docno_list;
    private JCheckBox item_box;
    private List<String> new_order_docno_list;
    private JTextField total_outstanding_text,total_selected_text,myTable_1_text;
    private DecimalFormat total_selected_text_format;
    public OrderScreen(JFrame owner,boolean Modal,List<String> order_docno_list){
        super(owner,Modal);
      //  order_docno_list=new ArrayList<String>();
     //   new_order_docno_list=new ArrayList<String>();
        new_order_docno_list=order_docno_list;
        //set_order_screen_outlook();    
  //      getContentPane().setLayout(null);
        
         try{
            for(UIManager.LookAndFeelInfo info:  UIManager.getInstalledLookAndFeels()){
                if(("Windows").equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                       
    //                SwingUtilities.updateComponentTreeUI(this);
     //               pack();
     //               getContentPane().revalidate();
     //               getContentPane().repaint();
                    
                }
            }
        }
        catch(Exception e){
            
        }
         getContentPane().setLayout(null);
         alter_transaction=new AlterTransaction();
  //      set_order_screen_outlook();
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
  //      set_order_screen_outlook();
        JPanel main_panel=new JPanel();
        main_panel.setLocation(0,0);
        main_panel.setSize(screenSize.width,screenSize.height);
        main_panel.setBorder(BorderFactory.createEtchedBorder());
        main_panel.setLayout(null);
        getContentPane().add(main_panel);
        
        JPanel topPanel=new JPanel();
        topPanel.setLocation(0,0);
        topPanel.setSize(screenSize.width,25);
        topPanel.setBackground(new Color(100,149,237));
        topPanel.setLayout(null);
        main_panel.add(topPanel);
        
        JLabel outstanding_sales_orders=new JLabel("Outstanding Sales Orders");
        outstanding_sales_orders.setLocation(0,5);
        outstanding_sales_orders.setSize(200,16);
        outstanding_sales_orders.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        topPanel.add(outstanding_sales_orders);
        
        JLabel outstanding_orders_label=new JLabel("Outstanding orders");
        outstanding_orders_label.setLocation(0,30);
        outstanding_orders_label.setSize(200,20);
        outstanding_orders_label.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
        outstanding_orders_label.setForeground(new Color(0,0,205));
        main_panel.add(outstanding_orders_label);
        
        JTextField find_doc_text=new JTextField();
        find_doc_text.setLocation(0,60);
        find_doc_text.setSize(200,22);
        find_doc_text.setFont(new Font("SERIF",Font.PLAIN,14));
        main_panel.add(find_doc_text);
        
        JButton find_doc_button=new JButton("Find Doc #");
        find_doc_button.setLocation(230,60);
        find_doc_button.setSize(200,25);
        find_doc_button.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
        main_panel.add(find_doc_button);
        
        JButton clear_filter_button=new JButton("clear filter");
        clear_filter_button.setLocation(450,60);
        clear_filter_button.setSize(200,25);
        clear_filter_button.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
        main_panel.add(clear_filter_button);
        
        JLabel salesman_label=new JLabel("Salesman");
        salesman_label.setLocation(5,110);
        salesman_label.setSize(200,20);
        salesman_label.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
        main_panel.add(salesman_label);
        
        get_salesman();
        salesman_box=new JComboBox(salesman_combo_model);
        salesman_box.setLocation(160,90);
        salesman_box.setSize(490,50);
        salesman_box.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,18));
        salesman_box.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
  //              myTable.clearSelection();
                get_salesman_order_data();
            }
        });
        main_panel.add(salesman_box);
        
        String[] header={"S","Salesman","Table#","Doc#","Amount","Date"};
        model=new DefaultTableModel(header,60){
            @Override
            public Class getColumnClass(int c){
                Class clazz=String.class;
                switch(c){
                    case 0:
                        clazz=Boolean.class;
                        break;
                }
                return clazz;
            }
            
                 
        };
        myTable=new JTable(model){
            @Override
            public boolean isCellEditable(int args0,int args1){
                return args1==0;
            }
  /*          @Override
            public Class getColumnClass(int c){
                Class clazz=String.class;
                switch(c){
                    case 0:
                        clazz=Boolean.class;
                        break;
                }
                return clazz;
            }*/
            @Override
            public TableCellRenderer getCellRenderer(int row,int column){
                int modelColumn=convertColumnIndexToView(column);
                int modelRow=convertRowIndexToView(row);
                if((modelColumn==0)&&(row>=data_summary_list.size())){
                    return getDefaultRenderer(String.class);
                   
                    
                }
                else{
                return super.getCellRenderer(row,column);
                }
            }
            
        };
        
        myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         myTable.getColumnModel().getColumn(0).setPreferredWidth(50);
         myTable.getColumnModel().getColumn(1).setPreferredWidth(170);
         myTable.getColumnModel().getColumn(2).setPreferredWidth(80);
         myTable.getColumnModel().getColumn(3).setPreferredWidth(100);
         myTable.getColumnModel().getColumn(4).setPreferredWidth(90);
         myTable.getColumnModel().getColumn(5).setPreferredWidth(150);
         
         myTable.setRowHeight(18);
         myTable.setFont(new Font("ARIAL",Font.PLAIN,12));
         myTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
             @Override
             public void valueChanged(ListSelectionEvent e){
                 model_1.setRowCount(0);
                 model_1.setRowCount(40);
                     ordered_items_list=new ArrayList<Vector>();
                     if(myTable.getSelectedRow()==-1){
                         
                     }
                     else{
                     String docno=String.valueOf(model.getValueAt((myTable.getSelectedRow()),3));
                     ordered_items_list=alter_transaction.get_order_items(docno);
                     for(int i=0;i<ordered_items_list.size();i++){
                         model_1.insertRow(i,ordered_items_list.get(i));
                         
                     }
    //                 }
                 }
             }
         });
         
         JScrollPane pane=new JScrollPane(myTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                 ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
         pane.setLocation(10,150);
         pane.setSize(640,300);
         main_panel.add(pane);
         
         JLabel currently_selected_items_label=new JLabel("Currently Selecetd Items");
         currently_selected_items_label.setLocation(700,120);
         currently_selected_items_label.setSize(400,22);
         currently_selected_items_label.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,20));
         currently_selected_items_label.setForeground(Color.BLACK);
         main_panel.add(currently_selected_items_label);
         
         String[] header_1={"Description","Header1","Header1","Header1"};
         model_1=new DefaultTableModel(header_1,40);
         myTable_1=new JTable(model_1){
             @Override
             public boolean isCellEditable(int args0,int args1){
                 return false;
             }
         };
         myTable_1.getTableHeader().setFont(new Font("TIMES NEW ROMAN",Font.BOLD,15));
         myTable_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         myTable_1.getColumnModel().getColumn(0).setPreferredWidth(200);
         myTable_1.getColumnModel().getColumn(1).setPreferredWidth(75);
         myTable_1.getColumnModel().getColumn(2).setPreferredWidth(125);
         myTable_1.getColumnModel().getColumn(3).setPreferredWidth(150);
         
         myTable_1.setRowHeight(18);
         myTable_1.setFont(new Font("ARIAL",Font.PLAIN,12));
         myTable_1.getModel().addTableModelListener(new TableModelListener(){
             public void tableChanged(TableModelEvent e){
                 double selected_total=0.0;
                 for(int k=0;k<model_1.getRowCount();k++){
                     if(model_1.getValueAt(k,3)!=null){
                 String string_selected_total=String.valueOf(model_1.getValueAt(k,3));
                  double double_selected_total=Double.parseDouble(string_selected_total);
                  selected_total=selected_total+double_selected_total;
                 
                     }
                 }
                 myTable_1_text.setText(""+total_selected_text_format.format(selected_total));
             }
         });
         
         JScrollPane pane_1=new JScrollPane(myTable_1,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                 ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
         pane_1.setLocation(700,150);
         pane_1.setSize(550,150);
         main_panel.add(pane_1);
         
         JLabel myTable_1_total=new JLabel("Total");
         myTable_1_total.setLocation(700,350);
         myTable_1_total.setSize(200,18);
         myTable_1_total.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,17));
         main_panel.add(myTable_1_total);
         
         myTable_1_text=new JTextField("0.0000");
         myTable_1_text.setLocation(950,350);
         myTable_1_text.setSize(400,30);
         myTable_1_text.setFont(new Font("SERIF",Font.BOLD,18));
         myTable_1_text.setForeground(Color.BLACK);
         main_panel.add(myTable_1_text);
         
         JButton refresh_selected_orders_button=new JButton("Refresh Selected Orders");
         refresh_selected_orders_button.setLocation(700,400);
         refresh_selected_orders_button.setSize(650,60);
         refresh_selected_orders_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,20));
         refresh_selected_orders_button.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 get_orders_by_docno();
             }
         });
         main_panel.add(refresh_selected_orders_button);
         
         JCheckBox click_to_select_box=new JCheckBox();
         click_to_select_box.setLocation(10,470);
         click_to_select_box.setSize(20,20);
         main_panel.add(click_to_select_box);
         
         JLabel click_to_select_label=new JLabel("Click to Select/De Select all orders");
         click_to_select_label.setLocation(40,470);
         click_to_select_label.setSize(300,16);
         click_to_select_label.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,15));
         click_to_select_label.setForeground(Color.BLACK);
         main_panel.add(click_to_select_label);
         
         JLabel list_of_items_label=new JLabel("List of items in the selected order #");
         list_of_items_label.setLocation(40,500);
         list_of_items_label.setSize(350,22);
         list_of_items_label.setFont(new Font("ARIAL",Font.BOLD,18));
         list_of_items_label.setForeground(Color.RED);
         main_panel.add(list_of_items_label);
         
         
        String[] header_3={"Order","Item","Qty","Unit Price","Unit Discount","Amount"};
        total_selected_text_format=new DecimalFormat("#0.0000");
        model_2=new DefaultTableModel(header_3,40);
        myTable_2=new JTable(model_2){
            @Override
            public boolean isCellEditable(int args0,int args1){
                return false;
            }
        };
        
        myTable_2.setRowHeight(18);
         myTable_2.setFont(new Font("ARIAL",Font.PLAIN,12));
         myTable_2.getModel().addTableModelListener(new TableModelListener(){
             public void tableChanged(TableModelEvent e){
                 double selected_total=0.0;
                 for(int k=0;k<model_2.getRowCount();k++){
                     if(model_2.getValueAt(k,5)!=null){
                 String string_selected_total=String.valueOf(model_2.getValueAt(k,5));
                  double double_selected_total=Double.parseDouble(string_selected_total);
                  selected_total=selected_total+double_selected_total;
                 
                     }
                 }
                 total_selected_text.setText(""+total_selected_text_format.format(selected_total));
             }
         });
         
         
        JScrollPane pane_2=new JScrollPane(myTable_2,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        pane_2.setLocation(40,530);
        pane_2.setSize(1300,150);
        main_panel.add(pane_2);
        
        JLabel total_selected_label=new JLabel("Total Selected");
        total_selected_label.setLocation(0,690);
        total_selected_label.setSize(200,15);
        total_selected_label.setFont(new Font("ARIAL",Font.BOLD,18));
        total_selected_label.setForeground(Color.BLACK);
        main_panel.add(total_selected_label);
        
        total_selected_text=new JTextField("0.0000");
        total_selected_text.setLocation(130,690);
        total_selected_text.setSize(220,30);
        total_selected_text.setFont(new Font("ARIAL",Font.BOLD,18));
        total_selected_text.setForeground(Color.BLACK);
        main_panel.add(total_selected_text);
        
        JLabel total_outstanding_label=new JLabel("Total Outstanding");
        total_outstanding_label.setLocation(0,730);
        total_outstanding_label.setSize(200,17);
        total_outstanding_label.setFont(new Font("ARIAL",Font.BOLD,18));
        total_outstanding_label.setForeground(Color.BLACK);
        main_panel.add(total_outstanding_label);
        
        total_outstanding_text=new JTextField("0.0000");
        total_outstanding_text.setLocation(160,725);
        total_outstanding_text.setSize(220,30);
        total_outstanding_text.setFont(new Font("ARIAL",Font.BOLD,18));
        total_outstanding_text.setForeground(Color.BLACK);
        main_panel.add(total_outstanding_text);
        
        
        JButton keyboard_button=new JButton("Keyboard");
        keyboard_button.setLocation(450,690);
        keyboard_button.setSize(250,70);
        keyboard_button.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,20));
        main_panel.add(keyboard_button);
        
        JButton ok_button=new JButton("Ok");
        ok_button.setLocation(720,690);
        ok_button.setSize(300,70);
        ok_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,20));
        ok_button.setForeground(Color.BLACK);
        ok_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              selected_order_option();  
            }
        });
        main_panel.add(ok_button);
        
        JButton cancel_button=new JButton("Cancel");
        cancel_button.setLocation(1040,690);
        cancel_button.setSize(300,70);
        cancel_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,20));
        cancel_button.setForeground(Color.BLACK);
        cancel_button.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
       //        quit();
      /*         if(getOwner()!=null){
                   ((OrderOutlookInterface)getOwner()).set_sales_look_and_feel();
               }*/
               no_selected_order_option();
           } 
        });
        main_panel.add(cancel_button);
        get_salesman_order_data();
        
        this.setSize(screenSize.width,screenSize.height);
        
        
  //      set_order_screen_outlook();
        setLocationRelativeTo(null);
        
    }
    private void get_salesman(){
        salesman_list=alter_transaction.get_salesman();
        salesman_vector=new Vector();
        Vector all_salesman_vector=new Vector(2);
        all_salesman_vector.add(0,"<ALL>");
        all_salesman_vector.add(1,"<ALL>");
    //    salesman_vector.set(0,all_salesman_vector);
 //       salesman_vector.setElementAt(all_salesman_vector,1);
        salesman_vector.add(all_salesman_vector.get(0));
        for(int i=0;i<salesman_list.size();i++){
            salesman_vector.add(salesman_list.get(i).get(0));
        }
        
        salesman_combo_model=new DefaultComboBoxModel(salesman_vector);
        
        
    }
    private void get_orders_by_docno(){
        model_2.setRowCount(0);
        model_2.setRowCount(40);
        docno_list=new ArrayList<String>();
        for(int i=0;i<model.getRowCount();i++){
            String string_box=String.valueOf(model.getValueAt(i,0));
            boolean boolean_box=Boolean.parseBoolean(string_box);
            String string_docno=String.valueOf(model.getValueAt(i,3));
            if((boolean_box==true)&&(string_docno!=null)){
            String new_docno=String.valueOf(model.getValueAt(i,3));
            docno_list.add(new_docno);
   //         System.out.println(""+new_docno);
            }
        }
        int row_number=0;
        double selected_total=0.0;
        for(int j=0;j<docno_list.size();j++){
            orders_by_docno_list=alter_transaction.get_orders_by_docno(docno_list.get(j));
              for(int k=0;k<orders_by_docno_list.size();k++){
                  orders_by_docno_vector=new Vector(6);
                  orders_by_docno_vector.add(0,orders_by_docno_list.get(k).get(0));
                  orders_by_docno_vector.add(1,orders_by_docno_list.get(k).get(1));
                  orders_by_docno_vector.add(2,orders_by_docno_list.get(k).get(2).toString().substring(1));
                  orders_by_docno_vector.add(3,orders_by_docno_list.get(k).get(3));
                  orders_by_docno_vector.add(4,orders_by_docno_list.get(k).get(4));
                  orders_by_docno_vector.add(5,orders_by_docno_list.get(k).get(5));
                  model_2.insertRow(row_number,orders_by_docno_vector);
                  
                  
                  
                  row_number++;
                  model_2.fireTableDataChanged();
              }
        }
        
    }
    private void get_salesman_order_data(){
        
        data_summary_list=new ArrayList<Vector>();
        model.setRowCount(0);
        model.setRowCount(60);
        double total=0.0;
        String salesman_name=String.valueOf(salesman_box.getSelectedItem());
        if(salesman_name.equalsIgnoreCase("<ALL>")){
            data_summary_list=alter_transaction.get_all_salesman_by_name();
            for(int i=0;i<data_summary_list.size();i++){
                data_summary_vector=new Vector(5);
                String date=data_summary_list.get(i).get(3).toString().substring(8,10)+"/"+data_summary_list.get(i).get(3).toString().substring(5,7)+
                    "/"+data_summary_list.get(i).get(3).toString().substring(0,4);
            String time=data_summary_list.get(i).get(3).toString().substring(11,19);
           
            
   
  //          data_summary_vector.add(0,false);
            data_summary_vector.add(0,false);
            data_summary_vector.add(1,data_summary_list.get(i).get(0));
            data_summary_vector.add(2,"");
            data_summary_vector.add(3,data_summary_list.get(i).get(1));
            data_summary_vector.add(4,data_summary_list.get(i).get(2));
            data_summary_vector.add(5,date+"  "+time);
            model.insertRow(i,data_summary_vector);
            model.fireTableDataChanged();
            
           
    //        myTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(item_box));
    //       model.setValueAt(item_box,i,0);
            
            }
            for(int j=0;j<data_summary_list.size();j++){
              String string_total=String.valueOf(data_summary_list.get(j).get(2));
              double double_total=Double.parseDouble(string_total);
              total=total+double_total;
              total_outstanding_text.setText(""+total_selected_text_format.format(total));
            }
            }
        else{
            data_summary_list=alter_transaction.get_individual_salesman(salesman_name);
            for(int i=0;i<data_summary_list.size();i++){
                data_summary_vector=new Vector(5);
                String date=data_summary_list.get(i).get(3).toString().substring(8,10)+"/"+data_summary_list.get(i).get(3).toString().substring(5,7)+
                    "/"+data_summary_list.get(i).get(3).toString().substring(0,4);
            String time=data_summary_list.get(i).get(3).toString().substring(11,19);
           
            
   
            data_summary_vector.add(0,false);
            data_summary_vector.add(1,data_summary_list.get(i).get(0));
            data_summary_vector.add(2,"");
            data_summary_vector.add(3,data_summary_list.get(i).get(1));
            data_summary_vector.add(4,data_summary_list.get(i).get(2));
            data_summary_vector.add(5,date+"  "+time);
            model.insertRow(i,data_summary_vector);
            model.fireTableDataChanged();
            
    //        myTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(item_box));
    //       model.setValueAt(item_box,i,0);
            
            }
            
        }
    }
    private void quit(){
        
        this.dispose();
    }
    private void selected_order_option(){
        int counter=model.getRowCount();
        for(int i=0;i<counter;i++){
            String string_box=String.valueOf(model.getValueAt(i,0));
            boolean boolean_box=Boolean.parseBoolean(string_box);
            String string_docno=String.valueOf(model.getValueAt(i,3));
            if((boolean_box==true)&&(string_docno!=null)){
            String new_docno=String.valueOf(model.getValueAt(i,3));
            new_order_docno_list.add(new_docno);
        }
    }
        if(getOwner()!=null){
            ((Order_interface)getOwner()).process_order_option();
    //        System.out.println(""+new_order_docno_list.get(0));
            dispose();
        }
    }
    private void no_selected_order_option(){
        if(getOwner()!=null){
            ((Order_interface)getOwner()).process_no_order_option();
            dispose();
        }
    }
    
    public static void main(String[] args){
        try{
         for(UIManager.LookAndFeelInfo  info: UIManager.getInstalledLookAndFeels()){
//             System.out.println(info.getClassName());
             if(("Windows").equals(info.getName())){
                 UIManager.setLookAndFeel(info.getClassName());
             }}}
         catch(Exception e){
             
         }
        OrderScreen order_screen=new OrderScreen(null,false,null);
        order_screen.setUndecorated(true);
        order_screen.setVisible(true);
        
  //      Trial trial=new Trial("",null);
        
    }
}
