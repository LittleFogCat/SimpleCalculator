package com.jjy.mycalculator;

import java.util.ArrayList;

/**
 * Created by jjy on 2016/11/25.
 */

public class Calculate {
    private String s;
    public static final double ERROR = 1.491859;

    private ArrayList<Double> numbers = new ArrayList<>();
    private ArrayList<Character> operators = new ArrayList<>();

    Calculate(String s) {
        this.s = s;
    }

    private static String format(String s) {
        if (s.startsWith("-")) {
            s = "0" + s;
        }
        s = s.replace("*-", "m");
        s = s.replace("/-", "d");
        s = s.replace("^-", "p");
        s = s.replace("√", "1√");
        return s;
    }

    double cal() {
        s = format(s);
        String tempNum = "";

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // 判断是否是最后一位，如果是的话：
            if (i == s.length() - 1) {
                // 判断前一位是否为符号
                // 如果是则将该数字添加到numbers数组中
                // 否则则作为前一个数字的最后一位保存
                if (tempNum.equals("")) {
                    numbers.add(ch - 48.0);
                } else numbers.add(Double.parseDouble(tempNum + ch));
            }

            // 判断输入是否为数字
            // 如果是数字，则作为tempNum寄存器最后一位保存
            // 如果是符号，将其放入operators数组，并取出寄存器中的数字，放入到numbers数组，并清空tempNum
            if (isNumber(ch))
                tempNum += ch;
            else {
                numbers.add(Double.parseDouble(tempNum));
                operators.add(ch);
                tempNum = "";
            }
        }

        for (int i = operators.size() - 1; i >= 0; i--) {
            char o = operators.get(i);
            if (o == 'E') {
                merge(i, o);
            }
        }

        //  p = "^-"
        // 将算式从右到左遍历，先计算乘方和开方
        for (int i = operators.size() - 1; i >= 0; i--) {
            char o = operators.get(i);
            if (o == '^' || o == '√' || o == 'p') {
                merge(i, o);
            }
        }

        // m = "*-", d = "/-"
        // 然后计算乘法和除法
        for (int i = 0; i < operators.size(); i++) {
            char o = operators.get(i);
            if (o == '*' || o == '/' || o == 'm' || o == 'd') {
                if (merge(i, o))
                    i--;
                else return ERROR;
            }
        }

        // 剩下加法和减法进行计算
        while (!operators.isEmpty()) {
            merge(0, operators.get(0));
        }

        // 返回计算结果
        return numbers.get(0);
    }

    private boolean merge(int i, char o) {
        double d1 = numbers.get(i), d2 = numbers.get(i + 1);
        double result = 0;

        switch (o) {
            case '+':
                result = d1 + d2;
                break;
            case '-':
                result = d1 - d2;
                break;
            case '*':
                result = d1 * d2;
                break;
            case '/':
                if (d2 != 0)
                    result = d1 / d2;
                else
                    return false;
                break;
            case '^':
                result = Math.pow(d1, d2);
                break;
            case 'm':
                result = d1 * (0 - d2);
                break;
            case 'd':
                if (d2 != 0)
                    result = d1 / (0 - d2);
                else
                    return false;
                break;
            case 'p':
                result = Math.pow(d1, 0 - d2);
                break;
            case '√':
                result = Math.sqrt(d2);
                break;
            case 'E':
                result = d1 * Math.pow(10, d2);
                break;
            default:
                break;
        }
        numbers.remove(i + 1);
        numbers.remove(i);
        numbers.add(i, result);
        operators.remove(i);
        return true;
    }

    private boolean isNumber(char c) {
        return '0' <= c && c <= '9' || c == '.';
    }
}
