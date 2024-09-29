package tech.ada.localizada.model;

public class VehicleTruck extends Vehicle {

    private double load;

    public VehicleTruck(String model, int year, String plate, Company findVehicleAgency, boolean isVehicleRented, double load) {
        super(model, year, plate, findVehicleAgency, isVehicleRented);
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
                | ID: %s
                | Modelo: %s
                | Ano: %s
                | Placa: %s
                | Agência: %s
                | Alugado: %s
                | Carga: %s
                = ----------------------------------------
                """, getId(), getModel(), getYear(), getPlate(), getFindVehicleAgency().getName(), (isVehicleRented() ? "Sim" : "Não"), load);

        return data;
    }
}
