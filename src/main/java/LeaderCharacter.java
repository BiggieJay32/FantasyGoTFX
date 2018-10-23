

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by JordanMichael on 7/18/2017.
 */
public class LeaderCharacter
{
    public final SimpleIntegerProperty nameId;
    public final SimpleStringProperty characterName;
    public final SimpleIntegerProperty characterScore;

    public LeaderCharacter(){
        this.nameId = new SimpleIntegerProperty(0);
        this.characterName = new SimpleStringProperty("");
        this.characterScore = new SimpleIntegerProperty(0);

    }

    public LeaderCharacter(Integer id, String cName, Integer cScore)
    {
        this.nameId = new SimpleIntegerProperty(id);
        this.characterName = new SimpleStringProperty(cName);
        this.characterScore = new SimpleIntegerProperty(cScore);
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

    public Integer getCharacterScore(){ return characterScore.get();}
    public void setCharacterScore(Integer cScore){characterScore.set(cScore);}
}
