import java.util.Date;

public abstract class Insurance {
    private String name;
    private double unitPrice;
    private Date startDate;
    private Date expiryDate;

    public Insurance(String name, double unitPrice, Date startDate, Date expiryDate) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
    }
    
    public abstract double calculate();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    
}
