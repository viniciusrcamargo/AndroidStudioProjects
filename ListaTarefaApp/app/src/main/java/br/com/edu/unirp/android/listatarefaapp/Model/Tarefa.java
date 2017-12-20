package br.com.edu.unirp.android.listatarefaapp.Model;

/**
 * Created by Vinicius on 26/08/2017.
 */

public class Tarefa {
    private int id;
    private String nome;
    private String descricao;
    private boolean concluido;

    /*gerar get e set automático Alt+Insert*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    @Override
    public String toString(){
        return this.getNome();
    }
}
