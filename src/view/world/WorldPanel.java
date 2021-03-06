package src.view.world;

import src.model.world.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class WorldPanel extends JPanel implements IDisplayable {
	public static final int tileLength = 32;
	protected Board boardModel;
	private ImageLibrary spriteLibrary;

    /**
     *
     */
    public WorldPanel (Board b) {
    	this.boardModel = b ;
    	initiateView();
    	this.spriteLibrary = new ImageLibrary ();
    	this.spriteLibrary.loadWorldImage();
    }


	public void setBoard(Board b){
		this.boardModel = b ;
	}

    private void initiateView() {
    	int length = Board.boardLength * tileLength;
    	this.setPreferredSize(new Dimension(length,length));
    	this.setMinimumSize(new Dimension(length,length));
    	this.setMaximumSize(new Dimension(length,length));

    }


    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	//Draw Here
    	this.paintBoardDecor(g);
    	this.paintPersonage(g);
    }

    private void paintBoardDecor(Graphics g) {
    	for(int i = 0 ; i < Board.boardLength ; i++) {
    		for(int j = 0 ; j < Board.boardLength ; j++) {
				String decorSpriteName = boardModel.getDecor(j,i) != null ? boardModel.getDecor(j,i).toString(): "vide";
				spriteLibrary.getSprite(decorSpriteName).paintIcon(this,g,j*tileLength, i*tileLength);
    		}
		}
    }

    private void paintPersonage (Graphics g) {
		int i = 0;
		while(i < boardModel.getCharacter().size()){
			Entity e = boardModel.getCharacter().get(i);
    		String entitySpriteName = e.toString();
			spriteLibrary.getSprite(entitySpriteName).paintIcon(this,g,e.getY()*tileLength, e.getX()*tileLength);
			i ++;
		}
    }
    public void updateDisplay() {
    	repaint();
	}
	
	public ImageLibrary getImageLibrary() {
		return spriteLibrary;
	}
}
