package gasolinaoualcool.cursoandroid.com.gasolinaoualcool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edtPrecoAlcool;
    private EditText edtPrecoGasolina;
    private Button btnVerificar;
    private TextView txvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPrecoAlcool = findViewById(R.id.edtPrecoAlcool);
        edtPrecoGasolina = findViewById(R.id.edtPrecoGasolina);
        btnVerificar = findViewById(R.id.btnVerificar);
        txvResultado = findViewById(R.id.txvResultado);

        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double precoAlcool =   Double.parseDouble(String.valueOf(edtPrecoAlcool.getText()));
                double precoGasolina = Double.parseDouble(String.valueOf(edtPrecoGasolina.getText()));
                double calculo = precoAlcool / precoGasolina;

                if(calculo < 0.7){
                    txvResultado.setText("É melhor abastecer com álcool");
                }else{
                    txvResultado.setText("É melhor abastecer com gasolina");
                }
            }
        });
    }
}
