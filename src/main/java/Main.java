import processor.Processor;
import reader.FileReader;
import reader.FileType;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Description: Main file for most active cookie
 * program.
 * @author Luciano
 * @date 1/24/21
 */

public class Main {
    public static void main(String[] args) {
        if(args.length < 3) {
            System.out.println("---------------------------------------------------------");
            System.out.println("Example Usage: java -jar file.jar file.csv -d 2018-12-09");
            System.out.println("---------------------------------------------------------");
            return;
        }

        String filePath = "./" + args[0];
        String date = args[2];

        // Initialize Reader Object
        FileReader reader = new FileReader(FileType.CSV, filePath);

        // List that contains each line of the file
        ArrayList<String> contents;

        try {
            // Reads the file and returns the contents
            contents = reader.read();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            return;
        }

        // Checks if file is null or empty
        if(contents == null || contents.isEmpty()) {
            System.out.println("File Empty. Program closing...");
            return;
        }

        // Create processor object and get most active cookies
        Processor processor = new Processor(contents);
        ArrayList<String> result = processor.getMostActiveCookies(date);

        // Print most active cookies
        for(String res : result) {
            System.out.println(res);
        }

    }
}
