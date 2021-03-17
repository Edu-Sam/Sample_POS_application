/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;
import javax.swing.JWindow;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
public class SplashScreen extends JWindow{
    public SplashScreen(){
 //    getContentPane().setBackground(new Color(255,215,0));
 //       getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);
        getRootPane().setBorder(BorderFactory.createEtchedBorder());
      try{
/*JPanel panel=new JPanel(); 
panel.setBackground(new Color(0,0,30));
JLabel label1=new JLabel("ECLIPSE ENTERPRISE SOFTWARE");
label1.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,20));
label1.setSize(100,30);
label1.setBackground(Color.WHITE);
label1.setForeground(Color.WHITE);
panel.add(label1);
getContentPane().add(BorderLayout.NORTH,panel);

JLabel software_label=new JLabel("    EDGEHIVE  SOLUTIONS  LTD ");
software_label.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,35));
software_label.setSize(100,30);
software_label.setBackground(Color.BLACK);
software_label.setForeground(Color.BLACK);
getContentPane().add(BorderLayout.CENTER,software_label);

JPanel panel1=new JPanel();
panel1.setBackground(new Color(0,0,30));
Box box1=Box.createVerticalBox();
JLabel label2=new JLabel("A product of SMARTplus Solutions Ltd.Terms and conditions apply.By using this software you have " );
label2.setFont(new Font("TIMES NEW ROMAN",15,15));
label2.setSize(100,30);
label2.setBackground(Color.WHITE);
label2.setForeground(Color.WHITE);
box1.add(label2);


JLabel label3=new JLabel(" agreed to be bound to the licensing terms found in the signup forms or ");
label3.setFont(new Font("TIMES NEW ROMAN",15,15));
label3.setSize(100,30);
label3.setBackground(Color.WHITE);
label3.setForeground(Color.WHITE);
box1.add(label3);

JLabel label4=new JLabel("installation screens");
label4.setFont(new Font("TIMES NEW ROMAN",15,15));
label4.setSize(100,30);
label4.setBackground(Color.WHITE);
label4.setForeground(Color.WHITE);
box1.add(label4);

JLabel label5=new JLabel("                                                       PLEASE WAIT.LOADING....");
label5.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,18));
label5.setSize(200,30);
label5.setBackground(Color.RED);
label5.setForeground(Color.RED);
box1.add(label5);
panel1.add(box1);


getContentPane().add(BorderLayout.SOUTH,panel1);*/
 JPanel main_panel=new JPanel();
 main_panel.setLocation(0,0);
 main_panel.setSize(600,330);
 main_panel.setLayout(null);
 main_panel.setBackground(Color.WHITE);
 getContentPane().add(main_panel);
 
 JLabel easyllite_enterprise_label=new JLabel("Easyllite Business Management System");
 easyllite_enterprise_label.setLocation(100,5);
 easyllite_enterprise_label.setSize(400,30);
 easyllite_enterprise_label.setFont(new Font("verdana",Font.BOLD,16));
 easyllite_enterprise_label.setForeground(new Color(208,213,127));
 main_panel.add(easyllite_enterprise_label);
 
 JLabel powered_by_label=new JLabel("Powered By ");
 powered_by_label.setLocation(50,100);
 powered_by_label.setSize(200,30);
 powered_by_label.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
 powered_by_label.setForeground(new Color(105,105,105));
 main_panel.add(powered_by_label);
 
 ImageIcon icon=new ImageIcon("edgehive_sol.png");
 Image img=icon.getImage();
 Image temp_img=img.getScaledInstance(190,190,Image.SCALE_SMOOTH);
 icon=new ImageIcon(temp_img);
 JLabel logo_label=new JLabel("",icon,JLabel.CENTER);
 
// logo_label.setBounds(200,50,100,100);
 logo_label.setLocation(140,0);
 logo_label.setSize(190,190);
 main_panel.add(logo_label);
 
Box box1=Box.createVerticalBox();
box1.setLocation(50,180);
box1.setSize(600,100);
JLabel label2=new JLabel("A product of EdgeHive Solutions Ltd.Terms and conditions apply.By using this software you have " );
label2.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,13));
label2.setSize(100,30);
//label2.setBackground(Color.WHITE);
label2.setForeground(new Color(105,105,105));
box1.add(label2);


JLabel label3=new JLabel(" agreed to be bound to the licensing terms found in the signup forms or ");
label3.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,13));
label3.setSize(100,30);
label3.setForeground(new Color(105,105,105));
box1.add(label3);

JLabel label4=new JLabel("installation screens");
label4.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,13));
label4.setSize(100,30);
label4.setForeground(new Color(105,105,105));
box1.add(label4);

JLabel label5=new JLabel("                  PLEASE WAIT.LOADING....");
label5.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,15));
label5.setSize(200,30);
label5.setForeground(new Color(255,102,102));
box1.add(label5);

main_panel.add(box1);


 
 
 
   
 setSize(600,330);
 setLocationRelativeTo(null);
 show();
      }
      catch(Exception exception){
          JOptionPane.showMessageDialog((java.awt.Component)null,
                  "ERROR"+exception.getMessage(),"ERROR:",javax.swing.JOptionPane.DEFAULT_OPTION);
      }
    }
    public static void main(String[] args){
        SplashScreen sp=new SplashScreen();
    }
    }
