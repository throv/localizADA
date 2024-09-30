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
            String options = """
                                        
                    = ------------------------------------- =
                    |              Menu Aluguel             |
                    = ------------------------------------- =
                                        
                    = -------------=== Menu ===------------ =
                    | 1 - Alugar veículo                    |
                    | 2 - Devolver veículo                  |
                    | 3 - Sair                              |
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

            if (option < 1 || option > 3) {
                System.out.println("\nError: Please enter a valid option!");
            }

            switch (option) {
                case 1:
                    createRentalSubMenu();
                    break;
                case 2:
                    System.out.println("Devolução");
                    devolverVeiculo();
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    break;
            }

        } while (option != 3);
    }

    public void createRentalSubMenu() {

        System.out.print("Informe o seu CPF ou CNPJ: ");
        String clientId = scanner.nextLine();
        Client client = clientService.getClientById(clientId);

        companyService.printCompanies();
        System.out.print("\nInforme a agência para a retirada do veículo: ");
        List<Company> companies = companyService.getAllCompanies();
        int companyIndex = Integer.parseInt(scanner.nextLine());
        Company companyWithdraal = companies.get(companyIndex);


        System.out.print("Selecione o veículo para a reserva: ");
        System.out.println(vehicleService.listVehicle());
        String vehiclePlate = (scanner.nextLine());
        Vehicle vehicle = vehicleService.getVehicleByPlate(vehiclePlate);

        companyService.printCompanies();
        System.out.print("\nInforme a agência para a devolucão do veículo: ");
        companyIndex = Integer.parseInt(scanner.nextLine());
        Company companyReturn = companies.get(companyIndex);


        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("\nDigite a data de retirada ( dd/MM/yyy hh:mm ): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.println("\nDigite a data de devolução ( dd/MM/yyy hh:mm ): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
        fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        int option = 0;

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

            PaymentMethod paymentMethod = switch (option) {
                case 1 -> PaymentMethod.PIX;
                case 2 -> PaymentMethod.CARTAOCREDITO;
                case 3 -> PaymentMethod.CARTAODEBITO;
                case 4 -> PaymentMethod.DINHEIRO;
                default -> PaymentMethod.PIX;
            };

            vehicle.setVehicleRented(true);

            Rental rental = rentalService.createRental(vehicle, client, start, finish, companyWithdraal, companyReturn, paymentMethod);
            System.out.println(rental);

        } while (option != 4);
    }

    public void devolverVeiculo() {
        System.out.println("Digite o seu CPF ou CNPJ:");
        String clientId = scanner.nextLine();
        Client client = clientService.getClientById(clientId);

        System.out.println("Digite a placa do Veículo que deseja realizar a devolução");
        System.out.println(rentalService.findByClient(client));
        String plate = scanner.nextLine();

        Rental rental = rentalService.devolucaoVeiculo(client, plate);

        System.out.println("O veículo " + rental.getVehicle().getModel() + " foi devolvido com sucesso.");
    }
}














