package tech.ada.localizada.view;

import tech.ada.localizada.exception.ClientNotFoundException;
import tech.ada.localizada.model.*;
import tech.ada.localizada.service.client.ClientService;
import tech.ada.localizada.service.company.CompanyService;
import tech.ada.localizada.service.rental.RentalService;
import tech.ada.localizada.service.vehicle.VehicleService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
                    System.out.println("\nDevolução");
                    devolverVeiculo();
                case 3:
                    System.out.println("\nSaindo...");
                    break;
                default:
                    break;
            }

        } while (option != 3);
    }

    public void createRentalSubMenu() {
        // Captura todas informações necessárias
        Client client = getClient();
        Company companyWithdrawal = getCompany("retirada");
        Vehicle vehicle = getVehicle(companyWithdrawal);
        Company companyReturn = getCompany("devolucão");
        LocalDateTime start = getStartDateTime();
        LocalDateTime finish = getFinishDateTime(start);
        PaymentMethod paymentMethod = getPaymentMethod();

        //Realiza o aluguel
        vehicle.setVehicleRented(true);
        Rental rental = rentalService.createRental(vehicle, client, start, finish, companyWithdrawal, companyReturn, paymentMethod);
        System.out.println(rental);

        //}
    }

    private PaymentMethod getPaymentMethod() {
        PaymentMethod paymentMethod = null;
        int paymentMethodOption;
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
                paymentMethodOption = Integer.parseInt(scanner.next());
                scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("\nError: Please enter a valid option!");
                continue;
            }
            if (paymentMethodOption < 1 || paymentMethodOption > 4) {
                System.out.println("\nError: Please enter a valid option!");
            } else {
                paymentMethod = switch (paymentMethodOption) {
                    case 1 -> PaymentMethod.PIX;
                    case 2 -> PaymentMethod.CARTAOCREDITO;
                    case 3 -> PaymentMethod.CARTAODEBITO;
                    case 4 -> PaymentMethod.DINHEIRO;
                    default -> PaymentMethod.PIX;
                };
            }
        } while (paymentMethod == null);
        return paymentMethod;
    }

    private LocalDateTime getStartDateTime() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = null;
        do {
            System.out.println("\nDigite a data de retirada ( dd/MM/yyy hh:mm ):");
            try {
                dateTime = LocalDateTime.parse(sc.nextLine(), fmt);
                if (dateTime.isBefore(LocalDateTime.now())) {
                    System.out.println("\nA data informada deve ser maior do que a data atual. Tente novamente.");
                    dateTime = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("\nA data deve ser informada no formato dd/MM/yyy hh:mm. Tente novamente.");
            }
        } while (dateTime == null);
        return dateTime;
    }

    private LocalDateTime getFinishDateTime(LocalDateTime start) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = null;
        do {
            System.out.println("\nDigite a data de devolução ( dd/MM/yyy hh:mm ):");
            try {
                dateTime = LocalDateTime.parse(sc.nextLine(), fmt);
                if (dateTime.isBefore(start)) {
                    System.out.println("\nA data de devolução não pode ser anterior à data de retirada. Tente novamente.");
                    dateTime = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("\nA data deve ser informada no formato dd/MM/yyy hh:mm. Tente novamente.");
            }
        } while (dateTime == null);
        return dateTime;
    }

    private Vehicle getVehicle(Company companyWithdrawal) {
        Vehicle vehicle = null;
        do {
            System.out.print("\nInforme a placa do veículo para a reserva: ");
            System.out.println(companyWithdrawal.getVehicles());
            String vehiclePlate = (scanner.nextLine());
            vehicle = vehicleService.getVehicleByPlate(vehiclePlate);
            if (vehicle == null) {
                System.out.println("\nVeículo não encontrado. Tente novamente.");
            }
        } while (vehicle == null);
        return vehicle;
    }

    private Company getCompany(String texto) {
        Company company = null;
        do {
            companyService.printCompanies();
            System.out.print("\nInforme o ID da agência para a " + texto + " do veículo: ");
            List<Company> companies = companyService.getAllCompanies();
            int companyId = Integer.parseInt(scanner.nextLine());
            company = companies.stream().filter(c -> c.getId() == companyId).findFirst().orElse(null);
            if (company == null) {
                System.out.println("\nAgência não encontrada. Tente novamente.");
            }
        }
        while (company == null);
        return company;
    }

    private Client getClient() {
        Client client = null;
        do {
            System.out.print("\nInforme o seu CPF ou CNPJ: ");
            String clientId = scanner.nextLine();
            try {
                client = clientService.getClientById(clientId);
            } catch (ClientNotFoundException e) {
                System.out.println("\nCliente não encontrado. Informe um CPF ou CNPJ cadastrado!");
            }

        } while (client == null);
        return client;
    }

    public void devolverVeiculo() {
        Client client = getClient();
        boolean found;
        String plate = null;
        List<Rental> clientRentals = rentalService.findByClient(client);
        do {
            System.out.println("= ---- Veículos alugados pelo cliente ---- =");
            System.out.println(clientRentals);
            System.out.println("= ---------------------------------------- =");
            System.out.print("\nDigite a placa do Veículo que deseja realizar a devolução: ");
            String finalPlate = scanner.nextLine();
            found = clientRentals.stream().anyMatch(rental -> rental.getVehicle().getPlate().equals(finalPlate));
            if(found){
                plate = finalPlate;
            }else{
                System.out.println("\nAluguel não encotrado. Tente novamente.");
            }

        } while (!found);

        Rental rental = rentalService.devolucaoVeiculo(client, plate);

        System.out.println("\nO veículo " + rental.getVehicle().getModel() + " foi devolvido com sucesso.");
    }
}


// 30/10/2024 13:00
// 05/11/2024 13:00









