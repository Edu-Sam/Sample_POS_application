/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;
public class InvoiceScreen extends JDialog{
    private DecimalFormat decimal_format,number_format;
    private TenderedAdjusted tender_two;
    private List<Customer> customer_list,customer_list_by_name;
    private DefaultComboBoxModel customer_box_model;
    private Vector customer_vector;
    private AlterTransaction alter_transaction;
    private TenderedAdjusted tender_adjusted;
    private double invoice_amount;
    private JComboBox account_text;
    private JTextField address1_text,address2_text,telephone_text,email_text;
    private JTextField invoice_amount_text,amount_withstanding_text;
    private JTextArea user_area;
    private UserTransaction new_user_transaction;
    private double default_value=0.0;
    public InvoiceScreen(Dialog owner,boolean modal,TenderedAdjusted tender_adjusted){
        super(owner,modal);
        alter_transaction=new AlterTransaction();
        decimal_format=new DecimalFormat("#0.0000");
        this.tender_adjusted=tender_adjusted;
      //  this.new_user_transaction=new_user_transaction;
        setLayout(null);
        buildInvoice(this.tender_adjusted);
       setSize(700,500); 
       setLocationRelativeTo(owner);
    }
    
    private void buildInvoice(TenderedAdjusted tender_adjusted){
        JPanel main_panel=new JPanel();
        main_panel.setLayout(null);
        main_panel.setLocation(0,0);
        main_panel.setSize(700,500);
        main_panel.setBorder(BorderFactory.createEtchedBorder());
        main_panel.setBackground(Color.YELLOW);
        add(main_panel);
        
        JPanel header_panel=new JPanel();
        header_panel.setLocation(0,0);
        header_panel.setSize(700,25);
        header_panel.setBackground(new Color(100,149,237));
        header_panel.setLayout(null);
        main_panel.add(header_panel);
        
        JPanel top_panel=new JPanel();
        top_panel.setLocation(5,52);
        top_panel.setSize(500,80);
        top_panel.setBackground(new Color(255,228,181));
        top_panel.setBorder(BorderFactory.createEtchedBorder());
        top_panel.setLayout(null);
        main_panel.add(top_panel);
        
        JLabel account_label=new JLabel("Account:");
        account_label.setLocation(40,30);
        account_label.setSize(300,15);
        account_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        top_panel.add(account_label);
        
        getCustomers();
        account_text=new JComboBox(customer_vector);
        account_text.setLocation(110,25);
        account_text.setSize(250,30);
        account_text.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,13));
        account_text.setSelectedItem(null);
        account_text.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                get_customer_data();
            }
        });
        
        top_panel.add(account_text);
        
        JPanel second_panel=new JPanel();
        second_panel.setLocation(5,150);
        second_panel.setSize(530,250);
        second_panel.setBackground(new Color(255,228,181));
        second_panel.setBorder(BorderFactory.createEtchedBorder());
        second_panel.setLayout(null);
        main_panel.add(second_panel);
        
        JLabel address1_label=new JLabel("Address 1:");
        address1_label.setLocation(10,30);
        address1_label.setSize(150,15);
        address1_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        second_panel.add(address1_label);
        
        address1_text=new JTextField();
        address1_text.setLocation(80,25);
        address1_text.setSize(220,30);
        address1_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        address1_text.setEditable(false);
        second_panel.add(address1_text);
        
        JLabel address2_label=new JLabel("Address 2:");
        address2_label.setLocation(10,70);
        address2_label.setSize(300,15);
        address2_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        second_panel.add(address2_label);
        
        address2_text=new JTextField();
        address2_text.setLocation(80,65);
        address2_text.setSize(220,30);
        address2_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        address2_text.setEditable(false);
        second_panel.add(address2_text);
        
        JLabel telephone_label=new JLabel("Telephone:");
        telephone_label.setLocation(10,110);
        telephone_label.setSize(300,15);
        telephone_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        second_panel.add(telephone_label);
        
        telephone_text=new JTextField();
        telephone_text.setLocation(80,105);
        telephone_text.setSize(220,30);
        telephone_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        telephone_text.setEditable(false);
        second_panel.add(telephone_text);
        
        JLabel email_label=new JLabel("Email:");
        email_label.setLocation(10,150);
        email_label.setSize(50,15);
        email_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        second_panel.add(email_label);
        
        email_text=new JTextField();
        email_text.setLocation(80,145);
        email_text.setSize(220,30);
        email_text.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        email_text.setEditable(false);
        second_panel.add(email_text);
 
        user_area=new JTextArea();
        user_area.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,13));
        user_area.setEditable(false);
        
        
        JScrollPane user_area_pane=new JScrollPane(user_area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        user_area_pane.setLocation(320,25);
        user_area_pane.setSize(200,150);
        second_panel.add(user_area_pane);
        
        JPanel third_panel=new JPanel();
        third_panel.setLocation(5,420);
        third_panel.setSize(300,75);
        third_panel.setBorder(BorderFactory.createTitledBorder("Amount to be invoiced"));
        third_panel.setLayout(null);
        third_panel.setBackground(new Color(255,228,181));
        main_panel.add(third_panel);
        
       JLabel invoice_amount_label=new JLabel("Amount:");
       invoice_amount_label.setLocation(5,20);
       invoice_amount_label.setSize(300,15);
       invoice_amount_label.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,14));
       third_panel.add(invoice_amount_label);
       
