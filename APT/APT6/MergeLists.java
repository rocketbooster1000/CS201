// package APT6;

public class MergeLists {
    public ListNode weave (ListNode a, ListNode b) {
        ListNode first = a;
        while (a != null) {
            ListNode nextA = a.next;
            ListNode nextB = b.next;
            a.next = b;
            b.next = nextA;
            b = nextB;
            a = nextA;
        }
        return first;
    }
}
