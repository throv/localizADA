package tech.ada.localizada.model;

public abstract class Client {
    private String name;
    private String email;
    private Address address;

    protected Client(String name, String email) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
