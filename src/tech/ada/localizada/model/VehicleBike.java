package tech.ada.localizada.model;

public class VehicleBike extends Vehicle {

    public VehicleBike(String model, int year, String plate, boolean isVehicleRented) {
        super(model, year, plate, isVehicleRented);
    }

    @Override
    public String toString() {
        String data = String.format("""

                = ---------=== Dados da Moto ===----------
                | Modelo: %s
                | Ano: %s
                | Placa: %s
                | Alugado: %s
                = ----------------------------------------
                """, getModel(), getYear(), getPlate(), (isVehicleRented() ? "Sim" : "Não"));

        return data;
    }
}
