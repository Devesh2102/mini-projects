public class Employee extends Person {
    protected int employeeId;
    protected String department;
    protected double basicSalary;

    public Employee(int employeeId, String name, int age, String gender, String department, double basicSalary) {
        super(name, age, gender);
        this.employeeId = employeeId;
        this.department = department;
        this.basicSalary = basicSalary;
    }

    public double calculateSalary() {
        return basicSalary;
    }

    public void displayDetails() {
        System.out.println("ID: " + employeeId + ", Name: " + name + ", Age: " + age + ", Gender: " + gender +
                ", Department: " + department + ", Salary: " + calculateSalary());
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String toFileString() {
        return employeeId + "," + name + "," + age + "," + gender + "," + department + "," + basicSalary;
    }

    public String toDataString() {
        return employeeId + "," + name + "," + age + "," + gender + "," + department + "," + basicSalary;
    }


    public static Employee fromFileString(String line) {
        String[] parts = line.split(",");
        return new Employee(
            Integer.parseInt(parts[0]),
            parts[1],
            Integer.parseInt(parts[2]),
            parts[3],
            parts[4],
            Double.parseDouble(parts[5])
        );
    }
}
