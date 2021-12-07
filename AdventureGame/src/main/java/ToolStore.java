public class ToolStore extends NormalLoc{
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }
    
    @Override
    public boolean onLocation(){
        System.out.println("----- Mağazaya hoşgeldiniz ! -----");
        boolean showMenu = true;
        while(showMenu){
            System.out.println("1 - Silahlar");
        System.out.println("2 - Zırhlar");
        System.out.println("3 - Çıkış Yap");
        System.out.print("Seçiminiz : ");
        int selectCase = Location.input.nextInt();
        while(selectCase < 1 || selectCase > 3){
            System.out.println("Geçersiz değer, tekrar giriniz : ");
            selectCase = Location.input.nextInt();
        }
        switch(selectCase){
            case 1:
                printWeapon();
                buyWeapon();
                break;
            case 2:
                printArmor();
                buyArmor();
                break;
            case 3:
                System.out.println("Bir daha bekleriz !");
                showMenu = false;
                break;
        }
        }
        return true;
    }
    
    public void printWeapon(){
        System.out.println("----- Silahlar -----");
        for(Weapon w : Weapon.weapons()){
            System.out.println(w.getId() + " - " + w.getWeaponName() 
                    + " <Para : " + w.getPrice() 
                    + " , Hasar : " + w.getDamage() + ">");
        }
        System.out.println("Çıkış yapmak için -1 yazınız");
    }
    
    public void buyWeapon(){
        System.out.print("Bir silah seçiniz : ");
        int selectWeaponID = Location.input.nextInt();
        
        while(selectWeaponID < -1 || selectWeaponID > Weapon.weapons().length){
            System.out.println("Geçersiz değer, tekrar giriniz : ");
            selectWeaponID = Location.input.nextInt();
        }
        if(selectWeaponID != -1){
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
        
        if(selectedWeapon != null){
            if(selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                System.out.println("Yeterli paranız bulunmamaktadır !");
            }else{
                System.out.println(selectedWeapon.getWeaponName() + " silahını satın aldınız !");
                int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                this.getPlayer().setMoney(balance);
                System.out.println("Kalan paranız : " + this.getPlayer().getMoney());
                this.getPlayer().getInventory().setWeapon(selectedWeapon);
            }
        }
        }
        
    }
    
    public void printArmor(){
        System.out.println("----- Zırhlar -----");
        for(Armor a : Armor.armors()){
            System.out.println(a.getId() + " - " + a.getArmorName() 
                    + " <Para : " + a.getPrice() 
                    + " , Zırh : " + a.getBlock() + ">");
        }
        System.out.println("Çıkış yapmak için -1 yazınız");
    }
    
    public void buyArmor(){
        System.out.print("Bir zırh seçiniz : ");
        int selectArmorID = Location.input.nextInt();
        
        while(selectArmorID < -1 || selectArmorID > Armor.armors().length){
            System.out.println("Geçersiz değer, tekrar giriniz : ");
            selectArmorID = Location.input.nextInt();
        }
        if(selectArmorID != -1){
           Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);
        
        if(selectedArmor != null){
            if(selectedArmor.getPrice() > this.getPlayer().getMoney()){
                System.out.println("Yeterli paranız bulunmamaktadır !");
            }else{
                System.out.println(selectedArmor.getArmorName() + " zırhını satın aldınız !");
                int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                this.getPlayer().setMoney(balance);
                System.out.println("Kalan paranız : " + this.getPlayer().getMoney());
                this.getPlayer().getInventory().setArmor(selectedArmor);
            }
        } 
        }
        
    }
}
