

import java.sql.*;

/**
 * Created by JordanMichael on 7/11/2017.
 */
public class SQLiteConnection
{
    public static Connection Connector()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite::resource:DataBase/gotDB.sqlite");
            return conn;
        }catch (Exception e){
            return null;
        }
    }
}
