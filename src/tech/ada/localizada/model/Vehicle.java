package tech.ada.localizada.model;

    public abstract class Vehicle {

        private int id;
        private String model;
        private int year;
        private String plate;

        public Vehicle(int id, String model, int year, String plate) {
            this.id = id;
            this.model = model;
            this.year = year;
            this.plate = plate;
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
    }


