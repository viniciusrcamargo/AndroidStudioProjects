package br.com.edu.unirp.android.somadoisnumeros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText EdtNum1 = (EditText) findViewById(R.id.EdtNum1);
    EditText EdtNum2 = (EditText) findViewById(R.id.EdtNum2);
    EditText EdtResultado = (EditText) findViewById(R.id.EdtResultado);
    Button BtnSomar = (Button) findViewById(R.id.BtnSomar);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



}
