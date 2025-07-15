class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {
    private Node head;

    // Add a new node at the end of the list
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Add a new node at a specific position
    public void addAtPosition(int data, int position) {
        Node newNode = new Node(data);
        if (position < 0) {
            System.out.println("Position cannot be negative");
            return;
        }
        if (position == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node current = head;
        for (int i = 0; i < position - 1; i++) {
            if (current == null) {
                System.out.println("Position out of bounds");
                return;
            }
            current = current.next;
        }

        if (current == null) {
            System.out.println("Position out of bounds");
            return;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    // Remove a node by value
    public void remove(int data) {
        if (head == null) return;

        if (head.data == data) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    // Display the list
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.display(); // Output: 1 -> 2 -> 3 -> null

        list.addAtPosition(4, 1); // Add 4 at position 1
        list.display(); // Output: 1 -> 4 -> 2 -> 3 -> null

        list.addAtPosition(5, 0); // Add 5 at position 0
        list.display(); // Output: 5 -> 1 -> 4 -> 2 -> 3 -> null

        list.addAtPosition(6, 10); // Attempt to add at an out-of-bounds position
        list.remove(2);
        list.display(); // Output: 5 -> 1 -> 4 -> 3 -> null
    }
}
