package DataBase;

import java.sql.*;

public class TableController {
    private final DataBaseController dbc;
    private final TableTemplate template;

    private String tableName;

    public TableController(DataBaseController dbc, TableTemplate template) {
        this.dbc = dbc;
        this.template = template;
    }

    public TableController setTableName(String tableName)
    {
        this.tableName = tableName;
        if (!dbc.tableExist(tableName))
        {
            throw new RuntimeException("Table with name [" + tableName + "] does not exist!");
        }
        return this;
    }

    public TableController addValue(Object... params)
    {
        try {
            String builder = "INSERT INTO "
                    + tableName
                    + template.getColumns()
                    + " VALUES "
                    + "(" + getParamsAsSqlValues(params) + ");";

             dbc.getConnection().prepareStatement(dbc.getConnection().nativeSQL(builder)).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    private String getParamsAsSqlValues(Object[] params)
    {
        var out = new StringBuilder();
        for (int i = 0; i < params.length; i++)
        {
            switch (params[i].getClass().getName()) {
                case "java.lang.String" -> out.append("'").append(params[i]).append("'");
                case "java.lang.Integer" -> out.append(params[i]);
                default -> throw new RuntimeException("Unknown variable type!");
            }

            if (i != params.length - 1) {
                out.append(", ");
            }
        }
        return out.toString();
    }

    public void removeValue(int keyId)
    {
        try {
            dbc.getConnection().prepareStatement("DELETE FROM " + tableName + " WHERE " + template.getKey().name() + "=" + keyId).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String tableAsString()
    {
        var out = new StringBuilder();
        try {
            var rs = dbc.getConnection().createStatement().executeQuery("SELECT * FROM " + tableName);

            var columnsNumber = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for(int i = 1 ; i <= columnsNumber; i++) {
                    out.append(rs.getString(i)).append(" ");
                }
                out.append("\n");
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return out.toString();
    }

    public TableController clearTable()
    {
        try {
            dbc.getConnection().prepareStatement("DELETE FROM " + tableName + ";").execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return this;
    }
}
