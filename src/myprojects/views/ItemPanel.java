/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
public class ItemPanel extends JDialog{
    public ItemPanel(JFrame myOwner,String title){
        super(myOwner,title);
    JPanel finalPanel=new JPanel();
    finalPanel=setupPanel();
    getContentPane().add(finalPanel);
    addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent er){
       System.exit(0);
        }
    });
    
    
    }
    private JPanel setupPanel(){
        JPanel tabbedpanel=new JPanel();
        tabbedpanel.setLayout(new GridLayout(2,1,10,10));
        String header[]={"ITEM","UNIT PRICE","QTY","PRICE","TOTAL"};
        DefaultTableModel model3=new DefaultTableModel(header,50);
        JTable aTable=new JTable(model3){
            @Override
            public  boolean isCellEditable(int args0,int args1){
                return false;
            }
        };
        JScrollPane mySecondPane=new JScrollPane(aTable);
    //    JPanel tablePanel=new JPanel();
     //   tablePanel.add(mySecondPane);
        tabbedpanel.add(mySecondPane);
        JButton okButton=new JButton("OK");
        okButton.setLocation(0,400);
        okButton.setSize(50,20);
  //    tabbedpanel.add(BorderLayout.SOUTH,okButton);
        
        
        JTabbedPane tabbedPane=new JTabbedPane();
        JPanel aPanel=new JPanel();
        aPanel.setLayout(new GridLayout(3,3,10,10));
        tabbedPane.addTab("PAGE 1",null,aPanel);
        tabbedpanel.add(tabbedPane);
        getContentPane().add(tabbedpanel);
        return tabbedpanel;
    }
        
        
        
    
    public static void main(String[] args){
        ItemPanel i=new ItemPanel(new JFrame(),"GROUP:FOOD");
        i.setSize(900,500);
        i.setVisible(true);
        
    }
    
}
