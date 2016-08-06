
package trafficsimulator;
import java.util.*;
import java.io.*;
/**
 *
 * @author Drew
 */
public class TrafficSimulator extends TimerTask {

    static ArrayList<Position> positions = new ArrayList<Position>();
    static ArrayList<RoadTerminator> roadTerminators = new ArrayList<RoadTerminator>();
    static ArrayList<Intersection> intersections = new ArrayList<Intersection>();
    static ArrayList<Road> roads = new ArrayList<Road>();
    static ArrayList<Car> cars = new ArrayList<Car>();
    
    static GUIGenerator gui = new GUIGenerator();
    
    public static void main(String[] args) throws IOException{
        Scanner map = new Scanner(new File("Test1_2Intersections.rdm"));
        while(map.hasNextLine()) {
            Scanner line = new Scanner(map.nextLine());
            String type = line.next();
            if(type.equals(TypeNames.POSITION)) {
                positions.add(new Position(line.next(), line.nextDouble(), line.nextDouble()));
            } else if(type.equals(TypeNames.TERMINATOR)) {
                roadTerminators.add(new RoadTerminator(line.next(), line.nextDouble(), line.nextDouble()));
            } else if(type.equals(TypeNames.INTERSECTION)) {
                intersections.add(new Intersection(line.next(), line.nextDouble(), line.nextDouble()));
            } else if(type.equals(TypeNames.ROAD)) {
                Road road = new Road(line.next(), line.nextInt(), line.nextInt());
                while(line.hasNext()) {
                    Scanner roadPosData = new Scanner(line.next());
                    roadPosData.useDelimiter(":");
                    String name = roadPosData.next();
                    for(int i = 0; i < positions.size(); i++) {
                        if(positions.get(i).name.equals(name)) {
                            road.addPosition(positions.get(i));
                        }
                    }
                    for(int i = 0; i < roadTerminators.size(); i++) {
                        if(roadTerminators.get(i).name.equals(name)) {
                            road.addRoadTerminator(roadTerminators.get(i));
                            roadTerminators.get(i).setRoad(road);
                        }
                    }
                    for(int i = 0; i < intersections.size(); i++) {
                        if(intersections.get(i).name.equals(name)) {
                            ArrayList<ArrayList<Integer>> laneOptions = new ArrayList<ArrayList<Integer>>();
                            while(roadPosData.hasNext()) {
                                ArrayList<Integer> laneData = new ArrayList<Integer>();
                                Scanner nextLanes = new Scanner(roadPosData.next());
                                nextLanes.useDelimiter(",");
                                while(nextLanes.hasNext()) {
                                    laneData.add(nextLanes.nextInt());
                                }
                                laneOptions.add(laneData);
                            }
                            road.addPosition(intersections.get(i));
                        }
                    }
                }
                roads.add(road);
            }
        }
        TrafficSimulator trafficSim = new TrafficSimulator();
        gui.addAllRoads();
        gui.addAllCars();
        
        gui.setVisible(true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(trafficSim, new Date(), 60);
    }
    
    @Override
    public void run() {
        for(int i = 0; i < roadTerminators.size(); i++) {
            roadTerminators.get(i).addCar();
        }
        
        for(int i = 0; i < cars.size(); i++) {
            cars.get(i).drive();
        }
        gui.repaint();
    }
    
}
