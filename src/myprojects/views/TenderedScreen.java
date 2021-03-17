/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.text.DecimalFormat;
public class TenderedScreen extends JDialog{
    double y=0.0;
    boolean isOk=false;
   private JTextField field,amount_text;
   private TenderedAdjusted tender_two;
   double tender_amount=0.0;
   double change_amount=0.0;
   double field2_value=0.0;
   JTextField field2;
   private JButton button_0,button_1,button_2,button_3,button_4,button_5;
   private  JButton button_6,button_7,button_8,button_9,decimal_button;
   private  JButton clear_button;
   private DecimalFormat decimal_format,number_format;
   public TenderedScreen(Dialog owner,boolean modal,TenderedAdjusted tender_adjusted){
     super(owner,modal);
     decimal_format=new DecimalFormat("#0.0000");
     number_format=new DecimalFormat("#");
     tender_two=new TenderedAdjusted();
     tender_two=tender_adjusted;
     
     BuildTender(tender_two);
     getContentPane().addKeyListener(new KeyAdapter(){
        public void keyReleased(KeyEvent e){
            if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                cancelButtonClicked();
            }
        } 
        public void keyTyped(KeyEvent e){
            
        }
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                cancelButtonClicked();
                
            }
        }
     });
     
   setSize(420,650);
  setLocationRelativeTo(owner);
   }
   private void BuildTender(TenderedAdjusted tender_two){
       JPanel panel=new JPanel();
       panel.setLayout(null);
 //      panel.setBackground(new Color(240,128,128));
       panel.setBackground(Color.YELLOW);
 //      panel.setBackground(new Color(240,128,128));
       panel.setBorder(BorderFactory.createEtchedBorder());
       
       JPanel top_panel=new JPanel();
       top_panel.setLocation(0,0);
       top_panel.setSize(420,25);
       top_panel.setBackground(new Color(100,149,237));
       top_panel.setLayout(null);
       panel.add(top_panel);
       
       JLabel cash_payment_label=new JLabel("Cash payment(press escape to cancel)");
       cash_payment_label.setLocation(5,5);
       cash_payment_label.setSize(300,15);
       cash_payment_label.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
       top_panel.add(cash_payment_label);
       
       JPanel second_panel=new JPanel();
       second_panel.setLocation(10,35);
       second_panel.setSize(370,120);
       second_panel.setBackground(new Color(255,228,181));
       second_panel.setBorder(BorderFactory.createEtchedBorder());
       second_panel.setLayout(null);
       panel.add(second_panel);
       
       JLabel select_customer=new JLabel("Select customer or leave blank if there is no");
       select_customer.setLocation(20,30);
       select_customer.setSize(400,15);
       select_customer.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,14));
       select_customer.setForeground(Color.BLACK);
       second_panel.add(select_customer);
       
       JLabel select_customer_2=new JLabel("customer associated with the transaction");
       select_customer_2.setLocation(20,42);
       select_customer_2.setSize(400,15);
       select_customer_2.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,14));
       select_customer_2.setForeground(Color.BLACK);
       second_panel.add(select_customer_2);
       
       JComboBox customer_box=new JComboBox();
       customer_box.setLocation(20,60);
       customer_box.setSize(280,40);
       customer_box.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,20));
       second_panel.add(customer_box);
       
       JPanel third_panel=new JPanel();
       third_panel.setLocation(10,165);
       third_panel.setSize(370,180);
       third_panel.setLayout(null);
       third_panel.setBorder(BorderFactory.createEtchedBorder());
       third_panel.setBackground(new Color(255,228,181));
       panel.add(third_panel);
       
       JLabel amount_label=new JLabel("Amount:");
       amount_label.setLocation(15,10);
       amount_label.setSize(200,30);
       amount_label.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,22));
       third_panel.add(amount_label);
       
       amount_text=new JTextField(""+decimal_format.format(tender_amount));
       amount_text.setLocation(120,10);
       amount_text.setSize(220,40);
       amount_text.setFont(new Font("SERIF",Font.BOLD,22));
       amount_text.setForeground(Color.BLACK);
       amount_text.setHorizontalAlignment(SwingConstants.RIGHT);
       amount_text.setEnabled(false);
  /*     amount_text.getDocument().addDocumentListener(new DocumentListener(){
          public void changedUpdate(DocumentEvent e){
               set_Amount_due();
           }
           public void insertUpdate(DocumentEvent e){
               set_Amount_due();
               
           }
           public void removeUpdate(DocumentEvent e){
               set_Amount_due();
           }
       });*/
       third_panel.add(amount_text);
       
     
       
       field=new JTextField(""+decimal_format.format(tender_two.getTendered()));
  //     field=new JTextField();
       field.setSize(220,40);
       field.setLocation(120,55);
       field.setFont(new Font("SERIF",Font.BOLD,22));
       field.setForeground(Color.WHITE);
       field.setBackground(Color.BLUE);
       field.setHorizontalAlignment(SwingConstants.RIGHT);
       field.getDocument().addDocumentListener(new DocumentListener(){
           public void changedUpdate(DocumentEvent e){
              setAmount(tender_amount,change_amount); 
           }
           public void insertUpdate(DocumentEvent e){
           setAmount((Double.parseDouble(field.getText())),change_amount);    
           }
           public void removeUpdate(DocumentEvent e){
   //            setAmount(getFieldValue(),change_amount);
               setAmount(field2_value,change_amount);
           }
       });
       third_panel.add(field);
       
       JLabel label=new JLabel("Tendered:");
       label.setLocation(15,60);
       label.setSize(200,30);
       label.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,22));
       label.setForeground(Color.BLACK);
       third_panel.add(label);
       
       JLabel label2=new JLabel("Change:");
       label2.setLocation(15,105);
       label2.setSize(200,30);
       label2.setFont(new Font("SERIF",Font.BOLD,22));
       third_panel.add(label2);
       
       field2=new JTextField(40);
       field2.setLocation(120,100);
       field2.setSize(220,40);
       field2.setFont(new Font("SERIF",Font.BOLD,22));
       field2.setForeground(Color.RED);
       field2.setHorizontalAlignment(SwingConstants.RIGHT);
       field2.setEnabled(false);
       third_panel.add(field2);
       
       JPanel fourth_panel=new JPanel();
       fourth_panel.setLocation(10,350);
       fourth_panel.setSize(370,210);
       fourth_panel.setLayout(null);
       fourth_panel.setBorder(BorderFactory.createEtchedBorder());
       fourth_panel.setBackground(new Color(255,228,181));
       panel.add(fourth_panel);
       
       button_0=new JButton("0");
       button_0.setLocation(175,135);
       button_0.setSize(80,60);
       button_0.setFont(new Font("SERIF",Font.BOLD,35));
       button_0.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
     //     field.setText(field.getText()+button_0.getText());
                String button_0_buffer=String.valueOf(number_format.format(Double.parseDouble(field.getText())))+button_0.getText();
               field.setText(""+decimal_format.format(Double.parseDouble(button_0_buffer)));
           }
       });
       fourth_panel.add(button_0);
       
       button_1=new JButton("1");
       button_1.setLocation(260,135);
       button_1.setSize(80,60);
       button_1.setFont(new Font("SERIF",Font.BOLD,35));
       button_1.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
     //          field.setText(field.getText()+button_1.getText());
                String button_1_buffer=String.valueOf(number_format.format(Double.parseDouble(field.getText())))+button_1.getText();
               field.setText(""+decimal_format.format(Double.parseDouble(button_1_buffer)));
           }
       });
       fourth_panel.add(button_1);
       
       button_2=new JButton("2");
       button_2.setLocation(5,70);
       button_2.setSize(80,60);
       button_2.setFont(new Font("SERIF",Font.BOLD,35));
       button_2.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
          //     field.setText(field.getText()+button_2.getText());
                String button_2_buffer=String.valueOf(number_format.format(Double.parseDouble(field.getText())))+button_2.getText();
               field.setText(""+decimal_format.format(Double.parseDouble(button_2_buffer)));
           }
       });
      
         fourth_panel.add(button_2);
       
       button_3=new JButton("3");
       button_3.setLocation(90,70);
       button_3.setSize(80,60);
       button_3.setFont(new Font("SERIF",Font.BOLD,35));
       button_3.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
     //          field.setText(field.getText()+button_3.getText());
                String button_3_buffer=String.valueOf(number_format.format(Double.parseDouble(field.getText())))+button_3.getText();
               field.setText(""+decimal_format.format(Double.parseDouble(button_3_buffer)));
           }
       });
       fourth_panel.add(button_3);
       
       button_4=new JButton("4");
       button_4.setLocation(175,70);
       button_4.setSize(80,60);
       button_4.setFont(new Font("SERIF",Font.BOLD,35));
       button_4.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
     //          field.setText(field.getText()+button_4.getText());
                String button_4_buffer=String.valueOf(number_format.format(Double.parseDouble(field.getText())))+button_4.getText();
               field.setText(""+decimal_format.format(Double.parseDouble(button_4_buffer)));
           }
       });
       fourth_panel.add(button_4);
       
       button_5=new JButton("5");
       button_5.setLocation(260,70);
       button_5.setSize(80,60);
       button_5.setFont(new Font("SERIF",Font.BOLD,35));
       button_5.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
  //             field.setText(field.getText()+button_5.getText());
                String button_5_buffer=String.valueOf(number_format.format(Double.parseDouble(field.getText())))+button_5.getText();
               field.setText(""+decimal_format.format(Double.parseDouble(button_5_buffer)));
           }
       });
       fourth_panel.add(button_5);
       
       button_6=new JButton("6");
       button_6.setLocation(5,5);
       button_6.setSize(80,60);
       button_6.setFont(new Font("SERIF",Font.BOLD,35));
       button_6.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
  //             field.setText(field.getText()+button_6.getText());
 /*      String button_6_buffer=String.valueOf(String.valueOf(number_format.format(Double.parseDouble(field.getText()))))+(button_6.getText());
               System.out.println(""+button_6_buffer);
               field.setText(""+button_6_buffer);*/
       //        double yu=Double.parseDouble(field.getText());
               String button_6_buffer=String.valueOf(number_format.format(Double.parseDouble(field.getText())))+button_6.getText();
               field.setText(""+decimal_format.format(Double.parseDouble(button_6_buffer)));
           }
       });
       fourth_panel.add(button_6);
       
       button_7=new JButton("7");
       button_7.setLocation(90,5);
       button_7.setSize(80,60);
       button_7.setFont(new Font("SERIF",Font.BOLD,35));
       button_7.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
    //           field.setText(field.getText()+button_7.getText());
   /*           String button_7_buffer=String.valueOf(String.valueOf(number_format.format(Double.parseDouble(field.getText()))))+(button_7.getText());
               System.out.println(""+button_7_buffer);
               field.setText(""+button_7_buffer); */
                String button_7_buffer=String.valueOf(number_format.format(Double.parseDouble(field.getText())))+button_7.getText();
               field.setText(""+decimal_format.format(Double.parseDouble(button_7_buffer)));
           }
       });
       fourth_panel.add(button_7);
       
       button_8=new JButton("8");
       button_8.setLocation(175,5);
       button_8.setSize(80,60);
       button_8.setFont(new Font("SERIF",Font.BOLD,35));
       button_8.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
  //             field.setText(field.getText()+button_8.getText());
                String button_8_buffer=String.valueOf(number_format.format(Double.parseDouble(field.getText())))+button_8.getText();
               field.setText(""+decimal_format.format(Double.parseDouble(button_8_buffer)));
           }
       });
       fourth_panel.add(button_8);
       
       button_9=new JButton("9");
       button_9.setLocation(260,5);
       button_9.setSize(80,60);
       button_9.setFont(new Font("SERIF",Font.BOLD,35));
       button_9.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
   //            field.setText(field.getText()+button_9.getText());
                String button_9_buffer=String.valueOf(number_format.format(Double.parseDouble(field.getText())))+button_9.getText();
               field.setText(""+decimal_format.format(Double.parseDouble(button_9_buffer)));
           }
       });
       fourth_panel.add(button_9);
       
       decimal_button=new JButton(".");
       decimal_button.setLocation(90,135);
       decimal_button.setSize(80,60);
       decimal_button.setFont(new Font("SERIF",Font.BOLD,50));
       decimal_button.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
   //            field.setText(field.getText()+decimal_button.getText());
               
           }
       });
       fourth_panel.add(decimal_button);
       
       clear_button=new JButton("C");
       clear_button.setLocation(5,135);
       clear_button.setSize(80,60);
       clear_button.setFont(new Font("SERIF",Font.BOLD,35));
       clear_button.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
       clear_button.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               field.setText(""+decimal_format.format(0.0));
           }
       });
       fourth_panel.add(clear_button);
      
      JButton ok_button=new JButton("OK");
      ok_button.setLocation(5,580);
      ok_button.setSize(180,50);
      ok_button.setForeground(Color.BLACK);
      ok_button.setFont(new Font("TIMES NEW ROMAN",30,30));
