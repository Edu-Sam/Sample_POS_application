/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ParametersDialog extends JDialog{
    
    public ParametersDialog(JFrame owner,boolean Modal){
        super(owner,Modal);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(240,128,128));
        
        JPanel topPanel=new JPanel();
        topPanel.setLayout(null);
        topPanel.setLocation(0,0);
        topPanel.setSize(400,20);
     //   topPanel.setBackground(new Color(30,144,255));
        topPanel.setBackground(new Color(100,149,237));
        getContentPane().add(topPanel);
        
        JLabel admin_menu_label=new JLabel("Admin Menu");
        admin_menu_label.setLocation(5,5);
        admin_menu_label.setSize(200,13);
        admin_menu_label.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,14));
        admin_menu_label.setForeground(Color.BLACK);
        topPanel.add(admin_menu_label);
        JButton admin_param_button=new JButton("ADMIN PARAMETERS");
        admin_param_button.setLocation(80,50);
        admin_param_button.setSize(200,30);
        admin_param_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,15));
        admin_param_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                call_param();
            }
        });
        getContentPane().add(admin_param_button);
        
        JButton exit_button=new JButton("EXIT");
        exit_button.setLocation(80,300);
        exit_button.setSize(200,30);
        exit_button.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,15));
        exit_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                quit();
            }
        });
        getContentPane().add(exit_button);
        
        setSize(400,400);
        setLocationRelativeTo(null);
    }
    private void quit(){
        this.dispose();
    }
    private void call_param(){
        Parameters parameters=new Parameters(this,true);
        parameters.setUndecorated(true);
        parameters.setVisible(true);
    }
    
    public static void main(String [] args){
        try{
            for(UIManager.LookAndFeelInfo info:  UIManager.getInstalledLookAndFeels()){
                if(("Windows").equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                }
            }
        }
        catch(Exception e){
            
        }
        ParametersDialog f=new ParametersDialog(null,false);
        f.setUndecorated(true);
        f.setVisible(true);
    }
    
}
