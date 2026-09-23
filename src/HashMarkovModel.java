import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HashMarkovModel extends BaseMarkovModel{
    private HashMap<String, String> map = new HashMap<>();

    public HashMarkovModel(int size) {
        super(size);
    }

    @Override 
    public void processTraining() {
        map.clear();
        for (int i = 0; i < myWordSequence.size() - this.myModelSize; i++) {
            // List<String> tempList = new ArrayList<>();
            String s = "";
            for (int j = 0; j < myModelSize; j++) {
                // tempList.add(myWordSequence.get(i + j));
                s += myWordSequence.get(i + j) + " ";
            }
            map.put(s, map.getOrDefault(s, "") + myWordSequence.get(i + myModelSize) + " ");
        }
        // for (String key : map.keySet()) {
        //     System.out.println("key: " + key + " | value: " + map.get(key));
        // }
    }

    @Override 
    protected List<String> getFollows(List<String> context) {
        String s = "";
        for (String st : context) {
            s += st + " ";
        }
        String t = map.getOrDefault(s, "");
        String[] arr = t.split(" ");
        // System.out.print("Context: " + context);
        
        ArrayList<String> al = new ArrayList<String>(Arrays.asList(arr));
        // System.out.println(" | follows: " + al);
        return al;
    }
}