//      ok_button.setBorder(BorderFactory.createRaisedBevelBorder());
      ok_button.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
              okButtonClicked();
          }
      });
      panel.add(ok_button);
      
      JButton cancel_button=new JButton("CANCEL");
      cancel_button.setLocation(200,580);
 //     cancel_button.setLocation(275,600);
      cancel_button.setSize(180,50);
      cancel_button.setForeground(Color.BLACK);
      cancel_button.setFont(new Font("TIMES NEW ROMAN",30,30));
//      cancel_button.setBorder(BorderFactory.createRaisedBevelBorder());
      cancel_button.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
              cancelButtonClicked();
          }
      });
      panel.add(cancel_button);
      
      add(panel);
      
      addWindowListener(new WindowAdapter(){
          public void windowClosed(WindowEvent e){
              cancelButtonClicked();
          }
      });}
   public void setAmount(double a,double b){
       double c=a-b;
  //     field.setText(""+a);
       field2.setText(""+decimal_format.format(c));
   }
   public void setNewAmount(double x,double y){
       field.setText(""+decimal_format.format(x));
       amount_text.setText(""+decimal_format.format(x));
       tender_amount=x;
       change_amount=y;
   }
   private double getFieldValue(){
      double value=Double.parseDouble(field.getText());
      return value;
   }
   private void set_Amount_due(){
       amount_text.setText(""+decimal_format.format(tender_amount));
   }
    private void okButtonClicked(){
    y=Double.parseDouble(field.getText());
 //   tender_two=new TenderedAdjusted(y);
    tender_two.changeTendered(y);
//    tender_two.getTendered();
    if(getOwner()!=null){
        
        ((ProjectInterface)getOwner()).Finished();
        dispose();
    }
   }
 //  private boolean okButtonClicked(){
//       isOk=true;
//       return true;
//   } 
  // private void AdjustAmount(){
  //     y=Double.parseDouble(field.getText());
 //      tender_two=new TenderedAdjusted(y);
 //      tender_two.changeTendered(y);
 //      tender_two.getTendered();
 //      dispose();
 //  }
   private void cancelButtonClicked(){
       if(getOwner()!=null){
          ((ProjectInterface)getOwner()).dialogCancelled();
           dispose();
       }}
  
   public static void main(String[] args){
       TenderedScreen b=new TenderedScreen(null,false,null);
       b.setVisible(true);
   }
}
