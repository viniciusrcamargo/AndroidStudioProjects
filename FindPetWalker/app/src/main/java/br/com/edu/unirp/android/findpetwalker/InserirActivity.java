package br.com.edu.unirp.android.findpetwalker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import model.Usuario;

public class InserirActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtNome, edtEndereco, edtCidade, edtTelefone, edtEmail, edtSenha;
    private Button btnSalvar;
    private Usuario usuario;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtEndereco = (EditText)findViewById(R.id.edtEndereco);
        edtCidade = (EditText)findViewById(R.id.edtCidade);
        edtTelefone = (EditText)findViewById(R.id.edtTelefone);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtSenha = (EditText)findViewById(R.id.edtSenha);

        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

        usuario = new Usuario();
    }

    @Override
    public void onClick(View view) {

        if(view == btnSalvar){
            usuario.setNome(edtNome.getText().toString());
            usuario.setEndereco(edtEndereco.getText().toString());
            usuario.setCidade(edtCidade.getText().toString());
            usuario.setTelefone(edtTelefone.getText().toString());
            usuario.setEmail(edtEmail.getText().toString());
            usuario.setSenha(edtSenha.getText().toString());

            final ProgressDialog dialog = ProgressDialog.show(this, "Processando", "Salvando...");

            auth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    dialog.dismiss();
                    if(task.isSuccessful()){
                        FirebaseUser usuarioFirebase = task.getResult().getUser();
                        usuario.setCodigo(usuarioFirebase.getUid());
                        usuario.salvar();
                        //finish();
                        Toast.makeText(InserirActivity.this, "Sucesso ao Salvar o usuário!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getBaseContext(),
                                task.getException().getMessage(), Toast.LENGTH_LONG)
                                .show();
                    }
                }
            }
        );



        }
    }
}
