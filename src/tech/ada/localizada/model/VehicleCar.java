package tech.ada.localizada.model;

public class VehicleCar extends Vehicle {

    private int numberOfDoors;

    public VehicleCar (String model, int year, String plate,
                       boolean isVehicleRented, int numberOfDoors) {

        super(model, year, plate, isVehicleRented);
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

                = ---------=== Dados do Carro ===---------
                | Modelo: %s
                | Ano: %s
                | Placa: %s
                | Alugado: %s
                | Número de Portas: %s
                = ----------------------------------------
                """, getModel(), getYear(), getPlate(), (isVehicleRented() ? "Sim" : "Não"), numberOfDoors);

        return data;
    }
}



