import com.zubayear.customds.bintree.BinaryTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeTest {

    BinaryTree<Character> binaryTree;

    @BeforeEach
    public void init() {
        binaryTree = new BinaryTree<>();
        binaryTree.insert('A');
        binaryTree.insert('B');
        binaryTree.insert('C');
        binaryTree.insert('D');
        binaryTree.insert('E');
        binaryTree.insert('F');
    }

    @Test
    public void shouldDoBFSTraversal() {
        List<List<Character>> actual = binaryTree.bfs(binaryTree.root);
        List<List<Character>> expected = new ArrayList<>(List.of(List.of('A'), List.of('B', 'C'), List.of('D', 'E', 'F')));
        Assertions.assertIterableEquals(actual, expected);
    }

    @Test
    public void shouldDoDFSTraversal() {
        List<Character> actual = binaryTree.dfs(binaryTree.root);
        List<Character> expected = new ArrayList<>(List.of('A', 'B', 'D', 'E', 'C', 'F'));
        Assertions.assertIterableEquals(actual, expected);
    }

    @Test
    public void shouldDoIterativePreorderTraversal() {
        binaryTree.preorderIter(binaryTree.root);
    }
}
