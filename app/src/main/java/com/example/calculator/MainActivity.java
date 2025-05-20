package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvResult;
    private String currentInput = "";
    private double firstNumber = 0;
    private String currentOperation = "";
    private boolean isOperationClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);

        int[] buttonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnPlus, R.id.btnMinus, R.id.btnEquals, R.id.btnClear
        };

        for (int id : buttonIds) {
            Button btn = findViewById(id);
            btn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Button clicked = (Button) view;
        String value = clicked.getText().toString();

        if (id == R.id.btnClear) {
            tvResult.setText("0");
            currentInput = "";
            firstNumber = 0;
            currentOperation = "";
            isOperationClicked = false;
        } else if (id == R.id.btnEquals) {
            if (!currentOperation.isEmpty() && !currentInput.isEmpty()) {
                double secondNumber = Double.parseDouble(currentInput);
                double result = 0;

                switch (currentOperation) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "−":
                        result = firstNumber - secondNumber;
                        break;
                }

                tvResult.setText(String.valueOf((result == (int) result) ? (int) result : result));
                currentInput = String.valueOf(result);
                currentOperation = "";
            }
        } else if (value.equals("+") || value.equals("−")) {
            if (!currentInput.isEmpty()) {
                firstNumber = Double.parseDouble(currentInput);
                currentInput = "";
                currentOperation = value;
                isOperationClicked = true;
            }
        } else {
            if (isOperationClicked) {
                currentInput = "";
                isOperationClicked = false;
            }
            currentInput += value;
            tvResult.setText(currentInput);
        }
    }
}
