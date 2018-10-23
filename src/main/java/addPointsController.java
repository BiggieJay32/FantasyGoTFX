

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by JordanMichael on 7/11/2017.
 */
public class addPointsController implements Initializable
{
    private int point;
    private int totalPoint;
    @FXML private Label isConnected;
    @FXML private TableView<Rule> ruleTable;
    @FXML private TableColumn<Rule, Integer> numRuleCol;
    @FXML private TableColumn<Rule, String> ruleCol;
    @FXML private TableView<Character> characterTable;
    @FXML private TableColumn<Character, Integer> numCharacterCol;
    @FXML private TableColumn<Character, String> characterCol;
    @FXML Label ruleNum;
    @FXML Label charNum;
    @FXML ImageView charImg = new ImageView();
    @FXML ImageView playerImg = new ImageView();

    public GoTModel gotModel = new GoTModel();

    final ObservableList<Rule> ruleData = FXCollections.observableArrayList(
            new Rule(1, "Knee Slapper"), new Rule(2, "The Jester"), new Rule(3, "You Can't Handle the Truth"),
            new Rule(4, "The Serpent"), new Rule(5, "Top Gun"), new Rule(6, "King of the North"),
            new Rule(7, "Dearly Beloved"), new Rule(8, "Prima Nocta"), new Rule(9, "The Sparrows"),
            new Rule(10, "Upper Management"), new Rule(11, "The Trelawney"), new Rule(12, "The Committee"),
            new Rule(13, "Marauders"), new Rule(14, "Bewitched"), new Rule(15, "Sultan of Swat"),
            new Rule(16, "The Lazarus"), new Rule(17, "The Comeback"), new Rule(18, "Lower Management"),
            new Rule(19, "Dismembered"), new Rule(20, "The Prince and the Pauper"), new Rule(21, "Mr. Steal Your Girl"),
            new Rule(22, "The Twat"), new Rule(23, "Unamed"), new Rule(24, "Named"),
            new Rule(25, "Drafted"), new Rule(26, "Winter is Coming"), new Rule(27, "Betrayal"),
            new Rule(28, "Head of the House"), new Rule(29, "Kingslayer"), new Rule(30, "The Iron Throne"),
            new Rule(31, "Unknown Beast"), new Rule(32, "Flaming Dragon"), new Rule(33, "Knock Out Unknown"),
            new Rule(34, "Knock Out Drafted"), new Rule(35, "Death"), new Rule(36, "Memorable Death"),
            new Rule(37, "How You Doing?"), new Rule(38, "Bold Move"), new Rule(39, "King's Cup"),
            new Rule(40, "A Night of Flowers"), new Rule(41, "A Knight of Flowers"), new Rule(42, "Slumpbuster"),
            new Rule(43, "The Fornicator"), new Rule(44, "Peeping Tom"), new Rule(45, "Booty/Nip Slip"),
            new Rule(46, "Full Monty"), new Rule(47, "Plan B")
    );

    final ObservableList<Character> characterData = FXCollections.observableArrayList(
            new Character(1, "Bran Stark"), new Character(2, "Sansa Stark"), new Character(3, "Arya Stark"),
            new Character(4, "Benjen Stark"), new Character(5, "Jon Snow"), new Character(6, "Alys Karstark"),
            new Character(7, "Edmure Tully"), new Character(8, "Gendry"), new Character(9, "Theon Greyjoy"),
            new Character(10, "Yara Greyjoy"), new Character(11, "Euron Greyjoy"), new Character(12, "Meera Reed"),
            new Character(13, "Beric Dondarrion"), new Character(14, "Thoros of Myr"), new Character(15, "Brienne of Tarth"),
            new Character(16, "Bronn"), new Character(17, "Jamie Lannister"), new Character(18, "Cersei Lannister"),
            new Character(19, "Tyrion Lannister"), new Character(20, "Kevan Lannister"), new Character(21, "Qyburn"),
            new Character(22, "Randyll Tarly"), new Character(23, "Dickon Tarly"), new Character(24, "Samwell Tarly"),
            new Character(25, "Gilly"), new Character(26, "Edison Tollett"), new Character(27, "Ser Davos Seaworth"),
            new Character(28, "Melisandre"), new Character(29, "Gregor Clegane"), new Character(30, "Sandor Clegane"),
            new Character(31, "Tormund Giantsbane"), new Character(32, "Obara Sand"), new Character(33, "Nymeria Sand"),
            new Character(34, "Ellaria Sand"), new Character(35, "Tyene Sand"), new Character(36, "Olenna Tyrell"),
            new Character(37, "Maester Wolkan"), new Character(38, "Robett Glover"), new Character(39, "Yohn Royce"),
            new Character(40, "Petyr Baelish"), new Character(41, "Robin Arryn"), new Character(42, "Grey Worm"),
            new Character(43, "Missandei"), new Character(44, "Lord Varys"), new Character(45, "Daenerys Targaryen"),
            new Character(46, "Daario Naharis"), new Character(47, "Jorah Mormont"), new Character(48, "Lyanna Mormont"),
            new Character(49, "Podrick Payne"), new Character(50, "Harrag"), new Character(51, "Jaquen H'agar"),
            new Character(52, "Kinvara"), new Character(53, "Koner"), new Character(54, "Marei"),
            new Character(55, "Qhono"), new Character(56, "The Night King"), new Character(57, "Tycho Nestoris"),
            new Character(58, "Marwyn the Mage"), new Character(59, "Ghost"), new Character(60, "Nymeria"),
            new Character(61, "Raeghar"), new Character(62, "Viserion"), new Character(63, "Drogon")
    );

