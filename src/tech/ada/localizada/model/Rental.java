package tech.ada.localizada.model;

import java.time.LocalDateTime;

public class Rental {
    protected long id;
    protected Client client;
    protected Company companyWithdrawal;
    protected Company companyReturn;
    protected Vehicle vehicle;
    protected LocalDateTime start;
    protected LocalDateTime finish;
    protected Invoice invoice;
    protected boolean isFinished;


    public Rental(long id,Client client, Company companyWithdrawal, Company companyReturn, Vehicle vehicle, LocalDateTime start, LocalDateTime finish,Invoice invoice) {
        this.id=id;
        this.client = client;
        this.companyWithdrawal = companyWithdrawal;
        this.companyReturn = companyReturn;
        this.vehicle = vehicle;
        this.start = start;
        this.finish = finish;
        this.invoice = invoice;
    }

    public Client getClient() {
        return client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Company getCompanyWithdrawal() {
        return companyWithdrawal;
    }

    public void setCompanyWithdrawal(Company companyWithdrawal) {
        this.companyWithdrawal = companyWithdrawal;
    }

    public Company getCompanyReturn() {
        return companyReturn;
    }

    public void setCompanyReturn(Company companyReturn) {
        this.companyReturn = companyReturn;
    }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String comprovanteLocacao(){
        String texto= "Comprovante de Locação\n";
        texto += "----------" + "\n";
        texto += " Número do contrato: "+getId() + "\n";
        texto += " Nome Cliente: "+getClient() + "\n";
        texto += " Agência de retirada do Veículo: "+ getCompanyWithdrawal() + "\n";
        texto += " Agência de devolução do Veiculo: "+ getCompanyReturn() + "\n";
        texto += " Veículo: "+ getVehicle() + "\n";
        texto += " Data da retirada: "+ getStart() + "\n";
        texto += " Data da devolução: " + getFinish() + "\n";
        texto += " Fatura: " + getInvoice() + "\n";
        return texto;


    }

    public String comprovanteDevolução(){
        String texto= "Comprovante de Devolução\n";
        texto += "----------" + "\n";
        texto += " Número do contrato: "+getId() + "\n";
        texto += " Nome Cliente: "+getClient() + "\n";
        texto += " Agência de retirada do Veículo: "+ getCompanyWithdrawal() + "\n";
        texto += " Agência de devolução do Veiculo: "+ getCompanyReturn() + "\n";
        texto += " Veículo: "+ getVehicle() + "\n";
        texto += " Data da retirada: "+ getStart() + "\n";
        texto += " Data da devolução: " + getFinish() + "\n";
        texto += " Fatura: " + getInvoice() + "\n";
        return texto;

    }




    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id + "\n" +
                ", client=" + client + "\n" +
                ", companyWithdrawal=" + companyWithdrawal + "\n" +
                ", companyReturn=" + companyReturn + "\n" +
                ", vehicle=" + vehicle + "\n" +
                ", start=" + start + "\n" +
                ", finish=" + finish + "\n" +
                ", invoice=" + invoice + "\n" +
                '}';
    }




}
