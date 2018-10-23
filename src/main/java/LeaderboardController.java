

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by JordanMichael on 7/18/2017.
 */
public class LeaderboardController implements Initializable
{
    private ObservableList<LeaderCharacter> data;
    private ObservableList<Player> playerData;

    @FXML private TableView<LeaderCharacter> characterT;
    @FXML private TableColumn<LeaderCharacter, Integer> characterID;
    @FXML private TableColumn<LeaderCharacter, String> characterName;
    @FXML private TableColumn<LeaderCharacter, Integer> characterScore;

    @FXML private TableView<Player> playerT;
    @FXML private TableColumn<Player, Integer> playerID;
    @FXML private TableColumn<Player, String> playerName;
    @FXML private TableColumn<Player, Integer> playerScore;
    public GoTModel model = new GoTModel();


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        characterID.setCellValueFactory(new PropertyValueFactory<LeaderCharacter, Integer>("nameID"));
        characterName.setCellValueFactory(new PropertyValueFactory<LeaderCharacter, String>("characterName"));
        characterScore.setCellValueFactory(new PropertyValueFactory<LeaderCharacter, Integer>("characterScore"));

        playerID.setCellValueFactory(new PropertyValueFactory<Player, Integer>("playerID"));
        playerName.setCellValueFactory(new PropertyValueFactory<Player, String>("playerName"));
        playerScore.setCellValueFactory(new PropertyValueFactory<Player, Integer>("playerScore"));

        data = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM CharacterList";
            PreparedStatement pstmt = model.connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next())
            {
                LeaderCharacter cFill = new LeaderCharacter();
                cFill.nameId.set(rs.getInt(1));
                cFill.characterName.set(rs.getString("Character"));
                cFill.characterScore.set(rs.getInt(3));
                data.add(cFill);
            }
            characterT.setItems(data);
            rs.close();
            pstmt.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        playerData = FXCollections.observableArrayList();
        try{
            String sql2 = "SELECT * FROM PointSystem";
            PreparedStatement pstmt2 = model.connection.prepareStatement(sql2);
            ResultSet rs2 = pstmt2.executeQuery();

            while(rs2.next())
            {
                Player pFill = new Player();
                pFill.playerID.set(rs2.getInt(1));
                pFill.playerName.set(rs2.getString("Player"));
                pFill.playerScore.set(rs2.getInt(3));
                playerData.add(pFill);
            }
            playerT.setItems(playerData);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
