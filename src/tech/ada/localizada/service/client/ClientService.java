package tech.ada.localizada.service.client;

import tech.ada.localizada.model.Client;

import java.util.List;

public interface ClientService {

    Client saveClient(Client client);
    void deleteClient(int id);
    Client getClientById(int id);
    List<Client> listClients();

}
