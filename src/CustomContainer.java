import java.io.Serializable;
import java.util.Iterator;

class CustomContainer<T> implements Iterable<T>, Serializable {
    private Node<T> head;

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    private static class Node<T> implements Serializable {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }
}
