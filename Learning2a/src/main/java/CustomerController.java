import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerController {
    private CustomerView customerView;
    private ArrayList<Customer> customers;
    String filePath;

    public CustomerController(CustomerView customerView, String filePath, ArrayList<Customer> customers) {
        this.customers = customers;
        this.customerView = customerView;
        this.filePath = filePath;
    }

    public static void loadData(String filePath, ArrayList<Customer> customers) throws IOException {
        BufferedReader fileReader = null;
        File file = new File(filePath);
        String line;

        if (file.exists()) {
            try {
                fileReader = new BufferedReader(new FileReader(filePath));
                line = fileReader.readLine();
                while (line != null) {
                    String[] splittedLine = line.split(",");
                    customers.add(new Customer(splittedLine[0], splittedLine[1], Integer.parseInt(splittedLine[2])));
                    line = fileReader.readLine();
                }
            } catch (IOException exception){
                System.out.println("File read error!");
            }
            finally {
                if (fileReader != null) {
                    fileReader.close();
                }
            }
        } else System.out.println("File doesn't exist");
    }

    public static void saveData(String filePath, ArrayList<Customer> customers) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
            for (Customer customer : customers) {
                fileWriter.write(customer.getName() + "," + customer.getSurname() + "," + customer.getAge() + "\n");
            }
        } catch (IOException exception) {
            System.out.println("File save error!");
        }finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
        System.out.println("Data saved to file");
    }

    public static  void addCustomer(Scanner scan, ArrayList<Customer> customers){
        String[] record = new String[2];
        int _age;
        System.out.println("Give name: ");
        record[0] = scan.next();
        System.out.println("Give surname: ");
        record[1] = scan.next();
        System.out.println("Give age: ");
        try {
            _age = scan.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Wrong input! Integer is needed.\nCustomer adding failed");
            return;
        }
        customers.add(new Customer(record[0], record[1], _age));
        System.out.println("Customer added successfully");
    }

    public static  void editCustomer(Scanner scan, ArrayList<Customer> customers){
        System.out.println("Give index of customer to edit:");
        int id = 0;
        try {
            id = scan.nextInt()-1;
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("No such index");
        } catch (InputMismatchException exception) {
            System.out.println("Wrong input! Integer is needed.");
        }
        String[] record = new String[2];
        int _age;
        System.out.println("Give name: ");
        record[0] = scan.next();
        System.out.println("Give surname: ");
        record[1] = scan.next();
        System.out.println("Give age: ");
        try {
            _age = scan.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Wrong input! Integer is needed.\nCustomer editing failed");
            return;
        }
        customers.get(id).setAge(_age);
        customers.get(id).setName(record[0]);
        customers.get(id).setSurname(record[1]);
        System.out.println("Customer edited successfully");
    }

    public  static void removeCustomer(Scanner scan,ArrayList<Customer> customers){
        System.out.println("Removing customer of index: ");
        try {
            customers.remove(scan.nextInt()-1);
            System.out.println("Customer removed successfully");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("No such index");
            return;
        } catch (InputMismatchException exception) {
            System.out.println("Wrong input! Integer is needed.");
        }
    }

    public static  void console(CustomerView customerView,String filePath,ArrayList<Customer> customers) throws IOException {
        loadData(filePath,customers);
        int button = -1;
        customerView.showMenu();
        while (button != 0) {
            final Scanner scan = new Scanner(System.in);
            try {
                button = scan.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("Wrong input! Integer is needed.");
            }
            switch (button) {
                case 1:
                    CustomerView.showCustomers(customers);
                    break;
                case 2:
                    CustomerView.searchCustomer(scan,customers);
                    break;
                case 3:
                    addCustomer(scan,customers);
                    break;
                case 4:
                    editCustomer(scan,customers);
                    break;
                case 5:
                    removeCustomer(scan,customers);
                    break;
                case 6:
                    saveData(filePath,customers);
                    break;
                case 7:
                    customerView.showMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Input needs to be in range 0-8.");
                    break;
            }
        }
    }
}
