package br.com.edu.unirp.android.whatsappclone;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class AtualizaActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText txtNome, txtEmail;
    private Button btnSalvar;

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualiza);

        txtNome = (EditText)findViewById(R.id.txtNome);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);


        user = auth.getCurrentUser();
        if(user != null){
        txtNome.setText(user.getDisplayName());
        txtEmail.setText(user.getEmail());}
        btnSalvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnSalvar){
           String nome = txtNome.getText().toString();
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setDisplayName(nome).build();
            //setPhotoUri(http://)
            //user.updateEmail("email@email.com")
            //user.updatePassword("nova senha")
            //validar a operação salvar
           user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
               @Override
               public void onComplete(@NonNull Task<Void> task) {
                   auth.signOut();
                   finish();
               }
           });
        }
    }
}
