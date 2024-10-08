package tech.ada.localizada.model;

public class VehicleTruck extends Vehicle {

    private double load;

    public VehicleTruck(String model, int year, String plate,
                        boolean isVehicleRented, double load) {
        super(model, year, plate, isVehicleRented);
        this.load = load;
    }

    public double getLoad() {
        return load;
    }

    public void setLoad(double load) {
        this.load = load;
    }

    @Override
    public String toString() {
        String data = String.format("""

                = -------=== Dados do Caminhão ===--------
                | Modelo: %s
                | Ano: %s
                | Placa: %s
                | Alugado: %s
                | Carga: %s toneladas
                = ----------------------------------------
                """, getModel(), getYear(), getPlate(), (isVehicleRented() ? "Sim" : "Não"), load);

        return data;
    }
}
