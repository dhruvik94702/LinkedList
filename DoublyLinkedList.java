import java.util.ArrayList;
import java.util.List;

class DoublyLinkedList {

    /* ----------  node definition ---------- */
    private class Node {
        int data;
        Node prev, next;
        Node(int data) {
            this.data = data;
        }
    }

    /* ----------  list fields  ---------- */
    private Node head;
    private final List<String> operationsLog = new ArrayList<>();

    /* ----------  helpers  ---------- */
    private void log(String text) {
        operationsLog.add(text);
    }

    /* ----------  CRUD operations  ---------- */

    // 1. Add at the beginning
    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (head != null) {
            newNode.next = head;
            head.prev  = newNode;
        }
        head = newNode;
        log("addFirst  →  " + data);
    }

    // 2. Add at the end
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            log("addLast   →  " + data + "  (list was empty)");
            return;
        }
        Node temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = newNode;
        newNode.prev = temp;
        log("addLast   →  " + data);
    }

    // 3. Add at a specific 0‑based index
    public void addAtPosition(int index, int data) {
        if (index < 0) { log("addAtPos  →  invalid index " + index); return; }
        if (index == 0) { addFirst(data); return; }

        Node temp = head;
        for (int i = 0; i < index - 1 && temp != null; i++) temp = temp.next;
        if (temp == null) {
            log("addAtPos  →  index " + index + " out of bounds");
            return;
        }
        Node newNode = new Node(data);
        newNode.next = temp.next;
        newNode.prev = temp;
        if (temp.next != null) temp.next.prev = newNode;
        temp.next = newNode;
        log("addAtPos  →  index " + index + ", value " + data);
    }

    // 4. Delete a node at a specific 0‑based index
    public void deleteAtPosition(int index) {
        if (head == null || index < 0) {
            log("deletePos →  invalid index " + index);
            return;
        }
        if (index == 0) {
            log("deletePos →  removed head " + head.data);
            head = head.next;
            if (head != null) head.prev = null;
            return;
        }
        Node temp = head;
        for (int i = 0; i < index && temp != null; i++) temp = temp.next;
        if (temp == null) {
            log("deletePos →  index " + index + " out of bounds");
            return;
        }
        log("deletePos →  index " + index + ", value " + temp.data);
        if (temp.prev != null) temp.prev.next = temp.next;
        if (temp.next != null) temp.next.prev = temp.prev;
    }

    /* ----------  traversal / reporting  ---------- */

    public void printList() {
        Node temp = head;
        System.out.print("Doubly Linked List: ");
        while (temp != null) {
            System.out.print(temp.data + (temp.next != null ? " <-> " : ""));
            temp = temp.next;
        }
        System.out.println(temp == null ? " null" : "");
    }

    public void printOperationsHistory() {
        System.out.println("=== Operations History ===");
        if (operationsLog.isEmpty()) {
            System.out.println("No operations recorded.");
            return;
        }
        for (int i = 0; i < operationsLog.size(); i++) {
            System.out.println((i + 1) + ". " + operationsLog.get(i));
        }
        System.out.println("==========================");
    }

    /* ----------  demo / test  ---------- */
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();

        dll.addFirst(10);        // 10
        dll.addLast(20);         // 10 <-> 20
        dll.addAtPosition(1,15); // 10 <-> 15 <-> 20
        dll.addFirst(5);         // 5 <-> 10 <-> 15 <-> 20
        dll.printList();

        dll.deleteAtPosition(2); // 5 <-> 10 <-> 20
        dll.printList();

        dll.printOperationsHistory();
    }
}