//       JTextField invoice_amount_text=new JTextField("" + decimal_format.format(tender_adjusted.getTendered()));
       invoice_amount_text=new JTextField("" + decimal_format.format(default_value));
       invoice_amount_text.setHorizontalAlignment(SwingConstants.RIGHT);
       invoice_amount_text.setLocation(60,15);
       invoice_amount_text.setSize(200,30);
       invoice_amount_text.setFont(new Font("SERIF",Font.BOLD,22));
       invoice_amount_text.setEditable(false);
       third_panel.add(invoice_amount_text);
        
       JPanel fourth_panel=new JPanel();
        fourth_panel.setLocation(350,420);
        fourth_panel.setSize(300,75);
        fourth_panel.setBorder(BorderFactory.createTitledBorder("Amount withstanding"));
        fourth_panel.setLayout(null);
        fourth_panel.setBackground(new Color(255,228,181));
        main_panel.add(fourth_panel);
       
       amount_withstanding_text=new JTextField();
       amount_withstanding_text.setLocation(10,15);
       amount_withstanding_text.setSize(200,30);
       amount_withstanding_text.setFont(new Font("SERIF",Font.BOLD,22));
       amount_withstanding_text.setEditable(false);
       fourth_panel.add(amount_withstanding_text);
       
       JButton ok_button=new JButton("OK");
       ok_button.setLocation(550,160);
       ok_button.setSize(130,50);
       ok_button.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
       ok_button.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               ok_button_clicked();
           }
       });
       main_panel.add(ok_button);
       
       JButton cancel_button=new JButton("CANCEL");
       cancel_button.setLocation(550,230);
       cancel_button.setSize(130,50);
       cancel_button.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
       cancel_button.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               cancel_button_clicked();
           }
       });
       main_panel.add(cancel_button);
       
        
    }
    
    private void getCustomers(){
        customer_list=alter_transaction.get_customer_info();
        customer_vector=new Vector();
        
        for(int i=0;i<customer_list.size();i++){
            customer_vector.add(customer_list.get(i).getAccount_nm());
        }
        
        customer_box_model=new DefaultComboBoxModel(customer_vector);
    }
    
    private void get_customer_data(){
        Customer customer=new Customer();
        String selected_customer_name=String.valueOf(account_text.getSelectedItem());
        customer_list_by_name=alter_transaction.get_customer_info(selected_customer_name);
        
        if(customer_list_by_name.size() >=0){
        address1_text.setText(customer_list_by_name.get(0).getAddress1());
        System.out.println(""+ customer_list_by_name.get(0).getAddress1());
        address2_text.setText(customer_list_by_name.get(0).getAddress2());
        telephone_text.setText(customer_list_by_name.get(0).getTelephone());
        email_text.setText(customer_list_by_name.get(0).getEmail());
        user_area.setText(customer_list_by_name.get(0).getNotes());
        }
           
    }
    
    private void ok_button_clicked(){
        String customer_account_no=customer_list_by_name.get(0).getAccount_no();
        double inv_amount=Double.parseDouble(invoice_amount_text.getText());
        tender_adjusted.change_Tendered_With_Customer(inv_amount, customer_account_no);
        
        if(getOwner()!=null){
            ((InvoiceInterface)getOwner()).invoiceFinished();
            dispose();
        }
        
        
    }
    
    private void cancel_button_clicked(){
        if(getOwner()!=null){
            ((InvoiceInterface)getOwner()).invoiceCancelled();
            dispose();
        }
    }
    
    public void setNewAmount(double amount){
        invoice_amount_text.setText("" + decimal_format.format(amount));
    }
    
    private void quit(){
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
        InvoiceScreen screen=new InvoiceScreen(null,false,null);
        screen.setVisible(true);
    }
    
    
}
