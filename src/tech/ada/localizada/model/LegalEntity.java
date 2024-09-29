package tech.ada.localizada.model;

public class LegalEntity extends Client {

    private String cnpj;
    public LegalEntity(String name, String email, String cnpj) {
        super(name, email);
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
}
