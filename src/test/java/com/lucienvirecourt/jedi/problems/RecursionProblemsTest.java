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

    int[] reverseParam = new int[]{1,2,3,4,5,6};
    reverse(reverseParam);
    int[] reverseActual = new int[]{6,5,4,3,2,1};
    assertThat("Assert reversing nums array", reverseActual, is(reverseParam));

    int[] nums = new int[]{1,2,3,4,5};
    int s = sum(nums);
    assertThat("Assert sum", 15, is(s));

    String str1 = "hello";
    String str2 = "";
    assertThat("Assert palindrome", false, is(palindrome(str1)));
    assertThat("Assert empty string in palindrome", true, is(palindrome(str2)));

    String reversedStr = reverseStr(str1);
    assertThat("Assert string reverse", "olleh", is(reversedStr));

    assertThat("Assert subsequence", subsequence(new int[]{1,2,3,4}), hasSize(16));
  }
}
