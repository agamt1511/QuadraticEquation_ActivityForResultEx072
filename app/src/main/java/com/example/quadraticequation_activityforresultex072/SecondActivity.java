package com.example.quadraticequation_activityforresultex072;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    Button btn;
    WebView wv;
    float a, b, c, x1, x2;
    int numOfResults = 0;
    String url;
    Intent ri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn = (Button) findViewById(R.id.btn);
        wv = (WebView) findViewById(R.id.wv);

        ri = new Intent(this,MainActivity.class);

        start();
    }

    private void start() {
        ri = getIntent();
        x1 = ri.getIntExtra("x1", 0);
        x2 = ri.getIntExtra("x2", 0);
        a = ri.getFloatExtra("a",0);
        b = ri.getFloatExtra("b",0);
        c = ri.getFloatExtra("c",0);

        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new MyWebViewClient());
        url = "https://www.google.com/search?q=x%5E"+ a + "%2B" + b +"x%2B" + c + "&biw=1280&bih=578&sxsrf=ALeKk02aoH5zVZ14CNvxGZjKfZVn_inESA%3A1619628257815&ei=4ZCJYP37MJD-7_UP9-e30A8&oq=x%5E2-4x&gs_lcp=Cgdnd3Mtd2l6EAMYATIFCAAQywEyBQgAEMsBMgUIABDLATIFCAAQywEyBQgAEMsBMgUIABDLATIFCAAQywEyBQgAEMsBMgUIABDLATIFCAAQywE6BAgjECc6BQgAELEDOgsIABCxAxDHARCjAjoICAAQsQMQgwE6AggAOgQIABADOgcIIxDqAhAnUMKpAViEygNg_-sDaARwAXgAgAGnAYgB4geSAQMwLjiYAQCgAQGqAQdnd3Mtd2l6sAEKwAEB&sclient=gws-wiz";
        wv.loadUrl(url);
        showAnswer();
    }

    private void showAnswer() {
        double discriminant = (b * b) - (4 * a * c);
        if (discriminant < 0) {
            Toast.makeText(this, "Sorry, both roots are complex numbers. I can not solve that quadratic equation.", Toast.LENGTH_LONG).show();
            numOfResults = 0;
            return;
        }

        float rootDiscriminant = (float) Math.sqrt(discriminant);
        x1 = ((-b + rootDiscriminant) / (2 * a));
        x2 = ((-b - rootDiscriminant) / (2 * a));
        if (x1 == x2) {
            numOfResults = 1;
        }
        else {
            numOfResults = 2;
        }
    }

    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void goBack(View view) {
        ri.putExtra("x1", x1);
        ri.putExtra("x2",x2);
        ri.putExtra("numOfResults", numOfResults);
        setResult(RESULT_OK, ri);
        finish();
    }
}