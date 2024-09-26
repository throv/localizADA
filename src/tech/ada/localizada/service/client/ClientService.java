package tech.ada.localizada.service.client;

import tech.ada.localizada.model.Client;

import java.util.List;

public interface ClientService {

    Client createClient(Client client);
    Client updateClient(Client client);
    void deleteClient(String id);
    Client getClientById(String id);
    List<Client> listClients();

}
