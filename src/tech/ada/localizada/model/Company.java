package tech.ada.localizada.model;

public class Company {

    private int id;
    private String name;
    private String address;
    private String city;
    private String cnpj;

    public Company(String name, String address, String city, String cnpj) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.cnpj = cnpj;
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

                = --------=== Dados da Agência ===----------
                | ID: %s
                | Nome: %s
                | Endereço: %s
                | Cidade: %s
                | CNPJ: %s
                = ------------------------------------------
                """, getId(), getName(), getAddress(), getCity(), getCnpj());

        return data;
    }
}
