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
        return "VehicleCar {" +
                "ID: " + getId() + "\n" +
                "Model: " + getModel() + "\n" +
                "Year: " + getYear() + "\n" +
                "Plate: " + getPlate() + "\n" +
                "Rented: " + (isVehicleRented() ? "Yes" : "No") + "\n" +
                "Number of Doors: " + numberOfDoors + "\n" +
                '}';
    }
}



