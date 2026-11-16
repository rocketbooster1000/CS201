// package APT6;

public class ListSum {
    public int sum(ListNode list, int thresh) {
        if (list == null) return 0;
        if (list.info <= thresh) return sum(list.next, thresh);
        return list.info + sum(list.next, thresh);
    }
}
