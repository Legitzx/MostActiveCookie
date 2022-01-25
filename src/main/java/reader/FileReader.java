package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Description: Extracts data from file.
 * @author Luciano
 * @date 1/24/21
 */

public class FileReader {
    private final String FILE_PATH;

    private FileType type;

    public FileReader(FileType type, String filePath) {
        this.type = type;
        FILE_PATH = filePath;
    }

    public ArrayList<String> read() throws FileNotFoundException {
        ArrayList<String> contents = new ArrayList<>();

        if(type == FileType.CSV) {
            Scanner sc = new Scanner(new File(FILE_PATH));

            sc.useDelimiter("\n");

            while(sc.hasNext()) {
                contents.add(sc.next());
            }

            sc.close();
        }

        return contents;
    }
}
