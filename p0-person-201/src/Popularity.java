import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Popularity {
    public static String URL = "https://courses.cs.duke.edu/compsci201/fall26/data/p0/compsci201.txt";
    public static void main(String[] args){
        try {
            Person201[] pa = PeopleDownloader.loadData(URL);
            TreeSet<String> eateries = new TreeSet<>();
            Map<String,Integer> counts = new HashMap<>();
            for(Person201 p : pa){
                eateries.add(p.eatery());
            }
            for (String e : eateries){
                int count = 0;
                for(Person201 p : pa){
                    if (p.eatery().equals(e)){
                        count++;
                    }
                }
                counts.put(e, count);
            }

            int rank = 1;
            while (counts.size() > 0){
                String maxE = null;
                int maxC = 0;
                for (String e : counts.keySet()){
                    int c = counts.get(e);
                    if (c > maxC){
                        maxC = c;
                        maxE = e;
                    }
                }
                System.out.printf("rank: %d:\toccurences: %d:\t%s\n",rank,maxC,maxE);
                counts.remove(maxE);
                rank++;
            }

            System.out.printf("total = %d\n",pa.length);
        }
        catch (Exception e) {
            System.err.println("problem loading data");
            e.printStackTrace();
        }
    }
}
