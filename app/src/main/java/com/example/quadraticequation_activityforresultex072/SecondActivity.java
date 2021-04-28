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
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn = (Button) findViewById(R.id.btn);
        wv = (WebView) findViewById(R.id.wv);
        
        start();
        onActivityResult(2, RESULT_OK, );
    }

    protected void onActivityResult (int source, int good, @Nullable Intent data_back) {
        if (data_back != null) {
            x1 = data_back.getIntExtra("x1",0);
            x2 = data_back.getIntExtra("x2",0);}}

    private void start() {
        Intent ri = getIntent();
        a = ri.getFloatExtra("a",0);
        b = ri.getFloatExtra("b",0);
        c = ri.getFloatExtra("c",0);

        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new MyWebViewClient());
        url = "https://www.google.com/search?q=x%5E"+ a + "%2B" + b +"x%2B" + c + "&biw=1280&bih=578&sxsrf=ALeKk02aoH5zVZ14CNvxGZjKfZVn_inESA%3A1619628257815&ei=4ZCJYP37MJD-7_UP9-e30A8&oq=x%5E2-4x&gs_lcp=Cgdnd3Mtd2l6EAMYATIFCAAQywEyBQgAEMsBMgUIABDLATIFCAAQywEyBQgAEMsBMgUIABDLATIFCAAQywEyBQgAEMsBMgUIABDLATIFCAAQywE6BAgjECc6BQgAELEDOgsIABCxAxDHARCjAjoICAAQsQMQgwE6AggAOgQIABADOgcIIxDqAhAnUMKpAViEygNg_-sDaARwAXgAgAGnAYgB4geSAQMwLjiYAQCgAQGqAQdnd3Mtd2l6sAEKwAEB&sclient=gws-wiz";
        showAnswer();
    }

    private void showAnswer() {
        double discriminant = (b * b) - (4 * a * c);
        if (discriminant < 0) {
            Toast.makeText(this, "Sorry, both roots are complex numbers. I can not solve that quadratic equation.", Toast.LENGTH_LONG).show();
            return;
        }

        float rootDiscriminant = (float) Math.sqrt(discriminant);
        x1 = ((-b + rootDiscriminant) / (2 * a));
        x2 = ((-b - rootDiscriminant) / (2 * a));
    }

    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void goBack(View view) {
        Intent ri = getIntent();
        x1 = ri.getFloatExtra("x1",0);
        x2 = ri.getFloatExtra("x2",0);
        setResult(RESULT_OK,ri);
        finish();
    }
}