package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String num1, num2;
    int result;
    int[] numBtnIDs = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9};
    int[] operBtnIDs = {R.id.btn_add, R.id.btn_subtract, R.id.btn_multiply, R.id.btn_divide};
    EditText et_num1, et_num2;
    Button[] btn_num = new Button[10];
    Button[] btn_oper = new Button[4];
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OperationListener operationListener = new OperationListener();
        NumListener numListener = new NumListener();

        et_num1 = (EditText) findViewById(R.id.et_num1);
        et_num2 = (EditText) findViewById(R.id.et_num2);
        tv_result = (TextView) findViewById(R.id.tv_result);
        for(int i = 0; i< btn_num.length; i++){
            btn_num[i] = (Button) findViewById(numBtnIDs[i]);
        }
        for(int i = 0; i< btn_oper.length; i++){
            btn_oper[i] = (Button) findViewById(operBtnIDs[i]);
        }

        for(int i = 0; i< btn_num.length; i++){
            btn_num[i].setOnClickListener(numListener);
        }
        for(int i = 0; i< btn_oper.length; i++){
            btn_oper[i].setOnClickListener(operationListener);
        }
    }

    private int operation(int id){
        this.num1 = et_num1.getText().toString();
        this.num2 = et_num2.getText().toString();
        switch (id){
            case R.id.btn_add:
                return Integer.parseInt(this.num1) + Integer.parseInt(this.num2);
            case R.id.btn_subtract:
                return Integer.parseInt(this.num1) - Integer.parseInt(this.num2);
            case R.id.btn_multiply:
                return Integer.parseInt(this.num1) * Integer.parseInt(this.num2);
            case R.id.btn_divide:
                return Integer.parseInt(this.num1) / Integer.parseInt(this.num2);
            default:
                break;
        }
        return 0;
    }

    private char num(int id){
        switch(id){
            case R.id.btn_0 : return '0';
            case R.id.btn_1 : return '1';
            case R.id.btn_2 : return '2';
            case R.id.btn_3 : return '3';
            case R.id.btn_4 : return '4';
            case R.id.btn_5 : return '5';
            case R.id.btn_6 : return '6';
            case R.id.btn_7 : return '7';
            case R.id.btn_8 : return '8';
            case R.id.btn_9 : return '9';
            default : break;
        }
        return '0';
    }

    private class OperationListener implements View.OnClickListener{
        public void onClick(View v) {
            try {
                result = operation(v.getId());
                tv_result.setText("계산 결과 : " + String.valueOf(result));
            } catch (NumberFormatException e){
                Toast.makeText(getApplicationContext(), "먼저 숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class NumListener implements View.OnClickListener{
        public void onClick(View v) {
            if(et_num1.isFocused()){
                num1 = et_num1.getText().toString() + num(v.getId());
                et_num1.setText(num1);
            } else if(et_num2.isFocused()){
                num2 = et_num2.getText().toString() + num(v.getId());
                et_num2.setText(num2);
            } else{
                Toast.makeText(getApplicationContext(), "먼저 에디트 텍스트를 선택하세요", Toast.LENGTH_SHORT).show();
            }
        }
    }
}