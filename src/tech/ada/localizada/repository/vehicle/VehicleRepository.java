package tech.ada.localizada.repository.vehicle;

import tech.ada.localizada.model.Vehicle;
import tech.ada.localizada.repository.Repository;

import java.util.Optional;

public interface VehicleRepository extends Repository <Vehicle, Integer> {

    Optional<Vehicle> findByPlate(String plate);

}
