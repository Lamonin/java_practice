package DataBase;

public record TableField(String name, String type) {

    public String getSqlField()
    {
        return name + " " + type;
    }
}
