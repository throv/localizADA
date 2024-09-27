package tech.ada.localizada.repository.rental;

import tech.ada.localizada.model.Rental;
import tech.ada.localizada.repository.RepositoryImpl;

import java.util.List;

public class RentalRepository extends RepositoryImpl<Rental, String> {
    @Override
    protected List<Rental> getList() {
        return List.of();
    }

    @Override
    protected String getId(Rental entity) {
        return "";
    }
}
