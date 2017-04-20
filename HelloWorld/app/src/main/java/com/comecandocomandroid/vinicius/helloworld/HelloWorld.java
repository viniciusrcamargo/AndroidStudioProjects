package com.comecandocomandroid.vinicius.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HelloWorld extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        TextView view = new TextView(this);
        view.setText(&quot;Hello, Android&quot;);
        setContentView(view);
    }
}
