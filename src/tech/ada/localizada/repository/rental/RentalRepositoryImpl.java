package tech.ada.localizada.repository.rental;

import tech.ada.localizada.model.Client;
import tech.ada.localizada.model.Rental;
import tech.ada.localizada.repository.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class RentalRepositoryImpl extends RepositoryImpl<Rental, Long> implements RentalRepository {

    private ArrayList<Rental> rentals = new ArrayList<>();

    @Override
    protected List<Rental> getList() {
        return rentals;
    }

    @Override
    protected Long getId(Rental entity) {
        return entity.getId();
    }

    @Override
    public List<Rental> findByClient(Client client) {
        return rentals.stream().filter(rental -> rental.getClient().equals(client)).toList();
    }
}
