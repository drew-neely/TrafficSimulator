
package trafficsimulator;

import java.util.*;

/**
 * @author Drew
 */
public class RoadTerminator extends Position {
    
    double probAdd = 0.01;
    Road road;
    
    public RoadTerminator(String name, Double x, Double y) {
        super(name, x, y);
    }
    
    public void setRoad(Road road) {
        this.road = road;
    }
    
    public void addCar() {
        if(Math.random() < probAdd) {
            Direction neededDir = (road.path.get(0).equals(this))? Direction.WITH_ROAD_DEF : Direction.AGAINST_ROAD_DEF;
            ArrayList<Lane> lanes = new ArrayList<Lane>();
            for(int i = 0; i < road.lanes.length; i++) {
                if(road.lanes[i].direction == neededDir) {
                    lanes.add(road.lanes[i]);
                }
            }
            if(lanes.isEmpty()) {
                return;
            }
            Lane lane = lanes.get((int) (Math.random() * lanes.size()));
            TrafficSimulator.cars.add(new Car(this, road, lane));
            
        }
    }
    
    @Override
    public String toString() {
        return "RoadTerminator \"" + name + "\" : (" + x + ", " + y + ")";
    }
}
