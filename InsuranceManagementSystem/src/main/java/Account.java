import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Account implements Comparable<Account> {
    private User user;
    private ArrayList<Insurance> insurances;
    private AuthenticationStatus status = AuthenticationStatus.FAIL;
    
    public Account(User user) {
        this.user = user;
    }
    
    public void login(String email, String password) throws InvalidAuthenticationException {
        if (user.getEmail().equals(email) && user.getPassword().equals(password))
            status = AuthenticationStatus.SUCCESS;
        else throw new InvalidAuthenticationException("Incorrect email or password");
    }
    
    public final void showUserInfo(){
        SimpleDateFormat format = new SimpleDateFormat("hh:mm dd/MM/yyy");
        System.out.println("First Name: " + user.getName());
        System.out.println("Last Name: " + user.getSurname());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Career: " + user.getCareer());
        System.out.println("Age: " + user.getAge());
        System.out.println("Last Login: " + format.format(user.getLastLogin()));
        System.out.println("Addresses: ");
        for(Address address : this.user.getAddressList()) {
            System.out.println(address);
        }
    }
    
    AddressManager addressManager = new AddressManager();
    
    public  void addAddress(Address address) {
        addressManager.addAddress(this.user, address);
    }

    public  void removeAddress(Address address) {
        addressManager.removeAddress(this.user, address);
    }

    public abstract  Insurance addInsurance();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.user);
        hash = 97 * hash + Objects.hashCode(this.insurances);
        hash = 97 * hash + Objects.hashCode(this.status);
        hash = 97 * hash + Objects.hashCode(this.addressManager);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.insurances, other.insurances)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return Objects.equals(this.addressManager, other.addressManager);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(ArrayList<Insurance> insurances) {
        this.insurances = insurances;
    }

    public AuthenticationStatus getStatus() {
        return status;
    }

    public void setStatus(AuthenticationStatus status) {
        this.status = status;
    }

    public AddressManager getAddressManager() {
        return addressManager;
    }

    public void setAddressManager(AddressManager addressManager) {
        this.addressManager = addressManager;
    }
    
    
}
