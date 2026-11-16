// package APT1;

public class AccessLevel {
    public String canAccess(int[] rights, int minPermission) {
        // fill in code here
        String s = "";
        for (int i = 0; i < rights.length; i++) {
            s = s + ((rights[i] >= minPermission) ? "A" : "D");
        }
        return s;
    }
}