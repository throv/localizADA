package tech.ada.localizada.model;

public class Address {

    private String streetName;
    private String neighboorhood;

    private String city;

    private String state;
    private String cep;
    private int streetNumber;

    @Override
    public String toString() {
        return String.format("""
                Full address:
                %n %s, %s
                %s - %s, %s 
                """, streetNumber, streetName, neighboorhood, cep, city, state);
    }
}
