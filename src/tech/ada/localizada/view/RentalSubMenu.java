package tech.ada.localizada.view;

import tech.ada.localizada.model.*;
import tech.ada.localizada.service.client.ClientService;
import tech.ada.localizada.service.company.CompanyService;
import tech.ada.localizada.service.rental.RentalService;
import tech.ada.localizada.service.vehicle.VehicleService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class RentalSubMenu {
    private RentalService rentalService;


    static Scanner scanner = new Scanner(System.in);
    ClientService clientService;
    VehicleService vehicleService;
    CompanyService companyService;

    //Principio I - SOLID
    public RentalSubMenu(RentalService rentalService, ClientService clientService, VehicleService vehicleService, CompanyService companyService) {
        this.rentalService = rentalService;
        this.clientService = clientService;
        this.vehicleService = vehicleService;
        this.companyService = companyService;
    }

    public void showMenu() {

        int option = 0;

        do {
<<<<<<< Updated upstream
            String options = """
                                        
                    = ------------------------------------- =
                    |              Menu Aluguel             |
                    = ------------------------------------- =
                                        
                    = -------------=== Menu ===------------ =
                    | 1 - Alugar veículo                    |
                    | 2 - Sair                              |
                    = ------------------------------------- =
                    """;

            System.out.println(options);
=======
            System.out.println("Menu:");
            System.out.println("1 - Alugar Veículo");
            System.out.println("2 - Devolução");
            System.out.println("3 - Sair");
>>>>>>> Stashed changes
            System.out.print("Escolha uma opção: ");

            try {
                option = Integer.parseInt(scanner.next());
                scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("\nError: Please enter a valid option!");
                continue;
            }

            if (option < 1 || option > 2) {
                System.out.println("\nError: Please enter a valid option!");
            }

            switch (option) {
                case 1:
                    createRentalSubMenu();
                    break;
                case 2:
                    System.out.println("Devolução");

                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    break;
            }

        } while (option != 3);
    }

    public void createRentalSubMenu() {

<<<<<<< Updated upstream
        System.out.print("\nInforme o seu CPF: ");
=======
        System.out.print("Informe o seu CPF ou CNPJ: ");
>>>>>>> Stashed changes
        String clientId = scanner.nextLine();
        Client client = clientService.getClientById(clientId);

        companyService.printCompanies();
        System.out.print("\nInforme a agência para a retirada do veículo: ");
        List<Company> companies = companyService.getAllCompanies();
        int companyIndex = Integer.parseInt(scanner.nextLine());
<<<<<<< Updated upstream
        Company companyWithdraal = companies.get(companyIndex);
        scanner.nextLine();

        vehicleService.printVehicles();
        System.out.print("\nSelecione o veículo para a reserva: ");
        List<Vehicle> vehicles = vehicleService.listVehicleByCompany(companyWithdraal);
        int vehiclesIndex = Integer.parseInt(scanner.nextLine());
        Vehicle vehicle = vehicles.get(vehiclesIndex + 1);
=======
        Company companyWithdraal = companies.get(companyIndex );


        System.out.print("Selecione o veículo para a reserva: ");
        System.out.println(vehicleService.listVehicle());
        String vehiclePlate = (scanner.nextLine());
        Vehicle vehicle = vehicleService.updateVehiclePlate(vehiclePlate);
>>>>>>> Stashed changes

        companyService.printCompanies();
        System.out.print("\nInforme a agência para a devolucão do veículo: ");
        companyIndex = Integer.parseInt(scanner.nextLine());
<<<<<<< Updated upstream
        Company companyReturn = companies.get(companyIndex);
=======
        Company companyReturn = companies.get(companyIndex );

>>>>>>> Stashed changes

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("\nDigite a data de retirada ( dd/MM/yyy hh:mm ): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.println("\nDigite a data de devolução ( dd/MM/yyy hh:mm ): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
        fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        int option = 0;

<<<<<<< Updated upstream
        do {
            String options = """
                    
                    = ------------------------------------- =
                    |           Forma de Pagamento          |
                    = ------------------------------------- =
                    
                    = ----------=== Pagamento ===---------- =
                    | 1 - Pix                               |
                    | 2 - Cartão de Crédito                 |
                    | 3 - Cartão de Débito                  |
                    | 4 - Dinheiro                          |
                    = ------------------------------------- =
                    """;

            System.out.println(options);
            System.out.print("Escolha uma opção: ");

            try {
                option = Integer.parseInt(scanner.next());
                scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("\nError: Please enter a valid option!");
                continue;
            }

            if (option < 1 || option > 4) {
                System.out.println("\nError: Please enter a valid option!");
            }

            PaymentMethod paymentMethod;
=======
        System.out.println("Informe a forma de pagamento: ");
        System.out.println("1- PIX");
        System.out.println("2- Cartão de Crédito");
        System.out.println("3- Cartão de Débito");
        System.out.println("4- Dinheiro");
        int paymentMethodIndex = Integer.parseInt(scanner.nextLine());
        PaymentMethod paymentMethod;
>>>>>>> Stashed changes

        switch (paymentMethodIndex) {
            case 1:
                paymentMethod = PaymentMethod.PIX;
                break;
            case 2:
                paymentMethod = PaymentMethod.CARTAOCREDITO;
                break;
            case 3:
                paymentMethod = PaymentMethod.CARTAODEBITO;
                break;
            case 4:
                paymentMethod = PaymentMethod.DINHEIRO;
                break;

            default:
                paymentMethod = PaymentMethod.PIX;
        }

        vehicle.setVehicleRented(true);
        Rental rental = rentalService.createRental(vehicle, client, start, finish,
                companyWithdraal, companyReturn, paymentMethod);
        System.out.println(rental);

            switch (option) {
                case 1:
                    paymentMethod = PaymentMethod.PIX;
                    break;
                case 2:
                    paymentMethod = PaymentMethod.CARTAOCREDITO;
                    break;
                case 3:
                    paymentMethod = PaymentMethod.CARTAODEBITO;
                    break;
                case 4:
                    paymentMethod = PaymentMethod.DINHEIRO;
                    break;
                default:
                    paymentMethod = PaymentMethod.PIX;
                    break;
            }

            Rental rental = rentalService.createRental(vehicle, client, start, finish, companyWithdraal, companyReturn, paymentMethod);
            System.out.println(rental);

        } while (option != 4);
    }

    public void devolverVeículo(){
        System.out.println("Digite o seu CPF ou CNPJ:");
        String clientId = scanner.nextLine();
        Client client = clientService.getClientById(clientId);

        System.out.println("Digite a placa do Veículo que deseja realizar a devolução");
        System.out.println(rentalService.findByClient(client));
        String plate = scanner.nextLine();

        rentalService.devolucaoVeiculo(client,plate);
    }
}














