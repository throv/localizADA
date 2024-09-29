package tech.ada.localizada.model;

import tech.ada.localizada.util.Util;

public class LegalEntity extends Client {

    private String cnpj;
    public LegalEntity(String name, String email, String phone, String cnpj) {
        super(name, email, phone);
        this.cnpj = cnpj;
    }

    @Override
    public String getId() {
        return cnpj;
    }

    @Override
    public void setDocument(String document) {
        this.cnpj = document;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        String data = String.format("""

                = --------=== Dados do Cliente ===--------
                | Id: %s
                | Nome: %s
                | Email: %s
                | Telefone: %s
                | CNPJ: %s
                = ----------------------------------------
                """, getId(), getName(), getEmail(), Util.formatPhone(getPhone()), Util.formatCnpj(getCnpj()));
        return data;
    }
}
