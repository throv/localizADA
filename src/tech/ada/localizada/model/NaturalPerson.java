package tech.ada.localizada.model;

import tech.ada.localizada.util.Util;

public class NaturalPerson extends Client{

    private String cpf;
    public NaturalPerson(String name, String email, String phone, String cpf) {
        super(name, email, phone);
        this.cpf = cpf;
    }

    @Override
    public String getId() {
        return cpf;
    }

    @Override
    public void setDocument(String document) {
        this.cpf = document;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        String data = String.format("""

                = --------=== Dados do Cliente ===--------
                | Nome: %s
                | Email: %s
                | Telefone: %s
                | CPF: %s
                = ----------------------------------------
                """, getName(), getEmail(), Util.formatPhone(getPhone()), Util.formatCpf(getCpf()));
        return data;
    }
}
