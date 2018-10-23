

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/home.fxml"));
        primaryStage.setTitle("GoT");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();

//        File file = new File("gotDB.sqlite");
//        if (!file.exists()) {
//            InputStream link = (getClass().getResourceAsStream("gotDB.sqlite"));
//            Files.copy(link, file.getAbsoluteFile().toPath());
//        }
    }

    public static void main(String[] args){

        launch(args);
    }
}
