import DataBase.DataBaseController;
import DataBase.TableController;
import DataBase.TableField;
import DataBase.TableTemplate;
import FileIO.Employee;
import FileIO.EmployeIO;
import Flight.*;
import ExceptionContainer.*;
import LogMessage.*;
import Philosophy.Table;
import Trails.Trail;
import Other.FIOBirthDateAndPlace;

import javax.swing.*;
import java.awt.*;

class Demos {
    // Task 5 - 1
    public static void FlightDemo() {
        Trip trip = new Trip();

        trip.addFlight(new Flight(new Time(11, 30), new Time(12, 15)));
        trip.addFlight(new Flight(new Time(13, 15), new Time(15, 45)));
        trip.addFlight(new Flight(new Time(16, 0), new Time(18, 45)));
        trip.addFlight(new Flight(new Time(22, 15), new Time(23, 0)));

        System.out.println(trip.getDuration());
        System.out.println(trip.getShortestLayover());
    }

    // Task 2 - 3
    public static void TrailsDemo() {
        Trail trail = new Trail(new int[] {10, 20, 30});
        System.out.println(trail.isLevelTrailSegment(0, 1));
        System.out.println(trail.isDifficult());
    }

    // Task 3 - 2
    public static void LogMessageDemo() {
        SystemLog systemLog = new SystemLog();

        systemLog.addMessage(new LogMessage("Webserver:disk offline"));
        systemLog.addMessage(new LogMessage("MY PC:Disk not selected!"));
        systemLog.addMessage(new LogMessage("Webserver:disk online"));
        systemLog.addMessage(new LogMessage("MY PC:Disk was damaged!"));

        for (LogMessage m : systemLog.getMessageList())
        {
            System.out.println(m.getMessage());
        }

        System.out.println();
        for (LogMessage rm : systemLog.removeMessages("disk"))
        {
            System.out.println(rm.getMessage());
        }

        System.out.println();
        for (LogMessage m : systemLog.getMessageList())
        {
            System.out.println(m.getMessage());
        }
    }

    // Task: фамилия, имя, отчество, дата, год и место рождения,
    public static void RegexDemo() {
        System.out.println(FIOBirthDateAndPlace.isCorrect("Евгенов Евгений 29.12.1991 Ahhzz"));
        System.out.println(FIOBirthDateAndPlace.isCorrect("Евгенов Евгений 29.12.1991 Большой Камень"));
        System.out.println(FIOBirthDateAndPlace.isCorrect("Евгенов Евгений 29.12.1991 Ростов-на-Дону"));
        System.out.println(FIOBirthDateAndPlace.isCorrect("Евгенов Евгений 29.12.1991 Ростов-на-дОну"));
        System.out.println();
        System.out.println(FIOBirthDateAndPlace.isCorrect("Ефремов Евгений ЕвгеньевичЕвгеньевич 30.12.1990 Новосибирск"));
        System.out.println(FIOBirthDateAndPlace.isCorrect("Евгенов Евгений Евгеньевич 30.12.0 Новосибирск"));
        System.out.println(FIOBirthDateAndPlace.isCorrect("Тинкофь Антон Олегович 29.02.2004 Благовещенск"));
        System.out.println(FIOBirthDateAndPlace.isCorrect("Тинкофь Антон Олегович 30.02.2004 Благовещенск"));
        System.out.println(FIOBirthDateAndPlace.isCorrect("V D C 29/12/2002 О"));
    }

    public static void ExceptionDemo() {
        ExceptionContainer<Integer> container = new ExceptionContainer<Integer>();

        try {
            for (int i = 0; i < 10; i++)
            {
                container.push(i * i);
            }
            System.out.println("Now container size is " + container.size());
            container.push(1);
        }
        catch (ContainerFullException e) {
            System.out.println(e.getMessage());
        }

        container.clear();
        try {
            container.pop();
        }
        catch (EmptyContainerException e) {
            System.out.println(e.getMessage());
        }

        container.clear();
        try {
            container.contains(10);
        }
        catch (EmptyContainerException e) {
            System.out.println(e.getMessage());
        }

        //Code without exceptions
        container.clear();
        container.push(101);
        container.push(23);
        container.push(11);
        container.push(12);
        container.push(14);

        System.out.println(container.pop());
        System.out.println(container.pop());
        System.out.println(container.contains(11));
        System.out.println(container.pop());
        System.out.println(container.contains(11));
        container.pop();
        System.out.println(container.pop());
        System.out.println("Final size: " + container.size());
    }

    public static void FileIODemo() {
        Employee employee = new Employee("Джон", "Сильвер", "10.10.1960", "Остров сокровищ");

        System.out.println(employee.getFullInfo());
        EmployeIO.Write(employee,"input.txt");

        System.out.println();
        System.out.println(EmployeIO.Read("input.txt").getFullInfo());
        System.out.println();
        System.out.println(EmployeIO.Read("input_2.txt").getFullInfo());
    }

    public static void PhilosophersDemo() {
        JFrame frame = new JFrame("Стол с философами");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 480);

        var label = new JTextArea();
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setMargin(new Insets(10, 10, 10, 10));

        frame.getContentPane().add(label, BorderLayout.NORTH);
        frame.setVisible(true);

        var table = new Table();
        do{
            label.setText(table.getPhilosophersInfo());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        } while (true);
    }

    public static void JDBCDemo()
    {
        var dbc = new DataBaseController();
        dbc.setUser("javapractice", "javapracticepassword")
           .connect("jdbc:postgresql://localhost/javapractice");

        var tt = new TableTemplate();
        tt.setPrimaryKey("Id").stringField("Name", 30).intField("Age");

        var tc = new TableController(dbc, tt);
        tc.setTableName("users")
                .clearTable()
                .addValue("Biba", "27")
                .addValue("Boba", "30");

        System.out.println(tc.setTableName("users").tableAsString());

        // dbc.createTable("managers", tt).addValue("Bob", 100).addValue("Jon", 40);
    }
}
