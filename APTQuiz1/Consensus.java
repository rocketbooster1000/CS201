// package APTQuiz1;

import java.util.ArrayList;
import java.util.Collections;

public class Consensus {
    public String consensus(String[] words){
        ArrayList<int[]> map = new ArrayList<>();

        for (int i = 0; i < words[0].length(); i++) {
            int[] arr = new int[256];

            for (int j = 0; j < words.length; j++) {
                arr[words[j].charAt(i)] += 1;
            }

            map.add(arr);
        }

        String s = "";
        
        for (int i = 0; i < map.size(); i++) {
            int max = 0;
            int indexOfMax = 0;
            for (int j = 0; j < map.get(i).length; j++) {
                if (max < map.get(i)[j]) {
                    max = map.get(i)[j];
                    indexOfMax = j;
                }
            }
            s += (char)(indexOfMax);
        }

        return s;
    }

    public static void main(String[] args) {
        String[] words = {
            "cat", "bat", "car", "bar"
        };

        System.out.println(new Consensus().consensus(words));
    }
}
