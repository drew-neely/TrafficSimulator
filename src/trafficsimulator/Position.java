
package trafficsimulator;

/**
 * @author Drew
 */
public class Position {
    
    String name;
    double x;
    double y;
    
    public Position(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "Position \"" + name + "\" : (" + x + ", " + y + ")";
    }
}