package tech.ada.localizada.service.client;

import tech.ada.localizada.model.Client;
import tech.ada.localizada.repository.client.ClientRepository;

import java.util.List;


public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {

        this.clientRepository = clientRepository;
    }

    @Override
    public Client saveClient(Client client) {

        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(int id) {

    }

    @Override
    public Client getClientById(int id) {
        return null;
    }

    @Override
    public List<Client> listClients() {
        return List.of();
    }

    //MÉTODOS CRUD


}
