/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class QuantityScreen extends JDialog{
    HashMap<JButton,Integer> myMap=new HashMap<JButton,Integer>();
    JButton[] button=new JButton[9];
    double value=0.0;
    JTextField field;
    JButton button_0,button_1,button_2,button_3,button_4,button_5;
    JButton button_6,button_7,button_8,button_9,decimal_button;
    JButton clear_button;
    Quantity quantity;
    float y;
   public QuantityScreen(JFrame owner,boolean modal,Quantity newQuantity){
       super(owner,modal);
       quantity=newQuantity;
       BuildPanel(quantity);
       setSize(700,600);
      setLocationRelativeTo(owner);
   }
   private void BuildPanel(Quantity quantity){   
   JPanel panel=new JPanel();
  //     getContentPane().setLayout(null);
       panel.setLayout(null);
       panel.setBorder(BorderFactory.createTitledBorder("QUANTITY"));
 //      panel.setLayout(new GridLayout(3,3,0,0));
 //      for(int i=0;i<9;i++){
 //          button[i]=new JButton();
 //          button[i].setSize(5,5);
 //          button[i].setText(""+(i));
 //          button[i].setFont(new Font("SERIF",30,30));
 //          button[i].setBackground(Color.WHITE);
 //          myMap.put(button[i],new Integer (i));
 //          button[i].addActionListener(new ActionListener(){
 //              public void actionPerformed(ActionEvent e){
 //                 Integer index=myMap.get(e.getSource());
 //                 value=Double.parseDouble(button[index].getText());
 //                 field.setText(""+value);
 //              }
 //          });
           panel.setBackground(Color.YELLOW);
 //          panel.add(button[i]);
 //      }
       button_0=new JButton("0");
       button_0.setLocation(10,100);
       button_0.setSize(150,80);
       button_0.setFont(new Font("SERIF",50,50));
       button_0.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
          field.setText(field.getText()+button_0.getText());
           }
       });
       panel.add(button_0);
       
       button_1=new JButton("1");
       button_1.setLocation(250,100);
       button_1.setSize(150,80);
       button_1.setFont(new Font("SERIF",50,60));
       button_1.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               field.setText(field.getText()+button_1.getText());
           }
       });
       panel.add(button_1);
       
       button_2=new JButton("2");
       button_2.setLocation(490,100);
       button_2.setSize(150,80);
       button_2.setFont(new Font("SERIF",50,60));
       button_2.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               field.setText(field.getText()+button_2.getText());
           }
       });
       panel.add(button_2);
       
       button_3=new JButton("3");
       button_3.setLocation(10,200);
       button_3.setSize(150,80);
       button_3.setFont(new Font("SERIF",50,60));
       button_3.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               field.setText(field.getText()+button_3.getText());
           }
       });
       panel.add(button_3);
       
       button_4=new JButton("4");
       button_4.setLocation(250,200);
       button_4.setSize(150,80);
       button_4.setFont(new Font("SERIF",50,60));
       button_4.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               field.setText(field.getText()+button_4.getText());
           }
       });
       panel.add(button_4);
       
       button_5=new JButton("5");
       button_5.setLocation(490,200);
       button_5.setSize(150,80);
       button_5.setFont(new Font("SERIF",50,60));
       button_5.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               field.setText(field.getText()+button_5.getText());
           }
       });
       panel.add(button_5);
       
       button_6=new JButton("6");
       button_6.setLocation(10,300);
       button_6.setSize(150,80);
       button_6.setFont(new Font("SERIF",50,60));
       button_6.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               field.setText(field.getText()+button_6.getText());
           }
       });
       panel.add(button_6);
       
       button_7=new JButton("7");
       button_7.setLocation(250,300);
       button_7.setSize(150,80);
       button_7.setFont(new Font("SERIF",50,60));
       button_7.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               field.setText(field.getText()+button_7.getText());
           }
       });
       panel.add(button_7);
       
       button_8=new JButton("8");
       button_8.setLocation(490,300);
       button_8.setSize(150,80);
       button_8.setFont(new Font("SERIF",50,60));
       button_8.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               field.setText(field.getText()+button_8.getText());
           }
       });
       panel.add(button_8);
       
       button_9=new JButton("9");
       button_9.setLocation(10,400);
       button_9.setSize(150,80);
       button_9.setFont(new Font("SERIF",50,60));
       button_9.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               field.setText(field.getText()+button_9.getText());
           }
       });
       panel.add(button_9);
       
       decimal_button=new JButton(".");
       decimal_button.setLocation(250,400);
       decimal_button.setSize(150,80);
       decimal_button.setFont(new Font("SERIF",100,100));
       decimal_button.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               field.setText(field.getText()+decimal_button.getText());
               
           }
       });
       panel.add(decimal_button);
       
       JButton clear_button=new JButton("C");
       clear_button.setLocation(490,400);
       clear_button.setSize(150,80);
       clear_button.setFont(new Font("SERIF",50,60));
       clear_button.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
       clear_button.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               field.setText("");
           }
       });
       panel.add(clear_button);
      
      field=new JTextField(40);
 //     field.setText("0.00");
      field.setHorizontalAlignment(SwingConstants.RIGHT);
      field.setLocation(210,20);
      field.setSize(250,50);
      field.setFont(new Font("SERIF",50,50));
      
