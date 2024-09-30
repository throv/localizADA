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
        return clientDb;
    }


    @Override
    protected String getId(Client client) {
        return client.getId();
    }

    @Override
    public List<Client> findByName(String name) {

        List<Client> result = new ArrayList<>();
        for (Client client : clientDb) {
            if(client.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(client);
            }
        }

        return result;
    }
}
