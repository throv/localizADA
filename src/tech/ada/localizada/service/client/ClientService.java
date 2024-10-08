package tech.ada.localizada.service.client;

import tech.ada.localizada.model.Client;

import java.util.List;

public interface ClientService {

    Client createClient(Client client);
    Client updateClient(String id, Client client);
    void deleteClient(String id);
    Client getClientById(String id);
    void printClients();
    List<Client> searchClientByName(String name);

}
