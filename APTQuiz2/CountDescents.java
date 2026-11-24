// package APTQuiz2;

public class CountDescents {
    public int count(ListNode list) {
        int descents = 0;
        while (list.next != null) {
            if (list.info > list.next.info) descents++;
            list = list.next;
        }
        return descents;
    }
}
