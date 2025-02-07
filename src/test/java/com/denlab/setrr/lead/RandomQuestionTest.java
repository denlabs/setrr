package com.denlab.setrr.lead;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RandomQuestionTest {


    @Test
    void isPalindrome() {
        Assertions.assertTrue(isPalindrome("madam"));
        Assertions.assertFalse(isPalindrome("nono"));
        Assertions.assertTrue(isPalindrome("noon"));
        Assertions.assertTrue(isPalindrome("non"));
        Assertions.assertTrue(isPalindrome("babababababab"));
    }

    boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2 + s.length() % 2; i++) {
            final var endIndex = s.length() - i - 1;
            final var match = s.charAt(i) == s.charAt(endIndex);
            System.out.println(
                    String.format("Evaluating: left(%s = %s) : right (%s = %s) :: %s", i, s.charAt(i), endIndex, s.charAt(endIndex), match ? "✓" : "x"));
            if (!match) {
                System.out.println(String.format("The value of string %s is not a palindrom since the value at index %s does not match the value at index %s"
                        , s, i, endIndex));
                return false;
            }
        }
        return true;
    }

    @Test
    void is3Sum() {

        Assertions.assertTrue(is3Sum(20, new int[] {3, 7, 1, 2, 8, 4, 5}));
        Assertions.assertTrue(is3Sum(29, new int[] {3, 7, 1, 2, 8, 4, 5, 14, 11, 6}));
        Assertions.assertFalse(is3Sum(-1, new int[] {1, -1, 0}));
    }


    private boolean is3Sum(final int target, final int[] ints) {
        Arrays.sort(ints);
        System.out.println(Arrays.toString(ints));
        // [2, 8, 11, 14]
        for (int i = 0; i < ints.length - 2; i++) {
            // we'll have to iterate up to i times
            int curval = ints[i]; // the first position

            // traverse the array again
            for (int j = 0; j < ints.length - 1; j++) {
                if (ints[j] == curval) {//skip past
                    System.out.printf("Skipping because %s is equal to %s at index %s\n", ints[j], curval, j);
                    j++;
                }
                final var low = ints[j];
                var k = j + 1;
                if (ints[k] == ints[i]) // skip past
                {
                    k++;
                }
                var high = ints[k];
                int indxsum = curval + low + high;
                System.out.printf("%s + %s + %s = %s\n", curval, low, high, indxsum);
                if (indxsum == target) {
                    System.out.printf("The target %s was match with the following numbers {%s, %s, %s} ✓✓✓✓", target, curval, low, high);
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    void shouldMerge() {
        merge(new int[] {1, 2, 3, 0, 0, 0}, 3, new int[] {2, 5, 6}, 3);
        merge(new int[]{0}, 0, new int[] {1}, 1);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m; i < m + n; i++) {
            nums1[i] = nums2[i - n >= 1 ? i-n: 0];
        }
        boolean swapped = false;
        int temp = 0;
        for (int i = 0; i < nums1.length - 1; i++) {
            for (int j = 0; j < nums1.length -1; j++) {
                if (nums1[j] > nums1[j+1]){
                    temp = nums1[j];
                    nums1[j] = nums1[j + 1];
                    nums1[j + 1] = temp;
                    swapped = true;
                }
            }
                if (!swapped){
                    break;
                }
        }

        System.out.println(Arrays.toString(nums1));
    }

    @Test
    void shouldSort() {
        final var actual = sortColors(new int[] {2, 1, 2, 0, 1, 0, 1, 2});
        System.out.println(Arrays.toString(actual));

    }

    @Test
    void removeElement() {
//        Input: nums = [3,2,2,3], val = 3
//        Output: 2, nums = [2,2,_,_]
        int[] nums = {3,2,2,3};
        int val =3;

        for (int i = 0; i < nums.length ; i++) {
            if(nums[i] == val) {
                nums[i] = 0;
            }
        }
        int k = 0;
        for (int i = 0; i < nums.length ; i++) {
            if (nums[i] != 0)
                k++;
            for (int j = 0; j < nums.length - 1; j++) {
                if (nums[j] < nums[j + 1]) {
                    int t = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = t;
                }
            }
        }

        //return k
        System.out.println(Arrays.toString(nums));
    }

    @Test
    void removeDupes(){
//        Input: nums = [1,1,2]
//        Output: 2, nums = [1,2,_]

        int[] nums = {1,1,2};
//        int[] newArray = new int[nums.length];
//        System.out.println(Arrays.toString(newArray));
//            int k = 0; // the  pointer for newArray
        for (int i = 0; i < nums.length; i++) {
            //travers array again to see if the number exists and if it does don't add i
            boolean contains = false;
            for (int j = 0; j < nums.length; j++) {

                if(nums[j] == nums[i]){
                    contains = true;
                    break;
                }
            }
                if (contains){
                    nums[i] = 0;
//                    k++;
                }
        }
        System.out.println(Arrays.toString(nums));
        //return k;
    }

    public static int[] sortColors(int[] ints) {
        int temp = 0; // temporary place to store the current value
        boolean swapped;

        for (int i = 0; i < ints.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < ints.length - 1; j++) {
                if (ints[j] > ints[j + 1]) {
                    //then swap the values
                    temp = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = temp;
                    swapped = true;
                }

            }
            if (!swapped) {
                break;
            }
        }

        return ints;
    }

    @Test
    void shouldSwapWords() {
        String s = "Hello World  One Two Three Four Five";

        final var split = s.split(" ");
        System.out.println(split.length / 2);
        for (int i = 0; i < split.length - split.length / 2; i++) {
            var s1 = split[i];
            var s2 = split[split.length - 1 - i];
            split[i] = s2;
            split[split.length - 1 - i] = s1;
        }
        System.out.println(Arrays.stream(split).collect(Collectors.joining(" ")));
    }

    @Test
    void happyNumberTest() {
        //1 is a happy number
        int n = 2828;
        List<Integer> sums = new ArrayList<>();
        sums.add(n);

        //repeat until the number sums to 1, or resets itself, indicating a cycle
        List<Integer> l = Arrays.stream(String.valueOf(n).split("")).map(Integer::valueOf).toList();
        int sum = l.stream()
                .mapToInt(i -> i * i).sum();
        sums.add(sum);
        while (sum != 1) {
            var list = Arrays.stream(String.valueOf(sum).split("")).map(Integer::valueOf).toList();
            System.out.println(list);
            sum = list.stream()
                    .mapToInt(i -> i * i).sum();

            if (sums.contains(sum)) {
                break;
            }
            sums.add(sum);
        }

        if (sum == 1) {
            System.out.printf("TRUE: %s\n", sum);
        } else {
            System.out.printf("FALSE, number was %s and is not happy\n", sum);
        }
    }


//    @Test
//    void removeNthNode(){
//        //given
//        var actual = new LinkedListNode(Stream.of(43, 68, 11, 5, 69, 37, 70, 121, 1).toList());
//        var expected = new LinkedList<>(actual);
//        expected.remove(5); // this is the fourth from the right...
//        assertThat(removeNthNode(4, actual), containsInAnyOrder(expected.toArray(Integer[]::new)));
//
//        expected = new LinkedList<>(actual);
//        expected.remove(2); // remove 11
//        assertThat(removeNthNode(7, actual), containsInAnyOrder(expected.toArray(Integer[]::new)));
//    }

//    static List<?> removeNthNode(final int node, final LinkedListNode list) {
//        final var integers = new LinkedList<Integer>(list);
//        var left = 0;
//        var right = node;
//
//        while (right < list.size()) {
//            left++;
//            right++;
//        }
//
//        integers.remove(left);
//        return integers;
//    }


}

class LinkedListNode {
    public int data;

    public LinkedListNode next;

    // Constructor will be used to make a LinkedListNode type object
    public LinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }
}