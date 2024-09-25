package tech.ada.localizada.service.vehicle;

import tech.ada.localizada.model.Vehicle;
import tech.ada.localizada.repository.vehicle.VehicleRepository;

import java.util.List;
import java.util.Optional;

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
        this.vehicleRepository.delete(id);
    }

    @Override
    public Vehicle getVehicleById(int id) {
        Optional <Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.orElse(null);
    }

    @Override
    public List<Vehicle> listVehicle() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleByPlaca(String plate) {
        Optional<Vehicle> vehicle = vehicleRepository.findByPlate(plate);
        return vehicle.orElse(null);
    }



}