    public void handleSubmitButton(ActionEvent event) throws SQLException
    {
        System.out.println("Rule Number: " + ruleNum.getText() + " Character Number: " + charNum.getText());

        int val, cellData, rule, champ, totalCellData, playerPoints;

        rule = Integer.parseInt(ruleNum.getText());
        champ = Integer.parseInt((charNum.getText()));
        val = addPoints(rule);

        //Selecting the amount of current points per character
        String sql = "SELECT Points FROM CharacterList WHERE ID = ?";
        PreparedStatement pstmt = gotModel.connection.prepareStatement(sql);
        pstmt.setInt(1, champ);
        ResultSet rs = pstmt.executeQuery();

        cellData = rs.getInt(1);
        System.out.println("Current points: " + cellData);
        totalCellData = val + cellData;
        System.out.println("Points after update: " + totalCellData);
        rs.close();
        pstmt.close();

        //Updating the points of the character
        String sql2 = "UPDATE CharacterList SET Points = ? WHERE  ID = ?";
        PreparedStatement pstmt2 = gotModel.connection.prepareStatement(sql2);
        pstmt2.setInt(1, totalCellData);
        pstmt2.setInt(2, champ);
        pstmt2.executeUpdate();
        pstmt2.close();

        String sql3 = "SELECT Points FROM PointSystem WHERE ID = ?";
        PreparedStatement pstmt3 = gotModel.connection.prepareStatement(sql3);

        String sql4 = "UPDATE PointSystem SET Points = ? WHERE ID = ?";
        PreparedStatement pstmt4 = gotModel.connection.prepareStatement(sql4);

        //Tanner's team
        if (champ == 5 || champ == 10 || champ == 16 || champ == 35 || champ == 43 || champ == 57 || champ == 6){
            System.out.println("Tanner's team, character number selected: " + champ);
            int player = 1;
            int totalVal;

            pstmt3.setInt(1, player);
            ResultSet rs2 = pstmt3.executeQuery();
            playerPoints = rs2.getInt(1);
            totalVal = point + cellData;
            pstmt4.setInt(1, totalVal);
            pstmt4.setInt(2, player);
            pstmt4.executeUpdate();
            System.out.println("Players current total points: " + playerPoints);
        }
        //Shane's team
        else if (champ == 45 || champ == 11 || champ == 42 || champ == 50 || champ == 8 || champ == 55 || champ == 51){
            System.out.println("Shane's team, character number selected: " + champ);
            int player = 2;
            int totalVal;

            pstmt3.setInt(1, player);
            ResultSet rs2 = pstmt3.executeQuery();
            playerPoints = rs2.getInt(1);
            totalVal = point + cellData;
            pstmt4.setInt(1, totalVal);
            pstmt4.setInt(2, player);
            pstmt4.executeUpdate();
            System.out.println("Players current total points: " + playerPoints);
        }
        //Ryder's team
        else if (champ == 30 || champ == 15 || champ == 29 || champ == 4 || champ == 36 || champ == 48 || champ == 38){
            System.out.println("Ryder's team, character number selected: " + champ);
            int player = 3;
            int totalVal;

            pstmt3.setInt(1, player);
            ResultSet rs2 = pstmt3.executeQuery();
            playerPoints = rs2.getInt(1);
            totalVal = point + cellData;
            pstmt4.setInt(1, totalVal);
            pstmt4.setInt(2, player);
            pstmt4.executeUpdate();
            System.out.println("Players current total points: " + playerPoints);
        }
        //Mitch's team
        else if (champ == 18 || champ == 27 || champ == 47 || champ == 52 || champ == 21 || champ == 41 || champ == 39){
            System.out.println("Mitch's team, character number selected: " + champ);
            int player = 4;
            int totalVal;

            pstmt3.setInt(1, player);
            ResultSet rs2 = pstmt3.executeQuery();
            playerPoints = rs2.getInt(1);
            totalVal = point + cellData;
            pstmt4.setInt(1, totalVal);
            pstmt4.setInt(2, player);
            pstmt4.executeUpdate();
            System.out.println("Players current total points: " + playerPoints);
        }
        //Ian's team
        else if (champ == 19 || champ == 3 || champ == 28 || champ == 62 || champ == 7 || champ ==25 || champ == 53){
            System.out.println("Ian's team, character number selected: " + champ);
            int player = 5;
            int totalVal;

            pstmt3.setInt(1, player);
            ResultSet rs2 = pstmt3.executeQuery();
            playerPoints = rs2.getInt(1);
            totalVal = point + cellData;
            pstmt4.setInt(1, totalVal);
            pstmt4.setInt(2, player);
            pstmt4.executeUpdate();
            System.out.println("Players current total points: " + playerPoints);
        }
        //Jacob's team
        else if (champ == 63 || champ == 31 || champ == 46 || champ == 32 || champ == 12 || champ == 20 || champ == 22){
            System.out.println("Jacob's team, character number selected: " + champ);
            int player = 6;
            int totalVal;

            pstmt3.setInt(1, player);
            ResultSet rs2 = pstmt3.executeQuery();
            playerPoints = rs2.getInt(1);
            totalVal = point + cellData;
            pstmt4.setInt(1, totalVal);
            pstmt4.setInt(2, player);
            pstmt4.executeUpdate();
            System.out.println("Players current total points: " + playerPoints);
        }
        //Jordan's team
        else if (champ == 1 || champ == 9 || champ == 49 || champ == 24 || champ == 33 || champ == 26 || champ == 23){
            System.out.println("Jordan's team, character number selected: " + champ);
            int player = 7;
            int totalVal;

            pstmt3.setInt(1, player);
            ResultSet rs2 = pstmt3.executeQuery();
            playerPoints = rs2.getInt(1);
            totalVal = point + cellData;
            pstmt4.setInt(1, totalVal);
            pstmt4.setInt(2, player);
            pstmt4.executeUpdate();
            System.out.println("Players current total points: " + playerPoints);
        }
        //Amber/Josh's team
        else if (champ == 17 || champ == 2 || champ == 13 || champ == 61 || champ == 14 || champ == 59 || champ == 54){
            System.out.println("Amber/Josh's team, character number selected: " + champ);
            int player = 8;
            int totalVal;

            pstmt3.setInt(1, player);
            ResultSet rs2 = pstmt3.executeQuery();
            playerPoints = rs2.getInt(1);
            totalVal = point + cellData;
            pstmt4.setInt(1, totalVal);
            pstmt4.setInt(2, player);
            pstmt4.executeUpdate();
            System.out.println("Players current total points: " + playerPoints);
        }
        //Casey's team
        else if (champ == 40 || champ == 56 || champ == 34 || champ == 44 || champ == 60 || champ == 58 || champ == 37){
            System.out.println("Casey's team, character number selected: " + champ);
            int player = 9;
            int totalVal;

            pstmt3.setInt(1, player);
            ResultSet rs2 = pstmt3.executeQuery();
            playerPoints = rs2.getInt(1);
            totalVal = point + cellData;
            pstmt4.setInt(1, totalVal);
            pstmt4.setInt(2, player);
            pstmt4.executeUpdate();
            System.out.println("Players current total points: " + playerPoints);
        }
    }

