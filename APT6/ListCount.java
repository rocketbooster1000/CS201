// package APT6;

public class ListCount {
    public int count (ListNode list) {
        // replace statement below with code you write
        if (list == null) return 0;
        return 1 + count(list.next);
    }
}
