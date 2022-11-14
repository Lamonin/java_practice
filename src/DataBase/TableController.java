package DataBase;

import java.sql.Connection;
import java.sql.SQLException;

public class TableController {
    private final DataBaseController dbc;
    private final String tableName;

    private String addSQLTemplate;

    public TableController(DataBaseController dbc, String tableName) {
        this.tableName = tableName;
        this.dbc = dbc;

        if (!dbc.tableExist(tableName))
        {
            throw new RuntimeException("Table with name [" + tableName + "] does not exist!");
        }
    }

    public void setTableTemplate()
    {

    }

    public void addValue(String... params)
    {
        try {
            String builder = "INSERT INTO "
                    + tableName
                    + ""
                    + " VALUES "
                    + "(" + String.join(", ", params) + ");";
            for (int i = 0; i < params.length; i++)
            {
            }
            // dbc.getConnection().prepareStatement(dbc.getConnection().nativeSQL("INSERT INTO users (Name, Age) VALUES ('Tom', 33), ('Ben', 32);")).execute();
            dbc.getConnection().prepareStatement(dbc.getConnection().nativeSQL(builder)).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void removeValue()
    {

    }

    public void getValue()
    {

    }
}
