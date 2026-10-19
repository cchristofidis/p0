import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


import java.net.*;
import com.google.gson.*;

/**
 * Read information from a URL that has one json string
 * per line, each string representing a Person201 object.
 * Format of actual data on a line in file accessed by URL is string,double,double,string
 * but each line is assumed to be in the format
 * ip-address<>string,double,double,string
 * That is the data is assumed to be after the marker "<>", e.g., 
  
10.194.139.181<>{"name":"Vance","latitude":32.7767,"longitude":96.797,"eatery":"Banh\u0027s Cuisine"}
10.194.140.136<>{"name":"Noa","latitude":25.76,"longitude":80.19,"eatery":"Alpaca"}
10.194.144.206<>{"name":"Alex","latitude":39.1405,"longitude":84.4802,"eatery":"Cosmic Cantina"}
10.194.17.222<>{"name":"Brian","latitude":41.8781,"longitude":-87.6298,"eatery":"Juju"}

@author Owen Astrachan
@version 3.0, updated for Fall 2026

*/

public class PeopleDownloader {
    private final static String DELIM = "<>";
    private static ArrayList<Person201> ourList = new ArrayList<>();

    public static String URL = "https://courses.cs.duke.edu/compsci201/fall26/data/p0/compsci201.txt";

    /**
     * Read the info in the specified URL, a text file which
     * should contain one json string per line, where
     * the json string comes after an arbitrary string followed
     * by "<>" -- this string "<>" delimits the json from
     * other information, e.g., the IP address that posted the info.
     * @param address is the URL of a file storing valid
     *                json strings, one per line after "<>"
     * @return a Person201[] array where each entry represents
     * a Person 201 object formed by parsing the json on each line,
     * first line at index 0. Invalid json strings are silently
     * ignored
     * @throws IOException if reading the URL fails
     */
    public static Person201[] loadData(String address) throws Exception {
        URI uri = new URI(address);
        URL url = uri.toURL();
        Scanner s = new Scanner(url.openStream());
        ourList.clear();
        Gson gs = new Gson();


        while (s.hasNextLine()) {
            String line = s.nextLine();
            //System.err.println(line);
            String[] data = line.split(DELIM);
            if (data.length > 1) {
                try {
                    String json = data[1];
                    Person201 p = gs.fromJson(json, Person201.class);
                    if (! ourList.contains(p)) {
                        ourList.add(p);
                    }
                }
                catch (JsonParseException jspe) {
                    // print json syntax issues
                    System.err.println("**ERROR "+jspe);
                }
            }
        }
        s.close();
        return ourList.toArray(new Person201[0]);
    }

    public static void main(String[] args){
        try {
            Person201[] pa = PeopleDownloader.loadData(URL);
            for(Person201 p : pa) {
                System.out.println(p);
                //System.out.printf("%s,%2.3f,%2.3f,%s\n",p.name(),p.latitude(),p.longitude(),p.eatery());
            }
            System.out.printf("total = %d\n",pa.length);
        }
        catch (Exception e) {
            System.err.println("problem loading data");
            e.printStackTrace();
        }
    }
}
