/**
 * Simple linear algebra operations on vectors of double values.
 * 
 * @author Owen Astrachan
 * @date November, 2025
 */

public class VectorUtils {
    
    /**
     * Return a normalized version of parameter.
     * @param vector is unmodified after call
     * @return normalized version of vector
     */
    public static double[] normalize(double[] vector) {
        double[] normed = new double[vector.length];
        double mag = magnitude(vector);
        for(int k=0; k < vector.length; k++){
            normed[k] = vector[k]/mag;
        }
        return normed;
    }

    /**
     * Return vector that is the sum of two parameters
     * which should have same length
     * @param a is left addend
     * @param b is right addend
     * @return a + b as a vector
     */
    public static double[] add(double[] a, double[] b){
        double[] sum = new double[a.length];
        for(int k=0; k < a.length; k++){
            sum[k] = a[k] + b[k];
        }
        return sum;
    }

    /**
     * Return vector that is the difference of two parameters
     * which should have the same length
     * @param a is the left argument in a - b, subtract(a,b)
     * @param b is the right argument
     * @return a - b as a vector
     */
    public static double[] subtract(double[] a, double[] b){
        double[] sum = new double[a.length];
        for(int k=0; k < a.length; k++){
            sum[k] = a[k] - b[k];
        }
        return sum;
    }

    /**
     * return the dot product of a and b
     * @param a is the left argument of a.b, dotp(a,b)
     * @param b is the right argument
     * @return value that is the dot product of a x b
     */
    public static double dotp(double[] a, double[] b){
        double sum = 0.0;
        for(int k=0; k < a.length; k++){
            sum += a[k]*b[k];
        }
        return sum;
    }

    /**
     * Return the magnitude of vector (Euclidean norm)
     * @param vector is argument
     * @return |vector|, the Euclidean norm magnitude
     */
    public static double magnitude(double[] vector) {
        double sum = 0;
        for(double value : vector) {
            sum += value*value;
        }
        return Math.sqrt(sum);
    }

    /**
     * return cosine similarity of two vectors 
     * @param a first argument
     * @param b second argument
     * @return the cosine similarity of a and b
     */
    public static double cosineSimilarity(double[] a, double[] b){
        double sum = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for(int k=0; k < a.length; k++){
            sum += a[k]*b[k];
            normA += a[k]*a[k];
            normB += b[k]*b[k];
        }
        return sum/(Math.sqrt(normA)*Math.sqrt(normB)+1e-9);
    }
}
