package tech.ada.localizada.model;

public class VehicleCar extends Vehicle {

    private int numberOfDoors;

    public VehicleCar (String model, int year, String plate, Company findVehicleAgency,
                       boolean isVehicleRented, int numberOfDoors) {

        super(model, year, plate, findVehicleAgency, isVehicleRented);
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public String toString() {
        String data = String.format("""

                = --------=== Dados do Carro ===----------
                | ID: %s
                | Modelo: %s
                | Ano: %s
                | Placa: %s
                | Agência: %s
                | Alugado: %s
                | Número de Portas: %s
                = ----------------------------------------
                """, getId(), getModel(), getYear(), getPlate(), getFindVehicleAgency().getName(), (isVehicleRented() ? "Sim" : "Não"), numberOfDoors);

        return data;
    }
}



