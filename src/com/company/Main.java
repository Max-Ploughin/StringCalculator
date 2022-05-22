package com.company;

/*
Write a calculator for evaluating arithmetic expressions.

An expression can consist of:

Digits (0-9)
Dots as decimal marks (valid example: 100.02, not valid example : 100..02)
Parentheses
Mathematical symbols (allowed are : "+", "-", "*", "/")
Rounding is to be performed to 4 significant digits, only the final result is to be rounded. Example: 102.12356 -> 102.1236

Input and expected output
Input : String containing arithmetic expression
Output : evaluation result or null if the expression cannot be evaluated
 */

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Calculator check = new Calculator();
        String str = "(1+38)*4-5";
        System.out.println(str);
        System.out.println(check.calculation(str));

    }
}
