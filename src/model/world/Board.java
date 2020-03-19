package src.model.world;

import java.util.*;
import src.model.langage.*;

public class Board {

	private Cell[][] cells;
	private Cell finish;
	private ArrayList<Entity> characters;
	
	public static final int boardLength = 17; 
	
    //FIXME : Should be deleted
    public Board(int yFinish, int xFinish, ArrayList<Entity> characters) {
    	this.cells = new Cell[boardLength][boardLength];
    	this.characters = characters;
    	createBorder();
    	initiateCells();
    	try{
    	    this.finish = this.cells[yFinish][xFinish];
    	} catch(Exception e){
    	    System.out.println("Erreur : Les coordonnées sont hors limite");
    	    return;
    	}
    }

    public Board () {
    	this.cells = new Cell[boardLength][boardLength];
    	createBorder();
    	initiateCells();
    }
    
    public void initiatePlayerActions(Queue<Action> script){
        Player player = getPlayer();
        if(player == null) {
        	System.out.println("Erreur le joueur n'est pas initialiser ! ");
        	return;
        }
        player.setActions(script);
    }

    //FIXME: not optimal yet
    //FIXME: high risk of NullPointerException
    private Player getPlayer(){
		Player player = null;
        for(Entity p: characters){
            if(p instanceof Player)
                player = (Player) p;
        }
        if(player == null)
        	return null;
		return player;
    }

    public Decor getDecor(int y, int x) {
    	try {
    		return this.cells[y][x].getDecor();
    	} catch(IndexOutOfBoundsException e) {
    		System.out.println("[Erreur] : Case inexistante");
    		return null;
    	}
    }
    
    public void setCell(int y, int x, Cell c) {
    	try {
    		this.cells[y][x] = c;
    	} catch(Exception e) {
    		System.out.println("[Erreur]: La case n'a pas pu être modifiée");
    		return;
    	}
    }
    
    public void setEntity(int y, int x, Entity e) {
    	try {
    		this.cells[y][x].setEntity(e);
    	} catch (Exception ex) {
    		System.out.println("[Erreur]: La case n'a pas pu être modifiée");
    		return;
    	}
    }

    public void setDecor(Decor d,int y, int x) {
    	this.cells[y][x].setDecor(d);
    }
    /*
     * @return true if the Cell dont have an obstacle or entity false otherwise
     */
    private boolean isNotOccupied(int y, int x) {
    	return this.cells[y][x].getEntity() == null && !(this.cells[y][x].getDecor() instanceof Obstacle);
    }

    //method to initiate the entity when its not on the board
    public boolean initiateEntity(int y, int x, Entity being) {
    	try {
    		if(isNotOccupied(y, x)) {
    			this.cells[y][x].setEntity(being);
    			return true;
    		} else {
    			System.out.println("[Erreur] : Il y a quelque chose sur cette case");
    			return false;
    		}
    	} catch(IndexOutOfBoundsException e) {
    		System.out.println("[Erreur] : Case inexistante");
    		return false;
    	}
    }

	public Cell[][] getCells(){
		return cells;
	}

	public ArrayList<Entity> getCharacter(){
		return this.characters;
	}
    //This method will create a border on the board that won't be crossable
    private void createBorder() {
    	for(int i = 0; i < this.cells[0].length; i++) {
    		this.cells[0][i] = new Cell(new Wall(this, 0, i));
    		this.cells[16][i] = new Cell(new Wall(this, 16, i));
    		this.cells[i][0] = new Cell(new Wall(this, i, 0));
    		this.cells[i][16] = new Cell(new Wall(this, i, 16));
    	}
    }

    //This method will initiate all cells remaining that are still = null
    private void initiateCells() {
    	for(int i = 0; i < this.cells.length; i++) {
    		for(int j = 0; j < this.cells[0].length; j++) {
    			if(this.cells[i][j] == null) {
    				this.cells[i][j] = new Cell();
    			}
    		}
    	}
    }
    
    //TODO the method should verify that the entity isnt trying to go into an obstacle
    //TODO what happens if a player entity try to go into an ennemy entity ? 
    public boolean move (int yStart, int xStart, int yEnd, int xEnd) {
    	try {
    		if(isNotOccupied(yEnd, xEnd)) {
    			this.cells[yEnd][xEnd].setEntity(this.cells[yStart][xStart].getEntity());
    			this.cells[yStart][xStart].setEntity(null);
    		} else {
    			System.out.println("[Erreur] : Il y a quelque chose sur la destination");
    			return false;
    		}
    	} catch(IndexOutOfBoundsException e) {
    		System.out.println("[Erreur] : Le déplacement n'a pas pu se faire");
    		return false;
    	}
    	System.out.println("("+xStart+":"+yStart+")"+" -> ("+ xEnd + ":" + yEnd + ")");
    	return true;
    }

    //FIXME Function Place Holder for the view
    private void filling () {
    	for (int i = 1; i < this.cells.length - 1; i++)
    		for(int j = 1; j < this.cells[i].length - 1; j++)
    				this.cells[i][j] = new Cell ();
    }

    //FIXME: maybe Personage rather than Player for instanceof
    public boolean endOfLevel(){
    	return (this.finish.getEntity() != null && this.finish.getEntity() instanceof Player) || !getPlayer().hasActionsLeft();
    }

    public void run() {
    	for(Entity p : this.characters) 
    		p.run();
    }

    //Terminal View
    public String toString() {
    	String res = "";
    	for(int i = 0; i< this.cells.length ; i++) {
    		res = res + "\n";
    		for (int j=0; j< this.cells[i].length ; j++)
    			res = res + this.cells[i][j].toString() + " | ";
    	}
    	return res;
    }
}
