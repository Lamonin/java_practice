package FileIO;

import java.io.*;

public final class EmployeIO {

    public static void Write(Employee employee, String path) {
        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(employee);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Employee Read(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Employee) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
