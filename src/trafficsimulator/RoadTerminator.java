
package trafficsimulator;

/**
 * @author Drew
 */
public class RoadTerminator extends Position {
    
    public RoadTerminator(String name, Double x, Double y) {
        super(name, x, y);
    }
    
    @Override
    public String toString() {
        return "RoadTerminator \"" + name + "\" : (" + x + ", " + y + ")";
    }
}
