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
import java.util.Scanner;

public class CarRentalApp {


    public static void main(String[] args) {

        VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();
        VehicleServiceImpl vehicleService = new VehicleServiceImpl(vehicleRepository);
        CompanyRepositoryImpl companyRepository = new CompanyRepositoryImpl();
        CompanyServiceImpl companyService = new CompanyServiceImpl(companyRepository);
        RentalRepositoryImpl rentalRepository = new RentalRepositoryImpl();
        RentalService rentalService = new RentalService();

        ClientService clientService = new ClientServiceImpl(new ClientRepositoryImpl());
        ClientSubMenu clientSubMenu = new ClientSubMenu(clientService);

        Company company1 = new Company ("Eldorado", "Rua Joao Pessoa, Nº 15", "Suzano","60.949.723/0001-63");
        Company company2 = new Company("Salvador","Rua Jose Pedro Augusto Nº 255", "Mogi das Cruzes", "39.710.503/0001-96");
        companyService.addCompany(company1);
        companyService.addCompany(company2);
        Vehicle vehicle1 = new VehicleCar("Uno", 1993,"THX-2020",false,4);
        vehicleService.saveVehicle(vehicle1);
        Client client1 = new NaturalPerson("Joao","lucas@gmail.com","1199773133","40156215810");
        clientService.createClient(client1);

        Menu menu = new Menu(vehicleService,companyService,rentalService,clientService);
        menu.startMenu();

  /*    Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados do aluguel");
        System.out.println("Retirada ( dd/MM/yyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(),fmt);
        System.out.println("Retorno ( dd/MM/yyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(),fmt);

        RentalService rs = new RentalService();

        sc.close();
  */
    }
}
