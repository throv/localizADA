package ada.tech.localizada.model;

public class Agencia {
    private String nome;
    private String logradouro;
    private String cidade;
    private String telefone;
    private String cnpj;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Agencia(String nome, String logradouro, String cidade, String telefone, String cnpj){
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.telefone = telefone;
        this.cnpj = cnpj;



    }
}
