package model;

import java.time.LocalDate;

public class Rental {
    private String rentalId;
    private Car car;
    private Customer customer;
    private LocalDate rentalDate;

    public Rental(String rentalId, Car car, Customer customer) {
        this.rentalId = rentalId;
        this.car = car;
        this.customer = customer;
        this.rentalDate = LocalDate.now();
    }

    public String getRentalId() { return rentalId; }
    public Car getCar() { return car; }
    public Customer getCustomer() { return customer; }
    public LocalDate getRentalDate() { return rentalDate; }

    @Override
    public String toString() {
        return rentalId + " | " + customer.getName() + " rented " + car.getBrand() + " " + car.getModel() + " on " + rentalDate;
    }
}
