import model.*;
import service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CarService service = new CarServiceImpl();

        while (true) {
            System.out.println("\n--- Car Rental System ---");
            System.out.println("1. Add Car");
            System.out.println("2. View Available Cars");
            System.out.println("3. Rent Car");
            System.out.println("4. View All Rentals");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Car ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Brand: ");
                    String brand = sc.nextLine();
                    System.out.print("Enter Model: ");
                    String model = sc.nextLine();
                    service.addCar(new Car(id, brand, model));
                    break;

                case 2:
                    service.viewAvailableCars();
                    break;

                case 3:
                    System.out.print("Enter Car ID to rent: ");
                    String carId = sc.nextLine();
                    System.out.print("Enter Customer ID: ");
                    String custId = sc.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String custName = sc.nextLine();
                    service.rentCar(carId, new Customer(custId, custName));
                    break;

                case 4:
                    service.viewAllRentals();
                    break;

                case 5:
                    System.out.println("Exiting system.");
                    System.exit(0);

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
