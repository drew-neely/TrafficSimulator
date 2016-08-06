
package trafficsimulator;

/**
 * @author Drew
 */
public class Lane {
    Direction direction;
    Road parentRoad;
    
    public Lane(Road parentRoad, Direction direction) {
        this.parentRoad = parentRoad;
        this.direction = direction;
    }
    
    public String toString() {
        return "";
    } 
}
