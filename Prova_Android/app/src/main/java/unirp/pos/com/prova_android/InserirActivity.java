package unirp.pos.com.prova_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import unirp.pos.com.model.Contato;

public class InserirActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNome, edtEmail;
    private Button btnSalvar;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtEmail = (EditText)findViewById(R.id.edtEmail);

        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        if(view == btnSalvar){
            Contato contato = new Contato();
            contato.setNome(edtNome.getText().toString());
            contato.setEmail(edtEmail.getText().toString());

            DatabaseReference usersRef = ref.child("contatos");
            String key = usersRef.push().getKey();
            usersRef.child(key).setValue(contato);
            finish();
        }
    }
}
