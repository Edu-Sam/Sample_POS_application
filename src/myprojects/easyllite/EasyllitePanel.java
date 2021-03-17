/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects.easyllite;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author EDWIN
 */
public class EasyllitePanel extends JPanel{
 @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color2 = new Color(238,232,170);
        Color color1 =new Color(255,255,255);
//        Color color1 = new Color(250,235,215);
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRoundRect(0, 0, w-1, h-1, 10, 10);
   //     g2d.fillRect(0, 0, w, h);
    }
}