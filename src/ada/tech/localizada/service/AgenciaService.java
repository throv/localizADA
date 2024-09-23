package ada.tech.localizada.service;

import ada.tech.localizada.model.Agencia;

import java.util.ArrayList;

public class AgenciaService implements Service<Agencia>{
    ArrayList<Agencia>agencia = new ArrayList<>();


    @Override
    public void criar(Agencia agencia) {
// ToDO
    }

    @Override
    public Agencia buscar(String nome) {
        return null;
    }

    @Override
    public void editar(String cnpj) {

    }

    @Override
    public void excluir(String cnpj) {

    }
}
