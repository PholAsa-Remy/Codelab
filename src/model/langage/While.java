
package src.model.langage;

import java.util.*;
import src.model.world.*;
/**
 *
 */
public class While extends ControlFlowStatement {

	private int limit = 0;

    public While(Personage personage) {
    	super(personage);
    }


	public int run() {

		class FinWhile extends Action{

			public FinWhile(Personage personage){
				super(personage);
				FinWhile end = new FinWhile(personage);
				actions.add(end);		//add the condition for the verification
			}

			public int run(){
				if(condition.isTrue()){
					limit +=1;					//limit for the infinite loop
					return 0;
				}
				return -1;						//condition is not verify
			}
		}

		int verification = actions.peek().run();
		while(verification == 0) {				//do the no count actions.
			actions.offer(actions.poll());		//add this action in the end of the actions list
			verification = actions.peek().run();
		}

		if(limit>100){
				System.out.println("Boucle infini");
				System.exit(0);
			}

		if(verification == 1){
			actions.offer(actions.poll());
			return 2;					//when the list isn't over
		}

		return 1;						//when the list of actions is over
	}
}
