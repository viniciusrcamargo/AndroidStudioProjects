package br.com.edu.unirp.android.findpetwalker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.edu.unirp.android.findpetwalker.config.ConfiguracaoFirebase;
import model.Usuario;

public class ListarUsuarios extends AppCompatActivity {
    private DatabaseReference dbRef;

    private ListView listarUsuarios;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);

        listarUsuarios = (ListView)findViewById(R.id.lvListarUsuarios);
//        adapter = new ArrayAdapter<String>(this,android.R.layout.activity_list_item,arrayList);
        listarUsuarios.setAdapter(adapter);

        dbRef = ConfiguracaoFirebase.getFirebase().child("usuarios");
        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        arrayList.clear();
        for(DataSnapshot dbU : dataSnapshot.getChildren()){
            Usuario usuario = dbU.getValue(Usuario.class);
            /*usuario.setCidade(dbU.getValue(Usuario.class).getCidade());
            usuario.setEmail(dbU.getValue(Usuario.class).getEmail());
            usuario.setEndereco(dbU.getValue(Usuario.class).getEndereco());
            usuario.setNome(dbU.getValue(Usuario.class).getNome());
            usuario.setTelefone(dbU.getValue(Usuario.class).getTelefone());
*/
            arrayList.add(usuario.getNome());


        }
        adapter = new ArrayAdapter<String>(ListarUsuarios.this,android.R.layout.simple_list_item_1,arrayList);
        listarUsuarios.setAdapter(adapter);
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
}
