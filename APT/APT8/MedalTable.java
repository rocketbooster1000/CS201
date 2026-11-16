import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class MedalTable {
    public String[] generate(String[] results) {
        HashMap<String, Country> map = new HashMap<>();

        for (String s : results) {
            String[] arr = s.split(" ");
            int i = 0;
            for (String c : arr) {
                if (!map.containsKey(c)) {
                    map.put(c, new Country(c));
                }
                if (i == 0) map.get(c).gold++;
                else if (i == 1) map.get(c).silver++;
                else map.get(c).bronze++;
                i++;
            }
        }

        ArrayList<Country> list = new ArrayList<>();

        for (Country country : map.values()) {
            list.add(country);
        }
        
        Collections.sort(list,
            Comparator.comparingInt(Country::getGold).reversed()
                .thenComparing(
                    Comparator.comparingInt(Country::getSilver).reversed())
                .thenComparing(
                    Comparator.comparingInt(Country::getBronze).reversed())
                .thenComparing(
                    Comparator.comparing((c) -> c.country)
                )
        );

        String[] arr = new String[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i).toString();
        }
        return arr;
    }

    public static class Country {
        public String country;
        public int gold;
        public int silver;
        public int bronze;
        public Country(String country) {
            this.country = country;
        }

        public String toString() {
            return String.format("%s %d %d %d", country, gold, silver, bronze);
        }

        public int getGold() {return this.gold;}

        public int getSilver() {return this.silver;}

        public int getBronze() {return this.bronze;}
    }
}
