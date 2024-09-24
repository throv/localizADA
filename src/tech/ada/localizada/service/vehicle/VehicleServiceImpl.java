package tech.ada.localizada.service.vehicle;

import tech.ada.localizada.model.Vehicle;
import tech.ada.localizada.repository.client.ClientRepository;
import tech.ada.localizada.repository.vehicle.VehicleRepository;

import java.util.List;

public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return this.vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteVehicle(int id) {
        return;
    }

    @Override
    public Vehicle getVehicleById(int id) {
        return null;
    }

    @Override
    public List<Vehicle> listVehicle() {
        return List.of();
    }
}
