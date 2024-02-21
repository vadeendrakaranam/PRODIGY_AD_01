package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private String currentInput = "";
    private double result = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Digits 0-9 and decimal point
        setButtonOnClickListener(R.id.digit1, "1");
        setButtonOnClickListener(R.id.digit2, "2");
        setButtonOnClickListener(R.id.digit3, "3");
        setButtonOnClickListener(R.id.digit4, "4");
        setButtonOnClickListener(R.id.digit5, "5");
        setButtonOnClickListener(R.id.digit6, "6");
        setButtonOnClickListener(R.id.digit7, "7");
        setButtonOnClickListener(R.id.digit8, "8");
        setButtonOnClickListener(R.id.digit9, "9");
        setButtonOnClickListener(R.id.digit0, "0");
        setButtonOnClickListener(R.id.decimalPoint, ".");

        // Operators
        setButtonOnClickListener(R.id.operatorPlus, "+");
        setButtonOnClickListener(R.id.operatorMinus, "-");
        setButtonOnClickListener(R.id.operatorMultiply, "*");
        setButtonOnClickListener(R.id.operatorDivide, "/");

        // Equals and Clear buttons
        setButtonOnClickListener(R.id.equalsButton, "=");
        setButtonOnClickListener(R.id.clearButton, "C");
    }

    private void setButtonOnClickListener(int buttonId, final String buttonText) {
        findViewById(buttonId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick(buttonText);
            }
        });
    }

    private void handleButtonClick(String buttonText) {
        if (Character.isDigit(buttonText.charAt(0)) || buttonText.equals(".")) {
            currentInput += buttonText;
            display.setText(currentInput);
        } else if (buttonText.equals("=")) {
            calculateResult();
        } else if (buttonText.equals("C")) {
            clear();
        } else {
            if (!currentInput.isEmpty()) {
                calculateResult();
            }
            operator = buttonText;
            currentInput = "";
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty()) {
            double inputValue = Double.parseDouble(currentInput);

            switch (operator) {
                case "+":
                    result += inputValue;
                    break;
                case "-":
                    result -= inputValue;
                    break;
                case "*":
                    result *= inputValue;
                    break;
                case "/":
                    if (inputValue != 0) {
                        result /= inputValue;
                    } else {
                        // Handle division by zero error
                        result = 0;
                        display.setText("Error");
                        return;
                    }
                    break;
                default:
                    result = inputValue;
                    break;
            }

            display.setText(String.valueOf(result));
            currentInput = "";
        }
    }

    private void clear() {
        currentInput = "";
        result = 0;
        operator = "";
        display.setText("");
    }
}
