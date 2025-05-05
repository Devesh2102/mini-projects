import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();
        FileManager.loadFromFile(manager);

        Scanner sc = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Add Manager");
            System.out.println("3. View All Employees");
            System.out.println("4. Search Employee by ID");
            System.out.println("5. Delete Employee");
            System.out.println("6. Save and Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                sc.next();
                continue;
            }


            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id1 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Employee's Name: ");
                    String name1 = sc.nextLine();;
                    System.out.print("Enter Employee's Age: ");
                    int age1 = sc.nextInt();
                    System.out.print("Enter Employee's Gender: ");
                    String gender1 = sc.next();
                    System.out.print("Enter Employee's Department: ");
                    String dept1 = sc.next();
                    System.out.print("Enter Employee's Basic Salary: ");
                    double salary1 = sc.nextDouble();
                    manager.addEmployee(new Employee(id1, name1, age1, gender1, dept1, salary1));
		    System.out.println("Employee added successfully. Need to save data permanently, choose save option.");
                    break;

                case 2:
                    System.out.print("Enter Manager's ID: ");
                    int id2 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Manager's Name: ");
                    String name2 = sc.nextLine();
                    System.out.print("Enter Manager's Age: ");
                    int age2 = sc.nextInt();
                    System.out.print("Enter Manager's Gender: ");
                    String gender2 = sc.next();
                    System.out.print("Enter Manager's Department: ");
                    String dept2 = sc.next();
                    System.out.print("Enter Manager's Salary: ");
                    double salary2 = sc.nextDouble();
                    System.out.print("Enter Manager's Bonus: ");
                    double bonus = sc.nextDouble();
                    manager.addEmployee(new Manager(id2, name2, age2, gender2, dept2, salary2, bonus));
		    System.out.println("Manager added successfully. Need to save data permanently, choose save option.");
                    break;

                case 3:
                    manager.viewEmployees();
                    break;

                case 4:
                    System.out.print("Enter Employee ID to search: ");
                    int id4 = sc.nextInt();
                    Employee emp = manager.searchById(id4);
                    if (emp != null) emp.displayDetails();
                    else System.out.println("Employee not found.");
                    break;

                case 5:
                    System.out.print("Enter Employee ID to delete: ");
                    int id5 = sc.nextInt();
                    manager.deleteById(id5);
                    break;

                case 6:
                    FileManager.saveToFile(manager.getAllEmployees());
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);
	  sc.close();
    }
}
