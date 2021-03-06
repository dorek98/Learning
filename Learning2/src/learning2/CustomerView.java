package learning2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerView {
    public CustomerView() {
    }

    public static void showCustomers(ArrayList<Customer> customers){
        int id = 1;
        for (Customer customer : customers) {
            System.out.printf("#%d: %s \n",id,customer);
            id++;
        }
    }

    public static void searchCustomer(Scanner scan, ArrayList<Customer> customers){
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

    public void showMenu(){
        System.out.println("MENU!\n");
        System.out.println("View all the customers press 1!");
        System.out.println("View specific customer press 2!");
        System.out.println("Add a customer press 3!");
        System.out.println("Edit specific customer press 4!");
        System.out.println("Remove specific customer press 5!");
        System.out.println("Save the changes to file press 6!");
        System.out.println("Show menu press 7!");
        System.out.println("Exit press  0!\n");
    }
}
