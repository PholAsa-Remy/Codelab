package src.model.langage;

import java.util.LinkedList;
import java.util.Queue;

import src.model.world.Personage;

public class IfElse extends If {

    protected Queue<Action> elseActions;
    protected Queue<Action> ifActions;

    public IfElse(Personage personage) {
        super(personage);
        ifActions = actions;
        elseActions = new LinkedList<Action>();
        elseActions.add(new FinIfElse(personage));
    }

    protected void addFirstAction() {
        this.addAction(new FinIfElse(personage));
    }

    public void addElseActions(Queue<Action> q) {
        elseActions.addAll(q);
    }

    public Instruction createNewInstruction() {
        return new IfElse(personage);
    }

    public void setPersonage(Personage pers) {
        super.setPersonage(pers);
        for (Action a : elseActions) 
            a.setPersonage(pers);
    }
    
    class FinIfElse extends FinIf {

        public FinIfElse(Personage personage){
            super(personage);
        }

        public Instruction createNewInstruction() {
            return new FinIfElse(personage);
        }

        public int run(){
            actions = condition.isTrue() ? ifActions : elseActions;
            
            return 0;
        }
    }
}