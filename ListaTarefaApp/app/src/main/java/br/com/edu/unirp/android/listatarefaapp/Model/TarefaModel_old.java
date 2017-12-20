package br.com.edu.unirp.android.listatarefaapp.Model;

/**
 * Created by Vinicius on 26/08/2017.
 */
import java.util.*;

public class TarefaModel_old {

    //construtor para setar as tarefas na lista
    public TarefaModel_old(){
        Tarefa t1 = new Tarefa();
        t1.setNome("Tarefa 1");
        t1.setDescricao("Descrição da Tarefa 1");
        t1.setConcluido(true);

        Tarefa t2 = new Tarefa();
        t2.setNome("Tarefa 2");
        t2.setDescricao("Descrição da Tarefa 2");
        t2.setConcluido(false);

        if(this.tarefas.size() == 0){
            this.tarefas.add(t1);
            this.tarefas.add(t2);
        }
    }

    private static List<Tarefa> tarefas = new ArrayList<Tarefa>();

    public void Create(Tarefa e){
        this.tarefas.add(e);
    }

    public List<Tarefa> Read(){
        return tarefas;
    }

    public Tarefa Read(int id){
        for(Tarefa t : this.tarefas){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }

    public void Update(Tarefa e){
        for(Tarefa t : this.tarefas) {
            if (t.getId() == t.getId()) {
                t.setNome(e.getNome());
                t.setDescricao(e.getDescricao());
                t.setConcluido(e.isConcluido());
                break;
            }
        }
    }

    /**
     * Método para deletar os dados inseridos
     * @param id id para excluir determinado registro
     */
    public void Delete(int id){
        for(Tarefa t : this.tarefas) {
            if (t.getId() == id) {
                this.tarefas.remove(t);
                break;
            }
    }
    }
}
