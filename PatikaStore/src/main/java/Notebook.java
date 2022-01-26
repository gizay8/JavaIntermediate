public class Notebook extends Product{
    private int storage;
   
    public Notebook(int id, double unitPrice, double discountRate, int stockAmount,
            String name, String brand, double  screenSize, int ram, 
            String categoryName, int storage) {
        
        super(id, unitPrice, discountRate, stockAmount, name, brand, screenSize,
                ram, categoryName);
        this.storage = storage;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }
    

}
