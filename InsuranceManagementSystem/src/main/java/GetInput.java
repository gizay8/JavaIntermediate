import java.util.Scanner;

public class GetInput {
   private String email, password;

    public GetInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your email : ");
        this.email = scanner.next();
        System.out.print("Enter your password: ");
        this.password = scanner.next();
        AccountManager.login(this);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    } 
}
