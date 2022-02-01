public class HomeAddress implements Address{
    private Address address;

    public HomeAddress(Address address) {
        this.address = address;
    }

    @Override
    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
}
