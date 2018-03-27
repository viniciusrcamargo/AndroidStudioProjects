package frasedodia.cursoandroid.com.frasedodia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnNovaFrase;
    private TextView txtNovaFrase;
    private String[] frases = {"As pessoas costumam dizer que a motivação não dura sempre. Bem, nem o efeito do banho, por isso recomenda-se diariamente. by Zig Ziglar",
                               "Motivação é a arte de fazer as pessoas fazerem o que você quer que elas façam porque elas o querem fazer. by Dwight Eisenhower",
                                "Toda ação humana, quer se torne positiva ou negativa, precisa depender de motivação. by Dalai Lama",
                                "A verdadeira motivação vem de realização, desenvolvimento pessoal, satisfação no trabalho e reconhecimento.",
                                "Minha energia é o desafio minha motivação é o impossível e é por isso que eu preciso ser, à força e a esmo, inabalável."};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNovaFrase = (Button) findViewById(R.id.btnNovaFrase);
        txtNovaFrase = (TextView) findViewById(R.id.txtNovaFrase);

        //txtNovaFrase.setText(frases[0]);

        btnNovaFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random randomico = new Random();
                int numero = randomico.nextInt(frases.length);
                txtNovaFrase.setText(frases[numero]);
            }
        });
    }
}
