package tech.ada.localizada;

import tech.ada.localizada.model.*;
import tech.ada.localizada.repository.client.ClientRepository;
import tech.ada.localizada.repository.client.ClientRepositoryImpl;
import tech.ada.localizada.repository.company.CompanyRepository;
import tech.ada.localizada.repository.company.CompanyRepositoryImpl;
import tech.ada.localizada.repository.rental.RentalRepositoryImpl;
import tech.ada.localizada.repository.vehicle.VehicleRepositoryImpl;
import tech.ada.localizada.service.client.ClientService;
import tech.ada.localizada.service.client.ClientServiceImpl;
import tech.ada.localizada.service.company.CompanyService;
import tech.ada.localizada.service.company.CompanyServiceImpl;
import tech.ada.localizada.service.rental.RentalService;
import tech.ada.localizada.service.vehicle.VehicleService;
import tech.ada.localizada.service.vehicle.VehicleServiceImpl;
import tech.ada.localizada.view.ClientSubMenu;
import tech.ada.localizada.view.Menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class CarRentalApp {


    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();
        VehicleServiceImpl vehicleService = new VehicleServiceImpl(vehicleRepository);
        CompanyRepositoryImpl companyRepository = new CompanyRepositoryImpl();
        CompanyServiceImpl companyService = new CompanyServiceImpl(companyRepository);
        RentalRepositoryImpl rentalRepository = new RentalRepositoryImpl();
        RentalService rentalService = new RentalService(rentalRepository, companyRepository, vehicleRepository);

        ClientService clientService = new ClientServiceImpl(new ClientRepositoryImpl());
        ClientSubMenu clientSubMenu = new ClientSubMenu(clientService);

        Company company1 = new Company ("Eldorado", "Rua Joao Pessoa, Nº 15", "Suzano","60949723000163");
        Company company2 = new Company("Salvador","Rua Jose Pedro Augusto Nº 255", "Mogi das Cruzes", "39710503000196");

        companyService.addCompany(company1);
        companyService.addCompany(company2);

        Vehicle vehicle1 = new VehicleCar("Uno", 1993,"THX-2020",false,4);
        Vehicle vehicle2 = new VehicleBike("Honda", 2023,"TEK-1234",false);
        Vehicle vehicle3 = new VehicleTruck("Caminhao",1999,"ABC-9999",false,2.8);

        vehicleService.saveVehicle(vehicle1);
        vehicleService.saveVehicle(vehicle2);
        vehicleService.saveVehicle(vehicle3);

        company1.addVehicle(vehicle1);
        company1.addVehicle(vehicle3);

        Client client1 = new NaturalPerson("João Lucas","lucas@teste","11997731331","00123456789");
        Client client2= new LegalEntity("Maria Isabel", "maribel@teste","11998126781","60949724000163");

        clientService.createClient(client1);
        clientService.createClient(client2);

        Menu menu = new Menu(vehicleService,companyService,rentalService,clientService);
        menu.startMenu();
    }
}
