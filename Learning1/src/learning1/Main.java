package learning1;


import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String filePath1 = "E:\\IntelliJ IDEA Educational Edition 2019.3\\Learning1\\resources\\file1.txt";
        FileWriter fileWriter = null;
        BufferedReader fileReader = null;
        File file = new File(filePath1);
        Menu menu = new Menu();

        List<Customer> customers = new LinkedList<>();
        String line;
        int button = -1;
        menu.show();
        while(button != 0){

            try {
                button = scan.nextInt();
            } catch (Exception e){
            }
            switch (button){
                case 1:
                    customers.removeAll(customers);
                    if(file.exists()){
                    try{
                        fileReader = new BufferedReader(new FileReader(filePath1));
                        line = fileReader.readLine();
                        while( line != null) {
                           String[] splittedLine = line.split(",");
                            customers.add(new Customer(splittedLine[0],splittedLine[1],Integer.parseInt(splittedLine[2])));
                            line = fileReader.readLine();

                        }

                    } finally {
                        if (fileReader != null){
                            fileReader.close();
                        }
                    }}else System.out.println("File doesn't exist");
                    break;
                case 2:
                    for(int i = 0; i < customers.size() ;i++){
                        System.out.println("#"+(i)+": " + customers.get(i).getName()+" "+customers.get(i).getSurname()+" "+customers.get(i).getAge() );
                    }
                    break;
                case 3:
                    String[] record = new String[3];
                    System.out.println("Give name, surname and age of customer: ");
                    for(int i = 0; i < 3;i++) {
                        scan = new Scanner(System.in);
                        record[i] = scan.nextLine();
                    }
                    customers.add(new Customer(record[0],record[1],Integer.parseInt(record[2])));
                    System.out.println("Customer added successfully");
                    break;
                case 4:
                    if(customers.size() > 0 )
                    customers.remove(customers.size()-1);
                    break;
                case 5:
                    System.out.println("Removing customer of index: ");
                    scan = new Scanner(System.in);
                    try {
                        customers.remove(scan.nextInt());
                        System.out.println("Customer removed successfully");
                    } catch (Exception e){
                        System.out.println("No such index");
                        scan = new Scanner(System.in);
                        break;
                    }
                    break;
                case 6:
                    System.out.println("Searching for customer of index:");
                    scan = new Scanner(System.in);
                    try {
                        int j = scan.nextInt();
                        System.out.println("#" + (j) + ": " + customers.get(j).getName() + " " + customers.get(j).getSurname() + " " + customers.get(j).getAge());
                    } catch (Exception e){
                        System.out.println("No such index");
                        scan = new Scanner(System.in);
                    }
                    break;
                case 7:
                    try{
                        fileWriter = new FileWriter(filePath1);
                        for(Customer e:customers){
                            fileWriter.write(e.getName()+","+e.getSurname()+","+e.getAge()+"\n");
                        }
                    } finally {
                        if(fileWriter != null){
                            fileWriter.close();
                        }
                    }
                    break;
                case 8:
                    menu.show();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Wrong input! Try again.");
                    scan = new Scanner(System.in);
                    break;
            }
        }
    }
}
