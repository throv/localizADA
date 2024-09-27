package tech.ada.localizada.model;

    public abstract class Vehicle {

        private int id;
        private String model;
        private int year;
        private String plate;
        private Company findVehicleAgency;
        private boolean isVehicleRented;

        public Vehicle (String model, int year,
                        String plate, Company findVehicleAgency, boolean isVehicleRented ) {

            this.model = model;
            this.year = year;
            this.plate = plate;
            this.findVehicleAgency = findVehicleAgency;
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

        public Company getFindVehicleAgency() {
            return findVehicleAgency;
        }

        public void setFindVehicleAgency(Company findVehicleAgency) {
            this.findVehicleAgency = findVehicleAgency;
        }

        public boolean isVehicleRented() {
            return isVehicleRented;
        }

        public void setVehicleRented(boolean vehicleRented) {
            isVehicleRented = vehicleRented;
        }
    }


