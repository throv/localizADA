package tech.ada.localizada.service.vehicle;

import tech.ada.localizada.model.Client;
import tech.ada.localizada.model.Vehicle;

import java.util.List;

public interface VehicleService {

    Vehicle saveVehicle(Vehicle vehicle);
    void deleteVehicle(int id);
    Vehicle getVehicleById(int id);
    List<Vehicle> listVehicle();

}
