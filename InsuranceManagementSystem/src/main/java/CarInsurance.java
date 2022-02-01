import java.util.Date;

public class CarInsurance extends Insurance{

    public CarInsurance(double unitPrice, Date startDate, Date expiryDate) {
        super("Car Insurance", unitPrice, startDate, expiryDate);
    }

    @Override
    public double calculate() {
        long difference = getExpiryDate().getTime() - getStartDate().getTime();
        double price = getUnitPrice() * difference;
        return price;
    }
    
}
