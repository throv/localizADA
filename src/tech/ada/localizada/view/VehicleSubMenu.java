package tech.ada.localizada.view;

import tech.ada.localizada.model.Company;
import tech.ada.localizada.model.Vehicle;
import tech.ada.localizada.model.VehicleCar;
import tech.ada.localizada.repository.company.CompanyRepositoryImpl;
import tech.ada.localizada.repository.vehicle.VehicleRepositoryImpl;
import tech.ada.localizada.service.vehicle.VehicleServiceImpl;

import java.util.Optional;
import java.util.Scanner;

public class VehicleSubMenu {

    private final VehicleServiceImpl vehicleService;
    static Scanner scanner = new Scanner(System.in);
    CompanyRepositoryImpl companyRepository = new CompanyRepositoryImpl();

    public VehicleSubMenu(VehicleServiceImpl vehicleService) {
        this.vehicleService = vehicleService;
    }

    public void showMenu() {

        int option;

        do {
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

        companyRepository.findAll();

        System.out.print("Digite o CNPJ da Companhia: ");
        String cnpjCompany = scanner.nextLine();
        Company company = getCompanyByCNPJ(cnpjCompany);

        System.out.print("O carro está alugado? (true/false): ");
        boolean isRented = scanner.nextBoolean();

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
        return company.orElse(null);
    }


}




