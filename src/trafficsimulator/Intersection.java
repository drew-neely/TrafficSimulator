
package trafficsimulator;

import java.util.*;

/**
 * @author Drew
 */
public class Intersection extends Position {
    
    HashMap<Lane, ArrayList<Integer>> preTurnOptions = new HashMap<Lane, ArrayList<Integer>>();
    HashMap<Lane, ArrayList<Lane>> turnOptions = new HashMap<Lane, ArrayList<Lane>>(); 
    ArrayList<Road> roads = new ArrayList<Road>(); // not defined in any particular order
    
    public Intersection(String name, int x, int y ) {
        super(name, x, y);
    }
    
    public ArrayList<Lane> getPossibilities(Lane start) {
        return turnOptions.get(start);
    }
    
    public void setInfo(Road road, Lane fromLane, ArrayList<Integer> preToLanes) {
        preTurnOptions.put(fromLane, preToLanes);
    }
    
    public void finalize() {
        for (Map.Entry<Lane, ArrayList<Integer>> entry : preTurnOptions.entrySet()) {
            Lane fromLane = entry.getKey();
            ArrayList<Integer> preToLanes = entry.getValue();
            ArrayList<Lane> toLanes = new ArrayList<Lane>();
            Road toRoad = (roads.get(0).equals(fromLane.parentRoad))? roads.get(1) : roads.get(0);
            for(int i = 0; i < preToLanes.size(); i++) {
                toLanes.add(toRoad.lanes[preToLanes.get(i)]);
            }
            turnOptions.put(fromLane, toLanes);
        }
    }
}
