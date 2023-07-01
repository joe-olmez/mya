package com.olmez.mya.temp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Temp {

    public static void main(String[] args) {
        String str = "Joseph, This is test. I live in Kitchener. This is second test. Joseph, Joseph Joseph, Kitchener.";
        var res = countWord(str);
        System.out.println(res);
        finallyExample();

    }

    // Q1: What is immutable? What do you think given codes below?
    // A1: An object is immutable if an object's status cannot change after it is
    // constructed. We prefer them for thread safety, cache performance, and easy
    // maintenance.
    public static void stringExample() {
        String str1 = "Joseph";
        String str2 = "Joseph";
        // Both refer to the same String object in the String pool. (str1==str2).
        String str3 = new String("Joseph");
        // str3 is explicitly created using the new keyword, which creates a new string
        // object in the heap memory. They are different objects with different
        // references. (str1 !=str3)
        if (str1 == str2) {
            // YES
        }
        if (str1 == str3) {
            // NO
        }
    }

    // Q2: What is finally block? What is the result?
    // A2: "finally" is a code block we use along with the "try" keyword. It always
    // runs after the "try" or "catch" block. It executes regardless of whether an
    // exception is thrown or caught.
    public static void finallyExample() {
        try {
            System.out.println("abc");
            return;
        } catch (Exception e) {
            System.out.println("xyz");
        } finally {
            System.out.println("pqr");
        }
    }

    // Q3: Write an algorithm that finds how many times each word occurs in a given
    // A3: Clear punctuation and split text. Collect all the words in a HashMap
    // through a loop by count.
    public static Map<String, Integer> countWord(String text) {
        if (text == null || text.isEmpty()) {
            return Collections.emptyMap();
        }
        // clean punctuation. Note! Must put a space after "a-zA-Z"
        String cleanedStr = text.replaceAll("[^a-zA-Z ]", "");
        String[] words = cleanedStr.split("\\s+");
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }
        return countMap;

    }

}
