package br.com.edu.unirp.android.listatarefaapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.edu.unirp.android.listatarefaapp.Model.Tarefa;
import br.com.edu.unirp.android.listatarefaapp.Model.TarefaModel;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {


        private EditText txtNome;
        private EditText txtDescricao;
        private Button btnSalvar;
        private Tarefa tarefa = new Tarefa();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_update);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("Edita Tarefa");

            txtNome = (EditText) findViewById(R.id.txtNome);
            txtDescricao = (EditText) findViewById(R.id.txtDescricao);
            btnSalvar = (Button) findViewById(R.id.btnSalvar);

            btnSalvar.setOnClickListener(this);

            int id = getIntent().getExtras().getInt("id");

            TarefaModel model = new TarefaModel(this);
            tarefa = model.Read(id);

            txtNome.setText(tarefa.getNome());
            txtDescricao.setText(tarefa.getDescricao());

        }

        @Override
        public void onClick(View view) {
            if(view == btnSalvar){//tem que informar qual componente clicou

                tarefa.setNome(txtNome.getText().toString());
                tarefa.setDescricao(txtDescricao.getText().toString());


                //TODO: Invocar o método para inserir ao banco de dados
                TarefaModel model = new TarefaModel(this);
                model.update(tarefa);
                finish();
            }
        }
    }
