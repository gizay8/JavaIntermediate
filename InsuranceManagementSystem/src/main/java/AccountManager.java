import java.util.TreeSet;

public class AccountManager {
    public static TreeSet<Account> accounts = new TreeSet<>();

    public static boolean login(GetInput input) {
        Account account = accounts.stream()
                .filter(accountRaw -> (
                        accountRaw.getUser().getEmail().equalsIgnoreCase(input.getEmail())  ||
                                accountRaw.getUser().getPassword().equalsIgnoreCase(input.getPassword())  ))
                .findFirst().orElse(null);

        if (account == null)
            return false;

        else {
            try {
                account.login(input.getEmail(), input.getPassword());
                return true;
            } catch (InvalidAuthenticationException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }
}
