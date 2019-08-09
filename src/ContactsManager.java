import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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

            String userPhoneNumber = sc.nextLine();
            String formattedNumber = userPhoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
            try{
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("./contacts.txt", true)
                );
                writer.newLine();
                writer.write(String.format(userAdd + " | " + formattedNumber));
                writer.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }


        } else if (userInput.equals("3")){
            System.out.println("What would you like to search for?");
            Scanner scanner = new Scanner(System.in);
            String searchInput = scanner.nextLine().toLowerCase();
            File file = new File("contacts.txt");
            Scanner in = null;
            try {
                in = new Scanner(file);
                while(in.hasNextLine())
                {
                    String line=in.nextLine().toLowerCase();
                    if(line.contains(searchInput))
                        System.out.println(line);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (userInput.equals("4")){
            System.out.println("What would you like to delete");
            Scanner deleteScanner = new Scanner(System.in);
            String deleteInput = deleteScanner.nextLine();
            try{


            File file = new File("contacts.txt");
            File temp = File.createTempFile("file", ".txt", file.getParentFile());

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp)));
            for (String line; (line = reader.readLine()) != null;) {
                if(!line.contains(deleteInput)) {
                    writer.println(line);
                }

            }
            reader.close();
            writer.close();
            file.delete();
            temp.renameTo(file);
            } catch (IOException e){
                e.printStackTrace();
            }

        } else if (userInput.equals("5")){
            keepGoing=false;
        }else{
            System.out.println("Bye");
        }
        }while(keepGoing);



    }
}
