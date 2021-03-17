/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.awt.event.*;
public class SectionDialog extends JDialog {
    private AlterTransaction alter_transaction;
    private List<Account> section_list;
    private HashMap<JButton,Integer> map=new HashMap<JButton,Integer>();
    private CustomColorButton item_button;
    private Account section_instance;
    private JPanel inner_panel;
    private UserTransaction user_transaction;
    public SectionDialog(JFrame owner,boolean Modal,UserTransaction new_user_transaction){
        super(owner,Modal);
        alter_transaction=new AlterTransaction();
        user_transaction=new_user_transaction;
        getContentPane().setLayout(null);
        JPanel main_panel=new JPanel();
        main_panel.setLocation(0,0);
        main_panel.setSize(500,600);
        main_panel.setBorder(BorderFactory.createEtchedBorder());
   //     main_panel.setBackground(new Color(240,230,140));
  //      main_panel.setBackground(new Color(255,215,0));
        main_panel.setBackground(new Color(255,228,181));
        getContentPane().add(main_panel);
        
        inner_panel=new JPanel();
        inner_panel.setLocation(0,0);
        inner_panel.setSize(500,600);
        inner_panel.setBorder(BorderFactory.createTitledBorder("Select"));
        inner_panel.setBackground(new Color(255,228,181));
        inner_panel.setLayout(new BoxLayout(inner_panel,BoxLayout.Y_AXIS));
        main_panel.add(inner_panel);
        
        get_sections();
        
        setSize(500,600);
        setLocationRelativeTo(null);
        
    }
    private void get_sections(){
        section_list=alter_transaction.get_section();
        section_instance=new Account();
  //      Box box=Box.createVerticalBox();
        for(int i=0;i<section_list.size();i++){
            section_instance=section_list.get(i);
   //         item_button=new JButton(""+(i+1)+":"+" "+section_instance.getAccountName());
            item_button=new CustomColorButton(new Color(0,128,0),Color.WHITE);
            item_button.setText(""+(i+1)+":"+" "+section_instance.getAccountName());
         //   item_button.setPreferredSize(new Dimension(500,30));
            item_button.setSize(500,30);
   //         item_button.setBackground(new Color(0,128,0));
  //          item_button.setForeground(Color.WHITE);
            item_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,20));
            map.put(item_button,new Integer(i));
            item_button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    Integer index=map.get(e.getSource());
                    user_transaction.set_section_id(section_list.get(index).getAccountNo());
                    quit();
                    ((Transaction)getOwner()).StartTransact();
                    
                }
            });
            inner_panel.add(item_button);
            
            inner_panel.add(Box.createVerticalStrut(15));
            
        }
        
    }
    private void quit(){
          this.dispose();
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
   SectionDialog section_dialog=new SectionDialog(null,false,null);
   section_dialog.setUndecorated(true);
   section_dialog.setVisible(true);
//   Trial trial=new Trial("",null);
   
   
    
}
}
