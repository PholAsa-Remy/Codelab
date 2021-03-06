
package src.model.language;

import src.model.world.*;
import java.util.*;
import org.json.*;

/**
 *
 */
public abstract class Instruction {

    abstract public String getType();
    
    public String getVersion() {
        String classname = this.getClass().getName();
        String[] array = classname.split("\\.");
        String version = array[array.length - 1].toLowerCase();
        return version;
    }
	
    protected Personage personage;
    
    public Instruction(Personage personage) {
    	this.personage = personage;
    }

    public abstract Instruction createNewInstruction();

    /**
     * @return the JSON representation of the instruction 
     */
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("type", getType());
        json.put("version", getVersion());
        
        return json;
    }

    public Personage getPersonage() {
        return personage;
    }

    public void setPersonage(Personage pers) {
        personage = pers;
    }

    /**
     * uniquement pour les tests
     */
    public void printTypeAndVersion() {
        System.out.println("type : " + getType() + "\nversion : " + getVersion());
    }
}
