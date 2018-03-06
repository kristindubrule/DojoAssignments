public class SLL {
    Node head;

    public SLL() {
        this.head = null;
    }

    public void addFront(int val) {
        Node n1 = new Node(val);
        n1.next = this.head;
        this.head = n1;
    }

    public void addBack(int val) {
        Node n1 = new Node(val);
        // If the list is empty, there won't be a next to check
        if (this.head == null) {
            this.head = n1;
        } else {
            Node runner = this.head.next;
            while (runner.next != null) {
                runner = runner.next;
            }
            runner.next = n1;
        }
    }

    public int removeBack() {
        int temp;

        if (this.head == null) {
            return -1;
        }
        // Only one node in the list
        if (this.head.next == null) {
            temp = this.head.getVal();
            this.head = null;
        } else { // We have at least two nodes
            Node runner = this.head;
            // With two nodes, this while loop never runs
            while (runner.next.next != null) {
                runner = runner.next;
            }
            temp = runner.next.getVal();
            runner.next = null;
        }
        return temp;
    }

    public void print() {
        Node runner = this.head;
        while (runner != null) {
            System.out.println(runner.getVal());
            runner = runner.next;
        }
    }

    public Node find(int number) {
        Node runner = this.head;
        Node found = null;
        while (runner != null) {
            if (runner.getVal() == number) {
                found = runner;
            }
            runner = runner.next;
        }
        return found;
    }

    public void removeAt(int n) {
        int counter = 1;
        Node runner = this.head;
        // If the list is empty, nothing to remove
        if (this.head != null) {
            // If there's only one node or we're removing the first node
            if (this.head.next == null || n == 0) {
                // Works even if there's only one node, because this.head.next = null
                // Couldn't do this below, because head is special (setting head not next)
                this.head = this.head.next;
            } else {
                while (runner.next != null && counter != n) {
                    runner = runner.next;
                    counter++;
                }
                if (counter == n) {
                    runner.next = runner.next.next;
                }
            }
        }
    }
}
