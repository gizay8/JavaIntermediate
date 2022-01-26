import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ProductPanel {
    Scanner input = new Scanner(System.in);
    ArrayList<Notebook> noteBookList = new ArrayList<>();
    ArrayList<MobilePhone> phoneList = new ArrayList<>();


    public void Notebook(){
        Notebook nHuawei = new Notebook(1, 7000.0, 0, 12, 
                "HUAWEI Matebook 14", "Huawei", 14.0, 16, "Notebook", 512);
        Notebook nLenovo = new Notebook(2, 3699.0, 15, 15, 
                "LENOVO V14 IGL", "Lenovo", 14.0, 8, "Notebook", 1024);
        Notebook nAsus  = new Notebook(3, 8199.0, 25, 22, 
                "ASUS Tuf Gaming", "Asus", 15.6, 32, "Notebook", 2048);


        noteBookList.add(nHuawei);
        noteBookList.add(nLenovo);
        noteBookList.add(nAsus);
        listNoteBook(noteBookList);
    }
    private void listNoteBook(ArrayList<Notebook> noteBookList) {

        String leftAlignFormat = "| %-2d | %-20s | %-11s | %-9s | %-10d | %-9s | %-9s |%n";
        String headers = "| %-2s | %-20s | %-11s | %-9s | %-10s | %-9s | %-9s |%n";
        System.out.println("--------------------------------------------------------------------------------------------\n");
        System.out.format(headers, "ID", "Ürün Adı", "Fiyat", "Marka ", "Depolama", "Ekran", "RAM");
        System.out.println("--------------------------------------------------------------------------------------------\n");

        for (Notebook product : noteBookList) {
            System.out.format(leftAlignFormat, product.getId(), 
                    product.getName(), product.getUnitPrice(), 
                    product.getBrand(), product.getStorage(), 
                    product.getScreenSize(),  product.getRam());

        }

    }
    public void listByNotebookId(int id) {
        String leftAlignFormat = "| %-2d | %-20s | %-11s | %-9s | %-10d | %-9s | %-9s |%n";
        String headers = "| %-2s | %-20s | %-11s | %-9s | %-10s | %-9s | %-9s |%n";
        System.out.println("--------------------------------------------------------------------------------------------\n");
        System.out.format(headers, "ID", "Ürün Adı", "Fiyat", "Marka ", "Depolama", "Ekran", "RAM");
        System.out.println("--------------------------------------------------------------------------------------------\n");

        Iterator<Notebook> iterator = noteBookList.iterator();
        while(iterator.hasNext())
        {
            Notebook product = iterator.next();
            if (product.getId() == id)
            {
                System.out.format(leftAlignFormat, product.getId(), 
                        product.getName(), product.getUnitPrice(), 
                        product.getBrand(), product.getStorage(), 
                        product.getScreenSize(), product.getRam());
                break;
            }
        }
    }

    public void listByNotebookBrand(String brandN)
    {
        String leftAlignFormat = "| %-2d | %-20s | %-11s | %-9s | %-10d | %-9s | %-9s |%n";
        String headers = "| %-2s | %-20s | %-11s | %-9s | %-10s | %-9s | %-9s |%n";
        System.out.println("--------------------------------------------------------------------------------------------\n");
        System.out.format(headers, "ID", "Ürün Adı", "Fiyat", "Marka ", "Depolama", "Ekran", "RAM");
        System.out.println("--------------------------------------------------------------------------------------------\n");

        Iterator<Notebook> iterator = noteBookList.iterator();
        while(iterator.hasNext())
        {
            Notebook product = iterator.next();
            String b = product.getBrand();
            if (b.equalsIgnoreCase(brandN))
            {
                System.out.format(leftAlignFormat, product.getId(), 
                        product.getName(), product.getUnitPrice(), 
                        product.getBrand(), product.getStorage(), 
                        product.getScreenSize(),product.getRam());
            }
        }

    }

    public void addNotebook(Notebook n)
    {
        this.noteBookList.add(n);
    }

    public void deleteNotebook(int id)
    {
        Iterator<Notebook> iterator = noteBookList.iterator();
        while(iterator.hasNext())
        {
            Notebook value = iterator.next();
            if (value.getId() == id)
            {
                iterator.remove();
                break;
            }
        }
    }

    public void MobilePhone(){
        MobilePhone pSamsung = new MobilePhone(1, 3199.0, 0, 12, 
                "SAMSUNG GALAXY A51", "Samsung", 6.5, 6, "Mobile Phone", 128, 
                4000.0, "Black");
        MobilePhone pApple =new MobilePhone(2, 7379.0, 15, 15,
                "iPhone 11 64 GB", "Apple", 6.1, 6, "Mobile Phone", 64, 3046.0,
                "Blue");
        MobilePhone pXiaomi =new MobilePhone(3, 4012.0, 25, 22, 
                "Redmi Note 10 Pro 8GB", "Xiaomi", 6.5, 12, "Mobile Phone", 128,
                4000.0, "White");

        phoneList.add(pSamsung);
        phoneList.add(pApple);
        phoneList.add(pXiaomi);
        listPhone(phoneList);
    }
    private void listPhone(ArrayList<MobilePhone> phoneList){
        String leftAlignFormat = "| %-2d | %-20s | %-11s | %-9s | %-10d | %-9s | %-9s | %-9s | %-5d |%n";
        String headers = "| %-2s | %-20s | %-11s | %-9s | %-10s | %-9s | %-9s | %-9s | %-5s |%n";

        System.out.println("--------------------------------------------------------------------------------------------\n");
        System.out.format(headers, "ID","Ürün Adı", "Fiyat",  "Marka", "Depolama", "Ekran","Renk", "Pil","RAM" );
        System.out.println("------------------------------------------------------------------------------------------------------\n");
        for (MobilePhone product : phoneList){
            System.out.format(leftAlignFormat, product.getId(), 
                    product.getName(), product.getUnitPrice(), 
                    product.getBrand(), product.getMemory(), 
                    product.getScreenSize(),
                    product.getColor(),product.getBatteryPower(),
                    product.getRam());
        }

        System.out.println("---------------------------------------------------------------------------------------------");
    }

    public void listByPhoneId(int id) {
        String leftAlignFormat = "| %-2d | %-20s | %-11s | %-9s | %-10d | %-9s | %-9s | %-9s | %-5d |%n";
        String headers = "| %-2s | %-20s | %-11s | %-9s | %-10s | %-9s | %-9s | %-9s | %-5s |%n";
        System.out.println("--------------------------------------------------------------------------------------------\n");
        System.out.format(headers, "ID","Ürün Adı", "Fiyat",  "Marka", "Depolama", "Ekran","Renk", "Pil","RAM" );
        System.out.println("------------------------------------------------------------------------------------------------------\n");
        Iterator<MobilePhone> iterator = phoneList.iterator();
        while(iterator.hasNext())
        {
            MobilePhone product = iterator.next();
            if (product.getId() == id)
            {
                System.out.format(leftAlignFormat, product.getId(), 
                        product.getName(), product.getUnitPrice(), 
                        product.getBrand(), product.getMemory(), 
                        product.getScreenSize(),
                        product.getColor(),product.getBatteryPower(),
                        product.getRam());
                break;
            }
        }
    }

    public void listByPhoneBrand(String brandP)
    {
        String leftAlignFormat = "| %-2d | %-20s | %-11s | %-9s | %-10d | %-9s | %-9s | %-9s | %-5d |%n";
        String headers = "| %-2s | %-20s | %-11s | %-9s | %-10s | %-9s | %-9s | %-9s | %-5s |%n";
        System.out.println("--------------------------------------------------------------------------------------------\n");
        System.out.format(headers, "ID","Ürün Adı", "Fiyat",  "Marka", "Depolama", "Ekran","Renk", "Pil","RAM" );
        System.out.println("------------------------------------------------------------------------------------------------------\n");

        Iterator<MobilePhone> iterator = phoneList.iterator();
        while(iterator.hasNext())
        {
            MobilePhone product = iterator.next();
            String b = product.getBrand();
            if (b.equalsIgnoreCase(brandP))
            {
                System.out.format(leftAlignFormat, product.getId(), 
                        product.getName(), product.getUnitPrice(), 
                        product.getBrand(), product.getMemory(), 
                        product.getScreenSize(),
                        product.getColor(),product.getBatteryPower(),
                        product.getRam());
            }
        }

    }

    public void addPhone(MobilePhone n)
    {
        this.phoneList.add(n);
    }

    public void deletePhone(int id)
    {
        Iterator<MobilePhone> iterator = phoneList.iterator();
        while(iterator.hasNext())
        {
            MobilePhone value = iterator.next();
            if (value.getId() == id)
            {
                iterator.remove();
                break;
            }
        }
    }

}
