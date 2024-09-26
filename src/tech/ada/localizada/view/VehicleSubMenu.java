package tech.ada.localizada.view;

import tech.ada.localizada.model.Company;
import tech.ada.localizada.model.VehicleCar;
import tech.ada.localizada.repository.company.CompanyRepositoryImpl;
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
                    creatVehicleSubMenu();
                    break;
                case 2:
                    // Chamar método para editar carro
                    break;
                case 3:
                    // Chamar método para excluir carro
                    break;
                case 4:
                    // Chamar método para mudar disponibilidade do veículo
                    break;
                case 5:
                    // Chamar método para atualizar localização do carro
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (option != 6);
    }

    public void creatVehicleSubMenu() {

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
                    creatVehicleCarSubMenu(model,year,plate,company,isRented);
                    break;
                case 2:
                    creatVehicleBikeSubMenu(model,year,plate,company,isRented);
                    break;
                case 3:
                    creatVehicleTruckSubMenu(model,year,plate,company,isRented);
                    break;
                default:
            }

        } while (vehicleType < 1 || vehicleType > 3);
    }

    public void creatVehicleCarSubMenu(String model, int year,String plate,
                                       Company company,boolean isRented) {

        System.out.print("Digite o número de portas do carro: ");
        int numberOfDoors = scanner.nextInt();

        VehicleCar vehicleCar = new VehicleCar(model,year,plate,company,isRented,numberOfDoors);

        vehicleService.saveVehicle(vehicleCar);

    }

    public void creatVehicleBikeSubMenu(String model, int year,String plate,
                                       Company company,boolean isRented) {

    }

    public void creatVehicleTruckSubMenu(String model, int year,String plate,
                                        Company company,boolean isRented) {

    }











    private Company getCompanyByCNPJ(String cnpjCompany) {
        Optional<Company> company = companyRepository.findCompanyByCNPJ(cnpjCompany);
        return company.orElse(null);
    }


}




