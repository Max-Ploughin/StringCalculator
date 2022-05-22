package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Calculator {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Double> numericList = new ArrayList<>();
    ArrayList<String> symbols = new ArrayList<>();

    ArrayList<Double> mainResult = new ArrayList<>();

    public String calculation(String string){
        String resultStr = null;



        while (!isNumber(string)){
            String checkParenthese = parenthesesString(string);

            ArrayList<Double> numbers = getListOfNumbers(checkParenthese);
            Double result = figureOut(numbers, checkParenthese);
            string = removeParenthese(string, checkParenthese, Double.toString(result));
            resultStr = string;


        }

        return resultStr;
    }

    private boolean isNumber(String string){
        try {
            double d = Double.parseDouble(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /*
        Метод для получения выражения в скобках
     */
    public String parenthesesString (String string){



        String strParentheses = null;
        int indexOfFirstParentheses = 0;
        int indexOfSecondParentheses = 0;

        for (int i=0; i<string.length(); i++){
            char word = string.charAt(i);
            if (Character.toString(word).equals("(")){
                indexOfFirstParentheses = i;
            }
            if (Character.toString(word).equals(")")){
                indexOfSecondParentheses = i;
                break;
            }
            else
            continue;

        }
        if (indexOfSecondParentheses == 0){
            return string;
        }
        else
            strParentheses = string.substring(indexOfFirstParentheses+1, indexOfSecondParentheses);
            parenthesesString(strParentheses);

        return strParentheses;
    }

    /*
    Метод для нахождения арифметических символов в строке
     */
    public ArrayList<String> arithmeticalOperations(String string) {
        ArrayList<String> operations = new ArrayList<>();
        ArrayList<Double> nums = getListOfNumbers(string);

        for (int i=0; i < nums.size(); i++){
            String num = Double.toString(nums.get(i));
            if (Integer.parseInt(Character.toString(num.charAt(num.length()-1))) == 0){
                num = num.replace(".0", "");
            }
            string = string.replace(num, "");
            string = string.replace(".0", "");
        }

        for (int i = 0; i < string.length(); i++) {

            char word = string.charAt(i);
            operations.add(Character.toString(word));
            }

        return operations;
    }

    /*
        Метод для замены выржения в скобках на результат вычисления
     */
    public String removeParenthese(String originalString, String strForRemove, String newStr){
        int checkParenthese = originalString.indexOf("(");
        if (checkParenthese != -1) {
            strForRemove = "(" + strForRemove + ")";
            String result = originalString.replace(strForRemove, newStr);

            return result;
        }
        else if (newStr != null){
            strForRemove = strForRemove;
            String result = originalString.replace(strForRemove, newStr);

            return result;
        }
        else {
            return originalString;
        }
    }

    private boolean isParenthesesOrDigit(String string, int index) {
        boolean isParenthesesOrDigit = (Character.isDigit(string.charAt(index - 1)) &&
                Character.isDigit(string.charAt(index + 1))) ||
                Character.toString(string.charAt(index + 1)).equals("(") ||
                Character.toString(string.charAt(index - 1)).equals(")");
        return isParenthesesOrDigit;
    }

//    private boolean isMinus (String string, int index){
//        char word = string.charAt(index);
//        boolean isMinus = Character.toString(word).equals("-") && isParenthesesOrDigit(string, index);
//
//        return isMinus;
//    }
//
//    private boolean isPlus (String string, int index){
//        char word = string.charAt(index);
//        boolean isPlus = Character.toString(word).equals("+") && isParenthesesOrDigit(string, index);
//
//        return isPlus;
//    }
//
//    private boolean isMultiply (String string, int index){
//        char word = string.charAt(index);
//        boolean isMultiply = Character.toString(word).equals("*") && isParenthesesOrDigit(string, index);
//
//        return isMultiply;
//    }
//
//    private boolean isDivision (String string, int index){
//        char word = string.charAt(index);
//        boolean isDivision = Character.toString(word).equals("/") && isParenthesesOrDigit(string, index);
//
//        return isDivision;
//    }
//
//    private boolean isArithmeticalOperator(String string, int index) {
//        if (index != string.length() - 1) {
//            return isMinus(string, index) || isPlus(string, index) || isDivision(string, index) || isMultiply(string, index);
//        }
//        return false;
//
//    }


    private boolean isMinus (String string, int index){
        char word = string.charAt(index);
        boolean isMinus = Character.toString(word).equals("-");
        return isMinus;
    }

    private boolean isPlus (String string, int index){
        char word = string.charAt(index);
        boolean isPlus = Character.toString(word).equals("+");
        return isPlus;
    }

    private boolean isMultiply (String string, int index){
        char word = string.charAt(index);
        boolean isMultiply = Character.toString(word).equals("*");
        return isMultiply;
    }

    private boolean isDivision (String string, int index){
        char word = string.charAt(index);
        boolean isDivision = Character.toString(word).equals("/");
        return isDivision;
    }

    private boolean isArithmeticalOperator(String string, int index) {
        if (index != string.length() - 1) {
            return isMinus(string, index) || isPlus(string, index) || isDivision(string, index) || isMultiply(string, index);
        }
        return false;

    }


    /*
        Метод для получения цифр из строки и парсинга в Double
     */
    public ArrayList<Double> getListOfNumbers (String string){

        ArrayList<Double> listOfNumbers = new ArrayList<>();
        String numStr = "";
        for (int i = 0; i<string.length(); i++){
            char word = string.charAt(i);

            if (checkNegative(string, i)){
                numStr += Character.toString(word);
                continue;
            }
            if (Character.isDigit(word) || Character.toString(word).equals(".")){
                numStr += Character.toString(word);
                if (i == string.length()-1){
                    listOfNumbers.add(Double.parseDouble(numStr));
                }
                continue;
            }
            listOfNumbers.add(Double.parseDouble(numStr));
            numStr = "";

        }

        return listOfNumbers;
    }

    /*
        Метод для решения
     */
    public Double figureOut (ArrayList<Double> arrayNums, String string){


        if (arrayNums.size() > 1) {
            ArrayList<String> operands = arithmeticalOperations(string);
            Double result = 0.0;
            for (int i = 0; i < operands.size();) {

                int indexOfMult = operands.indexOf("*");
                if (indexOfMult != -1) {
                    if (indexOfMult == 0 && Character.toString(string.charAt(0)).equals("-")){
                        result = -1 * arrayNums.get(indexOfMult) * arrayNums.get(indexOfMult + 1);
                        arrayNums.set(indexOfMult, result);
                        arrayNums.remove(indexOfMult + 1);
                        operands.remove(indexOfMult);
                        operands.set(indexOfMult-1, "+");
                        continue;
                    }
                    else if (indexOfMult == 0 && !(Character.toString(string.charAt(0)).equals("-"))){
                        result = arrayNums.get(indexOfMult) * arrayNums.get(indexOfMult + 1);
                        arrayNums.set(indexOfMult, result);
                        arrayNums.remove(indexOfMult + 1);
                        operands.remove(indexOfMult);
                        continue;
                    }
                    if (operands.get(indexOfMult - 1).equals("-")){
                        result = -1 * arrayNums.get(indexOfMult) * arrayNums.get(indexOfMult + 1);
                        arrayNums.set(indexOfMult, result);
                        arrayNums.remove(indexOfMult + 1);
                        operands.remove(indexOfMult);
                        operands.set(indexOfMult-1, "+");
                        continue;
                    }
                    else {
                        result = arrayNums.get(indexOfMult) * arrayNums.get(indexOfMult + 1);
                        arrayNums.set(indexOfMult, result);
                        arrayNums.remove(indexOfMult + 1);
                        operands.remove(indexOfMult);
                        continue;
                    }
                }

                int indexOfDevide = operands.indexOf("/");
                if (indexOfDevide != -1){
                    if (indexOfDevide == 0 && Character.toString(string.charAt(0)).equals("-")){
                        result = -1 * arrayNums.get(indexOfDevide) / arrayNums.get(indexOfDevide + 1);
                        arrayNums.set(indexOfDevide, result);
                        arrayNums.remove(indexOfDevide + 1);
                        operands.remove(indexOfDevide);
                        operands.set(indexOfDevide-1, "+");
                        continue;
                    }
                    else if (indexOfDevide == 0 && !(Character.toString(string.charAt(0)).equals("-"))){
                        result = arrayNums.get(indexOfDevide) / arrayNums.get(indexOfDevide + 1);
                        arrayNums.set(indexOfDevide, result);
                        arrayNums.remove(indexOfDevide + 1);
                        operands.remove(indexOfDevide);
                        continue;
                    }
                    if (operands.get(indexOfDevide - 1).equals("-")){
                        result = -1 * arrayNums.get(indexOfDevide) / arrayNums.get(indexOfDevide + 1);
                        arrayNums.set(indexOfDevide, result);
                        arrayNums.remove(indexOfDevide + 1);
                        operands.remove(indexOfDevide);
                        operands.set(indexOfDevide-1, "+");
                        continue;
                    }
                    else {
                        result = arrayNums.get(indexOfDevide) / arrayNums.get(indexOfDevide + 1);
                        arrayNums.set(indexOfDevide, result);
                        arrayNums.remove(indexOfDevide + 1);
                        operands.remove(indexOfDevide);
                        continue;
                    }


                }
//                if (Character.toString(operand).equals("/")) {
//                    result = arrayNums.get(i) / arrayNums.get(i + 1);
//                }

                char operand = operands.get(i).charAt(0);
                if (Character.toString(operand).equals("+")) {
                    result = arrayNums.get(i) + arrayNums.get(i + 1);
                    arrayNums.set(i, result);
                    arrayNums.remove(i + 1);
                    operands.remove(i);
                    continue;
                }
                if (Character.toString(operand).equals("-")) {
                    result = arrayNums.get(i) - arrayNums.get(i + 1);
                    arrayNums.set(i, result);
                    arrayNums.remove(i + 1);
                    operands.remove(i);
                    continue;
                }



            }
            return result;
        }
        else{
            Double result2 = arrayNums.get(0);
            return result2;
        }
    }

    /*
        Метод для проверки отрицательное ли первое число в строке
     */
//    private boolean checkNegative (String string, int index){
//        if (index == 0){
//            boolean checkNegative = Character.toString(string.charAt(index)).equals("-") && Character.isDigit(string.charAt(index+1));
//            return checkNegative;
//        }
//        return false;
//    }
    private boolean checkNegative (String string, int index){
        if (index == 0){
            boolean checkNegative = Character.toString(string.charAt(index)).equals("-") && Character.isDigit(string.charAt(index+1));
            return checkNegative;
        }
        else if(!Character.isDigit((string.charAt(index-1)))) {
            boolean checkNegative = Character.toString(string.charAt(index)).equals("-") && Character.isDigit(string.charAt(index+1));

            return checkNegative;
        }
        return false;
    }


}
