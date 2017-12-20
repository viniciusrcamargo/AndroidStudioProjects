package br.com.edu.unirp.android.whatsappclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtEmail, txtSenha;
    private Button btnEntrar, btnRegistrar;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtSenha = (EditText)findViewById(R.id.txtSenha);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(this);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(this);
    }

    protected void onStart(){
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if(user != null){
            Intent it = new Intent(this, ChatActivity.class);
            startActivity(it);
        }
    }

    @Override
    public void onClick(View view) {
        if(view == btnEntrar){
            String email = txtEmail.getText().toString();
            String senha = txtSenha.getText().toString();

            final ProgressDialog dialog = ProgressDialog.show(this, "Autenticação", "Conectando");
            auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    dialog.dismiss();
                    if(task.isSuccessful()){
                        Intent it = new Intent(getBaseContext(), ChatActivity.class);
                        startActivity(it);
                    }else{
                        Toast.makeText(getBaseContext(),"falha", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }else if(view == btnRegistrar){
            Intent it = new Intent(this, RegistrarActivity.class);
            startActivity(it);
        }
    }
}
