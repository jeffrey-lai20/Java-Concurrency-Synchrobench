package soft3410;

/**
 * A basic linked list implementation of a set.
 */
public class LinkedListIntSet
    extends contention.abstractions.AbstractCompositionalIntSet {
  final private Node head;
  final private Node tail;

  public LinkedListIntSet() {
    head = new Node(Integer.MIN_VALUE);
    tail = new Node(Integer.MAX_VALUE);
    head.setNext(tail);
  }

  /**
   * Add a new int to the set.
   * 
   * @param value
   *          The new int to be added
   * @return false if the int already exists in the set
   */
  public boolean addInt(int value) {
    Node predecessor = findPredecessor(value);
    Node current = predecessor.getNext();

    if (current.getValue() == value) {
      return false;
    }
    Node node = new Node(value, current);
    predecessor.setNext(node);
    return true;
  }

  /**
   * Remove an int from the set.
   * 
   * @param value
   *          The int to be removed
   * @return false if the int did not exist in the set
   */
  public boolean removeInt(int value) {
    Node predecessor = findPredecessor(value);
    Node current = predecessor.getNext();

    if (current.getValue() != value) {
      return false;
    }
    predecessor.setNext(current.getNext());
    return true;
  }

  /**
   * Check if an int is a member of the set.
   * 
   * @param value
   *          The int to be checked for
   * @return true if value exists in the set
   */
  public boolean containsInt(int value) {
    Node current = head.getNext();
    while (current.getValue() < value) {
      current = current.getNext();
    }

    return (current.getValue() == value);
  }

  /**
   * Return the size of the set.
   * @return the size of the set
   */
  public int size() {
    int size = 0;
    Node current = head.getNext();
    while (current != tail) {
      current = current.getNext();
      ++size;
    }
    return size;
  }

  /**
   * Find and return the predecessor to the node that would contain the given value.
   * @param value The value to search for.
   * @return the Node containing the largest value smaller than the search value
   */
  private Node findPredecessor(int value) {
    Node predecessor = head;
    Node curr = head.getNext();
    while (true) {
      // Found a match, so return the predecessor
      if (curr.value >= value) {
        return predecessor;
      }
      predecessor = curr;
      curr = curr.getNext();
    }
  }

  /**
   * Empty the set.
   */
  public void clear() {
    head.setNext(tail);
  }

  class Node {

    final private int value;
    private Node next;

    Node(int value, Node next) {
      this.value = value;
      this.next = next;
    }

    Node(int value) {
      this(value, null);
    }

    int getValue() {
      return value;
    }

    void setNext(Node next) {
      this.next = next;
    }

    Node getNext() {
      return next;
    }
  }

}

