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
        return "VehicleCar {" +
                "ID: " + getId() + "\n" +
                "Model: " + getModel() + "\n" +
                "Year: " + getYear() + "\n" +
                "Plate: " + getPlate() + "\n" +
                "Agency: " + getFindVehicleAgency().getName() + "\n" +
                "Rented: " + (isVehicleRented() ? "Yes" : "No") + "\n" +
                "Number of Doors: " + numberOfDoors + "\n" +
                '}';
    }

}



