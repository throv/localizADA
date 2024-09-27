package tech.ada.localizada.repository.rental;

import tech.ada.localizada.model.Client;
import tech.ada.localizada.model.Rental;
import tech.ada.localizada.repository.Repository;

import java.util.List;

public interface RentalRepository extends Repository <Rental, Long> {
    List<Rental> findByClient(Client client);
}
