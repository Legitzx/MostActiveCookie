import org.junit.Assert;
import org.junit.Test;
import processor.Processor;
import reader.FileReader;
import reader.FileType;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MostActiveCookieTest {
    @Test
    public void startMostActiveCookieTest() {
        String filePath = "./smallLog.csv";
        String date = "2018-12-09";

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

        Assert.assertEquals("5UAVanZf6UtGyKVS", result.get(0));
    }
}
