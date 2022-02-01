public class AddressManager {
    public static void addAddress(User user,Address address){
        user.getAddressList().add(address.getAddress());
    }

    public static void removeAddress(User user,Address address){
        user.getAddressList().remove(address.getAddress());
    }
}
