package br.com.edu.unirp.android.whatsappclone;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private ListView mensagens;
    private EditText texto;
    private ImageButton enviar;
    private ImageButton camera;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("mensagens");//referenciando tabela mensagens no firebase

    private List<Mensagem> lista = new ArrayList<>();

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = auth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mensagens = (ListView) findViewById(R.id.messagens);
        texto = (EditText) findViewById(R.id.edtMessagens);
        enviar = (ImageButton) findViewById(R.id.btnEnviar);
        camera = (ImageButton) findViewById(R.id.camera);


        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mensagem mensagem = new Mensagem();
                mensagem.setTexto(texto.getText().toString());
                mensagem.setData(new Date().getTime());
                mensagem.setId(user.getUid());
                mensagem.setDisplayName(user.getDisplayName());

                String key = ref.push().getKey();
                ref.child(key).setValue(mensagem);
                texto.setText("");
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it, 0);
            }
        });

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Mensagem mensagem = dataSnapshot.getValue(Mensagem.class);
                lista.add(mensagem);
                atualiza();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void atualiza(){
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, lista);
//        mensagens.setAdapter(adapter);
        ListaChatAdapter adapter = new ListaChatAdapter(lista, this);
        mensagens.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_logout){
            //Todo: logout
            auth.signOut();
            finish();
        }else if(id == R.id.action_atualiza){
            Intent it = new Intent(this, AtualizaActivity.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(data != null){
                Bundle bundle = data.getExtras();
                if(bundle != null){
                    Bitmap bitmap = (Bitmap)bundle.get("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    upload(byteArray);
                }
            }
        }
    }

    private void upload(byte[] byteArray){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference _ref = storage.getReference().child("imagens").child(new Date().getTime() + ".png");

        UploadTask uploadTask = _ref.putBytes(byteArray);
        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    @SuppressWarnings("VisibleForTests")
                    Uri uri = task.getResult().getDownloadUrl();

                    Mensagem mensagem = new Mensagem();
                    mensagem.setTexto(texto.getText().toString());
                    mensagem.setData(new Date().getTime());
                    mensagem.setId(user.getUid());
                    mensagem.setImagem(uri.toString());

                    String key = ref.push().getKey();
                    ref.child(key).setValue(mensagem);
                }
            }
        });
    }
}
