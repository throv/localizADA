package tech.ada.localizada.repository.vehicle;

import tech.ada.localizada.model.Vehicle;
import tech.ada.localizada.repository.Repository;
import tech.ada.localizada.repository.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleRepositoryImpl extends RepositoryImpl
        <Vehicle, Integer> implements Repository <Vehicle, Integer> {

    private final List<Vehicle> vehiclesDb = new ArrayList<>();

    @Override
    protected Integer getId(Vehicle entity) {
        return entity.getId();
    }

    @Override
    public Vehicle save(Vehicle entity) {
        return super.save(entity);
    }

    @Override
    public Optional<Vehicle> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public List<Vehicle> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    public Optional<Vehicle> findByPlate(String plate) {
        return vehiclesDb.stream()
                .filter(vehicle -> vehicle.getPlate().equalsIgnoreCase(plate))
                .findFirst();
    }

}
