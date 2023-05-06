import com.zubayear.customds.linkedlist.SinglyLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SinglyLinkedListTest {

    SinglyLinkedList<Character> singlyLinkedList;

    @BeforeEach
    public void init() {
        singlyLinkedList = new SinglyLinkedList<>();
    }

    @Test
    public void shouldInsertAtHead() {
        singlyLinkedList.insertAtHead('A');
        singlyLinkedList.insertAtHead('B');
        singlyLinkedList.insertAtHead('C');
        singlyLinkedList.insertAtHead('D');
        singlyLinkedList.insertAtHead('E');
        List<Character> actual = singlyLinkedList.getList();
        List<Character> expected = new LinkedList<>(Arrays.asList('E', 'D', 'C', 'B', 'A'));
        Assertions.assertIterableEquals(actual, expected);
    }

    @Test
    public void shouldInsertAtTail() {
        singlyLinkedList.insertAtTail('A');
        singlyLinkedList.insertAtTail('B');
        singlyLinkedList.insertAtTail('C');
        singlyLinkedList.insertAtTail('D');
        singlyLinkedList.insertAtTail('E');
        List<Character> actual = singlyLinkedList.getList();
        List<Character> expected = new LinkedList<>(Arrays.asList('A', 'B', 'C', 'D', 'E'));
        Assertions.assertIterableEquals(actual, expected);
        singlyLinkedList.printList();
    }

    @Test
    public void shouldInsertAtPos() {
        singlyLinkedList.insertAt('F', 2);
        List<Character> actual = singlyLinkedList.getList();
        List<Character> expected = new LinkedList<>(Arrays.asList('A', 'B', 'F', 'C', 'D', 'E'));
        Assertions.assertIterableEquals(actual, expected);
    }

    @Test
    public void shouldDeleteANode() {
        assertTrue(singlyLinkedList.delete('C'));
        List<Character> actual = singlyLinkedList.getList();
        List<Character> expected = new LinkedList<>(Arrays.asList('A', 'B', 'D', 'E'));
        Assertions.assertIterableEquals(actual, expected);
    }
}
