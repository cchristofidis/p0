import com.google.gson.Gson;


public class Popularity {
    public static void main(String[] args){
        try {
            Person201[] pa = PeopleDownloader.loadData("https://courses.cs.duke.edu/compsci201/fall26/data/p0/compsci201.txt");
            int max=0;
            String s="";
            for(Person201 p : pa) {
                int n=0;
                int j=0;
                for(Person201 pp : pa){
                    j++;
                    if(p.eatery().equals(pp.eatery())){
                        boolean b = true;
                        for(int i=0; i< j-1;i++)
                            if(pp.name().equals(pa[i].name()) && pp.latitude() == (pa[i].latitude()) && pp.longitude() == (pa[i].longitude()) && pp.eatery().equals(pa[i].eatery()))
                                b=false;
                        
                        if(b == true)
                            n++;
                    }
                }
                if(n>max){
                    max=n;
                    s= p.eatery();
                }
            }
            System.out.println(s);
            System.out.println(max);
        }
        catch (Exception e) {
            System.err.println("problem loading data");
            e.printStackTrace();
        }
    }
    

}
