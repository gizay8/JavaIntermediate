import java.util.Scanner;
public class Game {
    private Scanner input = new Scanner(System.in);
    
    public void start(){
        System.out.println("Macera Oyununa Hoşgeldiniz !");
        System.out.print("Lütfen bir isim giriniz : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Sayın " + player.getPlayerName() + " bu karanlık ve sisli adaya hoşgeldiniz !! Burada yaşananların hepsi gerçek !");
        System.out.println("Lütfen bir karakter seçiniz !");
        System.out.println("----------------------------------------------------------------------");
        player.selectChar();
        boolean isExit = false;
        Location location = null;
        while(true){
            player.printInfo();
            System.out.println();
            System.out.println("######## Bölgeler ########");
            System.out.println();
            System.out.println("0 - Çıkış Yap --> Oyunu sonlandır.");
            System.out.println("1 - Güvenli Ev --> Burası sizin için güvenli bir ev, düşman yoktur !");
            System.out.println("2 - Eşya Dükkanı --> Silah veya zırh satın alabilirsiniz !");
            System.out.println("3 - Mağara --> Ödül <Yemek> , dikkatli ol karşına zombi çıkabilir!");
            System.out.println("4 - Orman --> Ödül <Odun> , dikkatli ol karşına vampir çıkabilir!");
            System.out.println("5 - Nehir --> Ödül <Su> , dikkatli ol karşına ayı çıkabilir!");
            System.out.println("6 - Maden --> Rakiplerini yenip rastgele eşya kazanabilirsin , dikkatli ol karşına yılan çıkabilir!");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz : ");
            int selectLoc = input.nextInt();
            switch(selectLoc){
                case 0:
                    isExit = true;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    location = new SafeHouse(player);
            }
            if(location.getClass().getName().equals("SafeHouse")){
                if(player.getInventory().isFood() && player.getInventory().isFirewood() && player.getInventory().isWater()){
                    System.out.println("Tebrikler Oyunu Kazandınız !");
                    break;
                }
            }
            if(isExit){
                System.out.println("Bu karanlık ve sisli adadan çabuk vazgeçtin !");
                break;
            }
            if(!location.onLocation()){
                System.out.println("GAME OVER !");
                break;
            }
        }
    }
    
}
