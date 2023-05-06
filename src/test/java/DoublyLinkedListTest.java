import com.zubayear.customds.linkedlist.DoublyLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoublyLinkedListTest {

    DoublyLinkedList<Integer> doublyLinkedList;

    @BeforeEach
    public void init() {
        doublyLinkedList = new DoublyLinkedList<>(0);
    }

    @Test
    public void shouldInsertAtHead() {
        doublyLinkedList.insertAtHead(1);
        doublyLinkedList.insertAtHead(2);
        doublyLinkedList.insertAtHead(3);
        List<Integer> actual = doublyLinkedList.getList(true);
        List<Integer> expected = new LinkedList<>(Arrays.asList(3, 2, 1));
        Assertions.assertIterableEquals(actual, expected);
    }

    @Test
    public void shouldInsertAtTail() {
        doublyLinkedList.insertAtTail(1);
        doublyLinkedList.insertAtTail(2);
        doublyLinkedList.insertAtTail(3);
        List<Integer> actual = doublyLinkedList.getList(true);
        List<Integer> expected = new LinkedList<>(Arrays.asList(1, 2, 3));
        Assertions.assertIterableEquals(actual, expected);
    }

    @Test
    public void shouldDeleteNode() {
        doublyLinkedList.insertAtTail(1);
        doublyLinkedList.insertAtTail(2);
        doublyLinkedList.insertAtTail(3);
        assertTrue(doublyLinkedList.delete(2));
        List<Integer> actual = doublyLinkedList.getList(false);
        List<Integer> expected = new LinkedList<>(Arrays.asList(3, 1));
        Assertions.assertIterableEquals(actual, expected);
    }
}