    public int addPoints(int num){
        switch (num){
            case 1: point = 10;
                    break;
            case 2: point = 15;
                break;
            case 3: point = 20;
                break;
            case 4: point = 10;
                break;
            case 5: point = 10;
                break;
            case 6: point = 15;
                break;
            case 7: point = 10;
                break;
            case 8: point = 15;
                break;
            case 9: point = 15;
                break;
            case 10: point = 20;
                break;
            case 11: point = 20;
                break;
            case 12: point = 25;
                break;
            case 13: point = 25;
                break;
            case 14: point = 25;
                break;
            case 15: point = 30;
                break;
            case 16: point = 50;
                break;
            case 17: point = 100;
                break;
            case 18: point = -20;
                break;
            case 19: point = -10;
                break;
            case 20: point = -25;
                break;
            case 21: point = -15;
                break;
            case 22: point = 5;
                break;
            case 23: point = 10;
                break;
            case 24: point = 15;
                break;
            case 25: point = 25;
                break;
            case 26: point = 20;
                break;
            case 27: point = 30;
                break;
            case 28: point = 35;
                break;
            case 29: point = 50;
                break;
            case 30: point = 75;
                break;
            case 31: point = 50;
                break;
            case 32: point = 100;
                break;
            case 33: point = 5;
                break;
            case 34: point = 10;
                break;
            case 35: point = 25;
                break;
            case 36: point = 50;
                break;
            case 37: point = 5;
                break;
            case 38: point = 5;
                break;
            case 39: point = 10;
                break;
            case 40: point = 10;
                break;
            case 41: point = 20;
                break;
            case 42: point = 25;
                break;
            case 43: point = 25;
                break;
            case 44: point = 10;
                break;
            case 45: point = 10;
                break;
            case 46: point = 25;
                break;
            case 47: point = 25;
                break;
        }
        System.out.println("Points selected: " + point);
        return point;
    }

