// package APT4;

public class Encryption {
    public String encrypt(String message) {
        String s = "";
        int[] map = new int[256];

        int pointer = 'a';
        for (char c : message.toCharArray()) {
            if (map[c] == 0) {
                s += (char)(pointer);
                map[c] = pointer;
                pointer++;
            } else {
                s += (char)(map[c]);
            }
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(new Encryption().encrypt("encryption"));
    }
}
