package DataBase;

import java.util.ArrayList;

public class TableTemplate {
    private ArrayList<String> template;
    private String key;

    public TableTemplate setPrimaryKey()
    {
        key = "";

        return this;
    }

    public TableTemplate addIntField()
    {

        return this;
    }

    public TableTemplate addStringField()
    {

        return this;
    }
}
