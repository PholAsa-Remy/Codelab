package src.model.world;

import java.util.*;

public class Cell {

	private Decor decor;
	private Entity being;

    public Cell() {
    	this.decor = null;
    	this.being = null;
    }

    public Cell(Decor decor) {
    	this.decor = decor;
    	this.being = null;
    }
    
    public Entity getEntity() {
    	return this.being;
    }
    
    public Decor getDecor() {
    	return this.decor;
    }
    
    protected void setEntity(Entity e) {
    	this.being = e;
    }
}
