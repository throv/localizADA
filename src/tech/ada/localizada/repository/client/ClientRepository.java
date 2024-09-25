package tech.ada.localizada.repository.client;

import tech.ada.localizada.model.Client;
import tech.ada.localizada.repository.Repository;

import java.util.List;

public interface ClientRepository extends Repository<Client, String> {


    List<Client> findByName(String name);

}
