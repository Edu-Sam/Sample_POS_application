/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
public class Trial2 extends JFrame{
    public Trial2(String title){
        super(title);
        getContentPane().setLayout(new GridLayout(2,1,0,0));
        JPanel original_Panel=new JPanel(new GridLayout(1,2,0,0));
        JPanel panel1=new JPanel();
        panel1.setLayout(new BorderLayout(2,2));
         String header[]={"ITEM","UNIT PRICE","QTY","PRICE","TOTAL"};
       DefaultTableModel model=new DefaultTableModel(header,30);
       JTable myTable=new JTable(model){
        @Override
        public boolean isCellEditable(int args0,int args1){
           return false;
       }
    };
       JScrollPane pane=new JScrollPane(myTable);
       panel1.add(BorderLayout.CENTER,pane);
       panel1.setBackground(new Color(0,0,64));
    //   panel1.setLocation(0,0);
       original_Panel.add(panel1);
       
       JPanel panel2=new JPanel();
        panel2.setBorder(BorderFactory.createRaisedBevelBorder());
        panel2.setLayout(null);
        JButton button1=new JButton("PAY");
        button1.setSize(300,50);
        button1.setBackground(Color.BLACK);
        button1.setFont(new Font("TIMES NEW ROMAN",50,25));
        button1.setForeground(Color.BLACK);
        button1.setLocation(5,10);
        button1.setBorder(BorderFactory.createRaisedBevelBorder());
   //     panel2.setLocation(0,0);
        panel2.add(button1);
        panel2.setBackground(Color.YELLOW);
        panel2.setLocation(600,0);
        original_Panel.add(panel2);
        
        getContentPane().add(original_Panel);
        
        JPanel panel3=new JPanel();
        panel3.setBorder(BorderFactory.createRaisedBevelBorder());
        panel3.setLayout(new BorderLayout(2,2));
        JTabbedPane tabbedpane=new JTabbedPane();
        JPanel tabbedPanel=new JPanel();
        tabbedPanel.setLayout(new GridLayout(4,4,0,0));
        tabbedPanel.setBackground(Color.PINK);
        tabbedpane.addTab("PAGE 1",null,tabbedPanel,"FIRST PANEL");
        panel3.add(tabbedpane);
        
   //     panel2.setLocation(0,0);
        
  //      panel3.setSize(575,500);
     getContentPane().add(panel3);
     Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width,screenSize.height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
      new Trial2("SALES SCREEN").setVisible(true);
       
   }
    
}
    
