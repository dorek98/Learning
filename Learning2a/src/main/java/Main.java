import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        final String FILEPATH = "..\\Learning2a\\src\\main\\resources\\fileCSV.csv";
        ArrayList<Customer> customers = new ArrayList<>();
        CustomerView customerView = new CustomerView();
        CustomerController customerController = new CustomerController(customerView,FILEPATH,customers);
        customerView.showMenu();
        customerController.console();
    }
}
