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
public class GUIGenerator extends JFrame {

    double carRadius = 10;
    double laneSpace = 20;
    double medianSpace = 30;
    ArrayList<Line2D.Double> Lines = new ArrayList<Line2D.Double>();
    ArrayList<Ellipse2D.Double> Ellipses = new ArrayList<Ellipse2D.Double>();

    public GUIGenerator() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, 2000, 2000);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < Lines.size(); i++) {
            g2.draw(Lines.get(i));
        }
        
        for(int i = 0; i < Ellipses.size(); i++){
        g2.fill(Ellipses.get(i));
    }
    }

    public void addAllRoads() {

        for (int i = 0; i < TrafficSimulator.roads.size(); i++) {
            addRoad(TrafficSimulator.roads.get(i));
        }
    }

    public void addRoad(Road R) {

        for (int i = 0; i < R.path.size() - 1; i++) {
            addSegment(R, i, i + 1);
        }
    }

    public void addSegment(Road R, int start, int end) {

        double angle = Math.atan(-1.0 / ((R.path.get(end).y - R.path.get(start).y) / (R.path.get(end).x - R.path.get(start).x)));

        double SX = R.path.get(start).x + .5 * medianSpace * Math.cos(angle);
        double SY = R.path.get(start).y + .5 * medianSpace * Math.sin(angle);
        double EX = R.path.get(end).x + .5 * medianSpace * Math.cos(angle);
        double EY = R.path.get(end).y + .5 * medianSpace * Math.sin(angle);
        Lines.add(new Line2D.Double(SX, SY, EX, EY));

        for (int i = 2; i <= R.lanesWith; i++) {
            SX += laneSpace * Math.cos(angle);
            SY += laneSpace * Math.sin(angle);
            EX += laneSpace * Math.cos(angle);
            EY += laneSpace * Math.sin(angle);
            Lines.add(new Line2D.Double(SX, SY, EX, EY));
        }

        for (int i = 1; i <= R.lanesAgainst; i++) {

            if (i == 1) {
                SX = R.path.get(start).x - .5 * medianSpace * Math.cos(angle);
                SY = R.path.get(start).y - .5 * medianSpace * Math.sin(angle);
                EX = R.path.get(end).x - .5 * medianSpace * Math.cos(angle);
                EY = R.path.get(end).y - .5 * medianSpace * Math.sin(angle);
            } else {
                SX -= laneSpace * Math.cos(angle);
                SY -= laneSpace * Math.sin(angle);
                EX -= laneSpace * Math.cos(angle);
                EY -= laneSpace * Math.sin(angle);
            }

            Lines.add(new Line2D.Double(SX, SY, EX, EY));

        }
    }
    
    public void addAllCars(){
        
        for(int i = 0; i<TrafficSimulator.cars.size(); i++){
            findCarPosition(TrafficSimulator.cars.get(i));
        }
            
        
    }
    
    public void findCarPosition(Car C){
        int index =0;
        for(int i =0; i< C.currentRoad.lanes.length; i++){
         if(C.currentRoad.lanes[i].equals(C.currentLane))
             index = i;
        }
        
        
        double angle = Math.atan(-1.0/C.direction);
        double startX = C.pos.x;
        double startY = C.pos.y;
        double offset;
        if(index<C.currentRoad.lanesAgainst){
            offset = -medianSpace/2.0 - laneSpace*(C.currentRoad.lanesAgainst-index-1);
        }else{
            offset = medianSpace/2.0 +laneSpace*(index-C.currentRoad.lanesAgainst);
        }
        
        double finalX = startX + offset*Math.cos(angle);
        double finalY = startY + offset*Math.sin(angle);
        
        Ellipses.add(new Ellipse2D.Double(finalX, finalY, carRadius, carRadius));
    }
}
