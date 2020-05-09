
package src.model.langage;

import src.model.world.*;
/**
 *
 */
public class Stay extends Action {


    public Stay(Personage personage) {
    	super(personage);
    }

    public Instruction createNewInstruction() {
        return new Stay(personage);
    }

    /**
     * @return return the number of actions executed
     */
    public int run() {
        this.personage.stay();
		return 1;
	}

}