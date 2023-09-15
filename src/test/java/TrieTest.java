import com.zubayear.customds.trie.Trie;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

public class TrieTest {

    Trie trie;

//    @BeforeEach
    public void init() {
        trie = new Trie();
        trie.insert("apple");
        trie.insert("apply");
        trie.insert("cat");
    }

    @Test
    void contextLoads() {
        List<String> randomStrings = Arrays.asList("01877063359", "01385988004", "01873185054", "01791891687", "01776084486", "01807526932");
        Set<String> mnpList = new HashSet<>(), dndList = new HashSet<>();
        mnpList.add("01385988004");
        dndList.add("01877063359");
        dndList.add("01385988004");

        List<String> result = new ArrayList<>();
        for (String s : randomStrings) {
            // [01877063359, 01385988004, 01873185054, 01791891687, 01776084486, 01807526932]
            if (s.startsWith("017") || s.startsWith("013")) {
                // need to check if this number exists in tbl_mnp
                if (!mnpList.contains(s)) { // false
                    // ignore
                    continue;
                } else {
                    // exists in tbl_mnp
                    // check if it exists in tbl_dnd
                    if (dndList.contains(s)) {
                        continue;
                    } else {
                        // add to result
                        result.add(s);
                    }
                }
            } else {
                // check tbl_dnd
                // if it exists in tbl_dnd continue
                if (dndList.contains(s)) { // true
                    continue;
                }
                // add to result
                result.add(s);
            }
        }
        // valid -> 018 & not in tbl_dnd
        // valid -> 017,013 & exists in tbl_mnp & not exists in tbl_dnd
        for (String s : randomStrings) {
            boolean notInDnd = !dndList.contains(s);
            boolean notMnp = !s.startsWith("018") || !s.startsWith("016");
            boolean valid = (notMnp && mnpList.contains(s) && notInDnd) || (!notMnp && notInDnd);
            if (valid) {
                result.add(s);
            }
        }
        System.out.println(result);
    }

    public String storedProcedure(String numbers) {
        List<String> result = new ArrayList<>();
        Set<String> mnpList = new HashSet<>(), dndList = new HashSet<>();
        mnpList.add("01385988004");
        dndList.add("01877063359");
        dndList.add("01385988004");
        String[] randomStrings = numbers.split(",");
        for (String s : randomStrings) {
            boolean notInDnd = !dndList.contains(s);
            boolean mnp = s.startsWith("017") || s.startsWith("013");
            boolean valid = (mnp && mnpList.contains(s) && notInDnd) || (!mnp && notInDnd);
            if (valid) {
                result.add(s);
            }
        }
        return String.join(",", result);
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
