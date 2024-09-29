package tech.ada.localizada.service.client;

import tech.ada.localizada.exception.ClientAlreadyExistsException;
import tech.ada.localizada.exception.ClientNotFoundException;
import tech.ada.localizada.exception.DocumentNotAcceptedException;
import tech.ada.localizada.model.Client;
import tech.ada.localizada.repository.client.ClientRepository;

import java.util.List;
import java.util.Optional;


public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {

        this.clientRepository = clientRepository;
    }

    private void validateClient(Client client) {

        if (client.getName() == null || client.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }

        String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (client.getEmail() == null || !client.getEmail().matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        validateDocument(client.getId());
    }

    private void validateDocument(String document) {

        if (!document.matches("\\d{11}") && !document.matches("\\d{14}")) {
            throw new DocumentNotAcceptedException("Invalid document format. Must be 11 digits for CPF or 14 digits for CNPJ.");
        }
    }


    @Override
    public Client createClient(Client client) {

        validateClient(client);

        if (clientRepository.findById(client.getId()).isPresent()) {
            throw new ClientAlreadyExistsException("Client with ID " + client.getId() + " already exists.");
        }
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(String id, Client client) {

        validateClient(client);

        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isEmpty()) {
            throw new ClientNotFoundException("Client with ID " + client.getId() + " was not found.");
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
            throw new ClientNotFoundException("Client with ID " + id + " was not found.");
        }


        clientRepository.delete(id);
    }

    @Override
    public Client getClientById(String id) {
        return clientRepository
                .findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with ID " + id + "could not be found. No resources deleted."));
    }

    @Override
    public List<Client> listClients() {
        return clientRepository.findAll();
    }


}
