
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by JordanMichael on 7/11/2017.
 */
public class GoTModel
{
    public Connection connection;

    public GoTModel(){
        connection = SQLiteConnection.Connector();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
    }

    public boolean isDbConnected()
    {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
