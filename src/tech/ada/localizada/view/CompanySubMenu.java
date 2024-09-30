package tech.ada.localizada.view;

import tech.ada.localizada.model.Company;
import tech.ada.localizada.model.Vehicle;
import tech.ada.localizada.service.company.CompanyService;
import tech.ada.localizada.service.vehicle.VehicleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
                    | 1 - Adicionar Agência                 |
                    | 2 - Editar Agência                    |
                    | 3 - Excluir Agência                   |
                    | 4 - Buscar Agência                    |
                    | 5 - Listar Veículos da Agência        |
                    | 6 - Adicionar Veículo à Agência       |
                    | 7 - Remover Veículo da Agência        |
                    | 8 - Sair                              |
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

            if (option < 1 || option > 8) {
                System.out.println("\nError: Please enter a valid option!");
            }

            switch (option) {
                case 1:
                    createCompany();
                    break;
                case 2:
                    updateComapanySubMenu();
                    break;
                case 3:
                    deleteCompany();
                    break;
                case 4:
                    searchCompanyMenu();
                    break;
                case 5:
                    listVehicles();
                    break;
                case 6:
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
                case 7:
                    removeVehicle();
                    break;
                case 8:
                    System.out.println("Saindo...");
                    break;
                default:
                    break;
            }

        } while (option != 8);
    }

    public void createCompany() {

        System.out.print("\nDigite o nome da empresa: ");
        String name = scanner.nextLine();

        System.out.print("\nDigite o endereço da empresa: ");
        String address = scanner.nextLine();

        System.out.print("\nDigite a cidade da empresa: ");
        String city = scanner.nextLine();

        System.out.print("\nDigite o CNPJ da empresa: ");
        String cnpj = scanner.nextLine();

        Company company = new Company(name,address,city,cnpj);

        companyService.addCompany(company);

    }

    public void updateComapanySubMenu() {

        int option = 0;

        do {
            String options = """

                    = ------------------------------------- =
                    |     Qual informação deseja editar?    |
                    = ------------------------------------- =
                    
                    = -----------=== Editar ===------------ =
                    | 1 - Nome da Empresa                   |
                    | 2 - Endereço da Empresa               |
                    | 3 - Cidade da Empresa                 |
                    | 4 - CNPJ da Empresa                   |
                    | 5 - Voltar                            |
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

            if (option < 1 || option > 5) {
                System.out.println("\nError: Please enter a valid option!");
            }

            switch (option) {
                case 1:
                    updateCompanyName(companyService);
                    break;
                case 2:
                    updateCompanyAndress(companyService);
                    break;
                case 3:
                    updateCompanyCity(companyService);
                    break;
                case 4:
                    updateCompanyCNPJ(companyService);
                    break;
                case 5:
                    System.out.println("\nSaindo...");
                    break;
                default:
                    break;
            }

        } while (option != 5);
    }

    public void updateCompanyName (CompanyService companyService) {
        Company company = getCompanyFromUserInput().orElse(null);
        System.out.print("\nDigite o novo Nome: ");
        String name = scanner.nextLine();
        company.setAddress(name);
    }

    public void updateCompanyAndress (CompanyService companyService) {
        Company company = getCompanyFromUserInput().orElse(null);
        System.out.print("\nDigite o novo endereço: ");
        String anderess = scanner.nextLine();
        company.setAddress(anderess);
    }

    public void updateCompanyCity (CompanyService companyService) {
        Company company = getCompanyFromUserInput().orElse(null);
        System.out.print("\nDigite a nova Cidade: ");
        String anderess = scanner.nextLine();
        company.setCity(anderess);
    }

    public void updateCompanyCNPJ (CompanyService companyService) {
        Company company = getCompanyFromUserInput().orElse(null);
        System.out.print("\nDigite o novo CNPJ: ");
        String cnpj = scanner.nextLine();
        company.setCnpj(cnpj);
    }

    public void deleteCompany () {
        System.out.print("\nDigite o CNPJ da empresa: ");
        String cnpj = scanner.nextLine();
        Optional<Company> company = companyService.findCompanyByCNPJ(cnpj);
        companyService.deleteCompany(company.get().getId());
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
            System.out.println("\nCompany not found.");
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
            System.out.println("\nVEÍCULO ADICIONADO COM SUCESSO!");
        } else {
            System.out.println("\nCompany not found.");
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
                System.out.println("\nVEÍCULO REMOVIDO COM SUCESSO!");
            } else {
                System.out.println("\nVehicule not found.");
            }
        } else {
            System.out.println("\nCompany not found.");
        }
    }

    public void searchCompanyMenu() {

        int option = 0;

        do {
            String options = """

                    = ------------------------------------- =
                    |     Qual informação deseja editar?    |
                    = ------------------------------------- =
                    
                    = -----------=== Editar ===------------ =
                    | 1 - Pesquisar pelo Nome               |
                    | 2 - Pesquisar pelo CNPJ               |
                    | 3 - Voltar                            |
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
                    findCompanyName(companyService);
                    break;
                case 2:
                    findCompanyCNPJ(companyService);
                    break;
                case 3:
                    System.out.println("\nSaindo...");
                    break;
                default:
                    break;
            }

        } while (option != 3);
    }

    public void findCompanyCNPJ (CompanyService companyService) {
        Optional <Company> company = getCompanyFromUserInput();
        System.out.println(company);
    }

    public void findCompanyName(CompanyService companyService) {
        System.out.print("\nDigite parte do nome da empresa: ");
        String namePart = scanner.nextLine();

        List <Company> companies = findCompaniesByNamePart(namePart, companyService);

        if (companies.isEmpty()) {
            System.out.println("\nNenhuma empresa encontrada com esse nome.");
        } else {
            System.out.println("\nEmpresas encontradas:");
            for (Company company : companies) {
                System.out.println(company);
            }
        }
    }

    public List<Company> findCompaniesByNamePart (String namePart, CompanyService companyService) {
        List<Company> matchingCompanies = new ArrayList<>();
        for (Company company : companyService.getAllCompanies()) {
            if (company.getName().toLowerCase().contains(namePart.toLowerCase())) {
                matchingCompanies.add(company);
            }
        }
        for (Company company : companyService.getAllCompanies()) {
            if (company.getAddress().toLowerCase().contains(namePart.toLowerCase())) {
                matchingCompanies.add(company);
            }
        }
        return matchingCompanies;
    }

    public Optional <Company> getCompanyFromUserInput() {
        System.out.print("\nDigite o CNPJ da Empresa: ");
        String cnpj = scanner.nextLine();
        return companyService.findCompanyByCNPJ(cnpj);
    }
}