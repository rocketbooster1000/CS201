// package APTQuiz1;

import java.util.HashMap;
import java.util.Map;

public class BestRecord {
    public String best(String[] games) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String s : games) {
            String[] arr = s.split("-");
            map.put(arr[0], map.getOrDefault(arr[0], 0) + 1);
            map.put(arr[1], map.getOrDefault(arr[1], 0) - 1);
        }
        
        String maxKey = "";
        int max = 0;

        for (String s : map.keySet()) {
            if (map.get(s) > max) {
                max = map.get(s);
                maxKey = s;
            }
        }

        return maxKey + ":" + map.get(maxKey);
    }
}
