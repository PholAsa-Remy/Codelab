
package src.view.langage;

import src.controller.ControllerLanguage;
import src.model.langage.*;

import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * 
 */
public class EditPanel extends JPanel implements IActionPanelListable {

    /**
     * 
     */
    protected ActionPanel head;
    protected ControllerLanguage controller;

    /**
     * Default constructor
     */
    public EditPanel(ControllerLanguage controller) {
        this.controller = controller;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        head = new ActionPanel( controller, new Move(null) );
        head.setParentPanel(this);
        add(head);
    }   


    /**
     * 
     */
    public void addActionPanel(ActionPanel ap, ActionPanel previous) {

        int indexAp = getIndexInPane(ap, head);
        int indexPrevious = getIndexInPane(previous, head);
        if (indexAp != -1 && indexAp <= indexPrevious)
            return;

        if (ap.getParentPanel() != null) 
            ap.getParentPanel().removeActionPanel(ap);

        updateNext(ap, previous);
        addRecursively(ap, this, this);
    }

    /**
     * 
     */
    public void removeActionPanel(ActionPanel ap) {
        if (ap.getInstruction() == null)
            return;

        ActionPanel previous = getPrevious(ap, head);
        if (previous != null)
            previous.next = null;

        removeRecursively(ap, this);
    }

    public String getListType() {
        return "editPanelList";
    }

    /**
     * @return
     */
    public ArrayList<Instruction> convertToInstructions() {
        // TODO implement here
        return null;
    }

}
