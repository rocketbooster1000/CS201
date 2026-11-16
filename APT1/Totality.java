package APT1;

public class Totality {
    public int sum(int[] a, String stype) {
        // you add code here
        int sum = 0;
        int oddSum = 0;
        int evenSum = 0;
        for (int i = 0; i < a.length; i++) {
            if (i % 2 == 0) oddSum += a[i];
            else evenSum += a[i];
            sum += a[i];
        }

        if (stype.equals("odd")) return evenSum;
        else if (stype.equals("even")) return oddSum;
        return sum;
    }
 }