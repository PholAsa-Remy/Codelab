package src.story;

import src.model.langage.*;
import src.view.world.*;
import javax.swing.*;
import java.awt.*;
import org.json.simple.JSONObject;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StoryPanel extends JPanel{
	private int advancement;
	private static final int nbOfLevel = 3;
	private LevelPanel level;
	private String storyMessage;
	private JFrame parent;
	
	public StoryPanel (JFrame parent) {
		this.parent = parent;
		this.advancement = getAdvancement();
		if(advancement > nbOfLevel) {
			this.add(victoryPanel());
			setAdvancement(1);
		}else {
			System.out.println("new Level" + advancement);			
			this.level = new StoryLevel("story/" + advancement, this);
			this.level.setLevelFrame(parent);
			this.storyMessage = readJSON("story/" + advancement).get("story").toString();
			this.add(level);
			storyPopUp(storyMessage);
		}
	}
	
	private static int getAdvancement() {
		JSONObject save = readJSON ("story/sauvegarde");
		return Integer.parseInt(save.get("advancement").toString());
	}
	
	private static void setAdvancement(int advancement) {
		try {
			JSONObject save = readJSON ("story/sauvegarde");
			save.put("advancement",advancement);
			FileWriter file = new FileWriter("resources/story/sauvegarde.json");
			file.write(save.toString());
			file.flush();
		}catch(Exception e) {
			System.out.println("OOOF, la sauvegarde a disparu alors la c'est TRES GRAVE");
		}
	}
	
	private JPanel victoryPanel() {
		JPanel victory = new JPanel ();
		victory.add(new JLabel("Ouais cette page est moche mais tu as gagné !"));
		return victory;
	}

	public void nextLevel() {
		System.out.println(advancement);
		loadNextLevel(advancement+1);
		this.parent.dispose();
	}

	private static void loadNextLevel(int advancement) {
		JFrame windows = createWindows("Story");
		setAdvancement(advancement);
		windows.setContentPane(new StoryPanel(windows));
		windows.pack();
	}
	
	private static JFrame createWindows (String title) {
		JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        frame.setMinimumSize(new Dimension(1300,700));
        frame.setVisible(true);
        return frame;
	}

	private static void storyPopUp(String message){
		Object[] options = {"OK"};
		JOptionPane.showOptionDialog(null, message, "Histoire",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);
	}
	
	private static JSONObject readJSON (String name){
    	try {
    		JSONParser jsonParser = new JSONParser();
    		FileReader reader = new FileReader("resources/" + name + ".json");
    		Object obj = jsonParser.parse(reader);
    		JSONObject jsonLevel = (JSONObject) obj;
    		return jsonLevel;
    	}catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
}