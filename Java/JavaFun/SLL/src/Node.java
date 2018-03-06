public class Node {
    Node next;
    int val;

    public Node(int val) {
        this.next = null;
        this.val = val;
    }

    public int getVal() {
        return this.val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
