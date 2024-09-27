package tech.ada.localizada.model;

public class Invoice {
    protected double finalPrice;
    protected double totalPrice;
    protected double discount;

    public Invoice(double finalPrice, double totalPrice, double discount) {
        this.finalPrice = finalPrice;
        this.totalPrice = totalPrice;
        this.discount = discount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
