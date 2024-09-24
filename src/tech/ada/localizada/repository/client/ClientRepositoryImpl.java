package tech.ada.localizada.repository.client;

import tech.ada.localizada.model.Client;
import tech.ada.localizada.repository.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl extends RepositoryImpl<Client, Integer> implements ClientRepository {

    private final List<Client> clientDb = new ArrayList<>();
    private int id = 1;

    @Override
    public Client save(Client client) {
        if(client.getId() != 0) {

            Optional<Client> existingClient = findById(client.getId());
            if(existingClient.isPresent()) {
                clientDb.remove(existingClient.get());
            }

        } else {
            client.setId(id++);
        }

        clientDb.add(client);
        return client;
    }

    @Override
    protected Integer getId(Client entity) {
        return 0;
    }

    @Override
    public List<Client> findByName(String name) {
        return List.of();
    }
}
