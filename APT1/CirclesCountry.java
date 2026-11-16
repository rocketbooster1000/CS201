// package APT1;

import java.util.ArrayList;

public class CirclesCountry {
    public int leastBorders(int[] x, int[] y, int[] r, 
                            int x1, int y1, int x2, int y2) {
        // you write code here
        int numCircles = x.length;

        int districtCrossings = 0;

        for (int i = 0; i < numCircles; i++) {
            boolean startInside = Math.hypot(x1 - x[i], y1 - y[i]) < r[i];
            boolean endInside = Math.hypot(x2 - x[i], y2 - y[i]) < r[i];
            if (startInside != endInside) {
                districtCrossings++;
            }

        }
        return districtCrossings;
    }
}
