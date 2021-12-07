import java.util.Random;
public abstract class BattleLoc extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String locationName, Obstacle obstacle, String award, int maxObstacle) {
        super(player, locationName);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }
    
    public boolean onLocation(){
        if (this.award.equals("Food") && this.getPlayer().getInventory().isFood() == true) {
            System.out.println("Mağaradan yemek daha önce elde edildi, tekrar giremezsiniz !");
            return true;  
	} 
        else if (this.award.equals("Firewood") && this.getPlayer().getInventory().isFirewood() == true) {
	    System.out.println("Ormandan odun daha önce elde edildi, tekrar giremezsiniz !");
	}
        else if (this.award.equals("Water") && this.getPlayer().getInventory().isWater() == true) {
            System.out.println("Nehirden su daha önce elde edildi, tekrar giremezsiniz !");
		
	} 
        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şu an buradasınız : " + this.getLocationName());
        System.out.println("Dikkatli ol! Burada " + obsNumber + " tane " + this.getObstacle().getObstacleName() + " yaşıyor!");
        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase = input.nextLine().toUpperCase();
        if(selectCase.equals("S") && combat(obsNumber)){
            System.out.println(this.getLocationName() + " bölgesindeki tüm düşmanları temizlediniz !");
            if (this.award.equals("Food") && this.getPlayer().getInventory().isFood() == false) {
                System.out.println(this.award + " Kazandınız !");
		this.getPlayer().getInventory().setFood(true);
	    } 
            else if (this.award.equals("Firewood") && this.getPlayer().getInventory().isFirewood() == false) {
		System.out.println(this.award + " Kazandınız !");
		this.getPlayer().getInventory().setFirewood(true);
	    }
            else if (this.award.equals("Water") && this.getPlayer().getInventory().isWater() == false) {
		System.out.println(this.award + " Kazandınız !");
		this.getPlayer().getInventory().setWater(true);
	    }
            return true;
        }
        if(this.getPlayer().getHealth() <= 0){
            System.out.println("Öldünüz !");
            return false;
        }
        return true;
    }
    
    public boolean combat(int obsNumber){
        for(int i = 1 ; i <= obsNumber ; i++){
            this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth());
            playerStats();
            obstacleStats(i);
            while(this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                System.out.print("<V>ur veya <K>aç : ");
                String selectCombat = input.nextLine().toUpperCase();
                if(selectCombat.equals("V")){
                    if(whoStart()){
                        System.out.println("Siz vurdunuz !");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if(this.getObstacle().getHealth() > 0){
                            System.out.println(this.getObstacle().getObstacleName() + " size vurdu !");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if(obstacleDamage < 0){
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                    }
                    else{
                        System.out.println(this.getObstacle().getObstacleName() + " size vurdu !");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if(obstacleDamage < 0){
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                        if(this.getPlayer().getHealth() > 0){
                            System.out.println("Siz vurdunuz !");
                            this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                            afterHit();
                        }
                    }
                }else{
                    return false;
                }
            }
            if(this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                System.out.println("Düşmanı Yendiniz !");
                if (this.award.equals("-")){
                Random r = new Random();
                int r1 = r.nextInt(100);
                if(r1 < 15){
                    int earnedWeaponID;
                    int r2 = r.nextInt(100);
                    if(r2 < 20){
                        earnedWeaponID = 2;
                    }else if(r2 >= 20 && r2 < 50){
                        earnedWeaponID = 1;
                    }else{
                        earnedWeaponID = 0;
                    }
                    Weapon earnedWeapon = Weapon.getWeaponObjByID(earnedWeaponID);
                    this.getPlayer().getInventory().setWeapon(earnedWeapon);
                    System.out.println("Rakibinizden " + earnedWeapon.getWeaponName() 
                            + " düştü. Envanterinize eklendi !");
                }
                else if(r1 >= 15 && r1 < 30){
                    int earnedArmorID;
                    int r3 = r.nextInt(100);
                    if(r3 < 20){
                        earnedArmorID = 2;
                    }else if(r3 >= 20 && r3 < 50){
                        earnedArmorID = 1;
                    }else{
                        earnedArmorID = 0;
                    }
                    Armor earnedArmor = Armor.getArmorObjByID(earnedArmorID);
                    System.out.println("Rakibinizden " + earnedArmor.getArmorName() 
                            + " zırh düştü. Envanterinize eklendi !");
                }
                else if(r1 >= 30 && r1 < 55){
                    int r4 = r.nextInt(100);
                    if(r4 < 20){
                        this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                        System.out.println("Rakibinizden 10 para düştü. Bakiyenize eklendi !");
                    }else if(r4 >= 20 && r4 < 50){
                        this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                        System.out.println("Rakibinizden 5 para düştü. Bakiyenize eklendi !");
                    }else{
                        this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                        System.out.println("Rakibinizden 1 para düştü. Bakiyenize eklendi !");
                    }
                }
                else{
                    System.out.println("Rakibinizden hiçbir şey kazanamadınız !"); 
                }
                }else{
                System.out.println(this.getObstacle().getAward() + " para kazandınız !");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel paranız : " + this.getPlayer().getMoney());
                }
            }else{
                return false;
            }
        }
        return true;
    }
    
    public void afterHit(){
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getObstacleName() + " Canı : " + this.getObstacle().getHealth());
        System.out.println("-----------------------");
    }
    
    public void playerStats(){
        System.out.println("Oyuncu Değerleri");
        System.out.println("-----------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getWeaponName());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getArmorName());
        System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para : " + this.getPlayer().getMoney());
        System.out.println();
    }
    
    public void obstacleStats(int i){
        System.out.println(i + ". " + this.getObstacle().getObstacleName() + " Değerleri");
        System.out.println("-----------------------");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Ödül : " + this.getObstacle().getAward());
        System.out.println();
    }
    
    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }
    
    boolean whoStart(){
        Random random = new Random();
        return random.nextBoolean();
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
    
}
