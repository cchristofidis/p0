public class Person201Farthest{
public static void main(String[] args) throws Exception {
        String file = "data/foodlarge.txt";
        
        double max = 0;
        Person201 a = null;
        Person201 b = null;
        
        Person201[] p = Person201Utilities.read(file);
        for(int i=0;i< p.length;i++){
            for(int j=i;j<p.length;j++){
                if(max < Person201Utilities.distance(p[i],p[j])){
                    max = Person201Utilities.distance(p[i],p[j]);
                    a=p[i];
                    b=p[j];
                }
            }
        }
        System.out.printf("farthest distance is %3.2f between %s and %s\n",max,a.name(),b.name());
    }
}