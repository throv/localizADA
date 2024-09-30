package tech.ada.localizada.model;

public class Invoice {
    protected double finalPrice;
    protected double totalPrice;
    protected double discount;
    protected PaymentMethod paymentMethod;




    public Invoice(double finalPrice, double totalPrice, double discount, PaymentMethod paymentMethod) {
        this.finalPrice = finalPrice;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.paymentMethod = paymentMethod;

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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "finalPrice=" + finalPrice +
                ", totalPrice=" + totalPrice +
                ", discount=" + discount +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
