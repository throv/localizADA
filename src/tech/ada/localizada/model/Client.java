package tech.ada.localizada.model;

public abstract class Client {
    private String name;
    private String email;
    private Address address;


    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public abstract String getId();
    public abstract void setDocument(String document);
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        String data = String.format("""

                = --------=== Dados do Cliente ===----------
                | Nome: %s
                | Email: %s
                | Endereço: %s
                = ----------------------------------------
                """, name, email, address);
        return data;
    }
}
