package tech.ada.localizada.repository.client;

import tech.ada.localizada.model.Client;
import tech.ada.localizada.repository.Repository;

import java.util.List;

public interface ClientRepository extends Repository<Client, Integer> {

    //ADICIONAR MÉTODOS ESPECÍFICOS DE CLIENTE
    //EXEMPLO: BUSCAR POR NOME
    List<Client> findByName(String name);

}
