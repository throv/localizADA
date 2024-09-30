package tech.ada.localizada.service.vehicle;
import tech.ada.localizada.model.Company;
import tech.ada.localizada.model.Vehicle;

import java.util.List;

public interface VehicleService {

    Vehicle saveVehicle(Vehicle vehicle);

    void deleteVehicle(int id);

    Vehicle getVehicleById(int id);

    Vehicle getVehicleByPlate(String plate);

    List<Vehicle> listVehicle();
    List<Vehicle> listVehicleByCompany(Company company);

    void updateVehicleRented(Vehicle vehicle, boolean isRented);

    void updateVehicleVehicleCompany(Vehicle vehicle, Company company);

    void updateVehiclePlate(Vehicle vehicle, String newPlate);

    void updateVehicleYear(Vehicle vehicle, int newYear);

    void updateModelVehicle(Vehicle vehicle, String model);

<<<<<<< Updated upstream
    void printVehicles();

=======
    void updateVehiclePlate(String vehiclePlate);
>>>>>>> Stashed changes
}