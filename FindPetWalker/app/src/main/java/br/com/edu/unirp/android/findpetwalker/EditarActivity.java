package br.com.edu.unirp.android.findpetwalker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import br.com.edu.unirp.android.findpetwalker.config.ConfiguracaoFirebase;

import model.Usuario;

public class EditarActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNome, edtEndereco, edtCidade, edtTelefone, edtEmail, edtSenha;
    private Button btnEditar;
    private Usuario usuario;
    private String UID;

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = null;

//    private DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebase();
    private DatabaseReference usuarioRef;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtCidade = (EditText) findViewById(R.id.edtCidade);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener((View.OnClickListener) this);

        user = auth.getCurrentUser();
//
//        edtNome.setText(user.getDisplayName());
//        edtEmail.setText(user.getEmail());

        usuarioRef = FirebaseDatabase.getInstance().getReference()
                .child("usuarios");

//        usuarioRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.i("FIREBASE", dataSnapshot.getValue().toString());
//                //código para listar nos edittext não funciona
////                usuario.setNome(edtNome.getText().toString());
////                usuario.setEndereco(edtEndereco.getText().toString());
////                usuario.setCidade(edtCidade.getText().toString());
////                usuario.setTelefone(edtTelefone.getText().toString());
////                usuario.setNome(dataSnapshot.child("nome").getValue().toString());
////                edtNome.setText(usuario.getNome());
//                String nome = dataSnapshot.getValue(String.class);
//                edtNome.setText(nome);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nome = dataSnapshot.getValue(String.class);
                edtNome.setText(nome);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btnEditar) {
            usuario.setNome(edtNome.getText().toString());
            usuario.setEndereco(edtEndereco.getText().toString());
            usuario.setCidade(edtCidade.getText().toString());
            usuario.setTelefone(edtTelefone.getText().toString());
            usuario.setEmail(edtEmail.getText().toString());

            UserProfileChangeRequest profile = new UserProfileChangeRequest
                    .Builder()
                    .setDisplayName(usuario.getNome())
                    .build();

            user.updateProfile(profile)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {

                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            auth.signOut();
                            finish();
                        }
                    });

            Toast.makeText(EditarActivity.this, "Sucesso ao editar o usuário!", Toast.LENGTH_LONG).show();
            Intent it = new Intent(this, PrincipalActivity.class);
            startActivity(it);

        }
    }
}