import java.util.Date;

public class HealthInsurance extends Insurance{

    public HealthInsurance(double unitPrice, Date startDate, Date expiryDate) {
        super("Health Insurance", unitPrice, startDate, expiryDate);
    }
    
    @Override
    public double calculate() {
        long difference = getExpiryDate().getTime() - getStartDate().getTime();
        double price = getUnitPrice() * difference;
        return price;
    }
    
}
