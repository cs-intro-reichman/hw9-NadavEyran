public class LinkedList {
    private Node first;
    private Node last;
    private int size;

    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }

    public int getSize() {
        return size;
    }

    public void add(int index, MemoryBlock block) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index must be between 0 and size");
        }
        Node node = new Node(block);
        if (index == 0) {
            node.next = first;
            first = node;
            if (size == 0) last = node;
        } else if (index == size) {
            last.next = node;
            last = node;
        } else {
            Node p = first;
            for (int i = 0; i < index - 1; i++) {
                p = p.next;
            }
            node.next = p.next;
            p.next = node;
        }
        size++;
    }

    public void addFirst(MemoryBlock block) {
        Node node = new Node(block);
        node.next = first;
        first = node;
        if (size == 0) last = node;
        size++;
    }

    public void addLast(MemoryBlock block) {
        Node node = new Node(block);
        if (size == 0) {
            first = last = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
    }

    public void remove(Node node) {
        if (first == null) return;
        if (first == node) {
            first = first.next;
            size--;
            return;
        }
        Node p = first;
        while (p.next != null) {
            if (p.next == node) {
                p.next = p.next.next;
                if (p.next == null) last = p;
                size--;
                return;
            }
            p = p.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node p = first;
        while (p != null) {
            result.append(p.block.toString());
            if (p.next != null) result.append(", ");
            p = p.next;
        }
        result.append("]");
        return result.toString();
    }
}
