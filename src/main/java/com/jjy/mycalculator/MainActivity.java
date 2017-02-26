package com.jjy.mycalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.sql.Time;

public class MainActivity extends Activity implements View.OnClickListener {

    TextView tvUp, tvDown;

    //    Input input;

    /**
     * textUp: 上行显示内容
     * textDown: 下行显示内容
     * formula: 算式
     * state: 当前输入状态 输入/结果/开始
     * result: 计算结果
     */
    String textUp = "", textDown = "0";
    final int STATE_INPUT = 1001, STATE_RESULT = 1002, STATE_ERR = 1003;
    int state = STATE_RESULT;
    //    String formula = "";
    //    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    void init() {
        Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bDo, bCl, bCe, bEa, bPl, bMi, bMu, bDe, bPo, bRo;
        tvUp = (TextView) findViewById(R.id.textUp);
        tvDown = (TextView) findViewById(R.id.textDown);

        b0 = (Button) findViewById(R.id.b0);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        bPl = (Button) findViewById(R.id.bPl);
        bMi = (Button) findViewById(R.id.bMi);
        bMu = (Button) findViewById(R.id.bMu);
        bDe = (Button) findViewById(R.id.bDe);
        bPo = (Button) findViewById(R.id.bPo);
        bRo = (Button) findViewById(R.id.bRo);
        bEa = (Button) findViewById(R.id.bEa);
        bCl = (Button) findViewById(R.id.bCl);
        bCe = (Button) findViewById(R.id.bCe);
        bDo = (Button) findViewById(R.id.bDo);

        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        bPl.setOnClickListener(this);
        bMi.setOnClickListener(this);
        bMu.setOnClickListener(this);
        bDe.setOnClickListener(this);
        bEa.setOnClickListener(this);
        bPo.setOnClickListener(this);
        bRo.setOnClickListener(this);
        bDo.setOnClickListener(this);
        bCl.setOnClickListener(this);
        bCe.setOnClickListener(this);

        System.out.println("here1");
        if (getActionBar() != null)
            getActionBar().hide();
//        input = new Input();
    }

    long lastPressTime = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastPressTime < 3000) {
            finish();
        } else {
            lastPressTime = System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b0:
                input('0');
                break;
            case R.id.b1:
                input('1');
                break;
            case R.id.b2:
                input('2');
                break;
            case R.id.b3:
                input('3');
                break;
            case R.id.b4:
                input('4');
                break;
            case R.id.b5:
                input('5');
                break;
            case R.id.b6:
                input('6');
                break;
            case R.id.b7:
                input('7');
                break;
            case R.id.b8:
                input('8');
                break;
            case R.id.b9:
                input('9');
                break;
            case R.id.bPl:
                input('+');
                break;
            case R.id.bMi:
                input('-');
                break;
            case R.id.bMu:
                input('*');
                break;
            case R.id.bDe:
                input('/');
                break;
            case R.id.bEa:
                input('=');
                break;
            case R.id.bCl:
                input('C');
                break;
            case R.id.bCe:
                input('E');
                break;
            case R.id.bPo:
                input('^');
                break;
            case R.id.bRo:
                input('√');
                break;
            case R.id.bDo:
                input('.');
                break;
        }
    }

