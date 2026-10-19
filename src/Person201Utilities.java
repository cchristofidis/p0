import java.io.*;
import java.util.*;
import java.net.*;

/**
 * Utilities for processing data for the Person201 record/class
 * Has not changed over time, even when Person201 class changes
 * to record, or final string in a Person201 object changes as long
 * as it is a String.
 * 
 * @author Owen Astrachan
 * @version 2.0, updated URI/URL code to be current with recent Java
 * @version 2.1, added read(...) method to call readFile or readURL
 */

public class Person201Utilities {
    public static final double EARTH_RADIUS_KM = 6372.8;


    /**
     * Returns the distance in miles between objects give their
     * latitude and longtidue
     * @param p is one of the objects
     * @param q is the other object
     * @return the distance between p and q
     */
    public static double distance(Person201 p, Person201  q){
        return distance(p.latitude(),p.longitude(),q.latitude(),q.longitude());
    }

    /**
     * Returns the distance in miles between two (lat,long) point pairs
     * @param alat is latitude of first point
     * @param along is longitude of first point
     * @param blat is latitude of second point
     * @param blong is longitude of second point
     * @return the distance between point pairs, using Haversin formula
     */
    // code taken from https://rosettacode.org/wiki/Haversine_formula#Java

    public static double distance(double alat, double along, double blat, double blong) {

        double deltaLat = Math.toRadians(blat-alat);
        double deltaLong = Math.toRadians(blong-along);

        double alatRad = Math.toRadians(alat);
        double blatRad = Math.toRadians(blat);

        double calc = Math.pow(Math.sin(deltaLat/2),2) +
                   Math.pow(Math.sin(deltaLong/2),2) *
                   Math.cos(alatRad) * Math.cos(blatRad);
        double c = 2 * Math.atan2(Math.sqrt(calc), Math.sqrt(1-calc)); //2*Math.asin(Math.sqrt(calc));
        
        return EARTH_RADIUS_KM * c;
    }

    /**
     * Returns array of Person201 objects read one-per-line from file. Format of file
     * is string,double,double,string (on each line) 
     * @param fname name of file to read
     * @return array of Person201 objects read
     * @throws IOException if file/reading don't work
     */
    private static Person201[] readFile(String fname) throws IOException {
        Scanner s = new Scanner(new File(fname));
        Person201[] result = readFromScanner(s);
        s.close();
        return result;
    }

     /**
     * Returns array of Person201 objects read one-per-line from URL. Format of file
     * accessible via URL is string,double,double,string (on each line) 
     * @param fname name of URL to read
     * @return array of Person201 objects read
     * @throws IOException if file/reading don't work
     */
    private static Person201[] readURL(String address) throws Exception {

        URI uri = new URI(address);
        URL url = uri.toURL();
        Scanner s = new Scanner(url.openStream());
        Person201[] result = readFromScanner(s);
        s.close();
        return result;
    }

    /**
     * return result of reading a file or a URL
     * @param address is accessible file or URL
     * @return array of Person201 objects read
     * @throws Exception file/web reading fails
     */
    public static Person201[] read(String address) throws Exception {
        if (address.startsWith("http")) {
            return readURL(address);
        }
        else {
            return readFile(address);
        }
    }

    /**
     * helper method to access well-formed data via a Scanner.
     * Typically Scanner is bound to a file or a URL
     * @param s is the Scanner read line-by-line
     * @return array of data read by Scanner
     */
    private static Person201[] readFromScanner(Scanner s) {
        ArrayList<Person201> list = new ArrayList<>();

        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] data = line.split(",");
            Person201 p = new Person201(
                    data[0],
                    Double.parseDouble(data[1]),
                    Double.parseDouble(data[2]),
                    data[3]);
            list.add(p);
        }
        return list.toArray(new Person201[0]);
    }
}
