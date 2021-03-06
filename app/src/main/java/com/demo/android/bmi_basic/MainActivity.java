package com.demo.android.bmi_basic;


import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //Define parameters
    private Button button;
    private EditText fieldheight;
    private EditText fieldweight;
    private TextView result;
    private TextView fieldsuggest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button button = findViewById(R.id.submit);
        findViewSetting();
        setListeners();
//        button.setOnClickListener(listener);
        openOptionDialog();
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            System.out.println("使用匿名的Listener");

            //Using try&catch for app exception!!!!!
            double BMI = 0;//Call BMI calculate function
            try {
                BMI = calBMI();
                Toast.makeText(MainActivity.this,"Toast Calculate",Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            showResult(BMI);//Show BMI final results
        }
    };

    DialogInterface.OnClickListener ConfirmListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
//            System.out.print("Show Message");
        }
    };

    //Add Dialog
    private void openOptionDialog() {
        AlertDialog.Builder DialogBuiler = new AlertDialog.Builder(MainActivity.this);
        DialogBuiler.setTitle(R.string.dialog_title);//Set Dialog Title
        DialogBuiler.setMessage(R.string.dialog_message);//Set Dialog Message
        DialogBuiler.setPositiveButton(R.string.dialog_button_confirm, ConfirmListener);//Set Dialog Confirm Button
        DialogBuiler.setNegativeButton(R.string.dialog_button_cancel, null);//Set Dialog Cancel Button
    }


    //Setting findView
    private void findViewSetting() {
        button = (Button) findViewById(R.id.submit);
        fieldheight = (EditText) findViewById(R.id.height);
        fieldweight = (EditText) findViewById(R.id.weight);
        result = (TextView) findViewById(R.id.result);
        fieldsuggest = (TextView) findViewById(R.id.suggest);
    }

    //Setting Listeners
    private void setListeners() {
        button.setOnClickListener(listener);
    }

    private double calBMI() {
//        EditText fieldheight = (EditText) findViewById(R.id.height);//Find EditText to find height text
//        EditText fieldweight = (EditText) findViewById(R.id.weight);//Find EditText to find weight text
        double height = Double.parseDouble(fieldheight.getText() + "") / 100;//Change text height type to double
        double weight = Double.parseDouble(fieldweight.getText() + "");//Change text weight type to double
        double BMI = weight / (height * height);
        return BMI;
    }

    private void showResult(double BMI) {
        DecimalFormat nf = new DecimalFormat("0.00");//Define nf after point 2 (xxx.oo)
//        TextView result = (TextView)findViewById(R.id.result);//Find TextView to find result
        result.setText("Your BMI is : " + nf.format(BMI));
//        TextView fieldsuggest = (TextView)findViewById(R.id.suggest);

        if (BMI > 25) {
            fieldsuggest.setText(R.string.advice_heavy);
        } else if (BMI < 20) {
            fieldsuggest.setText(R.string.advice_light);
        } else {
            fieldsuggest.setText(R.string.advice_average);
        }
    }

}
