package tech.ada.localizada.model;

public abstract class Client {
    private String name;
    private String email;
    private String phone;

    protected Client(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
}

    @Override
    public String toString() {
        String data = String.format("""

                = --------=== Dados do Cliente ===--------
                | Id: %s
                | Nome: %s
                | Email: %s
                | Telefone: %s
                = ----------------------------------------
                """, getId(),  name, email, phone);
        return data;
    }
}
