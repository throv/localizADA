package tech.ada.localizada;

import tech.ada.localizada.model.Client;
import tech.ada.localizada.repository.client.ClientRepository;
import tech.ada.localizada.repository.client.ClientRepositoryImpl;
import tech.ada.localizada.service.client.ClientService;
import tech.ada.localizada.service.client.ClientServiceImpl;

public class CarRentalApp {
    public static void main(String[] args) {

        ClientRepository clientRepository = new ClientRepositoryImpl();
        ClientService clientService = new ClientServiceImpl(clientRepository);
        Client newClient = new Client("Pedro", "pedro@teste.com");
        clientService.saveClient(newClient);
    }
}
