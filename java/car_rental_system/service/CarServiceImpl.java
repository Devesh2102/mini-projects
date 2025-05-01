package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService {
    private List<Car> carList = new ArrayList<>();
    private List<Rental> rentalList = new ArrayList<>();

    @Override
    public void addCar(Car car) {
        carList.add(car);
        System.out.println("Car added successfully.");
    }

    @Override
    public void viewAvailableCars() {
        boolean found = false;
        for (Car car : carList) {
            if (car.isAvailable()) {
                System.out.println(car);
                found = true;
            }
        }
        if (!found) System.out.println("No available cars.");
    }

    @Override
    public void rentCar(String carId, Customer customer) {
        for (Car car : carList) {
            if (car.getId().equals(carId) && car.isAvailable()) {
                car.setAvailable(false);
                Rental rental = new Rental("RENT" + (rentalList.size() + 1), car, customer);
                rentalList.add(rental);
                System.out.println("Car rented successfully.");
                return;
            }
        }
        System.out.println("Car not available or doesn't exist.");
    }

    @Override
    public void viewAllRentals() {
        if (rentalList.isEmpty()) {
            System.out.println("No rentals yet.");
        } else {
            for (Rental r : rentalList) {
                System.out.println(r);
            }
        }
    }
}
