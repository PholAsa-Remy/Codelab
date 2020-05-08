
package src.view.langage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import src.controller.ControllerLanguage;
import src.model.langage.*;

/**
 * 
 */
public class InstructionPanelGenerator extends JPanel implements IMouseReactive {

	Instruction instruction;
	InstructionPanel instructionPanel;
	ControllerLanguage controller;

	public InstructionPanelGenerator(InstructionPanel instructionPanel) {
		this.instruction = instructionPanel.getInstruction();
		this.instructionPanel = instructionPanel;
		this.controller = instructionPanel.getController();
		addMouseListener(controller);
		addMouseMotionListener(controller);
		setMaximumSize(new Dimension(200, 32));
		
		add(new JLabel(instruction.getVersion()));
		setBackground(Color.GRAY);
	}

	public InstructionPanel createInstructionPanel() {
		return instructionPanel.createNewInstructionPanel(controller, instruction);
	}

	public InstructionPanel getSourcePanel() {
		return createInstructionPanel();
	}
}
