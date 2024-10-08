package tech.ada.localizada.model;

public abstract class Vehicle {

    private static int idCounter = 0;
    private int id;
    private String model;
    private int year;
    private String plate;;
    private boolean isVehicleRented;

    protected Vehicle(String model, int year, String plate,
                   boolean isVehicleRented) {
        this.id = ++idCounter;
        this.model = model;
        this.year = year;
        this.plate = plate;
        this.isVehicleRented = isVehicleRented;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public boolean isVehicleRented() {
        return isVehicleRented;
    }

    public void setVehicleRented(boolean vehicleRented) {
        isVehicleRented = vehicleRented;
    }

}
