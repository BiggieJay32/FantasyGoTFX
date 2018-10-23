
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by JordanMichael on 7/12/2017.
 */
public class Rule
{
    private final SimpleIntegerProperty ruleId;
    private final SimpleStringProperty ruleText;

    public Rule(){
        this.ruleId = new SimpleIntegerProperty(0);
        this.ruleText = new SimpleStringProperty("");
    }

    public Rule(Integer rId, String rText)
    {
        this.ruleId = new SimpleIntegerProperty(rId);
        this.ruleText = new SimpleStringProperty(rText);
    }

    public Integer getRuleId(){
        return ruleId.get();
    }

    public void setRuleId(Integer rId){
        ruleId.set(rId);
    }

    public String getRuleText(){
        return ruleText.get();
    }

    public void setRuleText(String rText){
        ruleText.set(rText);
    }
}
