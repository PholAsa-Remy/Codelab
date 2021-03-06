
package src.view.language;

import src.controller.ControllerLanguage;
import src.model.language.*;
import java.awt.Color;
import javax.swing.JLabel;


public class ConditionPanel extends InstructionPanel {

    public ConditionPanel(ControllerLanguage controller, Condition condition) {
        super(controller);

        if (condition != null)
            instruction = InstructionFactory.createInstruction(condition);      

        normalColor = Color.CYAN.darker(); 
        highlightColor = Color.CYAN;

        setBackground(normalColor);
        add(new JLabel(instruction != null ? instruction.toString() : "nothing"));        
    }

    public void setCondition(Condition condition) {
        instruction = condition;
    }

    public Condition getCondition() {
        return (Condition)instruction;
    }

    public InstructionPanel createNewInstructionPanel(ControllerLanguage controller, Instruction instruction) {
        return new ConditionPanel(controller, (Condition)instruction);
    }
    
    public boolean onRelease(InstructionPanel source) {

        if (!(source instanceof ConditionPanel))
            return false;

        if (source.getInstruction() == null)
            return false;

        if (getParentPanel() != null) {
            IConditionPanelAdjustable parent = (IConditionPanelAdjustable) getParentPanel();
            parent.addConditionPanel((ConditionPanel)source);
            source.dehighlight();
            return true;
        }

        return false;
    }

}
