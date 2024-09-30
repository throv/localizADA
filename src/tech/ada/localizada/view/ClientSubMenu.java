package tech.ada.localizada.view;

import tech.ada.localizada.exception.ClientAlreadyExistsException;
import tech.ada.localizada.exception.ClientNotFoundException;
import tech.ada.localizada.exception.DocumentNotAcceptedException;
import tech.ada.localizada.model.Client;
import tech.ada.localizada.model.LegalEntity;
import tech.ada.localizada.model.NaturalPerson;
import tech.ada.localizada.service.client.ClientService;
import tech.ada.localizada.util.Util;

import java.util.Scanner;
import java.util.List;

public class ClientSubMenu {

    private final ClientService clientService;
    private final Scanner scanner = new Scanner(System.in);

    public ClientSubMenu(ClientService clientService) {
        this.clientService = clientService;
    }

    public void startMenuClient() {

        int option = 0;

        do {
            String options = """
                    
                    = ------------------------------------- =
                    |             Menu Cliente              |
                    = ------------------------------------- =
                    
                    = ------------=== Menu ===------------- =
                    | 1 - Cadastrar cliente                 |
                    | 2 - Alterar cliente                   |
                    | 3 - Excluir cliente                   |
                    | 4 - Buscar cliente (CPF/CNPJ)         |
                    | 5 - Buscar cliente (Nome)             |
                    | 6 - Listar clientes                   |
                    | 7 - Sair                              |
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

            if (option < 1 || option > 7) {
                System.out.println("\nError: Please enter a valid option!");
            }

            switch (option) {
                case 1:
                    createClientSubMenu();
                    break;
                case 2:
                    updateClientSubMenu();
                    break;
                case 3:
                    deleteClientSubMenu();
                    break;
                case 4:
                    searchClientSubMenu();
                    break;
                case 5:
                    searchClientByName();
                    break;
                case 6:
                    listClientsSubMenu();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    break;
            }

        } while (option != 7);
    }

    public void createClientSubMenu() {

        int option = 0;

        do {
            String options = """
                    
                    = ------------------------------------- =
                    |             Menu Cliente              |
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
                    break;
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

        try {
            NaturalPerson naturalPerson = new NaturalPerson(name, email, phone, cpf);
            clientService.createClient(naturalPerson);
            System.out.println("\nCLIENTE (PESSOA FÍSICA) ADICIONADO COM SUCESSO!");
        } catch (ClientAlreadyExistsException e)  {
            System.out.println("Erro: " + e.getMessage());
        } catch (IllegalArgumentException | DocumentNotAcceptedException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        }
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

        try {
            LegalEntity legalEntity = new LegalEntity(name, email, phone, cnpj);
            clientService.createClient(legalEntity);
            System.out.println("\nCLIENTE (PESSOA JURÍDICA) ADICIONADO COM SUCESSO!");
        } catch (ClientAlreadyExistsException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (IllegalArgumentException | DocumentNotAcceptedException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        }
    }

    public void updateClientSubMenu() {

        clientService.printClients();
        System.out.print("\nDigite o CPF/CNPJ do cliente que deseja atualizar: ");
        String cpfOrCnpj = Util.validateCnpjAndCpf(scanner.nextLine());

        try {
            Client client = clientService.getClientById(cpfOrCnpj);

            if (client instanceof NaturalPerson) {
                updateClientNP((NaturalPerson) client);
            } else if (client instanceof LegalEntity) {
                updateClientLE((LegalEntity) client);
            } else {
                System.out.println("\nTipo de cliente desconhecido.");
            }
        } catch (ClientNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void updateClientNP(NaturalPerson client) {

        try {
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
        } catch (IllegalArgumentException | DocumentNotAcceptedException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        }

    }

    private void updateClientLE(LegalEntity client) {

        try {
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
        } catch (IllegalArgumentException | DocumentNotAcceptedException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        }
    }

    public void deleteClientSubMenu() {

        clientService.printClients();
        System.out.print("\nDigite o CPF/CNPJ do cliente que deseja excluir: ");
        String cpfOrCnpj = Util.validateCnpjAndCpf(scanner.nextLine());

        try {
            Client client = clientService.getClientById(cpfOrCnpj);

            if (client == null) {
                System.out.println("\nCliente com o CPF/CNPJ " + cpfOrCnpj + " não foi encontrado.");
                return;
            }

            clientService.deleteClient(client.getId());
            System.out.println("\nCLIENTE EXCLUÍDO COM SUCESSO!");
        } catch (ClientNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void searchClientSubMenu() {

        System.out.print("\nDigite o CPF/CNPJ do cliente que deseja buscar: ");
        String cpfOrCnpj = Util.validateCnpjAndCpf(scanner.nextLine());

        try {
            Client client = clientService.getClientById(cpfOrCnpj);
            System.out.println("\nCliente encontrado:");
            System.out.println(client);
        } catch (ClientNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void searchClientByName() {
        System.out.print("Digite o nome que deseja buscar: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Erro: O nome não pode estar vazio.");
            return;
        }

        List<Client> clients = clientService.searchClientByName(name);

        if (clients.isEmpty()) {
            System.out.println("Nenhum cliente encontrado com o nome: " + name);
        } else {
            System.out.println("Clientes encontrados:");
            for (Client client : clients) {
                System.out.println(client);
            }
        }
    }

    public void listClientsSubMenu() {
        clientService.printClients();
    }

    //    public void searchClientSubMenu() {
//
//        int option = 0;
//
//        do {
//            String options = """
//
//                    = ------------------------------------- =
//                    |             Menu Cliente              |
//                    = ------------------------------------- =
//
//                    = -----------=== Buscar ===------------ =
//                    | 1 - Documento                         |
//                    | 2 - Nome                              |
//                    | 3 - Sair                              |
//                    = ------------------------------------- =
//                    """;
//
//            System.out.println(options);
//            System.out.print("Escolha uma opção: ");
//            String optionString = scanner.next();
//
//            try {
//                option = Integer.parseInt(optionString);
//                scanner.nextLine();
//            } catch (NumberFormatException e) {
//                System.out.println("\nError: Please enter a valid option!");
//                continue;
//            }
//
//            if (option < 1 || option > 3) {
//                System.out.println("\nError: Please enter a valid option!");
//            }
//
//            switch (option) {
//                case 1:
//                    //searchClientByDocument();
//                    break;
//                case 2:
//                    //searchClientByName();
//                    break;
//                case 3:
//                    System.out.println("Saindo...");
//                    break;
//                default:
//                    break;
//            }
//
//        } while (option != 3);
//    }
}
