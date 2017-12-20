package br.com.edu.unirp.android.listatarefaapp;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.edu.unirp.android.listatarefaapp.Model.*;

/**
 * Created by Vinicius on 26/08/2017.
 */

public class ListaAdapter extends BaseAdapter {

    private List<Tarefa> tarefas;
    private Activity activity;

    public ListaAdapter(List<Tarefa> tarefas, Activity activity) {
        this.tarefas = tarefas;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.tarefas.size();
    }

    @Override
    public Object getItem(int position) {
        return tarefas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tarefas.get(position).getId();
    }

    @Override
    public View getView(int i, View view1, ViewGroup parent) {
        View view = this.activity.getLayoutInflater().inflate(R.layout.lista, parent, false);

        TextView tvNome = (TextView) view.findViewById(R.id.tvNome);
        TextView tvConcluida = (TextView) view.findViewById(R.id.tvConcluida);
        TextView tvDescricao = (TextView) view.findViewById(R.id.tvDescricao);

        tvNome.setText(tarefas.get(i).getNome());
        tvDescricao.setText(tarefas.get(i).getDescricao());
        tvConcluida.setText((tarefas.get(i).isConcluido() ? "concluida" : "não concluida"));
        tvConcluida.setTextColor((tarefas.get(i).isConcluido() ? Color.GREEN : Color.RED));//se for true = verde, falso = vermelho

        return view;
    }
}
