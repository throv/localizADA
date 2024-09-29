package tech.ada.localizada.model;

public class NaturalPerson extends Client{

    private String cpf;
    public NaturalPerson(String name, String email, String cpf) {
        super(name, email);
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
}
