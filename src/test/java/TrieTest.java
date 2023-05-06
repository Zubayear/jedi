import com.zubayear.customds.trie.Trie;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

public class TrieTest {

    Trie trie;

    @BeforeEach
    public void init() {
        trie = new Trie();
        trie.insert("apple");
        trie.insert("apply");
        trie.insert("cat");
    }

    @Test
    public void shouldReturnWords() {
        List<String> actual = trie.dfs(trie.root);
        List<String> expected = Arrays.asList("apply", "apple", "cat");
        assertAll(() -> MatcherAssert.assertThat(actual, containsInAnyOrder(expected.toArray())));
    }

    @Test
    public void shouldReturnTrueForExistingWord() {
        assertTrue(trie.exists("cat"));
        assertFalse(trie.exists("trie"));
    }

    @Test
    public void shouldReturnTrueForExistingPrefix() {
        assertTrue(trie.startsWith("app"));
        assertTrue(trie.startsWith("ca"));
        assertFalse(trie.startsWith("go"));
    }

    @Test
    public void shouldReturnAllWordsWithPrefix() {
        List<String> actual = trie.getWordsWithPrefix("app");
        List<String> expected = Arrays.asList("apply", "apple");
        assertAll(() -> MatcherAssert.assertThat(actual, containsInAnyOrder(expected.toArray())));
    }
}
