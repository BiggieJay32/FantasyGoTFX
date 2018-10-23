

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by JordanMichael on 7/11/2017.
 */
public class Character
{
    private final SimpleIntegerProperty nameId;
    private final SimpleStringProperty characterName;

    public Character()
    {
        this.nameId = new SimpleIntegerProperty(0);
        this.characterName = new SimpleStringProperty("");
    }

    public Character(Integer id, String cName)
    {
        this.nameId = new SimpleIntegerProperty(id);
        this.characterName = new SimpleStringProperty(cName);
    }

    public Integer getNameId(){
        return nameId.get();
    }
    public void setNameId(Integer id){
        nameId.set(id);
    }

    public String getCharacterName(){
        return characterName.get();
    }
    public void setCharacterName(String cName){
        characterName.set(cName);
    }
}
