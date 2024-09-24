package tech.ada.localizada.repository.vehicle;

import tech.ada.localizada.model.Vehicle;
import tech.ada.localizada.repository.Repository;
import tech.ada.localizada.repository.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepositoryImpl extends RepositoryImpl
        <Vehicle, Integer> implements Repository <Vehicle, Integer> {

    private final List<Vehicle> vehiclesDb = new ArrayList<>();

    @Override
    protected Integer getId(Vehicle entity) {
        return 0;
    }
    
}
