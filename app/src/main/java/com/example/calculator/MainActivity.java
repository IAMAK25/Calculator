package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result,solution;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.app));
       solution = findViewById(R.id.solution);
       result = findViewById(R.id.result);

       Id(buttonC,R.id.button_c);
       Id(buttonBrackOpen,R.id.button_open_bracket);
       Id(buttonBrackClose,R.id.button_close_bracket);
       Id(buttonDivide,R.id.button_divide);
       Id(buttonMultiply,R.id.button_multiply);
       Id(buttonPlus,R.id.button_plus);
       Id(buttonMinus,R.id.button_minus);
       Id(buttonEquals,R.id.button_equals);
       Id(button0,R.id.button_0);
       Id(button1,R.id.button_1);
       Id(button2,R.id.button_2);
       Id(button3,R.id.button_3);
       Id(button4,R.id.button_4);
       Id(button5,R.id.button_5);
       Id(button6,R.id.button_6);
       Id(button7,R.id.button_7);
       Id(button8,R.id.button_8);
       Id(button9,R.id.button_9);
       Id(buttonAC,R.id.button_ac);
       Id(buttonDot,R.id.button_dot);
    }
    void Id(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = result.getText().toString();

        if(buttonText.equals("AC")){
            result.setText("");
            solution.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            result.setText(solution.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        result.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Error")){
            solution.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }

}