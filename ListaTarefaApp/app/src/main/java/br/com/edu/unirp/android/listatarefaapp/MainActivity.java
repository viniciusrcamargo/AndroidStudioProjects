package br.com.edu.unirp.android.listatarefaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.edu.unirp.android.listatarefaapp.Model.*;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    private ListView lvTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Precisa fazer o casting para dizer ao java qual componente está referenciado
        lvTarefas = (ListView) findViewById(R.id.lvTarefa);
        lvTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(MainActivity.this, UpdateActivity.class);
                Bundle b = new Bundle();
                b.putInt("id", (int)l);
                it.putExtras(b);
                startActivityForResult(it, 1);
            }
        });

        atualizaLista();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    public void atualizaLista(){
        TarefaModel model = new TarefaModel(this);
        List<Tarefa> tarefas = model.Read();

        /*ArrayAdapter<Tarefa> adapter = new ArrayAdapter<Tarefa>(this, android.R.layout.simple_expandable_list_item_1, tarefas);
        lvTarefas.setAdapter(adapter);*/

        ListaAdapter listaAdapter = new ListaAdapter(tarefas, this);
        lvTarefas.setAdapter(listaAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_add){
            Intent it = new Intent(this, CreateActivity.class);
            startActivityForResult(it, 0);

            Toast.makeText(this, "Clicou em Add", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        atualizaLista();
    }
}
