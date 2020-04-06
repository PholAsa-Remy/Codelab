package src.editor.view;

import javax.swing.*;
import src.model.world.*;
import src.controller.ControllerEditor;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;

public class CollectableGeneratorPanel extends JPanel{
	private ControllerEditor controller;
	private PlacementButton key;
	private PlacementButton coin;
	
	public CollectableGeneratorPanel (ControllerEditor c) {
		this.controller = c;
		this.key = new PlacementButton(c,(b,y,x) -> keyPlacement(b,y,x));
		this.coin = new PlacementButton(c,(b,y,x) -> coinPlacement(b,y,x));
		
		layoutPlacement();
	}
	
	private void layoutPlacement() {
		TitledBorder title = BorderFactory.createTitledBorder("Collectable");
		this.setBorder(title);
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		this.addButton();
	}
	
	private void addButton() {
		this.addWithSeparation(key);
		this.addWithSeparation(coin);
	}
	
	private void addWithSeparation(JComponent c) {
    	this.add(c);
    	this.add(Box.createRigidArea(new Dimension(0,5)));
    }
	
	private static void keyPlacement(Board b, int x, int y) {
		b.initiateEntity( y, x , new Key(b,x,y));
	}
	private static void coinPlacement(Board b, int x, int y) {
		b.initiateEntity( y, x , new Coin(b,x,y));
	}
}
