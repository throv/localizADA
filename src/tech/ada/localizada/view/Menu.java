package tech.ada.localizada.view;

import java.util.Scanner;

public class Menu {

    private final Scanner input;

    public Menu() {
        this.input = new Scanner(System.in);
    }

    public void startMenu() {

        int option = 0;

        do {

            String options = """

                    = ------------------------------- =
                    |   Sistema Aluguel de Veículos   |
                    = ------------------------------- =
                    
                    = ---------=== Menu ===---------- =
                    | 1 - Cliente                     |
                    | 2 - Veículo                     |
                    | 3 - Agência                     |
                    | 4 - Aluguel                     |
                    | 5 - Sair                        |
                    = ------------------------------- =
                    """;

            System.out.println(options);
            System.out.print("Digite uma opção: ");
            String optionString = input.next();

            try {
                option = Integer.parseInt(optionString);
                input.nextLine();
            } catch (NumberFormatException e) {
                System.err.println("Error: Please enter a valid option!\n");
                continue;
            }

            if (option < 1 || option > 5) {
                System.err.println("Error: Please enter a valid option!\n");
            }

            switch (option) {
                case 1:
                    System.out.println("Client menu");
                    break;
                case 2:
                    System.out.println("Vehicle menu");
                    break;
                case 3:
                    System.out.println("Company menu");
                    break;
                case 4:
                    System.out.println("Rent menu");
                    break;
                case 5:
                    System.out.println("Leaving...");
                    break;
                default:
                    break;
            }

        } while (option != 5);

    }
}