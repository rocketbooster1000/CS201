// package APT4;

import java.util.ArrayList;

public class Anonymous {
    public int howMany(String[] headlines, String[] messages) {
        ArrayList<int[]> map = new ArrayList<>();
        // for (String s : headlines) {
        String s = String.join("", headlines);
            s = s.toLowerCase();
            int[] art = new int[256];
            for (char c : s.toCharArray()) {
                art[c] += 1;
            }

            // for (int i = 'a'; i < 'z' + 1; i++) {
            //     System.out.print((char)(i) + ": " + art[i] + " | ");
            // }
            // System.out.println();
            map.add(art);
            // System.out.println(arrToString(art));
        // }

        // for (int[] arr : map) {
        //     printCharMap(arr);
        // }

        int numValid = 0;
        int index = 0;
        for (String msg : messages) {
            msg = msg.toLowerCase();
            int[] arr = new int[256];
            for (int c : msg.toCharArray()) {
                arr[c] += 1;
            }
            // System.out.println("");
            // printCharMap(arr);
            boolean valid = true;
            for (int headlineIndex = 0; headlineIndex < map.size(); headlineIndex++) {
                boolean wordValid = true;
                for (int i = 0; i < arr.length; i++) {
                    if (i == ' ') {
                        continue;
                    }
                    if (arr[i] > map.get(headlineIndex)[i]) {
                        // System.out.println("headline: " + headlineIndex);
                        // System.out.print(arr[i] + " | " + i + " || ");
                        // System.out.print("map: " + map.get(headlineIndex)[i] + " | ");
                        // System.out.print((char)(i)+ " ");
                        if (headlineIndex == map.size() - 1) valid = false;
                        wordValid = false;
                        // System.out.println(valid);
                        break;
                    }
                    // System.out.println(valid);

                }
                // System.out.println();
                // System.out.println(headlineIndex);
                if (wordValid) break;
                
            }

            if (valid) {
                numValid++;
            }
            index++;
            // System.out.println("\n--------------------");
        }
        return numValid;
    }

    public static void main(String[] args) {
        String[] headlines = 
   {"abcdef","abcdef"}

        ;
        String[] messages = 
   {"AaBbCc","aabbbcc","   ","FADE"}

        ;

        System.out.println(new Anonymous().howMany(headlines, messages));
    }

    private void printCharMap(int[] arr) {
                    for (int i = 'a'; i < 'z' + 1; i++) {
                System.out.print((char)(i) + ": " + arr[i] + " | ");
            }
            System.out.println();
    }
}
