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
        int option;

        do {
            System.out.println("Menu:");
            System.out.println("1 - Listar veículos da empresa");
            System.out.println("2 - Adicionar veículo à empresa");
            System.out.println("3 - Remover veículo da empresa");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    listVehicles();
                    break;
                case 2:
                    System.out.print("Informe o ID do veículo: ");
                    int vehicleId = scanner.nextInt();
                    scanner.nextLine();
                    Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
                    if (vehicle != null) {
                        addVehicle(vehicle);
                    } else {
                        System.out.println("Veículo não encontrado.");
                    }
                    break;
                case 3:
                    removeVehicle();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (option != 4);
    }

    public void listVehicles() {
        System.out.print("Informe o ID da empresa: ");
        int companyId = scanner.nextInt();
        scanner.nextLine();
        Company company = companyService.getAllCompanies().stream()
                .filter(c -> c.getId() == companyId)
                .findFirst()
                .orElse(null);
        if (company != null) {
            System.out.println("Veículos da empresa:");
            for (Vehicle vehicle : company.getVehicles()) {
                System.out.println("- " + vehicle.getModel() + " (" + vehicle.getPlate() + ")");
            }
        } else {
            System.out.println("Empresa não encontrada.");
        }
    }

    public void addVehicle(Vehicle vehicle) {
        System.out.print("Informe o ID da empresa: ");
        int companyId = scanner.nextInt();
        scanner.nextLine();
        Company company = companyService.getAllCompanies().stream()
                .filter(c -> c.getId() == companyId)
                .findFirst()
                .orElse(null);
        if (company != null) {
            company.addVehicle(vehicle);
            companyService.updateCompany(companyId, company);
            System.out.println("Veículo adicionado com sucesso!");
        } else {
            System.out.println("Empresa não encontrada.");
        }
    }

    public void removeVehicle() {
        System.out.print("Informe o ID da empresa: ");
        int companyId = scanner.nextInt();
        scanner.nextLine();
        Company company = companyService.getAllCompanies().stream()
                .filter(c -> c.getId() == companyId)
                .findFirst()
                .orElse(null);
        if (company != null) {
            System.out.print("Informe a placa do veículo: ");
            String vehiclePlate = scanner.nextLine();
            Vehicle vehicleToRemove = vehicleService.getVehicleByPlate(vehiclePlate);
            if (vehicleToRemove != null) {
                int vehicleId = vehicleToRemove.getId();
                company.removeVehicle(vehicleToRemove);
                companyService.updateCompany(companyId, company);
                vehicleService.deleteVehicle(vehicleId);
                System.out.println("Veículo removido com sucesso!");
            } else {
                System.out.println("Veículo não encontrado.");
            }
        } else {
            System.out.println("Empresa não encontrada.");
        }
    }
}