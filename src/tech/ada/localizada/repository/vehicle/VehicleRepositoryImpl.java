package tech.ada.localizada.repository.vehicle;

import tech.ada.localizada.model.Vehicle;
import tech.ada.localizada.repository.Repository;
import tech.ada.localizada.repository.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleRepositoryImpl extends RepositoryImpl
        <Vehicle, Integer> implements Repository <Vehicle, Integer> {

    public final List<Vehicle> vehiclesDb = new ArrayList<>();

    @Override
    protected List<Vehicle> getList() {
        return vehiclesDb;
    }

    @Override
    protected Integer getId(Vehicle entity) {
        return entity.getId();
    }

    public Optional<Vehicle> findByPlate(String plate) {
        return vehiclesDb.stream()
                .filter(vehicle -> vehicle.getPlate().equalsIgnoreCase(plate))
                .findFirst();
    }

    public Optional<Integer> findIdByPlate (String plate) {
        return vehiclesDb.stream()
                .filter(vehicle -> vehicle.getPlate().equalsIgnoreCase(plate))
                .map(Vehicle::getId)
                .findFirst();
    }


}
