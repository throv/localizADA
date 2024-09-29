package tech.ada.localizada.service.vehicle;

import tech.ada.localizada.model.Company;
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

    @Override
    public void updateModelVehicle(Vehicle vehicle, String model) {
        vehicle.setModel(model);
    }

    @Override
    public void updateVehicleYear(Vehicle vehicle, int newYear) {
        vehicle.setYear(newYear);
    }

    @Override
    public void updateVehiclePlate(Vehicle vehicle, String newPlate) {
        vehicle.setPlate(newPlate);
    }

    @Override
    public void updateVehicleVehicleCompany(Vehicle vehicle, Company company) {
        vehicle.setFindVehicleAgency(company);
    }

    @Override
    public void updateVehicleRented(Vehicle vehicle, boolean isRented) {
        vehicle.setVehicleRented(isRented);
    }

    @Override
    public void printVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        for (Vehicle vehicle : vehicles) {
            System.out.print(vehicle);
        };
    }

}
