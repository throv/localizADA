package tech.ada.localizada.repository.client;

import tech.ada.localizada.model.Client;
import tech.ada.localizada.repository.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl extends RepositoryImpl<Client, String> implements ClientRepository {

    private final List<Client> clientDb = new ArrayList<>();

    @Override
    protected List<Client> getList() {
        return List.of();
    }

    @Override
    public Client save(Client client) {

            Optional<Client> existingClient = findById(client.getId());
            if(existingClient.isPresent()) {
                clientDb.remove(existingClient.get());
            }

        clientDb.add(client);
        return client;
    }

    @Override
    protected String getId(Client client) {
        return client.getId();
    }

    @Override
    public List<Client> findByName(String name) {

        List<Client> result = new ArrayList<>();
        for (Client client : clientDb) {
            if(client.getName().contains(name)) {
                result.add(client);
            }
        }

        return result;
    }
}
