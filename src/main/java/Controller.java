

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller
{
    @FXML
    public void handleAddButton(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("FXML/add.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void handleLeaderboardButton(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("FXML/leader.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}