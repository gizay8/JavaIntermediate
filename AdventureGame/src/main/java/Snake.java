import java.util.Random;
public class Snake extends Obstacle{
    public Snake() {
        super("Yılan", 4, 0, 12, 0);
        Random r = new Random();
        int d = r.nextInt(3) + 3;
        this.setDamage(d);
    }
    
}
