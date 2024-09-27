package tech.ada.localizada.service.rental;

import tech.ada.localizada.model.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

public class RentalService {
    // private double pricePerDay;
    private double getPricePerDay(Vehicle vehicle) {
        if (vehicle instanceof VehicleCar) {
            return 150.0;
        } else {
            return 0;
        }
    }

    private Rental createRental(Vehicle vehicle, Client client, LocalDateTime start, LocalDateTime finish, Company companyWithdrawal, Company companyReturn) {

        long days = (Duration.between(start, finish).toDays()); // achar a diferenca em dias e arredonda

        double totalPrice = getPricePerDay(vehicle) * days;
        double discount = totalPrice * getDiscountRate(client, days);

        double finalPrice = totalPrice - discount;
        return new Rental(new Random().nextLong(), client, companyWithdrawal, companyReturn, vehicle, start, finish, new Invoice(finalPrice, totalPrice, discount));
    }

    private double getDiscountRate(Client client, long days) {
        if (days > 5 && client instanceof NaturalPerson) {
            return 0.05;
        }
        if (days > 3 && client instanceof LegalEntity) {
            return 0.1;
        }
        return 0.0;
    }


}
