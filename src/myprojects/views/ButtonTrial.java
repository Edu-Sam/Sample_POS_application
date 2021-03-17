/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ButtonTrial extends JDialog {
    public ButtonTrial(JFrame owner,boolean Modal){
        super(owner,Modal);
        
        getContentPane().setLayout(null);
        CustomColorButton custom_button=new CustomColorButton(Color.GREEN,Color.WHITE);
        custom_button.setText("MY BUTTON");
        custom_button.setLocation(10,20);
        custom_button.setSize(200,40);
        custom_button.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,14));
        custom_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("YES");
            }
        });
        getContentPane().add(custom_button);
        
        setSize(400,400);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args){
        try{
         for(UIManager.LookAndFeelInfo  info: UIManager.getInstalledLookAndFeels()){
//             System.out.println(info.getClassName());
             if(("Windows").equals(info.getName())){
                 UIManager.setLookAndFeel(info.getClassName());
             }}}
         catch(Exception e){
             
         }
        ButtonTrial button_trial=new ButtonTrial(null,false);
        button_trial.setUndecorated(true);
        button_trial.setVisible(true);
    }
    
}
