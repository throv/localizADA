package tech.ada.localizada;

import tech.ada.localizada.model.Client;
import tech.ada.localizada.model.Company;
import tech.ada.localizada.repository.client.ClientRepository;
import tech.ada.localizada.repository.client.ClientRepositoryImpl;
import tech.ada.localizada.repository.company.CompanyRepository;
import tech.ada.localizada.repository.company.CompanyRepositoryImpl;
import tech.ada.localizada.service.client.ClientService;
import tech.ada.localizada.service.client.ClientServiceImpl;
import tech.ada.localizada.service.company.CompanyService;
import tech.ada.localizada.service.company.CompanyServiceImpl;

public class CarRentalApp {
    public static void main(String[] args) {

        ClientRepository clientRepository = new ClientRepositoryImpl();
        ClientService clientService = new ClientServiceImpl(clientRepository);
        Client newClient = new Client("Pedro", "pedro@teste.com");
        clientService.saveClient(newClient);

        CompanyRepository companyRepository = new CompanyRepositoryImpl();
        CompanyService companyService = new CompanyServiceImpl(companyRepository);
        Company company1 = new Company("AdaTech", "Avenida dos Abacates", "Macapá", "823199123992");
        companyService.addCompany(company1);

        Company company2 = new Company("Santander", "Avenida dos Ovos", "Fortaleza", "823199123994");
        companyService.addCompany(company2);

    }
}
