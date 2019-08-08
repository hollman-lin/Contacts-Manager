import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ContactsManager {


    static Path contactsPath = Paths.get("contacts.txt");

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
        return "";
    }


    public static void main(String[] args) {
        list();
    }
}
