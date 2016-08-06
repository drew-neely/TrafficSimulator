/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimulator;
import java.util.*;
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
   
    double laneSpace = 20;
    double medianSpace = 30;
    ArrayList<Line2D.Double> Lines;
    
    public GUIGenerator(){
        JPanel panel=new JPanel();
        getContentPane().add(panel);
        setSize(800,400);
        
    }
    
    
    
    @Override
    public void paint (Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
    for(int i =0; i<Lines.size(); i++){
        g2.draw(Lines.get(i));
    }
    }
    
    
    
    public void addAllRoads(){
        
        for(int i = 0; i< TrafficSimulator.roads.size(); i++){
            addRoad(TrafficSimulator.roads.get(i));
        }
    }
    
    public void addRoad(Road R){
        
        for(int i =0; i<R.path.size()-1; i++){
            addSegment(R, i, i+1);
        }
    }
    
    public void addSegment(Road R, int start, int end){
        
        double angle =  Math.atan(-1.0/((R.path.get(end).y-R.path.get(start).y)/(R.path.get(end).x-R.path.get(start).x)));
        
        double SX = R.path.get(start).x+.5*medianSpace*Math.cos(angle);
        double SY = R.path.get(start).y+.5*medianSpace*Math.sin(angle);
        double EX = R.path.get(end).x+.5*medianSpace*Math.cos(angle);
        double EY = R.path.get(end).y+.5*medianSpace*Math.sin(angle);
        Lines.add(new Line2D.Double(SX, SY, EX, EY));
        
        for(int i= 2; i<=R.lanesWith; i++){
            SX+=laneSpace*Math.cos(angle);
            SY+=laneSpace*Math.sin(angle);
            EX+=laneSpace*Math.cos(angle);
            EY+=laneSpace*Math.sin(angle);
            Lines.add(new Line2D.Double(SX, SY, EX, EY));
        }
        
        for(int i = 1; i<=R.lanesAgainst; i++){
            
            if(i==1){
            SX = R.path.get(start).x-.5*medianSpace*Math.cos(angle);
            SY = R.path.get(start).y-.5*medianSpace*Math.sin(angle);
            EX = R.path.get(end).x-.5*medianSpace*Math.cos(angle);
            EY = R.path.get(end).y-.5*medianSpace*Math.sin(angle);
            }else{
            SX-=laneSpace*Math.cos(angle);
            SY-=laneSpace*Math.sin(angle);
            EX-=laneSpace*Math.cos(angle);
            EY-=laneSpace*Math.sin(angle);
            }
            
            Lines.add(new Line2D.Double(SX, SY, EX, EY));
            
        }
    }
    
}
