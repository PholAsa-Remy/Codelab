
package src.view.langage;

import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JLabel;

import src.controller.ControllerLanguage;

public class DeletePanel extends JButton implements IMouseReactive {

    public DeletePanel(ControllerLanguage controller) {
        add(new JLabel("Delete"));
        addMouseListener(controller);
    }

    @Override
    public void paintComponent(Graphics g) {
        // TODO Auto-generated method stub

    }

    public String getDestType() {
        return "deletePanel";
    }
    
}