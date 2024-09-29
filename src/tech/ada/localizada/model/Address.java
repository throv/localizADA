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
        String data = String.format("""

                = --------=== Dados do Endereço ===----------
                | Cidade: %s
                | Estado: %s
                | Numero: %s
                | Rua: %s
                | CEP: %s
                = ----------------------------------------
                """, city, state, streetNumber, streetName, cep);

        return data;
    }
}
