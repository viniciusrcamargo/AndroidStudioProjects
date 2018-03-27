package idadedecachorro.cursoandroid.com.idadedecachorro;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnCalcular;
    private EditText edtIdade;
    private TextView txvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        edtIdade = (EditText) findViewById(R.id.edtIdade);
        txvResultado = (TextView) findViewById(R.id.txvResultado);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idadeString = edtIdade.getText().toString();
                if(idadeString.isEmpty()){
                    txvResultado.setText("Nenhum número digitado");
                    txvResultado.setTextColor(Color.parseColor("#FF0000"));
                }else{
                    int idade;
                    idade = Integer.parseInt(edtIdade.getText().toString());
                    txvResultado.setText("Seu cachorro tem = " + (idade * 7) + " anos");
                    txvResultado.setTextColor(Color.parseColor("#FFFFFF"));
                }

            }
        });
    }
}
