import java.util.Date;

public class ResidenceInsurance extends Insurance{

    public ResidenceInsurance(double unitPrice, Date startDate, Date expiryDate) {
        super("Residence Insurance", unitPrice, startDate, expiryDate);
    }

    @Override
    public double calculate() {
        long difference = getExpiryDate().getTime() - getStartDate().getTime();
        double price = getUnitPrice() * difference;
        return price;
    }
    
}