//      field.setBackground(Color.WHITE);
      field.setForeground(Color.BLACK);
 //     Box box1=Box.createHorizontalBox();
      panel.add(field);
      JLabel label=new JLabel("QUANTITY :");
      label.setSize(200,30);
      label.setFont(new Font("TIMES NEW ROMAN",30,30));
      label.setLocation(10,20);
      label.setBackground(Color.BLACK);
      label.setForeground(Color.BLACK);
      panel.add(label);
     
 //     panel2.add(label);
 //     panel2.add(field);
  
       getContentPane().add(BorderLayout.CENTER,panel);
      
      
      
      
      JPanel panel3=new JPanel();
 //     panel3.setLayout(null);
      panel3.setBackground(Color.YELLOW);
      panel3.setBorder(BorderFactory.createTitledBorder(""));
      JButton ok_button=new JButton("OK");
      ok_button.setSize(200,50);
      ok_button.setLocation(10,500);
      ok_button.setFont(new Font("TIMES NEW ROMAN",30,30));
      ok_button.setForeground(Color.BLACK);
      ok_button.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
             okButtonClicked(); 
          }
      });
      panel.add(ok_button);
      
      JButton cancel_button=new JButton("CANCEL");
      cancel_button.setSize(200,50);
      cancel_button.setLocation(350,500);
      cancel_button.setFont(new Font("TIMES NEW ROMAN",30,30));
      cancel_button.setForeground(Color.BLACK);
      cancel_button.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
              cancelButtonClicked();
          }
      });
      panel.add(cancel_button);
   
 //     getContentPane().add(BorderLayout.SOUTH,panel3);
   } 
      
   
   private void okButtonClicked(){
       try{
       y=Float.parseFloat(field.getText());
       quantity.change_qty(y);
       if(getOwner()!=null){
           ((QuantityInterface)getOwner()).setQuantity();
           dispose();
       }
       }
       catch(Exception e){
           JOptionPane.showMessageDialog((java.awt.Component)null,"ERROR: "+e.getMessage(),
                   "ERROR",JOptionPane.ERROR_MESSAGE);
       }
   }
   private void cancelButtonClicked(){
       if(getOwner()!=null){
           ((QuantityInterface)getOwner()).unsetQuantity();
           dispose();
       }
   }
       
   
   public static void main(String[] args){
       try{
           for(UIManager.LookAndFeelInfo info:    UIManager.getInstalledLookAndFeels()){
               if(("Windows").equals(info.getName())){
                   UIManager.setLookAndFeel(info.getClassName());
               }
           }
       }
       catch(Exception e){
           
       }
       QuantityScreen c=new QuantityScreen(null,false,null);
       c.setUndecorated(true);
       c.setVisible(true);
   }
    
}
