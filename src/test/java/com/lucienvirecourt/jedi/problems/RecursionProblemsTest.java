package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lucienvirecourt.jedi.problems.RecursionProblems.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class RecursionProblemsTest {
  @Test
  @DisplayName("Basic recursion problems tests")
  void testRecursiveBasicProblems() {
    List<String> result = new ArrayList<>();
    int n = 5;
    nameList(0, n, result, "Bob Marley");
    List<String> actual = Arrays.asList("Bob Marley", "Bob Marley", "Bob Marley", "Bob Marley", "Bob Marley");
    assertThat("Assert name list", actual, contains(result.toArray()));
    assertThat("Assert name list size", actual, hasSize(n));

    List<Integer> oneToNParam = new ArrayList<>();
    n = 50;
    oneToN(n, oneToNParam);
    List<Integer> oneToNActual = new ArrayList<>();
    for (int i = 1; i <= n; ++i) {
      oneToNActual.add(i);
    }
    assertThat("Assert one to n", oneToNActual, contains(oneToNParam.toArray()));
    assertThat("Assert one to n list size", oneToNActual, hasSize(n));

    List<Integer> nToOneParam = new ArrayList<>();
    n = 5;
    nToOne(n, nToOneParam);
    List<Integer> nToOneActual = new ArrayList<>();
    for (int i = n; i > 0; --i) {
      nToOneActual.add(i);
    }
    assertThat("Assert n to one", nToOneActual, contains(nToOneParam.toArray()));
    assertThat("Assert n to one list size", nToOneActual, hasSize(n));

    int[] reverseParam = new int[]{1, 2, 3, 4, 5, 6};
    reverse(reverseParam);
    int[] reverseActual = new int[]{6, 5, 4, 3, 2, 1};
    assertThat("Assert reversing nums array", reverseActual, is(reverseParam));

    int[] nums = new int[]{1, 2, 3, 4, 5};
    int s = sum(nums);
    assertThat("Assert sum", 15, is(s));

    String str1 = "hello";
    String str2 = "";
    assertThat("Assert palindrome", false, is(palindrome(str1)));
    assertThat("Assert empty string in palindrome", true, is(palindrome(str2)));

    String reversedStr = reverseStr(str1);
    assertThat("Assert string reverse", "olleh", is(reversedStr));
  }

  @Test
  void testSubset() {
    var actual = List.of(List.of(), List.of(1), List.of(2), List.of(1, 2), List.of(3), List.of(1, 3), List.of(2, 3), List.of(1, 2, 3));
    assertThat("Assert subsets", actual, containsInAnyOrder(subsets(new int[]{1, 2, 3}).toArray()));
  }

  @Test
  void testCombinationSum() {
    assertThat("Assert combination sum - 1",
      List.of(List.of(2, 2, 3), List.of(7)),
      containsInAnyOrder(combinationSum(new int[]{2, 3, 6, 7}, 7).toArray())
    );
    assertThat("Assert combination sum - 2",
      List.of(List.of(2, 2, 2, 2), List.of(2, 3, 3), List.of(3, 5)),
      containsInAnyOrder(combinationSum(new int[]{2, 3, 5}, 8).toArray())
    );

    assertThat("Assert combination sum with duplicate",
      List.of(List.of(1, 1, 6), List.of(1, 2, 5), List.of(1, 7), List.of(2, 6)),
      containsInAnyOrder(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8).toArray())
    );

    assertThat("Assert subsets with duplicate",
      List.of(List.of(), List.of(1), List.of(1, 2), List.of(1, 2, 2), List.of(2), List.of(2, 2)),
      containsInAnyOrder(subsetsWithDuplicate(new int[]{1, 2, 2}).toArray())
    );

    List<List<Integer>> expected = permute(new int[]{1, 2, 3});
    assertThat("Assert permutation", 6, is(expected.size()));

    assertThat("Assert word search - true", true, is(wordSearch(new char[][]{
      {'A', 'B', 'C', 'E'},
      {'S', 'F', 'C', 'S'},
      {'A', 'D', 'E', 'E'},
    }, "ABCCED")));

    assertThat("Assert word search - false", false, is(wordSearch(new char[][]{
      {'A', 'B', 'C', 'E'},
      {'S', 'F', 'C', 'S'},
      {'A', 'D', 'E', 'E'},
    }, "ABCB")));
  }
}
