public class BusinessAddress implements Address{
    private Address address;

    public BusinessAddress(Address address) {
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
