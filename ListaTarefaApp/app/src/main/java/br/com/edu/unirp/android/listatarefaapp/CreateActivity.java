package br.com.edu.unirp.android.listatarefaapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.edu.unirp.android.listatarefaapp.Model.Tarefa;
import br.com.edu.unirp.android.listatarefaapp.Model.TarefaModel;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNome;
    private EditText txtDescricao;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Nova Tarefa");

        txtNome = (EditText) findViewById(R.id.txtNome);
        txtDescricao = (EditText) findViewById(R.id.txtDescricao);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnSalvar){//tem que informar qual componente clicou
            Tarefa tarefa = new Tarefa();
            tarefa.setNome(txtNome.getText().toString());
            tarefa.setDescricao(txtDescricao.getText().toString());
            tarefa.setConcluido(false);

            //TODO: Invocar o método para inserir ao banco de dados
            TarefaModel model = new TarefaModel(this);
            model.create(tarefa);
            finish();
        }
    }
}
