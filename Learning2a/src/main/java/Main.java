import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        final String filePath = "..\\Learning2a\\src\\main\\resources\\file2.txt";
        ArrayList<Customer> customers = new ArrayList<>();
        CustomerView customerView = new CustomerView();
        CustomerController customerController = new CustomerController(customerView,filePath,customers);
        customerView.showMenu();
        customerController.console(customerView,filePath,customers);
    }
}
