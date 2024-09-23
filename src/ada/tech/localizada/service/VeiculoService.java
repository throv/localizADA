package ada.tech.localizada.service;

import ada.tech.localizada.model.Veiculo;

public class VeiculoService implements Service<Veiculo>{

    @Override
    public void criar(Veiculo veiculo) {
        // ToDo
    }

    @Override
    public Veiculo buscar(String nome) {
        // ToDo
        return null;
    }

    // extra no requisito veiculo, nao esta no contrato interface
    public Veiculo buscarPelaPlaca (String placa){
        // ToDo
        return null;
    }

    @Override
    public void editar(String valor) {
        // ToDo
    }

    @Override
    public void excluir(String valor) {
        // ToDo
    }
}
