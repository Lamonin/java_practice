package FileIO;

import java.io.Serial;
import java.io.Serializable;

public class Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 0;
    private final String name;
    private final String surname;
    private final String birthDate;
    private final String address;

    public Employee() {
        this.name = "Иван";
        this.surname = "Иванов";
        this.birthDate = "22.12.2000";
        this.address = "Пушкина, 10";
    }

    public Employee(String name, String surname, String birthDate, String address) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.address = address;
    }

    public String getFullInfo()
    {
        var out = new StringBuilder();

        out.append("\tСотрудник:\t");
        out.append(name);
        out.append(" ");
        out.append(surname);
        out.append("\n");
        out.append("Дата рождения:\t");
        out.append(birthDate);
        out.append("\n");
        out.append("\t\tАдрес:\t");
        out.append(address);

        return out.toString();
    }
}
