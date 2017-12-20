package br.com.edu.unirp.android.listatarefaapp.Model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinicius on 02/09/2017.
 */

public class TarefaModel extends SQLiteOpenHelper{

    private static final String NOME_BANCO = "tarefas.db";
    private static final int VERSAO_BANCO = 1;

    public TarefaModel(Context context){
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists tarefas(" +
        "id integer primary key autoincrement, " +
        "nome text not null," +
                "descricao text not null, concluida boolean" +
        ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void create(Tarefa e){
        SQLiteDatabase db = getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("nome", e.getNome());
            values.put("descricao", e.getDescricao());
            values.put("concluida", false);
            db.insert("tarefas", null, values);
        }finally {
         db.close();
        }
    }

    public List<Tarefa> Read(){
        List<Tarefa> lista = new ArrayList<Tarefa>();
        SQLiteDatabase db = getWritableDatabase();

        try{
            Cursor c = db.query("tarefas",null,null,null,null,null,null);

            if(c.moveToFirst()){
                do{
                    Tarefa tarefa = new Tarefa();
                    tarefa.setId(c.getInt(c.getColumnIndex("id")));
                    tarefa.setNome(c.getString(c.getColumnIndex("nome")));
                    tarefa.setDescricao(c.getString(c.getColumnIndex("descricao")));
                    tarefa.setConcluido(c.getInt(c.getColumnIndex("concluida"))==1);

                    lista.add(tarefa);

                }while(c.moveToNext());
            }
        }finally {
            db.close();
        }
        return lista;
    }

    //só retorna um objeto
    public Tarefa Read(int id){


        SQLiteDatabase db = getWritableDatabase();

        try{
            //select * from tarefas where = 1
            Cursor c = db.query("tarefas",null,"id=?",new String[]{String.valueOf(id)},null,null,null);

            if(c.moveToFirst()){
                    Tarefa tarefa = new Tarefa();
                    tarefa.setId(c.getInt(c.getColumnIndex("id")));
                    tarefa.setNome(c.getString(c.getColumnIndex("nome")));
                    tarefa.setDescricao(c.getString(c.getColumnIndex("descricao")));
                    tarefa.setConcluido(c.getInt(c.getColumnIndex("concluida"))==1);

                    return tarefa;
            }
        }finally {
            db.close();
        }
        return null;
    }

    public void update(Tarefa e){

        SQLiteDatabase db = getWritableDatabase();

        try{

            ContentValues values = new ContentValues();
            values.put("nome",e.getNome());
            values.put("descricao",e.getDescricao());
            values.put("concluida",e.isConcluido());

            db.update("tarefas", values, "id=?", new String[]{String.valueOf(e.getId())});

        }catch (Exception ex) {
            Log.e("db", ex.getMessage());
        }
            finally
        {
            db.close();
        }
    }

    public void Delete(int id){
        //TODO: Tarefa de casa.
    }
}
