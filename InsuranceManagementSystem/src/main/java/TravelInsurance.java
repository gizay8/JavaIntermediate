import java.util.Date;

public class TravelInsurance extends Insurance{

    public TravelInsurance(double unitPrice, Date startDate, Date expiryDate) {
        super("Travel Insurance", unitPrice, startDate, expiryDate);
    }

    @Override
    public double calculate() {
        long difference = getExpiryDate().getTime() - getStartDate().getTime();
        double price = getUnitPrice() * difference;
        return price;
    }
    
}
