package service;

import model.*;

public interface CarService {
    void addCar(Car car);
    void viewAvailableCars();
    void rentCar(String carId, Customer customer);
    void viewAllRentals();
}
