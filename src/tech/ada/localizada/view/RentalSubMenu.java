package tech.ada.localizada.view;

import tech.ada.localizada.model.*;
import tech.ada.localizada.repository.client.ClientRepositoryImpl;
import tech.ada.localizada.repository.company.CompanyRepositoryImpl;
import tech.ada.localizada.repository.vehicle.VehicleRepositoryImpl;
import tech.ada.localizada.service.rental.RentalService;
import tech.ada.localizada.service.vehicle.VehicleServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RentalSubMenu {
    private RentalService rentalService = new RentalService();
    static Scanner scanner = new Scanner(System.in);
    CompanyRepositoryImpl companyRepository = new CompanyRepositoryImpl();
    VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();
    ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();

    public RentalSubMenu(VehicleServiceImpl vehicleService) {
        this.rentalService = rentalService;
    }

    public RentalSubMenu(RentalService rentalService) {
        this.rentalService = rentalService;
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

        System.out.print("Cliente que irá alugar Veículo: ");
        List<Client> clientDb = clientRepository.findAll();
        System.out.println(clientDb);
        int clientIndex = Integer.parseInt(scanner.nextLine());
        Client client = clientDb.get(clientIndex + 1);

        System.out.print("Digite o número identificador do veículo que será alugado: ");
        List<Vehicle> vehiclesDb = vehicleRepository.findAll();
        System.out.println(vehiclesDb);
        int vehicleIndex = Integer.parseInt(scanner.nextLine());
        Vehicle vehicle = vehiclesDb.get(vehicleIndex + 1);


        System.out.print("Informe o número identificador da agência para retirada: ");
        List<Company> companies = companyRepository.findAll();
        System.out.println(companies);
        int companyIndex = Integer.parseInt(scanner.nextLine());
        Company companyWithdraal = companies.get(companyIndex + 1);


        System.out.print("Inform the agency to return the vehicle: ");
        System.out.println(companies);
        companyIndex = Integer.parseInt(scanner.nextLine());
        Company companyReturn = companies.get(companyIndex + 1);


        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Digite a data de retirada ( dd/MM/yyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(),fmt);
        System.out.println("Digite a data de devolução ( dd/MM/yyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(),fmt);
        fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");



        System.out.print("Fatura: ");
        rentalService.createRental(vehicle,client,start,finish,companyWithdraal,companyReturn,PaymentMethod.PIX);
      /*

        int vehicleType;
        do {

            System.out.println("Escolha o tipo de veículo:");
            System.out.println("1 - Carro");
            System.out.println("2 - Moto");
            System.out.println("3 - Caminhão");
            System.out.print("Escolha uma opção: ");
            vehicleType = scanner.nextInt();
            scanner.nextLine();
            switch (vehicleType) {
                case 1:
                    createVehicleCarSubMenu(model,year,plate,company,isRented);
                    break;
                case 2:
                    createVehicleBikeSubMenu(model,year,plate,company,isRented);
                    break;
                case 3:
                    createVehicleTruckSubMenu(model,year,plate,company,isRented);
                    break;
                default:
            }

        } while (vehicleType < 1 || vehicleType > 3);
    }

    public void createVehicleCarSubMenu(String model, int year, String plate,
                                        Company company, boolean isRented) {

        System.out.print("Digite o número de portas do carro: ");
        int numberOfDoors = scanner.nextInt();

        VehicleCar vehicleCar = new VehicleCar(model,year,plate,company,isRented,numberOfDoors);

        vehicleService.saveVehicle(vehicleCar);

    }

    public void createVehicleBikeSubMenu(String model, int year,String plate,
                                         Company company,boolean isRented) {


    }

    public void createVehicleTruckSubMenu(String model, int year,String plate,
                                          Company company,boolean isRented) {

    }

    public void updateVehicleSubMenu() {

        int opcao;

        do {
            System.out.println("Qual informação deseja editar");
            System.out.println("1 - Editar Modelo do Carro");
            System.out.println("2 - Editar Placa do Carro");
            System.out.println("3 - Editar Ano do Carro");
            System.out.println("4 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    updateVehicleModelMenu();
                    break;
                case 2:
                    updateVehiclePlateMenu();
                    break;
                case 3:
                    updateVehicleYearMenu();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 4);
    }

    public void updateVehicleModelMenu() {

        VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();

        System.out.print("Digite o modelo do carro: ");
        String plate = scanner.nextLine();

        Vehicle vehicle = vehicleRepository.findVehicleByPlate(plate);

        System.out.print("Digite o modelo do carro: ");
        String model = scanner.nextLine();

        vehicleService.updateModelVehicle(vehicle,model);

    }

    public void updateVehicleYearMenu() {

        VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();

        System.out.print("Digite a placa do carro: ");
        String plate = scanner.nextLine();

        Vehicle vehicle = vehicleRepository.findVehicleByPlate(plate);

        System.out.print("Digite o novo ano do carro: ");
        int newYear = Integer.parseInt(scanner.nextLine());

        vehicleService.updateVehicleYear(vehicle,newYear);
    }

    public void updateVehiclePlateMenu() {

        VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();

        System.out.print("Digite a placa do carro: ");
        String plate = scanner.nextLine();

        Vehicle vehicle = vehicleRepository.findVehicleByPlate(plate);

        System.out.print("Digite o novo ano do carro: ");
        String newPlate = scanner.nextLine();

        vehicleService.updateVehiclePlate(vehicle,newPlate);

    }

    public void updateVehicleCompanyMenu() {

        VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();

        System.out.print("Digite a placa do carro: ");
        String plate = scanner.nextLine();

        Vehicle vehicle = vehicleRepository.findVehicleByPlate(plate);

        companyRepository.findAll();

        System.out.print("Digite o novo CNPJ da Companhia: ");
        String newCnpjCompany = scanner.nextLine();

        Company newCompany = getCompanyByCNPJ(newCnpjCompany);

        vehicleService.updateVehicleVehicleCompany(vehicle,newCompany);

    }

    public void updateVehicleRented () {

        VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();

        System.out.print("Digite a placa do carro: ");
        String plate = scanner.nextLine();

        Vehicle vehicle = vehicleRepository.findVehicleByPlate(plate);

        System.out.print("Digite aqui é true ou false ? (true/false): ");
        boolean isRented = scanner.nextBoolean();

        vehicleService.updateVehicleVehicleRented(vehicle, isRented);
    }

    public void deleteVehicle() {

        VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();

        System.out.print("Digite a placa do carro: ");
        String plate = scanner.nextLine();

        int idVehicle = vehicleRepository.findIdByPlate(plate);

        vehicleService.deleteVehicle(idVehicle);
    }

    private Company getCompanyByCNPJ(String cnpjCompany) {
        Optional<Company> company = companyRepository.findCompanyByCNPJ(cnpjCompany);
        return company.orElse(null); */
    }


    }




