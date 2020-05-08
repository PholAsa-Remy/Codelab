
package src.view.langage;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.controller.ControllerLanguage;
import src.model.langage.*;

public class NotPanel extends ConditionPanel implements IConditionPanelAdjustable {

    ConditionPanel conditionPanel;

    JLabel notLabel;

    public NotPanel(ControllerLanguage controller, Not not) {
        super(controller, not); 
        
        setLayout(null);
        notLabel = new JLabel("Not");
        notLabel.setBackground(color);
        notLabel.setBounds(0, 0, 64, 32);
        add(notLabel);

        conditionPanel = ControlFlowStatementPanel.createEmptyConditionPanel(this, controller);
        add(conditionPanel);
    }

    public ConditionPanel getConditionPanel() {
        return conditionPanel;
    }

    public void setConditionPanel(ConditionPanel cp) {
        conditionPanel = cp;
    }

    @Override
    public Instruction toInstruction() {

        Not not = (Not) instruction;
        if (conditionPanel.getCondition() == null)
            return null;
        
        not.setCondition(conditionPanel.getCondition());
        return not;
    }

    public InstructionPanel createNewInstructionPanel(ControllerLanguage controller, Instruction instruction) {
        return new NotPanel(controller, (Not)instruction);
    }

    public void setPosition(int x, int y, int w) {
        conditionPanel.setPosition(64, 0, w - 64);
        super.setPosition(x, y, w);
    }
}