package com.company;

import java.util.LinkedList;
import java.util.List;

public class ColumnKeyGenerator {
    private List<Integer> keyList;
    private StringBuilder stringKey;
    private static final int ALPHABET_START_ASCII = 65;
    private static final int ALPHABET_SIZE = 26;


    public String convertKeyToString(int intKey) {
        keyList = new LinkedList<>();
        stringKey = new StringBuilder();
        calculateColumnKey(intKey);
        generateLetterKey();
        return stringKey.toString();

    }

    private void calculateColumnKey(int key) {
        int result;
        result = key % ALPHABET_SIZE;
        keyList.add(0, result);

        if (key > 25) {
            key = key / ALPHABET_SIZE;
            calculateColumnKey(key);

        }

    }

    private void generateLetterKey() {
        int intkey;
        for (Integer i : keyList) {
            intkey = i + ALPHABET_START_ASCII;
            char letter = (char) intkey;
            stringKey.append(letter);

        }

    }

}
