package com.bistriycredit.online.nakartu.creditModule.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CreditCalculator {

    public static String[] createNewSumRange(String[] sumCurrentRange, int sum) {
        ArrayList<String> currentRange = new ArrayList<>(Arrays.asList(sumCurrentRange));
        ArrayList<Integer> newRange = new ArrayList<>();

        for (String s : currentRange) {
            newRange.add(Integer.valueOf(s));
        }

        newRange.add(sum);
        Collections.sort(newRange);
        System.out.println(newRange);
        String s[] = new String[newRange.size()];
        for (int i = 0; i < s.length; i++) {
            s[i] = String.valueOf(newRange.get(i));
        }

        return s;
    }

    public static int getCurrentElementPosition(String[] sumNewRange, int sum) {

        for (int i = 0; i < sumNewRange.length; i++) {
            try {
                if (sum == Integer.valueOf(sumNewRange[i])) return i;
            } catch (NumberFormatException e){}
        }

        return 0;
    }
}
