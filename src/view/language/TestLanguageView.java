
package src.view.language;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import src.controller.ControllerLanguage;
import src.model.language.*;


public class TestLanguageView {
    public static void run() {

        try {

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    frame.setSize(800, 800);

                    UserCode model = new UserCode();

                    ControllerLanguage controller = new ControllerLanguage(model);

                    LanguageView view = new LanguageView(controller, null);
                    frame.setContentPane(view);
                    controller.setView(view);

                    testInstructionPanelGeneratorClick(view, controller);
                    frame.revalidate();
                }
            });
        } catch (Exception e) {
        }

    }

    public static void testInstructionPanelGeneratorClick(LanguageView view, ControllerLanguage controller) {
        InstructionPanelGenerator ig1 = new InstructionPanelGenerator(new ControlFlowStatementPanel(controller, new If(null)));
        InstructionPanelGenerator ig2 = new InstructionPanelGenerator(new ControlFlowStatementPanel(controller, new While(null)));
        InstructionPanelGenerator ig3 = new InstructionPanelGenerator(new ActionPanel(controller, new Move(null)));
        InstructionPanelGenerator ig4 = new InstructionPanelGenerator(new ActionPanel(controller, new TurnLeft(null)));
        InstructionPanelGenerator ig5 = new InstructionPanelGenerator(new ActionPanel(controller, new TurnRight(null)));
        InstructionPanelGenerator ig6 = new InstructionPanelGenerator(new ConditionPanel(controller, new ObstacleFront(null)));
        InstructionPanelGenerator ig7 = new InstructionPanelGenerator(new ConditionPanel(controller, new CoinFront(null)));
        InstructionPanelGenerator ig8 = new InstructionPanelGenerator(new ConditionPanel(controller, new ChestFront(null)));
        InstructionPanelGenerator ig9 = new InstructionPanelGenerator(new NotPanel(controller, new Not(null)));
        InstructionPanelGenerator ig10 = new InstructionPanelGenerator(new IfElsePanel(controller, new IfElse(null)));
        InstructionPanelGenerator ig11 = new InstructionPanelGenerator(new ConditionPanel(controller, new ObstacleRight(null)));
        InstructionPanelGenerator ig12 = new InstructionPanelGenerator(new ConditionPanel(controller, new ObstacleLeft(null)));

        view.resourcePanel.addGenerator(ig3);
        view.resourcePanel.addGenerator(ig4);
        view.resourcePanel.addGenerator(ig5);
        
        view.resourcePanel.addGenerator(ig1);
        view.resourcePanel.addGenerator(ig2);
        view.resourcePanel.addGenerator(ig10);
        view.resourcePanel.addGenerator(ig9);
        view.resourcePanel.addGenerator(ig6);
        view.resourcePanel.addGenerator(ig11);
        view.resourcePanel.addGenerator(ig12);
        view.resourcePanel.addGenerator(ig7);
        view.resourcePanel.addGenerator(ig8);
        
    }

}
