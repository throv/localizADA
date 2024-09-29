package tech.ada.localizada.view;

import tech.ada.localizada.service.client.ClientService;
import tech.ada.localizada.service.company.CompanyService;
import tech.ada.localizada.service.rental.RentalService;
import tech.ada.localizada.service.vehicle.VehicleService;
import tech.ada.localizada.service.vehicle.VehicleServiceImpl;

import java.util.Scanner;

public class Menu {

    private VehicleService vehicleService;
    private CompanyService companyService;
    private RentalService rentalService;
    private ClientService clientService;

    public Menu(VehicleService vehicleService, CompanyService companyService,
                RentalService rentalService, ClientService clientService) {
        this.vehicleService = vehicleService;
        this.companyService = companyService;
        this.rentalService = rentalService;
        this.clientService = clientService;
    }

    Scanner input = new Scanner(System.in);

        public void startMenu () {

            int option = 0;

            do {

                String options = """
                                            
                        = ------------------------------- =
                        |      Vehicle Rental System      |
                        = ------------------------------- =
                                            
                        = ---------=== Menu ===---------- =
                        | 1 - Client                      |
                        | 2 - Vehicle                     |
                        | 3 - Company                     |
                        | 4 - Rent                        |
                        | 5 - Exit                        |
                        = ------------------------------- =
                        """;

                System.out.println(options);
                System.out.print("Enter an option: ");
                String optionString = input.next();

                try {
                    option = Integer.parseInt(optionString);
                    input.nextLine();
                } catch (NumberFormatException e) {
                    System.err.println("Error: Please enter a valid option!\n");
                    continue;
                }

                if (option < 1 || option > 5) {
                    System.err.println("Error: Please enter a valid option!\n");
                }

                switch (option) {
                    case 1:

                        break;
                    case 2:
                        VehicleSubMenu vehicleSubMenu = new VehicleSubMenu (vehicleService, companyService);
                        vehicleSubMenu.startMenuVehicle();
                        break;
                    case 3:
                        System.out.println("Company menu");
                        break;
                    case 4:
                        RentalSubMenu rentalSubMenu = new RentalSubMenu(rentalService,clientService,vehicleService,companyService);
                        rentalSubMenu.showMenu();
                        break;
                    case 5:
                        System.out.println("Leaving...");
                        break;
                    default:
                        break;
                }

            } while (option != 5);

        }
    }

