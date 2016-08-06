
package trafficsimulator;

/**
 * @author Drew
 */
public class Car {
    
    double stopDistance;
    double velocity;
    boolean inIntersection = false;
    
    Position pos;
    Road currentRoad;
    Lane currentLane;
    Position lastGoal;
    Position currentGoal;
    Lane targetLane;
    double direction; 
    
    public Car(Position startPos, Road startRoad, Lane startLane){
        
    }

    public void determineDirection(){
        
        direction = ( currentGoal.y - lastGoal.y)/( currentGoal.x - lastGoal.x);
        
    }
    
    public void drive(){
        
        if(inIntersection){
            
        }else{
        if(distance(pos, currentGoal)<stopDistance&& currentGoal instanceof Intersection){
            Intersection next = (Intersection) currentGoal;
            handleIntersection(next);
        }else{
            move();
        }
        
        }
    }
    
    public double distance(Position a, Position b){
        return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
    } 
    
    public void handleIntersection(Intersection I){
        
        pos.x = I.x;
        pos.y = I.y;
        
        currentLane = targetLane;
        currentRoad = currentLane.parentRoad;
        lastGoal = currentGoal;
        int change;
        if(currentLane.direction == Direction.WITH_ROAD_DEF){
            change = 1;
        }else{
            change = -1;
        }
        
        currentGoal = currentRoad.path.get(currentRoad.path.indexOf(I)+change);
    }
    
    public void move(){
        double dX = 1.0/Math.sqrt(1+direction*direction)*velocity;
        pos.x += dX;
        pos.y+= Math.sqrt(1-dX*dX);
    }
    
    public void decideNextTurn(){
        
    }
    
}


