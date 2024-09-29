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

       // clientSubMenu.startMenuClient();

        Company company1 = new Company ("Eldorado", "Rua Joao", "Suzao","123");
        Company company2 = new Company("Salvaodr","Rua Jose", "Mogi", "456");
        companyService.addCompany(company1);
        companyService.addCompany(company2);

        Menu menu = new Menu(vehicleService,companyService,rentalService,clientService);
        menu.startMenu();

  /*      Scanner sc = new Scanner(System.in);
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
