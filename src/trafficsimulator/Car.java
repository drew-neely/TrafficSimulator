
package trafficsimulator;

/**
 * @author Drew
 */
public class Car {
    
    double stopDistance;
    double snapDistance;
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
        
        pos = startPos;
        currentRoad = startRoad;
        currentLane = startLane;
        if(currentLane.direction==Direction.WITH_ROAD_DEF){
            lastGoal = currentRoad.path.get(0);
            currentGoal = currentRoad.path.get(1);
        }else{
            lastGoal = currentRoad.path.get(currentRoad.path.size()-1);
            currentGoal = currentRoad.path.get(currentRoad.path.size()-2);
        }
    }

    public void determineDirection(){
        
        direction = ( currentGoal.y - lastGoal.y)/( currentGoal.x - lastGoal.x);
        
    }
    
    public void drive(){
        
        if(inIntersection){
            
        }else{
        if(distance(pos, currentGoal)<stopDistance&& currentGoal instanceof Intersection){
            
            handleIntersection();
        }else if(distance(pos, currentGoal)<snapDistance&& !(currentGoal instanceof Intersection)){
            pos.x = currentGoal.x;
            pos.y = currentGoal.y;
            lastGoal = currentGoal;
            int change;
        if(currentLane.direction == Direction.WITH_ROAD_DEF){
            change = 1;
        }else{
            change = -1;
        }
            currentGoal = currentRoad.path.get(currentRoad.path.indexOf(currentGoal)+change);
            
            determineDirection();
            
        }else{
            move();
        }
        
        }
    }
    
    public double distance(Position a, Position b){
        return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
    } 
    
    public void handleIntersection(){
        
        pos.x = currentGoal.x;
        pos.y = currentGoal.y;
        
        currentLane = targetLane;
        currentRoad = currentLane.parentRoad;
        lastGoal = currentGoal;
        int change;
        if(currentLane.direction == Direction.WITH_ROAD_DEF){
            change = 1;
        }else{
            change = -1;
        }
        
        currentGoal = currentRoad.path.get(currentRoad.path.indexOf(currentGoal)+change);
        decideNextTurn();
        determineDirection();
    }
    
    public void move(){
        double dX = 1.0/Math.sqrt(1+direction*direction)*velocity;
        pos.x += dX;
        pos.y+= Math.sqrt(1-dX*dX);
    }
    
    public void decideNextTurn(){
        Lane newLane;
        do{
            int i = randomInt(0,currentRoad.lanes.length);
            newLane = currentRoad.lanes[i];
        }while(newLane.direction!=currentLane.direction);
        
        changeLanes(newLane);
        
        if(currentGoal instanceof Intersection){
            Intersection next = (Intersection) currentGoal;
            targetLane = next.getPossibilities(newLane).get(randomInt(0,next.getPossibilities(newLane).size()-1));
        }
    }
    
    public int randomInt(int low, int high){
        return (int)(low +(high-low+1)*Math.random()); 
    }
    
    public void changeLanes(Lane newLane){
        currentLane = newLane;
    }
}