    public int getPicture(int num2){
        switch (num2){

            case 1: Image img1 = new Image(getClass().getResource("/Images/bran.jpg").toExternalForm());
                    charImg.setImage(img1);
                break;
            case 2: Image img2 = new Image(getClass().getResource("/Images/sansa.jpg").toExternalForm());
                charImg.setImage(img2);
                break;
            case 3: Image img3 = new Image(getClass().getResource("/Images/arya.jpg").toExternalForm());
                charImg.setImage(img3);
                break;
            case 4: Image img4 = new Image(getClass().getResource("/Images/benjen.png").toExternalForm());
                charImg.setImage(img4);
                break;
            case 5: Image img5 = new Image(getClass().getResource("/Images/jon.jpg").toExternalForm());
                charImg.setImage(img5);
                break;
            case 6: Image img6 = new Image(getClass().getResource("/Images/alys.jpg").toExternalForm());
                charImg.setImage(img6);
                break;
            case 7: Image img7 = new Image(getClass().getResource("/Images/edmure.jpg").toExternalForm());
                charImg.setImage(img7);
                break;
            case 8: Image img8 = new Image(getClass().getResource("/Images/gendry.jpg").toExternalForm());
                charImg.setImage(img8);
                break;
            case 9: Image img9 = new Image(getClass().getResource("/Images/theon.jpg").toExternalForm());
                charImg.setImage(img9);
                break;
            case 10: Image img10 = new Image(getClass().getResource("/Images/yara.jpg").toExternalForm());
                charImg.setImage(img10);
                break;
            case 11: Image img11 = new Image(getClass().getResource("/Images/euron.jpg").toExternalForm());
                charImg.setImage(img11);
                break;
            case 12: Image img12 = new Image(getClass().getResource("/Images/meera.png").toExternalForm());
                charImg.setImage(img12);
                break;
            case 13: Image img13 = new Image(getClass().getResource("/Images/beric.jpg").toExternalForm());
                charImg.setImage(img13);
                break;
            case 14: Image img14 = new Image(getClass().getResource("/Images/thoros.jpg").toExternalForm());
                charImg.setImage(img14);
                break;
            case 15: Image img15 = new Image(getClass().getResource("/Images/brienne.jpg").toExternalForm());
                charImg.setImage(img15);
                break;
            case 16: Image img16 = new Image(getClass().getResource("/Images/bron.png").toExternalForm());
                charImg.setImage(img16);
                break;
            case 17: Image img17 = new Image(getClass().getResource("/Images/jamie.jpg").toExternalForm());
                charImg.setImage(img17);
                break;
            case 18: Image img18 = new Image(getClass().getResource("/Images/cersei.jpg").toExternalForm());
                charImg.setImage(img18);
                break;
            case 19: Image img19 = new Image(getClass().getResource("/Images/tyrion.jpg").toExternalForm());
                charImg.setImage(img19);
                break;
            case 20: Image img20 = new Image(getClass().getResource("/Images/kevan.png").toExternalForm());
                charImg.setImage(img20);
                break;
            case 21: Image img21 = new Image(getClass().getResource("/Images/qyburn.png").toExternalForm());
                charImg.setImage(img21);
                break;
            case 22: Image img22 = new Image(getClass().getResource("/Images/randyll.jpg").toExternalForm());
                charImg.setImage(img22);
                break;
            case 23: Image img23 = new Image(getClass().getResource("/Images/dickon.png").toExternalForm());
                charImg.setImage(img23);
                break;
            case 24: Image img24 = new Image(getClass().getResource("/Images/samwell.jpg").toExternalForm());
                charImg.setImage(img24);
                break;
            case 25: Image img25 = new Image(getClass().getResource("/Images/gilly.jpg").toExternalForm());
                charImg.setImage(img25);
                break;
            case 26: Image img26 = new Image(getClass().getResource("/Images/edison.jpg").toExternalForm());
                charImg.setImage(img26);
                break;
            case 27: Image img27 = new Image(getClass().getResource("/Images/davos.jpg").toExternalForm());
                charImg.setImage(img27);
                break;
            case 28: Image img28 = new Image(getClass().getResource("/Images/melisandre.jpg").toExternalForm());
                charImg.setImage(img28);
                break;
            case 29: Image img29 = new Image(getClass().getResource("/Images/mountain.jpg").toExternalForm());
                charImg.setImage(img29);
                break;
            case 30: Image img30 = new Image(getClass().getResource("/Images//hound.jpg").toExternalForm());
                charImg.setImage(img30);
                break;
            case 31: Image img31 = new Image(getClass().getResource("/Images/tormund.jpg").toExternalForm());
                charImg.setImage(img31);
                break;
            case 32: Image img32 = new Image(getClass().getResource("/Images/obara.png").toExternalForm());
                charImg.setImage(img32);
                break;
            case 33: Image img33 = new Image(getClass().getResource("/Images/nymeria.png").toExternalForm());
                charImg.setImage(img33);
                break;
            case 34: Image img34 = new Image(getClass().getResource("/Images/ellaria.png").toExternalForm());
                charImg.setImage(img34);
                break;
            case 35: Image img35 = new Image(getClass().getResource("/Images/tyene.png").toExternalForm());
                charImg.setImage(img35);
                break;
            case 36: Image img36 = new Image(getClass().getResource("/Images/olenna.jpg").toExternalForm());
                charImg.setImage(img36);
                break;
            case 37: Image img37 = new Image(getClass().getResource("/Images/maester.png").toExternalForm());
                charImg.setImage(img37);
                break;
            case 38: Image img38 = new Image(getClass().getResource("/Images/robett.png").toExternalForm());
                charImg.setImage(img38);
                break;
            case 39: Image img39 = new Image(getClass().getResource("/Images/yohn.png").toExternalForm());
                charImg.setImage(img39);
                break;
            case 40: Image img40 = new Image(getClass().getResource("/Images/little.jpg").toExternalForm());
                charImg.setImage(img40);
                break;
            case 41: Image img41 = new Image(getClass().getResource("/Images/robin.jpg").toExternalForm());
                charImg.setImage(img41);
                break;
            case 42: Image img42 = new Image(getClass().getResource("/Images/greyworm.png").toExternalForm());
                charImg.setImage(img42);
                break;
            case 43: Image img43 = new Image(getClass().getResource("/Images/missandei.jpg").toExternalForm());
                charImg.setImage(img43);
                break;
            case 44: Image img44 = new Image(getClass().getResource("/Images/varys.jpg").toExternalForm());
                charImg.setImage(img44);
                break;
            case 45: Image img45 = new Image(getClass().getResource("/Images/dt.jpg").toExternalForm());
                charImg.setImage(img45);
                break;
            case 46: Image img46 = new Image(getClass().getResource("/Images/daario.jpg").toExternalForm());
                charImg.setImage(img46);
                break;
            case 47: Image img47 = new Image(getClass().getResource("/Images/jorah.jpg").toExternalForm());
                charImg.setImage(img47);
                break;
            case 48: Image img48 = new Image(getClass().getResource("/Images/lyanna.png").toExternalForm());
                charImg.setImage(img48);
                break;
            case 49: Image img49 = new Image(getClass().getResource("/Images/podrick.jpg").toExternalForm());
                charImg.setImage(img49);
                break;
            case 50: Image img50 = new Image(getClass().getResource("/Images/harrag.png").toExternalForm());
                charImg.setImage(img50);
                break;
            case 51: Image img51 = new Image(getClass().getResource("/Images/jaquen.jpg").toExternalForm());
                charImg.setImage(img51);
                break;
            case 52: Image img52 = new Image(getClass().getResource("/Images/kinvara.jpg").toExternalForm());
                charImg.setImage(img52);
                break;
            case 53: Image img53 = new Image(getClass().getResource("/Images/koner.jpg").toExternalForm());
                charImg.setImage(img53);
                break;
            case 54: Image img54 = new Image(getClass().getResource("/Images/marei.jpg").toExternalForm());
                charImg.setImage(img54);
                break;
            case 55: Image img55 = new Image(getClass().getResource("/Images/quono.png").toExternalForm());
                charImg.setImage(img55);
                break;
            case 56: Image img56 = new Image(getClass().getResource("/Images/king.jpg").toExternalForm());
                charImg.setImage(img56);
                break;
            case 57: Image img57 = new Image(getClass().getResource("/Images/tycho.jpg").toExternalForm());
                charImg.setImage(img57);
                break;
            case 58: Image img58 = new Image(getClass().getResource("/Images/marwyn.jpg").toExternalForm());
                charImg.setImage(img58);
                break;
            case 59: Image img59 = new Image(getClass().getResource("/Images/ghost.jpg").toExternalForm());
                charImg.setImage(img59);
                break;
            case 60: Image img60 = new Image(getClass().getResource("/Images/ny.png").toExternalForm());
                charImg.setImage(img60);
                break;
            case 61: Image img61 = new Image(getClass().getResource("/Images/raeghar.jpg").toExternalForm());
                charImg.setImage(img61);
                break;
            case 62: Image img62 = new Image(getClass().getResource("/Images/viserion.jpg").toExternalForm());
                charImg.setImage(img62);
                break;
            case 63: Image img63 = new Image(getClass().getResource("/Images/drogon.jpg").toExternalForm());
                charImg.setImage(img63);
                break;
        }
        return num2;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        //int champ2 = Integer.parseInt((charNum.getText()));
        numRuleCol.setCellValueFactory(new PropertyValueFactory<>("ruleId"));
        ruleCol.setCellValueFactory(new PropertyValueFactory<>("ruleText"));
        ruleTable.setItems(ruleData);

        numCharacterCol.setCellValueFactory(new PropertyValueFactory<>("nameId"));
        characterCol.setCellValueFactory(new PropertyValueFactory<>("characterName"));
        characterTable.setItems(characterData);

        ruleTable.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                ruleNum.setText(String.valueOf(ruleTable.getSelectionModel().getSelectedItem().getRuleId()));
            }
        });

        characterTable.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                charNum.setText(String.valueOf(characterTable.getSelectionModel().getSelectedItem().getNameId()));

                getPicture(Integer.valueOf(charNum.getText()));

                if (Integer.parseInt(charNum.getText()) == 5 || Integer.parseInt(charNum.getText()) == 10
                        || Integer.parseInt(charNum.getText()) == 16 || Integer.parseInt(charNum.getText()) == 35
                        || Integer.parseInt(charNum.getText()) == 43 || Integer.parseInt(charNum.getText()) == 57
                        || Integer.parseInt(charNum.getText()) == 6) {
                    Image img = new Image(getClass().getResource("/Images/tanner.jpg").toExternalForm());
                    playerImg.setImage(img);
                }
                else if (Integer.parseInt(charNum.getText()) == 45 || Integer.parseInt(charNum.getText()) == 11
                        || Integer.parseInt(charNum.getText()) == 42 || Integer.parseInt(charNum.getText()) == 50
                        || Integer.parseInt(charNum.getText()) == 8 || Integer.parseInt(charNum.getText()) == 55
                        || Integer.parseInt(charNum.getText()) == 51) {
                    Image img = new Image(getClass().getResource("/Images/shane.jpg").toExternalForm());
                    playerImg.setImage(img);
                }
                else if (Integer.parseInt(charNum.getText()) == 30 || Integer.parseInt(charNum.getText()) == 15
                        || Integer.parseInt(charNum.getText()) == 29 || Integer.parseInt(charNum.getText()) == 4
                        || Integer.parseInt(charNum.getText()) == 36 || Integer.parseInt(charNum.getText()) == 48
                        || Integer.parseInt(charNum.getText()) == 38) {
                    Image img = new Image(getClass().getResource("/Images/ryder.jpg").toExternalForm());
                    playerImg.setImage(img);
                }
                else if (Integer.parseInt(charNum.getText()) == 18 || Integer.parseInt(charNum.getText()) == 27
                        || Integer.parseInt(charNum.getText()) == 47 || Integer.parseInt(charNum.getText()) == 52
                        || Integer.parseInt(charNum.getText()) == 21 || Integer.parseInt(charNum.getText()) == 41
                        || Integer.parseInt(charNum.getText()) == 39) {
                    Image img = new Image(getClass().getResource("/Images/mitch.jpg").toExternalForm());
                    playerImg.setImage(img);
                }
                else if (Integer.parseInt(charNum.getText()) == 19 || Integer.parseInt(charNum.getText()) == 3
                        || Integer.parseInt(charNum.getText()) == 28 || Integer.parseInt(charNum.getText()) == 62
                        || Integer.parseInt(charNum.getText()) == 7 || Integer.parseInt(charNum.getText()) == 25
                        || Integer.parseInt(charNum.getText()) == 53) {
                    Image img = new Image(getClass().getResource("/Images/ian.jpg").toExternalForm());
                    playerImg.setImage(img);
                }
                else if (Integer.parseInt(charNum.getText()) == 63 || Integer.parseInt(charNum.getText()) == 31
                        || Integer.parseInt(charNum.getText()) == 46 || Integer.parseInt(charNum.getText()) == 32
                        || Integer.parseInt(charNum.getText()) == 12 || Integer.parseInt(charNum.getText()) == 20
                        || Integer.parseInt(charNum.getText()) == 22) {
                    Image img = new Image(getClass().getResource("/Images/jake.jpg").toExternalForm());
                    playerImg.setImage(img);
                }
                else if (Integer.parseInt(charNum.getText()) == 1 || Integer.parseInt(charNum.getText()) == 9
                        || Integer.parseInt(charNum.getText()) == 49 || Integer.parseInt(charNum.getText()) == 24
                        || Integer.parseInt(charNum.getText()) == 33 || Integer.parseInt(charNum.getText()) == 26
                        || Integer.parseInt(charNum.getText()) == 23) {
                    Image img = new Image(getClass().getResource("/Images/jordan.jpg").toExternalForm());
                    playerImg.setImage(img);
                }
                else if (Integer.parseInt(charNum.getText()) == 17 || Integer.parseInt(charNum.getText()) == 2
                        || Integer.parseInt(charNum.getText()) == 13 || Integer.parseInt(charNum.getText()) == 61
                        || Integer.parseInt(charNum.getText()) == 14 || Integer.parseInt(charNum.getText()) == 59
                        || Integer.parseInt(charNum.getText()) == 54) {
                    Image img = new Image(getClass().getResource("/Images/josh.jpg").toExternalForm());
                    playerImg.setImage(img);
                }
                else if (Integer.parseInt(charNum.getText()) == 40 || Integer.parseInt(charNum.getText()) == 56
                        || Integer.parseInt(charNum.getText()) == 34 || Integer.parseInt(charNum.getText()) == 44
                        || Integer.parseInt(charNum.getText()) == 60 || Integer.parseInt(charNum.getText()) == 58
                        || Integer.parseInt(charNum.getText()) == 37) {
                    Image img = new Image(getClass().getResource("/Images/casey.jpg").toExternalForm());
                    playerImg.setImage(img);
                }
            }
        });

        if(gotModel.isDbConnected()){
            System.out.println("Connection to Database Successful");
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }
    }
}
