import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashListAutocomplete implements Autocompletor {
    private static final int MAX_PREFIX = 10;
    private Map<String, List<Term>> myMap;
    private int mySize;

    public HashListAutocomplete(String[] terms, double[] weights) {
		if (terms == null || weights == null) {
			throw new NullPointerException("One or more arguments null");
		}

		if (terms.length != weights.length) {
			throw new IllegalArgumentException("terms and weights are not the same length");
		}
		initialize(terms,weights);
    }

    @Override
    public List<Term> topMatches(String prefix, int k) {
        prefix = prefix.substring(0, Math.min(prefix.length(), MAX_PREFIX));
        if (!myMap.containsKey(prefix)) return new ArrayList<>();
        return myMap.get(prefix).subList(0, Math.min(k, myMap.get(prefix).size()));
    }

    @Override
    public void initialize(String[] terms, double[] weights) {
        myMap = new HashMap<>();
        mySize = 0;
        for (int i = 0; i < terms.length; i++) {
            Term t = new Term(terms[i], weights[i]);
            for (int j = 0; j <= Math.min(terms[i].length(), MAX_PREFIX); j++) {
                String prefix = terms[i].substring(0, j);
                mySize += BYTES_PER_CHAR * terms[i].length();
                mySize += BYTES_PER_DOUBLE;
                if (!myMap.containsKey(prefix)) {
                    myMap.put(prefix, new ArrayList<>());
                    mySize += BYTES_PER_CHAR * prefix.length();
                }
                myMap.get(prefix).add(t);
            }
        }
        for (List<Term> list : myMap.values()) {
            list.sort(Comparator.comparingDouble(Term::getWeight).reversed());
        }
    }

    @Override
    public int sizeInBytes() {
        return mySize;
    }  
}

