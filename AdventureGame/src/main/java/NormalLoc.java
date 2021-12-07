public abstract class NormalLoc extends Location{
    public NormalLoc(Player player, String locationName) {
        super(player, locationName);
    }
    
    public boolean onLocation(){
        return true;
    }
    
}
