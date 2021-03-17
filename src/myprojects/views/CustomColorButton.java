/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class CustomColorButton extends JButton implements ActionListener,MouseListener {
    private boolean hovered=false;
    private boolean clicked=false;
    
    private Color normalColor=null;
    private Color lightColor=null;
    private Color darkColor=null;
    
    public CustomColorButton(Color normalRedColor,Color fontColor){
        setForeground(fontColor);
        
        this.normalColor=normalRedColor;
        this.lightColor=normalRedColor.brighter();
        this.darkColor=normalRedColor.darker();
        
        addActionListener(this);
        addMouseListener(this);
        setContentAreaFilled(false);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2d=(Graphics2D)g;
        
        
        GradientPaint gp=null;
        
        if(clicked){
            gp=new GradientPaint(0,0,darkColor,0,getHeight(),darkColor.darker());
        }
        else if(hovered){
            gp=new GradientPaint(0,0,lightColor,0,getHeight(),lightColor.darker());
            
            }
        else{
            gp=new GradientPaint(0,0,normalColor,0,getHeight(),normalColor.darker());
            
        }
        g2d.setPaint(gp);
        
     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
     g2d.fillRoundRect(0,0,getWidth(),getHeight(),7,7);
     
     g2d.setColor(darkColor.darker().darker());
     g2d.drawRoundRect(0,0,getWidth()-1,getHeight()-1,7,7);
     
     super.paintComponent(g);
    }
    
    @Override
    public void actionPerformed(ActionEvent args0){
//        System.out.println("Button Clicked");
    }
    @Override
    public void mouseClicked(MouseEvent args0){
        
    }
    @Override
    public void mouseEntered(MouseEvent args0){
        hovered=true;
        clicked=false;
        repaint();
    }
    @Override 
    public void mouseExited(MouseEvent args0){
        hovered=false;
        clicked=false;
        
        repaint();
    }
    @Override
    public void mousePressed(MouseEvent args0){
        hovered=true;
        clicked=true;
        
        repaint();
    }
    @Override 
    public void mouseReleased(MouseEvent args0){
        hovered=true;
        clicked=false;
        repaint();
    }
    
    
}
