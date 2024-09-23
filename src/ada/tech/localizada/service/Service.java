package ada.tech.localizada.service;

import ada.tech.localizada.model.Agencia;

public interface Service <T>{

    public void criar(T model);

    public T buscar(String valor);

    public void editar(String valor);

    public void excluir(String valor);
}

