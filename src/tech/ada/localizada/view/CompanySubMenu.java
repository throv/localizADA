package tech.ada.localizada.view;

import tech.ada.localizada.model.Company;
import tech.ada.localizada.model.Vehicle;
import tech.ada.localizada.service.company.CompanyService;
import tech.ada.localizada.service.vehicle.VehicleService;

import java.util.List;
import java.util.Scanner;

public class CompanySubMenu {
    private CompanyService companyService;
    private VehicleService vehicleService;
    private static Scanner scanner = new Scanner(System.in);

    public CompanySubMenu(CompanyService companyService, VehicleService vehicleService) {
        this.companyService = companyService;
        this.vehicleService = vehicleService;
    }

    public void showMenu() {

        int option = 0;

        do {
            String options = """
                    
                    = ------------------------------------- =
                    |              Menu Agência             |
                    = ------------------------------------- =
                    
                    = ------------=== Menu ===------------- =
                    | 1 - Adicionar veículo à empresa       |
                    | 2 - Remover veículo da empresa        |
                    | 3 - Listar veículos da empresa        |
                    | 4 - Sair                              |
                    = ------------------------------------- =
                    """;

            System.out.println(options);
            System.out.print("Escolha uma opção: ");
            String optionString = scanner.next();

            try {
                option = Integer.parseInt(optionString);
                scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("\nError: Please enter a valid option!");
                continue;
            }

            if (option < 1 || option > 4) {
                System.out.println("\nError: Please enter a valid option!");
            }

            switch (option) {
                case 1:
                    listVehicles();
                    break;
                case 2:
                    System.out.print("\nInforme o ID do veículo: ");
                    int vehicleId = scanner.nextInt();
                    scanner.nextLine();
                    Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
                    if (vehicle != null) {
                        addVehicle(vehicle);
                    } else {
                        System.out.println("\nVehicle not found.");
                    }
                    break;
                case 3:
                    removeVehicle();
                    break;
                case 4:
                    System.out.println("\nSaindo...");
                    break;
                default:
                    break;
            }

        } while (option != 4);
    }

    public void listVehicles() {
        System.out.print("\nInforme o ID da empresa: ");
        int companyId = scanner.nextInt();
        scanner.nextLine();
        Company company = companyService.getAllCompanies().stream()
                .filter(c -> c.getId() == companyId)
                .findFirst()
                .orElse(null);
        if (company != null) {
            System.out.println("\nVeículos da empresa:");
            for (Vehicle vehicle : company.getVehicles()) {
                System.out.println("- " + vehicle.getModel() + " (" + vehicle.getPlate() + ")");
            }
        } else {
            System.out.println("Company not found.");
        }
    }

    public void addVehicle(Vehicle vehicle) {
        System.out.print("\nInforme o ID da empresa: ");
        int companyId = scanner.nextInt();
        scanner.nextLine();
        Company company = companyService.getAllCompanies().stream()
                .filter(c -> c.getId() == companyId)
                .findFirst()
                .orElse(null);
        if (company != null) {
            company.addVehicle(vehicle);
            companyService.updateCompany(companyId, company);
            System.out.println("VEÍCULO ADICIONADO COM SUCESSO!");
        } else {
            System.out.println("Company not found.");
        }
    }

    public void removeVehicle() {
        System.out.print("\nInforme o ID da empresa: ");
        int companyId = scanner.nextInt();
        scanner.nextLine();
        Company company = companyService.getAllCompanies().stream()
                .filter(c -> c.getId() == companyId)
                .findFirst()
                .orElse(null);
        if (company != null) {
            System.out.print("\nInforme a placa do veículo: ");
            String vehiclePlate = scanner.nextLine();
            Vehicle vehicleToRemove = vehicleService.getVehicleByPlate(vehiclePlate);
            if (vehicleToRemove != null) {
                int vehicleId = vehicleToRemove.getId();
                company.removeVehicle(vehicleToRemove);
                companyService.updateCompany(companyId, company);
                vehicleService.deleteVehicle(vehicleId);
                System.out.println("VEÍCULO REMOVIDO COM SUCESSO!");
            } else {
                System.out.println("Vehicule not found.");
            }
        } else {
            System.out.println("Company not found.");
        }
    }
}