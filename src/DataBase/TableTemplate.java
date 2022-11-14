package DataBase;

import java.util.ArrayList;

public class TableTemplate {
    private final ArrayList<TableField> fields;
    private TableField key;

    public TableTemplate() {
        fields = new ArrayList<>();
    }

    public TableTemplate setPrimaryKey(String keyName)
    {
        if (key != null)
        {
            throw new RuntimeException("Attempt to change table existing key!");
        }
        key = new TableField(keyName, "SERIAL PRIMARY KEY");

        return this;
    }

    public TableTemplate intField(String fieldName)
    {
        fields.add(new TableField(fieldName, "INTEGER"));
        return this;
    }

    public TableTemplate stringField(String fieldName, int maxCharacters)
    {
        fields.add(new TableField(fieldName, "CHARACTER VARYING("+maxCharacters+")"));
        return this;
    }

    public TableTemplate customField(String fieldName, String type)
    {
        fields.add(new TableField(fieldName, type));
        return this;
    }

    public TableField getKey() { return key; }

    public int getColumnsCount() { return fields.size(); }

    public String getColumns()
    {
        var out = new StringBuilder();
        out.append("(");
        for (int i = 0; i < fields.size(); i++)
        {
            out.append(fields.get(i).name());

            if (i != fields.size() - 1) {
                out.append(", ");
            }
        }
        out.append(")");
        return out.toString();
    }

    public String getColumnsWithType()
    {
        var out = new StringBuilder();
        out.append("(");
        out.append(key.getSqlField());
        for (TableField field : fields) {
            out.append(", ");
            out.append(field.getSqlField());
        }
        out.append(")");
        return out.toString();
    }
}
