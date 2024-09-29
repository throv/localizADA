package tech.ada.localizada.view;

import tech.ada.localizada.model.Client;
import tech.ada.localizada.model.LegalEntity;
import tech.ada.localizada.model.NaturalPerson;
import tech.ada.localizada.service.client.ClientService;
import tech.ada.localizada.util.Util;

import java.util.Scanner;

public class ClientSubMenu {

    private final ClientService clientService;
    private final Scanner scanner = new Scanner(System.in);

    public ClientSubMenu(ClientService clientService) {
        this.clientService = clientService;
    }

    public void startMenuClient() {

        int option;

        do {
            String options = """
                    
                    = ------------------------------------- =
                    |             Menu Clientes             |
                    = ------------------------------------- =
                    
                    = ------------=== Menu ===------------- =
                    | 1 - Cadastrar cliente                 |
                    | 2 - Alterar cliente                   |
                    | 3 - Sair                              |
                    = ------------------------------------- =
                    """;

            System.out.println(options);
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createClientSubMenu();
                    break;
                case 2:
                    updateClientSubMenu();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (option != 3);
    }

    public void createClientSubMenu() {

        int option = 0;

        do {
            String options = """
                    
                    = ------------------------------------- =
                    |             Menu Clientes             |
                    = ------------------------------------- =
                    
                    = ----------=== Cadastrar ===---------- =
                    | 1 - Pessoa Física                     |
                    | 2 - Pessoa Jurídica                   |
                    | 3 - Sair                              |
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

            if (option < 1 || option > 3) {
                System.out.println("\nError: Please enter a valid option!");
            }

            switch (option) {
                case 1:
                    createClientNPMenu();
                    break;
                case 2:
                    createClientLEMenu();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (option != 3);
    }

    public void createClientNPMenu() {

        System.out.print("\nDigite o nome do cliente (PF): ");
        String name = scanner.nextLine();

        System.out.print("\nDigite o e-mail do cliente (PF): ");
        String email = scanner.nextLine();

        System.out.print("\nDigite o telefone do cliente (PF): ");
        String phone = scanner.nextLine();

        System.out.print("\nDigite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        NaturalPerson naturalPerson = new NaturalPerson(name, email, phone, cpf);
        clientService.createClient(naturalPerson);
        System.out.println("\nCLIENTE (PESSOA FÍSICA) ADICIONADO COM SUCESSO!");
    }

    public void createClientLEMenu() {

        System.out.print("\nDigite o nome do cliente (PJ): ");
        String name = scanner.nextLine();

        System.out.print("\nDigite o e-mail do cliente (PJ): ");
        String email = scanner.nextLine();

        System.out.print("\nDigite o telefone do cliente (PJ): ");
        String phone = scanner.nextLine();

        System.out.print("\nDigite o CNPJ do cliente: ");
        String cnpj = scanner.nextLine();

        LegalEntity legalEntity = new LegalEntity(name, email, phone, cnpj);
        clientService.createClient(legalEntity);
        System.out.println("\nCLIENTE (PESSOA JURÍDICA) ADICIONADO COM SUCESSO!");
    }

    public void updateClientSubMenu() {

        clientService.printClients();
        System.out.print("\nDigite o CPF/CNPJ do cliente que deseja atualizar: ");
        String cpfOrCnpj = Util.validateCnpjAndCpf(scanner.nextLine());

        Client client = clientService.getClientById(cpfOrCnpj);

        if (client == null) {
            System.out.println("\nCliente com o CPF/CNPJ " + cpfOrCnpj + " não foi encontrado.");
            return;
        }

        if (client instanceof NaturalPerson) {
            updateClientNP((NaturalPerson) client);
        } else if (client instanceof LegalEntity) {
            updateClientLE((LegalEntity) client);
        } else {
            System.out.println("\nTipo de cliente desconhecido.");
        }
    }

    private void updateClientNP(NaturalPerson client) {

        System.out.print("\nDigite o novo nome do cliente (PF): ");
        String name = scanner.nextLine();

        System.out.print("\nDigite o novo e-mail do cliente (PF): ");
        String email = scanner.nextLine();

        System.out.print("\nDigite o novo telefone do cliente (PF): ");
        String phone = scanner.nextLine();

        System.out.print("\nDigite o novo CPF do cliente: ");
        String cpf = Util.validateCnpjAndCpf(scanner.nextLine());

        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);
        client.setCpf(cpf);

        clientService.updateClient(client.getId(), client);
        System.out.println("\nCLIENTE (PESSOA FÍSICA) ATUALIZADO COM SUCESSO!");
    }

    private void updateClientLE(LegalEntity client) {

        System.out.print("\nDigite o novo nome do cliente (PJ): ");
        String name = scanner.nextLine();

        System.out.print("\nDigite o novo e-mail do cliente (PJ): ");
        String email = scanner.nextLine();

        System.out.print("\nDigite o novo telefone do cliente (PJ): ");
        String phone = scanner.nextLine();

        System.out.print("\nDigite o novo CNPJ do cliente: ");
        String cnpj = Util.validateCnpjAndCpf(scanner.nextLine());

        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);
        client.setCnpj(cnpj);

        clientService.updateClient(client.getId(), client);
        System.out.println("\nCLIENTE (PESSOA JURÍDICA) ATUALIZADO COM SUCESSO!");
    }
}
