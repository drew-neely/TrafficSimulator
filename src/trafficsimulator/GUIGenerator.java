/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author tyler
 */
public class GUIGenerator extends JFrame{
   
    public GUIGenerator(){
        JPanel panel=new JPanel();
        getContentPane().add(panel);
        setSize(800,400);
        
    }
    public void paint (Graphics g){
        super.paint(g);
        g.drawLine(0,180,800,180);
        g.drawLine(0,220,800,220);
        g.drawLine(180,0,180,400);
        g.drawLine(220,0,220,400);
        g.drawLine(580,0,580,400);
        g.drawLine(620,0,620,400);
    }
    
}
