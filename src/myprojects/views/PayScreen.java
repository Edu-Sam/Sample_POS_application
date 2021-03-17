/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;
import javax.swing.*;
import java.awt.*;
import javax.swing.JWindow;
import java.awt.event.*;
import javax.swing.event.*;
import java.text.DecimalFormat;


public class PayScreen extends JDialog implements ProjectInterface,InvoiceInterface {
    double amount=0.0;
    double tendered_amount=0.0;
    double cash_text_amount=0.0;
    double invoice_text_amount=0.0;
    double mobile_text_amount=0.0;
    double cheque_amount_text=0.0;
    double change=0.0;
    private UserTransaction user_transaction;
    private TenderedAdjusted tenderedAdjusted;
    private JTextField cash_text,amount_due_text,change_text,invoice_text,mobile_text;
    private JButton ok_button;
    private double default_value=0.0;
    private DecimalFormat decimal_format;
    private String customer_account_no;
    
    public PayScreen(JFrame owner,boolean modal,double carry,UserTransaction new_user_transaction){
       super(owner,modal);
//      getContentPane().setLayout(null);
       amount=carry;
       user_transaction=new_user_transaction;
       decimal_format=new DecimalFormat("#0.00");
    getContentPane().setBackground(new Color(240,128,128));
 //   getContentPane().setBackground(Color.YELLOW);
/*    try{
            for(UIManager.LookAndFeelInfo info:  UIManager.getInstalledLookAndFeels()){
                if(("Windows").equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    SwingUtilities.updateComponentTreeUI(this);
                }
            }
        }
        catch(Exception e){
            
        }  
        */
        try{
        Box box1=Box.createHorizontalBox();
        JLabel label1=new JLabel("Select Mode Of Payment");
        label1.setFont(new Font("SERIF",Font.BOLD,26));
        label1.setBackground(Color.GREEN);
        label1.setForeground(Color.GREEN);
        JPanel panel=new JPanel();
 //       panel.setBackground(new Color(240,128,128));
        panel.setBackground(Color.BLACK);
 //       panel.setLayout(null);
        panel.add(label1);
 //       panel.setBorder(BorderFactory.createRaisedBevelBorder());
        panel.setBorder(BorderFactory.createEtchedBorder());
//        box1.add(label1);
//        box1.setLocation(0,0);
//        box1.setSize(300,20);
        getContentPane().add(BorderLayout.NORTH,panel);
        
       JPanel panel2=new JPanel();
        panel2.setLayout(null);
        panel2.setBackground(new Color(240,128,128));
  //      panel2.setBackground(Color.YELLOW);
        panel2.setBorder(BorderFactory.createLoweredBevelBorder());
        
        JPanel top_panel=new JPanel();
        top_panel.setLocation(10,5);
        top_panel.setSize(325,120);
        top_panel.setLayout(null);
        top_panel.setBorder(BorderFactory.createEtchedBorder());
 //       top_panel.setBackground(new Color(238,232,170));
        
        top_panel.setBackground(new Color(255,228,181));
        panel2.add(top_panel);
        
        JLabel amount_due_label=new JLabel("Amount Due");
        amount_due_label.setLocation(40,50);
        amount_due_label.setSize(200,17);
        amount_due_label.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
        top_panel.add(amount_due_label);
        
        JPanel middle_panel=new JPanel();
        middle_panel.setLocation(10,150);
        middle_panel.setSize(325,350);
        middle_panel.setLayout(null);
        middle_panel.setBorder(BorderFactory.createTitledBorder("Mode Of Payment"));
        middle_panel.setBackground(new Color(255,228,181));
        panel2.add(middle_panel);
        
        amount_due_text=new JTextField();
        amount_due_text.setHorizontalAlignment(SwingConstants.RIGHT);
        amount_due_text.setLocation(150,45);
        amount_due_text.setSize(150,30);
        amount_due_text.setFont(new Font("SERIF",Font.BOLD,20));
        amount_due_text.setText(""+decimal_format.format(amount));
        amount_due_text.setEditable(false);
  /*      amount_due_text.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e){
                
            }
            @Override
            public void removeUpdate(DocumentEvent e){
                
            }
            @Override
            public void insertUpdate(DocumentEvent e){
                setNewChange();
            }
        });*/
        top_panel.add(amount_due_text);
        
        JButton cash_button=new JButton("CASH");
        cash_button.setSize(185,40);
  //      cash_button.setBackground(Color.BLACK);
        cash_button.setForeground(Color.BLACK);
        cash_button.setLocation(10,20);
        cash_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,22));
 //       cash_button.setBorder(BorderFactory.createRaisedBevelBorder());
 /*       cash_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
 
                
                if(getOwner()!=null){
          quit();
                    ((Enable_Cash_Screen)getOwner()).Call_Cash_Screen();
                    
                }
                
            }
        });*/
        cash_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                DisplayTenderDialog();
            }
        });
        middle_panel.add(cash_button);
        
        cash_text=new JTextField(""+decimal_format.format(tendered_amount));
        cash_text.setLocation(200,20);
        cash_text.setSize(120,40);
        cash_text.setFont(new Font("SERIF",Font.BOLD,18));
        cash_text.setBackground(Color.MAGENTA);
        cash_text.setForeground(Color.WHITE);
        cash_text.setHorizontalAlignment(SwingConstants.RIGHT);
        cash_text.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e){
                
            }
            @Override
            public void removeUpdate(DocumentEvent e){
                
            }
            @Override
            public void insertUpdate(DocumentEvent e){
                setNewChange();
            }
        });
        middle_panel.add(cash_text);
        
        JButton invoice_button=new JButton("INVOICE");
        invoice_button.setSize(185,40);
  //     invoice_button.setBackground(Color.BLACK);
        invoice_button.setForeground(Color.BLACK);
        invoice_button.setLocation(10,80);
        invoice_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
        invoice_button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                DisplayInvoiceDialog();
            }
        });
 //       invoice_button.setBorder(BorderFactory.createRaisedBevelBorder());
        middle_panel.add(invoice_button);
        
        invoice_text=new JTextField(""+decimal_format.format(invoice_text_amount));
        invoice_text.setLocation(200,80);
        invoice_text.setSize(120,40);
        invoice_text.setFont(new Font("SERIF",Font.BOLD,18));
        invoice_text.setForeground(Color.WHITE);
        invoice_text.setBackground(Color.MAGENTA);
        invoice_text.setHorizontalAlignment(SwingConstants.RIGHT);
        invoice_text.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e){
                
            }
            @Override
            public void removeUpdate(DocumentEvent e){
                
            }
            @Override
            public void insertUpdate(DocumentEvent e){
                setNewChange();
            }
        });
        
        middle_panel.add(invoice_text);
        
        
        JButton mobile_button=new JButton("MOBILE");
        mobile_button.setSize(185,40);
 //       mobile_button.setBackground(Color.BLACK);
        mobile_button.setForeground(Color.BLACK);
        mobile_button.setLocation(10,140);
        mobile_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
 //       mobile_button.setBorder(BorderFactory.createRaisedBevelBorder());
        middle_panel.add(mobile_button);
        
        mobile_text=new JTextField(""+decimal_format.format(mobile_text_amount));
        mobile_text.setLocation(200,140);
        mobile_text.setSize(120,40);
        mobile_text.setFont(new Font("SERIF",Font.BOLD,18));
        mobile_text.setForeground(Color.WHITE);
        mobile_text.setBackground(Color.MAGENTA);
        mobile_text.setHorizontalAlignment(SwingConstants.RIGHT);
        mobile_text.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void changedUpdate(DocumentEvent e){
                
            }
            @Override
            public void removeUpdate(DocumentEvent e){
                
            }
            @Override
            public void insertUpdate(DocumentEvent e){
                setNewChange();
            }
        });
        
        middle_panel.add(mobile_text);
        
        ok_button=new JButton("OK");
        ok_button.setLocation(340,40);
        ok_button.setSize(140,80);
        ok_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
        ok_button.setEnabled(false);
        ok_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(((tendered_amount)-Double.parseDouble(amount_due_text.getText()))>=0){
    //           quit();   
                  if(getOwner()!=null){
                  double Total_double=Double.parseDouble(amount_due_text.getText());
                  double tendered_double=tendered_amount;
                  double change_double=Double.parseDouble(change_text.getText());
                  user_transaction.set_total_amount(Total_double);
                  user_transaction.set_tendered_Amount(tendered_double);
                  user_transaction.set_change(change_double);
                  if(!(tenderedAdjusted.getCustomer().equalsIgnoreCase(" "))){
                      user_transaction.set_customer_no(tenderedAdjusted.getCustomer());
                  }
                 System.out.println("The customer is " + user_transaction.get_customer_no());
                   if(user_transaction.get_is_use_section().equalsIgnoreCase("No")){
                       quit();
                   ((Transaction)getOwner()).StartTransact();
                   }
                   else{
                       if(user_transaction.get_is_use_section().equalsIgnoreCase("Yes")){
                           quit();
                       ((Transaction)getOwner()).start_transact_with_section();
                       }
                   }
                      
                    
                      
                      
                  }
 //                 JOptionPane.showMessageDialog(null,"YOUR TRANSACTION HAS BEEN SAVED",
 //                         "TRANSACTION SUCCESSFUL",JOptionPane.INFORMATION_MESSAGE);
   //               MyProjects newProject=new MyProjects("ECLIPSE ENTEPRISE");
   //               newProject.setVisible(true);
   //               quit();
                 
              }
              else{
                 
              }
            }
        });
        panel2.add(ok_button);
        
        JButton cancel_button=new JButton("CANCEL");
        cancel_button.setSize(140,80);
     // cancel_button.setBackground(Color.BLACK);
     //   cancel_button.setLocation(10,200);
        cancel_button.setLocation(340,150);
        cancel_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
 //       cancel_button.setBorder(BorderFactory.createRaisedBevelBorder());
        cancel_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                quit();
            }
        });
        panel2.add(cancel_button);
        
        JLabel change_label=new JLabel("Change:");
        change_label.setLocation(10,510);
        change_label.setSize(200,17);
        change_label.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
        panel2.add(change_label);
        
        change_text=new JTextField();
        change_text.setLocation(80,505);
        change_text.setSize(170,40);
        change_text.setFont(new Font("SERIF",Font.BOLD,24));
        change_text.setBackground(Color.BLACK);
        change_text.setForeground(Color.GREEN);
        change_text.setHorizontalAlignment(SwingConstants.RIGHT);
  //      change_text.setText(""+amount);
        panel2.add(change_text);
        
        setNewChange();
        
        getContentPane().add(BorderLayout.CENTER,panel2);
 //       getContentPane().add(cash_button);
        setSize(500,650);
        setLocationRelativeTo(null);
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog((java.awt.Component)null,"ERROR"+e.getMessage(),
                    "ERROR",javax.swing.JOptionPane.DEFAULT_OPTION);
        }
    }
    private void Call_Amount_Screen(){
 //       AmountScreen n=new AmountScreen(this,"",true,amount);
  //              n.setUndecorated(true);
  //              n.setVisible(true);
     //           n.receiveGrossTotal(amount);
    }
    
    private void setNewChange(){
        cash_text_amount=Double.parseDouble(cash_text.getText());
  //      change=Double.parseDouble(cash_text.getText())-Double.parseDouble(amount_due_text.getText());
        change=(tendered_amount)-Double.parseDouble(amount_due_text.getText());
        change_text.setText(""+decimal_format.format(change));
    }
    public void setOutLook(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception r){
            
        }
    }
    public double receiveTotal(double r){
        amount=r;
      return amount; 
    }
     private void DisplayTenderDialog(){
         user_transaction.set_customer_no("");
         customer_account_no="";
        tenderedAdjusted=new TenderedAdjusted();
        TenderedScreen tenderedscreen=new TenderedScreen(this,true,tenderedAdjusted);
        tenderedscreen.setNewAmount(amount,amount);
        tenderedscreen.setAmount(amount,amount);
        tenderedscreen.setUndecorated(true);
        tenderedscreen.setVisible(true);
              
        }
     private void DisplayInvoiceDialog(){
         tenderedAdjusted=new TenderedAdjusted();
         InvoiceScreen invoice_screen=new InvoiceScreen(this,true,tenderedAdjusted);
         invoice_screen.setNewAmount(amount);
         invoice_screen.setUndecorated(true);
         invoice_screen.setVisible(true);
     }
     public void Finished(){
         tendered_amount=tenderedAdjusted.getTendered();
         cash_text.setText(""+decimal_format.format(tendered_amount));
         invoice_text.setText("" + decimal_format.format(default_value));
         mobile_text.setText("" +decimal_format.format(default_value));
         user_transaction.set_transaction_type(TransactionType.CASH);
         if((Double.parseDouble(amount_due_text.getText()))<=(Double.parseDouble(cash_text.getText()))){
             ok_button.setEnabled(true);
         }
         else{
             ok_button.setEnabled(false);
         }
     }
     public void dialogCancelled(){
         
     }
     
    public void invoiceFinished(){
        tendered_amount=tenderedAdjusted.getTendered();
        customer_account_no=tenderedAdjusted.getCustomer();
        System.out.println("The Customer Account no is " + customer_account_no);
        invoice_text.setText("" + decimal_format.format(tendered_amount));
        cash_text.setText("" + decimal_format.format(default_value));
        mobile_text.setText(""+ decimal_format.format(default_value));
        user_transaction.set_transaction_type(TransactionType.INVOICE);
        user_transaction.set_customer_no(customer_account_no);
        if((Double.parseDouble(amount_due_text.getText()))<=(Double.parseDouble(invoice_text.getText()))){
             ok_button.setEnabled(true);
         }
         else{
             ok_button.setEnabled(false);
         }
    }
    
    public void invoiceCancelled(){
        
    }
  public void quit(){
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
   PayScreen pay=new PayScreen(null,false,0.0,null);    
   pay.setVisible(true);
    }
}
