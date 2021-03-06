package src.model.world;

/**
 * An Entity is an object in the board of a level
 */
public abstract class Entity {
	protected Board levelBoard; 
	protected int x;
	protected int y;
	
    public Entity(Board b, int xStart, int yStart) {
    	levelBoard = b;
    	x = xStart;
    	y = yStart;
    }
    
    public int getX () {
    	return this.x;
    }
    public int getY() {
    	return this.y;
    }
    
    public abstract void run();
    
}
