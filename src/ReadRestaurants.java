import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

/**
 * Read JSON file of Ninth-street area restaurants and print information
 * about each restaurant. Illustrates using Gson and Java APIs
 * 
 * @author Owen Astrachan
 * @date July 1, 2025
 */

public class ReadRestaurants {

    /**
     * 
     * @param fname is the name of a JSon file with data for Restaurant objects
     * @return array of Restaurant objects read from file
     * @throws IOException if reading fails
     */

    public static Restaurant[] readFromFile(String fname) throws IOException{
        List<Restaurant> list = new ArrayList<>();

         Gson gson = new Gson();

         // use JsonReader to read Json formatted data
         // Use try with resources to Autoclose JsonReader
         // which will, in turn, close the FileReader created

         try (JsonReader reader = new JsonReader(new FileReader(fname))) {
            reader.beginArray();
            while (reader.hasNext()){
                Restaurant r = gson.fromJson(reader, Restaurant.class);
                list.add(r);
            }
            reader.endArray();
         }

        return list.toArray(new Restaurant[0]);
    }

    public static void main(String[] args) throws IOException {
        String fname = "data/restaurants_ninth.json";
        Restaurant[] ra = readFromFile(fname);

        // sort the array so that it's in alphabetical order by name
        Arrays.sort(ra, Comparator.comparing(Restaurant::name));

        // print cuisine and name of restaurant for each array object
        for(Restaurant r : ra){
            System.out.printf("%-20s\t%s\n",r.cuisine(),r.name());
        }
    }
}
