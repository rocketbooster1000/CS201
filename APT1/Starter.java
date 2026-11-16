// package APT1;

public class Starter {
    // public static void main(String[] args) {
    //     String[] arr = {"zebras", "zebra", "aardvarks", "yak", "yaks", "zebras", "elephants", "zebras", "zebra", "aardvarks", "yak", "yaks", "zebras", "elephants" };
    //     String str = "e";
    //     Starter s = new Starter();
    //     System.out.println(s.begins(arr, str));
    // }
    public int begins(String[] words, String first) {
        // replace this code
        int num = 0;
        for (int i = 0; i < words.length; i++) {
            boolean skip = false;
            for (int j = 0; j < i; j++) {
                if (words[i].equals(words[j])){
                    skip = true;
                    break;
                }
            }
            if (skip) continue;
            else if (words[i].substring(0, 1).equals(first))
                num++;
        }
        return num;
    }
}