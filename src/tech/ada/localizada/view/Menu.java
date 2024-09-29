package tech.ada.localizada.view;

import tech.ada.localizada.service.client.ClientService;
import tech.ada.localizada.service.company.CompanyService;
import tech.ada.localizada.service.rental.RentalService;
import tech.ada.localizada.service.vehicle.VehicleService;

import java.util.Scanner;

public class Menu {

    private VehicleService vehicleService;
    private CompanyService companyService;
    private RentalService rentalService;
    private ClientService clientService;

    VehicleSubMenu vehicleSubMenu = new VehicleSubMenu(vehicleService, companyService);
    CompanySubMenu companySubMenu = new CompanySubMenu(companyService, vehicleService);
    RentalSubMenu rentalSubMenu = new RentalSubMenu(rentalService,clientService,vehicleService,companyService);
    ClientSubMenu clientSubMenu = new ClientSubMenu(clientService);

    public Menu(VehicleService vehicleService, CompanyService companyService,
                RentalService rentalService, ClientService clientService) {
        this.vehicleService = vehicleService;
        this.companyService = companyService;
        this.rentalService = rentalService;
        this.clientService = clientService;
    }

    Scanner input = new Scanner(System.in);

    public void startMenu() {

        int option = 0;

        do {

            String options = """
                    
                    = ------------------------------------- =
                    |       Sistema Aluguel de Veículos     |
                    = ------------------------------------- =
                    
                    = ------------=== Menu ===------------- =
                    | 1 - Cliente                           |
                    | 2 - Veículo                           |
                    | 3 - Agência                           |
                    | 4 - Aluguel                           |
                    | 5 - Sair                              |
                    = ------------------------------------- =
                    """;

            System.out.println(options);
            System.out.print("Digite uma opção: ");
            String optionString = input.next();

            try {
                option = Integer.parseInt(optionString);
                input.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("\nError: Please enter a valid option!");
                continue;
            }

            if (option < 1 || option > 5) {
                System.out.println("\nError: Please enter a valid option!");
            }

            switch (option) {
                case 1:
                    clientSubMenu.startMenuClient();
                    break;
                case 2:
                    vehicleSubMenu.startMenuVehicle();
                    break;
                case 3:
                    companySubMenu.showMenu();
                    break;
                case 4:
                    rentalSubMenu.showMenu();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    break;
            }

        } while (option != 5);
    }
}
