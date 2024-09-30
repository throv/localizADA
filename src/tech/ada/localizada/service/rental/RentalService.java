package tech.ada.localizada.service.rental;

import tech.ada.localizada.model.*;
import tech.ada.localizada.repository.rental.RentalRepository;
import tech.ada.localizada.repository.rental.RentalRepositoryImpl;
import tech.ada.localizada.service.company.CompanyService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


public class RentalService {
    protected RentalRepository repository;

    public RentalService() {
        repository = new RentalRepositoryImpl();
    }

    private double getPricePerDay(Vehicle vehicle) {
        if (vehicle instanceof VehicleCar) {
            return 150.0;
        }
        if (vehicle instanceof VehicleBike) {
            return 100.0;
        } else {
            return 200.0;
        }
    }

    public Rental createRental(Vehicle vehicle, Client client, LocalDateTime start, LocalDateTime finish, Company companyWithdrawal, Company companyReturn, PaymentMethod paymentMethod) {

        long days = (Duration.between(start, finish).toDays());

        double totalPrice = getPricePerDay(vehicle) * days;
        double discount = totalPrice * getDiscountRate(client, days);

        double finalPrice = totalPrice - discount;
        Rental rental = new Rental(new Random().nextLong(), client, companyWithdrawal, companyReturn, vehicle, start, finish, new Invoice(finalPrice, totalPrice, discount, paymentMethod));
        return repository.save(rental);
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

    public void devolucaoVeiculo(Client client, String plate) {
        List<Rental> rentals = repository.findByClient(client);
        Rental rental = rentals.stream().filter(r -> r.getVehicle().getPlate().equals(plate)).findFirst().get();



        if (vehicle.isVehicleRented()) {
            System.out.println("O veículo " + vehicle.getModel() + " foi devolvido com sucesso.");

        }

        // veiculo retornar para agencia ( lista )
    }

    public List<Rental> findByClient(Client client) {
        return repository.findByClient(client);
    }


}
