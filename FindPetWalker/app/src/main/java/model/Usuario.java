package model;

import android.content.res.Configuration;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import br.com.edu.unirp.android.findpetwalker.config.ConfiguracaoFirebase;

/**
 * Created by Vinicius on 18/10/2017.
 */

public class Usuario {

    private String codigo;
    private String nome;
    private String endereco;
    private String cidade;
    private String email;

    public Usuario() {

    }

    public void salvar(){
        DatabaseReference referenceFirebase = ConfiguracaoFirebase.getFirebase();
        referenceFirebase.child("usuarios").child(getCodigo()).setValue(this);
    }

    @Exclude
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    private String telefone;
    private String senha;
}
