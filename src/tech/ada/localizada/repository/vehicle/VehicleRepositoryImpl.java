package tech.ada.localizada.repository.vehicle;

import tech.ada.localizada.model.Company;
import tech.ada.localizada.model.Vehicle;
import tech.ada.localizada.repository.Repository;
import tech.ada.localizada.repository.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleRepositoryImpl extends RepositoryImpl
        <Vehicle, Integer> implements Repository <Vehicle, Integer> , VehicleRepository {

    public final List<Vehicle> vehiclesDb = new ArrayList<>();

    @Override
    protected List<Vehicle> getList() {
        return vehiclesDb;
    }

    @Override
    protected Integer getId(Vehicle entity) {
        return entity.getId();
    }

    public Vehicle findVehicleByPlate(String plate) {
        return vehiclesDb.stream()
                .filter(vehicle -> vehicle.getPlate().equalsIgnoreCase(plate))
                .findFirst()
                .orElse(null); // Retorna null se não encontrar o veículo
    }

    @Override
    public Optional<Vehicle> findByPlate(String plate) {
        return Optional.empty();
    }

    @Override
    public List<Vehicle> findByCompany(Company company) {
        return List.of();
    }

}
