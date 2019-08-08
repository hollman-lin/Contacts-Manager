import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {


    static Path contactsPath = Paths.get("./","contacts.txt");

    static List<String> contactList = new ArrayList<>();
    static List<String> lines;




    public static String list() {
        try {
            lines = Files.readAllLines(contactsPath);
            for(int i = 0; i < lines.size(); i++) {
                System.out.println(lines.get(i));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return "Hey";
    }

    public static void parseFile(String fileName,String searchStr) {
        try{

        Scanner scan = new Scanner(new File(fileName));
        while(scan.hasNext()){
            String line = scan.nextLine().toLowerCase().toString();
            if(line.contains(searchStr)){
                System.out.println(line);
            }
        }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

//        ---------added enter-----------


        boolean keepGoing= true;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("1. View contacts.\n" +
                    "2. Add a new contact.\n" +
                    "3. Search a contact by name.\n" +
                    "4. Delete an existing contact.\n" +
                    "5. Exit.\n" +
                    "Enter an option (1, 2, 3, 4 or 5):");
        String userInput = scan.nextLine();
        if(userInput.equals("1")){
            list();
        } else if (userInput.equals("2")){
            System.out.println("Please enter the name of the contact");
            Scanner sc = new Scanner(System.in);
            String userAdd= sc.nextLine();
            System.out.println("Please enter the phone number of the contact");
            double userPhoneNumber = sc.nextDouble();
            try{
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("./contacts.txt", true)
                );
                writer.newLine();
                writer.write(userAdd + " | " + userPhoneNumber);
                writer.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }


        } else if (userInput.equals("3")){
            System.out.println("What would you like to search for?");
            Scanner scanner = new Scanner(System.in);
            String searchInput = scanner.nextLine();
            System.out.println();
            parseFile("contacts.txt", searchInput);
        } else if (userInput.equals("4")){
            System.out.println("delete something");
        } else if (userInput.equals("5")){
            keepGoing=false;
        }else{
            System.out.println("Bye");
        }
        }while(keepGoing);



    }
}
