import java.util.*;

public class Brand {
    private String name;

    public Brand() {
        
    }

    public String getName() {
        ArrayList<String> brandsList = new ArrayList<>();
        brandsList.add("Samsung");
        brandsList.add("Lenovo");
        brandsList.add("Apple");
        brandsList.add("Huawei");
        brandsList.add("Casper");
        brandsList.add("Asus");
        brandsList.add("HP");
        brandsList.add("Xiaomi");
        brandsList.add("Monster");

        Collections.sort(brandsList);
        for(String brand : brandsList){
            System.out.println(brand);
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
