package tech.ada.localizada.view;

import tech.ada.localizada.model.*;
import tech.ada.localizada.service.company.CompanyService;
import tech.ada.localizada.service.vehicle.VehicleService;
import tech.ada.localizada.service.vehicle.VehicleServiceImpl;

import java.util.Scanner;

public class VehicleSubMenu {

    private final VehicleService vehicleService;
    private final CompanyService companyService;
    private final Scanner scanner = new Scanner(System.in);

    public VehicleSubMenu(VehicleService vehicleService, CompanyService companyService) {
        this.vehicleService = vehicleService;
        this.companyService = companyService;
    }

    public void startMenuVehicle() {

        int option;

        do {
            String options = """
                    
                    = ------------------------------------- =
                    |              Menu Veículo             |
                    = ------------------------------------- =
                    
                    = ------------=== Menu ===------------- =
                    | 1 - Adicionar veículo                 |
                    | 2 - Editar veículo                    |
                    | 3 - Excluir veículo                   |
                    | 4 - Mudar disponibilidade do veículo  |
                    | 5 - Atualizar localização do veículo  |
                    | 6 - Sair                              |
                    = ------------------------------------- =
                    """;

            System.out.println(options);
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createVehicleSubMenu();
                    break;
                case 2:
                    updateVehicleSubMenu();
                    break;
                case 3:
                    deleteVehicle(vehicleService);
                    break;
                case 4:
                    updateVehicleRented(vehicleService);
                    break;
                case 5:
                    updateVehicleCompanyMenu(vehicleService);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (option != 6);
    }

    public void createVehicleSubMenu() {

        System.out.print("\nDigite o modelo do carro: ");
        String model = scanner.nextLine();

        System.out.print("\nDigite o ano do carro: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("\nDigite a placa do carro: ");
        String plate = scanner.nextLine();

        companyService.printCompanies();

        System.out.print("\nDigite o CNPJ da Companhia: ");
        String cnpjCompany = scanner.nextLine();

        Company company = getCompanyByCNPJ(cnpjCompany);

        System.out.print(company);

        System.out.print("\nO carro está alugado? (true/false): ");
        boolean isRented = scanner.nextBoolean();

        int vehicleType;
        do {
            String options = """
                    
                    = ------------------------------------- =
                    |       Escolha o tipo de veículo?      |
                    = ------------------------------------- =
                    
                    = ----------=== Escolher ===----------- =
                    | 1 - Carro                             |
                    | 2 - Moto                              |
                    | 3 - Caminhão                          |
                    = ------------------------------------- =
                    """;

            System.out.println(options);
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

        System.out.print("\nDigite o número de portas do carro: ");
        int numberOfDoors = scanner.nextInt();
        VehicleCar vehicleCar = new VehicleCar(model,year,plate,company,isRented,numberOfDoors);
        vehicleService.saveVehicle(vehicleCar);
        vehicleService.printVehicles();
        System.out.println("\nCARRO ADICIONADO COM SUCESSO!");
    }

    public void createVehicleBikeSubMenu(String model, int year,String plate,
                                       Company company,boolean isRented) {

        VehicleBike vehicleBike = new VehicleBike(model,year,plate,company,isRented);
        vehicleService.saveVehicle(vehicleBike);
        System.out.println(vehicleService.listVehicle());
        System.out.println("\nMOTO ADICIONADA COM SUCESSO!");

    }

    public void createVehicleTruckSubMenu(String model, int year,String plate,
                                        Company company,boolean isRented) {

        System.out.print("\nLimite de carga do caminhão: ");
        double loadTruck = scanner.nextDouble();

        VehicleTruck vehicleTruck = new VehicleTruck(model,year,plate,company,isRented,loadTruck);
        vehicleService.saveVehicle(vehicleTruck);
        System.out.println(vehicleService.listVehicle());
        System.out.println("\nCAMINHÃO ADICIONADO COM SUCESSO!");
    }

    public void updateVehicleSubMenu() {

        int opcao;

        do {
            String options = """

                    = ------------------------------------- =
                    |     Qual informação deseja editar?    |
                    = ------------------------------------- =
                    
                    = -----------=== Editar ===------------ =
                    | 1 - Modelo do Veículo                 |
                    | 2 - Placa do Veículo                  |
                    | 3 - Ano do Veículo                    |
                    | 4 - Voltar                            |
                    = ------------------------------------- =
                    """;

            System.out.println(options);
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    updateVehicleModelMenu(vehicleService);
                    break;
                case 2:
                    updateVehiclePlateMenu(vehicleService);
                    break;
                case 3:
                    updateVehicleYearMenu(vehicleService);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 4);
    }

    public void updateVehicleModelMenu(VehicleService vehicleService) {
        Vehicle vehicle = getVehicleFromUserInput();
        //System.out.println(vehicle);
        System.out.print("\nDigite o modelo do carro: ");
        String model = scanner.nextLine();
        vehicleService.updateModelVehicle(vehicle,model);
        //System.out.println(vehicleService.listVehicle());
    }

    public void updateVehicleYearMenu(VehicleService vehicleService) {
        Vehicle vehicle = getVehicleFromUserInput();
        //System.out.println(vehicle);
        System.out.print("\nDigite o novo ano do carro: ");
        int newYear = Integer.parseInt(scanner.nextLine());
        vehicleService.updateVehicleYear(vehicle,newYear);
        vehicleService.listVehicle();
        //System.out.println(vehicleService.listVehicle());
    }

    public void updateVehiclePlateMenu(VehicleService vehicleService) {
        Vehicle vehicle = getVehicleFromUserInput();
        //System.out.println(vehicle);
        System.out.print("\nDigite a nova placa do carro: ");
        String newPlate = scanner.nextLine();
        vehicleService.updateVehiclePlate(vehicle,newPlate);
        //System.out.println(vehicleService.listVehicle());
    }

    public void updateVehicleCompanyMenu(VehicleService vehicleService) {
        Vehicle vehicle = getVehicleFromUserInput();
        //System.out.println(vehicle);
        System.out.println(companyService.getAllCompanies());
        System.out.print("\nDigite o novo CNPJ da Companhia: ");
        String newCnpjCompany = scanner.nextLine();
        Company newCompany = getCompanyByCNPJ(newCnpjCompany);
        vehicleService.updateVehicleVehicleCompany(vehicle,newCompany);
        //System.out.println(vehicleService.listVehicle());
    }

    public void updateVehicleRented (VehicleService vehicleService) {
        Vehicle vehicle = getVehicleFromUserInput();
        //System.out.println(vehicle);
        System.out.print("\nDigite aqui é true ou false ? (true/false): ");
        boolean isRented = scanner.nextBoolean();
        vehicleService.updateVehicleRented(vehicle, isRented);
        //System.out.println(vehicleService.listVehicle());
    }

    public void deleteVehicle(VehicleService vehicleService) {
        Vehicle vehicle = getVehicleFromUserInput();
        vehicleService.deleteVehicle(vehicle.getId());
        //System.out.println(vehicleService.listVehicle());
    }

    public Vehicle getVehicleFromUserInput() {
        System.out.print("\nDigite a placa do carro: ");
        String plate = scanner.nextLine();
        return getVehiclebyPlate(plate);
    }

    private Vehicle getVehiclebyPlate(String plate) {
        for (Vehicle vehicle : vehicleService.listVehicle()) {
            if (vehicle.getPlate().equals(plate)) {
                return vehicle; }
        } return null;
    }

    private Company getCompanyByCNPJ(String cnpjCompany) {
        for (Company company : companyService.getAllCompanies()) {
            if (company.getCnpj().equals(cnpjCompany)) {
                return company; }
        } return null;
    }




}




