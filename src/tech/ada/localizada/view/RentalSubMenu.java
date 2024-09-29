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

        int option;

        do {
            System.out.println("Menu:");
            System.out.println("1 - Alugar Veículo");
            System.out.println("2 - Sair");
            System.out.print("Escolha uma opção: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createRentalSubMenu();
                    break;

                case 2:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (option != 2);
    }

    public void createRentalSubMenu() {

        System.out.print("Informe o seu CPF: ");
        String clientId = scanner.nextLine();
        Client client = clientService.getClientById(clientId);

        System.out.print("Informe a Agência para a retirada do veículo:  ");
        List<Company> companies = companyService.getAllCompanies();
        System.out.println(companies);
        int companyIndex = Integer.parseInt(scanner.nextLine());
        Company companyWithdraal = companies.get(companyIndex + 1);


        System.out.print("Selecione o veículo para a reserva: ");
        List<Vehicle> vehicles = vehicleService.listVehicleByCompany(companyWithdraal);
        System.out.println(vehicles);
        int vehiclesIndex = Integer.parseInt(scanner.nextLine());
        Vehicle vehicle = vehicles.get(vehiclesIndex + 1);


        System.out.print("Informe a Agência para a devolucão do veículo: ");
        System.out.println(companies);
        companyIndex = Integer.parseInt(scanner.nextLine());
        Company companyReturn = companies.get(companyIndex + 1);


        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Digite a data de retirada ( dd/MM/yyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.println("Digite a data de devolução ( dd/MM/yyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
        fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


        System.out.print("Informe a forma de pagamento: ");
        System.out.println("1- PIX");
        System.out.println("2- Cartão de Crédito");
        System.out.println("3- Cartão de Débito");
        System.out.println("4- Dinheiro");
        int paymentMethodIndex = Integer.parseInt(scanner.nextLine());
        PaymentMethod paymentMethod;

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


        Rental rental = rentalService.createRental(vehicle, client, start, finish, companyWithdraal, companyReturn, paymentMethod);
        System.out.println(rental);
    }
}














