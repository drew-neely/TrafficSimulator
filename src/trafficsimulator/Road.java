
package trafficsimulator;

/**
 * @author Drew
 */
public class Road {
    
    Position[] path;
    Lane[] lanes;
    String name;
    
    public Road(String name, int lanesWith, int lanesAgainst) {
        this.name = name;
        lanes = new Lane[lanesWith + lanesAgainst];
        for(int i = 0; i < lanes.length; i++) {
            lanes[i] = new Lane((i < lanesWith)? Direction.WITH_ROAD_DEF : Direction.AGAINST_ROAD_DEF);
        }
    }
    
    public void setIntersectionData(int[][] laneOptions) {
        
    }
    
}
