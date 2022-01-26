import java.util.ArrayList;
import java.util.Scanner;

public class PatikaStore {

    private Scanner input = new Scanner(System.in);

    public void store() {

        ProductPanel operationPanel = new ProductPanel();
        Brand brands = new Brand();

        System.out.println("PatikaStore Ürün Yönetim Paneli !\n");
        boolean isExit = false;
        while (!isExit){
            System.out.println("0- Çıkış Yap\n" +
                    "1- Marka Listele\n" +
                    "2-Notebook İşlemleri\n" +
                    "3-Cep Telefonu İşlemleri");
            System.out.print("Tercihiniz :");
            int select=input.nextInt();
            switch (select){
                case 0:
                    System.out.println("Çıkış Yaptınız!");
                    isExit = true;
                    break;
                case 1:
                    System.out.println("\nMarkalarımız:\n----------");

                    brands.getName();
                    break;
                case 2:
                    boolean isExit1 = false;
                    while(!isExit1){
                    System.out.println("Notebook İşlemleri!\n"
                            + "1 - Notebook Listele\n"
                            + "2 - Notebook Ekle\n"
                            + "3 - Id ile Notebook Listele\n"
                            + "4 - Marka ile Notebook Listele\n"
                            + "5 - Id ile Notebook Sil\n"
                            + "0 - Çıkış\n");
                    System.out.print("Tercihiniz : ");
                    int select1 = input.nextInt();
                    if(select1 == 1)
                    {
                        operationPanel.Notebook();
                    }
                    else if(select1 == 2)
                    {

                        System.out.print("Id: ");
                        int addId = input.nextInt();
                        System.out.print("Ad: ");
                        String addName = input.next();
                        System.out.print("Fiyat: ");
                        int addUnitPrice = input.nextInt();
                        System.out.print("Kategori: ");
                        String addCategoryName = input.next();
                        System.out.print("RAM: ");
                        int addRam = input.nextInt();
                        System.out.print("Marka: ");
                        String addBrand = input.next();
                        System.out.print("Stok Miktarı: ");
                        int addStockAmount = input.nextInt();
                        System.out.print("İndirim Oranı: ");
                        double addDiscountRate = input.nextDouble();
                        System.out.print("Depolama: ");
                        int addStorage = input.nextInt();
                        System.out.print("Ekran Boyutu: ");
                        double addScreenSize = input.nextDouble();
                        operationPanel.addNotebook(new Notebook(addId, 
                                addUnitPrice, addDiscountRate, addStockAmount,
                                addName, addBrand, addScreenSize, addRam, 
                                addCategoryName, addStorage));
                    }
                    else if(select1 == 3)
                    {
                        System.out.print("Id Girin: ");
                        int id = input.nextInt();
                        operationPanel.listByNotebookId(id);
                    }
                    else if(select1 == 4)
                    {
                        System.out.print("Marka Adı Girin: ");
                        String brand = input.next();
                        operationPanel.listByNotebookBrand(brand);
                    }
                    else if(select1 == 5)
                    {
                        System.out.print("Id Girin: ");
                        int id = input.nextInt();
                        operationPanel.deleteNotebook(id);
                    }
                    else if(select1 == 0)
                    {
                        isExit1 = true;
                        break;
                    }
                    else
                    {
                        System.out.println("Yanlış değer girdiniz!");
                        break;
                    }
                    }break;

                case 3:
                    boolean isExit2 = false;
                    while(!isExit2){
                    System.out.println("Telefon İşlemleri!\n"
                            + "1 - Telefon Listele\n"
                            + "2 - Telefon Ekle\n"
                            + "3 - Id ile Telefon Listele\n"
                            + "4 - Marka ile Telefon Listele\n"
                            + "5 - Id ile Telefon Sil\n"
                            + "0 - Çıkış\n");

                    System.out.print("Tercihiniz: ");

                    int select2 = input.nextInt();
                    if(select2 == 1)
                    {
                        operationPanel.MobilePhone();
                    }
                    else if(select2 == 2)
                    {
                        System.out.print("Ürün Id: ");
                        int addId = input.nextInt();
                        System.out.print("Ürün Adı: ");
                        String addName = input.next();
                        System.out.print("Ürün Fiyat: ");
                        int addUnitPrice =input.nextInt();
                        System.out.print("Ürün Kategori: ");
                        String addCategory = input.next();
                        System.out.print("Ürün Ram: ");
                        int addRam = input.nextInt();
                        System.out.print("Marka: ");
                        String addBrand = input.next();
                        System.out.print("Ürün Stok: ");
                        int addStockAmount= input.nextInt();
                        System.out.print("Ürün İndirim Oranı: ");
                        double addDiscountRate = input.nextDouble();
                        System.out.print("Ürün Depolama: ");
                        int addMemory = input.nextInt();
                        System.out.print("Ürün Ekran: ");
                        double addScreenSize = input.nextDouble();
                        System.out.print("Ürün Pil:");
                        double addBatteryPower=input.nextInt();
                        System.out.print("Ürün Renk:");
                        String addColor=input.next();


                        operationPanel.addPhone(new MobilePhone(addId, 
                                addUnitPrice, addDiscountRate, addStockAmount, 
                                addName, addBrand, addScreenSize, addRam, 
                                addCategory, addMemory, addBatteryPower, addColor));
                    }
                    else if(select2 == 3)
                    {
                        System.out.print("Id Girin: ");
                        int id = input.nextInt();
                        operationPanel.listByPhoneId(id);
                    }
                    else if(select2 == 4)
                    {
                        System.out.print("Marka Adı Girin: ");
                        String brand = input.next();
                        operationPanel.listByPhoneBrand(brand);
                    }
                    else if(select2 == 5)
                    {
                        System.out.print("Id Girin: ");
                        int id = input.nextInt();
                        operationPanel.deletePhone(id);
                    }
                    else if(select2 == 0)
                    {
                        isExit2 = true;
                        break;
                    }
                    else
                    {
                        System.out.println("Yanlış değer girdiniz!");
                        break;
                    }

                    }break;
                    default:
                    System.out.println("Lütfen Geçerli Bir Değer Giriniz!");
                    
            }
        }
    }

}
