/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * This class is no longer in use in the main project
 * @Edwin
 */
package myprojects;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
public class AmountScreen extends JDialog implements ProjectInterface{
  JTextField field;
  JButton amount_button;
    JTextField field2;
    JTextField field3;
    double field2_amount=0.0;
    double new_amount=0.0;
    double change=0.0;
    JButton ok_button;
    private TenderedAdjusted tenderedAdjusted;
    private UserTransaction user_transaction;
    public AmountScreen(JFrame Owner,String title,boolean modal,double w,UserTransaction new_user_transaction){
        super(Owner,modal);
        user_transaction=new_user_transaction;
  /*      try{
            for(UIManager.LookAndFeelInfo info:  UIManager.getInstalledLookAndFeels()){
                if(("Windows").equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    SwingUtilities.updateComponentTreeUI(this);
                }
            }
        }
        catch(Exception e){
            
        }*/
        getContentPane().setBackground(Color.YELLOW);
        new_amount=w;
        
        try{
        JPanel panel=new JPanel();
        TenderedAdjusted g=new TenderedAdjusted();
        panel.setBackground(Color.YELLOW);
        panel.setBorder(BorderFactory.createTitledBorder("AMOUNT"));
        panel.setLayout(null);
       field=new JTextField();
      field.setLocation(270,100);
      field.setSize(210,40);
      field.setFont(new Font("SERIF",30,30));
      field.setText(""+new_amount);
      field.getDocument().addDocumentListener(new DocumentListener(){
          @Override
          public void changedUpdate(DocumentEvent e){
   //       setNewChange();    
          }
          @Override
          public void removeUpdate(DocumentEvent e){
    //       setNewChange();   
          }
          @Override
          public void insertUpdate(DocumentEvent e){
           setNewChange();   
          }
      });
      
      field.setEnabled(false);
      panel.add(field);
      
      JLabel label1=new JLabel("TENDERED:");
      label1.setLocation(0,100);
      label1.setSize(200,30);
  //    label1.setBorder(BorderFactory.createRaisedBevelBorder());
      label1.setFont(new Font("SERIF",28,28));
      panel.add(label1);
      
      JLabel label2=new JLabel("AMOUNT:");
      label2.setLocation(0,200);
      label2.setSize(200,30);
      label2.setFont(new Font("SERIF",28,28));
      panel.add(label2);
      
       field2=new JTextField(40);
      field2.setLocation(270,200);
      field2.setSize(210,40);
      field2.setFont(new Font("SERIF",30,30));
      field2.setText(""+new_amount);
      field2.setEnabled(false);
      panel.add(field2);
      
      JLabel label3=new JLabel("CHANGE:");
      label3.setLocation(0,300);
      label3.setSize(200,30);
      label3.setFont(new Font("SERIF",28,28));
      panel.add(label3);
      
      field3=new JTextField(40);
      field3.setLocation(270,300);
      field3.setSize(210,40);
      field3.setFont(new Font("SERIF",30,30));
      field3.setEnabled(false);
      panel.add(field3);
     
       amount_button=new JButton("ENTER AMOUNT");
      amount_button.setLocation(10,400);
      amount_button.setSize(300,50);
 //     amount_button.setBackground(Color.BLACK);
      amount_button.setForeground(Color.BLACK);
      amount_button.setFont(new Font("TIMES NEW ROMAN",30,30));
 //     amount_button.setBorder(BorderFactory.createRaisedBevelBorder());
      amount_button.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            DisplayTenderDialog();  
   //           TenderedScreen tenderedscreen=new TenderedScreen(tenderedscreen,"",true);
   //           tenderedscreen.setUndecorated(true);
   //           tenderedscreen.setVisible(true);
          }
      });
      panel.add(amount_button);
    
      ok_button=new JButton("OK");
      ok_button.setLocation(5,500);
      ok_button.setSize(200,50);
//      ok_button.setBackground(Color.GREEN);
      ok_button.setForeground(Color.BLACK);
      ok_button.setFont(new Font("SERIF",30,30));
 //     ok_button.setBorder(BorderFactory.createRaisedBevelBorder());
      ok_button.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
              if((Double.parseDouble(field.getText())-Double.parseDouble(field2.getText()))>=0){
    //           quit();   
                  if(getOwner()!=null){
                  double Total_double=Double.parseDouble(field2.getText());
                  double tendered_double=Double.parseDouble(field.getText());
                  double change_double=Double.parseDouble(field3.getText());
                  user_transaction.set_total_amount(Total_double);
                  user_transaction.set_tendered_Amount(tendered_double);
                  user_transaction.set_change(change_double);
                  System.out.println(user_transaction.get_is_use_section());
            //       quit();  
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
      panel.add(ok_button);
      
      JButton cancel_button=new JButton("CANCEL");
      cancel_button.setLocation(275,500);
      cancel_button.setSize(200,50);
//      cancel_button.setBackground(Color.RED);
      cancel_button.setForeground(Color.BLACK);
      cancel_button.setFont(new Font("SERIF",30,30));
  //    cancel_button.setBorder(BorderFactory.createRaisedBevelBorder());
      cancel_button.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
              if(getOwner()!=null){
              quit();
              ((Transaction)getOwner()).stopTransact();
              }
          }
      });
      panel.add(cancel_button);
      field.setText(""+new_amount);
        field2.setText(""+new_amount);
        change=Double.parseDouble(field.getText())-Double.parseDouble(field2.getText());
        field3.setText(""+change);
     
      getContentPane().add(BorderLayout.CENTER,panel);
      setSize(500,600);
      setLocationRelativeTo(null);
    //  show();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog((java.awt.Component)null,"ERROR"+e.getMessage(),
                    "ERROR",JOptionPane.DEFAULT_OPTION);
        }
      
    }
   private void Disable_Amount(){
       
       this.setEnabled(false);
       this.setVisible(false);
   }
    private void setNewChange(){
   //     field.setText(""+new_amount);
        field2_amount=Double.parseDouble(field2.getText());
        change=Double.parseDouble(field.getText())-Double.parseDouble(field2.getText());
        field3.setText(""+change);
  //      return change;
        
    } 
    private void DisplayTenderDialog(){
        tenderedAdjusted=new TenderedAdjusted();
              TenderedScreen tenderedscreen=new TenderedScreen(this,true,tenderedAdjusted);
              tenderedscreen.setNewAmount(new_amount,new_amount);
              tenderedscreen.setAmount(new_amount,new_amount);
              tenderedscreen.setUndecorated(true);
              tenderedscreen.setVisible(true);
              
        }
    private void DisableButton(){
        ok_button.setEnabled(false);
    }
    private void quit(){
        this.dispose();
    }
 //   private void setTenderedText(double Final_tendered){
 //       new_amount=Final_tendered;
 //       field.setText(""+new_amount);
        
 //   }
    
    public void Finished(){
       new_amount=tenderedAdjusted.getTendered();
       field.setText(""+new_amount);
    }
    public void dialogCancelled(){
        
    }
//    public double receiveGrossTotal(double w){
//        new_amount=w;
//        field.setText(""+new_amount);
//        field2.setText(""+new_amount);
//        change=Double.parseDouble(field.getText())-Double.parseDouble(field2.getText());
//        field3.setText(""+change);
//        return new_amount;
//    }
    public static void main(String[] args){
 //       AmountScreen amountscreen=new AmountScreen(null,"",false,0.0,null);
 //       amountscreen.setUndecorated(true);
 //       amountscreen.setVisible(true);
    } 
        
}
