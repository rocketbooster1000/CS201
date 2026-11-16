// package APT3;

public class TrueSpace {
    public long calculateSpace(int[] sizes, int clusterSize) {
        // fill in code here
        long clusters = 0;
        long lClusterSize = (long) clusterSize;

        for (int size : sizes) {
            long lSize = (long) size;
            long localClusterCount = lSize / lClusterSize;
            if (lSize % lClusterSize > 0) localClusterCount++;
            
            clusters += localClusterCount;
        }
        return clusters * lClusterSize;
    }

//     public static void main(String[] args) {
//         int[] sizes = 
// {4096, 33792, 76800}

//         ;
//         int clusterSize = 
// 1024
//         ;

//         System.out.println(new TrueSpace().calculateSpace(sizes, clusterSize));
//     }
}