//    void input(char ch) {
//        if (state == STATE_INPUT) {
//            switch (ch) {
//                case 'C':
//                    clear();
//                    break;
//                case 'E':
//                    formula = formula.substring(0, formula.length() - 1);
//                    break;
//                case 'P':
//                    formula += '^';
//                    break;
//                case 'R':
//                    formula += '√';
//                    break;
//                case '=':
//                    Calculate c = new Calculate(formula);
//                    double d;
//                    try {
//                        d = c.cal();
//                    } catch (Exception e) {
//                        err();
//                        return;
//                    }
//                    if (d != Calculate.ERROR)
//                        result = d;
//                    else {
//                        err();
//                        return;
//                    }
//
//                    textUp = formula + '=';
//                    textDown = result + "";
//                    if (textDown.endsWith(".0"))
//                        textDown = textDown.substring(0, textDown.length() - 2);
//                    formula = "";
//                    state = STATE_RESULT;
//                    break;
//                default:
//                    formula += ch;
//                    textDown = formula;
//                    break;
//            }
//        } else if (state == STATE_RESULT) {
//            switch (ch) {
//                case '=':
//                case 'E':
//                    break;
//                case 'C':
//                    clear();
//                    break;
//                default:
//                    if (textDown.equals("0"))
//                        formula = textDown + ch;
//                    else formula = ch + "";
//                    state = STATE_INPUT;
//                    break;
//            }
//            textDown = formula;
//        } else {
//            err();
//        }
//        System.out.println("result = " + result);
//        tvUp.setText(textUp);
//        tvDown.setText(textDown);
//    }

    int count = 0;
    boolean switchC = true;

    void reg(char ch) {
        if (ch == '+' && switchC) {
            count++;
            switchC = false;
        } else if (ch == '-')
            switchC = true;
        else {
            count = 0;
            switchC = true;
        }
        if (count >= 8) {
            String string = "\n\n    Programmed by JJY.";
            string = getResources().getString(R.string.update) + string;
            new AlertDialog.Builder(this).setTitle("My Calculator").setCancelable(false)
                    .setPositiveButton("确定", null).setMessage(string).show();
            count = 0;
        }
    }

    void input(char ch) {
        reg(ch);
        switch (state) {
            case STATE_INPUT:
                switch (ch) {
                    case 'C':
                        clear();
                        break;
                    case 'E':
                        if (textDown.length() > 1)
                            textDown = textDown.substring(0, textDown.length() - 1);
                        else textDown = "0";
                        break;
                    case '=':
                        Calculate calculate = new Calculate(textDown);
                        double d;
                        try {
                            d = calculate.cal();
                            if (d == Calculate.ERROR) {
                                err();
                            } else {
                                textUp = textDown + '=';
                                textDown = round(d);
                                state = STATE_RESULT;
                            }
                            break;
                        } catch (Exception e) {
                            err();
                            break;
                        }
                    case '+':
                    case '*':
                    case '/':
                    case '^':
                        char c = textDown.charAt(textDown.length() - 1);
                        switch (c) {
                            case '+':
                            case '*':
                            case '/':
                            case '^':
                                textDown = textDown.substring(0, textDown.length() - 1);
                                textDown += ch;
                                break;
                            case '√':
                                if (textDown.charAt(textDown.length() - 2) >= 48 && textDown.charAt(textDown.length() - 2) <= 57) {
                                    textDown = textDown.substring(0, textDown.length() - 1);
                                    textDown += ch;
                                } else {
                                    textDown = textDown.substring(0, textDown.length() - 2);
                                    textDown += ch;
                                }
                                break;
                            case '-':
                                if (textDown.charAt(textDown.length() - 2) >= 48 && textDown.charAt(textDown.length() - 2) <= 57) {
                                    textDown = textDown.substring(0, textDown.length() - 1);
                                    textDown += ch;
                                } else {
                                    textDown = textDown.substring(0, textDown.length() - 2);
                                    textDown += ch;
                                }
                                break;
                            default:
                                textDown += ch;
                                break;
                        }
                        break;
                    case '-':
                        c = textDown.charAt(textDown.length() - 1);
                        switch (c) {
                            case '+':
                            case '-':
                                textDown = textDown.substring(0, textDown.length() - 1);
                                textDown += ch;
                                break;
                            case '√':
                                if (textDown.charAt(textDown.length() - 2) >= 48 && textDown.charAt(textDown.length() - 2) <= 57) {
                                    textDown = textDown.substring(0, textDown.length() - 1);
                                    textDown += ch;
                                } else {
                                    textDown = textDown.substring(0, textDown.length() - 2);
                                    textDown += ch;
                                }
                                break;
                            default:
                                textDown += ch;
                                break;
                        }
                        break;
                    case '√':
                        c = textDown.charAt(textDown.length() - 1);
                        if ((c > 57 || c < 48) && c != '√')
                            textDown += ch;
                        break;
                    default:
                        if (textDown.equals("0") && ch >= 48 && ch <= 57)
                            textDown = ch + "";
                        else textDown += ch;
                        break;
                }
                break;
            case STATE_RESULT:
                switch (ch) {
                    case 'C':
                        clear();
                        break;
                    case '=':
                    case 'E':
                        break;
                    default:
                        textUp = "";
                        state = STATE_INPUT;
                        textDown = (ch >= 48 & ch <= 57 || ch == '√') ? ch + "" : textDown + ch;
                        break;
                }
                break;
            case STATE_ERR:
                if (ch == 'C') clear();
                break;
        }

        tvUp.setText(textUp);
//        tvDown.setText(textDown);
        setText(tvDown, textDown);
    }

    void setText(TextView textView, String s) {
        textView.setText(s);
        if (s.length() > 11) {
            textView.setTextSize(36);
        } else textView.setTextSize(52);
    }

    void clear() {
        textUp = "";
        textDown = "0";
        state = STATE_RESULT;
    }

    void err() {
        textUp = "";
        textDown = "输入有误，请重试";
        state = STATE_ERR;
    }

    String round(double s) {
        BigDecimal b = new BigDecimal(s);
        double doubleValue = b.setScale(13, BigDecimal.ROUND_HALF_UP).doubleValue();
        String str = doubleValue + "";
        if (str.endsWith(".0")) {
            return str.substring(0, str.length() - 2);
        } else return str;
    }
}
