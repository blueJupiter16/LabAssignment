/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question1;

import java.util.Scanner;

/**
 *
 * @author Samarjoy Pandit
 */
public class StringMatch {

    static char[] pattern;
    static int patlen;
    static int strlen;
    static char[] mainstr;
    static int[][] TransTable;
    public static int NO_OF_ALPHABETS = 256;

    //  Driver program to test if a pattern exists in a string
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Pattern: ");
        String pat = sc.next();
        System.out.println();
        System.out.println("#Enter 'exit' to exit the program.");

        String str;
        while (true) {
            System.out.print(" Enter String: ");
            str = sc.next();
            if(str.equalsIgnoreCase("exit"))
                System.exit(0);
            
            StringMatch strm = new StringMatch(pat, str);
            search(pattern, mainstr);
        }
    }

    StringMatch(String pat, String str) {

        pattern = pat.toCharArray();
        mainstr = str.toCharArray();
        patlen = pattern.length;
        strlen = mainstr.length;
        TransTable = new int[patlen + 1][NO_OF_ALPHABETS];
    }

    public static int search(char[] pat, char[] str) {

        int state = 0, flag = 0;
        computeTransFunc(pat);

        for (int i = 0; i < strlen; i++) {
            state = TransTable[state][str[i]];

            if (state == patlen) {
                flag = 1;
                System.out.println("Pattern found at index: " + (i - patlen + 1));
            }
        }

        if (flag == 0) {
            System.out.println("Pattern NOT found.");
        }

        return flag;
    }

    public static void computeTransFunc(char[] pat) {

        int state, x;
        for (state = 0; state <= patlen; ++state) {
            for (x = 0; x < NO_OF_ALPHABETS; ++x) {
                TransTable[state][x] = getNextState(pat, state, x);
            }
        }
    }

    public static int getNextState(char[] pat, int state, int x) {

        if (state < patlen && x == pat[state]) {
            return (state + 1);
        }

        int nextstate, i;
        for (nextstate = state; nextstate > 0; nextstate--) {
            if (pat[nextstate - 1] == x) {
                for (i = 0; i < nextstate - 1; i++) {
                    if (pat[i] != pat[state - nextstate + 1 + i]) {
                        break;
                    }
                }
                if (i == nextstate - 1) {
                    return nextstate;
                }
            }
        }
        return 0;
    }
}
