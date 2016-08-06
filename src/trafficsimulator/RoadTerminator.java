
package trafficsimulator;

/**
 * @author Drew
 */
public class RoadTerminator extends Position {
    
    double probAdd = 0.01;
    
    
    public RoadTerminator(String name, Double x, Double y) {
        super(name, x, y);
    }
    
    public void addCar() {
        if(Math.random() < probAdd) {
            //TrafficSimulator.cars.add(new Car(this, ))
        }
    }
    
    @Override
    public String toString() {
        return "RoadTerminator \"" + name + "\" : (" + x + ", " + y + ")";
    }
}
