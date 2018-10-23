

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by JordanMichael on 7/18/2017.
 */
public class Player
{
    public final SimpleIntegerProperty playerID;
    public final SimpleStringProperty playerName;
    public final SimpleIntegerProperty playerScore;

    Player()
    {
        this.playerID = new SimpleIntegerProperty(0);
        this.playerName = new SimpleStringProperty("");
        this.playerScore = new SimpleIntegerProperty(0);
    }

    Player(Integer pID, String pName, Integer pScore)
    {
        this.playerID = new SimpleIntegerProperty(pID);
        this.playerName = new SimpleStringProperty(pName);
        this.playerScore = new SimpleIntegerProperty(pScore);
    }

    public Integer getPlayerID(){ return playerID.get();}
    public void setPlayerID(Integer pID){ playerID.set(pID);}

    public String getPlayerName(){ return playerName.get();}
    public void setPlayerName(String pName){ playerName.set(pName);}

    public Integer getPlayerScore(){ return playerScore.get();}
    public void setPlayerScore(Integer pScore){ playerScore.set(pScore);}

}
