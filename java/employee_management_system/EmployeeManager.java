import java.util.*;

public class EmployeeManager {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee emp) {
        if (isDuplicateId(emp.getEmployeeId())) {
            System.out.println("Error: Employee ID " + emp.getEmployeeId() + " already exists. Cannot add duplicate.");
        } else {
            employees.add(emp);
        }
    }

    public boolean isDuplicateId(int id) {
        for (Employee emp : employees) {
            if (emp.getEmployeeId() == id) return true;
        }
        return false;
    }

    public void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            for (Employee emp : employees) {
                emp.displayDetails();
                System.out.println("-------------------");
            }
        }
    }

    public Employee searchById(int id) {
        for (Employee emp : employees) {
            if (emp.getEmployeeId() == id) return emp;
        }
        return null;
    }

    public void deleteById(int id) {
        Iterator<Employee> iterator = employees.iterator();
        boolean removed = false;
        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            if (emp.getEmployeeId() == id) {
                iterator.remove();
                System.out.println("Employee with ID " + id + " deleted.");
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Employee ID " + id + " not found.");
        }
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }
}
