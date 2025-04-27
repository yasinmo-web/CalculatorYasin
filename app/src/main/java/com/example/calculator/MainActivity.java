package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvResult;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button btnPlus, btnMinus, btnMultiply, btnDivide, btnEquals, btnClear;
    
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String currentOperation = "";
    private boolean isOperationClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // مقداردهی اولیه ویجت‌ها
        initializeViews();
        
        // اضافه کردن رویداد کلیک برای دکمه‌ها
        setClickListeners();
    }
    
    private void initializeViews() {
        tvResult = findViewById(R.id.tvResult);
        
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnEquals = findViewById(R.id.btnEquals);
        btnClear = findViewById(R.id.btnClear);
    }
    
    private void setClickListeners() {
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnEquals.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        
        // اگر دکمه‌های شماره فشرده شده‌اند
        if (viewId == R.id.btn0 || viewId == R.id.btn1 || viewId == R.id.btn2 ||
            viewId == R.id.btn3 || viewId == R.id.btn4 || viewId == R.id.btn5 ||
            viewId == R.id.btn6 || viewId == R.id.btn7 || viewId == R.id.btn8 ||
            viewId == R.id.btn9) {
            
            String digit = ((Button) view).getText().toString();
            
            if (tvResult.getText().toString().equals("0") || isOperationClicked) {
                tvResult.setText(digit);
                isOperationClicked = false;
            } else {
                tvResult.setText(tvResult.getText().toString() + digit);
            }
        }
        // اگر دکمه‌های عملیات فشرده شده‌اند
        else if (viewId == R.id.btnPlus || viewId == R.id.btnMinus ||
                 viewId == R.id.btnMultiply || viewId == R.id.btnDivide) {
            
            firstNumber = Double.parseDouble(tvResult.getText().toString());
            currentOperation = ((Button) view).getText().toString();
            isOperationClicked = true;
        }
        // اگر دکمه مساوی فشرده شده است
        else if (viewId == R.id.btnEquals) {
            secondNumber = Double.parseDouble(tvResult.getText().toString());
            double result = calculateResult();
            tvResult.setText(formatResult(result));
            isOperationClicked = true;
        }
        // اگر دکمه پاک کردن فشرده شده است
        else if (viewId == R.id.btnClear) {
            tvResult.setText("0");
            firstNumber = 0;
            secondNumber = 0;
            currentOperation = "";
            isOperationClicked = false;
        }
    }
    
    private double calculateResult() {
        switch (currentOperation) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "×":
                return firstNumber * secondNumber;
            case "÷":
                if (secondNumber == 0) {
                    return 0; // بهتر است خطای تقسیم بر صفر را نمایش دهیم
                }
                return firstNumber / secondNumber;
            default:
                return 0;
        }
    }
    
    private String formatResult(double result) {
        // اگر عدد صحیح است، بدون اعشار نمایش دهیم
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.valueOf(result);
        }
    }
}