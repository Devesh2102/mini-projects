import java.io.*;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME = "employees.txt";

    public static void saveToFile(List<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Employee emp : employees) {
                writer.write(emp.toDataString());
                writer.newLine();
            }
            System.out.println("All employee data saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error while saving to file: " + e.getMessage());
        }
    }

    public static void loadFromFile(EmployeeManager manager) {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    // Manager
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String gender = parts[3];
                    String dept = parts[4];
                    double salary = Double.parseDouble(parts[5]);
                    double bonus = Double.parseDouble(parts[6]);
                    manager.addEmployee(new Manager(id, name, age, gender, dept, salary, bonus));
                } else if (parts.length == 6) {
                    // Regular Employee
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String gender = parts[3];
                    String dept = parts[4];
                    double salary = Double.parseDouble(parts[5]);
                    manager.addEmployee(new Employee(id, name, age, gender, dept, salary));
                }
            }
            System.out.println("Employee data loaded from file.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error while loading from file: " + e.getMessage());
        }
    }
}
