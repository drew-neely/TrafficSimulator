
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
    
    public void setInfo(Road road, Lane fromLane, ArrayList<Integer> toLanes) {
        preTurnOptions.put(fromLane, toLanes);
    }
    
    public void finalize() {
        
    }
}
