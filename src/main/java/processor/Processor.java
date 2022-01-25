package processor;

import java.util.*;

/**
 * Description: Processing file content and outputs
 * the most active cookies.
 * @author Luciano
 * @date 1/24/21
 */

public class Processor {
    // Stores the contents of the file
    private ArrayList<String> contents;

    /*
    Stores lines that contain the specified date.
    Also stores the number of times a line has
    appeared in the file.
     */
    private HashMap<String, Integer> cookies;

    public Processor(ArrayList<String> contents) {
        this.contents = contents;

        cookies = new HashMap<>();
    }

    /**
     * Gets the most active cookie in a cookie log file.
     * @param date      specified date
     * @return          ArrayList of cookies
     */
    public ArrayList<String> getMostActiveCookies(String date) {
        /*
        Once the program finds a line with this date, the value will be set
        to true. Then if a program finds a line that does not equal the date AND this
        value is true, the loop will break to reduce runtime.
         */
        boolean finished = false;

        // Loops through each line in the file
        for(String line : contents) {

            // Checks if the line contains the date
            if(line.contains(date)) {
                // Splits the cookie and date between the comma
                String[] args = line.split(",");

                // Stores the cookie
                String cookie = args[0];

                // Checks if our map contains the cookie
                if(cookies.containsKey(cookie)) {
                    // If it does, increment the amount of appearances
                    cookies.put(cookie, cookies.get(cookie) + 1);
                } else {
                    // Create record in map
                    cookies.put(cookie, 1);
                }

                // Specified date has been found, if we see another date we can break.
                finished = true;

                continue;
            }

            if(finished) break;
        }

        // Records largest cookie
        int largest = 0;
        String cookie = "";

        // Iterator for hashmap
        Iterator mapIterator = cookies.entrySet().iterator();

        // Goes through each valid cookie
        while(mapIterator.hasNext()) {
            Map.Entry element = (Map.Entry) mapIterator.next();

            String tempCookie = (String) element.getKey();
            int appearances = (int) element.getValue();

            // Determines largest cookie
            if(appearances > largest) {
                largest = appearances;
                cookie = tempCookie;
            }
        }

        ArrayList<String> result = new ArrayList<>();
        result.add(cookie);

        // reset map iterator
        mapIterator = cookies.entrySet().iterator();

        // Adds cookies that are equal to the largest
        while(mapIterator.hasNext()) {
            Map.Entry element = (Map.Entry) mapIterator.next();

            String tempCookie = (String) element.getKey();
            int appearances = (int) element.getValue();

            if(appearances == largest && tempCookie != cookie) {
                result.add(tempCookie);
            }
        }

        return result;
    }
}
