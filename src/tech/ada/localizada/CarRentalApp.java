package tech.ada.localizada;

import tech.ada.localizada.model.Client;
import tech.ada.localizada.model.Company;
import tech.ada.localizada.model.Vehicle;
import tech.ada.localizada.model.VehicleCar;
import tech.ada.localizada.repository.client.ClientRepository;
import tech.ada.localizada.repository.client.ClientRepositoryImpl;
import tech.ada.localizada.repository.company.CompanyRepository;
import tech.ada.localizada.repository.company.CompanyRepositoryImpl;
import tech.ada.localizada.repository.vehicle.VehicleRepositoryImpl;
import tech.ada.localizada.service.client.ClientService;
import tech.ada.localizada.service.client.ClientServiceImpl;
import tech.ada.localizada.service.company.CompanyService;
import tech.ada.localizada.service.company.CompanyServiceImpl;

public class CarRentalApp {
    public static void main(String[] args) {

        /*ClientRepository clientRepository = new ClientRepositoryImpl();
        ClientService clientService = new ClientServiceImpl(clientRepository);
        Client newClient = new Client("Pedro", "pedro@teste.com");
        clientService.saveClient(newClient);

        CompanyRepository companyRepository = new CompanyRepositoryImpl();
        CompanyService companyService = new CompanyServiceImpl(companyRepository);
        Company company1 = new Company("AdaTech", "Avenida dos Abacates", "Macapá", "823199123992");
        companyService.addCompany(company1);

        Company company2 = new Company("Santander", "Avenida dos Ovos", "Fortaleza", "823199123994");
        companyService.addCompany(company2);*/

        VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();

        Vehicle vehicle1 = new VehicleCar(1, "ABC1234", 2000, "Fiesta",4);
        Vehicle vehicle2 = new VehicleCar(2, "XYZ5678", 2000, "Onix",4);
        Vehicle vehicle3 = new VehicleCar(3, "LMN3456", 2000, "Corolla",2);

        vehicleRepository.save(vehicle1);
        vehicleRepository.save(vehicle2);
        vehicleRepository.save(vehicle3);

        System.out.println(vehicleRepository.findAll());

        vehicleRepository.delete(1);

        System.out.println(vehicleRepository.findAll());

        System.out.println("------");

        for (int i = 0; i < vehicleRepository.vehiclesDb.size(); i ++ ) {
            System.out.println("Numero de portas");
            System.out.println(vehicleRepository.vehiclesDb.get(i));
        }

    }
}
