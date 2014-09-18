public class Stack<T> {
  public Node<T> top;

  public T pop() {
    Node<T> node = top;
    top = top.next;
    return node.data;
  }

  public void push(T data) {
    Node<T> node = new Node<T>(data);
    node.next = top;
    top = node;
  }

  public T peek() {
    if (top != null) {
      return top.data;
    }
    return null;
  }

  // Internal class
  public class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T data) {
      this.data = data;
      this.next = null;
    }
  }


}
