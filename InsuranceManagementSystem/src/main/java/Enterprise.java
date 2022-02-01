public class Enterprise extends Account{

    public Enterprise(User user) {
        super(user);
    }

    @Override
    public Insurance addInsurance() {
       return null; 
    }

    @Override
    public int compareTo(Account o) {
        return o.getUser().getName().charAt(0);
    }
    
}
