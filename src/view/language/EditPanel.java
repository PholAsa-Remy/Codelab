
package src.view.language;

import src.controller.ControllerLanguage;
import src.model.language.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Panel containing the user's InstructionPanel
 * of his code
 */
public class EditPanel extends JPanel implements IActionPanelListable {

    protected ActionPanel head;
    protected ControllerLanguage controller;

    private int margey = 16;
    public static final int margeleft = 3;
    public static final int width = 300;
    public static final int height = 600;

    public EditPanel(ControllerLanguage controller) {
        this.controller = controller;

        setLayout(null);        

        head = new BeginPanel( controller, new Begin(null) );
        head.setParentPanel(this);
        head.setPosition(margeleft, margey);
        add(head);
    }   

    public void addActionPanel(ActionPanel ap, ActionPanel previous) {
        if (ap.getInstruction() == null)
            return;

        add(ap);
        ap.setParentPanel(this);
        ap.next = previous.next;
        previous.next = ap;
        ap.setWidth(InstructionPanel.standardWidth);

        updatePlacement();
    }

    public InstructionPanel removeActionPanel(ActionPanel ap) {
        if (ap.getInstruction() == null)
            return null;

        ActionPanel previous = getPrevious(ap, head);
        previous.next = ap.next;
        ap.next = null;
        remove(ap);

        updatePlacement();        

        return previous;
    }

    public void updatePlacement() {        
        ActionPanel cur = head.next;
        int y = margey + head.getHeight();
        while (cur != null) {
            cur.setPosition(margeleft, y);
            y += cur.getHeight();
            cur = cur.next;
        }            
        
        if (y > this.getPreferredSize().getHeight())
            setPreferredSize(new Dimension(this.getWidth(), y));
        else if (y > EditPanel.height)
            setPreferredSize(new Dimension(this.getWidth(), y));
        else 
            setPreferredSize(new Dimension(this.getWidth(), EditPanel.height));
            
    }

    public String getListType() {
        return "editPanelList";
    }

    public ActionPanel getHead(ActionPanel ap) {
        return head;
    }

}
