package tech.ada.localizada.model;

public class VehicleCar extends Vehicle {

    private int numberOfDoors;

    public VehicleCar(int id, String model, int year, int numberOfDoors) {
        super(id, model, year);
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

}



