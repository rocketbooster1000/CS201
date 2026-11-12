public class LinkStrand implements IDnaStrand{
    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private Node myCurrent;
    private int myLocalIndex;

    public LinkStrand() {
        this("");
    }

    public LinkStrand(String strand) {
        this.initialize(strand);
    }

    @Override
    public long size() {
        return this.mySize;
    }

    @Override
    public void initialize(String source) {
        this.myFirst = new Node(source, null);
        this.myLast = myFirst;
        this.mySize = source.length();
        this.myAppends = 0;
        this.myCurrent = myFirst;
        this.myIndex = 0;
        this.myLocalIndex = 0;
    }

    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        Node temp = myLast;
        myLast = new Node(dna, null);
        temp.next = myLast;
        myAppends += 1;
        mySize += dna.length();
        return this;
    }

    @Override
    public IDnaStrand reverse() {
        LinkStrand newStrand = new LinkStrand();
        Node pointer = myFirst;

        Node newLast = new Node(new StringBuilder(myFirst.info).reverse().toString(), null);
        Node copy = newLast;

        while (pointer.next != null) {
            pointer = pointer.next;
            Node copyHead = new Node(new StringBuilder(pointer.info).reverse().toString(), copy);
            copy = copyHead;
        }

        newStrand.myFirst = copy;
        newStrand.myLast = newLast;
        newStrand.myAppends = this.myAppends;
        newStrand.mySize = this.mySize;
        newStrand.myCurrent = newStrand.myFirst;
        newStrand.myIndex = 0;
        newStrand.myLocalIndex = 0;

        return newStrand;
    }

    @Override
    public int getAppendCount() {
        return this.myAppends;
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index > mySize - 1) 
            throw new IndexOutOfBoundsException();

        if (index < this.myIndex) {
            this.myCurrent = this.myFirst;
            this.myLocalIndex = 0;
            this.myIndex = 0;
        }

        while (true) {
            if (this.myIndex - this.myLocalIndex + this.myCurrent.info.length() > index) {
                int startOfNode = this.myIndex - this.myLocalIndex;
                this.myIndex = index;
                this.myLocalIndex = index - startOfNode;
                return myCurrent.info.charAt(myLocalIndex);
            }
            this.myIndex = this.myIndex - this.myLocalIndex + this.myCurrent.info.length();
            this.myLocalIndex = 0;
            myCurrent = myCurrent.next;
        }
    }

    @Override 
    public String toString() {
        StringBuilder b = new StringBuilder();
        Node pointer = myFirst;
        while (pointer != null) {
            b.append(pointer.info);
            pointer = pointer.next;
        }
        return b.toString();
    }
    
    private static class Node {
        public String info;
        public Node next;
        public Node(String strand, Node next) {
            this.info = strand;
            this.next = next;
        }
    }
}
