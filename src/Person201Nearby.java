
/**
 * Demonstrate Reading Person201 objects and
 * determining distance values 
 * 
 * @author Owen Astrachan
 * @version 2.0 revised for Fall 2025
 * @version 2.1 updated for Spring 2026
 * @version 2.2 updated for Fall 2026
 */

public class Person201Nearby {
    public static void main(String[] args) throws Exception {
        Person201 query = new Person201("Ricardo",46.9994, -122.3921,"Juju");

        String largeFileName = "data/foodlarge.txt";
        String smallFileName = "data/foodsmall.txt";
        String largeURL = "https://courses.cs.duke.edu/fall26/compsci201/data/foodlarge.txt";
        
        Person201[] people = Person201Utilities.read(largeFileName);

        System.out.println("distance\tperson");
        int total = 0;
        int threshold = 17;
            for (Person201 p : people) {
                double d = Person201Utilities.distance(query, p);   
                if (d < threshold) {
                    System.out.printf("%3.2f:\t%s\n",d,p);
                    total++;
                }
            }
        // while (true) {
        //     total = 0;
        //     for (Person201 p : people) {
        //         double d = Person201Utilities.distance(query, p);   
        //         if (d < threshold) {
        //             System.out.printf("%3.2f:\t%s\n",d,p);
        //             total++;
        //         }
        //     }
        //     if (total == 2) {
        //         break;
        //     } else if (total > 2) {
        //         threshold--;
        //     } else {
        //         threshold++;
        //     }
        // }
        // System.out.println("Threshold minus one: " + threshold);
        System.out.println("\nFound " + total + " people near " + query.name() + "\n");
    }
}
