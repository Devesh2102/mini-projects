public class Manager extends Employee {
    private double bonus;

    public Manager(int employeeId, String name, int age, String gender, String department, double basicSalary, double bonus) {
        super(employeeId, name, age, gender, department, basicSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return basicSalary + bonus;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Bonus: " + bonus);
    }

    @Override
    public String toFileString() {
        return "Manager," + super.toFileString() + "," + bonus;
    }
    @Override
    public String toDataString() {
        return super.toDataString() + "," + bonus;
    }


    public static Manager fromFileString(String line) {
        String[] parts = line.split(",");
        return new Manager(
            Integer.parseInt(parts[1]),
            parts[2],
            Integer.parseInt(parts[3]),
            parts[4],
            parts[5],
            Double.parseDouble(parts[6]),
            Double.parseDouble(parts[7])
        );
    }
}
