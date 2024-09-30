package tech.ada.localizada.view;

import tech.ada.localizada.model.*;
import tech.ada.localizada.service.vehicle.VehicleService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class VehicleSubMenu {

    private final VehicleService vehicleService;
    private final Scanner scanner = new Scanner(System.in);

    public VehicleSubMenu(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public void startMenuVehicle() {

        int option = 0;

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
                    | 5 - Localizar Veiculo                 |
                    | 6 - Sair                              |
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

            if (option < 1 || option > 6) {
                System.out.println("\nError: Please enter a valid option!");
            }

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
                    searchVehicle(vehicleService);
                    break;
                case 6:
                    System.out.println("\nSaindo");
                    break;
                default:
                    break;
            }

        } while (option != 6);
    }

    public void createVehicleSubMenu() {

        System.out.print("\nDigite o modelo do carro: ");
        String model = scanner.nextLine();

        int year = 0;
        boolean validInputYear = false;
        while (!validInputYear) {
            try {
                System.out.print("\nDigite o ano do carro: ");
                year = scanner.nextInt();
                validInputYear = true;
            } catch (InputMismatchException e) {
                System.out.println("Caracteres invalidos, digite apenas numeros");
                scanner.nextLine();
            }
        }

        scanner.nextLine();

        String plate = "0";
        boolean validInputPlate = false;
        while (!validInputPlate) {

            System.out.print("\nDigite a placa do carro: ");
            plate = scanner.nextLine().toUpperCase();

            boolean plateExists = false;

            for (Vehicle vehicle : vehicleService.listVehicle()) {
                if (vehicle.getPlate().equalsIgnoreCase(plate)) {
                    plateExists = true;
                    break;
                }
            }

            if (!plateExists) {
                validInputPlate = true;
            } else {
                System.out.println("Placa já registrada. Tente novamente.");
            }
        }


        System.out.print("\nO carro está alugado? (true/false): ");
        boolean isRented = scanner.nextBoolean();

        int vehicleType = 0;

        do {
            String options = """
                    
                    = ------------------------------------- =
                    |             Tipo Veículo              |
                    = ------------------------------------- =
                    
                    = ----------=== Escolher ===----------- =
                    | 1 - Carro                             |
                    | 2 - Moto                              |
                    | 3 - Caminhão                          |
                    = ------------------------------------- =
                    """;

            System.out.println(options);
            System.out.print("Escolha uma opção: ");

            try {
                vehicleType = Integer.parseInt(scanner.next());
                scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("\nError: Please enter a valid option!");
                continue;
            }

            if (vehicleType < 1 || vehicleType > 3) {
                System.out.println("\nError: Please enter a valid option!");
            }

            switch (vehicleType) {
                case 1:
                    createVehicleCarSubMenu(model,year,plate,isRented);
                    break;
                case 2:
                    createVehicleBikeSubMenu(model,year,plate,isRented);
                    break;
                case 3:
                    createVehicleTruckSubMenu(model,year,plate,isRented);
                    break;
                default:
                    break;
            }

        } while (vehicleType < 1 || vehicleType > 3);
    }

    public void createVehicleCarSubMenu(String model, int year,
                                        String plate, boolean isRented) {

        System.out.print("\nDigite o número de portas do carro: ");
        int numberOfDoors = scanner.nextInt();
        VehicleCar vehicleCar = new VehicleCar(model,year,plate,isRented,numberOfDoors);
        vehicleService.saveVehicle(vehicleCar);
        vehicleService.printVehicles();
        System.out.println("\nCARRO ADICIONADO COM SUCESSO!");
    }

    public void createVehicleBikeSubMenu(String model, int year,String plate,boolean isRented) {

        VehicleBike vehicleBike = new VehicleBike(model,year,plate,isRented);
        vehicleService.saveVehicle(vehicleBike);
        vehicleService.printVehicles();
        System.out.println("\nMOTO ADICIONADA COM SUCESSO!");

    }

    public void createVehicleTruckSubMenu(String model, int year,String plate,boolean isRented) {

        System.out.print("\nLimite de carga do caminhão: ");
        double loadTruck = scanner.nextDouble();

        VehicleTruck vehicleTruck = new VehicleTruck(model,year,plate,isRented,loadTruck);
        vehicleService.saveVehicle(vehicleTruck);
        vehicleService.printVehicles();
        System.out.println("\nCAMINHÃO ADICIONADO COM SUCESSO!");
    }

    public void updateVehicleSubMenu() {

        int option = 0;

        do {
            String options = """

                    = ------------------------------------- =
                    |           Editar Informação           |
                    = ------------------------------------- =
                    
                    = -----------=== Editar ===------------ =
                    | 1 - Modelo do veículo                 |
                    | 2 - Placa do veículo                  |
                    | 3 - Ano do veículo                    |
                    | 4 - Voltar                            |
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

            if (option < 1 || option > 4) {
                System.out.println("\nError: Please enter a valid option!");
            }
            switch (option) {
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
                    System.out.println("\nSaindo...");
                    break;
                default:
                    break;
            }

        } while (option != 4);
    }

    public void updateVehicleModelMenu(VehicleService vehicleService) {
        Vehicle vehicle = getVehicleFromUserInput();
        if (vehicle == null) {
            System.out.println("\nVEÍCULO NÂO ENCONTRADO!");
            return; }
        System.out.print("\nDigite o modelo do carro: ");
        String model = scanner.nextLine();
        vehicleService.updateModelVehicle(vehicle,model);
    }

    public void updateVehicleYearMenu(VehicleService vehicleService) {
        Vehicle vehicle = getVehicleFromUserInput();
        if (vehicle == null) {
            System.out.println("\nVEÍCULO NÂO ENCONTRADO!");
            return; }
        System.out.print("\nDigite o novo ano do carro: ");
        int newYear = Integer.parseInt(scanner.nextLine());
        vehicleService.updateVehicleYear(vehicle,newYear);
        vehicleService.listVehicle();
    }

    public void updateVehiclePlateMenu(VehicleService vehicleService) {
        Vehicle vehicle = getVehicleFromUserInput();
        if (vehicle == null) {
            System.out.println("\nVEÍCULO NÂO ENCONTRADO!");
            return; }
        System.out.print("\nDigite a nova placa do carro: ");
        String newPlate = scanner.nextLine().toUpperCase();
        vehicleService.updateVehiclePlate(vehicle,newPlate);
    }

    public void updateVehicleRented (VehicleService vehicleService) {
        Vehicle vehicle = getVehicleFromUserInput();
        if (vehicle == null) {
            System.out.println("\nVEÍCULO NÂO ENCONTRADO!");
            return; }
        System.out.print("\nDigite aqui é true ou false ? (true/false): ");
        boolean isRented = scanner.nextBoolean();
        vehicleService.updateVehicleRented(vehicle, isRented);
    }

    public void deleteVehicle(VehicleService vehicleService) {
        Vehicle vehicle = getVehicleFromUserInput();
        if (vehicle == null) {
            System.out.println("\nVEÍCULO NÂO ENCONTRADO!");
            return; }
        vehicleService.deleteVehicle(vehicle.getId());
        System.out.println("\nVEÍCULO EXCLUÍDO COM SUCESSO!");
    }

    public void searchVehicle(VehicleService vehicleService) {
        Vehicle vehicle = getVehicleFromUserInput();
        if (vehicle == null) {
            System.out.println("\nVEÍCULO NÂO ENCONTRADO!");
            return; }
        System.out.println(vehicle);
    }

    public Vehicle getVehicleFromUserInput() {
        System.out.print("\nDigite a placa do carro: ");
        String plate = scanner.nextLine().toUpperCase();
        Vehicle vehicle = getVehiclebyPlate(plate);
        return vehicle;
    }

    private Vehicle getVehiclebyPlate(String plate) {
        for (Vehicle vehicle : vehicleService.listVehicle()) {
            if (vehicle.getPlate().equalsIgnoreCase(plate)) {
                return vehicle; }
        } return null;
    }






}




