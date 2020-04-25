import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerController {
    private CustomerView customerView;
    private ArrayList<Customer> customers;
    String filePath;
    final Scanner scan = new Scanner(System.in);


    public CustomerController(CustomerView customerView, String filePath, ArrayList<Customer> customers) {
        this.customers = customers;
        this.customerView = customerView;
        this.filePath = filePath;
    }

    public void loadCSV() throws IOException {
        Reader reader = null;
        try{
            reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser csvParser = new CSVParser(reader,CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withTrim());
            for (CSVRecord csvRecord : csvParser){
                customers.add(new Customer(csvRecord.get("Name"), csvRecord.get("Surname"),
                        Integer.parseInt(csvRecord.get("Age"))));
            }
        }catch (IOException exception){
            System.out.println("File read error!");
        }finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public void saveCSV() throws IOException {
        BufferedWriter writer = null;
        try{
            writer = Files.newBufferedWriter(Paths.get(filePath));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("Name","Surname","Age"));
            for (Customer customer : customers){
                csvPrinter.printRecord(customer.getName(),customer.getSurname(),customer.getAge());
            }
            csvPrinter.flush();
        }catch (IOException ex){
            System.out.println("File save error!");
            return;
        }finally {
            if (writer != null) {
                writer.close();
            }
        }
        System.out.println("Data saved to file");
    }

    public void addCustomer(){
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

    public void showCustomer(){
        System.out.println("Searching for customer of index:");
        try {
            int j = scan.nextInt()-1;
            System.out.printf("#%d: %s ",j+1,customers.get(j));
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("No such index");
        } catch (InputMismatchException exception) {
            System.out.println("Wrong input! Integer is needed.");
        }
    }

    public void editCustomer(){
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

    public void removeCustomer(){
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

    public void console() throws IOException {
        //loadData();
        loadCSV();
        int button = -1;
        customerView.showMenu();
        while (button != 0) {
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
                    showCustomer();
                    break;
                case 3:
                    addCustomer();
                    break;
                case 4:
                    editCustomer();
                    break;
                case 5:
                    removeCustomer();
                    break;
                case 6:
                    saveCSV();
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
