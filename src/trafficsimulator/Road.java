
package trafficsimulator;

import java.util.*;

/**
 * @author Drew
 */
public class Road {
    
    ArrayList<Position> path = new ArrayList<Position>();
    int lanesWith;
    int lanesAgainst;
    Lane[] lanes;
    String name;
    
    public Road(String name, int lanesWith, int lanesAgainst) {
        this.name = name;
        this.lanesWith = lanesWith;
        this.lanesAgainst = lanesAgainst;
        lanes = new Lane[lanesWith + lanesAgainst];
        for(int i = 0; i < lanes.length; i++) {
            lanes[i] = new Lane(this, (i < lanesWith)? Direction.WITH_ROAD_DEF : Direction.AGAINST_ROAD_DEF);
        }
    }
    
    public void addPosition(Position position) {
        path.add(position);
    }
    
    public void addRoadTerminator(RoadTerminator roadTerminator) {
        path.add(roadTerminator);
    }
    
    public void setIntersection(Intersection intersection, ArrayList<ArrayList<Integer>> laneOptions) {
        path.add(intersection);
        for(int a = 0; a < laneOptions.size(); a++) {
            intersection.setInfo(this, lanes[a], laneOptions.get(a));
        }
    }
    
}