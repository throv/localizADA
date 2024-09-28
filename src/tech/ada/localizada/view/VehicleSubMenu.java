package tech.ada.localizada.view;

import tech.ada.localizada.model.Company;
import tech.ada.localizada.model.Vehicle;
import tech.ada.localizada.model.VehicleCar;
import tech.ada.localizada.repository.vehicle.VehicleRepositoryImpl;
import tech.ada.localizada.service.company.CompanyService;
import tech.ada.localizada.service.vehicle.VehicleService;
import tech.ada.localizada.service.vehicle.VehicleServiceImpl;

import java.util.Scanner;

public class VehicleSubMenu {

    private final VehicleService vehicleService;
    private final CompanyService companyService;
    private final Scanner scanner = new Scanner(System.in);

    public VehicleSubMenu(VehicleServiceImpl vehicleService, CompanyService companyService) {
        this.vehicleService = vehicleService;
        this.companyService = companyService;
    }

    public void startMenuVehicle() {

        int option;

        do {
            String options = """
                    
                    = ------------------------------- =
                    |          Menu veículo           |
                    = ------------------------------- =
                    
                    = ---------=== Menu ===---------- =
                    | 1 - Adicionar                        |
                    | 2 - Moto                        |
                    | 3 - Caminhão                    |
                    = ------------------------------- =
                    """;
            System.out.println("Menu:");
            System.out.println("1 - Adicionar Carro");
            System.out.println("2 - Editar Carro");
            System.out.println("3 - Excluir Carro");
            System.out.println("4 - Mudar Disponibilidade Veiculo");
            System.out.println("5 - Atualizar localização do Carro");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            option = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (option) {
                case 1:
                    createVehicleSubMenu();
                    break;
                case 2:
                    updateVehicleSubMenu();
                    break;
                case 3:
                    deleteVehicle();
                    break;
                case 4:
                    updateVehicleRented();
                    break;
                case 5:
                    updateVehicleCompanyMenu();
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

        System.out.print("Digite o modelo do carro: ");
        String model = scanner.nextLine();

        System.out.print("Digite o ano do carro: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite a placa do carro: ");
        String plate = scanner.nextLine();

        System.out.println(companyService.getAllCompanies());

        System.out.print("Digite o CNPJ da Companhia: ");
        String cnpjCompany = scanner.nextLine();

        Company company = getCompanyByCNPJ(cnpjCompany);

        System.out.println(company);

        System.out.print("O carro está alugado? (true/false): ");
        boolean isRented = scanner.nextBoolean();

        int vehicleType;
        do {
            String options = """
                    
                    = ------------------------------- =
                    |    Escolha o tipo de veículo?   |
                    = ------------------------------- =
                    
                    = -------=== Escolher ===-------- =
                    | 1 - Carro                       |
                    | 2 - Moto                        |
                    | 3 - Caminhão                    |
                    = ------------------------------- =
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

        System.out.print("Digite o número de portas do carro: ");
        int numberOfDoors = scanner.nextInt();

        VehicleCar vehicleCar = new VehicleCar(model,year,plate,company,isRented,numberOfDoors);

        vehicleService.saveVehicle(vehicleCar);

        System.out.println(vehicleService.listVehicle());

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
            String options = """

                    = ------------------------------- =
                    |  Qual informação deseja editar? |
                    = ------------------------------- =
                    
                    = --------=== Editar ===--------- =
                    | 1 - Modelo do Veículo           |
                    | 2 - Placa do Veículo            |
                    | 3 - Ano do Veículo              |
                    | 4 - Voltar                      |
                    = ------------------------------- =
                    """;

            System.out.println(options);
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

        //vehicleService.updateModelVehicle(vehicle,model);

    }

    public void updateVehicleYearMenu() {

        VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();

        System.out.print("Digite a placa do carro: ");
        String plate = scanner.nextLine();

        Vehicle vehicle = vehicleRepository.findVehicleByPlate(plate);

        System.out.print("Digite o novo ano do carro: ");
        int newYear = Integer.parseInt(scanner.nextLine());

        //vehicleService.updateVehicleYear(vehicle,newYear);

        vehicleService.listVehicle();

    }

    public void updateVehiclePlateMenu() {

        VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();

        System.out.print("Digite a placa do carro: ");
        String plate = scanner.nextLine();

        Vehicle vehicle = vehicleRepository.findVehicleByPlate(plate);

        System.out.print("Digite a nova placa do carro: ");
        String newPlate = scanner.nextLine();

        //vehicleService.updateVehiclePlate(vehicle,newPlate);

    }

    public void updateVehicleCompanyMenu() {

        VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();

        System.out.print("Digite a placa do carro: ");
        String plate = scanner.nextLine();

        Vehicle vehicle = vehicleRepository.findVehicleByPlate(plate);

        System.out.println(companyService.getAllCompanies());

        System.out.print("Digite o novo CNPJ da Companhia: ");
        String newCnpjCompany = scanner.nextLine();

        Company newCompany = getCompanyByCNPJ(newCnpjCompany);

       // vehicleService.updateVehicleVehicleCompany(vehicle,newCompany);

    }

    public void updateVehicleRented () {

        VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();

        System.out.print("Digite a placa do carro: ");
        String plate = scanner.nextLine();

        Vehicle vehicle = vehicleRepository.findVehicleByPlate(plate);

        System.out.print("Digite aqui é true ou false ? (true/false): ");
        boolean isRented = scanner.nextBoolean();

       // vehicleService.updateVehicleVehicleRented(vehicle, isRented);
    }

    public void deleteVehicle() {

        VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();

        System.out.print("Digite a placa do carro: ");
        String plate = scanner.nextLine();

        Vehicle vehicle = vehicleRepository.findVehicleByPlate(plate);

        vehicleService.deleteVehicle(vehicle.getId());
    }

    private Company getCompanyByCNPJ(String cnpjCompany) {
        // Verifica se a string CNPJ não está vazia
        if (cnpjCompany == null || cnpjCompany.trim().isEmpty()) {
            System.out.println("O CNPJ informado está vazio.");
            return null;
        }

        // Busca a companhia pelo CNPJ na lista de companhias
        for (Company company : companyService.getAllCompanies()) {
            if (company.getCnpj().equals(cnpjCompany)) {
                return company; // Retorna a companhia se encontrada
            }
        }

        // Se não encontrou, exibe uma mensagem
        System.out.println("Companhia não encontrada para o CNPJ: " + cnpjCompany);
        return null; // Retorna null se não encontrou
    }


}




