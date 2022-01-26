public class MobilePhone extends Product{
    private int memory;
    private double batteryPower;
    private String color;
    
    public MobilePhone(int id, double unitPrice, double discountRate, int stockAmount,
            String name, String brand, double  screenSize, int ram, 
            String categoryName, int memory, double batteryPower, String color) {
        
        super(id, unitPrice, discountRate, stockAmount, name, brand, screenSize,
                ram, categoryName);
        this.memory = memory;
        this.batteryPower = batteryPower;
        this.color = color;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public double getBatteryPower() {
        return batteryPower;
    }

    public void setBatteryPower(double batteryPower) {
        this.batteryPower = batteryPower;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    

}