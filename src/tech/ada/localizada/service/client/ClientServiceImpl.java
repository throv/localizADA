package tech.ada.localizada.service.client;

import tech.ada.localizada.model.Client;
import tech.ada.localizada.repository.client.ClientRepository;

import java.util.List;
import java.util.Optional;


public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {

        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {

        if (clientRepository.findById(client.getId()).isPresent()) {
            throw new IllegalArgumentException("Client with ID " + client.getId() + " already exists.");
        }
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(String id, Client client) {

        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isEmpty()) {
            throw new IllegalArgumentException("Client with ID " + client.getId() + " was not found.");
        }

        Client existingClient = optionalClient.get();

        existingClient.setName(client.getName());
        existingClient.setEmail(client.getEmail());
        existingClient.setDocument(client.getId());


        return clientRepository.save(existingClient);
    }

    @Override
    public void deleteClient(String id) {


        if (clientRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Client with ID " + id + " was not found.");
        }


        clientRepository.delete(id);
    }

    @Override
    public Client getClientById(String id) {
        return clientRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client with ID " + id + "could not be found. No resources deleted."));
    }

    @Override
    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    @Override
    public void printClients() {
        List<Client> clients = clientRepository.findAll();
        for (Client client : clients) {
            System.out.print(client);
        }
    }

}
