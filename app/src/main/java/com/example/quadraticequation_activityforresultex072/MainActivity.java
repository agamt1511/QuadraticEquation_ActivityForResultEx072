package com.example.quadraticequation_activityforresultex072;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random rnd;
    EditText et1, et2, et3;
    Button btn1, btn2;
    TextView tv2;
    float a, b, c, x1, x2;
    int numOfResults = 0;
    String st;
    Intent si;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        tv2 = (TextView) findViewById(R.id.tv2);

        si = new Intent(this,SecondActivity.class);
        rnd = new Random();

    }

    protected void onActivityResult (int source, int good, @Nullable Intent data_back) {
        if (source == 1) {
            if (good == RESULT_OK) {
                if (data_back != null) {
                    x1 = data_back.getFloatExtra("x1",0);
                    x2 = data_back.getFloatExtra("x2",0);
                    numOfResults = data_back.getIntExtra("numOfResults", 0);
                    switch (numOfResults) {
                        case 0:
                            tv2.setText("No Result");
                            break;
                        case 1:
                            tv2.setText("Answer: " +"x = " + x1);
                            break;
                        case 2:
                            tv2.setText("Answer: " +"x1 = " + x1 + " x2= " + x2);
                            break;
                    }

                }
            }
        } }


    private void goToSecond() {
        si.putExtra("a",a);
        si.putExtra("b",b);
        si.putExtra("c",c);
        startActivityForResult(si,1);
    }

    public void sameNums(View view) {
        if ((TextUtils.isEmpty(et1.getText().toString())) || (TextUtils.isEmpty(et2.getText().toString())) || (TextUtils.isEmpty(et3.getText().toString()))) {
            Toast.makeText(this, "ERROR, You did not enter input", Toast.LENGTH_LONG).show();
        }
        else {
            st = et1.getText().toString();
            a = Float.parseFloat(st);
            st = et2.getText().toString();
            b = Float.parseFloat(st);
            st = et3.getText().toString();
            c = Float.parseFloat(st);
            goToSecond();
        }
    }

    public void randomNums(View view) {
        a = rnd.nextInt(10000)+1;
        b = rnd.nextInt(10000)+1;
        c = rnd.nextInt(10000)+1;

        goToSecond();
    }

}